package Java;

import java.io.*;
import java.util.*;
// import com.google.gson.*;
// import com.google.gson.stream.*;
import org.json.*;
import java.nio.*;
import java.nio.file.*;

public class testuser {
    public String username;
    private String pw;
    private messtest main;
    private encry secure;
    private chat[] chats;
    private int NumberOfChats;
    public File keys;
    File userjson;
    JSONTokener tokener;
    FileWriter writer;

    // the user currently loggedin
    testuser(String[] UP) {
        this.username = UP[0];
        this.pw = UP[1];
        this.main = new messtest(this);
        try {
            writer = new FileWriter(keys);
            tokener = new JSONTokener(new FileReader(keys));

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
    public void newchat(String username) {

        // JSONWriter;

        // InputStream is = testuser.class.getResourceAsStream(resourceName);
        JSONObject User = new JSONObject();
        // userjsonObject =
        // (JSONObject)JsonParser.parseString(Files.readString(this.keys.toPath())).getAsJsonObject().get(user);
        // System.out.println(User.get("messages"));
        // jsonObject =
        // JsonParser.parseString(Arrays.toString(Files.readAllLines(this.keys.toPath()).toArray(new
        // String [0]))).getAsJsonObject().get(user);

        if (getValue(username, "messages").equals(null)) {

            JSONObject userjson = new JSONObject()
                    .put("atemp", secure.a())
                    .put("Messages", new JSONArray())
                    .put("key", secure.getKey(username));
            User.put(username, userjson);

        } else {

            // jsonObject.get("key");
        }

        // JsonParser.parseString(Arrays.toString(Files.readAllLines(this.keys.toPath()).toArray(new
        // String [0]))).getAsJsonObject().toString();
        // jsonObject.put(jj, code);
        // FileWriter fileWriter = new FileWriter(userjson);
        // fileWriter.write(JSONObject.toString());
        // fileWriter.flush();
        // fileWriter.close();
        // jsonObject.get(json)

        // Write the JSON object to a file

    }

    public String getValue(String username, String key) {

        try {
            JSONObject User = (JSONObject) new JSONObject(tokener).get(username);
            return User.get(key).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setValue(String username, String key, String value) {
        JSONObject json = new JSONObject(tokener);

        // JSONObject user = (JSONObject) json.get(username);

        // user.put(key, value);
        json.put(username, ((JSONObject) json.get(username)).put(key, value));

        try {
            writer.write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // }

    // // checks if chat is ready to open (key is fully generated)
    public boolean openchat(String user) {

        // add check for key
        return true;
    }

}

    // closes everything neccessary and finishes up
public void close() {
    


}

// // asks server for user approval
// public boolean proofUser(String user){
// // passes on the boolean
// return sendrecv.proofuser(user);
// }

// // asks the server for hew messages and proofs if any are locally safed
// public String[] getMes(String user, int amount){

// // add get messages
// String mes[] = new String[amount];
// return mes;
// }

// }
