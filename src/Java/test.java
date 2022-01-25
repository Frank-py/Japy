import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test extends JFrame implements ActionListener {
    static JTextField jTextField;
    static JLabel jLabel;
    static JButton jButton;
    public static String send = "daniel";
    public static String recv = "Valentin";

    public static void main(String[] args) {
        JFrame jFrame = new test();
        jFrame.setLayout(null);
        jFrame.setVisible(true);
    }

    public test() {
        jLabel = new JLabel();
        jLabel.setBounds(50, 150, 350, 40);

        jTextField = new JTextField();
        jTextField.setBounds(50, 50, 150, 20);

        jButton = new JButton("Submit");
        jButton.setBounds(50, 100, 100, 30);

        jButton.addActionListener(this);

        add(jLabel);
        add(jButton);
        add(jTextField);

        setSize(400, 400);
    

        jButton = new JButton("Submit");
        jButton.setBounds(50, 100, 100, 30);

        jButton.addActionListener(this);

        add(jButton);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!jTextField.getText().equals("")) {
            jLabel.setText(jTextField.getText());
            javaclient.send("insertmessage " + send + " " + recv + " " + jTextField.getText());
        } else {
            jLabel.setText("Schreiben Sie etwas in die messagebox!");
        }
    }
}