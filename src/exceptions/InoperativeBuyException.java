package exceptions;
public class InoperativeBuyException extends RuntimeException
{
   public InoperativeBuyException(String message)
    {
        super(message);
    }
}
