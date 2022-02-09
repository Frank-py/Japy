package Japy.src.Java;

import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.awt.event.*;

public class login {
  public static String recv = "500";
  public static boolean accept = false;
  public static boolean h = true;

  public static int createGUI(Socket socket) {
    Color color = new Color(0x123456);
   // ImageIcon pic = new ImageIcon("Japy\\src\\Java\\prof.png");
    ImageIcon pic = new ImageIcon("src\\Java\\prof.png");

    JFrame frame = new JFrame();
    frame.setSize(300, 420);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Login");
    frame.setResizable(false);
    frame.setIconImage(pic.getImage());
    frame.getContentPane().setBackground(color);

    JLabel laby = new JLabel("Hier Anmelden:");
    laby.setFont(new Font("MV Boli", Font.BOLD, 30));
    laby.setForeground(new Color(0xFFFFFF));

    JPanel title = new JPanel();
    title.setBackground(color);

    JPanel UserInput = new JPanel(new GridLayout(9, 1));
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

    JTextField pass = new JTextField();
    JLabel PassTitle = new JLabel("Password: ");
    PassTitle.setFont(new Font("MV Boli", Font.BOLD, 20));
    PassTitle.setForeground(new Color(0x00FF00));

    JButton loginbu = new JButton("Login!");
    loginbu.setFont(new Font("MV Boli", Font.BOLD, 15));
    loginbu.setForeground(new Color(0x0000FF));
    loginbu.setFocusable(false);
    loginbu.setSize(200, 42);

    JCheckBox robo = new JCheckBox("I'm not a robot");
    robo.setFocusable(false);
    robo.setBackground(color);
    robo.setForeground(new Color(0x00FF00));

    JCheckBox EULA = new JCheckBox("Accept the EULA and our AGB and the collection of necessary data");
    EULA.setFocusable(false);
    EULA.setBackground(color);
    EULA.setForeground(new Color(0x00FF00));

    JCheckBox news = new JCheckBox("Subscribe to the Newsletter", true);
    news.setFocusable(false);
    news.setBackground(color);
    news.setForeground(new Color(0x00FF00));

    KeyListener g = new KeyListener() {
      public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {

          if (pass.getText().equals("") | user.getText().equals("")) {
            recv = "2";
          } else if (EULA.isSelected() == false | robo.isSelected() == false) {
            recv = "3";
          } else {
            try {
              String[] lol = { user.getText(), pass.getText() };
              recv = sendrecv.send(socket, "login", lol);
              // 0 = registriert, 1 = eingeloggt, 2 = falsches Passwort
            } catch (Exception f) {
              recv = "4";
            }
          }
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
          if (pass.getText().equals("") | user.getText().equals("")) {
            recv = "2";
          } else if (EULA.isSelected() == false | robo.isSelected() == false) {
            recv = "3";
          } else {
            try {
              String[] lol = { user.getText(), pass.getText() };
              recv = sendrecv.send(socket, "login", lol);
              // 0 = registriert, 1 = eingeloggt, 2 = falsches Passwort
            } catch (Exception f) {
              recv = "4";
            }
          }
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
    UserInput.add(robo);
    UserInput.add(EULA);
    UserInput.add(news);
    UserInput.add(button);
    // UserInput.add(space);
    frame.add(title, BorderLayout.NORTH);
    frame.add(UserInput, BorderLayout.CENTER);
    frame.setVisible(true);

    while (true) {
      System.out.print("");
      if (Integer.parseInt(recv) == 0) {
        frame.dispose();
        JOptionPane.showMessageDialog(null, "You have registered a new user", "NEW USER", JOptionPane.WARNING_MESSAGE);

        return 0;
      } else if (Integer.parseInt(recv) == 1) {
        frame.dispose();
        return 1;
      } else if (Integer.parseInt(recv) == 2) {
        JOptionPane.showMessageDialog(null, "No or Wrong Password or Username, try again!", "Invalid UserInput",
            JOptionPane.ERROR_MESSAGE);
        recv = "500";
      } else if (Integer.parseInt(recv) == 3) {
        JOptionPane.showMessageDialog(null, "Proof Humanity and Accept EULA", "Accept required Terms",
            JOptionPane.ERROR_MESSAGE);
        recv = "500";
      } else if (Integer.parseInt(recv) == 4) {
        JOptionPane.showMessageDialog(null,
            "An unknown exception occured please try again! \nEnsure your internet connection", "ERROR",
            JOptionPane.ERROR_MESSAGE);
        frame.dispose();
        return 4;
      }
    }
  }
}