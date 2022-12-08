package Java;

import javax.swing.*;
import java.io.*;
import com.google.gson.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.HashMap;
// keyslogin.namedeseingelogtenusers.json
// password damit man eingeloggt bleiben kann
// alle as mit dem der current user schreibt




public class chat {
    

    public String name;
    private String key;
    private String currentUser;
    private String[] oldMessages;
    private JTextField in;
    private JPanel chat;
    private JFrame frame;
    

    chat(String name) { // schreibt in File für jeden Chat Objekt
        this.name = name;
        this.key = key();
        

       in.setText("");
       chat.add(in);
       frame.setVisible(true);
       File Datei = new File("src/" + hzr + ".json");
       if (!Datei.exists() || !Datei.isDirectory()) {
        try {
            Datei.createNewFile();
            FileWriter fileWriter = new FileWriter(Datei);
            Map<String, String> jsonObject = new ();
            jsonObject.put("password", "password");
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

       }
        Datei = new File("src/" + hzr + ".json");
        FileWriter fileWriter = new FileWriter(Datei);
        try (FileReader reader = new FileReader("employees.json"))
        {
        JsonObject jsonObject = JsonParser.parseString(reader).getAsJsonObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 

    }   

    JsonObject json(){
        ;
        if(f.exists() && !f.isDirectory()) { 
    // do something
}
        
        
    }

    String key() {
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

    String[] getMessages() {
        String mes = sendrecv.getMes(currentUser);
        System.out.println(mes);
        String[] decMessages = null;
        if (mes.length() >= 8) {
            JLabel messages;
            messages = new JLabel();
            chat.remove(messages);
            decMessages = encry.decMes(mes, key);
            

            // for (int x = 0; x < decMessages.length - oldMessages.length; x++) {
            //     messages = new JLabel(decMessages[decMessages.length - 1 - x]);
            //     messages.setBackground(new Color(0, 255, 0));
            //     messages.setForeground(Color.white);
            //     messages.setSize(chat.getWidth(), 100);
            //     chat.add(messages);
            //     oldMessages = decMessages;
            // }
        }
        return new String[]{"2"};
        //return decMessages;
       
    }
}