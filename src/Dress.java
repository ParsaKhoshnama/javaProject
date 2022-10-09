import exceptions.CheckDefaultExceptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Formatter;
import java.util.Scanner;

public class Dress extends Garment implements Serializable
{

    private KindOfDress kind;
    private String nameOfKind;
    private int size;

    Dress(String name,String nameOfKind,int size,String country,String genus,String company,double price,String ID,String username,String password,int count,Discount discount,String statusForAdmin)throws IOException,ClassNotFoundException
    {
        super(name,country,genus,company,price,ID,username,password,discount,count);
      //  this.name=name;
        KindOfDress[]kinds=KindOfDress.values();
        for(int i=0;i<kinds.length;i++)
        {
            if(kinds[i].name().equals(nameOfKind))
            {
                this.kind=kinds[i];
                break;
            }
        }
        this.size=size;
        if (statusForAdmin.equals("accept") || statusForAdmin.equals("ignore")) {
            if (statusForAdmin.equals("accept")) {
                this.setCount(super.findCountOfCertainGarment(count));
                this.clearGarmentlistDress();
                Garment.getListOfAllGarmentsAl().add(this);
                Collections.sort(Garment.getListOfAllGarmentsAl());
                File dressFolder=new File("saved data\\categories\\garments\\dresses\\"+"dress "+this.getID());
                if(dressFolder.isDirectory())
                    this.editCommodityInFile();
                else
                {
                    dressFolder.mkdir();
                    this.writeInDigitalCommodityFile(dressFolder);
                }
            } else this.setCount(count);
        }
    }
    public boolean equals(Dress dress1,Dress dress2)
    {
        if(dress1.getID().equals(dress2.getID()))
            return true;
        return false;
    }
    String getNameOfKind()
    {
        return this.nameOfKind;
    }
    KindOfDress getKind()
    {
        return this.kind;
    }
    void setKind(String nameOfKind)
    {
        KindOfDress[]kinds=KindOfDress.values();
        for(int i=0;i<kinds.length;i++)
        {
            if(kinds[i].name().equals(nameOfKind))
            {
                this.kind=kinds[i];
                return;
            }
        }
        System.out.println("wrong kind");
    }
    int getSize()
    {
        return this.size;
    }
    void setSize(int size)
    {
        this.size=size;
    }
    static boolean checkDressNameOfkind(String nameOfKind)
    {
        KindOfDress[]kinds=KindOfDress.values();
        for(int i=0;i<kinds.length;i++)
        {
            if(kinds[i].name().equals(nameOfKind))
            {
                return true;
            }
        }
        System.out.println("wrong kind");
        return false;
    }
    private void clearGarmentlistDress()
    {
        for(int i=0;i<Garment.getListOfAllGarmentsAl().size();i++)
        {
            if(Garment.getListOfAllGarmentsAl().get(i).getName().equals(this.getName()))
            {
                if(Garment.getListOfAllGarmentsAl().get(i).getClerk().getPassWord().equals(this.getClerk().getPassWord()))
                {
                    if(Garment.getListOfAllGarmentsAl().get(i) instanceof Dress)
                    {
                        Garment.getListOfAllGarmentsAl().remove(i);
                    }
                }
            }
        }
    }
    void creatDressAfterGetingAccept()throws IOException,ClassNotFoundException
    {
        Dress dress=new Dress(this.getName(),this.getNameOfKind(),this.getSize(),
                this.getCountry(),this.getGenus(),this.getCompany(),this.getPrice(),
                this.getID(),this.getClerk().getUserName(),this.getClerk().getPassWord(),
                this.getCount(),this.getDiscount(),"accept");
        Garment.getListOfAllGarmentsAl().add(dress);
        this.getClerk().getCommodityListOfCertainClerk().add(dress);
    }
    void showDress()
    {
        System.out.println("Dress: ");
        System.out.println("name: "+this.getName());
        System.out.println("name of kind: "+this.getNameOfKind());
        System.out.println("size: "+this.getSize());
        System.out.println("country: "+this.getCountry());
        System.out.println("genus: "+this.getGenus());
        System.out.println("company: "+this.getCompany());
        System.out.println("price: "+this.getPrice()+" T");
        System.out.println("ID: "+this.getID());
        System.out.println("count: "+this.getCount());
        System.out.println("percent of dscount: "+this.getDiscount().getPercentOfDiscount());
        System.out.println("price after discount: "+this.getPriceAfterDiscount()+" T");
        System.out.println("average of scores: "+this.getAverageMark());
    }
    static boolean addDressFunction(String name,String country,String genus,String company,double price,Discount discount,String ID,String userName,String passWord)throws IOException,ClassNotFoundException
    {
        CheckDefaultExceptions checkDefaultExceptions=new CheckDefaultExceptions();
        Scanner sc=new Scanner(System.in);
        int size=checkDefaultExceptions.checkInt(" enter size");
        if(size<0)
            return false;
        int count=checkDefaultExceptions.checkInt("enter count");
        if(count<0)
            return false;
        String nameOfKind;
        while (true)
        {
            System.out.printf("enter name of kind: ");
            nameOfKind=sc.nextLine();
            if(Dress.checkDressNameOfkind(nameOfKind)==false)
            {
                System.out.println("wrong name of kind.try again");
            }
            else
                break;
        }
        Dress dress=new Dress(name,nameOfKind,size,country,genus,company,price,ID,userName,passWord,count,discount,"ignore");
        AddCommodityRequest request=new AddCommodityRequest(Clerk.findingClerk(userName,passWord),"add commodity request",dress);
        return true;
    }
    void changeDressInformation(Clerk clerk)throws IOException,ClassNotFoundException
    {
        Dress dress=new Dress(this.getName(),this.getNameOfKind(),this.getSize(),
                this.getCountry(),this.getGenus(),this.getCompany(),this.getPrice(),
                this.getID(),this.getClerk().getUserName(),this.getClerk().getPassWord(),
                this.getCount(),this.getDiscount(),"for change Information request");
       dress.changefieldsOfDress();
       ChangeInformationOfCommodityRequest request=new ChangeInformationOfCommodityRequest(clerk,"Change information of commodity request",this,dress);
    }
    private void changefieldsOfDress()
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
            else if(field.equals("genus"))
            {
                String genus=sc.nextLine();
                this.setGenus(genus);
            }
            else if(field.equals("size"))
            {
                int size=sc.nextInt();
                sc.nextLine();
                this.setSize(size);
            }
            else if(field.equals("name of kind"))
            {
                String nameOfKind;
                while (true)
                {
                    System.out.printf("enter name of kind: ");
                    nameOfKind=sc.nextLine();
                    if(Dress.checkDressNameOfkind(nameOfKind)==false)
                    {
                        System.out.println("wrong name of kind.try again");
                    }
                    else
                        break;
                    if(nameOfKind.equals("leave"))
                        break;
                }
            }
            else if(field.equals("count"))
            {
                int count=checkDefaultExceptions.checkInt("count");
                if(count<0)
                    continue;
                this.setCount(count);
            }
            else if(field.equals("leave"))
                break;
            else
                System.out.println("Wrong command");
        }
    }
    void createFolderOfGoodForClerk()throws IOException,ClassNotFoundException
    {
        File  goodFolderForClerk=new File("saved data\\users\\clerks\\"+"clerk "+this.getClerk().getUserName()+"\\goods\\"+"dress "+this.getID());
        goodFolderForClerk.mkdir();
        File  propertiesOfGoodForClerk=new File(goodFolderForClerk,"properties.txt");
        propertiesOfGoodForClerk.createNewFile();
        File commentsOfGoodForClerk=new File(goodFolderForClerk,"comments.txt");
        commentsOfGoodForClerk.createNewFile();
        File averageOfScores=new File(goodFolderForClerk,"average of scores.txt");
        averageOfScores.createNewFile();
        this.writePropertiesOfDress(propertiesOfGoodForClerk);
    }
    void writePropertiesOfDress(File file)throws IOException,ClassNotFoundException
    {
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        Formatter formatter=new Formatter(fileOutputStream);
        formatter.format("name: %s\n",this.getName());
        formatter.format("kind: %s\n",this.getKind().name());
        formatter.format("size: %d\n",this.getSize());
        formatter.format("company: %s\n",this.getCompany());
        formatter.format("country: %s\n",this.getCountry());
        formatter.format("price: %f\n",this.getPrice());
        formatter.format("percent of discount: %f\n",this.getDiscount().getPercentOfDiscount());
        formatter.close();
        fileOutputStream.close();
    }
    void editProperties(File file)throws IOException,ClassNotFoundException
    {
        File properties=new File(file,"properties.txt");
        properties.delete();
        properties.createNewFile();
        this.writePropertiesOfDress(properties);
        File propertiesForClerk=new File("saved data\\users\\clerks\\"+"clerk "+this.getClerk().getUserName()+"\\goods\\"+"dress "+this.getID()+"\\properties.txt");
        propertiesForClerk.delete();
        properties.createNewFile();
        this.writePropertiesOfDress(propertiesForClerk);
    }
}
