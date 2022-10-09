import exceptions.CheckDefaultExceptions;
import workWithFiles.MyObjectOutPutStream;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
abstract public class DigitalCommodity extends PublicPropertiesOfGoods implements Comparable, Serializable
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
   static boolean addDigitlaCommodityFunction(String name,String company,double price,Discount discount,String ID,String userName,String passWord,String commodityCommand)throws IOException,ClassNotFoundException
    {
        CheckDefaultExceptions checkDefaultExceptions=new CheckDefaultExceptions();
        Scanner sc=new Scanner(System.in);
        int ram=checkDefaultExceptions.checkInt("enter ram");
        if(ram<0)
            return false;
        int valencyOfMemory=checkDefaultExceptions.checkInt("enter valency of memory");
        if(valencyOfMemory<0)
            return false;
        int weight=checkDefaultExceptions.checkInt("enter weight");
        if(weight<0)
            return false;
        sc.nextLine();
        System.out.printf("enter operating system: ");
        String operatingSystem=sc.nextLine();
        if(commodityCommand.equals("laptop"))
        {
          return   LapTop.addLapTopFunction(name,company,price,discount,ID,userName,passWord,ram,valencyOfMemory,weight,operatingSystem);
        }
        else
        {
           return Mobile.addMobileFunction(name,ram,valencyOfMemory,weight,operatingSystem,company,price,discount,ID,userName,passWord);
        }
    }
    static void fillArraylistOfDigitalCommodities()throws IOException,ClassNotFoundException
    {
        DigitalCommodity.getDigiritlaCommodityAL().clear();
        File listOfDigitalCommodities=new File("saved data\\categories\\Digitals\\list of digitals.txt");
        FileInputStream fileInputStream=new FileInputStream(listOfDigitalCommodities);
        DigitalCommodity digitalCommodity;
        while (true)
        {
            try(ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream))
            {
                digitalCommodity=(DigitalCommodity)objectInputStream.readObject();
                if(digitalCommodity!=null)
                    DigitalCommodity.getDigiritlaCommodityAL().add(digitalCommodity);
            }
            catch (Exception exception)
            {
                fileInputStream.close();
                break;
            }
        }
    }
    void writeInDigitalCommodityFile(File FILE)throws IOException,ClassNotFoundException
    {
        File file=new File("saved data\\categories\\Digitals\\list of digitals.txt");
        MyObjectOutPutStream.setFile(file);
        MyObjectOutPutStream myObjectOutPutStream=new MyObjectOutPutStream(file);
        myObjectOutPutStream.writeObject(this);
        myObjectOutPutStream.close();
        File propertyFile=new File(FILE,"properties.txt");
        propertyFile.createNewFile();
        File comments=new File(FILE,"comments.txt");
        comments.createNewFile();
        File averageOfScores=new File(FILE,"average of scores.txt");
        averageOfScores.createNewFile();
        if(this instanceof LapTop)
        {
            ((LapTop) this).writePropertiesOfLapTop(propertyFile);
            ((LapTop)this).createFolderOfGoodForClerk();
            this.getClerk().addGoodInClerkListFile((LapTop)this);
        }
        else
        {
            ((Mobile)this).writePropertiesOfMobile(propertyFile);
            ((Mobile)this).createFolderOfGoodForClerk();
            this.getClerk().addGoodInClerkListFile((Mobile)this);
        }
    }
    void editCommodityInFile()throws IOException,ClassNotFoundException
    {
        File file=new File("saved data\\categories\\Digitals\\list of digitals.txt");
        file.delete();
        file.createNewFile();
        MyObjectOutPutStream.setFile(file);
        MyObjectOutPutStream myObjectOutPutStream=new MyObjectOutPutStream(file);
        for(int i=0;i<DigitalCommodity.getDigiritlaCommodityAL().size();i++)
            myObjectOutPutStream.writeObject(DigitalCommodity.getDigiritlaCommodityAL().get(i));
        myObjectOutPutStream.close();
        File listOfGoodsForClerk=new File("saved data\\users\\clerks\\"+"clerk "+this.getClerk().getUserName()+"\\goods\\list of goods.txt");
        listOfGoodsForClerk.delete();
        listOfGoodsForClerk.createNewFile();
        MyObjectOutPutStream.setFile(listOfGoodsForClerk);
        MyObjectOutPutStream objectOutPutStream=new MyObjectOutPutStream(listOfGoodsForClerk);
        for(int i=0;i<this.getClerk().getCommodityListOfCertainClerk().size();i++)
            myObjectOutPutStream.writeObject(this.getClerk().getCommodityListOfCertainClerk().get(i));
        objectOutPutStream.close();
    }
}
