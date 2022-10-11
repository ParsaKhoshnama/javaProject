import exceptions.CheckDefaultExceptions;
import exceptions.CheckMyExceptions;
import exceptions.InoperativeBuyException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Date;
import java.util.concurrent.Callable;

public class Basket implements Serializable {
    private Buyer buyer;
    private BuyFactor factor;
    private double priceOfBasket = 0;
    private double priceWithoutDiscount = 0;
    private ArrayList<Commodity> commodityListBasket = new ArrayList<Commodity>();

    Basket(String userName, String passWord) throws IOException,ClassNotFoundException
    {
        this.buyer = Buyer.findBuyer(userName, passWord);
        this.factor = null;
    }

    Buyer getBuyer() {
        return this.buyer;
    }

    ArrayList<Commodity> getCommodityListBasket() {
        return this.commodityListBasket;
    }

    void addCommodity(Commodity commodity) {
        this.commodityListBasket.add(commodity);
        this.priceOfBasket += commodity.getCountOfDiscounts() * commodity.getGood().getPriceAfterDiscount() + (commodity.getCount() - commodity.getCountOfDiscounts()) * commodity.getGood().getPrice();
        this.priceWithoutDiscount += commodity.getCount() * commodity.getGood().getPrice();
    }

    void setPriceOfBasket(double priceOfBasket) {
        this.priceOfBasket = priceOfBasket;
    }

    void setPriceWithoutDiscount(double priceWithoutDiscount) {
        this.priceWithoutDiscount = priceWithoutDiscount;
    }

    double getPriceOfBasket() {
        return this.priceOfBasket;
    }

    double getPriceWithoutDiscount() {
        return this.priceWithoutDiscount;
    }

    void removeCertainCommodity(Commodity commodity) {
        if (commodity == null) {
            System.out.println("wrong ID");
            return;
        }
        this.AmmendentCountOfCommodty(commodity.getCount(),commodity.getCountOfDiscounts(), commodity);
        this.priceOfBasket -=commodity.getCountOfDiscounts() * commodity.getGood().getPriceAfterDiscount() + (commodity.getCount() - commodity.getCountOfDiscounts()) * commodity.getGood().getPrice();
        this.priceWithoutDiscount -=commodity.getCount() * commodity.getGood().getPrice();
        for (int i = 0; i < this.commodityListBasket.size(); i++) {
            if (this.commodityListBasket.get(i).getGood().getID().equals(commodity.getGood().getID())) {
                this.commodityListBasket.remove(i);
                return;
            }
        }
    }

    private void AmmendentCountOfCommodty(int count,int countOfDiscount, Commodity commodity)
    {
        commodity.getGood().setCount(commodity.getGood().getCount() + count);
        commodity.getGood().getDiscount().setCapacity(countOfDiscount + commodity.getGood().getDiscount().getCapacity());
    }
    void removeCommodityForBuy() {
        Scanner sc = new Scanner(System.in);
        System.out.printf("enter ID: ");
        String ID = sc.nextLine();
        this.removeCertainCommodity(this.findIDinBasket(ID));
    }
    void returnCommodities(Commodity commodity)
    {
        commodity.getGood().getDiscount().setCapacity(commodity.getGood().getDiscount().getCapacity()+commodity.getCountOfDiscounts());
        commodity.getGood().setCount(commodity.getCount()+commodity.getGood().getCount());
        this.getListOfCommodities().remove(commodity);
    }
    void addCommodityForBuy() {
        CheckMyExceptions checkMyExceptions=new CheckMyExceptions();
        Scanner sc = new Scanner(System.in);
        String ID;
        System.out.printf("enter ID of commodity: ");
        ID = sc.nextLine();
        if (Commodity.findCommodity(ID) == null)
            System.out.println("wrong ID");
        else {
            int count=this.checkNumberOfGoodsForThrowingException(Commodity.findCommodity(ID));
            if(count<0)
                return;
            if (Commodity.checkCount(Commodity.findCommodity(ID), count) == true) {
                String discountCommand;
                Commodity commodity = new Commodity(Commodity.findCommodity(ID), count);
                while (true) {
                    System.out.printf("do you want to use discount (yes or no): ");
                    discountCommand = sc.nextLine();
                    if (discountCommand.equals("yes")) {
                        String codeOfDiscount;
                        while (true) {
                            System.out.printf("enter code of discount: ");
                            codeOfDiscount = sc.nextLine();
                            if (codeOfDiscount.equals(commodity.getGood().getDiscount().getCode())) {
                                Date date= GregorianCalendar.getInstance().getTime();
                                if(date.after(commodity.getGood().getDiscount().getDate()))
                                {
                                    System.out.println("time of discount has passed");
                                    return;
                                }
                                else {
                                    commodity.setUseDiscount(true);
                                    if (commodity.getGood().getDiscount().getCapacity() >= count) {
                                        commodity.setCountOfDiscounts(count);
                                        commodity.getGood().getDiscount().setCapacity(commodity.getGood().getDiscount().getCapacity() - count);
                                    } else {
                                        if (commodity.getGood().getDiscount().getCapacity() > 0) {
                                            System.out.println("the capacity of discount is " + commodity.getGood().getDiscount().getCapacity() + "  ----you can use this only");
                                            commodity.setCountOfDiscounts(commodity.getGood().getDiscount().getCapacity());
                                            commodity.getGood().getDiscount().setCapacity(0);
                                        } else
                                            System.out.println("the cpacity of discount is full");
                                        break;
                                    }
                                }
                            } else {
                                System.out.println("wrong ID. try again");
                                break;
                            }
                        }
                        if (codeOfDiscount.equals(commodity.getGood().getDiscount().getCode()))
                            break;
                    } else if (discountCommand.equals("no"))
                        break;
                    else
                        System.out.println("wrong command. try again");
                }
                this.addCommodity(commodity);
            } else
                System.out.println("this number is mor than that there are in store");
        }
    }
    void buyBasket()throws InoperativeBuyException
    {
        if(this.commodityListBasket.size()==0)
            System.out.println("warning. basket is empty");
        else if(this.buyer.getFund()>=this.priceOfBasket)
        {
            this.buyer.setFund(this.buyer.getFund()-this.priceOfBasket);
            this.factor=new BuyFactor(this.buyer,this.commodityListBasket,this.priceWithoutDiscount,this.priceOfBasket);
            this.buyer.getfactorsAl().add(this.factor);
            for (int i=0;i<this.commodityListBasket.size();i++)
            {
                SellFactor sellFactor=new SellFactor(this.buyer,this.commodityListBasket.get(i),
                        this.commodityListBasket.get(i).getCount()*this.commodityListBasket.get(i).getGood().getPrice(),
                        this.commodityListBasket.get(i).getCount()*this.commodityListBasket.get(i).getGood().getPriceAfterDiscount());

                this.commodityListBasket.get(i).getGood().getClerk().getSellFactors().add(sellFactor);
            }
            for(int i=0;i<this.commodityListBasket.size();i++)
            {
                this.buyer.getBoughtCommoditiesList().add(this.commodityListBasket.get(i));
            }
            System.out.println("your buying was successfully");
        }
        else
            this.throwExceptionForBuy();
    }
    ArrayList<Commodity> getListOfCommodities()
    {
        return this.commodityListBasket;
    }
   Commodity findIDinBasket(String ID)
   {
       for (int i=0;i<this.commodityListBasket.size();i++)
       {
           if(this.commodityListBasket.get(i).getGood().getID().equals(ID))
           {
               return this.commodityListBasket.get(i);
           }
       }
       return null;
   }
     void changeCountofGoodForBuy(Commodity commodity)
    {
        Scanner sc=new Scanner(System.in);
            System.out.printf("enter new number: ");
            int newCount=sc.nextInt();
            this.AmmendentCountOfCommodty(commodity.getCount(),commodity.getCountOfDiscounts(), commodity);
            if (Commodity.checkCount(commodity.getGood(), newCount) == false)
            {
                System.out.println("this number is more than the number of commodity in store");
                this.AmmendentCountOfCommodty(-commodity.getCount(),-commodity.getCountOfDiscounts(), commodity);
                return;
            }
            this.priceOfBasket -=commodity.getCountOfDiscounts()*commodity.getGood().getPriceAfterDiscount()+(commodity.getCount()-commodity.getCountOfDiscounts())*commodity.getGood().getPrice();
            this.priceWithoutDiscount-=commodity.getCount()*commodity.getGood().getPrice();
            this.priceWithoutDiscount+=newCount*commodity.getGood().getPrice();
            commodity.setCount(newCount);
            if(commodity.getUseDiscount()==true)
            {
                if(commodity.getGood().getDiscount().getCapacity()>=newCount)
                {
                    commodity.getGood().getDiscount().setCapacity(commodity.getGood().getDiscount().getCapacity()-newCount);
                    commodity.setCountOfDiscounts(newCount);
                   // this.setPriceOfBasket(newCount*commodity.getGood().getPriceAfterDiscount());
                    this.priceOfBasket +=newCount*commodity.getGood().getPriceAfterDiscount();
                }
                else
                {
                    commodity.setCountOfDiscounts(commodity.getGood().getDiscount().getCapacity());
                   // this.setPriceOfBasket(commodity.getGood().getDiscount().getCapacity()*commodity.getGood().getPriceAfterDiscount()+(newCount-commodity.getGood().getDiscount().getCapacity())*commodity.getGood().getPrice());
                    this.priceOfBasket +=commodity.getGood().getDiscount().getCapacity()*commodity.getGood().getPriceAfterDiscount()+(newCount-commodity.getGood().getDiscount().getCapacity())*commodity.getGood().getPrice();
                    commodity.getGood().getDiscount().setCapacity(0);
                }
            }
    }
    void useDiscountForChangeFunction(Commodity commodity)
    {
        Date date= GregorianCalendar.getInstance().getTime();
        if(date.after(commodity.getGood().getDiscount().getDate()))
        {
            System.out.println("time of discount has passed");
            return;
        }
        Scanner sc=new Scanner(System.in);
       if(commodity.getUseDiscount()==true)
       {
           System.out.println("you have used this discount");
           return;
       }
       else
       {
           String code;
           while (true)
           {
               System.out.printf("enter code of discount: ");
               code= sc.nextLine();
               if(code.equals(commodity.getGood().getDiscount().getCode()))
               {
                   if (commodity.getGood().getDiscount().getCapacity() >= commodity.getCount()) {
                       this.priceOfBasket -=commodity.getCountOfDiscounts() * commodity.getGood().getPriceAfterDiscount() + (commodity.getCount() - commodity.getCountOfDiscounts()) * commodity.getGood().getPrice();
                       commodity.setCountOfDiscounts(commodity.getCount());
                       commodity.getGood().getDiscount().setCapacity(commodity.getGood().getDiscount().getCapacity() - commodity.getCount());
                   }
                   else {
                       if (commodity.getGood().getDiscount().getCapacity() > 0) {
                           this.priceOfBasket -=commodity.getCountOfDiscounts() * commodity.getGood().getPriceAfterDiscount() + (commodity.getCount() - commodity.getCountOfDiscounts()) * commodity.getGood().getPrice();
                           System.out.println("the capacity of discount is " + commodity.getGood().getDiscount().getCapacity() + " ---you can use this only");
                           commodity.setCountOfDiscounts(commodity.getGood().getDiscount().getCapacity());
                           commodity.getGood().getDiscount().setCapacity(0);
                       } else {
                           System.out.println("the capacity of discount is full");
                            return;
                       }
                   }
                   this.priceOfBasket +=commodity.getCountOfDiscounts()*commodity.getGood().getPriceAfterDiscount()+(commodity.getCount()-commodity.getCountOfDiscounts())*commodity.getGood().getPrice();
                   return;
               }
               else
               {
                   String command;
                   while (true)
                   {
                       System.out.printf("wrong ID . press try again or leave: ");
                       command=sc.nextLine();
                       if(command.equals("leave"))
                           return;
                       else if(commodity.equals("try again"))
                           break;
                       else
                           System.out.println("wrong command");
                   }

               }
           }
       }
    }
    int checkNumberOfGoodsForThrowingException(PublicPropertiesOfGoods publicPropertiesOfGoods)
    {
        CheckDefaultExceptions checkDefaultExceptions=new CheckDefaultExceptions();
        System.out.printf("enter the count of good witch you want: ");
        int count=checkDefaultExceptions.checkInt("enter the count of good witch you want");
        if(count<0)
            return count;
        try
        {
            if(count>publicPropertiesOfGoods.getCount())
               throw new InoperativeBuyException("the count of this good witch you want is more than the count of stock for this good");
            return count;
        }
        catch (InoperativeBuyException inoperativeBuyException)
        {
            System.out.println(inoperativeBuyException.getMessage());
            inoperativeBuyException.printStackTrace();
            return -1;
        }

    }
    private void throwExceptionForBuy()
    {
        try
        {
            throw new InoperativeBuyException("the price of basket is more than your fund");
        }
        catch (InoperativeBuyException inoperativeBuyException)
        {
            System.out.println(inoperativeBuyException.getMessage());
            inoperativeBuyException.printStackTrace();
            return;
        }
    }
   void showBasket()
   {
       for (int i=0;i<this.commodityListBasket.size();i++)
       {
           System.out.println("commodity"+(i+1)+":");
           System.out.println("ID: "+this.commodityListBasket.get(i).getGood().getID());
           System.out.println("name: "+this.commodityListBasket.get(i).getGood().getName());
           System.out.println("count: "+this.commodityListBasket.get(i).getCount());
           System.out.println("count of discount: "+this.commodityListBasket.get(i).getCountOfDiscounts());
           System.out.println("______________________________________");
       }
   }

}
