//package Java;
package Japy.src.Java;

import java.io.*;
import java.net.*;

public class sendrecv {
    public static BufferedReader bf;
    public static  PrintWriter out;
    public static String s;
    public static Socket socket() throws UnknownHostException, IOException{
    
     Socket socket =   new Socket("localhost", 6000);
     return socket;
    }
    public static String send(Socket socket, String Funktion, String in[]) {
        try {
            s = null;
            bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            if (Funktion == "login") {
                out.print("login");
                out.flush();
                bf.readLine();
                out.print(in[0].replaceAll("\r", "").replaceAll("\n", ""));
                out.flush();
                bf.readLine();
                out.print(in[1].replaceAll("\r", "").replaceAll("\n", ""));
                out.flush();
                bf.readLine();
                s = bf.readLine();
            } else if (Funktion == "proofuser") {
                out.print("proofuser");
                out.flush();
                bf.readLine();
                out.print(in[0].replaceAll("\r", "").replaceAll("\n", ""));
                out.flush();
                bf.readLine();
                s = bf.readLine();
            } else if (Funktion == "sendMes") {
                out.print("message");
                out.flush();
                bf.readLine();
                out.print(in[0]);
                out.flush();
                bf.readLine();
                out.print(in[1]);
                out.flush();
                bf.readLine();
                s = "Success";
            } else if (Funktion == "sendkey") {

            }

        } catch (IOException e) {
            e.printStackTrace();
            return "4";
        }
        return s;
    }

    public static String[] recv() {
        String[] lol = {};
        bf.readLine();
        return lol;
        if ( == "recvkey") {

        }
    }
}