import java.util.ArrayList;

public class Clerk extends Person
{


    private static ArrayList<Clerk> clerkListAl=new ArrayList<Clerk>();
    private ArrayList<PublicPropertiesOfGoods> commodityListOfCertainClerk=new ArrayList<PublicPropertiesOfGoods>();
    private ArrayList<Request> listOfCertainClerkRequests=new ArrayList<Request>();
    private ArrayList<SellFactor>sellFactors=new ArrayList<SellFactor>();
    Clerk(String userName,String passWord,String name,String lastName,String phoneNumber,String eMail)
    {
        super(userName,passWord, name,lastName,phoneNumber,eMail);
    }
    static boolean searchClerkList(String userName,String passWord)
    {
        for(int i=0;i<clerkListAl.size();i++)
        {
            if(clerkListAl.get(i).getUserName().equals(userName) && clerkListAl.get(i).getPassWord().equals(passWord))
                return true;
        }
        return false;
    }
    static void addToClerkListAl(Clerk clerk)
    {
        clerkListAl.add(clerk);
    }
    void addToListOfCertainClerkRequests(Request request)
    {
        this.listOfCertainClerkRequests.add(request);
    }
   static Clerk findingClerk(String userName,String passWord)
    {
        for(int i=0;i<clerkListAl.size();i++)
        {
            if(clerkListAl.get(i).getPassWord().equals(passWord) && clerkListAl.get(i).getUserName().equals(userName))
            {
                return clerkListAl.get(i);
            }
        }
        return null;
    }
    ArrayList<SellFactor> getSellFactors()
    {
        return this.sellFactors;
    }
    ArrayList<Request> getListOfCertainClerkRequests()
    {
        return this.listOfCertainClerkRequests;
    }
    static ArrayList<Clerk> getClerkListAl()
    {
        return clerkListAl;
    }
    static void showClerkInformation(String password)
    {

        for(int i=0;i<clerkListAl.size();i++)
        {
            if(clerkListAl.get(i).getPassWord().equals(password))
            {
                System.out.println("name: "+clerkListAl.get(i).getName());
                System.out.println("last name: "+clerkListAl.get(i).getLastName());
                System.out.println("user name: "+clerkListAl.get(i).getUserName());
                System.out.println("phone number: "+clerkListAl.get(i).getPhoneNumber());
                System.out.println("email: "+ clerkListAl.get(i).geteMail());
            }
        }
    }
    ArrayList<PublicPropertiesOfGoods> getCommodityListOfCertainClerk()
    {
        return this.commodityListOfCertainClerk;
    }
    void showCertainClerkCommodities()
    {
        for(int i=0;i<this.commodityListOfCertainClerk.size();i++)
        {
            this.commodityListOfCertainClerk.get(i).showCommodity();
        }
    }

    void showRequestOfCertainClerk()
    {
        for(int i=0;i<this.listOfCertainClerkRequests.size();i++)
        {
           if(this.listOfCertainClerkRequests.get(i) instanceof BeClerkRequest)
           {
               System.out.println("request "+(i+1));
               System.out.println("be clerk request:  "+this.listOfCertainClerkRequests.get(i).getStatusOfRequestforAdmin());
               System.out.println("-------------------------------------");
               continue;
           }
           if(this.listOfCertainClerkRequests.get(i) instanceof AddCommodityRequest)
           {
               System.out.println("request "+(i+1));
               System.out.println("add commodity request:  "+this.listOfCertainClerkRequests.get(i).getStatusOfRequestforAdmin());
               System.out.println("information of commodity:");
               ((AddCommodityRequest)this.listOfCertainClerkRequests.get(i)).getCommodity();
               System.out.println("-------------------------------------");
               continue;
           }
           if(this.listOfCertainClerkRequests.get(i) instanceof ChangeInformationOfCommodityRequest)
           {
               System.out.println("request "+(i+1));
               System.out.println("change informatioon of commodity request:  "+this.listOfCertainClerkRequests.get(i).getStatusOfRequestforAdmin());

               if(this.listOfCertainClerkRequests.get(i).getStatusOfRequestforAdmin().equals("accept"))
               {
                   System.out.println("prvious information of commodity:");
                   ((ChangeInformationOfCommodityRequest)this.listOfCertainClerkRequests.get(i)).getCurrentCommodity();
                   System.out.println("_______________________________________________________________________________");
                   System.out.println("current information of commodity");
                   ((ChangeInformationOfCommodityRequest)this.listOfCertainClerkRequests.get(i)).getReformedCommodity();
                   continue;
               }
               if(this.listOfCertainClerkRequests.get(i).getStatusOfRequestforAdmin().equals("reject") || this.listOfCertainClerkRequests.get(i).getStatusOfRequestforAdmin().equals("ignore"))
               {
                   System.out.println("current information of commodity:");
                   ((ChangeInformationOfCommodityRequest)this.listOfCertainClerkRequests.get(i)).getCurrentCommodity();
                   System.out.println("_______________________________________________________________________________");
                   System.out.println("reformed information of commodity");
                   ((ChangeInformationOfCommodityRequest)this.listOfCertainClerkRequests.get(i)).getReformedCommodity();
                   continue;
               }

           }
        }
    }
    void removeCommodity(PublicPropertiesOfGoods commodity)
    {
        for (int i=0;i<this.getCommodityListOfCertainClerk().size();i++)
        {
            if(this.getCommodityListOfCertainClerk().get(i).getID().equals(commodity.getID()))
            {
                this.getCommodityListOfCertainClerk().remove(i);
                return;
            }
        }
    }
    void changeCommodityInformationOfCertainClerk(PublicPropertiesOfGoods currentCommodity,PublicPropertiesOfGoods reformedCommodity)
    {
        for (int i=0;i<this.commodityListOfCertainClerk.size();i++)
        {
            if(this.commodityListOfCertainClerk.get(i).getID().equals(currentCommodity.getID()))
            {
                this.commodityListOfCertainClerk.remove(i);
                this.commodityListOfCertainClerk.add(reformedCommodity);
                return;
            }
        }
    }
    void showSellFactors()
    {
        for(int i=0;i<this.sellFactors.size();i++)
        {
            System.out.println("Factor "+(i+1)+":");
            System.out.println("date: "+this.sellFactors.get(i).getDateString());
            System.out.println("commodity information:");
            System.out.printf("Commodity name: ");
            System.out.println(this.sellFactors.get(i).getCommodity().getGood().getName());
            System.out.println("Commodity ID: "+this.sellFactors.get(i).getCommodity().getGood().getID());
            System.out.println("price without discount: "+this.sellFactors.get(i).getCommodity().getGood().getPrice());
            System.out.println("percent of discount: "+this.sellFactors.get(i).getCommodity().getGood().getPercentOfDiscount());
            System.out.println("number: "+this.sellFactors.get(i).getCommodity().getCount());
            System.out.println("payed: "+this.sellFactors.get(i).getPriceAfterDiscount());
            System.out.println("buyer information: ");
            System.out.println("userName:" +this.sellFactors.get(i).getBuyer().getUserName());
            System.out.println("name: "+this.sellFactors.get(i).getBuyer().getName()+"    last name: "+this.sellFactors.get(i).getBuyer().getLastName());
            System.out.println("__________________________________________________");
        }
    }
    static void clerkRegisteration(String firstName,String lastName,String phoneNumber,String eMail,String userName,String passWord )
    {
        if(Person.checkPersonsInformation(firstName,lastName,phoneNumber,eMail,passWord,userName))
        {
            Clerk clerk=new Clerk(userName,passWord,firstName,lastName,phoneNumber,eMail);
            BeClerkRequest BCrequest=new BeClerkRequest(clerk,"requset for being clerk");
            BCrequest.addRequest();
        }
    }
    boolean checkCommodityID(String ID)
    {
        for(int i=0;i<HomeAppliance.getListOfHomeAppliancesAl().size();i++)
        {
            if(HomeAppliance.getListOfHomeAppliancesAl().get(i).getClerk().getPassWord().equals(this.getPassWord()) && HomeAppliance.getListOfHomeAppliancesAl().get(i).getID().equals(ID))
                return false;
        }
        for(int i=0;i<Garment.getListOfAllGarmentsAl().size();i++)
        {
            if(Garment.getListOfAllGarmentsAl().get(i).getClerk().getPassWord().equals(this.getPassWord()) && Garment.getListOfAllGarmentsAl().get(i).getID().equals(ID))
                return false;
        }
        for(int i=0;i<DigitalCommodity.getDigiritlaCommodityAL().size();i++)
        {
            if(DigitalCommodity.getDigiritlaCommodityAL().get(i).getClerk().getPassWord().equals(this.getPassWord()) && DigitalCommodity.getDigiritlaCommodityAL().get(i).getID().equals(ID))
                return false;
        }
        for(int i=0;i<this.commodityListOfCertainClerk.size();i++)
        {
            if(this.commodityListOfCertainClerk.get(i).getID().equals(ID))
                return true;
        }
        return true;
    }
}
