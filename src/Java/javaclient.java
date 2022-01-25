package Japy.src.Java;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class javaclient {
    static Thread sent;
    static Thread receive;
    static Socket socket;
    public static String in;
    static boolean v = true;

    public static void main(String args[]) {
        while (v) {
            String b = send("hi");
            System.out.println(b);
        }
    }

    public static String send(String a) {
        try {

            socket = new Socket("localhost", 5000);
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        sent = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.print(a + "\r\n");
                    out.flush();
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        sent.start();
        try {
            sent.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return a;
    }

}
