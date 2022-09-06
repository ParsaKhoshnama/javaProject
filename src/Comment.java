import java.util.ArrayList;

public class Comment
{
   private PublicPropertiesOfGoods good;
   private Person person;
   private String opinion;
   private static ArrayList<Comment> listOfComments=new ArrayList<Comment>();
   Comment(String userName,String passWord,String ID,String opinion)
   {
      this.person=Person.findPerson(userName,passWord);
      this.good=Commodity.findCommodity(ID);
      this.opinion=opinion;
      this.good.getListOfComments().add(this);
      listOfComments.add(this);
   }
   Person getPerson()
   {
      return this.person;
   }
   String getOpinion()
   {
      return this.opinion;
   }
   PublicPropertiesOfGoods getGood()
   {
      return this.good;
   }
   static boolean checkIDcommodity(String ID)
   {
      if(Commodity.findCommodity(ID)==null)
         return false;
      else
         return true;
   }
}
