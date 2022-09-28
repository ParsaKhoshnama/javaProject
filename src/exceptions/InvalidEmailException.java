package exceptions;

public class InvalidEmailException extends InoperativeInputException
{
    InvalidEmailException(String parentMessage,String childMessage)
    {
        super("parent message: "+parentMessage+"\n"+"child message: "+childMessage);
    }
}
