package Java;

//package Japy.src.Java;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class messInterface {
     String[] user;
     int n = 0;
     JButton[] userliste = new JButton[100];
     JLayeredPane back = new JLayeredPane();
     String recv = "500";
     Color color = new Color(27, 37, 43);
     ImageIcon logo = new ImageIcon("src\\Java\\prof5.png");
     ImageIcon plus = new ImageIcon("src\\Java\\plus.png");
     ImageIcon ba = new ImageIcon("src\\Java\\Background.png");
    //  ImageIcon pic = new ImageIcon("Java/prof5.png");
    //  ImageIcon plus = new ImageIcon("Java/plus.png");
    //  ImageIcon ba = new ImageIcon("Java/Background.png");
     Image img = ba.getImage();
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

    messInterface(int log){
        
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(1366, 768);
        frame.setMinimumSize(new Dimension(370, 370));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Messenger");
        frame.setIconImage(logo.getImage());
        frame.getContentPane().setBackground(color);


        //frame.setContentPane(back);????????????
        heighttemp = frame.getHeight();
        widthtemp = frame.getWidth();

        //back.setSize(frame.getHeight(), frame.getWidth());
        //back.setBackground(Color.red);
       // back.setOpaque(true);
        in.setFont(new Font("Consolas", Font.PLAIN, 25));
        in.setForeground(Color.white);
        in.setCaretColor(Color.white);
        in.setBackground(color);
        
        // chat.setLayout(new BoxLayout(chat,BoxLayout.Y_AXIS));
        // chat.setOpaque(false);
        KeyListener enter = new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    if (newUser.getText().length() > 20) {
                        recv = "5";
                        newUser.setText("");
                    } else {
                        String[] userf = { newUser.getText() };
                        user = userf;
                        recv = sendrecv.send("proofuser", user);
                        Users.remove(newUser);
                        frame.add(Users, BorderLayout.WEST);
                        frame.setVisible(true);
                    }
                }
            }
            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
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
                    frame.setVisible(true);
                    newUser.addKeyListener(enter);

                } else if (newUser.getText().length() > 20) {
                    recv = "4";
                }
                for (JButton o : userliste) {
                    if (e.getSource() == o) {
                        newchat();
                    }

                }
            }
        };

        

        Users = new JPanel();
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
        chat.setSize( frame.getWidth(),frame.getHeight());
        back.setPreferredSize(new Dimension(frame.getWidth(),frame.getHeight()));

        chat.setLayout(new BorderLayout());
        chat.setBackground(Color.green);
        back.setBackground(Color.red);
        back.setOpaque(false);

        chat.setOpaque(false);
        background = new JLabel(ba);
        background.setSize( frame.getWidth(),frame.getHeight());
        

       
       // back.setSize(1000,1000);
     //   c.gridx = 1;
     
        back.add(chat, Integer.valueOf(0));
        back.add(background, Integer.valueOf(1));
       frame.add(back,BorderLayout.EAST); //??????
        // frame.add(chat, BorderLayout.CENTER);

       // frame.add(back, c);
        frame.setVisible(true);
        while (true) {
            if (frame.getHeight() != heighttemp || frame.getWidth() != widthtemp) {
               reloadframe();
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
                userliste[n].setBackground(new Color(47, 49, 54));
                userliste[n].setForeground(new Color(0xFFFFFF));
                userliste[n].setFocusable(false);
                userliste[n].addActionListener(act);
                Users.add(userliste[n]);
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
                        "Username too long please try again!", "OutOfBounds",
                        JOptionPane.ERROR_MESSAGE);
                recv = "500";
            }
        }
    }
    public  void reloadframe() {
        heighttemp = frame.getHeight();
        widthtemp = frame.getWidth();
        chat.setSize( frame.getWidth(),frame.getHeight());
        back.setPreferredSize(new Dimension(frame.getWidth(),frame.getHeight()));
        background.setSize( frame.getWidth(),frame.getHeight());
    }
    // main for testing not necessary
    public static void main(String[] args) {
        sendrecv.socket();
        new messInterface(0);
    }

     
        

        /*JLabel background = new JLabel();
        Image imgscale = img.getScaledInstance(back.getWidth(), back.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon bascale = new ImageIcon(imgscale);
        background.setIcon(bascale);
        background.setSize(back.getWidth(), back.getHeight());
        back.add(background, Integer.valueOf(0));
        back.remove(background);
        frame.setVisible(true);*/
    

     void newchat() {
/* 
        if (lol) {
            in.setSize(50, 50);
            chat.setSize(back.getWidth(), back.getHeight());
            chat.add(in);
            lol = false;
        } */
        in.setText("");
        frame.setVisible(true);
    }
}