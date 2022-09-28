package exceptions;
public class InoperativeBuyException extends RuntimeException
{
    InoperativeBuyException(String message)
    {
        super(message);
    }
}
