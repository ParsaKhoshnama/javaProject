import java.util.ArrayList;
import java.util.Scanner;

abstract public class HomeAppliance extends PublicPropertiesOfGoods
{
    private boolean guearantee;
    private DegreeOfenergyConsumption degree;
    private String degreeName;
    private static ArrayList<HomeAppliance> listOfHomeAppliancesAl=new ArrayList<HomeAppliance>();
    HomeAppliance(String name,String degreeName,boolean guearantee,String company,double price,String ID,String userName,String password,double percentOfDiscount,int count)
    {
        super(name,company,price,ID,userName,password,percentOfDiscount,count);
        this.guearantee=guearantee;
        this.degreeName=degreeName;
        DegreeOfenergyConsumption[]degrees=DegreeOfenergyConsumption.values();
        for(int i=0;i<degrees.length;i++)
        {
            if(degrees[i].name().equals(degreeName))
            {
                this.degree=degrees[i];
                break;
            }
        }
    }
    boolean isGuearantee()
    {
        return this.guearantee;
    }

    String getDegreeName()
    {
        return this.degreeName;
    }
    void setGuearantee(boolean guearantee)
    {
        this.guearantee=guearantee;
    }
    void setDegree(String degreeName)
    {
        DegreeOfenergyConsumption[]degrees=DegreeOfenergyConsumption.values();
        for(int i=0;i<degrees.length;i++)
        {
            if(degrees[i].name().equals(degreeName))
            {
                this.degree=degrees[i];
                return;
            }
        }
        System.out.println("Wrong name for degree");
    }
    static boolean checkHomeApplianceDegreeOfenergyConsumption(String degreeName)
    {
        DegreeOfenergyConsumption[]degrees=DegreeOfenergyConsumption.values();
        for(int i=0;i<degrees.length;i++)
        {
            if(degrees[i].name().equals(degreeName))
            {
                return true;
            }
        }
        return false;
    }
   static ArrayList<HomeAppliance> getListOfHomeAppliancesAl()
   {
       return listOfHomeAppliancesAl;
   }
   int findCountOfCertainHomeAppilaince(int count)
   {
     int counter=0;
     boolean flag=false;
     for(int i=0;i<HomeAppliance.getListOfHomeAppliancesAl().size();i++)
     {
         if(HomeAppliance.getListOfHomeAppliancesAl().get(i).getName().equals(this.getName()))
         {
             if(HomeAppliance.getListOfHomeAppliancesAl().get(i).getClerk().getPassWord().equals(this.getClerk().getPassWord()))
             {
                 if(HomeAppliance.getListOfHomeAppliancesAl().get(i) instanceof Television)
                 {
                     counter=((Television)(HomeAppliance.getListOfHomeAppliancesAl().get(i))).getCount()+count;
                     flag=true;
                     break;
                 }
                 if(HomeAppliance.getListOfHomeAppliancesAl().get(i) instanceof  Refrigerator)
                 {
                     counter=((Refrigerator)(HomeAppliance.getListOfHomeAppliancesAl().get(i))).getCount()+count;
                     flag=true;
                     break;
                 }
                 if(HomeAppliance.getListOfHomeAppliancesAl().get(i) instanceof Stove)
                 {
                     counter=((Stove)(HomeAppliance.getListOfHomeAppliancesAl().get(i))).getCount()+count;
                     flag=true;
                     break;
                 }
             }
         }
     }
     if(flag==true)
         return counter;
     else
         return count;
   }
    void changeInformationRequestHomeApplianceCommodity(Request request)
    {
        for (int i = 0; i < HomeAppliance.getListOfHomeAppliancesAl().size(); i++) {
            if (HomeAppliance.getListOfHomeAppliancesAl().get(i).getClerk().getPassWord().equals(this.getClerk().getPassWord())) {
                if (HomeAppliance.getListOfHomeAppliancesAl().get(i).getID().equals(this.getID())) {
                    if (HomeAppliance.getListOfHomeAppliancesAl().get(i) instanceof Television) {
                        HomeAppliance.getListOfHomeAppliancesAl().remove(i);
                        HomeAppliance.getListOfHomeAppliancesAl().add((Television)((ChangeInformationOfCommodityRequest) request).getReformedCommodity());
                        break;
                    }
                    if (HomeAppliance.getListOfHomeAppliancesAl().get(i) instanceof Refrigerator) {
                        HomeAppliance.getListOfHomeAppliancesAl().remove(i);
                        HomeAppliance.getListOfHomeAppliancesAl().add((Refrigerator)((ChangeInformationOfCommodityRequest) request).getReformedCommodity());
                        break;
                    }
                    if(HomeAppliance.getListOfHomeAppliancesAl().get(i) instanceof Stove)
                    {
                        HomeAppliance.getListOfHomeAppliancesAl().remove(i);
                        HomeAppliance.getListOfHomeAppliancesAl().add((Stove)((ChangeInformationOfCommodityRequest) request).getReformedCommodity());
                        break;
                    }
                }
            }
        }
    }
    static void addHomeApplianceFunction(String commodityCommand,String name,String company,String ID,double price,double percentOfDiscount,String userName,String passWord)
    {
        Scanner sc=new Scanner(System.in);
        String gurantee;
        boolean gurant;
        while (true)
        {
            System.out.printf("guarantee: ");

            gurantee=sc.nextLine();
            if(gurantee.equals("true"))
            {
                gurant = true;
                break;
            }
            else if(gurantee.equals("false"))
            {
                gurant = false;
                break;
            }
            else
                System.out.println("wrong gurantee.try again");
        }
        String degreeOfconsumption;
        while (true)
        {
            System.out.printf("enter degree of energee cinsumption: ");
            degreeOfconsumption=sc.nextLine();
            if(HomeAppliance.checkHomeApplianceDegreeOfenergyConsumption(degreeOfconsumption)==false)
                System.out.println("wrong name for energy consumption.try again");
            else
                break;
        }
        if(commodityCommand.equals("stove"))
        {
            Stove.addStoveFunction(name,degreeOfconsumption,gurant,company,ID,price,percentOfDiscount,userName,passWord);
        }
        else if(commodityCommand.equals("television"))
        {
            Television.addTelevisionFunction(name,degreeOfconsumption,gurant,company,price,percentOfDiscount,ID,userName,passWord);
        }
        else
        {
            Refrigerator.addRefrigeratorFunction(name,degreeOfconsumption,gurant,company,price,percentOfDiscount,ID,userName,passWord);
        }
    }
}
