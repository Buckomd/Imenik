import javax.swing.*;
import java.sql.Connection;

public class Test extends JFrame {

//    private int height = 300, width = 300;
//    private JButton btSearch;
//    private JTextField tfSearch;
//    private JTextArea taResult;
//    private Connection connection = null;
//
//    public Test() {
//        super("Imenik");
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        Point centerPoint = ge.getCenterPoint();
//        setBounds(centerPoint.x - height/2, centerPoint.y - width/2, height, width);
//
//        setLayout(new BorderLayout());
//        initializeComponents();
//        addComponents();
//        addListeners();
//    }
//
//    public void initializeComponents() {
//        btSearch = new JButton("Search");
//        tfSearch = new JTextField(10);
//        taResult = new JTextArea(10,10);
//    }
//    public void addComponents() {
//        add(tfSearch, BorderLayout.NORTH);
//        add(taResult, BorderLayout.CENTER);
//        add(btSearch, BorderLayout.SOUTH);
//
//        taResult.setEnabled(false);
//    }
//    public void addListeners() {
//
//
//
//        btSearch.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//
//
//                String serachStr = tfSearch.getText();
//                String server_ip = "127.0.0.1";
//                String shema = "g3_imenik";
//                String url = "jdbc:mysql://" + server_ip + "/" + shema;
//                try {
//                    if( connection == null ) {
//                        connection = DriverManager.getConnection(url, "root", "");
//                    }
//                    String sql = "SELECT * FROM imenik WHERE imk_ime LIKE '%" + serachStr +
//                            "%' OR imk_prezime LIKE '%" + serachStr + "%' OR imk_telefon " +
//                            "LIKE '%" + serachStr + "%'";
//                    ResultSet rs = connection.createStatement().executeQuery(sql);
//                    taResult.setText("");
//                    while(rs.next()) {
//                        String out = rs.getString("imk_ime") + " " +
//                                rs.getString("imk_prezime");
//                        taResult.setText(taResult.getText() + "\n" + out);
//                    }
//                } catch (SQLException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        });
//    }
//
//    public static void main(String[] args) {
//        Test t = new Test();
//        t.setVisible(true);
//    }
}
