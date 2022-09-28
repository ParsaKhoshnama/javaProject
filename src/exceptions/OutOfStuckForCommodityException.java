package exceptions;

public class OutOfStuckForCommodityException extends InoperativeBuyException
{
    OutOfStuckForCommodityException(String parentMessage,String childMessage)
    {
        super("parent message: "+parentMessage+"\n"+"child message: "+childMessage);
    }
}
