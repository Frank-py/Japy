package Java;
import java.math.*;
import java.util.*;

public class encry {
    public static int p;
    public static int g;
    public static int a;
    public static BigInteger A;
    public static String key = "";
    testuser User;

    encry(testuser User) {
        this.User = User;
            a = 12;
        

    }

    public void main(String[] args) {
        getKey("wgg");
    }

    public String a() {
        return Integer.toString(a);
    }

    public String getKey(String username) {
        String key = User.getValue(username,"key");
        if (key.equals("")) {
            
                return sendrecv.createKey(username);
            }  
         else {
            return key;
        
        }

    }

    public static String[] newkey(String[] in) {
        Random randy = new Random();

        if (in.length == 0) { // Neu erstellte p und g
            do {
                //p = randy.nextInt(1000000000);
                p = randy.nextInt(1000);
                //p<1000000
            } while (!isPrime(p) | p < 100);
            g = randy.nextInt(p - 1);

        } else {
            p = Integer.parseInt(in[0]);
            g = Integer.parseInt(in[1]);
        }

        BigInteger gg = BigInteger.valueOf(g);
        BigInteger pp = BigInteger.valueOf(p);
        // a = randy.nextInt(1000000000);
        BigInteger rando = BigInteger.valueOf(a);
        BigInteger A = gg.modPow(rando, pp);
        String[] numbers = { pp.toString(10), gg.toString(10), A.toString(10) };
        return numbers;
    }

    public static String endnewkey(String[] in) {
        //String key = "";
        
        return new BigInteger(in[2]).modPow(new BigInteger(in[1]), new BigInteger(in[0])).toString(10);
    }

    public static String[] decMes(String in, String key) {
        Base64.Decoder dec = Base64.getDecoder();
        String[] messages = in.split(";;;");
        int n = 0;
        String[] out = new String[messages.length];
        for (String i : messages) {
            out[n] = decryption(Stringtobinary(new String(dec.decode(i))), key);
            n++;
        }
        return out;
    }

    public static String encryption(String in, String key) {
        key = Integer.toBinaryString(Integer.parseInt(key));
        in = Stringtobinary(in);
        Base64.Encoder enc = Base64.getEncoder();
        in = enc.encodeToString(Binarytostring(crypt(in, key)).getBytes());
        return in;
    }

    public static String decryption(String in, String key) {
        // key = Integer.toBinaryString(Integer.parseInt(key));
        // in = Binarytostring(crypt(Stringtobinary(in), key));
        // return in;
        int keyInt = Integer.parseInt(key);
        key = Integer.toBinaryString(keyInt);
        in = crypt(in, key);
        String res = Binarytostring(in);
        return res;
    }

    static boolean isPrime(double n) {
        if (n <= 1)
            return false;
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;
        return true;
    }

    public static String crypt(String mess, String key) {
        int messLen = mess.length();
        int keyLen = key.length();
        String temp = key;
        String out = "";

        while (messLen > keyLen) {
            key = key + temp;
            keyLen = key.length();
        }
        char[] messC = mess.toCharArray();
        char[] keyC = key.toCharArray();
        
        for (int x = 0; x < mess.length(); x++) {
            if (keyC[x] == messC[x]) {
                out = out + 0;
            } else {
                out = out + 1;
            }
        }
        return out;
    }

    public static String Stringtobinary(String in) {
        char[] c = in.toCharArray();
        String out = "";
        for (int x = 0; x < c.length; x++) {
            String bin = Integer.toBinaryString((int) c[x]);
            int y = bin.length() % 8;
            while (y != 0) {
                bin = 0 + bin;
                y = bin.length() % 8;
            }
            out = out + bin;
        }
        return out;
    }

    public static String Binarytostring(String in) {
        String out = "";
        String[] numbers = in.split("(?<=\\G.{8})");
        for (int x = 0; x <= numbers.length - 1; x++) {
            char c = (char) Integer.parseInt(numbers[x], 2);
            out = out + c;
        } 
        return out;
    }
}