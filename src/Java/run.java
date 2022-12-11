package Java;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class run {
  public static testuser user;

  public static void main(String[] args) {
    
    // JsonObject userjsonObject;
    // userjsonObject = (JsonObject)JsonParser.parseString(Files.readString(this.keys.toPath())).getAsJsonObject().get(user);
    // System.out.println(userjsonObject.get("messages"));
    // new messtest();
    login.createWindow();
  }

  public static void loggedin(String[] UP) {
    user = new testuser(UP);
  }

  public static testuser getUser() {
    return user;
  }
}