import java.util.ArrayList;

public class Basket
{
    private Buyer buyer;
    private BuyFactor factor;
    private double priceOfBasket=0;
    private double priceWithoutDiscount=0;
    ArrayList<Commodity>commodityListBasket=new ArrayList<Commodity>();

    Basket(String userName,String passWord)
    {
        this.buyer=Buyer.findBuyer(userName,passWord);
        this.factor=null;
    }
    Buyer getBuyer()
    {
        return this.buyer;
    }
    ArrayList<Commodity> getCommodityListBasket()
    {
        return this.commodityListBasket;
    }
    void addCommodity(Commodity commodity)
    {
        this.commodityListBasket.add(commodity);
        this.priceOfBasket +=commodity.getCount()*commodity.getGood().getPriceAfterDiscount();
        this.priceWithoutDiscount+=commodity.getCount()*commodity.getGood().getPrice();
    }
    void removeCertainCommodity(Commodity commodity)
    {
        if(commodity==null)
        {
            System.out.println("wrong ID");
            return;
        }
        this.AmmendentCountOfCommodty(commodity.getCount(),commodity.getGood());
        this.priceOfBasket -=commodity.getCount()*commodity.getGood().getPriceAfterDiscount();
        this.priceWithoutDiscount -=commodity.getCount()*commodity.getGood().getPrice();
        for(int i=0;i<this.commodityListBasket.size();i++)
        {
            if(this.commodityListBasket.get(i).getGood().getID().equals(commodity.getGood().getID()))
            {
                this.commodityListBasket.remove(i);
                return;
            }
        }
    }
  private void AmmendentCountOfCommodty(int count,PublicPropertiesOfGoods good)
    {
        good.setCount(good.getCount()+count);
    }
    void changeNumberOfcertainCommodity(int previousCount,int newCount,Commodity commodity)
    {
        if(commodity==null)
        {
            System.out.println("wrong ID");
            return;
        }
        this.AmmendentCountOfCommodty(commodity.getCount(),commodity.getGood());
        if(Commodity.checkCount(commodity.getGood(),newCount)==false)
        {
            System.out.println("this number is more than the number of commodity in store");
            this.AmmendentCountOfCommodty(-commodity.getCount(),commodity.getGood());
            return;
        }
        commodity.setCount(newCount);
        this.priceOfBasket -=previousCount*commodity.getGood().getPriceAfterDiscount();
        this.priceOfBasket +=newCount*commodity.getGood().getPriceAfterDiscount();
        this.priceWithoutDiscount -=previousCount*commodity.getGood().getPrice();
        this.priceWithoutDiscount +=newCount*commodity.getGood().getPrice();

    }
    boolean buyBasket()
    {
        if(this.commodityListBasket.size()==0)
        {
            System.out.println("warning. basket is empty");
            return false;
        }
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
            return true;
        }
        else
        {
            System.out.println("your fund is not enough");
            return false;
        }
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
   void showBasket()
   {
       for (int i=0;i<this.commodityListBasket.size();i++)
       {
           System.out.println("commodity"+(i+1)+":");
           System.out.println("ID: "+this.commodityListBasket.get(i).getGood().getID());
           System.out.println("name: "+this.commodityListBasket.get(i).getGood().getName());
           System.out.println("count: "+this.commodityListBasket.get(i).getCount());
           System.out.println("______________________________________");
       }
   }

}
