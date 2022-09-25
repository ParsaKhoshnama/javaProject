import java.util.Date;
public class Discount
{
   private Date date;
   private String code;
   private int capacity;
   private double percentOfDiscount;
   Discount(Date date,int capacity,double percentOfDiscount,String code)
   {
       this.date=date;
       this.capacity=capacity;
       this.percentOfDiscount=percentOfDiscount;
       this.code=code;
   }
    void setCode(String code)
   {
       this.code=code;
   }
   String getCode()
   {
       return this.code;
   }
   Date getDate()
   {
       return this.date;
   }
   int getCapacity()
   {
       return this.capacity;
   }
   void setCapacity(int capacity)
   {
       this.capacity=capacity;
   }
   void setPercentOfDiscount(double percentOfDiscount)
   {
       this.percentOfDiscount=percentOfDiscount;
   }
   double getPercentOfDiscount()
   {
       return this.percentOfDiscount;
   }
}
