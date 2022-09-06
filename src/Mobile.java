import java.util.Scanner;
public class Mobile extends  DigitalCommodity
{
    private int countOfSimcards;
    private String gradeOfCamera;
    Mobile(String name,int ram,int valencyOfMemory,String operatingSystem,int weight,String company,double price,String ID,String userName,String passWord,int countOfSimcards,String gradeOfCamera,int count,double percentOfDiscount,String statusForAdmin)
    {
        super(name,ram,valencyOfMemory,operatingSystem,weight,company,price,ID,userName,passWord,percentOfDiscount,count);
        this.countOfSimcards=countOfSimcards;
        this.gradeOfCamera=gradeOfCamera;
        if(statusForAdmin.equals("accept"))
        {
                this.setCount(super.findCountOfCertainDigitalCommodity(count));
                this.clearDigitalCommodityMobileAl(name);
                DigitalCommodity.getDigiritlaCommodityAL().add(this);
        }
        else
            this.setCount(count);
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
    void creatMobileAfterGettingAccept()
    {
        Mobile mobile=new Mobile(this.getName(),this.getRam(),this.getValencyOfMemory(),
                this.getOperatingSystem(),this.getWeight(),this.getCompany(),this.getPrice(),
                this.getID(),this.getClerk().getUserName(),this.getClerk().getPassWord(),
                this.getCountOfSimcards(),this.getGradeOfCamera(),this.getCount(),this.getCountOfSimcards(),"accept");
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
        System.out.println("percent of dscount: "+this.getPercentOfDiscount());
        System.out.println("price after discount: "+this.getPriceAfterDiscount()+" T");
        System.out.println("average of scores: "+this.getAverageMark());
    }

    static void addMobileFunction(String name,int ram,int valencyOfMemory,int weight,String operatingSystem ,String company,double price,double percentOfDiscount,String ID,String userName,String passWord)
    {
        Scanner sc=new Scanner(System.in);
        System.out.printf("count of simcards: ");
        int countOfSimcards=sc.nextInt();
        System.out.printf("enter count: ");
        int count=sc.nextInt();
        sc.nextLine();
        System.out.printf("enter grade of camera: ");
        String gardeOfCammera=sc.nextLine();
        Mobile mobile=new Mobile(name,ram,valencyOfMemory,operatingSystem,weight,company,price,ID,userName,passWord,countOfSimcards,gardeOfCammera,count,percentOfDiscount,"ignore");
        AddCommodityRequest request=new AddCommodityRequest(Clerk.findingClerk(userName,passWord),"add commodity request",mobile);
    }
    void changeInformationOfMobile(Clerk clerk)
    {
        Mobile mobile=new Mobile(this.getName(),this.getRam(),this.getValencyOfMemory(),
                this.getOperatingSystem(),this.getWeight(),this.getCompany(),this.getPrice(),
                this.getID(),this.getClerk().getUserName(),this.getClerk().getPassWord(),
                this.getCountOfSimcards(),this.getGradeOfCamera(),this.getCount(),this.getCountOfSimcards(),"for change Information request");
        mobile.changeFieldOfMobile();
        ChangeInformationOfCommodityRequest request=new ChangeInformationOfCommodityRequest(clerk,"Change information of commodity request",this,mobile);
    }
    private void changeFieldOfMobile()
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
            else if(field.equals("ram"))
            {
                int ram=sc.nextInt();
                sc.nextLine();
                this.setRam(ram);
            }
            else if(field.equals("valancy of memory"))
            {
                int valancyOfMemory=sc.nextInt();
                sc.nextLine();
                this.setValencyOfMemory(valancyOfMemory);
            }
            else if(field.equals("count of simcards"))
            {
                int countOfSimcarads=sc.nextInt();
                sc.nextLine();
                this.setCountOfSimcards(countOfSimcarads);
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
}
