import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MyTable extends JTable {

    private Object[][] data;
    private String[] columns;
    private int width = 700, height = 200;


    public MyTable(Object[][] data, String[] columns)
    {
        this.data = data;
        this.columns = columns;


        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model) {
            public Class getColumnClass(int column) {

                return getValueAt(0, column).getClass();
            }
        };
        JScrollPane scrollPane = new JScrollPane(table);

        setSize(600,400);
        setVisible(true);

    }
}
