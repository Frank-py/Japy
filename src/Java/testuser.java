package Java;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import org.json.*;

import com.google.gson.JsonParser;

public class testuser {
    public String username;
    private String pw;
    private messtest main;
    private encry secure;
    public File keys;
    File userjson;
    FileWriter writer;
    FileReader reader;
    JsonParser parser;

    Path path;

    // the user currently loggedin
    testuser(String[] UP) {
        this.username = UP[0];
        this.pw = UP[1];
        this.main = new messtest(this);
        try {

            // this.filePath = Path.of("src/" + this.username + ".json");
            this.secure = new encry(this);
            String initialize;
            this.keys = new File("src/", this.username + ".json");
            this.path = this.keys.toPath();
            if (this.keys.exists()) {
                initialize = Files.readString(this.path);

            } else {
                initialize = "{}";
                this.keys.createNewFile();

            }

            reader = new FileReader(keys);
            writer = new FileWriter(keys, false);
            writer.write(initialize);
            writer.flush();

            // Map<String, String> jsonObject = new HashMap<String, String>();

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    // creates a new chat between two users
    public boolean newchat(String username) {

        if (sendrecv.proofuser(username)) {
            System.out.println(username);

            // only executes if user is not in json
            if (getValue(username, "Messages") == null) {
                System.out.println(username);
                // writes the user + attributes to json
                JSONObject userjson = new JSONObject()
                        .put("atemp", secure.a())
                        .put("Messages", new JSONArray())
                        .put("key", "secure.getKey(username)");
                JSONObject json;
                try {
                    json = new JSONObject(reader.read());
                    writer.write((json.put(username, userjson)).toString());
                    System.out.println((json.put(username, userjson)).toString());
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
            JSONObject json = new JSONObject(reader.read());
            // writer.write(json.toString());
            // writer.flush();
            JSONObject User = (JSONObject) json.get(username);
            return User.get(key).toString();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return null;
        }

    }

    // sets the value for the given key and user
    public void setValue(String username, String key, String value) {

        try {
            JSONObject json = new JSONObject(Files.readString(path));
            json.put(username, ((JSONObject) json.get(username)).put(key, value));
            writer.write(json.toString());
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // checks if chat is ready to open (key is fully generated)
    public boolean openchat(String user) {
        if (!getValue(user, "key").equals("")) {
            System.out.println("22222");
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

    public void sendMes(String Mes) {

    }
}