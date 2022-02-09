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
    public static Color color = new Color(0x123456);
    // public static ImageIcon pic = new ImageIcon("Japy\\src\\Java\\prof.png");
    // public static ImageIcon plus = new ImageIcon("Japy\\src\\Java\\plus.png");
    public static ImageIcon pic = new ImageIcon("Japy\\src\\Java\\prof.png");
    public static ImageIcon plus = new ImageIcon("Japy\\src\\Java\\plus.png");
    public static JButton addUsers;
    public static JFrame frame;
    public static JTextField newUser = new JTextField();
    public static JPanel Users;
    public static boolean createUser = false;

    public static ActionListener act = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addUsers) {
                createUser = true;
            }
        }
    };

    // public static

    // main for testing not necessary
    public static void main(String[] args) {
        try {
            so = new Socket("localhost", 6000);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        createGUI(0, so);
    }

    public static void createGUI(int log, Socket socket) {
        KeyListener enter = new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    System.out.println(newUser.getText());
                    // Users.remove(newUser);

                    try {
                        String[] userf = { newUser.getText() };
                        user = userf;
                        recv = sendrecv.send(socket, "proofuser", user);
                    } catch (Exception y) {
                        System.out.println(y);
                    }
                    Users.remove(newUser);
                    frame.add(Users, BorderLayout.WEST);
                    addUsers.setEnabled(true);
                    frame.setVisible(true);
                }
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyTyped(KeyEvent e) {
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
        // Users.setLayout(new GridLayout(10,6));
        Users.setSize(1, 1);
        Users.setBackground(color);

        JPanel space = new JPanel();
        space.setForeground(new Color(0x0000FF));

        addUsers = new JButton("    Add User    ");
        addUsers.setIcon(plus);
        addUsers.setFont(new Font("MV Boli", Font.BOLD, 15));
        addUsers.setForeground(new Color(0x0000FF));
        addUsers.setFocusable(false);

        addUsers.addActionListener(act);
        Users.add(addUsers);
        frame.add(Users, BorderLayout.WEST);
        frame.add(space, BorderLayout.CENTER);
        frame.setVisible(true);

        while (true) {
            System.out.print("");
            if (recv == "0") {
                JOptionPane.showMessageDialog(null, "User not found!", "User not found!", JOptionPane.ERROR_MESSAGE);
            } else if (recv == "1") {
                userliste[n] = new JButton(user[0]);
                Users.add(userliste[n]);
                n = n + 1;
                System.out.print("hat funktioniert!");
            }

            if (createUser) {
                addUsers.setEnabled(false);

                newUser.setFont(new Font("Consolas", Font.PLAIN, 35));
                newUser.setForeground(new Color(0x00FF00));
                newUser.setBackground(color);
                newUser.setCaretColor(Color.white);
                newUser.setSize(10, 10);
                Users.add(newUser, 2, 1);
                frame.add(Users);
                frame.add(space, BorderLayout.CENTER);
                // frame.setVisible(false);
                frame.setVisible(true);
                // frame.repaint();
                newUser.addKeyListener(enter);
                createUser = false;

            }
        }

    }

}