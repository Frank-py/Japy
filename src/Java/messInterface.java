package Java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class messInterface {
    //String[] user;
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

        /*
         * while (!logins) {
         * logins = login.loggedin;
         * System.out.print("");
         * }
         */

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
                    } 
                    else {
                        recv = sendrecv.proofuser(newUser.getText());
                        if (recv.equals("0")) {
                            JOptionPane.showMessageDialog(null, "User not found!", "User not found!",
                                    JOptionPane.ERROR_MESSAGE);
                            recv = "500";
                        } 
                        else if (recv.equals("1")) {
                            recv = "500";
                            userliste[n] = new JButton(newUser.getText());
                            userliste[n].setFont(new Font("MV Boli", Font.PLAIN, 35));
                            userliste[n].setBackground(new Color(47, 49, 54));
                            userliste[n].setForeground(new Color(0xFFFFFF));
                            userliste[n].setFocusable(false);

                            userliste[n].addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    for (JButton o : userliste) {
                                        if (e.getSource() == o) {
                                            currentUser = o.getText();
                                            newchat();
                                        }
                                    }
                                }
                            });
                            Users.add(userliste[n], 1);
                            n++;
                            Users.remove(newUser);
                            addUsers.setEnabled(true);
                            frame.setVisible(true);
                        } 
                        else if (recv.equals("4")) {
                            JOptionPane.showMessageDialog(null,
                                    "An unknown exception occured please try again! \nEnsure your internet connection",
                                    "ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                            recv = "500";
                        } 
                        else if (recv.equals("5")) {
                            JOptionPane.showMessageDialog(null,
                                    "Invalid! Enter a valid Ussername", "OutOfBounds",
                                    JOptionPane.ERROR_MESSAGE);
                            recv = "500";
                        }
                        newUser.setText("");
                        frame.setVisible(true);
                    }
                }
            } 
            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        };

        FocusListener bla = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (e.getSource() == newUser) {
                    Users.remove(newUser);
                    addUsers.setEnabled(true);
                    frame.setVisible(true);
                }
            }
        };

        ActionListener act = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addUsers) {
                    newUser = new JTextField();
                    newUser.addFocusListener(bla);
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
            }
        };

        in.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_ENTER && in.getText().length() > 0) {
                    sendrecv.sendMes(new String[] { currentUser, in.getText() });
                    in.setText("");
                    JLabel label = new JLabel(sendrecv.getMes(currentUser));
                    label.setSize(100, 100);
                    chat.add(label);
                }
            }
            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
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
        Users.setPreferredSize(new Dimension(frame.getWidth() / 10 * 3, frame.getHeight()));
        frame.add(Users, BorderLayout.WEST);
        // Users.setSize(frame.getWidth()-100, frame.getHeight());
        // tUsers.setSize(frame.getWidth()/10*2, frame.getHeight());
        frame.setVisible(true);
        chat.setLayout(new GridLayout(10, 1));
        chat.setBackground(Color.green);
        
        back.setBackground(Color.red);
        chat.setOpaque(true);
        background = new JLabel(ba);
        back.add(background, Integer.valueOf(0));
        back.setPreferredSize(new Dimension(frame.getWidth() - Users.getWidth(), frame.getHeight()));
        frame.add(back, BorderLayout.EAST); // ??????
        frame.setVisible(true);
        chat.setSize(back.getWidth(),back.getHeight());
        back.add(chat, Integer.valueOf(1));
        frame.setVisible(true);
        // System.out.println(back.getWidth()+"rrr");
        // System.out.println(frame.getWidth());
    }
    // reloadframe();

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
        if (key.equals("0")) {
            JOptionPane.showMessageDialog(null,
                    "Invitation sent to user", "Good Luck",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (key.equals("1")) {
            JOptionPane.showMessageDialog(null,
                    "User has not accepted your invitation yet", "Ignored",
                    JOptionPane.WARNING_MESSAGE);
            return;
        } else {
             JOptionPane.showMessageDialog(null,
                    "Generating Key ...", "Working ...",
                    JOptionPane.INFORMATION_MESSAGE);
            String mes = sendrecv.getMes(currentUser);
            if (mes.length() != 0) {
                encry.decMes(mes, key);
            }
            JLabel label = new JLabel();
            label.setSize(10, 10);
            chat.add(label);
            in.setText("");
            chat.add(in);
            frame.setVisible(true);

        }

    }
}