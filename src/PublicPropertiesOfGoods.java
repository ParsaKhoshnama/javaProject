import java.util.ArrayList;
import java.util.Scanner;
abstract public class PublicPropertiesOfGoods
{
    private String company;
    private String name;
    private double price;
    private double percentOfDiscount;
    private double priceAfterDiscount;
    private String ID;
    private Clerk clerk;
    private int count;
    private double averageMark=0;
    private ArrayList<Score>listOfScores=new ArrayList<Score>();
    private ArrayList<Comment>listOfComments=new ArrayList<Comment>();
    PublicPropertiesOfGoods(String name,String company,double price,String ID,String username,String passWord,double percentOfDiscount,int count)
    {
        this.clerk=Clerk.findingClerk(username,passWord);
        this.count=count;
        this.percentOfDiscount=percentOfDiscount;
        this.priceAfterDiscount=price-(price*percentOfDiscount/100);
        this.ID=ID;
        this.company=company;
        this.price=price;
        this.name=name;

    }
    double getPercentOfDiscount()
    {
        return this.percentOfDiscount;
    }
    void setPercentOfDiscount(double percentOfDiscount)
    {
        this.percentOfDiscount=percentOfDiscount;
        this.priceAfterDiscount=getPrice()-(this.getPrice()*percentOfDiscount/100);
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
  static  void addCommodityFunction(String userName,String passWord,String categoryCommand,String commodityCommand)
    {
        Scanner sc=new Scanner(System.in);
        String IdCommand;
        String ID;
        System.out.printf("enter name: ");
        String name=sc.nextLine();
        System.out.printf("enter company: ");
        String company=sc.nextLine();
        System.out.printf("enter price: ");
        double price=sc.nextDouble();
        sc.nextLine();
        System.out.printf("enter percent of discount: ");
        double percentOfDiscount=sc.nextDouble();
        sc.nextLine();
        while (true)
        {
            System.out.printf("enter ID: ");
            ID=sc.nextLine();
            if(Clerk.findingClerk(userName,passWord).checkCommodityID(ID)==false)
            {
                System.out.println("this ID has been used before . try again");
            }
            else
                break;
        }
        if(categoryCommand.equals("digital"))
        {
            DigitalCommodity.addDigitlaCommodityFunction(name,company,price,percentOfDiscount,ID,userName,passWord,commodityCommand);
        }
        else if(categoryCommand.equals("garment"))
        {
           Garment.addGarmentFunction(userName,passWord,commodityCommand,company,price,percentOfDiscount,ID,name);
        }
        else
        {
           HomeAppliance.addHomeApplianceFunction(commodityCommand,name,company,ID,price,percentOfDiscount,userName,passWord);
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
                System.out.println("wrong ID. try again oe enter laeave");
            }
            if(ID.equals("leave"))
                return;
        }
        Commodity.findCommodity(ID).showComments();
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
    void changeInformation()
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
