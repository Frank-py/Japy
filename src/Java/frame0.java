package Japy.src.Java;

import java.awt.*;
import javax.swing.*;

public class frame0 extends JFrame {

  // JFrame = a GUI window to add components to
  public static void main(String[] args) {
    int x = 1;
    while (x==1)
    {
      new frame0();
    }
  }

  frame0() {
    this.setTitle("Close me!!!");
    //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    this.setResizable(false);
    this.setSize(1920, 1080);
    this.setVisible(true);
    // this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    // this.toFront();
    this.setAlwaysOnTop(true);
    // this.setUndecorated(true);
    
    this.getContentPane().setBackground(new Color(0x123456));

  }
}