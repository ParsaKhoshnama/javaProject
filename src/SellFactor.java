import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
public class SellFactor
{
    private Buyer buyer;
    private String dateString;
    private Commodity commodity;
    private double price;
    private double priceAfterDiscount;
    SellFactor(Buyer buyer,Commodity commodity,double price,double priceAfterDiscount)
    {
        Date date = GregorianCalendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.dateString=sdf.format(date).toString();
        this.buyer=buyer;
        this.commodity=commodity;
        this.price=price;
        this.priceAfterDiscount=priceAfterDiscount;
        this.getCommodity().getGood().getDiscount().setCapacity(this.getCommodity().getGood().getDiscount().getCapacity()-this.commodity.getCount());
    }
    double getPrice()
    {
        return this.price;
    }
    double getPriceAfterDiscount()
    {
        return this.priceAfterDiscount;
    }
    Buyer getBuyer()
    {
        return this.buyer;
    }
    Commodity getCommodity()
    {
        return this.commodity;
    }
    String getDateString()
    {
        return this.dateString;
    }
}
