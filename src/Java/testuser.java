package Java;

import java.io.*;

import com.google.gson.*;

public class testuser {
    public String username;
    private String pw;
    private String a;
    private messInterface main;
    private encry secure;
    private chat[] chats;
    private int NumberOfChats;
    public File keys;

    testuser(String[] UP){
        this.NumberOfChats = 0;
        this.username = UP[0];
        this.pw = UP[1];
        this.a = encry.a();
        this.main = new messInterface();
       // this.secure = new encry();
        this.chats = new chat[100];
        this.keys = new File("src/", this.username + ".json");
        try {
            Gson gson = new Gson();
            this.keys.createNewFile();
            FileWriter fileWriter = new FileWriter(this.keys);
            JsonObject jsonObject = new JsonObject();
            this.a = encry.a();
            jsonObject.add("a", jsonObject);
            jsonObject.add(a, jsonObject);
            System.out.println(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } 




    }

public void newchat(String newchat){

    chats[NumberOfChats] = new chat (newchat);
    this.NumberOfChats ++;
}
    
}
