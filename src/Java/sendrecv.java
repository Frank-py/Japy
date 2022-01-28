package Java;

import java.io.*;
import java.net.Socket;

//import java.net.UnknownHostException;
public class sendrecv {
    //public static Stream<String> resp = null;
    public static String s = null;
    public static String send(Socket socket, String Funktion, String args[]) { 
        try {
            if (Funktion == "login") {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.print("login");
                out.flush();
                out.print(args[0].replaceAll("\r", "").replaceAll("\n", ""));
                out.flush();
                out.print(args[1].replaceAll("\r", "").replaceAll("\n", ""));
                out.flush();
                InputStreamReader in = new InputStreamReader(socket.getInputStream());
                BufferedReader bf = new BufferedReader(in);
                s = bf.readLine();
                System.out.println(s);

                }

           // }

        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        return s;

    }
}
