//package Japy.src.Java;
package Java;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class sendrecv {
    public static String s;

    public static String send(Socket socket, String Funktion, String args[]) {
        try {
            if (Funktion == "login") {
                BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                s = null;
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                //TimeUnit.MILLISECONDS.sleep(50);
                out.print("login");
                out.flush();
                bf.readLine();
               // TimeUnit.MILLISECONDS.sleep(50);
                out.print(args[0].replaceAll("\r", "").replaceAll("\n", ""));
                out.flush();
                bf.readLine();
               // TimeUnit.MILLISECONDS.sleep(50);
                out.print(args[1].replaceAll("\r", "").replaceAll("\n", ""));
                out.flush();
                bf.readLine();
                s = bf.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "4";
        }// catch (InterruptedException e) {
            //e.printStackTrace();
            //return "4";
        //}
        return s;
    }
}