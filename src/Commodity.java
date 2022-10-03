import java.io.Serializable;

public class Commodity implements Serializable
{
   private PublicPropertiesOfGoods good;
   private boolean useDiscount=false;
   private  int countOfDiscounts=0;
   private int count;
   Commodity(PublicPropertiesOfGoods commodity,int count)
   {
       this.good=commodity;
       this.count=count;
   }
   public boolean equals(Commodity commodity1,Commodity commodity2)
   {
      if(commodity1.getGood().getID().equals(commodity2.getGood().getID()))
         return true;
      else
         return false;
   }
   static  PublicPropertiesOfGoods findCommodity(String ID)
   {
      for(int i=0;i<DigitalCommodity.getDigiritlaCommodityAL().size();i++)
      {
         if(DigitalCommodity.getDigiritlaCommodityAL().get(i).getID().equals(ID))
         {
            if(DigitalCommodity.getDigiritlaCommodityAL().get(i) instanceof LapTop)
            {
               return (LapTop)DigitalCommodity.getDigiritlaCommodityAL().get(i);
            }
            if(DigitalCommodity.getDigiritlaCommodityAL().get(i) instanceof Mobile)
            {
               return (Mobile)DigitalCommodity.getDigiritlaCommodityAL().get(i);
            }
         }
      }
      for(int i=0;i<Garment.getListOfAllGarmentsAl().size();i++)
      {
         if(Garment.getListOfAllGarmentsAl().get(i).getID().equals(ID))
         {
            if(Garment.getListOfAllGarmentsAl().get(i) instanceof Dress)
            {
               return (Dress)Garment.getListOfAllGarmentsAl().get(i);
            }
            if(Garment.getListOfAllGarmentsAl().get(i) instanceof Shoes)
            {
               return (Shoes)Garment.getListOfAllGarmentsAl().get(i);

            }
         }
      }
      for(int i=0;i<HomeAppliance.getListOfHomeAppliancesAl().size();i++)
      {
         if(HomeAppliance.getListOfHomeAppliancesAl().get(i).getID().equals(ID))
         {
            if(HomeAppliance.getListOfHomeAppliancesAl().get(i) instanceof Television)
            {
               return (Television)HomeAppliance.getListOfHomeAppliancesAl().get(i);
            }
            if(HomeAppliance.getListOfHomeAppliancesAl().get(i) instanceof Stove)
            {
               return (Stove)HomeAppliance.getListOfHomeAppliancesAl().get(i);
            }
            if(HomeAppliance.getListOfHomeAppliancesAl().get(i) instanceof Refrigerator)
            {
               return (Refrigerator)HomeAppliance.getListOfHomeAppliancesAl().get(i);
            }
         }
      }
      System.out.println("wrong ID");
      return null;
   }
   void setUseDiscount(boolean useDiscount)
   {
      this.useDiscount=useDiscount;
   }
   static boolean checkCount(PublicPropertiesOfGoods good,int count)
   {
     return good.checkNumberForBasket(count);
   }
   int getCountOfDiscounts()
   {
      return this.countOfDiscounts;
   }
   void setCountOfDiscounts(int countOfDiscounts)
   {
      this.countOfDiscounts=countOfDiscounts;
   }
   boolean getUseDiscount()
   {
      return this.useDiscount;
   }
   int getCount()
   {
      return this.count;
   }
   void setCount(int count)
   {
      this.count=count;
   }
   PublicPropertiesOfGoods getGood()
   {
      return this.good;
   }

}
