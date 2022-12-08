package Java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.google.gson.Gson;

public class messtest {

    Color fontcolor;
    JLayeredPane back;
    Color colortheme;
    ImageIcon logo;
    JPanel chat;
    String recv;
    JTextField writeMessage;
    JButton addUsers;
    JFrame frame;
    JTextField newUser;
    JPanel Users;
    Font stdFont;
    KeyListener enter;
    JButton Useruser;
    ActionListener UserPressed;
    testuser me;

    // JLabel background;

    messtest(testuser me) {
        this.me = me;
        initialize();
        listen();

    }

    void initialize() {

        // colors
        colortheme = new Color(27, 37, 43);

        // initializing the frame
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(1366, 768);
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(370, 370));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Messenger");
        frame.setIconImage(logo.getImage());
        frame.getContentPane().setBackground(colortheme);

        // creating the JButton for Usernames
        Useruser = new JButton();
        Useruser.setFont(new Font("MV Boli", Font.PLAIN, 35));
        Useruser.setBackground(new Color(47, 49, 54));
        Useruser.setForeground(new Color(0xFFFFFF));
        Useruser.setFocusable(false);

        // creating the chat textfield
        writeMessage.setFont(new Font("Consolas", Font.PLAIN, 25));
        writeMessage.setForeground(Color.white);
        writeMessage.setCaretColor(Color.white);
        writeMessage.setBackground(colortheme);

        // creating the addUser JButton

    }

    void listen() {

        // checks if an added user is clicked
        UserPressed = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textuser = ((JButton) e.getSource()).getText();
                if (me.openchat(textuser)) {
                    // creates the chat interface visually
                    newchat();
                    // loads the messages to the chat if necessary
                    loadMes(me.getMes(textuser, 5));
                } else{
                    // displaying the error message when key isn´t ready
                    JOptionPane.showMessageDialog(null,
                            "User has not accepted your invitation yet", "Ignored",
                            JOptionPane.WARNING_MESSAGE);
                }

            }
        };

        // keylistener with a variety of functions
        enter = new KeyListener() {
            public void keyPressed(KeyEvent e) {

                // closes frame when esc is pressed
                if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
                    frame.dispose();
                }

                // recognises user pressed enter to add new user
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                    // checks if the username is valid
                    if (newUser.getText().length() > 1069 || newUser.getText().length() < 1) {
                        JOptionPane.showMessageDialog(null,
                                "Invalid! Enter a valid username.", "OutOfBounds",
                                JOptionPane.ERROR_MESSAGE);
                    }

                    // verifies the user with the database
                    else {
                        recv = sendrecv.proofuser(newUser.getText());

                        // ERROR user is not in database
                        if (recv.equals("0")) {
                            JOptionPane.showMessageDialog(null, "User not found!",
                                    "Check spelling or send your partner a link to join this messenger.",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                        else if (recv.equals("1")) {
                            me.newchat(newUser.getText());
                            Useruser.setText(newUser.getText());
                            Useruser.addActionListener(UserPressed);
                            Users.add(Useruser, 1);
                            Users.remove(newUser);
                            addUsers.setEnabled(true);
                            frame.setVisible(true);
                        }

                        else {
                            JOptionPane.showMessageDialog(null,
                                    "An unknown exception occured please try again! \nEnsure your internet connection.",
                                    "ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                        frame.setVisible(true);
                    }
                }
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyTyped(KeyEvent e) {
            }
        };

    }

    void loadMes(String[] Mes) {

    }

    FocusListener bla = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

    @Override public void focusLost(FocusEvent e){if(e.getSource()==newUser){Users.remove(newUser);addUsers.setEnabled(true);frame.setVisible(true);}}};

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

        writeMessage.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_ENTER && writeMessage.getText().length() > 0) {

                    // currentUser = "vali";
                    // String key = encry.getKey(currentUser);
                    sendrecv.sendMes(
                            new String[] { currentUser, encry.encryption(writeMessage.getText(), encry.getKey(currentUser)) });
                            writeMessage.setText("");
                    // JLabel label = new JLabel(sendrecv.getMes(currentUser));
                    // label.setSize(100, 100);
                    // chat.add(label);
                }
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
        Users.setPreferredSize(new Dimension(frame.getWidth() / 10 * 3, frame.getHeight()));
        frame.add(Users, BorderLayout.WEST);
        // Users.setSize(frame.getWidth()-100, frame.getHeight());
        // tUsers.setSize(frame.getWidth()/10*2, frame.getHeight());
        frame.setVisible(true);
        chat.setLayout(new GridLayout(10, 1));
        chat.setBackground(Color.black);

        back.setBackground(Color.red);
        chat.setOpaque(true);
        background = new JLabel(ba);
        back.add(background, Integer.valueOf(0));
        back.setPreferredSize(new Dimension(frame.getWidth() - Users.getWidth(), frame.getHeight()));
        frame.add(back, BorderLayout.EAST); // ??????
        frame.setVisible(true);
        chat.setSize(back.getWidth(), back.getHeight());
        back.add(chat, Integer.valueOf(1));
        frame.setVisible(true);
        // System.out.println(back.getWidth()+"rrr");
        // System.out.println(frame.getWidth());

    public void reloadframe() {

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

    public void newchat() {
        // chat d = new chat("ee");
        // System.out.println(d.name);
        String key = "";
        for (chat ch : chats) {
            if (ch.name.equals(currentUser)) {
                ch.getMessages();
                key = ch.key();

                break;
            }

        }

        // if (key.equals("0")) {
        // joptionpane.showmessagedialog(null,
        // "invitation sent to user(or it crashed(50/50 chance))", "good luck",
        // joptionpane.information_message);
        // } else if (key.equals("1")) {
        // joptionpane.showmessagedialog(null,
        // "user has not accepted your invitation yet", "ignored",
        // joptionpane.warning_message);

        if (!key.equals("")) {

            JOptionPane.showMessageDialog(null,
                    "generating key ...", "working ...",
                    JOptionPane.INFORMATION_MESSAGE);
            String mes = sendrecv.getMes(currentUser);
            System.out.println(mes);
            if (mes.length() >= 8) {
                JLabel messages;
                messages = new JLabel();
                chat.remove(messages);
                String[] decMessages = encry.decMes(mes, key);

                for (int x = 0; x < decMessages.length - oldMessages.length; x++) {
                    messages = new JLabel(decMessages[decMessages.length - 1 - x]);
                    messages.setBackground(new Color(0, 255, 0));
                    messages.setForeground(Color.white);
                    messages.setSize(chat.getWidth(), 100);
                    chat.add(messages);
                    oldMessages = decMessages;
                }

            }
            writeMessage.setText("");
            chat.add(writeMessage);
            frame.setVisible(true);
        }

    }
}