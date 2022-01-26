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
        /*
         * while (v) {
         * String b = send("hi");
         * System.out.println(b);
         * }
         */
    }

    public static String send(String a, Socket socket) {
        /* try {

            socket = new Socket("localhost", 5000);
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } */
        sent = new Thread(new Callable() {
            @Override
            public String call() {
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out.print(a);
                    out.flush();
                    String resp = in.readLine();
                    return resp;
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
        return a;
    }

}