import java.awt.*;
import javax.swing.*;

public class frame0 extends JFrame{


  // JFrame = a GUI window to add components to
  
  frame0() throws InterruptedException {
    ImageIcon image = new ImageIcon("prof.png");
    this.setIconImage(image.getImage());
  this.setTitle("Close me!!!");
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
  this.setResizable(true); 
  this.setSize(1920,1080); 
  this.setVisible(true); 
  // this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
  //this.toFront();
  this.setAlwaysOnTop(false);
 // this.setUndecorated(true);
  this.getContentPane().setBackground(new Color(0x123456));
    
 }
}