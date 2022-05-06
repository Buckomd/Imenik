import javax.swing.*;
import java.awt.*;

public class AddWindow extends JFrame {

    private static String tittle = "Adding contact";
    private JLabel lName, lLastName, lMobile, lDateBirth;
    private JTextField tfName, tfLastName, tfMobile, tfDateBirth;
    private JButton btnSave;
    private int width = 400, height = 200;

    public AddWindow(){
        super(tittle);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5,2));
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        setBounds(centerPoint.x - height/2, centerPoint.y - width/2,width,height);

        initializationComponents();
        addComponents();

    }

    private void initializationComponents(){
        lName = new JLabel("Name:");
        lLastName = new JLabel("Last Name:");
        lMobile = new JLabel("Mobile:");
        lDateBirth = new JLabel("Birth Date");
        tfName = new JTextField(5);
        tfLastName = new JTextField(5);
        tfMobile = new JTextField(5);
        tfDateBirth = new JTextField(5);
        btnSave = new JButton("Save contacts.");
    }

    private void addComponents(){
        add(lName);
        add(tfName);
        add(lLastName);
        add(tfLastName);
        add(lMobile);
        add(tfMobile);
        add(lDateBirth);
        add(tfDateBirth);
        add(btnSave);
    }

}
