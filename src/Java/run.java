package Japy.src.Java;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.*;
import java.io.*;

public class run {
  // ArrayList<String> login = new ArrayList<>();
  public static String[] login = new String[3];
  public static Socket socket;


  public static void main(String[] args) {
    
    try{
      socket = new Socket("localhost", 5000);}
      catch(UnknownHostException e1)
      {
        e1.printStackTrace();
      }catch(
      IOException e2)
      {
        e2.printStackTrace();}
    ImageIcon pic = new ImageIcon(
        "C:\\Users\\valie\\OneDrive\\Desktop\\SwitchClips\\Programmieren\\Japy\\src\\Java\\prof.png");

    JLabel laby = new JLabel("Hier Anmelden:");
    laby.setFont(new Font("MV Boli", Font.BOLD, 30));
    laby.setForeground(new Color(0xFFFFFF));
    laby.setSize(100, 100);

    JFrame frame = new JFrame();
    frame.setSize(300, 500);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Messenger");
    frame.setResizable(false);
    frame.setIconImage(pic.getImage());
    frame.getContentPane().setBackground(new Color(0x123456));

    JPanel title = new JPanel();
    title.setSize(600, 600);

    JPanel test = new JPanel();
    // test.setSize(1000,1000);
    test.setBackground(new Color(0x123456));

    JPanel test2 = new JPanel(new GridLayout(10, 2));

    // test2.setSize(200,200);
    test2.setBackground(new Color(0x123456));

    // title.setBackground(Color.green);
    // title.setBounds(10, 10, 400, 50);
    title.setLayout(new BorderLayout());
    title.add(test, BorderLayout.CENTER);
    title.add(test2, BorderLayout.SOUTH);

    JTextField user = new JTextField("");
    JLabel tftitle = new JLabel("Username: ");
    tftitle.setFont(new Font("MV Boli", Font.BOLD, 20));
    tftitle.setForeground(new Color(0x00FF00));
    test2.add(tftitle);

    user.setPreferredSize(new Dimension(200, 50));
    test2.add(user);

    JTextField pass = new JTextField("");
    JLabel pw = new JLabel("Password: ");
    pw.setFont(new Font("MV Boli", Font.BOLD, 20));
    pw.setForeground(new Color(0x00FF00));

    pass.setPreferredSize(new Dimension(200, 50));
    test2.add(pw);
    test2.add(pass);
    
    login[0] = "login";
      KeyListener g = new KeyListener() {
        public void keyPressed(KeyEvent e) {
          if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            javaclient.send("login",socket);
            javaclient.send(user.getText(),socket);
            javaclient.send(pass.getText(),socket);
            
          }
        }

        public void keyReleased(KeyEvent e) {
        }

        public void keyTyped(KeyEvent e) {
        }
      };

    pass.addKeyListener(g);
    user.addKeyListener(g);
    test.add(laby);
    frame.add(test, BorderLayout.NORTH);
    frame.add(test2, BorderLayout.CENTER);
    frame.setVisible(true);

  }

}