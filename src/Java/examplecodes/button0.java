package Java.examplecodes;
//package Java.examplecodes;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
            button.setFont(new Font("Comic Sans",Font.BOLD,25));
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
                System.out.println("poo");
                button.setEnabled(false);
                label.setVisible(true);
            }	
        }
    }

