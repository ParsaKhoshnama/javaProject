import java.io.IOException;

public class AddCommodityRequest extends Request
{
   private PublicPropertiesOfGoods commodity;
   private Clerk clerk;
   AddCommodityRequest(Clerk clerk,String context,PublicPropertiesOfGoods commodity)throws IOException,ClassNotFoundException
   {
      super(clerk,context);
      this.clerk=clerk;
      this.commodity=commodity;
      this.getClerk().getListOfCertainClerkRequests().add(this);
      Admin.creatAdminObject().getRequestsOfclerks().add(this);
   }
   PublicPropertiesOfGoods getCommodity()
   {
      return this.commodity;
   }
   void setCommodity(PublicPropertiesOfGoods commodity)
   {
      this.commodity=commodity;
   }
   static boolean checkIDforAddingcommodityRequest(String ID)
   {
      for(int i=0;i<DigitalCommodity.getDigiritlaCommodityAL().size();i++)
      {

            if (DigitalCommodity.getDigiritlaCommodityAL().get(i).getID().equals(ID))
            {
              return false;
            }
      }
      for(int i=0;i<HomeAppliance.getListOfHomeAppliancesAl().size();i++)
      {
            if(HomeAppliance.getListOfHomeAppliancesAl().get(i).getID().equals(ID))
            {
               return false;
            }
      }
      for(int i=0;i<Garment.getListOfAllGarmentsAl().size();i++) {

         if (Garment.getListOfAllGarmentsAl().get(i).getID().equals(ID))
         {
            return false;
         }
      }
      return true;
   }
}
