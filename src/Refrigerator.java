import exceptions.CheckDefaultExceptions;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Scanner;

public class Refrigerator extends HomeAppliance implements Serializable
{
    private int content;
    private   KindOfFridge kind;
   private String nameOfKindOfFridge;
    private boolean freezer;
    Refrigerator(String name,String degreeName,boolean guarantee,int content,String nameOfKindOfFridge,boolean freezer,int count,String company,double price,String ID,String userName,String passWord,Discount discount,String statusForAdmin)
    {
        super(name,degreeName,guarantee,company,price,ID,userName,passWord,discount,count);
        this.content=content;
        this.freezer=freezer;
        this.nameOfKindOfFridge=nameOfKindOfFridge;
        KindOfFridge[]kinds=KindOfFridge.values();
        for(int i=0;i<kinds.length;i++)
        {
            if(kinds[i].name().equals(nameOfKindOfFridge))
            {
                this.kind=kinds[i];
                break;
            }
        }
        if (statusForAdmin.equals("accept") || statusForAdmin.equals("ignore")) {
            if (statusForAdmin.equals("accept")) {
                this.setCount(super.findCountOfCertainHomeAppilaince(count));
                this.clearHomeApplianceListRefrigerator();
                HomeAppliance.getListOfHomeAppliancesAl().add(this);
                Collections.sort(HomeAppliance.getListOfHomeAppliancesAl());
            } else
                this.setCount(count);
        }
    }
    void setContent(int content)
    {
        this.content=content;
    }
    String getNameOfKindOfFridge()
    {
        return this.nameOfKindOfFridge;
    }
    boolean isFreezer()
    {
        return this.freezer;
    }
    int getContent()
    {
        return this.content;
    }
    void setKind(String nameOfKindOfFridge)
    {
        KindOfFridge[]kinds=KindOfFridge.values();
        for(int i=0;i<kinds.length;i++)
        {
            if(kinds[i].name().equals(nameOfKindOfFridge))
            {
                return;
            }
        }
        System.out.println("wrong name for kind");
    }
    static boolean checkKindOfFridge(String nameOfKindOfFridge)
    {
        KindOfFridge[]kinds=KindOfFridge.values();
        for(int i=0;i<kinds.length;i++)
        {
            if(kinds[i].name().equals(nameOfKindOfFridge))
            {
                return true;
            }
        }
        return false;
    }
    private void clearHomeApplianceListRefrigerator()
    {
        for(int i=0;i<HomeAppliance.getListOfHomeAppliancesAl().size();i++)
        {
            if(HomeAppliance.getListOfHomeAppliancesAl().get(i).getName().equals(this.getName()))
            {
                if(HomeAppliance.getListOfHomeAppliancesAl().get(i).getClerk().getPassWord().equals(this.getClerk().getPassWord()))
                {
                    if(HomeAppliance.getListOfHomeAppliancesAl().get(i) instanceof Refrigerator)
                    {
                        HomeAppliance.getListOfHomeAppliancesAl().remove(i);
                    }
                }
            }
        }
    }
    void createRefirgeratorAfterGettingAccept()
    {
        Refrigerator refrigerator=new Refrigerator(this.getName(),this.getDegreeName(),
                this.isGuearantee(),this.getContent(),this.getNameOfKindOfFridge(),
                this.isFreezer(),this.getCount(),this.getCompany(),
                this.getPrice(),this.getID(),this.getClerk().getPassWord(),
                this.getClerk().getPassWord(),this.getDiscount(),"accept");
        HomeAppliance.getListOfHomeAppliancesAl().add(refrigerator);
        this.getClerk().getCommodityListOfCertainClerk().add(refrigerator);
    }
    void showRefrigerator()
    {
        System.out.println("Refrigeratore: ");
        System.out.println("name: "+this.getName());
        System.out.println("degree name: "+this.getDegreeName());
        System.out.println("ID: "+this.getID());
        if(isGuearantee()==true)
            System.out.println("has guarantee");
        if(this.isFreezer()==true)
            System.out.println("has freezer");
        System.out.println("content: "+this.getContent());
        System.out.println("company: "+this.getCompany());
        System.out.println("count: "+this.getCount());
        System.out.println("price: "+this.getPrice()+" T");
        System.out.println("percent of dscount: "+this.getDiscount().getPercentOfDiscount());
        System.out.println("price after discount: "+this.getPriceAfterDiscount()+" T");
        System.out.println("average of scores: "+this.getAverageMark());
    }
    static boolean addRefrigeratorFunction(String name,String degreeOfconsumption,boolean gurant,String company,double price,Discount discount,String ID,String userName,String passWord)throws IOException,ClassNotFoundException
    {
        CheckDefaultExceptions checkDefaultExceptions=new CheckDefaultExceptions();
        Scanner sc=new Scanner(System.in);
        int content=checkDefaultExceptions.checkInt("enter content");
        if(content<0)
            return false;
        sc.nextLine();
        String kindOfFridge;
        while (true)
        {
            System.out.printf("enter kind: ");
            kindOfFridge=sc.nextLine();
            if(Refrigerator.checkKindOfFridge(kindOfFridge)==false)
                System.out.println("wrong name for kind of refrigerator");
            else
                break;
        }
        String hasFreezer;
        boolean freezer;
        while (true)
        {
            System.out.printf(" has freezer: ");
            hasFreezer=sc.nextLine();
            if(hasFreezer.equals("true"))
            {
                freezer=true;
                break;
            }
            else if(hasFreezer.equals("false"))
            {
                freezer=false;
                break;
            }
            else
                System.out.println("wrong name for having freezer.try again");


        }
        int count=checkDefaultExceptions.checkInt("enter count");
        if(count<0)
            return false;
        sc.nextLine();
        Refrigerator refrigerator=new Refrigerator(name,degreeOfconsumption,gurant,content,kindOfFridge,freezer,count,
                company,price,ID,userName,passWord,discount,"ignore");
        AddCommodityRequest request=new AddCommodityRequest(Clerk.findingClerk(userName,passWord),"add commodity request",refrigerator);
        return true;
    }
   void changeInformationOfRefrigerator(Clerk clerk)throws IOException,ClassNotFoundException
    {
        Refrigerator refrigerator=new Refrigerator(this.getName(),this.getDegreeName(),
                this.isGuearantee(),this.getContent(),this.getNameOfKindOfFridge(),
                this.isFreezer(),this.getCount(),this.getCompany(),
                this.getPrice(),this.getID(),this.getClerk().getPassWord(),
                this.getClerk().getPassWord(),this.getDiscount(),"for change Information request");
        refrigerator.changeFieldOFRefrigerator();
        ChangeInformationOfCommodityRequest request=new ChangeInformationOfCommodityRequest(clerk,"Change information of commodity request",this,refrigerator);

    }
    private void changeFieldOFRefrigerator()
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
            else if(field.equals("freezer"))
            {
                String hasFreezer;
                while (true)
                {
                    System.out.printf(" has freezer: ");
                    hasFreezer=sc.nextLine();
                    if(hasFreezer.equals("leave"))
                        break;
                    if(hasFreezer.equals("true"))
                    {
                        this.freezer=true;
                    }
                    else if(hasFreezer.equals("false"))
                    {
                        this.freezer=false;
                        break;
                    }
                    else
                        System.out.println("wrong name for having freezer.try again");

                }
            }
            else if(field.equals("kind of fridge"))
            {
                String kindOfFridge;
                while (true)
                {
                    System.out.printf("enter kind: ");
                    kindOfFridge=sc.nextLine();
                    if(kindOfFridge.equals("leave"))
                        break;
                    if(Refrigerator.checkKindOfFridge(kindOfFridge)==false)
                        System.out.println("wrong name for kind of refrigerator");
                    else
                        break;
                }
            }
            else if(field.equals("content"))
            {
                int content=checkDefaultExceptions.checkInt("content");
                if(content<0)
                    continue;
                this.setContent(content);
            }
            else if(field.equals("leave"))
                break;
            else
                System.out.println("Wrong command");
        }
    }

}
