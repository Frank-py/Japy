package Java;

//package Japy.src.Java;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class messInterface {
    String[] user;
    String currentUser;
    int n = 0;
    Color fontcolor = new Color(0xD4D4D4);
    public static boolean logins = false;
    JButton[] userliste = new JButton[100];
    JLayeredPane back = new JLayeredPane();
    String recv = "500";
    Color color = new Color(27, 37, 43);
    // ImageIcon logo = new ImageIcon("src\\Java\\prof5.png");
    // ImageIcon plus = new ImageIcon("src\\Java\\plus.png");
    ImageIcon ba = new ImageIcon("src\\Java\\Backgroundy.png");
    ImageIcon logo = new ImageIcon("Java/prof5.png");
    ImageIcon plus = new ImageIcon("Java/plus.png");
    // ImageIcon ba = new ImageIcon("Java/Backgroundy.png");
    // Image img = ba.getImage();
    JPanel chat = new JPanel();
    JTextField in = new JTextField();
    JButton addUsers;
    JFrame frame;
    JTextField newUser;
    JPanel Users;
    public JLabel background;
    boolean createUser = false;
    public int heighttemp;
    public int widthtemp;

    messInterface() {

      /*   while (!logins) {
            logins = login.loggedin;
            System.out.print("");
        } */

        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(1366, 768);
        frame.setVisible(true);
         frame.setMinimumSize(new Dimension(370, 370));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Messenger");
        frame.setIconImage(logo.getImage());
        frame.getContentPane().setBackground(color);

        // frame.setContentPane(back);????????????
        heighttemp = frame.getHeight();
        widthtemp = frame.getWidth();

        // back.setSize(frame.getHeight(), frame.getWidth());
        // back.setBackground(Color.red);
        // back.setOpaque(true);
        in.setFont(new Font("Consolas", Font.PLAIN, 25));
        in.setForeground(Color.white);
        in.setCaretColor(Color.white);
        in.setBackground(color);

        // chat.setLayout(new BoxLayout(chat,BoxLayout.Y_AXIS));
        // chat.setOpaque(false);
        KeyListener enter = new KeyListener() {
            public void keyPressed(KeyEvent e) {

                if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
                    frame.dispose();
                }

                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    if (newUser.getText().length() > 1069 || newUser.getText().length() < 1) {
                        recv = "5";
                        newUser.setText("");
                    } else {
                        recv = sendrecv.proofuser(newUser.getText());
                        Users.remove(newUser);
                        // Users.setSize(addUsers.getWidth(), frame.getHeight());

                        frame.setVisible(true);
                    }
                }

            }

            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {
            }
        };

        ActionListener act = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addUsers) {
                    newUser = new JTextField();
                    addUsers.setEnabled(false);
                    newUser.setFont(new Font("Consolas", Font.PLAIN, 25));
                    newUser.setForeground(fontcolor);
                    newUser.setBackground(color);
                    newUser.setCaretColor(Color.white);

                    Users.add(newUser, 1);
                    newUser.requestFocusInWindow();
                    newUser.addKeyListener(enter);

                    frame.setVisible(true);

                }
                for (JButton o : userliste) {
                    if (e.getSource() == o) {
                        currentUser = o.getText();
                        newchat();
                    }

                }

                
            }
        };

        in.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_ENTER && in.getText().length() > 0) {
                    sendrecv.sendMes( new String[] { currentUser, in.getText() });
                    in.setText("");
                    JLabel label = new JLabel(sendrecv.getMes( currentUser));
                    label.setSize(100, 100);

                    chat.add(label);
                }
                ;
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyTyped(KeyEvent e) {
            }
        });
        Users = new JPanel();
        Users.setLayout(new GridLayout(11, 1));
        Users.setBackground(color);

        addUsers = new JButton("Add User");
        addUsers.setIcon(plus);
        addUsers.setFont(new Font("MV Boli", Font.BOLD, 20));
        addUsers.setForeground(fontcolor);
        addUsers.setBackground(new Color(47, 49, 54));
        addUsers.setFocusable(false);
        addUsers.addActionListener(act);

        Users.add(addUsers);
        Users.setPreferredSize(new Dimension(frame.getWidth() / 10 * 2, frame.getHeight()));
        frame.add(Users, BorderLayout.WEST);
        // Users.setSize(frame.getWidth()-100, frame.getHeight());
        // tUsers.setSize(frame.getWidth()/10*2, frame.getHeight());
        back.setPreferredSize(new Dimension(frame.getWidth() - Users.getWidth(), frame.getHeight()));
        chat.setLayout(new GridLayout(10, 1));
        chat.setBackground(Color.green);
        back.setBackground(Color.red);
        back.setOpaque(false);
        chat.setOpaque(true);
        background = new JLabel(ba);

        back.add(chat, Integer.valueOf(1));
        back.add(background, Integer.valueOf(0));
        frame.add(back, BorderLayout.EAST); // ??????
        frame.setVisible(true);}
       // reloadframe();
    
        
      /*   while (true) {
            if (frame.getHeight() != heighttemp || frame.getWidth() != widthtemp) {
               // reloadframe();
            }
            // String[] recieve = sendrecv.recv();
            System.out.print("");
            if (recv.equals("0")) {
                JOptionPane.showMessageDialog(null, "User not found!", "User not found!", JOptionPane.ERROR_MESSAGE);
                recv = "500";
                addUsers.setEnabled(true);
            } else if (recv.equals("1")) {
                recv = "500";
                userliste[n] = new JButton(user[0]);
                userliste[n].setFont(new Font("MV Boli", Font.PLAIN, 35));
                userliste[n].setBackground(new Color(47, 49, 54));q
                userliste[n].setForeground(new Color(0xFFFFFF));
                userliste[n].setFocusable(false);
                userliste[n].addActionListener(act);
                Users.add(userliste[n], 1);
                n++;
                addUsers.setEnabled(true);
                frame.setVisible(true);
            } else if (recv.equals("4")) {
                JOptionPane.showMessageDialog(null,
                        "An unknown exception occured please try again! \nEnsure your internet connection", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                recv = "500";
            } else if (recv.equals("5")) {
                JOptionPane.showMessageDialog(null,
                        "Invalid! Enter a valid Ussername", "OutOfBounds",
                        JOptionPane.ERROR_MESSAGE);
                recv = "500";
            }
        }
    }*/

    public void reloadframe() {
        heighttemp = frame.getHeight();
        widthtemp = frame.getWidth();
        // Users.add(addUsers);

        // Users.setSize(addUsers.getWidth(), frame.getHeight());

        back.setSize(new Dimension(frame.getWidth() - Users.getWidth(), frame.getHeight()));

        back.setPreferredSize(new Dimension(frame.getWidth() - Users.getWidth(), frame.getHeight()));
        chat.setSize(back.getWidth(), back.getHeight());
        background.setSize(frame.getWidth() - Users.getWidth(), frame.getHeight());
        Users.setPreferredSize(new Dimension(frame.getWidth() / 10 * 2, frame.getHeight()));

    }

    // main for testing not necessary
    public static void main(String[] args) {
       // sendrecv.socket();
        //new messInterface();
    }

    /*
     * JLabel background = new JLabel();
     * Image imgscale = img.getScaledInstance(back.getWidth(), back.getHeight(),
     * Image.SCALE_SMOOTH);
     * ImageIcon bascale = new ImageIcon(imgscale);
     * background.setIcon(bascale);
     * background.setSize(back.getWidth(), back.getHeight());
     * back.add(background, Integer.valueOf(0));
     * back.remove(background);
     * frame.setVisible(true);
     */

    void newchat() {
        String key = encry.getKey(currentUser);
        
        encry.decMes(sendrecv.getMes(currentUser),key);
        JLabel label = new JLabel();
        label.setSize(10, 10);

        chat.add(label);
        /*
         * 
         * if (lol) {
         * 
         * chat.setSize(back.getWidth(), back.getHeight());
         * chat.add(in);
         * lol = false;
         * }
         */
        in.setText("");

        // in.setSize(new Dimension(back.getWidth()-100, back.getHeight()-100));
        // in.setSize(new Dimension(back.getWidth(), back.getHeight()));

        chat.add(in);
        frame.setVisible(true);

    }
}