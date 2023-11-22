package Java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;

import org.json.*;

public class user {
    public String username;
    // private messenger_interface main;
    public File keys;
    File userjson;
    FileWriter writer;
    FileReader reader;
    Path path; 

    // the user currently logged_in
    user(String[] UP) {
        this.username = UP[0];
        String pw = UP[1];

      //  main =
        new messenger_interface(this);
        try {

            // this.filePath = Path.of("src/" + this.username + ".json");
           // this.secure = new encry(this);
            this.keys = new File("src/", this.username + ".json");
            this.path = keys.toPath();
           // Path keysPath = Path.of("C:\\Users\\HP\\Desktop\\gfg.txt");
           // this.path = this.keys.toPath();
            if (!this.keys.exists()) {
                writer = new FileWriter(keys);
                writer.write("{}");
                writer.flush();

            }

            reader = new FileReader(keys);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    // creates a new chat between two users
    public boolean newchat(String username) {

        if (sendrecv.proofuser(username)) {
            

            // only executes if user is not in json
            if (getValue(username, "Messages") == null) {
                
                // writes the user + attributes to json
                JSONObject userjson = new JSONObject()
                        .put("atemp", encrypt.a())
                        .put("Messages", new JSONArray())
                        .put("key", "secure.getKey(username)");
                JSONObject json;
                try {
                    json = new JSONObject(reader.read());
                    writer = new FileWriter(keys);
                    writer.write((json.put(username, userjson)).toString());
                    writer.flush();
                } catch (JSONException | IOException e1) {
                    e1.printStackTrace();
                }

            }
            return true;
        }
        return false;
    }

    // gets the value for the given key and user
    public String getValue(String username, String key) {
        try {
            JSONObject json = new JSONObject(Files.readString(path));
            JSONObject User = (JSONObject) json.get(username);
            return User.get(key).toString();
        } catch (Exception e1) {
            
            //e1.printStackTrace();
            return null;
        }

    }

    // sets the value for the given key and user
    public void setValue(String username, String key, String value) {

        try {
            JSONObject json = new JSONObject(reader.read());
            json.put(username, ((JSONObject) json.get(username)).put(key, value));
            writer = new FileWriter(keys);

            writer.write(json.toString());
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // checks if chat is ready to open (key is fully generated)
    public boolean openchat(String user) {
        return !getValue(user, "key").isEmpty();

        // add check for key
    }

    // closes everything neccessary and finishes up
    public void close() {
        sendrecv.sendClose();
        try {
            writer.close();
            reader.close();
        } catch (Exception e) {
            
        }
    }

    // asks the server for new messages and proofs if any are locally safed
    public String[] getMes(String user, int start, int end) {

        // add get messages
        String[] decMessages = encrypt.decMes(sendrecv.getMes(user, start, end), getValue(user, "key"));
        return decMessages;
    }

    public void sendMes(String user, String Mes) {
        sendrecv.sendMes(new String[]{user, encrypt.encryption(Mes, getValue(user, "key"))});

    }
}