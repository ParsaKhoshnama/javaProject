import exceptions.CheckDefaultExceptions;
import exceptions.CheckMyExceptions;
import workWithFiles.MyObjectOutPutStream;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;

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
    void seteMail(String eMail)throws IOException,ClassNotFoundException
    {
        if(this.checkEmail(eMail)==true)
            this.eMail=eMail;
        else
            System.out.println("this eMail has been used before");
    }
    String geteMail()
    {
        return this.eMail;
    }
    void setPhoneNumber(String phoneNumber)throws IOException,ClassNotFoundException
    {
        if(this.checkPhoneNumber(phoneNumber)==true)
            this.phoneNumber=phoneNumber;
        else
            System.out.println("this phone number has been used before");
    }
    String getPhoneNumber()
    {
        return this.phoneNumber;
    }
    void setUserName(String userName)throws IOException,ClassNotFoundException
    {
        if(this.checkUsername(userName)==true)
            this.userName=userName;
        else
            System.out.println("this username has been used before");
    }
    String getUserName()
    {
        return this.userName;
    }
    void setPassWord(String passWord)throws IOException,ClassNotFoundException
    {
        if(this.checkPassword(passWord)==true)
            this.passWord=passWord;
        else
            System.out.println("this password has been used before");
    }
    String getPassWord()
    {
        return this.passWord;
    }
 static boolean  checkPersonsInformation(String firstName,String lastName, String phoneNumber,String eMail,String passWord,String userName)throws IOException,ClassNotFoundException
 {
     writeClerksAndBuyersInArrayLists();
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
  static boolean checkPasswordAndUsername(String passWord, String userName)throws IOException,ClassNotFoundException
  {
      writeClerksAndBuyersInArrayLists();
      for(int i=0;i<Admin.creatAdminObject().getPersonsListAL().size();i++)
      {
          if(Admin.creatAdminObject().getPersonsListAL().get(i).getUserName().equals(userName) && Admin.creatAdminObject().getPersonsListAL().get(i).getPassWord().equals(passWord))
             return true;
      }
      return false;
  }
  private  boolean checkPhoneNumber(String phoneNumber)throws IOException,ClassNotFoundException
  {
      if(this.getPhoneNumber().equals(phoneNumber))
          return true;
      else
      {
          for(int i=0;i<Admin.creatAdminObject().getPersonsListAL().size();i++)
          {
              if(Admin.creatAdminObject().getPersonsListAL().get(i).getPhoneNumber().equals(phoneNumber) && Admin.creatAdminObject().getPersonsListAL().get(i).getPhoneNumber().compareTo(this.getPhoneNumber())!=0)
                  return false;
          }
          return true;
      }
  }
  private boolean checkEmail(String eMail)throws IOException,ClassNotFoundException
  {
      if(this.geteMail().equals(eMail))
          return true;
      else
      {
          for(int i=0;i<Admin.creatAdminObject().getPersonsListAL().size();i++)
          {
              if(Admin.creatAdminObject().getPersonsListAL().get(i).geteMail().equals(eMail) && Admin.creatAdminObject().getPersonsListAL().get(i).geteMail().compareTo(this.geteMail())!=0)
                  return false;
          }
          return true;
      }
  }
  private boolean checkUsername(String userName)throws IOException,ClassNotFoundException
  {
      if(this.getUserName().equals(userName))
          return true;
      else
      {
          for(int i=0;i<Admin.creatAdminObject().getPersonsListAL().size();i++)
          {
              if(Admin.creatAdminObject().getPersonsListAL().get(i).getUserName().equals(userName) && Admin.creatAdminObject().getPersonsListAL().get(i).getUserName().compareTo(this.getUserName())!=0)
                  return false;
          }
          return true;
      }
  }
  private boolean checkPassword(String passWord)throws IOException,ClassNotFoundException
  {
      if(this.getPassWord().equals(passWord))
          return true;
      else
      {
          for(int i=0;i<Admin.creatAdminObject().getRequestsOfclerks().size();i++)
          {
              if(Admin.creatAdminObject().getPersonsListAL().get(i).getPassWord().equals(passWord) && Admin.creatAdminObject().getPersonsListAL().get(i).getPassWord().compareTo(this.getPassWord())!=0)
                  return false;
          }
          return true;
      }
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
  void changeInformation()throws IOException,ClassNotFoundException
  {
      CheckMyExceptions checkMyExceptions=new CheckMyExceptions();
      Scanner sc=new Scanner(System.in);
      String changeInformationCommand;
      String uerName=this.userName;
      Person person=Person.findPerson(userName,passWord);
      while (true)
      {
          System.out.printf("enter (name)or(last name)or(username)or(password)or(email)or(phone number)or(leave): ");
          changeInformationCommand=sc.nextLine();
          if(changeInformationCommand.equals("leave"))
          {
              this.editPropertiesFile(uerName);
              break;
          }
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
    private static void writeClerksAndBuyersInArrayLists()throws IOException,ClassNotFoundException
    {
        Clerk.getClerkListAl().clear();
        Buyer.getBuyersListAl().clear();
        File buyersList=new File("saved data\\users\\buyers\\list of buyers.txt");
        FileInputStream fileInputStreamForBuyersLists=new FileInputStream(buyersList);
        Buyer buyer;
        while (true)
        {
            try(ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStreamForBuyersLists))
            {
                buyer=(Buyer)objectInputStream.readObject();
                if(buyer!=null)
                    Buyer.getBuyersListAl().add(buyer);

            }
            catch (Exception exception)
            {
                fileInputStreamForBuyersLists.close();
                break;
            }
        }
        File clerksList=new File("saved data\\users\\clerks\\list of clerks.txt");
        FileInputStream fileInputStreamForClerksList=new FileInputStream(clerksList);
        Clerk clerk;
        while (true)
        {
            try(ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStreamForClerksList))
            {
                clerk=(Clerk)objectInputStream.readObject();
                if(clerk!=null)
                    Clerk.getClerkListAl().add(clerk);
            }
            catch (Exception exception)
            {
                fileInputStreamForClerksList.close();
                break;
            }
        }
    }
    private void editPropertiesFile(String userName)throws IOException,ClassNotFoundException
    {
        StringBuilder exPersonFolderName;
        StringBuilder personFolderPath;
        if(this instanceof Clerk)
        {
            exPersonFolderName=new StringBuilder("clerk ");
            exPersonFolderName.append(userName);
            File exPersonFolder=new File("saved data\\users\\clerks",exPersonFolderName.toString());
            File exProperties=new File(exPersonFolder,"properties.txt");
            exProperties.delete();
            personFolderPath=new StringBuilder("clerk ");
            personFolderPath.append(this.getUserName());
            File personFolder=new File("saved data\\users\\clerks",personFolderPath.toString());
            exPersonFolder.renameTo(personFolder);
            ((Clerk)this).writePropertiesOfClerks(exPersonFolder);
        }
        else
        {
            exPersonFolderName=new StringBuilder("buyer ");
            exPersonFolderName.append(userName);
            File exPersonFolder=new File("saved data\\users\\buyers",exPersonFolderName.toString());
            File exProperties=new File(exPersonFolder,"properties.txt");
            exProperties.delete();
            personFolderPath=new StringBuilder("buyer ");
            personFolderPath.append(this.getUserName());
            File personFolder=new File("saved data\\users\\buyers",personFolderPath.toString());
            exPersonFolder.renameTo(personFolder);
            ((Buyer)this).writePropertiesOfBuyerOnFile(exProperties);
        }
        this.editClerksAndBuyersFile();
    }
   private void editClerksAndBuyersFile()throws IOException,ClassNotFoundException
    {
        if(this instanceof Clerk)
        {
            File listOfClerks = new File("saved data\\users\\clerks\\list of clerks.txt");
            listOfClerks.delete();
            listOfClerks.createNewFile();
            MyObjectOutPutStream.setFile(listOfClerks);
            MyObjectOutPutStream myObjectOutPutStream=new MyObjectOutPutStream(listOfClerks);
            for(int i=0;i<Clerk.getClerkListAl().size();i++)
                myObjectOutPutStream.writeObject(Clerk.getClerkListAl().get(i));
            myObjectOutPutStream.close();
        }
        else
        {
            File listOfBuyers = new File("saved data\\users\\clerks\\list of buyers.txt");
            listOfBuyers.delete();
            listOfBuyers.createNewFile();
            MyObjectOutPutStream.setFile(listOfBuyers);
            MyObjectOutPutStream myObjectOutPutStream=new MyObjectOutPutStream(listOfBuyers);
            for(int i=0;i<Clerk.getClerkListAl().size();i++)
                myObjectOutPutStream.writeObject(Clerk.getClerkListAl().get(i));
            myObjectOutPutStream.close();
        }
        this.editUsersFileForAdmin();
    }
    private void editUsersFileForAdmin()throws IOException,ClassNotFoundException
    {
        File listOfPersonsForAdmin=new File("saved data\\users\\admin\\users.txt");
        listOfPersonsForAdmin.delete();
        listOfPersonsForAdmin.createNewFile();
        MyObjectOutPutStream.setFile(listOfPersonsForAdmin);
        MyObjectOutPutStream myObjectOutPutStream=new MyObjectOutPutStream(listOfPersonsForAdmin);
        for(int i=0;i<Buyer.getBuyersListAl().size();i++)
            myObjectOutPutStream.writeObject(Buyer.getBuyersListAl().get(i));
        for (int i=0;i<Clerk.getClerkListAl().size();i++)
            myObjectOutPutStream.writeObject(Clerk.getClerkListAl().get(i));
        myObjectOutPutStream.close();
    }
}
