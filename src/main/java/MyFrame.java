import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class MyFrame extends JFrame {

    private static String tittle = "Imenik";
    private JComboBox jComboBox;
    private JPasswordField jPasswordField;
    private JButton jButton;
    private String[] username = {"buckomd", "krits"};



    public MyFrame() {
        super(tittle);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(400, 200, 400, 200);
        setLayout(new FlowLayout());

        initializationComponents();
        addComponents();
        addActionListener();
    }

    private void initializationComponents() {
        jComboBox = new JComboBox(username);
        jPasswordField = new JPasswordField(10);
        jButton = new JButton("Login");
    }

    private void addComponents() {
        add(jComboBox);
        add(jPasswordField);
        add(jButton);
    }


    private void addActionListener() {
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pass = String.valueOf(jPasswordField.getPassword());
                String user = jComboBox.getSelectedItem().toString();

                String server_ip = "127.0.0.1";
                String shema = "vezbaimenik";
                String url = "jdbc:mysql://" + server_ip + "/" + shema;




                try(Connection connection = DriverManager.getConnection(url,"root","Aleksandar.94")) {

                    System.out.println("Tu smo!");
                    String sql = "SELECT * FROM vezbaimenik.korisnik where kor_username LIKE '%" + user + "%'";

                    ResultSet rs = connection.createStatement().executeQuery(sql);

                    while (rs.next()){
                        String out = rs.getString("kor_password");

                        if(pass.equals(out))
                        System.out.println("Logovani ste!!!!");
                    }

                } catch (Exception ex) {
                }

                user = null;
            }

        });
    }


    public static void main(String[] args) {

        MyFrame myFrame = new MyFrame();
        myFrame.setVisible(true);


    }
}
