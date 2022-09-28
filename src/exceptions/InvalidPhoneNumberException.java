package exceptions;

public class InvalidPhoneNumberException extends InoperativeInputException
{
    InvalidPhoneNumberException(String parentMessage,String childMessage)
    {
        super("parent message: "+parentMessage+"\n"+"child message: "+childMessage);
    }
}
