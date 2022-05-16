import com.mysql.cj.xdevapi.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Imenik extends JFrame {

    private static String tittle = "Address book";
    private int width = 400, height = 150;
    private JLabel jLabel;
    private JTextField jTextField;
    private JButton btnSearch;
    private JPanel jPanel;
    private String[] columnNames = {"Name", "Last Name", "Mobile", "DateBirth"};
    private Connection connection = null;
    private int brojac = 0;

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
        jTextField.setFont(new Font("Serif",Font.PLAIN,20));
        btnSearch = new JButton("Search");
        jPanel = new JPanel(new FlowLayout());
    }

    private void addComponents() {
        add(jLabel, BorderLayout.NORTH);
        add(jTextField, BorderLayout.CENTER);
        add(jPanel, BorderLayout.SOUTH);
        jPanel.add(btnSearch);
    }

    private void addActionListeners() {

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String server_ip = "127.0.0.1";
                String shema = "vezbaimenik";
                String url = "jdbc:mysql://" + server_ip + "/" + shema;
                String unos = jTextField.getText();
                String[] columns = {"Name", "Last Name", "Mobile", "Adress","Check"};
                Object data[][] = new Object[8][5];

                String sql = "SELECT * FROM vezbaimenik.imenik WHERE imk_ime LIKE '%" + unos +
                        "%' OR imk_prezime LIKE '%" + unos + "%' OR imk_telefon " +
                        "LIKE '%" + unos + "%' OR imk_adresa LIKE '%" + unos + "%'";

                try {
                    if( connection == null ) {
                        connection = DriverManager.getConnection(url, "root", "Aleksandar.94");
                    }

                    ResultSet rs = connection.createStatement().executeQuery(sql);

                    int i = 0;
                    while (rs.next()) {
                        String name = rs.getString("imk_ime");
                        String last_name = rs.getString("imk_prezime");
                        String mobile = rs.getString("imk_telefon");
                        String adress = rs.getString("imk_adresa");
                        Boolean bolean  = false;

                        data[i][0] = name + "";
                        data[i][1] = last_name;
                        data[i][2] = mobile;
                        data[i][3] = adress;
                        data[i][4] = bolean;
                        i++;
                        brojac++;
                    }

                    if(brojac == 0){
                        String message = "No contacts found!";
                        String title = "Information";
                        int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.CANCEL_OPTION);
                    } else {
                        SearchInfo searchInfo = new SearchInfo(data, columns);
                        setVisible(false);
                    }
                } catch (Exception ex) {
                }
            }
        });


    }

}
