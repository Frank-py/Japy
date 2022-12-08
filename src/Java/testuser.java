package Java;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;

public class testuser {
    public String username;
    private String pw;
    private messtest main;
    private encry secure;
    private chat[] chats;
    private int NumberOfChats;
    public File keys;
    public Path filePath;
    //the user currently loggedin
    testuser(String[] UP) {
        this.NumberOfChats = 0;
        this.username = UP[0];
        this.pw = UP[1];
        this.main = new messtest(this);
        this.filePath = Path.of("src/" + this.username + ".json");
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

    //creates a new chat between two users
    public void newchat(String user) {
        //addd encryption and key exchange

        chats[NumberOfChats] = new chat(newchat);
        this.NumberOfChats++;
    }

    //checks if chat is ready to open (key is fully generated)
    public boolean openchat(String user) {
        //add check for key
        return true;
        
    }
    
    //asks the server for hew messages and proofs if any are locally safed
    public String[] getMes(String user,int amount){
        //add get messages
        String mes[] = new String[amount];
        return mes;
    }

}
