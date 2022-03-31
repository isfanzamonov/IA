import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in); //scanner for taking in user inputs
        System.out.println("Does File Exist?");
        System.out.println("Type 1 if yes and 2 if no"); //replace with buttons when gui created
        int input = userInput.nextInt();

        if (input == 2) { //if file doesn't exist
            FileHandler.FileCreation(); //create file
        } //need to make error avoiding loop for when file already exists

        Database db = new Database("FileWriterFile.txt"); //possible to remove, db -> Database, and change away from static
        input = 1; //preparing for while loop that repeats for more inputs
        int counter = 0;
        boolean displayed = false;

        while ( input != 0) {

            //print options, 1 and 3 depend on stage of program
            if (counter == 0) {
                System.out.println("1. Input manually"); //first time inputting
            } else {
                System.out.println("1. Input more manually"); //more inputs has different message
            }
            System.out.println("2. Display");
            if (displayed == true) System.out.println("3. End"); //after displaying first time, if user doesn't want any more changes they can end
            input = userInput.nextInt();

            if ( input == 1) {

                db.appendRecord(inputCAS(), true); //calls function that takes in input and appends to file
                counter++; //counter goes past 0, changing the option name next time its printed

            } else if (input == 2) {

                for (int i = 1; i < FileHandler.linesInFile(); i++) { //starts at 1 because line 0 is padding info not record
                    System.out.println(db.getRecord(i)); //gets all records in database
                }

                System.out.println(db.getCasType(1)); //not using this for now, will use this to print only one specific CAS type later
                System.out.println(db.getActivity(2)); //not using this for now, will use later
                System.out.println(db.getHours(3)); //not using this for now, will use later
                displayed = true;

            } else if (input == 3) { //if user chooses to end
                input = 0; //stops repeating this set of options
            }

        }

    }

    public static ArrayList inputCAS() { //allows reader to input CAS details

        ArrayList<String> appendInput = new ArrayList<>();
        Scanner userInput = new Scanner(System.in); //scanner for taking in user inputs
        System.out.println("Input CAS Type");
        appendInput.add(userInput.nextLine());
        System.out.println("Input Activity");
        appendInput.add(userInput.nextLine());
        System.out.println("Input Hours");
        appendInput.add(userInput.nextLine());
        return appendInput; //returns the data to be appended to text file

    }

}
