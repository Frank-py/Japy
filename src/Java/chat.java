
package Java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class chat {

    public String name;
    private String key;

    chat(String name) {
         this.name = name;
         this.key = key();

        else {
            //JOptionPane.showMessageDialog(null,
             //       "Generating Key ...", "Working ...",
              //      JOptionPane.INFORMATION_MESSAGE);
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
            in.setText("");
            chat.add(in);
            frame.setVisible(true);
        }

    }
    String key(){
        key = encry.getKey(this.name);
        if (key.equals("0")) {
            JOptionPane.showMessageDialog(null,
                    "Invitation sent to user", "Good Luck",
                    JOptionPane.INFORMATION_MESSAGE);
            return "";
        } else if (key.equals("1")) {
            JOptionPane.showMessageDialog(null,
                    "User has not accepted your invitation yet", "Ignored",
                    JOptionPane.WARNING_MESSAGE);
            return "";
        } else {
            return key;

    }

}
}
