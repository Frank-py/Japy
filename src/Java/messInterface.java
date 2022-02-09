package Java;
//package Japy.src.Java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class messInterface {
    public static String[] user;
    public static Socket so;
    public static int n = 0;
    public static JButton[] userliste;
    public static String recv = "500";
    public static Color color = new Color(150, 0, 255);
    // public static ImageIcon pic = new ImageIcon("Japy\\src\\Java\\prof.png");
    // public static ImageIcon plus = new ImageIcon("Japy\\src\\Java\\plus.png");
    public static ImageIcon pic = new ImageIcon("Java/prof.png");
    public static ImageIcon plus = new ImageIcon("Java/plus.png");
    public static JButton addUsers;
    public static JFrame frame;
    public static JTextField newUser;
    public static JPanel Users;
    public static boolean createUser = false;

    //public static 
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

    public static void createGUI(int log, Socket socket) {
         JPanel space = new JPanel();
                space.setForeground(new Color(0x0000FF));
        KeyListener enter = new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    // Users.remove(newUser);
                  //  user.add(newUser.getText());
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
                frame.add(Users,BorderLayout.WEST);
                frame.add(space, BorderLayout.CENTER);
                // frame.setVisible(false);
                frame.setVisible(true);
                newUser.addKeyListener(enter);
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
        Users.setLayout(new BoxLayout(Users, BoxLayout.Y_AXIS));
        Users.setBackground(color);

        
        addUsers = new JButton("Add User");
        addUsers.setIcon(plus);
        addUsers.setFont(new Font("MV Boli", Font.BOLD, 20));
        addUsers.setForeground(new Color(0x0000FF));
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
                //userliste[n] = new JButton(user[0]);
                //Users.add(userliste[n]);
                JButton a = new JButton(user[0]);
                a.setFont(new Font("MV Boli", Font.PLAIN, 35));
                Users.add(a);
                addUsers.setEnabled(true);
                n = n + 1;
                frame.setVisible(true);
            } else if (Integer.parseInt(recv) == 4) {
                JOptionPane.showMessageDialog(null,
                        "An unknown exception occured please try again! \nEnsure your internet connection", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            
            } 
        }

    }

}
