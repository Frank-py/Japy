//package Japy.src.Java;
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
    int log = login.createGUI(socket);
    if (log == 4) {
      return;
    }
    // System.out.println("new window");
    messInterface.createGUI(log);
  }
}
