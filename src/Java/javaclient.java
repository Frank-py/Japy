package Java;
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
        /*
         * while (v) {
         * String b = send("hi");
         * System.out.println(b);
         * }
         */
    }

    public static void send(String a, Socket socket) {
        /* try {

            socket = new Socket("localhost", 5000);
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } */
        sent = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.print(a);
                    out.flush();
                    //socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        sent.start();
        try {
            sent.join();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

}