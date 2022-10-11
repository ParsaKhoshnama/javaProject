import exceptions.CheckDefaultExceptions;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
abstract public class PublicPropertiesOfGoods implements Serializable
{
    private static boolean discountExceptionsFlag=true;
    private String company;
    private String name;
    private double price;
    private double priceAfterDiscount;
    private String ID;
    private Clerk clerk;
    private int count;
    private Discount discount;
    private boolean discountRemain;
    private double averageMark=0;
    private ArrayList<Score>listOfScores=new ArrayList<Score>();
    private ArrayList<Comment>listOfComments=new ArrayList<Comment>();
    PublicPropertiesOfGoods(String name,String company,double price,String ID,String username,String passWord,Discount discount,int count)throws IOException,ClassNotFoundException
    {
        this.clerk=Clerk.findingClerk(username,passWord);
        this.count=count;
        this.discount=discount;
        this.priceAfterDiscount=price-(price*this.discount.getPercentOfDiscount()/100);
        this.ID=ID;
        this.company=company;
        this.price=price;
        this.name=name;

    }
    Discount getDiscount()
    {
        return this.discount;
    }
    void setDiscount(Discount discount)
    {
        this.discount=discount;
    }
    double getPriceAfterDiscount()
    {
        return this.priceAfterDiscount;
    }
    void setID(String ID)
    {
        this.ID=ID;
    }
    String getID()
    {
        return this.ID;
    }
    String getCompany()
    {
        return this.company;
    }
    void setCompany(String company)
    {
        this.company=company;
    }
    double getPrice()
    {
        return this.price;
    }
    void setCount(int count)
    {
        this.count=count;
    }
    int getCount()
    {
        return this.count;
    }
    Clerk getClerk()
    {
        return this.clerk;
    }
    void setClerk(Clerk clerk)
    {
        this.clerk=clerk;
    }
    void setPrice(double price)
    {
        this.price=price;
    }
    void setName(String name)
    {
        this.name=name;
    }
    void setAverageMark(double averageMark)
    {
        this.averageMark=averageMark;
    }
    double getAverageMark()
    {
        return this.averageMark;
    }
    String getName()
    {
        return this.name;
    }
    int compareToForGoods(PublicPropertiesOfGoods good)
    {

        if(this.getAverageMark()>good.getAverageMark())
            return -1;
        else if(this.getAverageMark()<good.getAverageMark())
            return 1;
        else
        {
            if(this.getPrice()>good.getPrice())
                return -1;
            else if(this.getPrice()<good.getPrice())
                return 1;
            else
            {
                if(this.getCount()>good.getCount())
                    return -1;
                else if(this.getCount()<good.getCount())
                    return 1;
                else
                    return 0;
            }
        }
    }
    void showCommodity()
    {
        if(this instanceof LapTop)
        {
            ((LapTop)this).showLapTopInformation();
            return;
        }
        if(this instanceof Mobile)
        {
            ((Mobile)this).showMobileInformation();
            return;
        }
        if(this instanceof Dress)
        {
            ((Dress)this).showDress();
            return;
        }
        if(this instanceof Shoes)
        {
            ((Shoes)this).showShoes();
            return;
        }
        if(this instanceof Television)
        {
            ((Television)this).showTelevision();
            return;
        }
        if(this instanceof Stove)
        {
            ((Stove)this).showStove();
            return;
        }
        if(this instanceof Refrigerator)
        {
            ((Refrigerator)this).showRefrigerator();
        }
        System.out.println("___________________________________________________");
    }
    ArrayList<Comment> getListOfComments()
    {
        return this.listOfComments;
    }
    ArrayList<Score> getListOfScores()
    {
        return this.listOfScores;
    }
   void showComments()
    {
        for(int i=0;i<this.getListOfComments().size();i++)
        {
            System.out.println("user name: "+this.getListOfComments().get(i).getPerson().getUserName());
            System.out.println("opinion: "+this.getListOfComments().get(i).getOpinion());
            System.out.println("___________________________________");
        }
    }
    void showScores()
    {
        for(int i=0;i<this.getListOfScores().size();i++)
        {
            System.out.println("user name: "+this.getListOfScores().get(i).getBuyer().getUserName());
            System.out.println("score: "+this.getListOfScores().get(i).getMark());
            System.out.println("___________________________________");
        }
    }
    boolean checkNumberForBasket(int count)
    {
        if(this.getCount()>=count)
        {
            this.setCount(this.getCount()-count);
            return true;
        }
        System.out.println("your number is mor than commodity count");
        return false;
    }
  static  boolean addCommodityFunction(String userName,String passWord,String categoryCommand,String commodityCommand)throws IOException,ClassNotFoundException
    {
        Scanner sc=new Scanner(System.in);
        CheckDefaultExceptions checkDefaultExceptions=new CheckDefaultExceptions();
            String IdCommand;
            String ID;
            System.out.printf("enter name: ");
            String name = sc.nextLine();
            System.out.printf("enter company: ");
            String company = sc.nextLine();
            double price =checkDefaultExceptions.checkDouble("enter price: ");
            if(price<0)
               return false;
            sc.nextLine();
            Discount discount = giveDiscountInfo();
            if(discount==null && discountExceptionsFlag==false)
            {
                discountExceptionsFlag=true;
                return false;
            }
            while (true) {
                System.out.printf("enter ID: ");
                ID = sc.nextLine();
                if (Clerk.findingClerk(userName, passWord).checkCommodityID(ID) == false) {
                    System.out.println("this ID has been used before . try again");
                } else
                    break;
            }
            if (categoryCommand.equals("digital")) {
              return   DigitalCommodity.addDigitlaCommodityFunction(name, company, price, discount, ID, userName, passWord, commodityCommand);

            } else if (categoryCommand.equals("garment")) {
               return Garment.addGarmentFunction(userName, passWord, commodityCommand, company, price, discount, ID, name);
            } else {
              return HomeAppliance.addHomeApplianceFunction(commodityCommand, name, company, ID, price, discount, userName, passWord);
            }
    }
    static void showCommentCheck()
    {
        String ID;
        Scanner sc=new Scanner(System.in);
        while (true)
        {
            System.out.printf("enter the ID: ");
            ID=sc.nextLine();
            if(Commodity.findCommodity(ID)!=null)
                break;
            else
            {
                System.out.println("wrong ID. try again oe enter leave");
            }
            if(ID.equals("leave"))
                return;
        }
        Commodity.findCommodity(ID).showComments();
    }
    static Discount giveDiscountInfo()
    {
        Scanner sc=new Scanner(System.in);
        Discount discount;
        String DiscountCommand;
        CheckDefaultExceptions checkDefaultExceptions=new CheckDefaultExceptions();
        while (true)
        {
            System.out.printf("dou you want to give discount: ");
            DiscountCommand=sc.nextLine();
            Date date=new Date();
            if(DiscountCommand.equals("yes"))
            {
                while (true)
                {
                    int year = checkDefaultExceptions.checkInt("enter year");
                    if(year<0){
                        discountExceptionsFlag=false;
                        return null;
                    }
                    int month =checkDefaultExceptions.checkInt("enter month");
                    if(month<0){
                        discountExceptionsFlag=false;
                        return null;
                    }
                    int day =checkDefaultExceptions.checkInt("enter day of month");
                    if(day<0) {
                        discountExceptionsFlag=false;
                        return null;
                    }
                    int hour =checkDefaultExceptions.checkInt("enter hour");
                        if(hour<0){
                            discountExceptionsFlag=false;
                            return null;
                        }
                    System.out.printf("enter minute: ");
                    int minute =checkDefaultExceptions.checkInt("enter minute");
                    if (minute >=0)
                    {
                        date.setYear(year);
                        date.setMonth(month);
                        date.setDate(day);
                        date.setHours(hour);
                        date.setMinutes(minute);
                        break;
                    }
                    else {
                        discountExceptionsFlag=false;
                        return null;
                    }
                }
                System.out.printf("enter code of discount: ");
                String code=sc.nextLine();
                while (true)
                {
                    System.out.printf("enter capacity: ");
                    int capacity=sc.nextInt();
                    System.out.printf("enter percent of discount: ");
                    double percntOfDiscount=sc.nextDouble();
                    if(percntOfDiscount>0)
                        return new Discount(date,capacity,percntOfDiscount,code);
                }
            }
            else if(DiscountCommand.equals("no"))
                return null;
            else
            {
                System.out.println("wrong command try again.press yes or no");
            }
        }
    }
    static void showScoresCheck()
    {
        Scanner sc=new Scanner(System.in);
        String ID;
        while (true)
        {
            System.out.printf("enter the ID: ");
            ID=sc.nextLine();
            if(Commodity.findCommodity(ID)!=null)
                break;
            else
            {
                System.out.println("wrong ID. try again oe enter laeave");
            }
            if(ID.equals("leave"))
                return;
        }
        Commodity.findCommodity(ID).showScores();
    }
    void changeInformation()throws IOException,ClassNotFoundException
    {
        if(this instanceof LapTop)
            ((LapTop)this).changeInformationOfLapTop(this.getClerk());
        else if(this instanceof Mobile)
            ((Mobile)this).changeInformationOfMobile(this.getClerk());
        else if(this instanceof Dress)
            ((Dress)this).changeDressInformation(this.getClerk());
        else if(this instanceof Shoes)
            ((Shoes)this).changeShoesInformation(this.getClerk());
        else if(this instanceof Stove)
            ((Stove)this).changeInformationOfStove(this.getClerk());
        else if(this instanceof Television)
            ((Television)this).changeTelevisionInformation(this.getClerk());
        else
            ((Refrigerator)this).changeInformationOfRefrigerator(this.getClerk());
    }
}
