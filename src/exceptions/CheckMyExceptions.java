package exceptions;

import java.util.Scanner;

public class CheckMyExceptions {
    public String checkPhoneNumber(String string) {
        String phoneNumber;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.printf("%s :", string);
            phoneNumber = sc.nextLine();
            if (phoneNumber.charAt(0) != '0')
                throw new InvalidPhoneNumberException("invalid phone number Exception message","this phone number wasn't valid");
            if (phoneNumber.length() > 11)
                throw new InvalidPhoneNumberException("invalid phone number Exception message","this phone number wasn't valid");
            for (int i = 0; i < phoneNumber.length(); i++) {
                if (phoneNumber.charAt(i) < 48 || phoneNumber.charAt(i) > 57)
                    throw new InvalidPhoneNumberException("invalid phone number Exception message","this phone number wasn't valid");
            }
            return phoneNumber;
        } catch (InoperativeInputException inoperativeInputException) {
            System.out.println(inoperativeInputException.getMessage());
            inoperativeInputException.printStackTrace();
            return "wrong number";
        }

    }
    public String checkEmail(String string)
    {
        String eMail;
        Scanner sc=new Scanner(System.in);
        try
        {
            System.out.printf("%s :", string);
            eMail=sc.nextLine();
            int counter=0;
            for (int i=0;i<eMail.length();i++)
            {
                if(eMail.charAt(i)=='@')
                    counter++;
            }
            if(counter!=1)
                throw new InvalidEmailException("invalid Email address exception","this Email address wasn't valid");
            if(eMail.lastIndexOf(".com")==-1)
                throw new InvalidEmailException("invalid Email address exception","this Email address wasn't valid");
            return eMail;
        }
        catch (InoperativeInputException inoperativeInputException)
        {
            System.out.println(inoperativeInputException.getMessage());
            inoperativeInputException.printStackTrace();
            return "wrong eMail";
        }
    }
}
