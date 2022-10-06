
import workWithFiles.MyObjectOutPutStream;

import javax.transaction.xa.XAException;
import java.io.*;
import java.text.Format;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;

public class Clerk extends Person implements Serializable
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
    static void addToClerkListAl(Clerk clerk)throws IOException,ClassNotFoundException
    {
        clerkListAl.add(clerk);
        File listOfClerks=new File("saved data\\users\\clerks\\list of clerks.txt");
        MyObjectOutPutStream.setFile(listOfClerks);
        MyObjectOutPutStream myObjectOutPutStream=new MyObjectOutPutStream(listOfClerks);
        myObjectOutPutStream.writeObject(clerk);
        myObjectOutPutStream.close();
    }
    void addToListOfCertainClerkRequests(Request request)throws IOException,ClassNotFoundException
    {
        this.listOfCertainClerkRequests.add(request);
        StringBuilder clerkFolderName=new StringBuilder("clerk ");
        clerkFolderName.append(request.getClerk().getUserName());
        File clerkFolder=new File("save data\\users\\clerks",clerkFolderName.toString());
        clerkFolder.mkdir();
        request.getClerk().createFiles(clerkFolder,request);
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
            System.out.println("percent of discount: "+this.sellFactors.get(i).getCommodity().getGood().getDiscount().getPercentOfDiscount());
            System.out.println("number: "+this.sellFactors.get(i).getCommodity().getCount());
            System.out.println("payed: "+this.sellFactors.get(i).getPriceAfterDiscount());
            System.out.println("buyer information: ");
            System.out.println("userName:" +this.sellFactors.get(i).getBuyer().getUserName());
            System.out.println("name: "+this.sellFactors.get(i).getBuyer().getName()+"    last name: "+this.sellFactors.get(i).getBuyer().getLastName());
            System.out.println("__________________________________________________");
        }
    }
    static void clerkRegisteration(String firstName,String lastName,String phoneNumber,String eMail,String userName,String passWord )throws IOException,ClassNotFoundException
    {
        if(Person.checkPersonsInformation(firstName,lastName,phoneNumber,eMail,passWord,userName))
        {
            Clerk clerk=new Clerk(userName,passWord,firstName,lastName,phoneNumber,eMail);
            BeClerkRequest BCrequest=new BeClerkRequest(clerk,"request for being clerk");
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
   private void createFiles(File file,Request request)throws IOException,ClassNotFoundException
    {
        File listOfCommodities=new File(file,"goods.txt");
        listOfCommodities.createNewFile();
        File listOfRequests=new File(file,"requests.txt");
        listOfRequests.createNewFile();
        File listOfSellFactors=new File(file,"sell factors.txt");
        listOfSellFactors.createNewFile();
        MyObjectOutPutStream.setFile(listOfRequests);
        MyObjectOutPutStream myObjectOutPutStream=new MyObjectOutPutStream(listOfRequests);
        myObjectOutPutStream.writeObject(request);
        myObjectOutPutStream.close();
        this.writePropertiesOfClerks(file);
    }
    void writePropertiesOfClerks(File file)throws IOException,ClassNotFoundException
    {
        File properties=new File(file,"properties.txt");
        properties.createNewFile();
        FileOutputStream fileOutputStream=new FileOutputStream(properties);
        Formatter formatter=new Formatter(fileOutputStream);
        formatter.format("first name: %s\n",this.getName());
        formatter.format("last name: %s\n",this.getLastName());
        formatter.format("username: %s\n",this.getUserName());
        formatter.format("phone number: %s\n",this.getPhoneNumber());
        formatter.format("e mail: %s",this.geteMail());
        formatter.close();
        fileOutputStream.close();
    }
    void addRequestInFileForClerk(Request request)throws IOException,ClassNotFoundException
    {
        StringBuilder pathOfFolder=new StringBuilder("saved data\\users\\clerks\\");
        pathOfFolder.append("clerk "+this.getUserName());
        File clerkFolder=new File(pathOfFolder.toString());
        File listOfRequestsForClerk=new File(clerkFolder,"requests.txt");
        MyObjectOutPutStream.setFile(listOfRequestsForClerk);
        MyObjectOutPutStream myObjectOutPutStream=new MyObjectOutPutStream(listOfRequestsForClerk);
        myObjectOutPutStream.writeObject(request);
        myObjectOutPutStream.close();
    }
}
