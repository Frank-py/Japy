package Java;

import java.io.*;
import java.net.Socket;
import java.util.stream.Stream;

//import java.net.UnknownHostException;
public class sendrecv {
    //public static Stream<String> resp = null;
    public static String send(Socket socket, String Funktion, String args[]) { 
        try {
            if (Funktion == "login") {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out.print("login");
                out.flush();
                out.print(args[0].replaceAll("\r", "").replaceAll("\n", ""));
                out.flush();
                out.print(args[1].replaceAll("\r", "").replaceAll("\n", ""));
                out.flush();
                //System.out.println(in.ready());
                //resp = in.lines();

        
                String resp;
                /* while ((resp = in.readLine()) != null) {
                if ("$".equals(resp)) {
                    break;
                } */
        }

           // }

        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        return "1";

    }
}
