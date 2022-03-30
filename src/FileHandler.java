import java.io.*;
import java.util.ArrayList;

public class FileHandler {

   private static String fileName;

   public FileHandler(String FileName) {
       fileName = FileName;
       linesInFile();
   }

   public static int linesInFile() {
       int lineAmount = 0;
       try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
           while (br.readLine() != null) lineAmount++;
       } catch (IOException e) {
           e.printStackTrace();
       }
       return lineAmount;
   }

   public static String readFromFile(int start) {
       // read and print out the contents of a text file
       // using all the exception handling best practices
       try (RandomAccessFile rf = new RandomAccessFile(fileName, "rws")) {
           rf.seek(start);
           return rf.readLine();
       }
       catch (IOException e) {
           e.printStackTrace();
       }
       return null;
   }

   public static ArrayList ForLoopReadFromFile(String fileName, int lineAmount) {
       // read and print out the contents of a text file
       // using all the exception handling best practices
       ArrayList<String> readInLines = new ArrayList<>();
       int counter;
       try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
           String line = "";
           for (int i = 0; i < lineAmount; i++) {
               line = br.readLine();
               readInLines.add(line);
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
       return readInLines;
   }

   public static void writeToFile(String fileName, String input, boolean append) {
       try (PrintWriter pr = new PrintWriter(new FileWriter(fileName, append))) {
           pr.println(input);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   public static void compactFile(String fileName, ArrayList readInLines, boolean append) {
       String stringCompacter;
       System.out.println(readInLines.get(0));
       System.out.println(readInLines.get(1));
       System.out.println(readInLines.get(2));
       try (PrintWriter pr = new PrintWriter(new FileWriter(fileName, append))) {
           for (int i = 0; 0 < 2; i++) {
               stringCompacter = (readInLines.get(i)).toString();
               stringCompacter.replaceAll(" ", "");
               System.out.println(stringCompacter);
               pr.println(stringCompacter);
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   public void RandomFile(String fileName, int start)  {
       try (RandomAccessFile rf = new RandomAccessFile(fileName, "rws")) {
           rf.seek(start);
           char letter = (char)rf.read();
           System.out.println(letter);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

}


