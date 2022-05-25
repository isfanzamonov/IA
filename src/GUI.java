import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JPanel {

    JButton inputButton;
    JButton viewButton;
    JButton closeButton;
    boolean firstInput;
    int actionListenerCounter = 0;
    Database db = new Database("FileWriterFile.txt");
    ArrayList<String> appendInput = new ArrayList<>(3); //array list used for inputting to file

    public GUI(int width, int height) {

        appendInput.add(""); appendInput.add(""); appendInput.add("");

        System.out.println("SEQUENCE: Password constructor");
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(238, 227, 255));
        this.setLayout(null);

        JLabel CAS = new JLabel("CAS");
        CAS.setBounds(10,0, 200,50);
        CAS.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 36));
        add(CAS);

        JLabel passwordLabel = new JLabel("Enter Password:");
        passwordLabel.setBounds(10,50, 100,25);
        add(passwordLabel);

        JTextArea passwordError = new JTextArea("Wrong Password\nTry Again");
        passwordError.setBounds(10,110, 100,50);
        passwordError.setBackground(new Color(238, 227, 255));
        passwordError.setFont(new Font("SansSerif", Font.BOLD, 12));

        char[] correctPassword = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
        JPasswordField passwordField = new JPasswordField(8);
        passwordField.setBounds(10,80, 100,25);
        add(passwordField);

        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] passwordInput = e.getActionCommand().toCharArray();
                int checker = 0;
                if (correctPassword.length == passwordInput.length) {

                    for (int i = 0; i < correctPassword.length; i++) {
                        if (correctPassword[i] == passwordInput[i]) {
                            checker++;
                        }
                    }

                    if (checker == 8) {
                        passwordLabel.setVisible(false);
                        passwordField.setVisible(false);
                        passwordError.setVisible(false);
                        options();
                        actionListenerVoid();
                    } else {
                        add(passwordError);
                        repaint();
                    }

                } else {
                    add(passwordError);
                    repaint();
                }
            }
        });

    }

    JLabel inputLabel = new JLabel("");

    public void options() { //move to options class later

        System.out.println("SEQUENCE: Options constructor");

        inputLabel.setBounds(200, 25, 300, 25);
        add(inputLabel);

        if (FileHandler.FileCreation() == 0) { //file already exists
            inputLabel.setText("Data imported from file, press View to display");
        } else { //file created
            inputLabel.setText("File has been made, input CAS experience:");
        }

        inputButton = new JButton("Input");
        inputButton.setBounds(10,50,100,25);
        inputButton.setBackground(new Color(219, 203, 242));
        firstInput = true;

        viewButton = new JButton("View");
        viewButton.setBounds(10,90,100,25);
        viewButton.setBackground(new Color(219, 203, 242));

        closeButton = new JButton("Close");
        closeButton.setBounds(10,130,100,25);
        closeButton.setBackground(new Color(219, 203, 242));

        add(inputButton);
        add(viewButton);
        add(closeButton);
        repaint();

    }

    JButton creativity = new JButton("Creativity");
    JButton activity = new JButton("Activity");
    JButton service = new JButton("Service");
    JLabel projectLabel = new JLabel("Project name:");
    JTextField projectName = new JTextField();
    JLabel hoursLabel = new JLabel("Hours:");
    JTextField hours = new JTextField();
    JLabel confirmation = new JLabel("CAS Recorded");

    public void inputCAS(ArrayList appendInput) { //allows reader to input CAS details

        System.out.println("SEQUENCE: Input constructor");

        creativity.setBounds(200, 50, 100, 25);
        creativity.setBackground(new Color(219, 203, 242));
        activity.setBounds(320, 50, 100, 25);
        activity.setBackground(new Color(219, 203, 242));
        service.setBounds(440, 50, 100, 25);
        service.setBackground(new Color(219, 203, 242));
        add(creativity);
        add(activity);
        add(service);
        repaint();

        projectLabel.setBounds(200, 100, 300, 25);
        add(projectLabel);
        projectName.setBounds(200, 125, 150, 25);
        add(projectName);

        projectName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appendInput.set(1, projectName.getText()); //add project name to the input arraylist
            }
        });

        hoursLabel.setBounds(200, 175, 300, 25);
        add(hoursLabel);
        hours.setBounds(200, 200, 150, 25);
        add(hours);
    }

    JTable table = new JTable();

    public void view() {

        System.out.println("SEQUENCE: View constructor");

        String[] columnNames = {"CAS Category", "Project", "Hours",}; //creates column names for the table
        Object[][] data = new Object[FileHandler.linesInFile() - 1][3]; //gets data from the text file to put in table

        for (int i = 1; i < FileHandler.linesInFile(); i++) { //starts at 1 because line 0 is padding info not record
            data[i-1][0] = db.getCasType(i); //for each line get CAS type
            data[i-1][1] = db.getProject(i); //and project name
            data[i-1][2] = db.getHours(i); //and hours
        }

        table.setModel(new DefaultTableModel(data, columnNames));
        table.setBounds(200,50,300, 300);
        table.setBackground(new Color(219, 203, 242));
        add(table);
        repaint();

    }

    public void actionListenerVoid() { //method to create and hold all necessary action listeners preventing new action listeners being created every time a method is ran
        // before, action listeners in input got created every time input was run, creating multiple of the same, causing multiple action listeners to fire when 1 action was done
        // for instance, if the user has switched to the input page 3 times, the action listener that sends data to the txt file would have been created 3 times, so it sent the data 3 times
        inputButton.addActionListener(new ActionListener() { //change code if 2nd time pressing input (input more vs just input)
            @Override
            public void actionPerformed(ActionEvent e) {
                inputLabel.setText("Input CAS experience:");
                inputLabel.setVisible(true);
                creativity.setVisible(true);
                activity.setVisible(true);
                service.setVisible(true);
                projectLabel.setVisible(true);
                projectName.setVisible(true); //find a way to empty this when input called
                hoursLabel.setVisible(true);
                hours.setVisible(true); //find a way to empty this when input called
                table.setVisible(false);
                repaint();
                inputCAS(appendInput);
            }
        });

        viewButton.addActionListener(new ActionListener() { //can move these 2 action listeners to the separate method
            @Override
            public void actionPerformed(ActionEvent e) {
                inputLabel.setVisible(false);
                creativity.setVisible(false);
                activity.setVisible(false);
                service.setVisible(false);
                projectLabel.setVisible(false);
                projectName.setVisible(false);
                hours.setVisible(false);
                hoursLabel.setVisible(false);
                confirmation.setVisible(false);
                table.setVisible(true);
                repaint();
                view();
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {System.exit(0);}
        });

        creativity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {appendInput.set(0, "Creativity");} //add CAS type to the input arraylist
        });

        activity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {appendInput.set(0, "Activity");} //add CAS type to the input arraylist
        });

        service.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {appendInput.set(0, "Service");} //add CAS type to the input arraylist
        });

        hours.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appendInput.set(2, hours.getText()); //add hours to the input arraylist
                db.appendRecord(appendInput, true); //sends arraylist to be put in text file
                confirmation.setBounds(200, 250, 300, 25); //text that confirms input successful
                confirmation.setVisible(true);
                add(confirmation);
                repaint();
            }
        });

    }

}
