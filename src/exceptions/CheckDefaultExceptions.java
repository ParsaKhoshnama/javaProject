package exceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CheckDefaultExceptions
{
   public int checkInt(String string)
    {
        Scanner sc=new Scanner(System.in);
        int number;
        while (true)
        {
            try
            {
                System.out.printf("%s: ",string);
                number=sc.nextInt();
                    if(number<0)
                       System.out.println("this number wasn't valid");
                    return number;
            }
            catch (InputMismatchException inputMismatchException)
            {
                System.out.println(inputMismatchException.getMessage());
                inputMismatchException.printStackTrace();
                sc.nextLine();
                System.out.println("wrong chars.you must enter an integer number ");
                String command;
                while (true)
                {
                    System.out.printf("enter try again or leave: ");
                    command=sc.nextLine();
                    if(command.equals("leave"))
                        return -1;
                    else if(command.equals("trt again"))
                        break;
                    else
                        System.out.println("wrong command");
                }
            }

        }
    }
   public double checkDouble(String string)
    {
        Scanner sc=new Scanner(System.in);
        double number;
        while (true)
        {
            try
            {
                System.out.printf("%s: ",string);
                number=sc.nextDouble();
                if(number<0)
                    System.out.println("this number wasn't valid");
                return number;
            }
            catch (InputMismatchException inputMismatchException)
            {
                System.out.println(inputMismatchException.getMessage());
                inputMismatchException.printStackTrace();
                sc.nextLine();
                System.out.println("wrong chars.you must enter a double number");
                String command;
                while (true)
                {
                    System.out.printf("enter try again or leave: ");
                    command=sc.nextLine();
                    if(command.equals("leave"))
                        return -1.0;
                    else if(command.equals("try again"))
                        break;
                    else
                        System.out.println("wrong command");
                }
            }
        }
    }
}
