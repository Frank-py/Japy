package Java;

import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.awt.event.*;

public class login {
  public static String recv;
  public static void createGUI(Socket socket) {
    Color color = new Color(0x123456);
    ImageIcon pic = new ImageIcon("src\\Java\\prof.png");

    JFrame frame = new JFrame();
    frame.setSize(300, 400);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Messenger");
    frame.setResizable(false);
    frame.setIconImage(pic.getImage());
    frame.getContentPane().setBackground(color);

    JLabel laby = new JLabel("Hier Anmelden:");
    laby.setFont(new Font("MV Boli", Font.BOLD, 30));
    laby.setForeground(new Color(0xFFFFFF));

    JPanel title = new JPanel();
    title.setBackground(color);

    JPanel UserInput = new JPanel(new GridLayout(7, 1));
    UserInput.setBackground(color);

    JPanel button = new JPanel();
    button.setBackground(color);

    JPanel space = new JPanel();
    space.setSize(50, 50);
    space.setVisible(false);

    JTextField user = new JTextField("");
    JLabel UserTitle = new JLabel("Username: ");
    UserTitle.setFont(new Font("MV Boli", Font.BOLD, 20));
    UserTitle.setForeground(new Color(0x00FF00));

    JTextField pass = new JTextField("");
    JLabel PassTitle = new JLabel("Password: ");
    PassTitle.setFont(new Font("MV Boli", Font.BOLD, 20));
    PassTitle.setForeground(new Color(0x00FF00));

    JButton loginbu = new JButton("Login!");
    loginbu.setFont(new Font("MV Boli", Font.BOLD, 20));
    loginbu.setForeground(new Color(0x0000FF));
    loginbu.setFocusable(false);
    loginbu.setSize(200, 50);

    KeyListener g = new KeyListener() {
      public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
          String[] lol = {user.getText(), pass.getText()};
          recv = sendrecv.send(socket, "login", lol);
          System.out.println(recv);
          //loginbu.setEnabled(false);

        }
      }

      public void keyReleased(KeyEvent e) {
      }

      public void keyTyped(KeyEvent e) {
      }
    };

    ActionListener act = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginbu) {
          String[] lol = {user.getText(), pass.getText()};
          recv = sendrecv.send(socket, "login", lol);
          System.out.println(recv);
          //0 = registriert, 1 = eingeloggt, 2 = falsches Passwort
          //loginbu.setEnabled(false);
        } 
      }
    };
    loginbu.addActionListener(act);
    pass.addKeyListener(g);
    user.addKeyListener(g);
    title.add(laby);
    button.add(loginbu);
    UserInput.add(UserTitle);
    UserInput.add(user);
    UserInput.add(PassTitle);
    UserInput.add(pass);
    UserInput.add(space);
    UserInput.add(button);
    frame.add(title, BorderLayout.NORTH);
    frame.add(UserInput);
    frame.setVisible(true);

    while (true) {
      System.out.println(recv);
      if (Integer.parseInt(recv) == 0){
        return;
      }
      else if (Integer.parseInt(recv) == 1){ 
        return;
      }
    }
  }
}
