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
    public static JButton[] userliste = new JButton[100];
    // public static JPanel[] chat = new JPanel[100];
    public static String recv = "500";
    public static Color color = new Color(27, 37, 43);
    //public static ImageIcon pic = new ImageIcon("Japy\\src\\Java\\prof.png");
    //public static ImageIcon plus = new ImageIcon("Japy\\src\\Java\\plus.png");
    public static ImageIcon pic = new ImageIcon("prof5.png");
    public static ImageIcon plus = new ImageIcon("plus.png");
    public static ImageIcon ba = new ImageIcon("Background.png");
   // public static ImageIcon ba = new ImageIcon("Japy\\src\\Java\\Background.png");
    public static Image img = ba.getImage();
    public static JLabel backgroundthingthatveryconvincingandeasytoremambernameiguesswhatdoyouthinkisveryconfusingandannoyingwhyyoudodosseriouslyplstopilikecookiesandimagesarebad = new JLabel();
    public static JPanel chat = new JPanel(new BorderLayout());
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

    public static void createGUI(int log, Socket socket) {
        KeyListener enter = new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    if (newUser.getText().length()>20) {
                        recv = "5";
                        newUser.setText("");
                    } else {
                    String[] userf = { newUser.getText() };
                    user = userf;
                    recv = sendrecv.send(socket, "proofuser", user);
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
                    frame.add(chat, BorderLayout.CENTER);
                    frame.add(Users, BorderLayout.WEST);
                    frame.add(chat, BorderLayout.CENTER);
                    // frame.setVisible(false);
                    frame.setVisible(true);
                    newUser.addKeyListener(enter);
                }

                for (JButton o : userliste) {
                    if (e.getSource() == o) {
                        newchat();
                    }
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
        frame.add(chat, BorderLayout.CENTER);
        frame.setVisible(true);

        while (true) {
            scale();
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
            } else if(recv.equals("5")){
                JOptionPane.showMessageDialog(null,
                        "Username too long please try again!", "OutOfBounds",
                        JOptionPane.ERROR_MESSAGE);
                        recv = "500";
            }
        }
    }

    public static void scale() {

        Image imgscale = img.getScaledInstance(chat.getWidth(), chat.getHeight(), Image.SCALE_SMOOTH);
        // Image imgscale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

        ImageIcon bascale = new ImageIcon(imgscale);
        backgroundthingthatveryconvincingandeasytoremambernameiguesswhatdoyouthinkisveryconfusingandannoyingwhyyoudodosseriouslyplstopilikecookiesandimagesarebad
                .setIcon(bascale);
        chat.add(
                backgroundthingthatveryconvincingandeasytoremambernameiguesswhatdoyouthinkisveryconfusingandannoyingwhyyoudodosseriouslyplstopilikecookiesandimagesarebad);
        frame.add(chat, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void newchat() {
       chat.removeAll();
        scale();
        JTextField in = new JTextField();
        //in.setDocument(new JTextFieldLimit(15));
        //in.setOpaque(false);
         in.setFont(new Font("Consolas", Font.PLAIN, 25));
                    in.setForeground(new Color(0x00FF00));
                    in.setBackground(color);
                    in.setCaretColor(Color.white);
        chat.add(in,BorderLayout.CENTER);
        frame.add(chat, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    // public class JTextFieldLimit extends PlainDocument {
    //     private int limit;

    //     JTextFieldLimit(int limit) {
    //         super();
    //         this.limit = limit;
    //     }

    //     public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
    //         if (str == null)
    //             return;

    //         if ((getLength() + str.length()) <= limit) {
    //             super.insertString(offset, str, attr);
    //         }
    //     }
   // }
}
