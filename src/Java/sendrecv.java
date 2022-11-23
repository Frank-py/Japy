package Java;
//package Japy.src.Java;

import java.io.*;
import java.net.*;

public class sendrecv{
    public static BufferedReader bf;
    public static PrintWriter out;
    public static String s;
    public static Socket socket;

    public static String login(String in[]) {
        try {
            socket =   new Socket("localhost", 6000);
            bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            
       
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
                return s;
            } catch (IOException e) {
                return null;
            }
        
    }
    public static String proofuser (String in) {
    
        try {
        out.print("proofuser");
        out.flush();
        
            bf.readLine();
        out.print(in.replaceAll("\r", "").replaceAll("\n", ""));
        out.flush();
        bf.readLine();
        return bf.readLine();
        } catch (IOException e) {
            return null;
        }
    }

   public static String sendMes(String[] in) {
    try {
        out.print("sendMes");
        out.flush();
        bf.readLine();
        out.print(in[0]);
        out.flush();
        bf.readLine();
        out.print(in[1]);
        out.flush();
        bf.readLine();
        return "Success";}
        catch (IOException e) {
            return null;
        }
        }

       public static String getMes(String in)  {
        
            try {
            out.print("getMes");
            out.flush();
                bf.readLine();
            out.print(in);
            out.flush();
            bf.readLine();
            return bf.readLine();
            } catch (IOException e) {
                return null;
            }
        } 






    public String send(String Funktion, String in[]) {
        try {
            s = null;
            bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
           /* if (Funktion == "login") {
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
            } if (Funktion == "proofuser") {
                out.print("proofuser");
                out.flush();
                bf.readLine();
                out.print(in[0].replaceAll("\r", "").replaceAll("\n", ""));
                out.flush();
                bf.readLine();
                s = bf.readLine();
            }  if (Funktion == "sendMes") {
                out.print("sendMes");
                out.flush();
                bf.readLine();
                out.print(in[0]);
                out.flush();
                bf.readLine();
                out.print(in[1]);
                out.flush();
                bf.readLine();
                s = "Success";}*/
             if (Funktion == "getMes") {
                out.print("getMes");
                out.flush();
                bf.readLine();
                out.print(in[0]);
                out.flush();
                bf.readLine();
                s = bf.readLine();
            } else if (Funktion == "createKey") {
                out.print("createKey");
                out.flush();
                bf.readLine();
                out.print(in[0]);
                s = bf.readLine();
                if (s.equals("0")) {
                    s = "";
                    String[] pgA = encry.newkey(new String[] {});
                    // s.append(in[0]);
                    out.print(pgA[0]);
                    out.flush();
                    bf.readLine();
                    out.print(pgA[1]);
                    out.flush();
                    bf.readLine();
                    out.print(pgA[2]);
                    out.flush();
                    bf.readLine();

                }
                if (s.equals("2")) {
                    String[] Bap = new String[3];
                    Bap[2] = bf.readLine();
                    String g = bf.readLine();
                    Bap[0] = bf.readLine();
                    String[] pgA = encry.newkey(new String[] { Bap[2], g });
                    Bap[1] = encry.a();
                    out.print(pgA[0]);
                    out.flush();
                    bf.readLine();
                    out.print(pgA[1]);
                    out.flush();
                    bf.readLine();
                    out.print(pgA[2]);
                    out.flush();
                    bf.readLine();

                    s = encry.endnewkey(Bap);

                } else {
                    s = bf.readLine();

                }

            } else if (Funktion == "isKey") {
                out.print("isKey");
                out.flush();
                bf.readLine();
                out.print(in[0]);
                out.flush();
                bf.readLine();
                s = bf.readLine();

            }
            // bf.close();
            // out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "4";
        }
        return s;
    }

    // public static String[] recv() {
    // String[] lol = {};
    // bf.readLine();
    // if (bf.readline == "recvkey") {
    // return lol;

    // }
}
