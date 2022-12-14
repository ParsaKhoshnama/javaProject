import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public abstract class Garment extends PublicPropertiesOfGoods implements Comparable
{
    private String country;
    private String genus;
    private static ArrayList<Garment> listOfAllGarmentsAl=new ArrayList<Garment>();
    Garment(String name,String country, String genus,String company,double price,String ID,String username,String password,Discount discount,int count)
    {
        super(name,company,price,ID,username,password,discount,count);
        this.country=country;
        this.genus=genus;
    }
    public int compareTo(Object object)
    {
        if(this instanceof Dress && (object instanceof Dress)==false)
            return -1;
        else if((this instanceof Dress)==false && object instanceof Dress)
            return 1;
        else
        {
            if(this instanceof Dress && object instanceof Dress)
            {
                Dress dress=(Dress)object;
                return this.dressCompareTo(dress);
            }
            else
            {
                Shoes shoes=(Shoes)object;
                return this.shoesCompareTo(shoes);
            }
        }
    }
    private int dressCompareTo(Dress dress)
    {
        if(this.getName().compareTo(dress.getName())>0)
            return -1;
        else if(this.getName().compareTo(dress.getName())<0)
            return 1;
        else
        {
            if(((Dress)this).getSize()>dress.getSize())
                return -1;
            else if(((Dress)this).getSize()<dress.getSize())
                return 1;
            else
                return this.compareToForGoods(dress);
        }
    }
    private int shoesCompareTo(Shoes shoes)
    {
        if(this.getName().compareTo(shoes.getName())<0)
            return 1;
        else if(this.getName().compareTo(shoes.getName())>0)
            return -1;
        else
        {
            if(((Shoes)this).getSize()>shoes.getSize())
                return -1;
            else if(((Shoes)this).getSize()<shoes.getSize())
                return 1;
            else
                return this.compareToForGoods(shoes);
        }
    }
    String getCountry()
    {
        return this.country;
    }

    void setCountry(String country)
    {
        this.country=country;
    }
    String getGenus()
    {
        return this.genus;
    }
    void setGenus(String genus)
    {
        this.genus=genus;
    }
   static ArrayList<Garment> getListOfAllGarmentsAl()
    {
        return Garment.listOfAllGarmentsAl;
    }
    int findCountOfCertainGarment(int count)
    {
        boolean flag=false;
        int counter=0;
        for(int i=0;i<Garment.getListOfAllGarmentsAl().size();i++)
        {
            if(Garment.getListOfAllGarmentsAl().get(i).getName().equals(this.getName()))
            {
                if(Garment.getListOfAllGarmentsAl().get(i).getClerk().getPassWord().equals(this.getClerk().getPassWord()))
                {
                    if(Garment.getListOfAllGarmentsAl().get(i) instanceof Dress)
                    {
                        counter=((Dress)(Garment.getListOfAllGarmentsAl().get(i))).getCount()+count;
                        flag=true;
                        break;
                    }
                    if(Garment.getListOfAllGarmentsAl().get(i) instanceof Shoes)
                    {
                        counter=((Shoes)(Garment.getListOfAllGarmentsAl().get(i))).getCount()+count;
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
    void changeInformationRequestGarmentCommodity(Request request)
    {
        for (int i = 0; i < Garment.getListOfAllGarmentsAl().size(); i++)
        {
            if (Garment.getListOfAllGarmentsAl().get(i).getClerk().getPassWord().equals(this.getClerk().getPassWord())) {
                if (Garment.getListOfAllGarmentsAl().get(i).getID().equals(this.getID()))
                {
                    if (Garment.getListOfAllGarmentsAl().get(i) instanceof Dress)
                    {
                        Garment.getListOfAllGarmentsAl().remove(i);
                        Garment.getListOfAllGarmentsAl().add((Dress) ((ChangeInformationOfCommodityRequest) request).getReformedCommodity());
                        break;

                    }
                    if (Garment.getListOfAllGarmentsAl().get(i) instanceof Shoes)
                    {
                        Garment.getListOfAllGarmentsAl().remove(i);
                        Garment.getListOfAllGarmentsAl().add((Shoes) ((ChangeInformationOfCommodityRequest) request).getReformedCommodity());
                        break;
                    }
                }
            }
        }
        Collections.sort(Garment.getListOfAllGarmentsAl());
    }
    static void addGarmentFunction(String userName,String passWord,String commodityCommand,String company,double price,Discount discount,String ID,String name )
    {
        Scanner sc=new Scanner(System.in);
        System.out.printf("enter country: ");
        String country=sc.nextLine();
        System.out.printf("enter genus: ");
        String genus=sc.nextLine();
        if(commodityCommand.equals("dress"))
        {
            Dress.addDressFunction(name,country,genus,company,price,discount,ID,userName,passWord);
        }
        else
        {
            Shoes.addShooesFunction(name,country,genus,company,price,discount,ID,userName,passWord);
        }
    }

}
