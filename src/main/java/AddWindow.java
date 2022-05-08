import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddWindow extends JFrame {

    private static String tittle = "Adding contact";
    private JLabel lName, lLastName, lMobile, lAdress;
    private JTextField tfName, tfLastName, tfMobile, tfAdress;
    private JButton btnSave;
    private int width = 400, height = 200;
    private Connection connection = null;
    private JOptionPane jOptionPane;

    public AddWindow() {
        super(tittle);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        setBounds(centerPoint.x - height / 2, centerPoint.y - width / 2, width, height);

        initializationComponents();
        addComponents();
        addActionListener();

    }

    private void initializationComponents() {
        lName = new JLabel("Name:");
        lLastName = new JLabel("Last Name:");
        lMobile = new JLabel("Mobile:");
        lAdress = new JLabel("Adress");
        tfName = new JTextField(5);
        tfLastName = new JTextField(5);
        tfMobile = new JTextField(5);
        tfAdress = new JTextField(5);
        btnSave = new JButton("Save contacts.");
    }

    private void addComponents() {
        add(lName);
        add(tfName);
        add(lLastName);
        add(tfLastName);
        add(lMobile);
        add(tfMobile);
        add(lAdress);
        add(tfAdress);
        add(btnSave);
    }

    private void addActionListener() {
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String server_ip = "127.0.0.1";
                String shema = "vezbaimenik";
                String url = "jdbc:mysql://" + server_ip + "/" + shema;

                String ime = tfName.getText();
                String prezime = tfLastName.getText();
                String mobile = tfMobile.getText();
                String adress = tfAdress.getText();
                System.out.println(ime + prezime + mobile +adress);




                String sql = "INSERT INTO vezbaimenik.imenik (imk_ime, imk_prezime,imk_telefon,imk_adresa) VALUES ('" +ime+"','"+prezime+"',"+mobile+",'"+adress+"')";

                try {
                    if( connection == null ) {
                        connection = DriverManager.getConnection(url, "root", "Aleksandar.94");
                    }

                    Statement st = connection.createStatement();
                    st.executeUpdate(sql);

                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame,"Successfully Saved");
                    setVisible(false);

                } catch (Exception ex) {
                    System.out.println("Unos nije uspeo");
                }
            }

        });
    }
}

