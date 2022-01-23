//import javax.swing.*;
//import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class run2 {
    
    public static void main(String[] args) throws Exception
   {

       ImageIcon image = new ImageIcon("prof.png");

       // JFrame = a GUI window to add components to
       JFrame mainWin = new JFrame();
       mainWin.setIconImage(image.getImage());
       mainWin.setTitle("Close me!!!");
       mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       //mainWin.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
       mainWin.setResizable(true);
       mainWin.setSize(1920,1080);
       mainWin.setVisible(true);
       // mainWin.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
       //mainWin.toFront();
       //mainWin.setAlwaysOnTop(true);
       // mainWin.setUndecorated(true);
       mainWin.getContentPane().setBackground(new Color(0x123456));


        // JPanel = a GUI component that functions as a container to hold other components
       JPanel panel = new JPanel();
       panel.setBackground(Color.red);
       panel.setBounds(100, 100, 100, 100);
       panel.setLayout(new BorderLayout());
       

       // JLabel = a GUI display area for a string of text, an image, or both
       JLabel label = new JLabel();
       //label.setIcon(image);
       Border border = BorderFactory.createLineBorder(Color.yellow,3);
       label.setText("Messenger is loading"); //set text of label
       label.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT,CENTER, RIGHT of imageicon
       label.setVerticalTextPosition(JLabel.TOP); //set text TOP,CENTER, BOTTOM of imageicon
       label.setForeground(new Color(0x00FF00)); //set font color of text
       label.setFont(new Font("MV Boli",Font.PLAIN,10)); //set font of text
       //label.setIconTextGap(100); //set gap of text to image
       label.setBackground(Color.green); //set background color
       label.setOpaque(true); //display background color
       label.setBorder(border); //sets border of label (not image+text)
       label.setVerticalAlignment(JLabel.CENTER); //set vertical position of icon+text within label
       label.setHorizontalAlignment(JLabel.CENTER); //set horizontal position of icon+text within label
       //label.setBounds(100, 100, 1000, 1000); //set x,y position within frame as well as dimensions
       //frame.setLayout(null);


       panel.add(label);
       mainWin.add(panel);
       //mainWin.add(label);

        //mainWin.pack();
        //javaclient.send("hi");
   }
}
