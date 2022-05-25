import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.io.PrintWriter;

public class FileHandler {

    private static String fileName;

    public FileHandler(String FileName) {

        fileName = FileName;
        linesInFile();

    }

    public static int FileCreation() {

        File file = new File("FileWriterFile.txt"); //declares file name

        try {

            if (file.createNewFile()) { //if file created
                System.out.println("New Text File is created!");
                return 1;
            } else { //if unable to create (already exists)
                System.out.println("File already exists.");
                return 0;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return 2; //shouldn't return but necessary to have a return function here
        }

    }

    public static int linesInFile() {

        int lineAmount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (br.readLine() != null) lineAmount++; //if data in the line the counter increases, ends when no more data left
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lineAmount; //returns counter

    }

    public static String readFromFile(int start) {

        try (RandomAccessFile rf = new RandomAccessFile(fileName, "rws")) {
            rf.seek(start); //seeks the point given
            return rf.readLine(); //returns the line
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void writeToFile(String fileName, String input, boolean append) {

        try (PrintWriter pr = new PrintWriter(new FileWriter(fileName, append))) {
            pr.println(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
