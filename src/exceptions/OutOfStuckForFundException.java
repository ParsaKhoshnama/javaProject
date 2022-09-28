package exceptions;

public class OutOfStuckForFundException extends InoperativeBuyException
{
    OutOfStuckForFundException(String parentMessage,String childMessage)
    {
        super("parent message: "+parentMessage+"\n"+"child message: "+childMessage);
    }
}
