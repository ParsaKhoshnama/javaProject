import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import workWithFiles.MakeFiles;
import workWithFiles.MyObjectOutPutStream;

public class Admin implements Serializable {

    private String passWord;
    private String userName;
    private ArrayList<Person> personsListAL=new ArrayList<Person>();
    private ArrayList<Request> requestsOfclerks=new ArrayList<Request>();
    private ArrayList<Request> igonredRequestsOfclerks=new ArrayList<Request>();
    private Admin(String userName,String passWord)throws IOException
    {
        this.userName=userName;
        this.passWord=passWord;
    }
    public static Admin creatAdminObject()throws IOException,ClassNotFoundException
    {
        File savedDataDirectory=new File("saved data");
        if(!savedDataDirectory.isDirectory())
        {
            MakeFiles makeFiles=new MakeFiles();
            makeFiles.createFirstDirectoriesAndFiles();
            Admin admin=new Admin("admin","admin");
            File adminObject=new File("saved data//users//admin","admin object");
            adminObject.createNewFile();
            MyObjectOutPutStream.setFile(adminObject);
            MyObjectOutPutStream myObjectOutPutStream=new MyObjectOutPutStream(adminObject);
            myObjectOutPutStream.writeObject(admin);
            myObjectOutPutStream.close();
            return admin;
        }
        Admin admin=null;
        File file=new File("saved data//users//admin//admin object");
        try(ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(file)))
        {
             admin=(Admin)objectInputStream.readObject();
             return admin;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return admin;
        }
    }
    ArrayList<Person> getPersonsListAL()
    {
        return this.personsListAL;
    }

    void addPersonToPersonsListAL(Person person)
    {
        this.personsListAL.add(person);
    }
    ArrayList<Request> getRequestsOfclerks()
    {
        return this.requestsOfclerks;
    }
    ArrayList<Request> getIgonredRequestsOfclerks()
    {
        return this.igonredRequestsOfclerks;
    }
    void showRequests(int number)
    {
        System.out.println("Request "+(number+1)+":");
        if(this.requestsOfclerks.get(number) instanceof BeClerkRequest)
        {
            System.out.println(((BeClerkRequest)this.requestsOfclerks.get(number)).getContext());
            System.out.printf("username: ");
            System.out.println(((BeClerkRequest)this.requestsOfclerks.get(number)).getClerk().getUserName());
            System.out.printf("password: ");
            System.out.println(((BeClerkRequest)this.requestsOfclerks.get(number)).getClerk().getPassWord());
            System.out.println("--------------------------------------------------------------");
            return;
        }
        if(this.requestsOfclerks.get(number) instanceof AddCommodityRequest)
        {
            System.out.println(((AddCommodityRequest)this.requestsOfclerks.get(number)).getContext());
            System.out.printf("username: ");
            System.out.println(((AddCommodityRequest)this.requestsOfclerks.get(number)).getClerk().getUserName());
            System.out.printf("password: ");
            System.out.println(((AddCommodityRequest)this.requestsOfclerks.get(number)).getClerk().getPassWord());
            System.out.println("information of commodity");
            ((AddCommodityRequest)this.requestsOfclerks.get(number)).getCommodity().showCommodity();
        }
        if(this.requestsOfclerks.get(number) instanceof ChangeInformationOfCommodityRequest)
        {
            System.out.println(((ChangeInformationOfCommodityRequest)this.requestsOfclerks.get(number)).getContext());
            System.out.printf("username: ");
            System.out.println(((ChangeInformationOfCommodityRequest)this.requestsOfclerks.get(number)).getClerk().getUserName());
            System.out.printf("password: ");
            System.out.println(((ChangeInformationOfCommodityRequest)this.requestsOfclerks.get(number)).getClerk().getPassWord());
            System.out.println("current information of commodity: ");
            ((ChangeInformationOfCommodityRequest)this.requestsOfclerks.get(number)).getCurrentCommodity().showCommodity();
            System.out.println("________________");
            System.out.println("reformed information of commodity: ");
            ((ChangeInformationOfCommodityRequest)this.requestsOfclerks.get(number)).getReformedCommodity().showCommodity();
        }
    }
    void removeFromTempListRequest(Request request)
    {
        if(request instanceof BeClerkRequest)
        {
            for (int i = 0; i < this.igonredRequestsOfclerks.size(); i++)
            {
                if (this.igonredRequestsOfclerks.get(i).getClerk().getPassWord().equals(request.getClerk().getPassWord()))
                {
                    if (this.igonredRequestsOfclerks.get(i).getContext().equals(request.getContext()))
                    {
                        this.igonredRequestsOfclerks.remove(i);
                    }
                }
            }
        }
        else
        {
            for(int i=0;i<this.igonredRequestsOfclerks.size();i++)
            {
                if(this.igonredRequestsOfclerks.get(i).getClerk().getPassWord().equals(request.getClerk().getPassWord()))
                {
                    if(this.igonredRequestsOfclerks.get(i).getContext().equals(request.getContext()))
                    {
                        this.igonredRequestsOfclerks.remove(i);
                        return;
                    }
                }
            }
        }
    }
    void setRequestsOfclerks()
    {
        this.requestsOfclerks.clear();
        /*
       for(int i=0;i<this.requestsOfclerks.size();i++)
       {
           this.requestsOfclerks.remove(i);
       }
       */
       for(int i=0;i<this.igonredRequestsOfclerks.size();i++)
       {
           this.requestsOfclerks.add(this.igonredRequestsOfclerks.get(i));
       }
    }
    void showAllPerson()
    {
        for(Person person:this.getPersonsListAL())
        {
            if(person instanceof Buyer)
            {
                System.out.printf("Buyer: username: %s\n",person.getUserName());
                System.out.println("name: "+person.getName()+"  last name: "+person.getLastName());
            }
            if(person instanceof Clerk)
            {
                System.out.printf("Clerk: username: %s\n",person.getUserName());
                System.out.println("name: "+person.getName()+"  last name: "+person.getLastName());
            }
        }
    }
    void removePerson(String userName)
    {
        for(int i=0;i<this.personsListAL.size();i++)
        {
            if(this.personsListAL.get(i).getUserName().equals(userName))
            {
                if(this.personsListAL.get(i) instanceof Clerk)
                {
                    for(int j=0;i<Clerk.getClerkListAl().size();j++)
                    {
                       if(Clerk.getClerkListAl().get(j).getUserName().equals(userName))
                       {
                          Clerk.getClerkListAl().remove(j);
                          break;
                       }
                    }
                    break;
                }
                if(this.personsListAL.get(i) instanceof Buyer)
                {
                    for(int j=0;j<Buyer.getBuyersListAl().size();j++)
                    {
                        if(Buyer.getBuyersListAl().get(i).getUserName().equals(userName))
                        {
                            Buyer.getBuyersListAl().remove(j);
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }
    void adminCheckRequests()throws IOException,ClassNotFoundException
    {
        Scanner sc=new Scanner(System.in);
        String requestCommand;
        for (int i = 0; i < Admin.creatAdminObject().getRequestsOfclerks().size(); i++)
        {
            while (true)
            {
                Admin.creatAdminObject().showRequests(i);
                System.out.printf("if you want to accept request enter(accept)or enter(reject) or (next): ");
                requestCommand = sc.nextLine();
                if (requestCommand.equals("accept"))
                {
                    Admin.creatAdminObject().getRequestsOfclerks().get(i).setStatusOfRequestforAdmin(requestCommand);
                    Admin.creatAdminObject().removeFromTempListRequest(Admin.creatAdminObject().getRequestsOfclerks().get(i));
                    break;
                }
                if (requestCommand.equals("reject"))
                {
                    Admin.creatAdminObject().getRequestsOfclerks().get(i).setStatusOfRequestforAdmin(requestCommand);
                    Admin.creatAdminObject().removeFromTempListRequest(Admin.creatAdminObject().getRequestsOfclerks().get(i));
                    break;
                }
                if(requestCommand.equals("next"))
                    break;
                if (requestCommand.compareTo("accept") != 0 && requestCommand.compareTo("reject") != 0 &&requestCommand.compareTo("next")!=0)
                {
                    System.out.println("wrong command");
                }
            }

        }
        Admin.creatAdminObject().setRequestsOfclerks();
    }
    void showUsres()
    {
        System.out.println("Clerks: ______________________");
        for(Clerk clerk:Clerk.getClerkListAl())
        {
            System.out.println("name: "+clerk.getName());
            System.out.println("last name: "+clerk.getLastName());
            System.out.println("phone number"+clerk.getPhoneNumber());
            System.out.println("eMail: "+clerk.geteMail());
            System.out.println("user name: "+clerk.getUserName());
        }
        System.out.println("_____________________________________________________________________________________");
        System.out.println("Buyers: ");
        for (Buyer buyer:Buyer.getBuyersListAl())
        {
            System.out.println("name: "+buyer.getName());
            System.out.println("last name: "+buyer.getLastName());
            System.out.println("phone number"+buyer.getPhoneNumber());
            System.out.println("eMail: "+buyer.geteMail());
            System.out.println("user name: "+buyer.getUserName());
        }
        System.out.println("_____________________________________________________________________________________");
    }
    void removeUser()
    {
        Scanner sc=new Scanner(System.in);
        String userName;
        while (true)
        {
            System.out.printf("enter user name: ");
            userName=sc.nextLine();
            for (int i=0;i<this.personsListAL.size();i++)
            {
                if (this.personsListAL.get(i).getUserName().equals(userName)) {
                    this.personsListAL.remove(i);
                    if (this.personsListAL.get(i) instanceof Clerk) {
                        for (int j = 0; j < Clerk.getClerkListAl().size(); i++) {
                            if (Clerk.getClerkListAl().get(i).getUserName().equals(userName)) {
                                Clerk.getClerkListAl().remove(i);
                                return;
                            }
                        }
                    }
                    if (this.personsListAL.get(i) instanceof Buyer) {
                        for (int j = 0; j < Buyer.getBuyersListAl().size(); i++) {
                            if (Buyer.getBuyersListAl().get(i).getUserName().equals(userName)) {
                                Buyer.getBuyersListAl().remove(i);
                                return;
                            }
                        }
                    }
                }
            }
            if(userName.equals("leave"))
                return;
            System.out.println("wrong command.try again or type leave");
        }
    }
}


