package Java;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
public class javaclient2 {
    public static String send(String a, Socket socket) throws UnknownHostException, ClassNotFoundException, InterruptedException{
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
