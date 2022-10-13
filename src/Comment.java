import workWithFiles.MyObjectOutPutStream;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Comment implements Serializable
{
   private PublicPropertiesOfGoods good;
   private Person person;
   private String opinion;
   private static ArrayList<Comment> listOfComments=new ArrayList<Comment>();
   Comment(Person person,String ID,String opinion)
   {
      this.person=person;
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
   void addCommentToFiles()throws IOException,ClassNotFoundException
   {
      if(this.getGood() instanceof DigitalCommodity)
         this.addCommentsToDigitalFiles();
      else if(this.getGood() instanceof HomeAppliance)
         this.addCommentsToHomeAppliancesFiles();
      else if(this.getGood() instanceof Garment)
         this.addCommentsToGarmentsFiles();
   }
   private void addCommentsToDigitalFiles()throws IOException,ClassNotFoundException
   {
      File file=null;
      File fileOfGoodForClerk=null;
      if(this.getGood() instanceof LapTop) {
         file = new File("saved data\\categories\\Digitals\\lap tops\\lap top " + this.getGood().getID() + "\\comments.txt");
         fileOfGoodForClerk=new File("saved data\\users\\clerks\\clerk "+this.getGood().getClerk().getUserName()+"\\goods\\lap top "+this.getGood().getID()+"\\comments.txt");
      }
      else if(this.getGood() instanceof Mobile) {
         file = new File("saved data\\categories\\Digitals\\mobiles\\mobile " + this.getGood().getID() + "\\comments.txt");
         fileOfGoodForClerk=new File("saved data\\users\\clerks\\clerk "+this.getGood().getClerk().getUserName()+"\\goods\\mobile "+this.getGood().getID()+"\\comments.txt");
      }
      MyObjectOutPutStream.setFile(file);
      MyObjectOutPutStream myObjectOutPutStream=new MyObjectOutPutStream(file);
      myObjectOutPutStream.writeObject(this);
      myObjectOutPutStream.close();
      MyObjectOutPutStream.setFile(fileOfGoodForClerk);
      MyObjectOutPutStream objectOutPutStream=new MyObjectOutPutStream(fileOfGoodForClerk);
      objectOutPutStream.writeObject(this);
      objectOutPutStream.close();
   }
   private void addCommentsToGarmentsFiles()throws IOException,ClassNotFoundException
   {
      File file=null;
      File fileOfGoodForClerk=null;
      if(this.getGood() instanceof Dress) {
         file = new File("saved data\\categories\\garments\\dresses\\dress " + this.getGood().getID() + "\\comments.txt");
         fileOfGoodForClerk=new File("saved data\\users\\clerks\\clerk "+this.getGood().getClerk().getUserName()+"\\goods\\dress "+this.getGood().getID()+"\\comments.txt");
      }
      else if(this.getGood() instanceof Shoes) {
         file = new File("saved data\\categories\\garments\\shoes\\shoes " + this.getGood().getID() + "\\comments.txt");
         fileOfGoodForClerk=new File("saved data\\users\\clerks\\clerk "+this.getGood().getClerk().getUserName()+"\\goods\\shoes "+this.getGood().getID()+"\\comments.txt");
      }
      MyObjectOutPutStream.setFile(file);
      MyObjectOutPutStream myObjectOutPutStream=new MyObjectOutPutStream(file);
      myObjectOutPutStream.writeObject(this);
      myObjectOutPutStream.close();
      MyObjectOutPutStream.setFile(fileOfGoodForClerk);
      MyObjectOutPutStream objectOutPutStream=new MyObjectOutPutStream(fileOfGoodForClerk);
      objectOutPutStream.writeObject(this);
      objectOutPutStream.close();
   }
   private void addCommentsToHomeAppliancesFiles()throws IOException,ClassNotFoundException
   {
      File file=null;
      File fileOfGoodForClerk=null;
      if(this.getGood() instanceof Stove) {
         file = new File("saved data\\categories\\home appliances\\stoves\\stove " + this.getGood().getID() + "\\comments.txt");
         fileOfGoodForClerk=new File("saved data\\users\\clerks\\clerk "+this.getGood().getClerk().getUserName()+"\\goods\\stove "+this.getGood().getID()+"\\comments.txt");
      }
      else if(this.getGood() instanceof Refrigerator) {
         file = new File("saved data\\categories\\home appliances\\refrigerators\\refrigerator " + this.getGood().getID() + "\\comments.txt");
         fileOfGoodForClerk=new File("saved data\\users\\clerks\\clerk "+this.getGood().getClerk().getUserName()+"\\goods\\refrigerator "+this.getGood().getID()+"\\comments.txt");
      }
      else if(this.getGood() instanceof Television) {
         file = new File("saved data\\categories\\home appliances\\televisions\\television " + this.getGood().getID() + "\\comments.txt");
         fileOfGoodForClerk=new File("saved data\\users\\clerks\\clerk "+this.getGood().getClerk().getUserName()+"\\goods\\television "+this.getGood().getID()+"\\comments.txt");
      }
      MyObjectOutPutStream.setFile(file);
      MyObjectOutPutStream myObjectOutPutStream=new MyObjectOutPutStream(file);
      myObjectOutPutStream.writeObject(this);
      myObjectOutPutStream.close();
      MyObjectOutPutStream.setFile(fileOfGoodForClerk);
      MyObjectOutPutStream objectOutPutStream=new MyObjectOutPutStream(fileOfGoodForClerk);
      objectOutPutStream.writeObject(this);
      objectOutPutStream.close();
   }
}
