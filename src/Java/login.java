package Java;

//package  Japy.src.Java;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class login {
  static ImageIcon logo = new ImageIcon("Java/prof5.png");
  static JFrame frame = new JFrame();
  static Color color = new Color(27, 37, 43);
  static JLabel login_here = new JLabel("Login:");
  static JPanel title = new JPanel();
  static JPanel UserInput = new JPanel(new GridLayout(8, 1));
  static JPanel button = new JPanel();
  static JPanel space = new JPanel();
  static JTextField user = new JTextField("");
  static JLabel username = new JLabel("Username: ");
  static JPasswordField pass = new JPasswordField();
  static JLabel PassTitle = new JLabel("Password: ");
  static JButton login_button = new JButton("Login!");
  static JCheckBox reCaptcha = new JCheckBox("I'm not a robot", true);
  static JCheckBox EULA = new JCheckBox("Accept the EULA and our AGB", true);
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
          if (String.valueOf(pass.getPassword()).isEmpty() | user.getText().isEmpty()) {
            enter("2");

          } else if (!EULA.isSelected() | !reCaptcha.isSelected()) {
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

              // 0 = registered, 1 = eingeloggt, 2 = falsches Passwort
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
    ActionListener act = e -> {
      if (e.getSource() == login_button) {
        if (String.valueOf(pass.getPassword()).isEmpty() | user.getText().isEmpty()) {
          enter("2");

        } else if (!EULA.isSelected() | !reCaptcha.isSelected()) {
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
            // 0 = registered, 1 = eingeloggt, 2 = falsches Passwort
          } catch (Exception f) {
            f.printStackTrace();
            enter("5");
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
    login_here.setFont(new Font("MV Boli", Font.BOLD, 30));
    login_here.setForeground(Color.white);
    title.setBackground(color);
    UserInput.setBackground(color);
    button.setBackground(color);
    space.setSize(50, 50);
    space.setVisible(false);
    username.setFont(new Font("MV Boli", Font.BOLD, 20));
    username.setForeground(Color.white);
    PassTitle.setFont(new Font("MV Boli", Font.BOLD, 20));
    PassTitle.setForeground(Color.white);
    login_button.setFont(new Font("MV Boli", Font.BOLD, 14));
    login_button.setForeground(Color.white);
    login_button.setBackground(new Color(47, 49, 54));
    login_button.setFocusable(false);
    login_button.setSize(200, 40);
    reCaptcha.setFocusable(false);
    reCaptcha.setBackground(color);
    reCaptcha.setForeground(Color.white);
    EULA.setFocusable(false);
    EULA.setBackground(color);
    EULA.setForeground(Color.white);
    news.setFocusable(false);
    news.setBackground(color);
    news.setForeground(Color.white);

    login_button.addActionListener(act);
    pass.addKeyListener(g);
    user.addKeyListener(g);
    title.add(login_here);
    button.add(login_button);
    UserInput.add(username);
    UserInput.add(user);
    UserInput.add(PassTitle);
    UserInput.add(pass);
    UserInput.add(reCaptcha);
    UserInput.add(EULA);
    UserInput.add(news);
    UserInput.add(button);
    frame.add(title, BorderLayout.NORTH);
    frame.add(UserInput, BorderLayout.CENTER);
    frame.setVisible(true);

    // public static String name;
    // public static boolean logged_in = false;

  }

  static int enter(String receive) {
    if (Integer.parseInt(receive) == 0) {
      frame.dispose();
      JOptionPane.showMessageDialog(null, "You have registered a new user", "NEW USER", JOptionPane.WARNING_MESSAGE);
      return 1;

    } else if (Integer.parseInt(receive) == 1) {
      frame.dispose();
      return 1;

    } else if (Integer.parseInt(receive) == 2) {
      EULA.setSelected(false);
      news.setSelected(true);
      reCaptcha.setSelected(false);

      JOptionPane.showMessageDialog(null, "No or Wrong Password or Username, try again!", "Invalid UserInput",
          JOptionPane.ERROR_MESSAGE);
      return 0;
    } else if (Integer.parseInt(receive) == 3) {
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
    } else if (Integer.parseInt(receive) == 4) {
      EULA.setSelected(false);
      news.setSelected(true);
      reCaptcha.setSelected(false);
      pass.setText("");
      user.setText("");
      JOptionPane.showMessageDialog(null, "Username or Password is too long, try again!", "Invalid UserInput",
          JOptionPane.ERROR_MESSAGE);
      return 0;
    } else if (Integer.parseInt(receive) == 5) {
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