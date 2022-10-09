import exceptions.CheckDefaultExceptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Formatter;
import java.util.Scanner;
public class Mobile extends  DigitalCommodity implements Serializable
{
    private int countOfSimcards;
    private String gradeOfCamera;
    Mobile(String name,int ram,int valencyOfMemory,String operatingSystem,int weight,String company,double price,String ID,String userName,String passWord,int countOfSimcards,String gradeOfCamera,int count,Discount discount,String statusForAdmin)throws IOException,ClassNotFoundException
    {
        super(name,ram,valencyOfMemory,operatingSystem,weight,company,price,ID,userName,passWord,discount,count);
        this.countOfSimcards=countOfSimcards;
        this.gradeOfCamera=gradeOfCamera;
        if (statusForAdmin.equals("accept") || statusForAdmin.equals("ignore")) {
            if (statusForAdmin.equals("accept")) {
                this.setCount(super.findCountOfCertainDigitalCommodity(count));
                this.clearDigitalCommodityMobileAl(name);
                DigitalCommodity.getDigiritlaCommodityAL().add(this);
                Collections.sort(DigitalCommodity.getDigiritlaCommodityAL());
                File mobileFolder=new File("saved data\\categories\\Digitals\\mobiles\\mobile "+this.getID());
                if(mobileFolder.isDirectory())
                    this.editCommodityInFile();
                else
                {
                    mobileFolder.mkdir();
                    this.writeInDigitalCommodityFile(mobileFolder);
                }
            } else
                this.setCount(count);
        }
    }
    public boolean equals(Mobile mobile1,Mobile mobile2)
    {
        if(mobile1.getID().equals(mobile2.getID()))
            return true;
        else
            return false;
    }
    int getCountOfSimcards()
    {
        return this.countOfSimcards;
    }
    void setCountOfSimcards(int countOfSimcards)
    {
        this.countOfSimcards=countOfSimcards;
    }
    String getGradeOfCamera()
    {
        return this.gradeOfCamera;
    }
    void setGradeOfCamera(String gradeOfCamera)
    {
        this.gradeOfCamera=gradeOfCamera;
    }
    private void clearDigitalCommodityMobileAl(String name)
    {
        for(int i=0;i<DigitalCommodity.getDigiritlaCommodityAL().size();i++)
        {
            if(DigitalCommodity.getDigiritlaCommodityAL().get(i).getName().equals(this.getName()))
            {
                if(DigitalCommodity.getDigiritlaCommodityAL().get(i).getClerk().getPassWord().equals(this.getClerk().getPassWord()))
                {
                    if(DigitalCommodity.getDigiritlaCommodityAL().get(i) instanceof Mobile)
                    {
                        DigitalCommodity.getDigiritlaCommodityAL().remove(i);
                    }
                }
            }
        }
    }
    void creatMobileAfterGettingAccept()throws IOException,ClassNotFoundException
    {
        Mobile mobile=new Mobile(this.getName(),this.getRam(),this.getValencyOfMemory(),
                this.getOperatingSystem(),this.getWeight(),this.getCompany(),this.getPrice(),
                this.getID(),this.getClerk().getUserName(),this.getClerk().getPassWord(),
                this.getCountOfSimcards(),this.getGradeOfCamera(),this.getCount(),this.getDiscount(),"accept");
        DigitalCommodity.getDigiritlaCommodityAL().add(mobile);
        this.getClerk().getCommodityListOfCertainClerk().add(mobile);
    }
    void showMobileInformation()
    {
        System.out.println("Mobile: ");
        System.out.println("name: "+this.getName());
        System.out.println("ram: "+this.getRam()+"GB");
        System.out.println("valnacy of memory: "+this.getValencyOfMemory()+"GB");
        System.out.println("operating system: "+this.getOperatingSystem());
        System.out.println("weight: "+this.getWeight()+"Kg");
        System.out.println("company: "+this.getCompany());
        System.out.println("price: "+this.getPrice()+" T");
        System.out.println("ID: "+this.getID());
        System.out.println("count of simacards: "+this.getCountOfSimcards());
        System.out.println("count: "+this.getCount());
        System.out.println("percent of dscount: "+this.getDiscount().getPercentOfDiscount());
        System.out.println("price after discount: "+this.getPriceAfterDiscount()+" T");
        System.out.println("average of scores: "+this.getAverageMark());
    }

    static boolean addMobileFunction(String name,int ram,int valencyOfMemory,int weight,String operatingSystem ,String company,double price,Discount discount,String ID,String userName,String passWord)throws IOException,ClassNotFoundException
    {
        CheckDefaultExceptions checkDefaultExceptions=new CheckDefaultExceptions();
        Scanner sc=new Scanner(System.in);
        int countOfSimcards=checkDefaultExceptions.checkInt("count of simcards");
        if(countOfSimcards<0)
            return false;
        System.out.printf("enter count: ");
        int count=checkDefaultExceptions.checkInt("enter count");
        if(count<0)
            return false;
        System.out.printf("enter grade of camera: ");
        String gardeOfCammera=sc.nextLine();
        Mobile mobile=new Mobile(name,ram,valencyOfMemory,operatingSystem,weight,company,price,ID,userName,passWord,countOfSimcards,gardeOfCammera,count,discount,"ignore");
        AddCommodityRequest request=new AddCommodityRequest(Clerk.findingClerk(userName,passWord),"add commodity request",mobile);
        return true;
    }
    void changeInformationOfMobile(Clerk clerk)throws IOException,ClassNotFoundException
    {
        Mobile mobile=new Mobile(this.getName(),this.getRam(),this.getValencyOfMemory(),
                this.getOperatingSystem(),this.getWeight(),this.getCompany(),this.getPrice(),
                this.getID(),this.getClerk().getUserName(),this.getClerk().getPassWord(),
                this.getCountOfSimcards(),this.getGradeOfCamera(),this.getCount(),this.getDiscount(),"for change Information request");
        mobile.changeFieldOfMobile();
        ChangeInformationOfCommodityRequest request=new ChangeInformationOfCommodityRequest(clerk,"Change information of commodity request",this,mobile);
    }
    private void changeFieldOfMobile()
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
                int ram=sc.nextInt();
                sc.nextLine();
                this.setRam(ram);
            }
            else if(field.equals("valancy of memory"))
            {
                int valancyOfMemory=checkDefaultExceptions.checkInt("valancy of memory");
                if(valancyOfMemory<0)
                    continue;
                this.setValencyOfMemory(valancyOfMemory);
            }
            else if(field.equals("count of simcards"))
            {
                int countOfSimcards=checkDefaultExceptions.checkInt("count of simcards");
                if(countOfSimcards<0)
                    continue;
                this.setCountOfSimcards(countOfSimcards);
            }
            else if(field.equals("grade of camera"))
            {
                String gradeOfCamera=sc.nextLine();
                this.setGradeOfCamera(gradeOfCamera);
            }
            else if(field.equals("leave"))
                break;
            else
                System.out.println("Wrong command");
        }
    }
    void writePropertiesOfLapTop(File file)throws IOException,ClassNotFoundException
    {
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        Formatter formatter=new Formatter(fileOutputStream);
        formatter.format("name: %s\n",this.getName());
        formatter.format("ram: %d\n",this.getRam());
        formatter.format("valency of memory: %d\n",this.getValencyOfMemory());
        formatter.format("operating system: %s\n",this.getOperatingSystem());
        formatter.format("weight: %d\n",this.getWeight());
        formatter.format("company: %s\n",this.getCompany());
        formatter.format("price: %f\n",this.getPrice());
        formatter.format("percent of discount: %f",this.getDiscount().getPercentOfDiscount());
        formatter.close();
        fileOutputStream.close();
    }
    void createFolderOfGoodForClerk()throws IOException,ClassNotFoundException
    {
        File  goodFolderForClerk=new File("saved data\\users\\clerks\\"+"clerk "+this.getClerk().getUserName()+"\\goods\\"+"mobile "+this.getID());
        goodFolderForClerk.mkdir();
        File  propertiesOfGoodForClerk=new File(goodFolderForClerk,"properties.txt");
        propertiesOfGoodForClerk.createNewFile();
        File commentsOfGoodForClerk=new File(goodFolderForClerk,"comments.txt");
        commentsOfGoodForClerk.createNewFile();
        File averageOfScores=new File(goodFolderForClerk,"average of scores.txt");
        averageOfScores.createNewFile();
        this.writePropertiesOfLapTop(propertiesOfGoodForClerk);
    }
    void editProperties(File file)throws IOException,ClassNotFoundException
    {
        File properties=new File(file,"properties.txt");
        properties.delete();
        properties.createNewFile();
        this.writePropertiesOfLapTop(properties);
        File propertiesForClerk=new File("saved data\\users\\clerks\\"+"clerk "+this.getClerk().getUserName()+"\\goods\\"+"mobile "+this.getID()+"\\properties.txt");
        propertiesForClerk.delete();
        properties.createNewFile();
        this.writePropertiesOfLapTop(propertiesForClerk);
    }
}
