package Java;

import java.io.*;
import org.json.*;

public class testuser {
    public String username;
    private String pw;
    private messtest main;
    private encry secure;
    public File keys;
    File userjson;
    JSONTokener tokener;
    FileWriter writer;
    FileReader reader;

    // the user currently loggedin
    testuser(String[] UP) {
        this.username = UP[0];
        this.pw = UP[1];
        this.main = new messtest(this);
        try {
            writer = new FileWriter(keys);
            reader = new FileReader(keys);
            tokener = new JSONTokener(reader);

            // this.filePath = Path.of("src/" + this.username + ".json");
            this.secure = new encry(this);
            this.keys = new File("src/", this.username + ".json");

            this.keys.createNewFile();
            // Map<String, String> jsonObject = new HashMap<String, String>();

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    // creates a new chat between two users
    public boolean newchat(String username) {

        if (sendrecv.proofuser(username)) {

            // only executes if user is not in json
            if (getValue(username, "messages").equals(null)) {

                // writes the user + attributes to json
                JSONObject userjson = new JSONObject()
                        .put("atemp", secure.a())
                        .put("Messages", new JSONArray())
                        .put("key", secure.getKey(username));
                JSONObject json = new JSONObject(tokener);
                try {
                    writer.write((json.put(username, userjson)).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
        return false;
    }

    // gets the value for the given key and user
    public String getValue(String username, String key) {
        try {
            JSONObject User = (JSONObject) new JSONObject(tokener).get(username);
            return User.get(key).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // sets the value for the given key and user
    public void setValue(String username, String key, String value) {
        JSONObject json = new JSONObject(tokener);
        json.put(username, ((JSONObject) json.get(username)).put(key, value));

        try {
            writer.write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // checks if chat is ready to open (key is fully generated)
    public boolean openchat(String user) {
        if (!getValue(user, "key").equals("")) {
            return true;
        }
       

        // add check for key
        return false;
    }

    // closes everything neccessary and finishes up
    public void close() {
        sendrecv.sendClose();
        try {
            writer.close();
            reader.close();
        } catch (IOException e) {
            System.out.println("Couldn't close Reader or Writer!");
            e.printStackTrace();
        }
    }

    // asks the server for new messages and proofs if any are locally safed
    public String[] getMes(String user, int start, int end) {

        // add get messages
        String[] decMessages = encry.decMes(sendrecv.getMes(user, start, end), getValue(user, "key"));
        return decMessages;
    }
}