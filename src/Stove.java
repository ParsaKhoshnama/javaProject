import exceptions.CheckDefaultExceptions;

import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Stove extends HomeAppliance
{
    private String genus;
    private int countOfFlames;
    private boolean gasOven;

    Stove(String name,String degreeName,boolean guarantee,String genus,int countOfFlames,boolean gasOven,int count,String company,double price,String ID,String userName,String passWord,Discount discount,String statusForAdmin)
    {
        super(name,degreeName,guarantee,company,price,ID,userName,passWord,discount,count);
        this.genus=genus;
        this.countOfFlames=countOfFlames;
        this.gasOven=gasOven;
        if (statusForAdmin.equals("accept") || statusForAdmin.equals("ignore")) {
            if (statusForAdmin.equals("accept")) {
                this.setCount(super.findCountOfCertainHomeAppilaince(count));
                this.clearListofHomeApplianceStove();
                HomeAppliance.getListOfHomeAppliancesAl().add(this);
                Collections.sort(HomeAppliance.getListOfHomeAppliancesAl());
            } else
                this.setCount(count);
        }
    }
    String getGenus()
    {
        return this.genus;
    }
    void setGenus(String genus)
    {
        this.genus=genus;
    }
    int getCountOfFlames()
    {
        return this.countOfFlames;
    }
    void setCountOfFlames(int countOfFlames)
    {
        this.countOfFlames=countOfFlames;
    }
    boolean isGasOven()
    {
        return this.gasOven;
    }
    void setGasOven(boolean gasOven)
    {
        this.gasOven=gasOven;
    }
    private void clearListofHomeApplianceStove()
    {
        for(int i=0;i<HomeAppliance.getListOfHomeAppliancesAl().size();i++)
        {
            if(HomeAppliance.getListOfHomeAppliancesAl().get(i).getName().equals(this.getName()))
            {
                if(HomeAppliance.getListOfHomeAppliancesAl().get(i).getClerk().getPassWord().equals(this.getClerk().getPassWord()))
                {
                    if(HomeAppliance.getListOfHomeAppliancesAl().get(i) instanceof Stove)
                    {
                        HomeAppliance.getListOfHomeAppliancesAl().remove(i);
                    }
                }
            }
        }
    }
    void createStoveAfterGettingAccept()
    {

        Stove stove=new Stove(this.getName(),this.getDegreeName(),this.isGuearantee(),
                this.getGenus(),this.getCountOfFlames(),this.isGasOven(),this.getCount(),
                this.getCompany(),this.getPrice(),this.getID(),this.getClerk().getUserName(),
                this.getClerk().getPassWord(),this.getDiscount(),"accept");
        HomeAppliance.getListOfHomeAppliancesAl().add(stove);
        this.getClerk().getCommodityListOfCertainClerk().add(stove);
    }
    void showStove()
    {
        System.out.println("Stove: ");
        System.out.println("name: "+this.getName());
        System.out.println("degree name: "+this.getDegreeName());
        System.out.println("ID: "+this.getID());
        if(isGuearantee()==true)
            System.out.println("has guarantee");
        if(this.isGasOven()==true)
            System.out.println("has gas oven");
        System.out.println("count of flames: "+this.getCountOfFlames());
        System.out.println("company: "+this.getCompany());
        System.out.println("count: "+this.getCount());
        System.out.println("price: "+this.getPrice()+" T");
        System.out.println("percent of dscount: "+this.getDiscount().getPercentOfDiscount());
        System.out.println("price after discount: "+this.getPriceAfterDiscount()+" T");
        System.out.println("average of scores: "+this.getAverageMark());
    }
    static boolean addStoveFunction(String name,String degreeOfconsumption,boolean gurant,String company,String ID,double price,Discount discount,String userName,String passWord)throws IOException,ClassNotFoundException
    {
        CheckDefaultExceptions checkDefaultExceptions=new CheckDefaultExceptions();
        Scanner sc=new Scanner(System.in);
        System.out.printf("enter the genus: ");
        String genus=sc.nextLine();
        int countOfFlames=checkDefaultExceptions.checkInt("count of flames");
        if(countOfFlames<0)
            return false;
        System.out.printf("enter count: ");
        int count=checkDefaultExceptions.checkInt("enter count");
        if(count<0)
            return false;
        String gasOven;
        while (true)
        {
            System.out.printf("gas oven: ");
            gasOven = sc.nextLine();
            if (gasOven.equals("true")) {
                Stove stove = new Stove(name, degreeOfconsumption, gurant, genus, countOfFlames, true, count, company, price, ID, userName,
                        passWord, discount, "ignore");
                AddCommodityRequest request = new AddCommodityRequest(Clerk.findingClerk(userName, passWord), "add commodity request", stove);
                break;
            } else if (gasOven.equals("false")) {
                Stove stove = new Stove(name, degreeOfconsumption, gurant, genus, countOfFlames, false, count, company, price, ID, userName,
                        passWord, discount, "ignore");
                AddCommodityRequest request = new AddCommodityRequest(Clerk.findingClerk(userName, passWord), "add commodity request", stove);
                break;
            } else
                System.out.println("wrong name for having gas oven.try again");
        }
        return true;
    }
    void changeInformationOfStove(Clerk clerk)throws IOException,ClassNotFoundException
    {
        Stove stove=new Stove(this.getName(),this.getDegreeName(),this.isGuearantee(),
                this.getGenus(),this.getCountOfFlames(),this.isGasOven(),this.getCount(),
                this.getCompany(),this.getPrice(),this.getID(),this.getClerk().getUserName(),
                this.getClerk().getPassWord(),this.getDiscount(),"for change Information request");
        stove.cahngeStoveField();
        ChangeInformationOfCommodityRequest request=new ChangeInformationOfCommodityRequest(clerk,"Change information of commodity request",this,stove);
    }
    private void cahngeStoveField()
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
            else if(field.equals("count of flames"))
            {
                int CountOfFlames=checkDefaultExceptions.checkInt("count of flames");
                if(CountOfFlames<0)
                    continue;
                this.setCountOfFlames(CountOfFlames);
            }
            else if(field.equals("genus"))
            {
                String genus=sc.nextLine();
                this.setGenus(genus);
            }
            else if(field.equals("gas oven"))
            {
                String gasOven;
                while (true)
                {
                    System.out.printf("gas oven: ");
                    gasOven = sc.nextLine();
                    if(gasOven.equals("leave"))
                        break;
                    if (gasOven.equals("true"))
                    {
                        this.setGasOven(true);
                        break;
                    }
                    else if (gasOven.equals("false"))
                    {
                        this.setGasOven(false);
                        break;
                    } else
                        System.out.println("wrong name for having gas oven.try again");

                }
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
                    if (gurantee.equals("true")) {
                        this.setGuearantee(true);
                        break;
                    } else if (gurantee.equals("false")) {
                        this.setGuearantee(false);
                        break;
                    } else
                        System.out.println("wrong gurantee.try again");
                    if(gurantee.equals("leave"))
                        break;

                }
            }
            else if(field.equals("leave"))
                break;
            else
                System.out.println("Wrong command");
        }
    }

}
