package workWithFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Formatter;

public class MakeFiles
{
    public void createFirstDirectoriesAndFiles()throws IOException,ClassNotFoundException
    {
        File savedDataFolder=new File("saved data");
        savedDataFolder.mkdir();
        this.creatFolders(savedDataFolder);
        File users=new File(savedDataFolder,"users");
        users.mkdir();
        File admin=new File(users,"admin");
        admin.mkdir();
        File ckerks=new File(users,"clerks");
        ckerks.mkdir();
        File buyers=new File(users,"buyers");
        buyers.mkdir();
        File listOfPersons=new File(admin,"users.txt");
        listOfPersons.createNewFile();
        File listOfBuyers=new File(buyers,"list of buyers.txt");
        listOfBuyers.createNewFile();
        File listOfClerks=new File(ckerks,"list of clerks.txt");
        listOfClerks.createNewFile();
        File listOfRequests =new File(admin,"list of requests.txt");
        listOfRequests.createNewFile();
        File adminProperties=new File(admin,"properties.txt");
        adminProperties.createNewFile();
        FileOutputStream fileOutputStream=new FileOutputStream(adminProperties,true);
        Formatter formatter=new Formatter(fileOutputStream);
        formatter.format("username:%s\npassWord:%s\n","admin","admin");
        formatter.close();
        fileOutputStream.close();
    }
    private void creatFolders(File file)throws IOException,ClassNotFoundException
    {
        File categories=new File(file,"categories");
        categories.mkdir();
        File garments=new File(categories,"garments");
        garments.mkdir();
        File listOfGarments=new File(garments,"list of garments.txt");
        listOfGarments.createNewFile();
        this.createGarmentFolders(garments);
        File homeAppliances=new File(categories,"home appliances");
        homeAppliances.mkdir();
        File listOfHomeAppliances=new File(homeAppliances,"list of home appliances.txt");
        listOfHomeAppliances.createNewFile();
        this.createHomeAppliancesFolders(homeAppliances);
        File Digitals=new File(categories,"Digitals");
        File listOfDigitals=new File(Digitals,"list of digitals.txt");
       listOfDigitals.createNewFile();
        Digitals.mkdir();
        this.createDigitalFolders(Digitals);
    }
    private void createDigitalFolders(File file)throws IOException,ClassNotFoundException
    {
        File laptops=new File(file,"lap tops");
        laptops.mkdir();
        File mobiles=new File(file,"mobiles");
        mobiles.mkdir();
    }
    private void createGarmentFolders(File file)throws IOException,ClassNotFoundException
    {
        File dresses=new File(file,"dresses");
        dresses.mkdir();
        File shoes=new File(file,"shoes");
        shoes.mkdir();
    }
    private void createHomeAppliancesFolders(File file)throws IOException,ClassNotFoundException
    {
        File televisions=new File(file,"televisions");
        televisions.mkdir();
        File stoves=new File(file,"stoves");
        stoves.mkdir();
        File refrigerators=new File(file,"refrigerators");
        refrigerators.mkdir();
    }
}
