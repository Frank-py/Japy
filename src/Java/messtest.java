package Java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    //JLabel background;
    
    messtest(){
        initialize();
        listen();
        


    }

    void initialize(){
        fontcolor = new Color(0xD4D4D4);
        back = new JLayeredPane();
        colortheme = new Color(27, 37, 43);
        logo = new ImageIcon("src\\pictures\\prof5.png");
        chat = new JPanel();
        stdFont = new Font("Consolas", Font.PLAIN, 25);

        writeMessage = new JTextField();
        writeMessage.setFont(stdFont);
        writeMessage.setForeground(Color.white);
        writeMessage.setCaretColor(Color.white);
        writeMessage.setBackground(colortheme);

        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(1366, 768);
        frame.setMinimumSize(new Dimension(370, 370));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Messenger");
        frame.setIconImage(logo.getImage());
        frame.getContentPane().setBackground(colortheme);
        frame.setVisible(true);
    }
    void listen(){
        KeyListener enter = new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
                    frame.dispose();
                }
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    functions.newchat(newUser.getText());
                    if (newUser.getText().length() > 1069 || newUser.getText().length() < 1) {
                        recv = "5";
                    } else {
                        recv = sendrecv.proofuser(newUser.getText());
                        if (recv.equals("0")) {
                            JOptionPane.showMessageDialog(null, "User not found!", "User not found!",
                                    JOptionPane.ERROR_MESSAGE);
                            recv = "500";
                        } else if (recv.equals("1")) {
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
                        } else if (recv.equals("4")) {
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

    }

    
}
