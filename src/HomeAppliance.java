import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

abstract public class HomeAppliance extends PublicPropertiesOfGoods implements Comparable
{
    private boolean guearantee;
    private DegreeOfenergyConsumption degree;
    private String degreeName;
    private static ArrayList<HomeAppliance> listOfHomeAppliancesAl=new ArrayList<HomeAppliance>();
    HomeAppliance(String name,String degreeName,boolean guearantee,String company,double price,String ID,String userName,String password,Discount discount,int count)
    {
        super(name,company,price,ID,userName,password,discount,count);
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
    public int compareTo(Object object)
    {
        if(this instanceof Television && (object instanceof Television)==false)
            return -1;
        else if((this instanceof Television)==false && object instanceof Television)
            return 1;
        else
        {
            if(this instanceof Television && object instanceof Television)
            {
                Television television=(Television)object;
                return this.televisionCompareTo(television);
            }
            else if(this instanceof Refrigerator && (object instanceof Refrigerator)==false)
                return -1;
            else if((this instanceof Refrigerator)==false && object instanceof Refrigerator)
                return 1;
            else
            {
                if(this instanceof Refrigerator && object instanceof Refrigerator)
                {
                    Refrigerator refrigerator=(Refrigerator)object;
                  return this.refrigeratorCompareTo(refrigerator);
                }
                else
                {
                    Stove stove=(Stove)object;
                     return this.stoveCompareTo(stove);
                }
            }
        }
    }
    private int televisionCompareTo(Television television)
    {
        if(this.getName().compareTo(television.getName())>0)
            return -1;
        else if(this.getName().compareTo(television.getName())<0)
            return 1;
        else
        {
            if(((Television)this).isGuearantee()==true && television.isGuearantee()==false)
                return -1;
            else if(((Television)this).isGuearantee()==false && television.isGuearantee()==true)
                return 1;
            else
                return this.compareToForGoods(television);
        }
    }
    private int refrigeratorCompareTo(Refrigerator refrigerator)
    {
        if(this.getName().compareTo(refrigerator.getName())>0)
            return -1;
        else if(this.getName().compareTo(refrigerator.getName())<0)
            return 1;
        else
        {
            if (this.isGuearantee() == true && refrigerator.isGuearantee() == false)
                return -1;
            if (this.isGuearantee() == false && refrigerator.isGuearantee() == true)
                return 1;
            else {
                if (((Refrigerator) this).isFreezer() == true && refrigerator.isFreezer() == false)
                    return -1;
                else if (((Refrigerator) this).isFreezer() == false && refrigerator.isFreezer() == true)
                    return 1;
                else
                {
                    if (((Refrigerator) this).getContent() > refrigerator.getContent())
                        return -1;
                    else if (((Refrigerator) this).getContent() < refrigerator.getContent())
                        return 1;
                    else
                        return this.compareToForGoods(refrigerator);
                }
            }
        }
    }
    private int stoveCompareTo(Stove stove)
    {
        if(this.getName().compareTo(stove.getName())>0)
            return -1;
        else if(this.getName().compareTo(stove.getName())<0)
            return 1;
        else
        {
            if (this.isGuearantee() == true && stove.isGuearantee() == false)
                return -1;
            if (this.isGuearantee() == false && stove.isGuearantee() == true)
                return 1;
            else
            {
                if (((Stove) this).getCountOfFlames() > stove.getCountOfFlames())
                    return 1;
                else if (((Stove) this).getCountOfFlames() < stove.getCountOfFlames())
                    return -1;
                else
                    return this.compareToForGoods(stove);
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
        Collections.sort(HomeAppliance.getListOfHomeAppliancesAl());
    }
    static boolean addHomeApplianceFunction(String commodityCommand,String name,String company,String ID,double price,Discount discount,String userName,String passWord)throws IOException
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
           return Stove.addStoveFunction(name,degreeOfconsumption,gurant,company,ID,price,discount,userName,passWord);
        }
        else if(commodityCommand.equals("television"))
        {
           return Television.addTelevisionFunction(name,degreeOfconsumption,gurant,company,price,discount,ID,userName,passWord);
        }
        else
        {
          return   Refrigerator.addRefrigeratorFunction(name,degreeOfconsumption,gurant,company,price,discount,ID,userName,passWord);
        }
    }
}
