package Java;

import java.util.Map;
public class Daten
{
private String username;
private String password;
private Map<String, String> codes;

public Daten(){
}
public Daten(String username, String password, Map<String, String> codes){
this.username = username;
this.password = password;
this.codes = codes;
}

//Getters and setters

@Override
public String toString()
{
return "Daten [username=" + username + ", password=" + password+ ", " +
", codes=" + codes.toString()+ "]";
}
}