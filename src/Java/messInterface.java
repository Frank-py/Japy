package Java;
//package Japy.src.Java;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.image.BufferedImage;

public class messInterface {
    public static String[] user;
    public static Socket so;
    public static int n = 0;
    public static JButton[] userliste = new JButton[100];
    public static JPanel[] chat = new JPanel[100];
    public static String recv = "500";
    public static Color color = new Color(0x123456);
    // public static ImageIcon pic = new ImageIcon("Japy\\src\\Java\\prof.png");
    // public static ImageIcon plus = new ImageIcon("Japy\\src\\Java\\plus.png");
    public static ImageIcon pic = new ImageIcon("Java/prof.png");
    public static ImageIcon plus = new ImageIcon("Java/plus.png");
    public static ImageIcon ba = new ImageIcon("Java/Background.png");
    public static Image img = ba.getImage();
    public static JLabel backgroundthingthatveryconvincingandeasytoremambernameiguesswhatdoyouthinkisveryconfusingandannoyingwhyyoudodosseriouslyplstopilikecookiesandimagesarebad =  new JLabel();

    public static JButton addUsers;
    public static JFrame frame;
    public static JTextField newUser;
    public static JPanel Users;
    public static boolean createUser = false;

    // public static
    // public static

    // main for testing not necessary
    public static void main(String[] args) {
        try {
            so =   new Socket("localhost", 6000);
        } catch (UnknownHostException e) {

            e.printStackTrace();
            recv = "4";
        } catch (IOException e) {

            e.printStackTrace();
        }
        createGUI(0, so);
    }
public static void scale(JPanel panel) {
    
     Image imgscale = img.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
     //   Image imgscale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

    ImageIcon bascale = new ImageIcon(imgscale);
        backgroundthingthatveryconvincingandeasytoremambernameiguesswhatdoyouthinkisveryconfusingandannoyingwhyyoudodosseriouslyplstopilikecookiesandimagesarebad.setIcon(bascale);
}

    public static void createGUI(int log, Socket socket) {
        
        JPanel space = new JPanel();
        // space.paint(ba);
        //space.drawImage();
    
        KeyListener enter = new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    // Users.remove(newUser);
                    // user.add(newUser.getText());
                    String[] userf = { newUser.getText() };
                    user = userf;
                    recv = sendrecv.send(socket, "proofuser", user);
                    Users.remove(newUser);
                    frame.add(Users, BorderLayout.WEST);
                    frame.setVisible(true);
                }
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyTyped(KeyEvent e) {
            }

        };

        ActionListener act = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addUsers) {
                    newUser = new JTextField();
                    addUsers.setEnabled(false);
                    newUser.setFont(new Font("Consolas", Font.PLAIN, 25));
                    newUser.setForeground(new Color(0x00FF00));
                    newUser.setBackground(color);
                    newUser.setCaretColor(Color.white);
                    Users.add(newUser, 5, 1);
                    frame.add(space, BorderLayout.CENTER);
                    frame.add(Users, BorderLayout.WEST);
                    frame.add(space, BorderLayout.CENTER);
                    // frame.setVisible(false);
                    frame.setVisible(true);
                    newUser.addKeyListener(enter);
                     scale(space);
        space.add(backgroundthingthatveryconvincingandeasytoremambernameiguesswhatdoyouthinkisveryconfusingandannoyingwhyyoudodosseriouslyplstopilikecookiesandimagesarebad);
                }
            }
        };

        frame = new JFrame();
        frame.setSize(1366, 768);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Messenger");
        frame.setIconImage(pic.getImage());
        frame.getContentPane().setBackground(color);

        Users = new JPanel();
        // Users.setLayout(new BoxLayout(Users, BoxLayout.Y_AXIS));
        Users.setLayout(new GridLayout(10, 1, 0, 0));
        Users.setBackground(color);

        addUsers = new JButton("Add User");
        addUsers.setIcon(plus);
        addUsers.setFont(new Font("MV Boli", Font.BOLD, 20));
        addUsers.setForeground(new Color(0xFFFFFF));
        addUsers.setBackground(new Color(47, 49, 54));

        addUsers.setFocusable(false);

        addUsers.addActionListener(act);
        Users.add(addUsers);
        frame.add(Users, BorderLayout.WEST);
        frame.add(space, BorderLayout.CENTER);
        frame.setVisible(true);

        while (true) {
            System.out.print("");
            if (Integer.parseInt(recv) == 0) {
                JOptionPane.showMessageDialog(null, "User not found!", "User not found!", JOptionPane.ERROR_MESSAGE);
                recv = "500";
                addUsers.setEnabled(true);
            } else if (Integer.parseInt(recv) == 1) {
                recv = "500";
                userliste[n] = new JButton(user[0]);
                userliste[n].setFont(new Font("MV Boli", Font.PLAIN, 35));
                userliste[n].setBackground(new Color(47, 49, 54));
                userliste[n].setForeground(new Color(0xFFFFFF));
                chat[n] = new JPanel();
                chat[n].setBackground(new Color(0x00ff00));

                Users.add(userliste[n]);

                n++;

                // JButton a = new JButton(user[0]);
                // a.setFont(new Font("MV Boli", Font.PLAIN, 35));
                // Users.add(a);
                addUsers.setEnabled(true);
                frame.setVisible(true);
            } else if (Integer.parseInt(recv) == 4) {
                JOptionPane.showMessageDialog(null,
                        "An unknown exception occured please try again! \nEnsure your internet connection", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                recv = "500";

            }
        }

    }

}
