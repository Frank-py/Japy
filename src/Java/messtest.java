package Java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.google.gson.Gson;

public class messtest {
    testuser me;
    JFrame frame;
    Font stdFont;
    Color caretColor, colortheme, fontColor;
    JLayeredPane back;
    ImageIcon logo, ba, plus;
    JPanel chat, Users;
    JButton addUsers, Useruser;
    JTextField newUser, writeMessage;
    KeyListener enter;
    ActionListener UserPressed, addListener;
    WindowListener listener;

    // JLabel background;

    messtest(testuser me) {
        this.me = me;
        try {
            listen();
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void initialize() {

        // images
        ba = new ImageIcon("src\\Java\\Backgroundy.png");
        logo = new ImageIcon("src\\pictures\\prof5.png");
        plus = new ImageIcon("src\\pictures\\plus.png");

        // colors
        colortheme = new Color(27, 37, 43);
        caretColor = Color.white;
        fontColor = Color.white;

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
        frame.addWindowListener(listener);


        addUsers = new JButton("Add User");
        addUsers.setIcon(plus);
        addUsers.setFont(new Font("MV Boli", Font.BOLD, 20));
        addUsers.setForeground(fontColor);
        addUsers.setBackground(new Color(47, 49, 54));
        addUsers.setFocusable(false); // needed?
        addUsers.addActionListener(addListener);

        Users = new JPanel();
        Users.setLayout(new GridLayout(11, 1));
        Users.setBackground(colortheme);
        Users.setBackground(Color.BLUE);
        Users.setPreferredSize(new Dimension(frame.getWidth() / 10 * 3, frame.getHeight()));
        Users.add(addUsers);

        frame.add(Users, BorderLayout.WEST);

        // creating the JButton for Usernames
        Useruser = new JButton();
        Useruser.setFont(new Font("MV Boli", Font.PLAIN, 35));
        Useruser.setBackground(new Color(47, 49, 54));
        Useruser.setForeground(new Color(0xFFFFFF));
        Useruser.setFocusable(false);
        Useruser.addActionListener(UserPressed);

        // creating the chat textfield
        writeMessage = new JTextField();
        writeMessage.setFont(new Font("Consolas", Font.PLAIN, 25));
        writeMessage.setForeground(Color.white);
        writeMessage.setCaretColor(Color.white);
        writeMessage.setBackground(colortheme);
        writeMessage.addKeyListener(enter);

        // creating the addUser JButton

        // creating the newUser textfield
        newUser = new JTextField();
        newUser.setFont(new Font("Consolas", Font.PLAIN, 25));
        newUser.setForeground(fontColor);
        newUser.setBackground(colortheme);
        newUser.setCaretColor(caretColor);
        newUser.addKeyListener(enter);

        // creating the chatpanel
        back = new JLayeredPane();
        back.setPreferredSize(new Dimension(frame.getWidth() - Users.getWidth(), frame.getHeight()));//needed?
        
        // creating the chatpanel
        chat = new JPanel();
        chat.setSize(back.getWidth(), back.getHeight());
        chat.setLayout(new GridLayout(10, 1));

        back.add(chat, Integer.valueOf(1));
        frame.add(back, BorderLayout.EAST);
        //frame.setvisible(true);

        


    }

    void listen() {

        // checks if window got closed
        listener = new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                me.close();
            }
        };

        // checks if an added user is clicked
        UserPressed = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textuser = ((JButton) e.getSource()).getText();
                if (me.openchat(textuser)) {
                    // creates the chat interface visually
                    //newchat();fdsgsdghds
                    // loads the messages to the chat if necessary
                } else {
                    // displaying the error message when key is not ready
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

                if (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_ENTER && writeMessage.getText().length() > 0
                        && e.getSource() == writeMessage) {

                    // String key = encry.getKey(currentUser);
                    me.sendMes(writeMessage.getText());

                    // sendrecv.sendMes(
                    // new String[] { currentUser, encry.encryption(writeMessage.getText(),
                    // encry.getKey(currentUser)) });
                    // writeMessage.setText("");
                }

                // recognises user pressed enter to add new user
                if (e.getKeyChar() == KeyEvent.VK_ENTER && e.getSource() == newUser) { // potential bug

                    // checks if the username is valid
                    if (newUser.getText().length() > 1000 || newUser.getText().length() < 1) {
                        JOptionPane.showMessageDialog(null,
                                "Invalid! Enter a valid username.", "OutOfBounds",
                                JOptionPane.ERROR_MESSAGE);
                        newUser.setText("");
                    }
                    // verifies the user with the database
                    else {
                        // user is in database and data written to json
                        if (me.newchat(newUser.getText())) {

                            // creates new userbutton and rearranges the layout
                            Useruser.setText(newUser.getText());
                            Users.remove(newUser);
                            Users.add(Useruser, 1);
                            addUsers.setEnabled(true);
                        }
                        // user is not in database
                        else {
                            JOptionPane.showMessageDialog(null,"Check spelling or send your partner a link to join this messenger.", "User not found!",
                                    
                                    JOptionPane.ERROR_MESSAGE);
                            newUser.setText("");
                        }
                    }
                    frame.setVisible(true);
                }
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyTyped(KeyEvent e) {
            }
        };

        // checks if addUser button is pressed
        addListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // checks the source of the event
                if (e.getSource() == addUsers) {
                    // adds the textfield to the sidebar
                    addUsers.setEnabled(false);
                    Users.add(newUser, 1);
                    newUser.requestFocusInWindow();
                    frame.setVisible(true);
                }
            }
        };
    }

    // load messages to chat
    void loadMes(String[] mes) {

        // todo show on screen

    }

    void newchat(String User) {

        loadMes(me.getMes(User, 0, 5));

    }
}
//     {
        
    
//         // Users.setSize(frame.getWidth()-100, frame.getHeight());
//         // tUsers.setSize(frame.getWidth()/10*2, frame.getHeight());
//         frame.setVisible(true);
        
//         chat.setBackground(Color.black);

//         back.setBackground(Color.red);
//         chat.setOpaque(true);
//         background = new JLabel(ba);
//         back.add(background, Integer.valueOf(0));
//         back.setPreferredSize(new Dimension(frame.getWidth() - Users.getWidth(), frame.getHeight()));
//         frame.add(back, BorderLayout.EAST); // ??????
//         frame.setVisible(true);
        
//         frame.setVisible(true);
//         // System.out.println(back.getWidth()+"rrr");
//         // System.out.println(frame.getWidth());

//     public void reloadframe() {

//         // Users.add(addUsers);
//         // Users.setSize(addUsers.getWidth(), frame.getHeight());
//         back.setSize(new Dimension(frame.getWidth() - Users.getWidth(), frame.getHeight()));

//         back.setPreferredSize(new Dimension(frame.getWidth() - Users.getWidth(), frame.getHeight()));
//         chat.setSize(back.getWidth(), back.getHeight());
//         background.setSize(frame.getWidth() - Users.getWidth(), frame.getHeight());
//         Users.setPreferredSize(new Dimension(frame.getWidth() / 10 * 2, frame.getHeight()));

//     }}

//     /*
//      * JLabel background = new JLabel();
//      * Image imgscale = img.getScaledInstance(back.getWidth(), back.getHeight(),
//      * Image.SCALE_SMOOTH);
//      * ImageIcon bascale = new ImageIcon(imgscale);
//      * background.setIcon(bascale);
//      * background.setSize(back.getWidth(), back.getHeight());
//      * back.add(background, Integer.valueOf(0));
//      * back.remove(background);
//      * frame.setVisible(true);
//      */

//     // public void newchat() {
//     // chat d = new chat("ee");
//     // System.out.println(d.name);
//     String key = "";for(
//     chat ch:chats)
//     {
//         if (ch.name.equals(currentUser)) {
//             ch.getMessages();
//             key = ch.key();

//             break;
//         }

//     }

//     // if (key.equals("0")) {
//     // joptionpane.showmessagedialog(null,
//     // "invitation sent to user(or it crashed(50/50 chance))", "good luck",
//     // joptionpane.information_message);
//     // } else if (key.equals("1")) {
//     // joptionpane.showmessagedialog(null,
//     // "user has not accepted your invitation yet", "ignored",
//     // joptionpane.warning_message);

//     if(!key.equals(""))
//     {

//         JOptionPane.showMessageDialog(null,
//                 "generating key ...", "working ...",
//                 JOptionPane.INFORMATION_MESSAGE);
//         String mes = sendrecv.getMes(currentUser);
//         System.out.println(mes);
//         if (mes.length() >= 8) {
//             JLabel messages;
//             messages = new JLabel();
//             chat.remove(messages);
//             String[] decMessages = encry.decMes(mes, key);

//             for (int x = 0; x < decMessages.length - oldMessages.length; x++) {
//                 messages = new JLabel(decMessages[decMessages.length - 1 - x]);
//                 messages.setBackground(new Color(0, 255, 0));
//                 messages.setForeground(Color.white);
//                 messages.setSize(chat.getWidth(), 100);
//                 chat.add(messages);
//                 oldMessages = decMessages;
//             }

//         }
//         writeMessage.setText("");
//         chat.add(writeMessage);
//         frame.setVisible(true);
//     }

// }
