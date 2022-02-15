//package Java;
package Japy.src.Java;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class login {
  public static String recv = "500";
  public static boolean accept = false;
  public static boolean h = true;
  public static int counter = 0;

  public static int createGUI() {
    Color color = new Color(27,37,43);
    //ImageIcon pic = new ImageIcon("Japy\\src\\Java\\prof.png");
    //ImageIcon pic = new ImageIcon("/home/daniel/Projekt/src/Java/prof.png");
    ImageIcon pic = new ImageIcon("Java/prof5.png");
    JFrame frame = new JFrame();
    frame.setSize(320, 440);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Login");
    frame.setResizable(false);
    frame.setIconImage(pic.getImage());
    frame.getContentPane().setBackground(color);

    JLabel laby = new JLabel("Hier Anmelden:");
    laby.setFont(new Font("MV Boli", Font.BOLD, 30));
    laby.setForeground(Color.white);

    JPanel title = new JPanel();
    title.setBackground(color);

    JPanel UserInput = new JPanel(new GridLayout(8, 1));
    UserInput.setBackground(color);

    JPanel button = new JPanel();
    button.setBackground(color);

    JPanel space = new JPanel();
    space.setSize(50, 50);
    space.setVisible(false);

    JTextField user = new JTextField("");
    JLabel UserTitle = new JLabel("Username: ");
    UserTitle.setFont(new Font("MV Boli", Font.BOLD, 20));
    UserTitle.setForeground(Color.white);

    JPasswordField pass = new JPasswordField();
    JLabel PassTitle = new JLabel("Password: ");
    PassTitle.setFont(new Font("MV Boli", Font.BOLD, 20));
    PassTitle.setForeground(Color.white);

    JButton loginbu = new JButton("Login!");
    loginbu.setFont(new Font("MV Boli", Font.BOLD, 14));
    loginbu.setForeground(Color.white);
    loginbu.setBackground(new Color(47,49,54));
    loginbu.setFocusable(false);
    loginbu.setSize(200, 40);

    JCheckBox robo = new JCheckBox("I'm not a robot");
    robo.setFocusable(false);
    robo.setBackground(color);
    robo.setForeground(Color.white);

    JCheckBox EULA = new JCheckBox("Accept the EULA and our AGB");
    EULA.setFocusable(false);
    EULA.setBackground(color);
    EULA.setForeground(Color.white);

    JCheckBox news = new JCheckBox("Subscribe to the Newsletter", true);
    news.setFocusable(false);
    news.setBackground(color);
    news.setForeground(Color.white);

    KeyListener g = new KeyListener() {
      public void keyPressed(KeyEvent e) {
        
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
         
          if (String.valueOf(pass.getPassword()).equals("") | user.getText().equals("")) {
            recv = "2";
          } else if (EULA.isSelected() == false | robo.isSelected() == false) {
            recv = "3";
            /*JProgressBar lullol = new JProgressBar(0);
            lullol.setBounds(0, 0, 300, 420);
            lullol.setStringPainted(true);
            UserInput.add(lullol);
            loginbu.setEnabled(false);
            while (counter <= 100) {
              lullol.setValue(counter);
              try {
                Thread.sleep(100);
              } catch (Exception i) {
                System.out.println("sldöjkhkvhsjkhheshjdfsjljflsajfjsaf" + i);
              }
              counter++;
              System.out.println(counter);
            }
            loginbu.setEnabled(true);*/
          }
          else if (user.getText().length() > 20 | String.valueOf(pass.getPassword()).length() > 50){ 
              recv = "4";
            } else {
            try {
              String[] lol = { user.getText(), String.valueOf(pass.getPassword()) };
              recv = sendrecv.send( "login", lol);
              // 0 = registriert, 1 = eingeloggt, 2 = falsches Passwort
            } catch (Exception f) {
              recv = "5";
            }
          }
        }
      }
      public void keyReleased(KeyEvent e) {}
      public void keyTyped(KeyEvent e) {}
    };

    ActionListener act = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginbu) {
          if (String.valueOf(pass.getPassword()).equals("") | user.getText().equals("") ){
            recv = "2";
          } else if (EULA.isSelected() == false | robo.isSelected() == false) {
            recv = "3";
          } else if (user.getText().length() > 20 | String.valueOf(pass.getPassword()).length() > 50){ 
              recv = "4";
          } else {
            try {
              String[] lol = { user.getText(), String.valueOf(pass.getPassword()) };
              recv = sendrecv.send("login", lol);
              // 0 = registriert, 1 = eingeloggt, 2 = falsches Passwort
            } catch (Exception f) {
              recv = "5";
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
        EULA.setSelected(false);
        news.setSelected(true);
        robo.setSelected(false);

        JOptionPane.showMessageDialog(null, "No or Wrong Password or Username, try again!", "Invalid UserInput",
            JOptionPane.ERROR_MESSAGE);
        recv = "500";
      } else if (Integer.parseInt(recv) == 3) {
            JOptionPane.showMessageDialog(null, "Proof Humanity and Accept EULA", "Accept required Terms",
            JOptionPane.ERROR_MESSAGE);
            recv = "500";
      } else if (Integer.parseInt(recv) == 4){
          EULA.setSelected(false);
          news.setSelected(true);
          robo.setSelected(false);
          pass.setText("");
          user.setText("");
        JOptionPane.showMessageDialog(null, "Username or Password is too long, try again!", "Invalid UserInput",
            JOptionPane.ERROR_MESSAGE);
        recv = "500";
        }
        else if (Integer.parseInt(recv) == 5) {
        JOptionPane.showMessageDialog(null,
            "An unknown exception occured please try again! \nEnsure your internet connection", "ERROR",
            JOptionPane.ERROR_MESSAGE);
        frame.dispose();
        return 4;
      }
    }
  }
}