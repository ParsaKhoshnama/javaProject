import java.io.IOException;

public class RemoveCommodityRequest extends Request
{
    private PublicPropertiesOfGoods commodity;
    RemoveCommodityRequest(Clerk clerk,PublicPropertiesOfGoods commodity,String context)throws IOException
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
