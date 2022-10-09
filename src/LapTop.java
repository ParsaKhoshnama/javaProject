import exceptions.CheckDefaultExceptions;

import java.io.*;
import java.text.Format;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.Scanner;
public class LapTop extends DigitalCommodity implements Serializable
{
    private boolean gamingCPU;

   LapTop(String name,int ram,int valencyOfMemory,String operatingSystem,int weight,String company,double price,String ID,String userName,String passWord,boolean gamingCPU,int count,Discount discount,String statusForAdmin)throws IOException,ClassNotFoundException
   {
       super(name,ram,valencyOfMemory,operatingSystem,weight,company,price,ID,userName,passWord,discount,count);
       this.gamingCPU=gamingCPU;
       if (statusForAdmin.equals("accept") || statusForAdmin.equals("ignore"))
       {
           if (statusForAdmin.equals("accept")) {

               this.setCount(super.findCountOfCertainDigitalCommodity(count));
               this.clearDigitalCommodityLapTopAl(name);
               DigitalCommodity.getDigiritlaCommodityAL().add(this);
               Collections.sort(DigitalCommodity.getDigiritlaCommodityAL());
               File laptopFolder=new File("saved data\\categories\\Digitals\\lap tops\\"+"lap top "+this.getID());
               if(laptopFolder.isDirectory())
                   this.editCommodityInFile();
               else
               {
                   laptopFolder.mkdir();
                   this.writeInDigitalCommodityFile(laptopFolder);
               }
           } else
               this.setCount(count);
       }
   }
   public boolean equals(LapTop lapTop1,LapTop lapTop2)
   {
       if(lapTop1.getID().equals(lapTop2.getID()))
           return true;
       return false;
   }
   boolean isGamingCPU()
   {
       return this.gamingCPU;
   }
   void setGamingCPU(boolean gamingCPU)
   {
       this.gamingCPU=gamingCPU;
   }
   private void clearDigitalCommodityLapTopAl(String name)
   {
       for(int i=0;i<DigitalCommodity.getDigiritlaCommodityAL().size();i++)
       {
           if(DigitalCommodity.getDigiritlaCommodityAL().get(i).getName().equals(this.getName()))
           {
               if(DigitalCommodity.getDigiritlaCommodityAL().get(i).getClerk().getPassWord().equals(this.getClerk().getPassWord()))
               {
                   if(DigitalCommodity.getDigiritlaCommodityAL().get(i) instanceof LapTop)
                   {
                       DigitalCommodity.getDigiritlaCommodityAL().remove(i);
                   }
               }
           }
       }
   }
   void creatLaptopAfterGettingAccept()throws IOException,ClassNotFoundException
   {
       LapTop lapTop=new LapTop(this.getName(),this.getRam(),
               this.getValencyOfMemory(),this.getOperatingSystem(),this.getWeight(),this.getCompany(),
               this.getPrice(),this.getID(),this.
               getClerk().getUserName(),this.getClerk().getPassWord(),this.
               isGamingCPU(),this.getCount(),this.getDiscount(),"accept");
       DigitalCommodity.getDigiritlaCommodityAL().add(lapTop);
       this.getClerk().getCommodityListOfCertainClerk().add(lapTop);
   }
   void showLapTopInformation()
   {
       System.out.println("Lap Top: ");
       System.out.println("name: "+this.getName());
       System.out.println("ram: "+this.getRam()+"GB");
       System.out.println("valnacy of memory: "+this.getValencyOfMemory()+"GB");
       System.out.println("operating system: "+this.getOperatingSystem());
       System.out.println("weight: "+this.getWeight()+"Kg");
       System.out.println("company: "+this.getCompany());
       System.out.println("price: "+this.getPrice()+" T");
       System.out.println("ID: "+this.getID());
       if(this.gamingCPU==true)
           System.out.println("Gaming lapTop");
       System.out.println("count: "+this.getCount());
       System.out.println("percent of dscount: "+this.getDiscount().getPercentOfDiscount());
       System.out.println("price after discount: "+this.getPriceAfterDiscount()+" T");
       System.out.println("average of scores: "+this.getAverageMark());
   }
   static boolean addLapTopFunction(String name,String company,double price,Discount discount,String ID,String userName,String passWord,int ram,int valencyOfMemory,int weight,String operatingSystem)throws IOException,ClassNotFoundException
   {
       CheckDefaultExceptions checkDefaultExceptions=new CheckDefaultExceptions();
       Scanner sc=new Scanner(System.in);
       int count=checkDefaultExceptions.checkInt("enter count");
       if(count<0)
           return false;
       while (true)
       {
           System.out.printf("gamingCPU: ");
           String gamingCPU=sc.nextLine();
           if(gamingCPU.equals("true"))
           {
               LapTop lapTop=new LapTop(name,ram,valencyOfMemory,operatingSystem,weight,company,price,ID,userName,passWord,true,count,discount,"ignore");
               AddCommodityRequest request=new AddCommodityRequest(Clerk.findingClerk(userName,passWord),"add commodity request",lapTop);
               break;
           }
           else if(gamingCPU.equals("false"))
           {
               LapTop lapTop=new LapTop(name,ram,valencyOfMemory,operatingSystem,weight,company,price,ID,userName,passWord,false,count,discount,"ignore");
               AddCommodityRequest request=new AddCommodityRequest(Clerk.findingClerk(userName,passWord),"add commodity request",lapTop);
               break;
           }
           else
               System.out.println("wrong.try again");
       }
       return true;
   }
   void changeInformationOfLapTop(Clerk clerk)throws IOException,ClassNotFoundException
   {
       LapTop lapTop=new LapTop(this.getName(),this.getRam(),
               this.getValencyOfMemory(),this.getOperatingSystem(),this.getWeight(),this.getCompany(),
               this.getPrice(),this.getID(),this.
               getClerk().getUserName(),this.getClerk().getPassWord(),this.
               isGamingCPU(),this.getCount(),this.getDiscount(),"for change Information request");
       lapTop.changeFiledOfLapTop();
       ChangeInformationOfCommodityRequest request=new ChangeInformationOfCommodityRequest(clerk,"Change information of commodity request",this,lapTop);
   }
   private void changeFiledOfLapTop()
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
           else if(field.equals("ram"))
           {
               int ram=checkDefaultExceptions.checkInt("ram");
               if(ram<0)
                   continue;
               this.setRam(ram);
           }
           else if(field.equals("valancy of memory"))
           {
               int valancyOfMemory=checkDefaultExceptions.checkInt("valancy of memory");
               if(valancyOfMemory<0)
                   continue;
               this.setValencyOfMemory(valancyOfMemory);
           }
           else if(field.equals("operating system"))
           {
               String operatingSystem=sc.nextLine();
               this.setOperatingSystem(operatingSystem);
           }
           else if(field.equals("leave"))
               break;
           else
               System.out.println("Wrong command");
       }
   }
   void createFolderOfGoodForClerk()throws IOException,ClassNotFoundException
   {
     File  goodFolderForClerk=new File("saved data\\users\\clerks\\"+"clerk "+this.getClerk().getUserName()+"\\goods\\"+"lap top "+this.getID());
     goodFolderForClerk.mkdir();
     File  propertiesOfGoodForClerk=new File(goodFolderForClerk,"properties.txt");
     propertiesOfGoodForClerk.createNewFile();
     File commentsOfGoodForClerk=new File(goodFolderForClerk,"comments.txt");
     commentsOfGoodForClerk.createNewFile();
     File averageOfScores=new File(goodFolderForClerk,"average of scores.txt");
     averageOfScores.createNewFile();
     this.writePropertiesOfLapTop(propertiesOfGoodForClerk);
   }
   void writePropertiesOfLapTop(File file)throws IOException,ClassNotFoundException
   {
       FileOutputStream fileOutputStream=new FileOutputStream(file);
       Formatter formatter=new Formatter(fileOutputStream);
       formatter.format("name: %s\n",this.getName());
       formatter.format("ram: %d\n",this.getRam());
       formatter.format("valency of memory: %d\n"+this.getValencyOfMemory());
       formatter.format("operating system: %s\n",this.getOperatingSystem());
       formatter.format("gaming cpu %b\n",this.isGamingCPU());
       formatter.format("company: %s\n",this.getCompany());
       formatter.format("price: %f\n",this.getPrice());
       formatter.format("percent of discount: %f",this.getDiscount().getPercentOfDiscount());
       formatter.close();
       fileOutputStream.close();
   }
   void editProperties(File file)throws IOException,ClassNotFoundException
   {
       File properties=new File(file,"properties.txt");
       properties.delete();
       properties.createNewFile();
       this.writePropertiesOfLapTop(properties);
       File propertiesForClerk=new File("saved data\\users\\clerks\\"+"clerk "+this.getClerk().getUserName()+"\\goods\\"+"lap top "+this.getID()+"\\properties.txt");
       propertiesForClerk.delete();
       properties.createNewFile();
       this.writePropertiesOfLapTop(propertiesForClerk);
   }
}
