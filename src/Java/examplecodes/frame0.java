//package Japy.src.Java.examplecodes;
//package Ctest.examplecodes;
<<<<<<< HEAD
//package Java.examplecodes;
package Java.examplecodes;
=======
package Java.examplecodes;
//package Java.examplecodes;
>>>>>>> f64eded519e428f294062d55a424e6b66cef2602
import java.awt.*;
import javax.swing.*;

public class frame0 extends JFrame {
  // JFrame = a GUI window to add components to
  public static void main(String[] args) {
      new frame0();
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