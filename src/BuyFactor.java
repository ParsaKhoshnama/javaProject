import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.time.*;

public class BuyFactor
{
    private String dateString;
    private double price;
    private double priceAfterDiscounts;
    private ArrayList<Commodity> factorCommoditiesAl=new ArrayList<Commodity>();
    private Buyer buyer;

    BuyFactor(Buyer buyer,ArrayList<Commodity> factorCommoditiesAl,double price,double priceAfterDiscounts)
    {
        Date date = GregorianCalendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.dateString=sdf.format(date).toString();
        this.price=price;
        this.priceAfterDiscounts=priceAfterDiscounts;
        this.factorCommoditiesAl=factorCommoditiesAl;
    }
    String getDateString()
    {
        return this.dateString;
    }
    double getPrice()
    {
        return this.price;
    }
    double getPriceAfterDiscounts()
    {
        return this.priceAfterDiscounts;
    }
    ArrayList<Commodity>getFactorCommoditiesAl()
    {
        return this.factorCommoditiesAl;
    }
}
