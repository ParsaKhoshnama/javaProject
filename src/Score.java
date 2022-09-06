import java.util.ArrayList;

public class Score
{
    private double mark;
    private Buyer buyer;
    private PublicPropertiesOfGoods good;
    private static ArrayList<Score> listOfScores=new ArrayList<Score>();
    Score(String userName,String password,String ID,double mark)
    {
        this.buyer=Buyer.findBuyer(userName,password);
        for(int i=0;i<buyer.getBoughtCommoditiesList().size();i++)
        {
            if(buyer.getBoughtCommoditiesList().get(i).getGood().getID().equals(ID))
            {
                this.good=buyer.getBoughtCommoditiesList().get(i).getGood();
                break;
            }
        }
        this.good.getListOfScores().add(this);
        this.mark=mark;
        this.calculateAverage();
    }
    static boolean checkAbilityForgivingScore(String userName,String pasword,String ID)
    {
        Buyer buyer=Buyer.findBuyer(userName,pasword);
        for(int i=0;i<buyer.getBoughtCommoditiesList().size();i++)
        {
            if(buyer.getBoughtCommoditiesList().get(i).getGood().getID().equals(ID))
            {
                return true;
            }
        }
        System.out.println("you didn't buy this commodity");
        return false;
    }
    static Score checkScoreList(String userName,String pasword,String ID)
    {
        Buyer buyer=Buyer.findBuyer(userName,pasword);
        for(int i=0;i<listOfScores.size();i++)
        {
            if(listOfScores.get(i).getBuyer().getPassWord().equals(buyer.getPassWord()))
            {
                if(listOfScores.get(i).getGood().getID().equals(ID))
                {
                    System.out.println("you have graded this commodity before.you can change your mark");
                    return listOfScores.get(i);
                }
            }
        }
        return null;
    }

    Buyer getBuyer()
    {
        return this.buyer;
    }
    PublicPropertiesOfGoods getGood()
    {
        return this.good;
    }
    double getMark()
    {
        return this.mark;
    }
    void changeMark(double mark)
    {
        if(mark>10 ||mark<0)
            System.out.println("wrong mark. mark is between 0 and 10");
        else
          this.mark=mark;
    }
    private void calculateAverage()
    {
        double sum=0;
        int count=0;
        for(int i=0;i<listOfScores.size();i++)
        {
            if(listOfScores.get(i).good.getID().equals(this.good.getID()))
            {
                count++;
                sum+=listOfScores.get(i).getMark();
            }
        }
        double average=sum/count;
        this.good.setAverageMark(average);
    }
}
