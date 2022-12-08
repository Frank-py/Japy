package Java;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;

public class testuser {
    public String username;
    private String pw;
    private messInterface main;
    private encry secure;
    private chat[] chats;
    private int NumberOfChats;
    public File keys;
    public Path filePath;
    


    testuser(String[] UP){
        this.NumberOfChats = 0;
        this.username = UP[0];
        this.pw = UP[1];
        this.main = new messInterface();
        this.filePath = Path.of("src/"+ this.username + ".json");
       // this.secure = new encry();
        this.chats = new chat[100];
        this.keys = new File("src/", this.username + ".json");
        try {
                this.keys.createNewFile();
                // Map<String, String> jsonObject = new HashMap<String, String>();
            
        } catch (IOException e) {
            e.printStackTrace();
        } 




    }

public void newchat(String newchat){

    chats[NumberOfChats] = new chat (newchat);
    this.NumberOfChats ++;
}
    
}





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
        writeMessage.setFont(new Font("Consolas", Font.PLAIN, 25));
        writeMessage.setForeground(Color.white);
        writeMessage.setCaretColor(Color.white);
        writeMessage.setBackground(color);
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
                    } else {
                        recv = sendrecv.proofuser(newUser.getText());
                        if (recv.equals("0")) {
                            JOptionPane.showMessageDialog(null, "User not found!", "User not found!",
                                    JOptionPane.ERROR_MESSAGE);
                            recv = "500";
                        } else if (recv.equals("1")) {
                            currentUser = newUser.getText();
                            recv = "500";
                            chats[n] = new chat(currentUser);
                           

                            JButton Useruser = new JButton(newUser.getText());
                            Useruser.setFont(new Font("MV Boli", Font.PLAIN, 35));
                            Useruser.setBackground(new Color(47, 49, 54));
                            Useruser.setForeground(new Color(0xFFFFFF));
                            Useruser.setFocusable(false);
                           

                            Useruser.addActionListener(new ActionListener() {                               
                                
                                public void actionPerformed(ActionEvent e) {
                                    for (chat ch : chats) {
                                        System.out.println(ch.name);
                                       // System.out.println("ch.name");
                                        if (ch.name.equals(((JButton)e.getSource()).getText())) {
                                            ch.getMessages();
                                            currentUser = ch.name;
                                            break;
                                        }
                                       // System.out.println( gon.toJson(ch.json));
                                        
                                    }
                                    newchat();

                                    // for (JButton o : userliste) {
                                    //     if (e.getSource() == o) {
                                            
                                    //       //  newchat();
                                    //     }
                                    // }
                                }
                            });
                            Users.add(Useruser, 1);
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
                        } else if (recv.equals("5")) {
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
            

            public void keyReleased(KeyEvent e) {
            }
            public void keyTyped(KeyEvent e) {
            }
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