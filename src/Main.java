import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {

   public static void main(String[] args) {
       Scanner userInput = new Scanner(System.in); //scanner for taking in user inputs
       System.out.println("1. Input manually");
       System.out.println("2. Input from file");
       int num = userInput.nextInt();
       if (num == 2) {
           FileHandler fh = new FileHandler("FileWriterFile.txt");
           Database db = new Database("FileWriterFile.txt");
       } else {
           //NEED TO TRY/CATCH CREATION OF FILE BUT LIMIT THE PATH TO JUST THIS FOLDER - TO USE ON ANY COMPUTER
       }
       FileHandler fh = new FileHandler("FileWriterFile.txt");
       Database db = new Database("FileWriterFile.txt");
       System.out.println("1. Input more manually");
       System.out.println("2. Display");
       num = userInput.nextInt();
       if (num == 1) {
           ArrayList<String> appendInput = new ArrayList<>();
           appendInput.add("Creativity");
           appendInput.add("Museum");
           appendInput.add("3");

           db.appendRecord(db.getPadding(), appendInput, true);
       } else {
           System.out.println(db.getPadding());
           System.out.println(db.getRecord(1)); //NOT READING PROPERLY
           System.out.println(db.getRecord(2));
           System.out.println(db.getRecord(3));
           db.getCasType(1); //NOT WORKING BECAUSE GETRECORD IS BROKEN
           db.getActivity(2);
           db.getActivity(3);
       }

   }

}

