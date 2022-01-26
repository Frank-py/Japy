package Java;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class button0 extends JButton implements ActionListener {
    JButton loginbu;

    button0() {
        loginbu = new JButton("Login!");
        loginbu.setFont(new Font("MV Boli", Font.BOLD, 20));
        loginbu.addActionListener(this);
        loginbu.setForeground(new Color(0x0000FF));
        loginbu.setFocusable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginbu) {
            System.out.println("Button pressed!");
            loginbu.setEnabled(false);
        }
    }
}
