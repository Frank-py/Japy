package Java;
//package  Japy.src.Java;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class login{
  int counter = 0;
  ImageIcon pic = new ImageIcon("Java/prof5.png");
  //ImageIcon pic = new ImageIcon("Japy\\src\\Java\\prof.png");
    //ImageIcon pic = new ImageIcon("/home/daniel/Projekt/src/Java/prof.png");
  JFrame frame = new JFrame();
  Color color = new Color(27,37,43);
  JLabel laby = new JLabel("Hier Anmelden:");
  JPanel title = new JPanel();
  JPanel UserInput = new JPanel(new GridLayout(8, 1));
  JPanel button = new JPanel();
  JPanel space = new JPanel();
  JTextField user = new JTextField("");
  JLabel UserTitle = new JLabel("Username: ");
  JPasswordField pass = new JPasswordField();
  JLabel PassTitle = new JLabel("Password: ");
  JButton loginbu = new JButton("Login!");
  JCheckBox robo = new JCheckBox("I'm not a robot");
  JCheckBox EULA = new JCheckBox("Accept the EULA and our AGB");
  JCheckBox news = new JCheckBox("Subscribe to the Newsletter", true);
  KeyListener g = new KeyListener() {
    public void keyPressed(KeyEvent e) {
       if (e.getKeyChar() == KeyEvent.VK_ENTER) {
        if (String.valueOf(pass.getPassword()).equals("") | user.getText().equals("")) {
          enter("2");
  
        } else if (EULA.isSelected() == false | robo.isSelected() == false) {
          enter("3");
        } else if (user.getText().length() > 20 | String.valueOf(pass.getPassword()).length() > 50){ 
          enter("4");
          } else {
          try {
            String[] lol = { user.getText(), String.valueOf(pass.getPassword()) };
            enter(sendrecv.send( "login", lol));
            return;
            // 0 = registriert, 1 = eingeloggt, 2 = falsches Passwort
          } catch (Exception f) {
            enter("5");
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
        if (String.valueOf(pass.getPassword()).equals("") | user.getText().equals("")) {
          enter("2");
  
        } else if (EULA.isSelected() == false | robo.isSelected() == false) {
          enter("3");
        } else if (user.getText().length() > 20 | String.valueOf(pass.getPassword()).length() > 50){ 
          enter("4");
          } else {
          try {
            String[] lol = { user.getText(), String.valueOf(pass.getPassword()) };
            enter(sendrecv.send( "login", lol));
            System.out.println("2");
            return;
            // 0 = registriert, 1 = eingeloggt, 2 = falsches Passwort
          } catch (Exception f) {
            enter("5");
          }
        }
      }
    }
  };
  login(){
    frame.setSize(320, 440);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Login");
    frame.setResizable(false);
    frame.setIconImage(pic.getImage());
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
    loginbu.setBackground(new Color(47,49,54));
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
    //new messInterface(0);

  }
  
void enter(String recv){
    if (Integer.parseInt(recv) == 0) {
      frame.dispose();
      JOptionPane.showMessageDialog(null, "You have registered a new user", "NEW USER", JOptionPane.WARNING_MESSAGE);
    //new messInterface(0);
      System.out.println("hallo");
      return;
     
    } else if (Integer.parseInt(recv) == 1) {
      frame.dispose();
      new messInterface(1);
      System.out.println("hallo");
      return;
    
    } else if (Integer.parseInt(recv) == 2) {
      EULA.setSelected(false);
      news.setSelected(true);
      robo.setSelected(false);

      JOptionPane.showMessageDialog(null, "No or Wrong Password or Username, try again!", "Invalid UserInput",
          JOptionPane.ERROR_MESSAGE);
      return ;
    } else if (Integer.parseInt(recv) == 3) {
          JOptionPane.showMessageDialog(null, "Proof Humanity and Accept EULA", "Accept required Terms",
          JOptionPane.ERROR_MESSAGE);
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
          return ;
    } else if (Integer.parseInt(recv) == 4){
          EULA.setSelected(false);
          news.setSelected(true);
          robo.setSelected(false);
          pass.setText("");
          user.setText("");
          JOptionPane.showMessageDialog(null, "Username or Password is too long, try again!", "Invalid UserInput", JOptionPane.ERROR_MESSAGE);
          return ;
      }
      else if (Integer.parseInt(recv) == 5) {
          JOptionPane.showMessageDialog(null, "An unknown exception occured please try again! \nEnsure your internet connection", "ERROR",
          JOptionPane.ERROR_MESSAGE);
          frame.dispose();
          return ;
    }
  }
}