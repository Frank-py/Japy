package Java;

//package  Japy.src.Java;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class login {
  static ImageIcon logo = new ImageIcon("Java/prof5.png");
  // ImageIcon pic = new ImageIcon("Japy\\src\\Java\\prof.png");
  // ImageIcon pic = new ImageIcon("/home/daniel/Projekt/src/Java/prof.png");
  static JFrame frame = new JFrame();
  static Color color = new Color(27, 37, 43);
  static JLabel laby = new JLabel("Hier Anmelden:");
  static JPanel title = new JPanel();
  static JPanel UserInput = new JPanel(new GridLayout(8, 1));
  static JPanel button = new JPanel();
  static JPanel space = new JPanel();
  static JTextField user = new JTextField("");
  static JLabel UserTitle = new JLabel("Username: ");
  static JPasswordField pass = new JPasswordField();
  static JLabel PassTitle = new JLabel("Password: ");
  static JButton loginbu = new JButton("Login!");
  static JCheckBox robo = new JCheckBox("I'm not a robot");
  static JCheckBox EULA = new JCheckBox("Accept the EULA and our AGB");
  static JCheckBox news = new JCheckBox("Subscribe to the Newsletter", true);

  /**
   * 
   */
  public static void main() {

    // createWindow();

  }

  public static void createWindow() {
    KeyListener g = new KeyListener() {
      public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
          // name = user.getText();
          if (String.valueOf(pass.getPassword()).equals("") | user.getText().equals("")) {
            enter("2");

          } else if (EULA.isSelected() == false | robo.isSelected() == false) {
            enter("3");
          } else if (user.getText().length() > 20 | String.valueOf(pass.getPassword()).length() > 50) {
            enter("4");
          } else {
            try {
              String[] UP = { user.getText(), String.valueOf(pass.getPassword()) };
              // name=user.getText();
              if (enter(sendrecv.login(UP)) == 1) {
                run.loggedin(UP);
              }

              // 0 = registriert, 1 = eingeloggt, 2 = falsches Passwort
            } catch (Exception f) {
              enter("5");
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
          if (String.valueOf(pass.getPassword()).equals("") | user.getText().equals("")) {
            enter("2");

          } else if (EULA.isSelected() == false | robo.isSelected() == false) {
            enter("3");
          } else if (user.getText().length() > 20 | String.valueOf(pass.getPassword()).length() > 50) {
            enter("4");
          } else {
            try {
              String[] UP = { user.getText(), String.valueOf(pass.getPassword()) };
              // name = user.getText();
              if (enter(sendrecv.login(UP)) == 1) {
                run.loggedin(UP);
              }
              // 0 = registriert, 1 = eingeloggt, 2 = falsches Passwort
            } catch (Exception f) {
              f.printStackTrace();
              enter("5");
            }
          }
        }
      }
    };
    frame.setSize(320, 440);
    // frame.setAlwaysOnTop(true);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Login");
    frame.setResizable(false);
    frame.setIconImage(logo.getImage());
    frame.getContentPane().setBackground(color);
    laby.setFont(new Font("MV Boli", Font.BOLD, 30));
    laby.setForeground(Color.white);
    title.setBackground(color);
    UserInput.setBackground(color);
    button.setBackground(color);
    space.setSize(50, 50);
    space.setVisible(false);
    UserTitle.setFont(new Font("MV Boli", Font.BOLD, 20));
    UserTitle.setForeground(Color.white);
    PassTitle.setFont(new Font("MV Boli", Font.BOLD, 20));
    PassTitle.setForeground(Color.white);
    loginbu.setFont(new Font("MV Boli", Font.BOLD, 14));
    loginbu.setForeground(Color.white);
    loginbu.setBackground(new Color(47, 49, 54));
    loginbu.setFocusable(false);
    loginbu.setSize(200, 40);
    robo.setFocusable(false);
    robo.setBackground(color);
    robo.setForeground(Color.white);
    EULA.setFocusable(false);
    EULA.setBackground(color);
    EULA.setForeground(Color.white);
    news.setFocusable(false);
    news.setBackground(color);
    news.setForeground(Color.white);

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
    frame.add(title, BorderLayout.NORTH);
    frame.add(UserInput, BorderLayout.CENTER);
    frame.setVisible(true);

    // public static String name;
    // public static boolean loggedin = false;

  }

  static int enter(String recv) {
    if (Integer.parseInt(recv) == 0) {
      frame.dispose();
      JOptionPane.showMessageDialog(null, "You have registered a new user", "NEW USER", JOptionPane.WARNING_MESSAGE);
      return 1;

    } else if (Integer.parseInt(recv) == 1) {
      frame.dispose();
      return 1;

    } else if (Integer.parseInt(recv) == 2) {
      EULA.setSelected(false);
      news.setSelected(true);
      robo.setSelected(false);

      JOptionPane.showMessageDialog(null, "No or Wrong Password or Username, try again!", "Invalid UserInput",
          JOptionPane.ERROR_MESSAGE);
      return 0;
    } else if (Integer.parseInt(recv) == 3) {
      JOptionPane.showMessageDialog(null, "Proof Humanity and Accept EULA", "Accept required Terms",
          JOptionPane.ERROR_MESSAGE);
      /*
       * JProgressBar lullol = new JProgressBar(0);
       * lullol.setBounds(0, 0, 300, 420);
       * lullol.setStringPainted(true);
       * UserInput.add(lullol);
       * loginbu.setEnabled(false);
       * while (counter <= 100) {
       * lullol.setValue(counter);
       * try {
       * Thread.sleep(100);
       * } catch (Exception i) {
       * 
       * }
       * counter++;
       * 
       * }
       * loginbu.setEnabled(true);
       */
      return 0;
    } else if (Integer.parseInt(recv) == 4) {
      EULA.setSelected(false);
      news.setSelected(true);
      robo.setSelected(false);
      pass.setText("");
      user.setText("");
      JOptionPane.showMessageDialog(null, "Username or Password is too long, try again!", "Invalid UserInput",
          JOptionPane.ERROR_MESSAGE);
      return 0;
    } else if (Integer.parseInt(recv) == 5) {
      JOptionPane.showMessageDialog(null,
          "An unknown exception occured please try again! \nEnsure your internet connection", "ERROR",
          JOptionPane.ERROR_MESSAGE);
      frame.dispose();
      return 0;
    } else {
      return 0;
    }

  }
}