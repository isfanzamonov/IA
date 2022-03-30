import java.io.File;
import java.util.ArrayList;

public class Database {

   private String filename;
   private ArrayList<Integer> padding = new ArrayList<>();

   public Database(String filename) {
       this.filename = filename;
   }

   public ArrayList<Integer> getPadding() {
       String[] temp;
       temp = FileHandler.readFromFile(0).split(" ");
       padding.add(Integer.parseInt(temp[0].replaceAll(" ", "")));
       padding.add(Integer.parseInt(temp[1].replaceAll(" ", "")));
       padding.add(Integer.parseInt(temp[2].replaceAll(" ", "")));
       return padding;
   }

   public String getRecord(int rowNumber) {
       getPadding();
       if (rowNumber == 0) {
           return FileHandler.readFromFile(0);
       } else {
           int start = FileHandler.readFromFile(0).length();
           start = start + ((padding.get(0) + padding.get(1) + padding.get(2)) * (rowNumber - 1));
           return FileHandler.readFromFile(start + 1) ;
       }
   }

   public String getCasType(int rowNumber) {
       return getRecord(rowNumber).substring(0, 14);
   }

   public String getActivity(int rowNumber) {
       return getRecord(rowNumber).substring(14, 29);
   }

   //public String getHours(int rowNumber) {
   //  return getRecord(rowNumber).;
   //}

   public void appendRecord(ArrayList padding, ArrayList input, boolean append) {
       if (append == true) {
           String newString = "";
           for (int i = 0; i < padding.size(); i++) {
               newString = newString + input.get(i);
               for (int j = 0; j < (Integer) padding.get(i) - input.get(i).toString().length(); j++) { //have to convert from object to string to int, there has to be a quicker way
                   newString = newString + " ";
               }
           }
           System.out.println(newString);
           FileHandler.writeToFile(filename, newString, true);
       }
   }

   public void deleteRecord(int rowNumber) {
   }

}
