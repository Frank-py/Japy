package Java.examplecodes;

import java.awt.*;
import javax.swing.*;

public class gridback extends JFrame {
    public gridback() {
        // Set the layout manager
        getContentPane().setLayout(new GridBagLayout());

        // Create the components
        JLabel nameLabel = new JLabel("Username:");
        JTextField nameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        // Create the constraints
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        // Add the components to the container
        c.gridx = 0;
        c.gridy = 0;
        getContentPane().add(nameLabel, c);
        c.gridx = 1;
        getContentPane().add(nameField, c);
        c.gridx = 0;
        c.gridy = 1;
        getContentPane().add(passwordLabel, c);
        c.gridx = 1;
        getContentPane().add(passwordField, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        getContentPane().add(loginButton, c);

        // Set the frame properties
        setTitle("Login Form");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        gridback frame = new gridback();
        frame.setVisible(true);
    }
}