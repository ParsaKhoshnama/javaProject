import java.util.Scanner;

public class Refrigerator extends HomeAppliance
{
    private int content;
    private   KindOfFridge kind;
   private String nameOfKindOfFridge;
    private boolean freezer;
    Refrigerator(String name,String degreeName,boolean guarantee,int content,String nameOfKindOfFridge,boolean freezer,int count,String company,double price,String ID,String userName,String passWord,double percentOfDiscount,String statusForAdmin)
    {
        super(name,degreeName,guarantee,company,price,ID,userName,passWord,percentOfDiscount,count);
        this.content=content;
        this.freezer=freezer;
        this.nameOfKindOfFridge=nameOfKindOfFridge;
        KindOfFridge[]kinds=KindOfFridge.values();
        for(int i=0;i<kinds.length;i++)
        {
            if(kinds[i].name().equals(nameOfKindOfFridge))
            {
                this.kind=kinds[i];
                break;
            }
        }
        if(statusForAdmin.equals("accept"))
        {
            this.setCount(super.findCountOfCertainHomeAppilaince(count));
            this.clearHomeApplianceListRefrigerator();
            HomeAppliance.getListOfHomeAppliancesAl().add(this);
        }
        else
            this.setCount(count);
    }
    void setContent(int content)
    {
        this.content=content;
    }
    String getNameOfKindOfFridge()
    {
        return this.nameOfKindOfFridge;
    }
    boolean isFreezer()
    {
        return this.freezer;
    }
    int getContent()
    {
        return this.content;
    }
    void setKind(String nameOfKindOfFridge)
    {
        KindOfFridge[]kinds=KindOfFridge.values();
        for(int i=0;i<kinds.length;i++)
        {
            if(kinds[i].name().equals(nameOfKindOfFridge))
            {
                return;
            }
        }
        System.out.println("wrong name for kind");
    }
    static boolean checkKindOfFridge(String nameOfKindOfFridge)
    {
        KindOfFridge[]kinds=KindOfFridge.values();
        for(int i=0;i<kinds.length;i++)
        {
            if(kinds[i].name().equals(nameOfKindOfFridge))
            {
                return true;
            }
        }
        return false;
    }
    private void clearHomeApplianceListRefrigerator()
    {
        for(int i=0;i<HomeAppliance.getListOfHomeAppliancesAl().size();i++)
        {
            if(HomeAppliance.getListOfHomeAppliancesAl().get(i).getName().equals(this.getName()))
            {
                if(HomeAppliance.getListOfHomeAppliancesAl().get(i).getClerk().getPassWord().equals(this.getClerk().getPassWord()))
                {
                    if(HomeAppliance.getListOfHomeAppliancesAl().get(i) instanceof Refrigerator)
                    {
                        HomeAppliance.getListOfHomeAppliancesAl().remove(i);
                    }
                }
            }
        }
    }
    void createRefirgeratorAfterGettingAccept()
    {
        Refrigerator refrigerator=new Refrigerator(this.getName(),this.getDegreeName(),
                this.isGuearantee(),this.getContent(),this.getNameOfKindOfFridge(),
                this.isFreezer(),this.getCount(),this.getCompany(),
                this.getPrice(),this.getID(),this.getClerk().getPassWord(),
                this.getClerk().getPassWord(),this.getPercentOfDiscount(),"accept");
        HomeAppliance.getListOfHomeAppliancesAl().add(refrigerator);
        this.getClerk().getCommodityListOfCertainClerk().add(refrigerator);
    }
    void showRefrigerator()
    {
        System.out.println("Refrigeratore: ");
        System.out.println("name: "+this.getName());
        System.out.println("degree name: "+this.getDegreeName());
        System.out.println("ID: "+this.getID());
        if(isGuearantee()==true)
            System.out.println("has guarantee");
        if(this.isFreezer()==true)
            System.out.println("has freezer");
        System.out.println("content: "+this.getContent());
        System.out.println("company: "+this.getCompany());
        System.out.println("count: "+this.getCount());
        System.out.println("price: "+this.getPrice()+" T");
        System.out.println("percent of dscount: "+this.getPercentOfDiscount());
        System.out.println("price after discount: "+this.getPriceAfterDiscount()+" T");
        System.out.println("average of scores: "+this.getAverageMark());
    }
    static void addRefrigeratorFunction(String name,String degreeOfconsumption,boolean gurant,String company,double price,double percentOfDiscount,String ID,String userName,String passWord)
    {
        Scanner sc=new Scanner(System.in);
        System.out.printf("enter content: ");
        int content= sc.nextInt();
        sc.nextLine();
        String kindOfFridge;
        while (true)
        {
            System.out.printf("enter kind: ");
            kindOfFridge=sc.nextLine();
            if(Refrigerator.checkKindOfFridge(kindOfFridge)==false)
                System.out.println("wrong name for kind of refrigerator");
            else
                break;
        }
        String hasFreezer;
        boolean freezer;
        while (true)
        {
            System.out.printf(" has freezer: ");
            hasFreezer=sc.nextLine();
            if(hasFreezer.equals("true"))
            {
                freezer=true;
                break;
            }
            else if(hasFreezer.equals("false"))
            {
                freezer=false;
                break;
            }
            else
                System.out.println("wrong name for having freezer.try again");


        }
        System.out.printf("enter count: ");
        int count=sc.nextInt();
        sc.nextLine();
        Refrigerator refrigerator=new Refrigerator(name,degreeOfconsumption,gurant,content,kindOfFridge,freezer,count,
                company,price,ID,userName,passWord,percentOfDiscount,"ignore");
        AddCommodityRequest request=new AddCommodityRequest(Clerk.findingClerk(userName,passWord),"add commodity request",refrigerator);
    }
   void changeInformationOfRefrigerator(Clerk clerk)
    {
        Refrigerator refrigerator=new Refrigerator(this.getName(),this.getDegreeName(),
                this.isGuearantee(),this.getContent(),this.getNameOfKindOfFridge(),
                this.isFreezer(),this.getCount(),this.getCompany(),
                this.getPrice(),this.getID(),this.getClerk().getPassWord(),
                this.getClerk().getPassWord(),this.getPercentOfDiscount(),"for change Information request");
        refrigerator.changeFieldOFRefrigerator();
        ChangeInformationOfCommodityRequest request=new ChangeInformationOfCommodityRequest(clerk,"Change information of commodity request",this,refrigerator);

    }
    private void changeFieldOFRefrigerator()
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
            else if(field.equals("count"))
            {
                int count=sc.nextInt();
                sc.nextLine();
                this.setCount(count);
            }
            else if(field.equals("degree of concumption"))
            {
                String degreeOfconsumption;
                while (true)
                {
                    System.out.printf("enter degree of energee cinsumption: ");
                    degreeOfconsumption=sc.nextLine();
                    if(degreeOfconsumption.equals("leave"))
                        break;
                    if(HomeAppliance.checkHomeApplianceDegreeOfenergyConsumption(degreeOfconsumption)==false)
                        System.out.println("wrong name for energy consumption.try again");
                    else
                        break;
                }
            }
            else if(field.equals("gurantee"))
            {
                String gurantee;
                while (true)
                {
                    System.out.printf("guarantee: ");

                    gurantee = sc.nextLine();
                    if (gurantee.equals("true")) {
                        this.setGuearantee(true);
                        break;
                    } else if (gurantee.equals("false")) {
                        this.setGuearantee(false);
                        break;
                    } else
                        System.out.println("wrong gurantee.try again");
                    if(gurantee.equals("leave"))
                        break;

                }
            }
            else if(field.equals("freezer"))
            {
                String hasFreezer;
                while (true)
                {
                    System.out.printf(" has freezer: ");
                    hasFreezer=sc.nextLine();
                    if(hasFreezer.equals("leave"))
                        break;
                    if(hasFreezer.equals("true"))
                    {
                        this.freezer=true;
                    }
                    else if(hasFreezer.equals("false"))
                    {
                        this.freezer=false;
                        break;
                    }
                    else
                        System.out.println("wrong name for having freezer.try again");

                }
            }
            else if(field.equals("kind of fridge"))
            {
                String kindOfFridge;
                while (true)
                {
                    System.out.printf("enter kind: ");
                    kindOfFridge=sc.nextLine();
                    if(kindOfFridge.equals("leave"))
                        break;
                    if(Refrigerator.checkKindOfFridge(kindOfFridge)==false)
                        System.out.println("wrong name for kind of refrigerator");
                    else
                        break;
                }
            }
            else if(field.equals("content"))
            {
                int content=sc.nextInt();
                sc.nextLine();
                this.setContent(content);
            }
            else if(field.equals("leave"))
                break;
            else
                System.out.println("Wrong command");
        }
    }

}
