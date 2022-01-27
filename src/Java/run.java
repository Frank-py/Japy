package Java;
import java.net.*;
import java.io.*;

public class run {
  public static Socket socket;
  public static void main(String[] args) {
    try {
      socket = new Socket("localhost", 5000);
    } catch (UnknownHostException e1) {
      e1.printStackTrace();
    } catch (IOException e2) {
      e2.printStackTrace();
    }
    
    int resp = login.createGUI(socket);
    System.out.println(resp);
  }
  
}
