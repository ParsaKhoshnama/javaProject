import java.io.IOException;

public class ChangeInformationOfCommodityRequest extends Request
{
    private PublicPropertiesOfGoods currentCommodity;
    private PublicPropertiesOfGoods reformedCommodity;
    ChangeInformationOfCommodityRequest(Clerk clerk,String context,PublicPropertiesOfGoods currentCommodity,PublicPropertiesOfGoods reformedCommodity)throws IOException
    {
        super(clerk,context);
        this.currentCommodity=currentCommodity;
        this.reformedCommodity=reformedCommodity;
        this.getClerk().getListOfCertainClerkRequests().add(this);
        Admin.creatAdminObject().getRequestsOfclerks().add(this);
    }
    PublicPropertiesOfGoods getCurrentCommodity()
    {
        return this.currentCommodity;
    }
    void setCurrentCommodity(PublicPropertiesOfGoods currentCommodity)
    {
        this.currentCommodity=currentCommodity;
    }
    PublicPropertiesOfGoods getReformedCommodity()
    {
        return this.reformedCommodity;
    }
    void setReformedCommodity(PublicPropertiesOfGoods reformedCommodity)
    {
        this.reformedCommodity=reformedCommodity;
    }
    static boolean checkIDforChangeInformationOfcommodityRequest(String ID,String password)
    {
        for(int i=0;i<DigitalCommodity.getDigiritlaCommodityAL().size();i++)
        {
                if (DigitalCommodity.getDigiritlaCommodityAL().get(i).getID().equals(ID) && DigitalCommodity.getDigiritlaCommodityAL().get(i).getClerk().getPassWord().equals(password))
                {
                    return true;
                }
        }
        for(int i=0;i<HomeAppliance.getListOfHomeAppliancesAl().size();i++)
        {
                if(HomeAppliance.getListOfHomeAppliancesAl().get(i).getID().equals(ID) && HomeAppliance.getListOfHomeAppliancesAl().get(i).getClerk().getPassWord().equals(password))
                {
                    return true;
                }
        }
        for(int i=0;i<Garment.getListOfAllGarmentsAl().size();i++)
        {

                if(Garment.getListOfAllGarmentsAl().get(i).getID().equals(ID) && Garment.getListOfAllGarmentsAl().get(i).getClerk().getPassWord().equals(password))
                {
                        return true;
                }
        }
        return false;
    }
}
