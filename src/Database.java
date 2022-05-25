import java.util.ArrayList;

public class Database {

    private String filename; //creates holding variables for ease of use
    private ArrayList<Integer> padding = new ArrayList<>();


    public Database(String filename) {

        this.filename = filename;
        FileHandler fh = new FileHandler(filename);
        padding.add(0); padding.add(0); padding.add(0); //needs to have 3 elements to replace
    }

    public ArrayList<Integer> getPadding() { //gets padding data from line 0

        String[] temp; //holds data as spaces removes from line 0, leaving only the padding numbers
        temp = FileHandler.readFromFile(0).split(" "); //takes the separated padding numbers, includes spaces for now
        padding.set(0, Integer.parseInt(temp[0].replaceAll(" ", ""))); //each one has spaces removed and is converted to integers for arithmetic
        padding.set(1, Integer.parseInt(temp[1].replaceAll(" ", "")));
        padding.set(2, Integer.parseInt(temp[2].replaceAll(" ", "")));
        return padding; //returns an arraylist of the padding data

    }

    public String getRecord(int rowNumber) {

        getPadding();

        if (rowNumber == 0) { //if padding line requested

            return FileHandler.readFromFile(0); // returns first line

        } else { //if any other line

            int start = FileHandler.readFromFile(0).length(); //accounts for the padding line and moves to start of line 1
            start = start + ((padding.get(0) + padding.get(1) + padding.get(2)) * (rowNumber - 1)) + (rowNumber * 2); //adds up lengths of lines before desired record to move to the start of that line
            //only does this operation when rownumber > 0 to skip padding line, (rowNumber * 2) takes the end padding into account
            return FileHandler.readFromFile(start).replace(".", ""); //searches for the record and removes the fullstop at the end

        }

    }

    public String getCasType(int rowNumber) {
        return getRecord(rowNumber).substring(0, padding.get(0) - 1); //returns only first segment from the record, the cas type
    }

    public String getProject(int rowNumber) {
        return getRecord(rowNumber).substring(padding.get(0), padding.get(0) + padding.get(1) - 1); //returns only second segment from the record, the project name
    }

    public String getHours(int rowNumber) {
        return getRecord(rowNumber).substring(padding.get(0) + padding.get(1), padding.get(0) + padding.get(1) + padding.get(2) - 1); //returns last segment, the hours
    }

    public void appendRecord(ArrayList input, boolean append) {

        padding = getPadding();
        String newString = "";
        int paddingCalc;

        if (append == true) {

            for (int i = 0; i < padding.size(); i++) {

                newString = newString + input.get(i);

                if (i < 2) { //if CAS type or project name
                    paddingCalc = padding.get(i) - input.get(i).toString().length();
                } else { //if hours
                    paddingCalc = padding.get(i) - input.get(i).toString().length() - 1; //has 1 less space at the end to account for fullstop
                }

                for (int j = 0; j < paddingCalc; j++) {
                    newString = newString + " ";
                }

            }

            FileHandler.writeToFile(filename, newString + ".", true); //adds fullstop to avoid losing padding spaces at the end of lines when text file is saved

        }

    }

    public void deleteRecord(int rowNumber) { //will create at a later date
    }

}
