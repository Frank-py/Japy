package Java;
//package Japy.src.Java;

import java.net.*;
import java.io.*;

public class run {
  public static void main(String[] args) {
    sendrecv.socket();  
    int log = login.createGUI();
    if (log == 4) {
      return;
    }
    // System.out.println("new window");
    messInterface.createGUI(log);
  }
}
