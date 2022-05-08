import javax.swing.*;

public class Test2 {


    public static void main(String[] args) {
        String message = " fdafs";
        String title = "Dasd";
        int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "HELLO");
        } else {
            JOptionPane.showMessageDialog(null, "GOODBYE");
            System.exit(0);
        }
    }



}
