import java.io.IOException;

public abstract class Request
{
   private String context;
   private Clerk clerk;

   private   String statusOfRequestforAdmin;//reject accept ignore
     boolean statusOfAddingRequestToREquestListOfAdmin;
   Request(Clerk clerk, String context)
   {
       this.clerk=clerk;
       this.context=context;
       this.statusOfRequestforAdmin="ignore";
       this.statusOfAddingRequestToREquestListOfAdmin=false;
   }
   void setClerk(Clerk clerk)
   {
       this.clerk=clerk;
   }
   Clerk getClerk()
   {
       return this.clerk;
   }
   void setContext(String context)
   {
       this.context=context;
   }
   String getContext()
   {
       return this.context;
   }
    void addRequest()throws IOException,ClassNotFoundException
    {
        if(this.statusOfAddingRequestToREquestListOfAdmin==false)
        {
            this.statusOfAddingRequestToREquestListOfAdmin=true;
            Admin.creatAdminObject().getRequestsOfclerks().add(this);
            Admin.creatAdminObject().getIgonredRequestsOfclerks().add(this);
        }
        else
            System.out.println("your request has been sended before");
    }
    void setStatusOfRequestforAdmin(String statusOfRequestforAdmin)throws IOException,ClassNotFoundException
    {
        this.statusOfRequestforAdmin=statusOfRequestforAdmin;
        if(statusOfRequestforAdmin.equals("accept"))
        {
            if(this instanceof BeClerkRequest)
            {
                Admin.creatAdminObject().addPersonToPersonsListAL(this.getClerk());
                Clerk.addToClerkListAl(this.getClerk());
              //  Admin.creatAdminObject().addPersonToPersonsListAL(this.getClerk());
                this.getClerk().addToListOfCertainClerkRequests(this);
            }
            else if(this instanceof AddCommodityRequest)
            {
                this.AddcommodityRequestFunction();
            }
            else if(this instanceof RemoveCommodityRequest)
            {
                this.removeCommodity();
                this.getClerk().removeCommodity(((RemoveCommodityRequest) this).getCommodity());
            }
            else
            {
                this.changeCommodityInformationRequestFunction();
                this.getClerk().changeCommodityInformationOfCertainClerk(((ChangeInformationOfCommodityRequest)this).getCurrentCommodity(),((ChangeInformationOfCommodityRequest)this).getReformedCommodity());
            }
        }
    }
    String getStatusOfRequestforAdmin()
    {
        return this.statusOfRequestforAdmin;
    }
    void AddcommodityRequestFunction()
    {
        if(((AddCommodityRequest)this).getCommodity() instanceof LapTop)
        {
            ((LapTop)((AddCommodityRequest)this).getCommodity()).creatLaptopAfterGettingAccept();
        }
        else if(((AddCommodityRequest)this).getCommodity() instanceof Mobile)
        {
            ((Mobile)((AddCommodityRequest)this).getCommodity()).creatMobileAfterGettingAccept();
        }
        else if(((AddCommodityRequest)this).getCommodity() instanceof Dress)
        {
            ((Dress)((AddCommodityRequest)this).getCommodity()).creatDressAfterGetingAccept();
        }
        else if(((AddCommodityRequest)this).getCommodity() instanceof Shoes)
        {
            ((Shoes)((AddCommodityRequest)this).getCommodity()).createShoesAfterGettingAccept();
        }
        else if(((AddCommodityRequest)this).getCommodity() instanceof Television)
        {
            ((Television)((AddCommodityRequest)this).getCommodity()).creatTelevisionAterGettingAccept();
        }
        else if(((AddCommodityRequest)this).getCommodity() instanceof Refrigerator)
        {
            ((Refrigerator)((AddCommodityRequest)this).getCommodity()).createRefirgeratorAfterGettingAccept();
        }
        else
        {
            ((Stove)((AddCommodityRequest)this).getCommodity()).createStoveAfterGettingAccept();
        }
    }
    void changeCommodityInformationRequestFunction()
    {
        if(((ChangeInformationOfCommodityRequest)this).getCurrentCommodity() instanceof LapTop ||((ChangeInformationOfCommodityRequest)this).getCurrentCommodity() instanceof Mobile)
        {
            ((DigitalCommodity)(((ChangeInformationOfCommodityRequest) this).getCurrentCommodity())).changeInformationRequestDigitlCommodity(this);
        }
        else if(((ChangeInformationOfCommodityRequest)this).getCurrentCommodity() instanceof Dress ||((ChangeInformationOfCommodityRequest)this).getCurrentCommodity() instanceof Shoes)
        {
            ((Garment)(((ChangeInformationOfCommodityRequest) this).getCurrentCommodity())).changeInformationRequestGarmentCommodity(this);
        }
        else
        {
            ((HomeAppliance)(((ChangeInformationOfCommodityRequest) this).getCurrentCommodity())).changeInformationRequestHomeApplianceCommodity(this);
        }
    }
    private void removeCommodity()
    {
        for(int i=0;i<DigitalCommodity.getDigiritlaCommodityAL().size();i++)
        {
            if(DigitalCommodity.getDigiritlaCommodityAL().get(i).getID().equals(((RemoveCommodityRequest)this).getCommodity().getID()))
            {
                DigitalCommodity.getDigiritlaCommodityAL().remove(i);
                return;
            }
        }
        for (int i=0;i<HomeAppliance.getListOfHomeAppliancesAl().size();i++)
        {
            if(HomeAppliance.getListOfHomeAppliancesAl().get(i).getID().equals(((RemoveCommodityRequest)this).getCommodity().getID()))
            {
                HomeAppliance.getListOfHomeAppliancesAl().remove(i);
                return;
            }
        }
        for (int i=0;i<Garment.getListOfAllGarmentsAl().size();i++)
        {
            if(Garment.getListOfAllGarmentsAl().get(i).getID().equals(((RemoveCommodityRequest)this).getCommodity().getID()))
            {
                Garment.getListOfAllGarmentsAl().remove(i);
                return;
            }
        }
    }
}
