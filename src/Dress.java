import exceptions.CheckDefaultExceptions;

import java.util.Collections;
import java.util.Scanner;

public class Dress extends Garment
{

    private KindOfDress kind;
    private String nameOfKind;
    private int size;

    Dress(String name,String nameOfKind,int size,String country,String genus,String company,double price,String ID,String username,String password,int count,Discount discount,String statusForAdmin)
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
    void creatDressAfterGetingAccept()
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
    static boolean addDressFunction(String name,String country,String genus,String company,double price,Discount discount,String ID,String userName,String passWord)
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
    void changeDressInformation(Clerk clerk)
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
}
