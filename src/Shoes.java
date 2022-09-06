import java.util.Scanner;

public class Shoes extends Garment
{
    private KindOfShoes kind;
    private int size;
    private String nameOfKind;
    Shoes(String name,String nameOfkind,int size,String country,String genus,String company,double price,String ID,String username,String password,int count,double percentOfDiscount,String statusForAdmin)
    {
        super(name,country,genus,company,price,ID,username,password,percentOfDiscount,count);
        KindOfShoes[]kinds=KindOfShoes.values();
        for(int i=0;i<kinds.length;i++)
        {
            if(kinds[i].name().equals(nameOfkind))
            {
                this.kind=kinds[i];
                break;
            }
        }
        this.size=size;
        if (statusForAdmin.equals("accept") || statusForAdmin.equals("ignore")) {
            if (statusForAdmin.equals("accept")) {
                this.setCount(super.findCountOfCertainGarment(count));
                this.clearGarmentlistShoes();
                Garment.getListOfAllGarmentsAl().add(this);
            } else
                this.setCount(count);
        }
    }
    String getNameOfKind()
    {
        return this.nameOfKind;
    }
    int getSize()
    {
        return this.size;
    }
    void setSize(int size)
    {
        this.size=size;
    }
    void setKind(String nameOfKind)
    {
        KindOfShoes[]kinds=KindOfShoes.values();
        for (int i=0;i<kinds.length;i++)
        {
            if(kinds[i].name().equals(nameOfKind))
            {
                this.kind=kinds[i];
                return;
            }
        }
        System.out.println("wrong kind");
    }
    static boolean checkShoesNameofKind(String nameOfKind)
    {
        KindOfShoes[]kinds=KindOfShoes.values();
        for(int i=0;i<kinds.length;i++)
        {
            if(kinds[i].name().equals(nameOfKind))
            {
                return true;
            }
        }
        return false;
    }
    private void clearGarmentlistShoes()
    {
        for(int i=0;i<Garment.getListOfAllGarmentsAl().size();i++)
        {
            if(Garment.getListOfAllGarmentsAl().get(i).getName().equals(this.getName()))
            {
                if(Garment.getListOfAllGarmentsAl().get(i).getClerk().getPassWord().equals(this.getClerk().getPassWord()))
                {
                    if(Garment.getListOfAllGarmentsAl().get(i) instanceof Shoes)
                    {
                        Garment.getListOfAllGarmentsAl().remove(i);
                    }
                }
            }
        }
    }
    void createShoesAfterGettingAccept()
    {
        Shoes shoes=new Shoes(this.getName(),this.getNameOfKind(),this.getSize(),
                this.getCountry(),this.getGenus(),this.getCompany(),this.getPrice(),
                this.getID(),this.getClerk().getUserName(),this.getClerk().getPassWord(),
                this.getCount(),this.getPercentOfDiscount(),"accept");
        Garment.getListOfAllGarmentsAl().add(shoes);
        this.getClerk().getCommodityListOfCertainClerk().add(shoes);
    }
    void showShoes()
    {
        System.out.println("shoes: ");
        System.out.println("name: "+this.getName());
        System.out.println("name of kind: "+this.getNameOfKind());
        System.out.println("size: "+this.getSize());
        System.out.println("country: "+this.getCountry());
        System.out.println("genus: "+this.getGenus());
        System.out.println("company: "+this.getCompany());
        System.out.println("price: "+this.getPrice()+" T");
        System.out.println("ID: "+this.getID());
        System.out.println("count: "+this.getCount());
        System.out.println("percent of dscount: "+this.getPercentOfDiscount());
        System.out.println("price after discount: "+this.getPriceAfterDiscount()+" T");
        System.out.println("average of scores: "+this.getAverageMark());
    }
   static void addShooesFunction(String name,String country,String genus,String company,double price,double percentOfDiscount,String ID,String userName,String passWord)
    {
        Scanner sc=new Scanner(System.in);
        System.out.printf("enter size: ");
        int size=sc.nextInt();
        System.out.printf("enter count: ");
        int count=sc.nextInt();
        sc.nextLine();
        String nameOfKind;
        while (true)
        {
            System.out.printf("enter name of kind: ");
            nameOfKind=sc.nextLine();
            if(Shoes.checkShoesNameofKind(nameOfKind)==false)
            {
                System.out.println("wrong name of kind.try again");
            }
            else
                break;
        }
        Shoes shoes=new Shoes(name,nameOfKind,size,country,genus,company,price,ID,userName,
                passWord,count,percentOfDiscount,"ignore");
        AddCommodityRequest request=new AddCommodityRequest(Clerk.findingClerk(userName,passWord),"add commodity request",shoes);
    }
    void changeShoesInformation(Clerk clerk)
    {
       Shoes shoes=new Shoes(this.getName(),this.getNameOfKind(),this.getSize(),
               this.getCountry(),this.getGenus(),this.getCompany(),this.getPrice(),
               this.getID(),this.getClerk().getUserName(),this.getClerk().getPassWord(),
               this.getCount(),this.getPercentOfDiscount(),"Change information of commodity request");
       shoes.changeFiledOfShoes();
       ChangeInformationOfCommodityRequest request=new ChangeInformationOfCommodityRequest(clerk,"Change information of commodity request",this,shoes);
    }
    private void changeFiledOfShoes()
    {
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
                int price=sc.nextInt();
                sc.nextLine();
                this.setPrice(price);
            }
            else if(field.equals("percent of discount"))
            {
                int percentOfDiscount=sc.nextInt();
                sc.nextLine();
                this.setPercentOfDiscount(percentOfDiscount);
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
                    if(Shoes.checkShoesNameofKind(nameOfKind)==false)
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
                int count=sc.nextInt();
                sc.nextLine();
                this.setCount(count);
            }
            else if(field.equals("leave"))
                break;
            else
                System.out.println("Wrong command");
        }
    }

}
