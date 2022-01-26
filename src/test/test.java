package test;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class test {
    public static Socket socket;
    public static String a;
    public static void main(String[] args) {
        try{
            socket =   new Socket("localhost", 6000);}
            catch(UnknownHostException e1)
            {
              e1.printStackTrace();
            }catch(
            IOException e2)
            {
              e2.printStackTrace();}
        try {
          a = javaclient2.send("string", socket);
        } catch (Exception e) {
          System.out.println("a");
        }
        System.out.println(a);
        
    }
}
