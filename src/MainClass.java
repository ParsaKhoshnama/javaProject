
import exceptions.CheckDefaultExceptions;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.concurrent.Callable;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
    static Scanner sc=new Scanner(System.in);
    static String userName,passWord;
    public static void main(String[] args)throws IOException,ClassNotFoundException
    {
        Admin.creatAdminObject();
        String loginOrRegisterationcommand;
        while(true)
        {
            System.out.printf("if you have registrated before enter (log in) else enter(registeration): ");
            loginOrRegisterationcommand=sc.nextLine();
           if(loginOrRegisterationcommand.equals("log in"))
           {
               getLOginInformation();
                if(Person.checkPasswordAndUsername(passWord,userName))
                {
                    if(Buyer.searchBuyrListAl(userName, passWord))
                    {
                        String buyerCommand;
                        while (true)
                        {
                            System.out.println("you are Buyer");
                            System.out.printf("Enter the command: (commodities) or(user area) or (log out): ");
                            buyerCommand=sc.nextLine();
                            if(buyerCommand.equals("log out"))
                            {
                                break;
                            }
                            else if(buyerCommand.equals("user area"))
                            {
                                String userAreaCommand;
                                while (true)
                                {
                                    System.out.printf("enter the command: (show factors) or (show all goods) or (increase fund) or (show comments) or (send comment) or (change information)or (buy) or (leave user area): ");
                                    Buyer.showBuyerInformation(passWord);
                                    userAreaCommand=sc.nextLine();
                                    if(userAreaCommand.equals("leave user area"))
                                        break;
                                    else if(userAreaCommand.equals("change information"))
                                        changeInformationFunction(userName,passWord);
                                    else if(userAreaCommand.equals("increase fund"))
                                        Buyer.findBuyer(userName,passWord).setFund();
                                    else if(userAreaCommand.equals("show all goods"))
                                        Buyer.findBuyer(userName,passWord).showAllGoods();
                                    else if(userAreaCommand.equals("show factors"))
                                        Buyer.findBuyer(userName,passWord).showBuyFactors();
                                    else if(userAreaCommand.equals("give mark"))
                                        giveMark();
                                    else if(userAreaCommand.equals("send comment"))
                                        sendComment();
                                    else if(userAreaCommand.equals("show comments"))
                                        showCommnets();
                                    else if(userAreaCommand.equals("buy"))
                                       Buyer.findBuyer(userName,passWord).buy();
                                    else
                                        System.out.println("wrong command");
                                }
                            }
                            else if(buyerCommand.equals("commodities"))
                            {
                                Buyer.findBuyer(userName,passWord).showAllGoods();
                            }
                            else if(buyerCommand.equals("log out"))
                            {
                                break;
                            }
                            else
                                System.out.println("wrong command");
                        }
                    }
                    else
                    {
                        String clerkCommand;
                        while (true)
                        {
                            System.out.println("you are Clerk");
                            System.out.printf("Enter the command: (user area) or (log out): ");
                            Clerk.showClerkInformation(passWord);
                            clerkCommand = sc.nextLine();
                            if (clerkCommand.equals("user area"))
                            {
                                    String userAreaCommand;
                                    while (true)
                                    {
                                        System.out.printf("enter (change information) or (show scores) or (send comment)or(show comments)or(add good:send request )(change informations of a good: send request)or (show factors) (leave user area): ");
                                        userAreaCommand=sc.nextLine();
                                        if(userAreaCommand.equals("leave user area"))
                                        {
                                            break;
                                        }
                                        else if(userAreaCommand.equals("change information of a good"))
                                        {
                                            String ID;
                                            while (true)
                                            {
                                                System.out.printf("ID: ");
                                                ID=sc.nextLine();
                                                if(ID.equals("leave"))
                                                    break;
                                                if (ChangeInformationOfCommodityRequest.checkIDforChangeInformationOfcommodityRequest(ID, passWord) == false)
                                                    System.out.println("wrong ID");
                                                else
                                                {
                                                    Commodity.findCommodity(ID).changeInformation();
                                                    break;
                                                }
                                            }

                                        }
                                        else if(userAreaCommand.equals("show factors"))
                                        {
                                            Clerk.findingClerk(userName,passWord).showSellFactors();
                                        }
                                        else if(userAreaCommand.equals("show scores"))
                                        {
                                            showScores();
                                        }
                                        else if(userAreaCommand.equals("send comment"))
                                        {
                                            sendComment();
                                        }
                                        else if(userAreaCommand.equals("show comments"))
                                        {
                                            showCommnets();
                                        }
                                        else if(userAreaCommand.equals("add good"))
                                        {
                                            String categoryCommand;
                                            String commodityCommand;
                                            boolean bool=true;
                                            while (true)
                                            {
                                                System.out.printf("enter category(digital or home appliance or garment or leave)");
                                                categoryCommand=sc.nextLine();
                                                if(categoryCommand.equals("digital"))
                                                {
                                                    while (true)
                                                    {
                                                        System.out.printf("enter laptop or mobile or leave: ");
                                                        commodityCommand=sc.nextLine();
                                                        if(commodityCommand.equals("laptop"))
                                                        {
                                                           if(addCommodityFunction(userName,passWord,categoryCommand,commodityCommand)==false)
                                                               bool=false;
                                                            break;
                                                        }
                                                        else if(commodityCommand.equals("mobile"))
                                                        {
                                                            if(addCommodityFunction(userName,passWord,categoryCommand,commodityCommand)==false)
                                                                bool=false;
                                                            break;
                                                        }
                                                        else if (commodityCommand.equals("leave"))
                                                        {
                                                            break;
                                                        }
                                                        else
                                                        {
                                                            System.out.println("wrong command");
                                                        }
                                                    }
                                                    if(bool==false)
                                                        continue;
                                                }
                                                else if(categoryCommand.equals("garment"))
                                                {
                                                    while (true)
                                                    {
                                                        System.out.printf("enter shoes or dress or leave: ");
                                                        commodityCommand=sc.nextLine();
                                                        if(commodityCommand.equals("shoes"))
                                                        {
                                                            if(addCommodityFunction(userName,passWord,categoryCommand,commodityCommand)==false)
                                                                bool=false;
                                                            break;
                                                        }
                                                        else if(commodityCommand.equals("dress"))
                                                        {
                                                            if(addCommodityFunction(userName,passWord,categoryCommand,commodityCommand)==false)
                                                                bool=false;
                                                            break;
                                                        }
                                                        else if (commodityCommand.equals("leave"))
                                                        {
                                                            break;
                                                        }
                                                        else
                                                        {
                                                            System.out.println("wrong command");
                                                        }
                                                    }
                                                    if(bool==false)
                                                        continue;
                                                }
                                                else if(categoryCommand.equals("home appliance"))
                                                {
                                                    while (true)
                                                    {
                                                        System.out.printf("enter stove or refrigerator or television or leave: ");
                                                        commodityCommand=sc.nextLine();
                                                        if(commodityCommand.equals("stove"))
                                                        {
                                                            if(addCommodityFunction(userName,passWord,categoryCommand,commodityCommand)==false)
                                                                bool=false;
                                                            break;
                                                        }
                                                        else if(commodityCommand.equals("television"))
                                                        {
                                                            if(addCommodityFunction(userName,passWord,categoryCommand,commodityCommand)==false)
                                                                bool=false;
                                                            break;
                                                        }
                                                        else if(commodityCommand.equals("refrigerator"))
                                                        {
                                                            if(addCommodityFunction(userName,passWord,categoryCommand,commodityCommand)==false)
                                                                bool=false;
                                                            break;
                                                        }
                                                        else if (commodityCommand.equals("leave"))
                                                        {
                                                            break;
                                                        }
                                                        else
                                                        {
                                                            System.out.println("wrong command");
                                                        }
                                                    }
                                                    if(bool==false)
                                                        continue;
                                                }
                                                else if(categoryCommand.equals("leave"))
                                                {
                                                    break;
                                                }
                                                else {
                                                    System.out.println("wrong command");
                                                }
                                            }
                                        }
                                        else if(userAreaCommand.equals("show commodities"))
                                        {
                                            Clerk.findingClerk(userName,passWord).showCertainClerkCommodities();
                                        }
                                        else if(userAreaCommand.equals("remove commodity"))
                                        {
                                            String ID;
                                            while (true)
                                            {
                                                System.out.printf("enter the ID: ");
                                                ID = sc.nextLine();
                                                if(ID.equals("leave"))
                                                    break;
                                                if(Commodity.findCommodity(ID)==null)
                                                {
                                                    System.out.println("wrong ID.try again or enter leave.");
                                                }
                                                else
                                                {
                                                    RemoveCommodityRequest request=new RemoveCommodityRequest(Clerk.findingClerk(userName,passWord),Commodity.findCommodity(ID),"remove request");
                                                    break;
                                                }
                                            }
                                        }
                                        else if(userAreaCommand.equals("change information"))
                                        {
                                            changeInformationFunction(userName, passWord);
                                        }
                                        else
                                            System.out.println("wrong command");
                                    }
                            }
                            else if(clerkCommand.equals("log out"))
                            {
                                break;
                            }
                            else
                            {
                                System.out.println("wrong command.try again");
                            }
                        }

                    }
                }
                else if(passWord.equals("admin") && userName.equals("admin"))
                {
                    String adminCommand;
                    while (true)
                    {
                        System.out.println("if you want to see requests enter(requests)or(show user) or(remove user)");
                        System.out.println("if you want to log out enter (log out)");
                        adminCommand = sc.nextLine();
                        if (adminCommand.equals("requests"))
                        {
                            Request.writeRequestsInArrayListForAdmin();
                            if(Admin.creatAdminObject().getRequestsOfclerks().size()==0)
                            {
                                System.out.println("there isn't any request");
                                continue;
                            }
                            adminCheckRequests();
                        }
                        else if(adminCommand.equals("showUsers"))
                        {
                            Admin.creatAdminObject().showUsres();
                        }
                        else if(adminCommand.equals("remove user"))
                        {
                            Admin.creatAdminObject().removeUser();
                        }
                        else if(adminCommand.equals("log out"))
                            break;
                        else
                            System.out.println("Wrong command");
                    }
                }
                else
                    System.out.println("wrong password or username");

           }
           else if(loginOrRegisterationcommand.equals("registeration"))
           {
              registerationFunction();
           }
           else
               System.out.println("wrong command.try again");
        }
    }
















    static void getLOginInformation()
    {
        System.out.printf("Enter userName: ");
        userName=sc.nextLine();
        System.out.println();
        System.out.printf("Enter passWord: ");
        passWord=sc.nextLine();
        System.out.println();
    }
    static void getRegisterationInformations()throws IOException,ClassNotFoundException
    {
       Person.getRegisterationInformations();
    }
    static void registerationFunction()throws IOException,ClassNotFoundException
    {
       Person.getRegisterationInformations();
    }
    static void adminCheckRequests()throws IOException,ClassNotFoundException
    {
       Admin.creatAdminObject().adminCheckRequests();
    }
   static void changeInformationFunction(String userName,String passWord)throws IOException
    {
       Person.findPerson(userName,passWord).changeInformation();
    }
    static boolean addCommodityFunction(String userName,String passWord,String categoryCommand,String commodityCommand)throws IOException,ClassNotFoundException
    {
      return   PublicPropertiesOfGoods.addCommodityFunction(userName,passWord,categoryCommand,commodityCommand);
    }
    static void giveMark()
    {
        Buyer.findBuyer(userName,passWord).giveMark();
    }
    static void sendComment()
    {
        Person.findPerson(userName,passWord).sendComment();
    }
    static void showCommnets()
    {
       PublicPropertiesOfGoods.showCommentCheck();
    }
    static void showScores()
    {
        PublicPropertiesOfGoods.showScoresCheck();
    }
}
