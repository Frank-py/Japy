package Japy.src.Java.examplecodes;
//package Java.examplecodes;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;

public class panel0 extends JPanel {

    panel0() {
        // JPanel = a GUI component that functions as a container to hold other
        // components
        this.setBackground(Color.red);
        this.setBounds(100, 100, 100, 100);
        this.setLayout(new BorderLayout());
    }
}