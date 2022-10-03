import java.io.Serializable;

public class BeClerkRequest extends Request implements Serializable
{
   // private boolean statusOfRequestforAdmin=false;//true=reject true=accept false=ignore
  //  private boolean statusOfAddingRequestToREquestListOfAdmin;
    BeClerkRequest(Clerk clerk,String context)
    {

        super(clerk,context);
      //  this.getClerk().getListOfCertainClerkRequests().add(this);
    }
}
