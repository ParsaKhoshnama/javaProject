import exceptions.CheckDefaultExceptions;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Scanner;

public class Television extends HomeAppliance implements Serializable
{
    private  int sizeOfScreen;
   private String gardeOfScreen;
    Television(String name,String degreeName,boolean gaurantee,int sizeOfScreen,String gardeOfScreen,int count,String company,double price,String ID,String userName,String passWord,Discount discount,String statusForAdmin)
    {
        super(name,degreeName,gaurantee,company,price,ID,userName,passWord,discount,count);
        this.sizeOfScreen=sizeOfScreen;
        this.gardeOfScreen=gardeOfScreen;
        if (statusForAdmin.equals("accept") || statusForAdmin.equals("ignore")) {
            if (statusForAdmin.equals("accept")) {
                this.setCount(super.findCountOfCertainHomeAppilaince(count));
                this.clearHomeApplianceTelevision();
                HomeAppliance.getListOfHomeAppliancesAl().add(this);
                Collections.sort(HomeAppliance.getListOfHomeAppliancesAl());
            } else
                this.setCount(count);
        }
    }

    void setSizeOfScreen(int sizeOfScreen)
    {
        this.sizeOfScreen=sizeOfScreen;
    }
    int getSizeOfScreen()
    {
        return this.sizeOfScreen;
    }
    void setGardeOfScreen(String gardeOfScreen)
    {
        this.gardeOfScreen=gardeOfScreen;
    }
    String getGardeOfScreen()
    {
        return this.gardeOfScreen;
    }
    private void clearHomeApplianceTelevision()
    {
        for (int i=0;i<HomeAppliance.getListOfHomeAppliancesAl().size();i++)
        {
            if(HomeAppliance.getListOfHomeAppliancesAl().get(i).getName().equals(this.getName()))
            {
                if(HomeAppliance.getListOfHomeAppliancesAl().get(i).getClerk().getPassWord().equals(this.getClerk().getPassWord()))
                {
                    if(HomeAppliance.getListOfHomeAppliancesAl().get(i) instanceof Television)
                    {
                        HomeAppliance.getListOfHomeAppliancesAl().remove(i);
                    }
                }
            }
        }
    }
    void creatTelevisionAterGettingAccept()
    {

        Television television=new Television(this.getName(),this.getDegreeName(),
                this.isGuearantee(),this.getSizeOfScreen(),this.getGardeOfScreen(),
                this.getCount(),this.getCompany(),this.getPrice(),this.getID(),
                this.getClerk().getUserName(),this.getClerk().getPassWord(),this.getDiscount(),"accept");
        HomeAppliance.getListOfHomeAppliancesAl().add(television);
        this.getClerk().getCommodityListOfCertainClerk().add(television);
    }
    void showTelevision()
    {
        System.out.println("Television: ");
        System.out.println("name: "+this.getName());
        System.out.println("degree name: "+this.getDegreeName());
        System.out.println("ID: "+this.getID());
        if(isGuearantee()==true)
            System.out.println("has guarantee");
        System.out.println("size of screen: "+this.getSizeOfScreen());
        System.out.println("grade of screen: "+this.getGardeOfScreen());
        System.out.println("company: "+this.getCompany());
        System.out.println("count: "+this.getCount());
        System.out.println("price: "+this.getPrice()+" T");
        System.out.println("percent of dscount: "+this.getDiscount().getPercentOfDiscount());
        System.out.println("price after discount: "+this.getPriceAfterDiscount()+" T");
        System.out.println("average of scores: "+this.getAverageMark());
    }
    static boolean addTelevisionFunction(String name,String degreeOfconsumption,boolean gurant,String company,double price,Discount discount,String ID,String userName,String passWord)throws IOException,ClassNotFoundException
    {
        CheckDefaultExceptions checkDefaultExceptions=new CheckDefaultExceptions();
        Scanner sc=new Scanner(System.in);
        int screenSize=checkDefaultExceptions.checkInt("enter size of screen");
        if(screenSize<0)
            return false;
        int count=checkDefaultExceptions.checkInt("enter count");
        if(count<0)
            return false;
        sc.nextLine();
        System.out.printf("enter grade of screen: ");
        String gradOfScreen=sc.nextLine();
        Television television=new Television(name,degreeOfconsumption,gurant,screenSize,gradOfScreen,count,company,price,
                ID,userName,passWord,discount,"ignore");
        AddCommodityRequest request=new AddCommodityRequest(Clerk.findingClerk(userName,passWord),"add commodity request",television);
        return true;
    }
    void changeTelevisionInformation(Clerk clerk)throws IOException,ClassNotFoundException
    {
        Television television=new Television(this.getName(),this.getDegreeName(),
                this.isGuearantee(),this.getSizeOfScreen(),this.getGardeOfScreen(),
                this.getCount(),this.getCompany(),this.getPrice(),this.getID(),
                this.getClerk().getUserName(),this.getClerk().getPassWord(),this.getDiscount(),"for change Information request");
        television.changeTelevisionField();
        ChangeInformationOfCommodityRequest request=new ChangeInformationOfCommodityRequest(clerk,"Change information of commodity request",this,television);
    }
    private void changeTelevisionField()
    {
        CheckDefaultExceptions checkDefaultExceptions=new CheckDefaultExceptions();
        String field;
        Scanner sc=new Scanner(System.in);
        while (true)
        {
            field=sc.nextLine();
            if(field.equals("name"))
            {
                String name=sc.nextLine();
                this.setName(name);
            }
            else if(field.equals("company"))
            {
                String company=sc.nextLine();
                this.setCompany(company);
            }
            else if(field.equals("price"))
            {
                double price=checkDefaultExceptions.checkDouble("price");
                if(price<0)
                    continue;
                this.setPrice(price);
            }
            else if(field.equals("discount"))
            {
                this.setDiscount(PublicPropertiesOfGoods.giveDiscountInfo());
            }
            else if(field.equals("count"))
            {
                int count=checkDefaultExceptions.checkInt("count");
                if(count<0)
                    continue;
                this.setCount(count);
            }
            else if(field.equals("degree of concumption"))
            {
                String degreeOfconsumption;
                while (true)
                {
                    System.out.printf("enter degree of energee cinsumption: ");
                    degreeOfconsumption=sc.nextLine();
                    if(degreeOfconsumption.equals("leave"))
                        break;
                    if(HomeAppliance.checkHomeApplianceDegreeOfenergyConsumption(degreeOfconsumption)==false)
                        System.out.println("wrong name for energy consumption.try again");
                    else
                        break;
                }
            }
            else if(field.equals("gurantee"))
            {
                String gurantee;
                while (true)
                {
                    System.out.printf("guarantee: ");

                    gurantee = sc.nextLine();
                    if(gurantee.equals("leave"))
                        break;
                    if (gurantee.equals("true")) {
                        this.setGuearantee(true);
                        break;
                    } else if (gurantee.equals("false")) {
                        this.setGuearantee(false);
                        break;
                    } else
                        System.out.println("wrong gurantee.try again");

                }
            }
            else if(field.equals("size of screen"))
            {
                int sizeOfScreen=sc.nextInt();
                sc.nextLine();
                this.setSizeOfScreen(sizeOfScreen);
            }
            else if(field.equals("grade of screen"))
            {
                String gradeOfScreen=sc.nextLine();
                this.setGardeOfScreen(gradeOfScreen);
            }
            else if(field.equals("leave"))
                break;
            else
                System.out.println("Wrong command");
        }
    }
}
