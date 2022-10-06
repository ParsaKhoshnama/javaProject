import exceptions.CheckDefaultExceptions;
import workWithFiles.MyObjectOutPutStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.Scanner;

public class Buyer extends Person implements Serializable
{

    private double fund=0;
    private static ArrayList<Buyer> buyersListAl=new ArrayList<Buyer>();
    private  ArrayList<Commodity> boughtCommodities=new ArrayList<Commodity>();
    private ArrayList<BuyFactor>factors=new ArrayList<BuyFactor>();
    Buyer(String userName,String passWord,String name,String lastName,String phoneNumber,String eMail)
    {
        super(userName,passWord, name,lastName,phoneNumber,eMail);

    }
    double getFund()
    {
        return this.fund;
    }
    void increaseFund(double fund)
    {
        this.fund+=fund;
    }
    void setFund()
    {
        CheckDefaultExceptions checkDefaultExceptions=new CheckDefaultExceptions();
        int fund=checkDefaultExceptions.checkInt("enter the number of money");
        if(fund<0)
            return;
        this.fund=this.getFund()+fund;
    }
    void setFund(double fund)
    {
        this.fund=fund;
    }
    static boolean searchBuyrListAl(String userName,String passWord)
    {
        System.out.println(buyersListAl);
        for(int i=0;i<buyersListAl.size();i++)
        {
            if(buyersListAl.get(i).getUserName().equals(userName) && buyersListAl.get(i).getPassWord().equals(passWord))
                return true;
        }
        return false;
    }
   static ArrayList<Buyer> getBuyersListAl()
    {
        return buyersListAl;
    }
  static void addBuyerToBuyerListAl(Buyer buyer)
  {
      buyersListAl.add(buyer);
  }
  ArrayList<Commodity> getBoughtCommoditiesList()
  {
      return this.boughtCommodities;
  }
  static Buyer findBuyer(String userName,String passWord)
  {
      for (int i=0;i<buyersListAl.size();i++)
      {
          if(buyersListAl.get(i).getUserName().equals(userName))
          {
              if(buyersListAl.get(i).getPassWord().equals(passWord))
              {
                  return buyersListAl.get(i);
              }
          }
      }
      return null;
  }
  ArrayList<BuyFactor> getfactorsAl()
  {
      return this.factors;
  }
  void showBuyFactors()
  {
      if(this.factors.size()==0)
      {
          System.out.println("you don't have any factor");
          return;
      }
      for(int i=0;i<this.factors.size();i++)
      {
          System.out.println("Factor "+(i+1)+":");
          System.out.println("date: "+this.factors.get(i).getDateString());
          System.out.println("total price without discounts: "+this.factors.get(i).getPrice());
          System.out.println("total + price with discount(payed): "+this.factors.get(i).getPriceAfterDiscounts());
          System.out.println("Commodities:");
          for( int j=0;j<this.factors.get(i).getFactorCommoditiesAl().size();j++)
          {
              System.out.println("number "+(j+1));
              System.out.printf("commodity name: ");
              System.out.println(this.factors.get(i).getFactorCommoditiesAl().get(j).getGood().getName());
              System.out.println(this.factors.get(i).getFactorCommoditiesAl().get(j).getGood().getID());
              System.out.println("price: "+this.factors.get(i).getFactorCommoditiesAl().get(j).getGood().getPrice());
              System.out.println("percent of discount: "+this.factors.get(i).getFactorCommoditiesAl().get(j).getGood().getDiscount().getPercentOfDiscount());
              System.out.println("count: "+this.factors.get(i).getFactorCommoditiesAl().get(j).getCount());
              System.out.println("total price of this commodity: "+(this.factors.get(i).getFactorCommoditiesAl().get(j).getGood().getPriceAfterDiscount()*this.factors.get(i).getFactorCommoditiesAl().get(j).getCount()));
              System.out.println("Clerk information:");
              System.out.println("userName: "+this.factors.get(i).getFactorCommoditiesAl().get(j).getGood().getClerk().getUserName());
              System.out.println("name: "+this.factors.get(i).getFactorCommoditiesAl().get(j).getGood().getClerk().getName()+"      last name: "+this.factors.get(i).getFactorCommoditiesAl().get(j).getGood().getClerk().getLastName());
              System.out.println("-------------");
          }
          System.out.println("_______________________________________________________________________________________");
      }
  }
    static void showBuyerInformation(String password)
    {
        for(int i=0;i<buyersListAl.size();i++)
        {
            if(buyersListAl.get(i).getPassWord().equals(password))
            {
                System.out.println("name: "+buyersListAl.get(i).getName());
                System.out.println("last name: "+buyersListAl.get(i).getLastName());
                System.out.println("user name: "+buyersListAl.get(i).getUserName());
                System.out.println("phone number: "+buyersListAl.get(i).getPhoneNumber());
                System.out.println("email: "+ buyersListAl.get(i).geteMail());
                return;
            }
        }
    }
    void showAllGoods()
    {
        if(DigitalCommodity.getDigiritlaCommodityAL().size()==0 && Garment.getListOfAllGarmentsAl().size()==0 && HomeAppliance.getListOfHomeAppliancesAl().size()==0)
        {
            System.out.println("there isn't any good for show");
            return;
        }
        this.showDigitals();
        this.showHomeAppliance();
        this.showGarment();
    }
    void showDigitals()
    {
        System.out.println("Digital commodities: ");
        for (int i=0;i<DigitalCommodity.getDigiritlaCommodityAL().size();i++)
        {
            if(DigitalCommodity.getDigiritlaCommodityAL().get(i) instanceof LapTop)
            {
                DigitalCommodity.getDigiritlaCommodityAL().get(i).showCommodity();
            }
        }
        for (int i=0;i<DigitalCommodity.getDigiritlaCommodityAL().size();i++)
        {
            if(DigitalCommodity.getDigiritlaCommodityAL().get(i) instanceof Mobile)
                DigitalCommodity.getDigiritlaCommodityAL().get(i).showCommodity();
        }
        System.out.println("___________________________________________________");
    }
    void showHomeAppliance()
    {
        System.out.println("home appliance commodities: ");
        for (int i=0;i<HomeAppliance.getListOfHomeAppliancesAl().size();i++)
        {
            if(HomeAppliance.getListOfHomeAppliancesAl().get(i) instanceof Television)
                HomeAppliance.getListOfHomeAppliancesAl().get(i).showCommodity();
        }
        for (int i=0;i<HomeAppliance.getListOfHomeAppliancesAl().size();i++)
        {
            if(HomeAppliance.getListOfHomeAppliancesAl().get(i) instanceof Refrigerator)
                HomeAppliance.getListOfHomeAppliancesAl().get(i).showCommodity();
        }
        for (int i=0;i<HomeAppliance.getListOfHomeAppliancesAl().size();i++)
        {
            if(HomeAppliance.getListOfHomeAppliancesAl().get(i) instanceof Stove)
                HomeAppliance.getListOfHomeAppliancesAl().get(i).showCommodity();
        }
        System.out.println("___________________________________________________");
    }
    void showGarment()
    {
        System.out.println("Garment commodities: ");
        for(int i=0;i<Garment.getListOfAllGarmentsAl().size();i++)
        {
            if(Garment.getListOfAllGarmentsAl().get(i) instanceof Dress)
                Garment.getListOfAllGarmentsAl().get(i).showCommodity();
        }
        for(int i=0;i<Garment.getListOfAllGarmentsAl().size();i++)
        {
            if(Garment.getListOfAllGarmentsAl().get(i) instanceof Shoes)
                Garment.getListOfAllGarmentsAl().get(i).showCommodity();
        }
    }
    void giveMark()
    {
        Scanner sc=new Scanner(System.in);
        String ID;
        while (true)
        {
            System.out.printf("enter the ID: ");
            ID=sc.nextLine();
            if(Score.checkAbilityForgivingScore(this.getUserName(),this.getPassWord(),ID)==true)
                break;
            else
            {
                System.out.println("wrong ID. try again oe enter leave");
            }
            if(ID.equals("leave"))
                return;
        }
        System.out.printf("enter score: ");
        double mark=sc.nextDouble();
        sc.nextLine();
        Score score=new Score(this.getUserName(),this.getPassWord(),ID,mark);
    }
    void buy()
    {
        Scanner sc=new Scanner(System.in);
        Basket basket=new Basket(this.getUserName(),this.getPassWord());
        String buyCommand;
        while (true)
        {
            System.out.printf("enter command (change commodity),(remove commodity),(change count of a good),(buy basket),(leave): ");
            buyCommand=sc.nextLine();
            if(buyCommand.equals("leave"))
            {
                for (int i=0;i<basket.getListOfCommodities().size();i++)
                {
                    basket.returnCommodities(basket.getListOfCommodities().get(i));
                }
                break;
            }
            else if(buyCommand.equals("add commodity"))
            {
                basket.addCommodityForBuy();
            }
            else if(buyCommand.equals("remove commodity"))
            {
              basket.removeCommodityForBuy();
            }
            else if(buyCommand.equals("change commodity"))
            {
                this.changeCommodityFunction(basket);
            }
            else if(buyCommand.equals("buy basket"))
            {
                basket.buyBasket();
                break;
            }
            else if(buyCommand.equals("show basket"))
            {
                basket.showBasket();
            }
            else
                System.out.println("wrong command");
        }
    }

    static void buyerRegisteration(String firstName,String lastName,String phoneNumber,String passWord,String userName,String eMail)throws IOException,ClassNotFoundException
    {
        Scanner sc=new Scanner(System.in);
        if(Person.checkPersonsInformation(firstName,lastName,phoneNumber,eMail,passWord,userName))
        {
            Buyer buyer=new Buyer(userName,passWord,firstName,lastName,phoneNumber,eMail);
            Admin.creatAdminObject().addPersonToPersonsListAL(buyer);
            Buyer.addBuyerToBuyerListAl(buyer);
            buyer.createBuyerDirectoryAndFiles();
        }
    }
    void changeCommodityFunction(Basket basket)
    {
        Scanner sc=new Scanner(System.in);
        System.out.printf("enter ID: ");
        String ID=sc.nextLine();
        if(basket.findIDinBasket(ID)==null)
        {
            System.out.println("wrong ID");
            return;
        }
        String changeCommodityCommand;
        while (true)
        {
            System.out.printf("enter command (change count of commodity) or (use discount) or (leave): ");
            changeCommodityCommand=sc.nextLine();
            if(changeCommodityCommand.equals("change count of commodity"))
                basket.changeCountofGoodForBuy(basket.findIDinBasket(ID));
            else if(changeCommodityCommand.equals("use discount"))
                basket.useDiscountForChangeFunction(basket.findIDinBasket(ID));
            else if(changeCommodityCommand.equals("leave"))
                return;
            else
                System.out.println("wrong command");
        }
    }
    private void createBuyerDirectoryAndFiles()throws IOException
    {
        StringBuilder buyerFolderName=new StringBuilder("buyer ");
        buyerFolderName.append(this.getUserName());
        StringBuilder buyerFolderPath=new StringBuilder("saved data\\users\\buyers\\");
        buyerFolderPath.append(buyerFolderName.toString());
        File buyerFolder=new File(buyerFolderPath.toString());
        buyerFolder.mkdir();
        this.writePropertiesOfBuyerOnFile(buyerFolder);
        File listOfPersonsForAdmin=new File("saved data\\users\\admin\\users.txt");
        MyObjectOutPutStream.setFile(listOfPersonsForAdmin);
        MyObjectOutPutStream myObjectOutPutStream=new MyObjectOutPutStream(listOfPersonsForAdmin);
        myObjectOutPutStream.writeObject(this);
        myObjectOutPutStream.close();
        File buyersList=new File("saved data\\users\\buyers\\list of buyers.txt");
        MyObjectOutPutStream.setFile(buyersList);
        MyObjectOutPutStream objectOutPutStream=new MyObjectOutPutStream(buyersList);
        objectOutPutStream.writeObject(this);
        objectOutPutStream.close();
        File listOfBuyFactors=new File(buyerFolder,"buy factors.txt");
        listOfBuyFactors.createNewFile();
        File listOfGoods=new File(buyerFolder,"goods.txt");
        listOfGoods.createNewFile();
    }
      void writePropertiesOfBuyerOnFile(File file)throws IOException
    {
        File buyerProperties=new File(file,"properties.txt");
        buyerProperties.createNewFile();
        FileOutputStream fileOutputStream=new FileOutputStream(buyerProperties);
        Formatter formatter=new Formatter(fileOutputStream);
        formatter.format("first name: %s\n",this.getName());
        formatter.format("last name: %s\n",this.getLastName());
        formatter.format("username: %s\n",this.getUserName());
        formatter.format("email: %s\n",this.geteMail());
        formatter.format("phone number: %s",this.getPhoneNumber());
        formatter.close();
        fileOutputStream.close();
    }
}