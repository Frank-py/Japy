package Java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class functions {
    static String recv;

   static void newchat(String UserName){

    if (UserName.length() > 1069 || UserName.length() < 1) {
        JOptionPane.showMessageDialog(null,
                    "Invalid! Enter a valid Username", "OutOfBounds",
                    JOptionPane.ERROR_MESSAGE);
    } else {
        recv = sendrecv.proofuser(UserName);
        if (recv.equals("0")) {
            JOptionPane.showMessageDialog(null, "User not found!", "User not found!",
                    JOptionPane.ERROR_MESSAGE);
        } else if (recv.equals("1")) {
            this.newchat(UserName);
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
        } 
        newUser.setText("");
        frame.setVisible(true);
    }
}
}

    }
    
}
