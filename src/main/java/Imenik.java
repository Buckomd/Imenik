import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

public class Imenik extends JFrame {

    private static String tittle = "Address book";
    private int width = 400, height = 150;
    private JLabel jLabel;
    private JTextField jTextField;
    private JButton btnSearch, btnDelete, btnAdd;
    private JPanel jPanel;
    private JOptionPane jOptionPane;
    private String[] columnNames = {"Name", "Last Name", "Mobile", "DateBirth"};

    public Imenik() {
        super(tittle);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        setBounds(centerPoint.x - height / 2, centerPoint.y - width / 2, width, height);
        setLayout(new BorderLayout());

        initializationComponents();
        addComponents();
        addActionListeners();

    }


    private void initializationComponents() {
        jLabel = new JLabel("Enter text for searching adress book!", SwingConstants.CENTER);
        jLabel.setFont(new Font("Serif", Font.BOLD, 20));
        jTextField = new JTextField(10);
        btnSearch = new JButton("Search");
        btnAdd = new JButton("Add contact");
        btnDelete = new JButton("Delete contact");
        jPanel = new JPanel(new FlowLayout());
    }

    private void addComponents() {
        add(jLabel, BorderLayout.NORTH);
        add(jTextField, BorderLayout.CENTER);
        add(jPanel, BorderLayout.SOUTH);
        jPanel.add(btnSearch);
        jPanel.add(btnAdd);
    }

    private void addActionListeners() {

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String server_ip = "127.0.0.1";
                String shema = "imenik";
                String url = "jdbc:mysql://" + server_ip + "/" + shema;
                String unos = jTextField.getText();
                String[] columns = {"Name", "Last Name", "Mobile", "Date Birth"};
                String data[][] = new String[8][4];

                String sql = "SELECT * FROM imenik.imenik_table WHERE imk_ime LIKE '%" + unos +
                        "%' OR imk_prezime LIKE '%" + unos + "%' OR imk_brojtelefona " +
                        "LIKE '%" + unos + "%' OR imk_datumrodjenja LIKE '%" + unos + "%'";

                try (Connection connection = DriverManager.getConnection(url, "root", "Aleksandar.94")) {

                    ResultSet rs = connection.createStatement().executeQuery(sql);

                    int i = 0;
                    while (rs.next()) {
                        String name = rs.getString("imk_ime");
                        String last_name = rs.getString("imk_prezime");
                        String mobile = rs.getString("imk_brojtelefona");
                        String date_Birth = rs.getString("imk_datumrodjenja");

                        data[i][0] = name + "";
                        data[i][1] = last_name;
                        data[i][2] = mobile;
                        data[i][3] = date_Birth;
                        i++;
                    }

                    Table table = new Table(data, columns);

                } catch (Exception ex) {
                }
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {





            }
        });

    }

    public static void main(String[] args) {

        Imenik imenik = new Imenik();
        imenik.setVisible(true);

    }

}
