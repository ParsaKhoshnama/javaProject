import java.io.IOException;
import java.io.Serializable;

public class RemoveCommodityRequest extends Request implements Serializable
{
    private PublicPropertiesOfGoods commodity;
    RemoveCommodityRequest(Clerk clerk,PublicPropertiesOfGoods commodity,String context)throws IOException,ClassNotFoundException
    {
        super(clerk,context);
        this.commodity=commodity;
        this.getClerk().getListOfCertainClerkRequests().add(this);
        Admin.creatAdminObject().getRequestsOfclerks().add(this);
    }
    void setCommodiy(PublicPropertiesOfGoods commodity)
    {
        this.commodity=commodity;
    }
    PublicPropertiesOfGoods getCommodity()
    {
        return this.commodity;
    }
}
