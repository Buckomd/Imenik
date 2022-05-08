import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SearchInfo extends JFrame {

    private Object[][] data;
    private String[] columns;
    private int width = 600, height = 200;
    private Connection connection = null;

    private JPanel btnPnl, topBtnPnl, bottombtnPnl;
    private JTable table;
    private JButton btnSelectAll, btnDelete, btnCancel, btnAdd;


    public SearchInfo(Object[][] data, String[] columns) {

        this.data = data;
        this.columns = columns;
        setLayout(new BorderLayout());

        createTable();
        initializationComponents();
        addComponents();
        addActionListener();
        setDefault();


    }

    private void setDefault() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        this.setBounds(centerPoint.x - height / 2, centerPoint.y, width, height);
        setTitle("Searched Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createTable() {
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model) {
            public Class getColumnClass(int column) {

                return getValueAt(0, column).getClass();
            }
        };
        JScrollPane scrollPane = new JScrollPane(table);
    }


    private void initializationComponents() {
        btnAdd = new JButton("Add new contact");
        btnDelete = new JButton("Delete contact");
        btnSelectAll = new JButton("Select All");
        btnCancel = new JButton("Cancel");
        btnPnl = new JPanel(new BorderLayout());
        topBtnPnl = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
    }


    private void addComponents() {
        topBtnPnl.add(btnSelectAll);
        bottombtnPnl.add(btnAdd);
        bottombtnPnl.add(btnDelete);
        bottombtnPnl.add(btnCancel);

        btnPnl.add(topBtnPnl, BorderLayout.NORTH);
        btnPnl.add(bottombtnPnl, BorderLayout.CENTER);

        add(table.getTableHeader(), BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        add(btnPnl, BorderLayout.SOUTH);
    }


    private void addActionListener() {

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddWindow addWindow = new AddWindow();
                addWindow.setVisible(true);
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String message = "Are you sure you want to delete?";
                String title = "Answer the question!";
                int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {

                    int col = 4;

                    for (int i = 0; i < table.getModel().getRowCount(); i++) {


                        if (table.getModel().getValueAt(i, col)!=null) {

                            String server_ip = "127.0.0.1";
                            String shema = "vezbaimenik";
                            String url = "jdbc:mysql://" + server_ip + "/" + shema;

                            String sqlGet = "SELECT imk_id from vezbaimenik.imenik WHERE imk_ime LIKE '%" + table.getModel().getValueAt(i, 0) + "%' AND " +
                                    " imk_prezime LIKE '%" + table.getModel().getValueAt(i, 1) + "%'";

                            try {
                                if (connection == null) {
                                    connection = DriverManager.getConnection(url, "root", "Aleksandar.94");
                                }
                                ResultSet rs = connection.createStatement().executeQuery(sqlGet);

                                while (rs.next()) {
                                    int id = rs.getInt("imk_id");

                                    Statement st = connection.createStatement();
                                    try {
                                        String sqlDelete = "DELETE from vezbaimenik.imenik where imk_id LIKE '%" + id + "%'";
                                        st.executeUpdate(sqlDelete);
                                        JOptionPane.showMessageDialog(null, "Successfully Deleted");
                                    } catch (SQLException sqlException) {
                                        System.out.println("SQL Exeption");
                                    }
                                }
                            } catch (Exception ex) {
                            }
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "BYE!");
                    System.exit(0);
                }

            }
        });

    }

}
