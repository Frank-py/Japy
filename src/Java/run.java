package Japy.src.Java;

import javax.swing.*;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.net.*;
import java.awt.event.*;
import java.io.*;

public class run {
  public static Socket socket;
  

  public static void main(String[] args) {

    try {
      socket = new Socket("localhost", 5000);
    } catch (UnknownHostException e1) {
      e1.printStackTrace();
    } catch (IOException e2) {
      e2.printStackTrace();
    }
    ImageIcon pic = new ImageIcon(
        "C:\\Users\\valie\\OneDrive\\Desktop\\SwitchClips\\Programmieren\\Japy\\src\\Java\\prof.png");

    JFrame frame = new JFrame();
    frame.setSize(300, 400);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Messenger");
    frame.setResizable(false);
    frame.setIconImage(pic.getImage());
    frame.getContentPane().setBackground(new Color(0x123456));

    JLabel laby = new JLabel("Hier Anmelden:");
    laby.setFont(new Font("MV Boli", Font.BOLD, 30));
    laby.setForeground(new Color(0xFFFFFF));
    laby.setSize(100, 100);

    JPanel title = new JPanel();
    // title.setSize(1000,1000);
    title.setBackground(new Color(0x123456));

    
    JPanel UserInput = new JPanel(new GridLayout(7, 1));
    // UserInput.setSize(200,200);
    UserInput.setBackground(new Color(0x123456));

    JPanel button = new JPanel();
    button.setBackground(new Color(0x123456));

    
    JTextField user = new JTextField("");
    user.setPreferredSize(new Dimension(200, 50));
    JLabel UserTitle = new JLabel("Username: ");
    UserTitle.setFont(new Font("MV Boli", Font.BOLD, 20));
    UserTitle.setForeground(new Color(0x00FF00));
    
    
    
    JTextField pass = new JTextField("");
    JLabel PassTitle = new JLabel("Password: ");
    PassTitle.setFont(new Font("MV Boli", Font.BOLD, 20));
    PassTitle.setForeground(new Color(0x00FF00));
    
    pass.setPreferredSize(new Dimension(200, 50));
    
    
    KeyListener g = new KeyListener() {
      public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
          javaclient.send("login", socket);
          javaclient.send(user.getText(), socket);
          javaclient.send(pass.getText(), socket);
          
        }
      }
      
      public void keyReleased(KeyEvent e) {
      }
      
      public void keyTyped(KeyEvent e) {
      }
    };
    
        
    
    //JButton loginbu = new button0();
        JButton loginbu = new JButton("Login!");
        loginbu.setFont(new Font("MV Boli", Font.BOLD, 20));
        
        loginbu.setForeground(new Color(0x0000FF));
        loginbu.setFocusable(false);
        loginbu.setSize(200, 50);
    JPanel space = new JPanel();
    space.setBackground(new Color(0x123456));
    space.setSize(50,50);
    
    //loginbu.addActionListener(pressed);
    pass.addKeyListener(g);
    user.addKeyListener(g);
    title.add(laby);
    button.add(loginbu, BorderLayout.CENTER);
    UserInput.add(UserTitle);
    UserInput.add(user);
    UserInput.add(PassTitle);
    UserInput.add(pass);
    UserInput.add(space);
    UserInput.add(button);

    frame.add(title, BorderLayout.NORTH);
    frame.add(UserInput, BorderLayout.CENTER);
    frame.setVisible(true);
  }
  
}
