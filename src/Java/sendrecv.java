package Japy.src.Java;
import java.io.*;
import java.net.Socket;
//import java.net.UnknownHostException;
public class sendrecv {
    public static String send(String a, Socket socket) /*throws UnknownHostException, ClassNotFoundException, InterruptedException*/{
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.print(a);
            out.flush();
            String resp = in.readLine();
            socket.close();
            return resp;          
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        
    }
}
