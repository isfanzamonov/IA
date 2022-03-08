import java.util.ArrayList;

public class Database {

    private String filename;
    private int rowWidth;

    public Database(String filename, int rowWidth) {
        this.filename = filename;
        this.rowWidth = rowWidth;
    }

    public ArrayList<Integer> getPadding() {
        String[] temp;
        ArrayList<Integer> padding = new ArrayList<>();
        temp = FileHandler.readFromFile(0).split(" ");
        padding.add(Integer.parseInt(temp[0].replaceAll(" ", "")));
        padding.add(Integer.parseInt(temp[1].replaceAll(" ", "")));
        padding.add(Integer.parseInt(temp[2].replaceAll(" ", "")));
        return padding;
    }

    public String getRecord(int rowNumber) {
        return FileHandler.readFromFile(rowNumber * (rowWidth+1));
    }

    public void appendRecord(ArrayList padding, int lineAmount) {
        //NEED TO MAKE CODE - Take in input, print, print padding, loop that, and also need to be able to adjust padding and lineamount as it appends
    }

    public void deleteRecord(int rowNumber) {
    }

}
