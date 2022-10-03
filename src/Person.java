import exceptions.CheckDefaultExceptions;
import exceptions.CheckMyExceptions;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
public abstract class Person implements Serializable
{
    private String userName;
    private String passWord;
    private String name;
    private String lastName;
    private String eMail;
    private String phoneNumber;

    Person(String userName,String passWord,String name,String lastName,String phoneNumber,String eMail)
    {
        this.name=name;
        this.lastName=lastName;
        this.eMail=eMail;
        this.phoneNumber=phoneNumber;
        this.passWord=passWord;
        this.userName=userName;
    }
    void setName(String name)
    {
        this.name=name;
    }
    String getName()
    {
        return this.name;
    }
    void setLastName(String lastName)
    {
        this.lastName=lastName;
    }
    String getLastName()
    {
        return this.lastName;
    }
    void seteMail(String eMail)
    {
        this.eMail=eMail;
    }
    String geteMail()
    {
        return this.eMail;
    }
    void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber=phoneNumber;
    }
    String getPhoneNumber()
    {
        return this.phoneNumber;
    }
    void setUserName(String userName)
    {
        this.userName=userName;
    }
    String getUserName()
    {
        return this.userName;
    }
    void setPassWord(String passWord)
    {
        this.passWord=passWord;
    }
    String getPassWord()
    {
        return this.passWord;
    }
 static boolean  checkPersonsInformation(String firstName,String lastName, String phoneNumber,String eMail,String passWord,String userName)throws IOException,ClassNotFoundException
 {
     for(int i=0;i<Admin.creatAdminObject().getPersonsListAL().size();i++)
     {
         if(Admin.creatAdminObject().getPersonsListAL().get(i).getPassWord().equals(passWord) || Admin.creatAdminObject().getPersonsListAL().get(i).getUserName().equals(userName))
         {
             System.out.println("the password or username that you enter has been used before");
             return false;
         }
         if(Admin.creatAdminObject().getPersonsListAL().get(i).getName().equals(firstName) && Admin.creatAdminObject().getPersonsListAL().get(i).getLastName().equals(lastName))
         {
             System.out.println("this name and last name has been registerated before");
             return false;
         }
         if(Admin.creatAdminObject().getPersonsListAL().get(i).getPhoneNumber().equals(phoneNumber))
         {
             System.out.println("this phoneNumber has been used before");
             return false;
         }
         if(Admin.creatAdminObject().getPersonsListAL().get(i).geteMail().equals(eMail))
         {
             System.out.println("this email has been used before");
             return false;
         }
     }
     return true;
 }
  static boolean checkPhoneNumber(String phoneNumber)
  {
      for(int i=0;i<phoneNumber.length();i++)
      {
          if(phoneNumber.charAt(i)<48 || phoneNumber.charAt(i)>57)
          {
              System.out.println("charector "+phoneNumber.charAt(i)+" for phoneNumber is wrong .try again");
              return false;
          }
      }
      return true;
  }

  static boolean checkPasswordAndUsername(String passWord, String userName)throws IOException,ClassNotFoundException
  {
      for(int i=0;i<Admin.creatAdminObject().getPersonsListAL().size();i++)
      {
          if(Admin.creatAdminObject().getPersonsListAL().get(i).getUserName().equals(userName) && Admin.creatAdminObject().getPersonsListAL().get(i).getPassWord().equals(passWord))
             return true;
      }
      return false;
  }
  static Person findPerson(String userName,String passWord)
  {
      for(int i=0;i<Clerk.getClerkListAl().size();i++)
      {
          if(Clerk.getClerkListAl().get(i).getUserName().equals(userName) )
          {
              if(Clerk.getClerkListAl().get(i).getPassWord().equals(passWord))
              {
                  return Clerk.getClerkListAl().get(i);
              }
          }
      }
      for (int i=0;i<Buyer.getBuyersListAl().size();i++)
      {
          if(Buyer.getBuyersListAl().get(i).getUserName().equals(userName) )
          {
              if(Buyer.getBuyersListAl().get(i).getPassWord().equals(passWord))
              {
                  return Buyer.getBuyersListAl().get(i);
              }
          }
      }
      return null;
  }
  void changeInformation()
  {
      CheckMyExceptions checkMyExceptions=new CheckMyExceptions();
      Scanner sc=new Scanner(System.in);
      String changeInformationCommand;
      Person person=Person.findPerson(userName,passWord);
      while (true)
      {
          System.out.printf("enter (name)or(last name)or(username)or(password)or(email)or(phone number)or(leave): ");
          changeInformationCommand=sc.nextLine();
          if(changeInformationCommand.equals("leave"))
              break;
          else if(changeInformationCommand.equals("name"))
          {
              String rename=sc.nextLine();
              person.setName(rename);
          }
          else if(changeInformationCommand.equals("last name"))
          {
              String relastName=sc.nextLine();
              person.setLastName(relastName);
          }
          else if(changeInformationCommand.equals("username"))
          {
              String reUsername=sc.nextLine();
              person.setUserName(reUsername);
          }
          else if(changeInformationCommand.equals("password"))
          {
              String rePassword=sc.nextLine();
              person.setPassWord(rePassword);
          }
          else if(changeInformationCommand.equals("email"))
          {
              String rEmail=checkMyExceptions.checkEmail("Enter your eMail address");
              if(rEmail.equals("wrong eMail"))
                  continue;
              person.seteMail(rEmail);
          }
          else if(changeInformationCommand.equals("phone number"))
          {
              String rePhoneNumber=checkMyExceptions.checkPhoneNumber("Enter your phoneNumber");
              if(rePhoneNumber.equals("wrong number"))
                  continue;
              person.setPhoneNumber(rePhoneNumber);
          }
          else
          {
              System.out.println("wrong command");
          }
      }
  }
  void sendComment()
  {
      Scanner sc=new Scanner(System.in);
      String ID;
      while (true)
      {
          System.out.printf("enter the ID: ");
          ID=sc.nextLine();
          if(Commodity.findCommodity(ID)!=null)
              break;
         else
          {
              String command;
              while (true)
              {
                  System.out.printf("press leave or try again");
                  command=sc.nextLine();
                  if(command.equals("leave"))
                      return;
                  else if(command.equals("try again"))
                      break;
                  else
                      System.out.println("wrong command");
              }
          }
      }
      System.out.printf("write your comment: ");
      String opinion=sc.nextLine();
      Comment  comment=new Comment(this,ID,opinion);
  }
    static void getLOginInformation()
    {
        String userName,passWord;
        Scanner sc=new Scanner(System.in);
        System.out.printf("Enter userName: ");
        userName=sc.nextLine();
        System.out.println();
        System.out.println("_____________");
        System.out.printf("Enter passWord: ");
        passWord=sc.nextLine();
        System.out.println();
        System.out.println("------------------");
    }
    static void getRegisterationInformations()throws IOException,ClassNotFoundException
    {
        CheckMyExceptions checkMyExceptions=new CheckMyExceptions();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.printf("If you want to be buyer enter(clerk) else enter(buyer): ");
            String buyerOrClerkCommand;
            buyerOrClerkCommand = sc.nextLine();
            System.out.printf("Enter first name: ");
            String firstName = sc.nextLine();
            System.out.println();
            System.out.printf("Enter last name: ");
            String lastName = sc.nextLine();
            System.out.println();
            String phoneNumber = checkMyExceptions.checkPhoneNumber("Enter your phoneNumber");
            if(phoneNumber.equals("wrong number"))
                continue;
            System.out.println();
            String eMail =checkMyExceptions.checkEmail("Enter your eMail address");
            if(eMail.equals("wrong eMail"))
                continue;
            System.out.println();
            String userName, passWord;
            System.out.printf("Enter userName: ");
            userName = sc.nextLine();
            System.out.println();
            System.out.printf("Enter passWord: ");
            passWord = sc.nextLine();

            System.out.println();
            if (buyerOrClerkCommand.equals("buyer"))
            {
                Buyer.buyerRegisteration(firstName, lastName, phoneNumber, passWord, userName, eMail);
                return;
            }
            else if (buyerOrClerkCommand.equals("clerk"))
            {
                Clerk.clerkRegisteration(firstName, lastName, phoneNumber, eMail, userName, passWord);
                return;
            }
            else
                System.out.println("wrong command");
        }

    }
}
