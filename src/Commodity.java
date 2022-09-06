public class Commodity
{
   private PublicPropertiesOfGoods good;
   private int count;
   Commodity(PublicPropertiesOfGoods commodity,int count)
   {
       this.good=commodity;
       this.count=count;
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
   static boolean checkCount(PublicPropertiesOfGoods good,int count)
   {
     return good.checkNumberForBasket(count);
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
