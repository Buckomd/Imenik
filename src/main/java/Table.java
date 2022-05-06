import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Table {

   private String[][] data;
   private String[] columns;
   private int width = 600, height = 200;
   JButton btnAdd = new JButton("Add contact");


    public Table(String[][] data, String[] columns)
    {
        this.data = data;
        this.columns = columns;

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        JScrollPane pane = new JScrollPane(table);
        JFrame f = new JFrame("Informations about searched contacts");
        JPanel panel = new JPanel();
        panel.add(pane);
        panel.add(btnAdd);
        f.add(panel);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        f.setBounds(centerPoint.x - height/2, centerPoint.y - width/2,width,height);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        }
}
