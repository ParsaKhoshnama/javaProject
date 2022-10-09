import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class ChangeInformationOfCommodityRequest extends Request implements Serializable
{
    private PublicPropertiesOfGoods currentCommodity;
    private PublicPropertiesOfGoods reformedCommodity;
    ChangeInformationOfCommodityRequest(Clerk clerk,String context,PublicPropertiesOfGoods currentCommodity,PublicPropertiesOfGoods reformedCommodity)throws IOException,ClassNotFoundException
    {
        super(clerk,context);
        this.currentCommodity=currentCommodity;
        this.reformedCommodity=reformedCommodity;
        this.getClerk().getListOfCertainClerkRequests().add(this);
        this.addRequest();
        this.getClerk().addRequestInFileForClerk(this);
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
    void changeCommodityObjectInFiles()throws IOException,ClassNotFoundException
    {
        if(this.getCurrentCommodity() instanceof LapTop)
        {
            File laptopFolder=new File("saved data\\categories\\Digitals\\lap tops\\"+"lap top "+this.getReformedCommodity().getID());
            ((LapTop) this.getReformedCommodity()).editCommodityInFile();
            ((LapTop)this.getReformedCommodity()).editProperties(laptopFolder);
        }
        else if(this.getCurrentCommodity() instanceof Mobile)
        {
            File mobileFolder=new File("saved data\\categories\\Digitals\\lap tops\\"+"mobile "+this.getCurrentCommodity().getID());
            ((Mobile)this.getCurrentCommodity()).editCommodityInFile();
            ((Mobile)this.getReformedCommodity()).editProperties(mobileFolder);
        }
    }
}
