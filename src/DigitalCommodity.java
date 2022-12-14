import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
abstract public class DigitalCommodity extends PublicPropertiesOfGoods implements Comparable
{
    private static ArrayList<DigitalCommodity> DigiritlaCommodityAL=new ArrayList<DigitalCommodity>();
    private int ram;
    private int valencyOfMemory;
    private String operatingSystem;
    private int weight;

    DigitalCommodity(String name,int ram,int valencyOfMemory,String operatingSystem,int weight,String company,double price,String ID,String userName,String passWord,Discount discount,int count)
    {
        super(name,company,price,ID,userName,passWord,discount,count);
        this.ram=ram;
        this.valencyOfMemory=valencyOfMemory;
        this.operatingSystem=operatingSystem;
        this.weight=weight;

    }
   public int compareTo(Object object)
    {
        if(this instanceof LapTop &&(object instanceof LapTop)==false)
            return -1;
        else if((this instanceof LapTop)==false && object instanceof LapTop)
            return 1;
        else
        {
            if(this instanceof LapTop && object instanceof LapTop)
            {
                LapTop lapTop=(LapTop)object;
               return this.lapTopCompareTo(lapTop);
            }
            else
            {
                Mobile mobile=(Mobile)object;
                return this.mobileCompareTo(mobile);
            }
        }
    }
   private int lapTopCompareTo(LapTop lapTop)
    {
        if(this.getName().compareTo(lapTop.getName())>0)
            return -1;
        else if(this.getName().compareTo(lapTop.getName())<0)
            return 1;
        else
        {
            if(this.getRam()>lapTop.getRam())
                return -1;
            else if(this.getRam()<lapTop.getRam())
                return 1;
            else
                return this.compareToForGoods(lapTop);
        }
    }
   private int mobileCompareTo(Mobile mobile)
    {
        if(this.getName().compareTo(mobile.getName())>0)
            return -1;
        else if(this.getName().compareTo(mobile.getName())<0)
            return 1;
        else
        {
            if(this.getRam()>mobile.getRam())
                return -1;
            else if(this.getRam()<mobile.getRam())
                return 1;
            else
                return this.compareToForGoods(mobile);
        }
    }
    int getRam()
    {
        return this.ram;
    }
    void setRam(int ram)
    {
        this.ram=ram;
    }
    int getValencyOfMemory()
    {
        return this.valencyOfMemory;
    }
    void  setValencyOfMemory(int valencyOfMemory)
    {
        this.valencyOfMemory=valencyOfMemory;
    }
    String getOperatingSystem()
    {
        return this.operatingSystem;
    }
    void setOperatingSystem(String operatingSystem)
    {
        this.operatingSystem=operatingSystem;
    }
    int getWeight()
    {
        return this.weight;
    }
    void setWeight(int weight)
    {
        this.weight=weight;
    }
    static  ArrayList<DigitalCommodity> getDigiritlaCommodityAL()
    {
        return DigiritlaCommodityAL;
    }
    int findCountOfCertainDigitalCommodity(int count)
    {
        int counter=0;
        boolean flag=false;
        for(int i=0;i<DigitalCommodity.getDigiritlaCommodityAL().size();i++)
        {
            if(DigitalCommodity.getDigiritlaCommodityAL().get(i).getName().equals(this.getName()))
            {
                if(DigitalCommodity.getDigiritlaCommodityAL().get(i).getClerk().getPassWord().equals(this.getClerk().getPassWord()))
                {
                    if(DigitalCommodity.getDigiritlaCommodityAL().get(i) instanceof LapTop)
                    {
                        counter=((LapTop)(DigitalCommodity.getDigiritlaCommodityAL().get(i))).getCount()+count;
                        flag=true;
                        break;
                    }
                    if(DigitalCommodity.getDigiritlaCommodityAL().get(i) instanceof Mobile)
                    {
                        counter=((Mobile)(DigitalCommodity.getDigiritlaCommodityAL().get(i))).getCount()+count;
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
    void changeInformationRequestDigitlCommodity(Request request)
    {
        for (int i = 0; i < DigitalCommodity.getDigiritlaCommodityAL().size(); i++) {
            if (DigitalCommodity.getDigiritlaCommodityAL().get(i).getClerk().getPassWord().equals(this.getClerk().getPassWord())) {
                if (DigitalCommodity.getDigiritlaCommodityAL().get(i).getID().equals(this.getID())) {
                    if (DigitalCommodity.getDigiritlaCommodityAL().get(i) instanceof Mobile) {
                        DigitalCommodity.getDigiritlaCommodityAL().remove(i);
                        DigitalCommodity.getDigiritlaCommodityAL().add((Mobile) ((ChangeInformationOfCommodityRequest) request).getReformedCommodity());
                        break;
                    }
                    if (DigitalCommodity.getDigiritlaCommodityAL().get(i) instanceof LapTop) {
                        DigitalCommodity.getDigiritlaCommodityAL().remove(i);
                        DigitalCommodity.getDigiritlaCommodityAL().add((LapTop) ((ChangeInformationOfCommodityRequest) request).getReformedCommodity());
                        break;
                    }
                }
            }
        }
        Collections.sort(DigitalCommodity.getDigiritlaCommodityAL());
    }
   static void addDigitlaCommodityFunction(String name,String company,double price,Discount discount,String ID,String userName,String passWord,String commodityCommand)
    {
        Scanner sc=new Scanner(System.in);
        System.out.printf("enter ram: ");
        int ram=sc.nextInt();
        System.out.printf("enter valency of memory: ");
        int valencyOfMemory=sc.nextInt();
        System.out.printf("enter weight: ");
        int weight=sc.nextInt();
        sc.nextLine();
        System.out.printf("enter operating system: ");
        String operatingSystem=sc.nextLine();
        if(commodityCommand.equals("laptop"))
        {
            LapTop.addLapTopFunction(name,company,price,discount,ID,userName,passWord,ram,valencyOfMemory,weight,operatingSystem);
        }
        else
        {
            Mobile.addMobileFunction(name,ram,valencyOfMemory,weight,operatingSystem,company,price,discount,ID,userName,passWord);
        }
    }
}
