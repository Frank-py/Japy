package Java.examplecodes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;


public class button0 extends JFrame implements ActionListener {

        JButton button;
        JLabel label;
    
        button0(){
            
            ImageIcon icon = new ImageIcon("point.png");
            ImageIcon icon2 = new ImageIcon("face.png");
            
            label = new JLabel();
            label.setIcon(icon2);
            label.setBounds(150, 250, 150, 150);
            label.setVisible(false);
            
            button = new JButton();
            button.setBounds(100, 100, 250, 100);
            button.addActionListener(this);
            button.setText("I'm a button!");
            
            button.setFocusable(false);
            button.setIcon(icon);
            button.setHorizontalTextPosition(JButton.CENTER);
            button.setVerticalTextPosition(JButton.BOTTOM);
            button.setFont(new Font("Comic Sans", Font.BOLD,25));
            button.setIconTextGap(-15);
            button.setForeground(Color.cyan);
            button.setBackground(Color.lightGray);
            button.setBorder(BorderFactory.createEtchedBorder());
            
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(null);
            this.setSize(500,500);
            this.setVisible(true);
            this.add(button);
            this.add(label);
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==button) {
              
                button.setEnabled(false);
                label.setVisible(true);
            }	
        }

       
    }

