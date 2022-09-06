import java.util.ArrayList;
import java.util.Scanner;
abstract public class DigitalCommodity extends PublicPropertiesOfGoods
{
    private static ArrayList<DigitalCommodity> DigiritlaCommodityAL=new ArrayList<DigitalCommodity>();
    private int ram;
    private int valencyOfMemory;
    private String operatingSystem;
    private int weight;

    DigitalCommodity(String name,int ram,int valencyOfMemory,String operatingSystem,int weight,String company,double price,String ID,String userName,String passWord,double percentOfDiscount,int count)
    {
        super(name,company,price,ID,userName,passWord,percentOfDiscount,count);
        this.ram=ram;
        this.valencyOfMemory=valencyOfMemory;
        this.operatingSystem=operatingSystem;
        this.weight=weight;

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
    }
   static void addDigitlaCommodityFunction(String name,String company,double price,double percentOfDiscount,String ID,String userName,String passWord,String commodityCommand)
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
            LapTop.addLapTopFunction(name,company,price,percentOfDiscount,ID,userName,passWord,ram,valencyOfMemory,weight,operatingSystem);
        }
        else
        {
            Mobile.addMobileFunction(name,ram,valencyOfMemory,weight,operatingSystem,company,price,percentOfDiscount,ID,userName,passWord);
        }
    }
}
