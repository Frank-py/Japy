package Java;

import java.io.*;
import java.net.Socket;

public class sendrecv {
    public static String s;

    public static String send(Socket socket, String Funktion, String args[]) {
        try {
            s = null;
            BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println(args[0]);
            System.out.println(Funktion);
            if (Funktion == "login") {
                out.print("login");
                out.flush();
                bf.readLine();
                out.print(args[0].replaceAll("\r", "").replaceAll("\n", ""));
                out.flush();
                bf.readLine();
                out.print(args[1].replaceAll("\r", "").replaceAll("\n", ""));
                out.flush();
                bf.readLine();
                s = bf.readLine();
            }
            else if (Funktion == "proofuser"){
                System.out.println("test");
                out.print("proofuser");
                out.flush();
                bf.readLine();
                out.print(args[0].replaceAll("\r", "").replaceAll("\n", ""));
                out.flush();
                bf.readLine();
                s = bf.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "4";
        }
        return s;
    }
}