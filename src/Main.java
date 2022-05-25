import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("SEQUENCE: Program started");
        JFrame frame = new JFrame("CAS Tracker");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUI Panel = new GUI(600, 400);
        frame.add(Panel);
        frame.pack();
        frame.setVisible(true);

    }

}
