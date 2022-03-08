import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in); //scanner for taking in user inputs
        System.out.println("1. Input manually");
        System.out.println("2. Input from file");
        Integer num = userInput.nextInt();
        if (num == 2) {
            FileHandler fh = new FileHandler("FileWriterFile.txt");
            ArrayList<String> readInLines = new ArrayList<>();
            int lineAmount;
            lineAmount = fh.linesInFile();
            Database db = new Database("FileWriterFile.txt", lineAmount);
            ArrayList<Integer> padding = new ArrayList<>();
            db.appendRecord(db.getPadding(), lineAmount);

        }
    }

}
