package Java;
import java.math.BigInteger;
import java.util.Base64;
import java.util.Random;

public class encrypt {
    public static int p;
    public static int g;
    public static int a;
    public static BigInteger A;
    public static String key = "";
    user User;

    encrypt(user User) {
        this.User = User;
            a = 12;
        
    }

    public void main(String[] args) {
        getKey("wgg");
    }

    public static String a() {
        return Integer.toString(a);
    }

    public String getKey(String username) {
        String key = User.getValue(username,"key");
        if (key.isEmpty()) {
            
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
    //decrypt array of messages
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
    //can be used to encrypt single messages
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

    private static boolean isPrime(double n) {
        if (n <= 1)
            return false;
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;
        return true;
    }
        
    private static String crypt(String mess, String key) {
        int messLen = mess.length();
        int keyLen = key.length();
        String temp = key;
        StringBuilder out = new StringBuilder();

        while (messLen > keyLen) {
            key = key + temp;
            keyLen = key.length();
        }
        char[] messC = mess.toCharArray();
        char[] keyC = key.toCharArray();
        
        for (int x = 0; x < mess.length(); x++) {
            if (keyC[x] == messC[x]) {
                out.append(0);
            } else {
                out.append(1);
            }
        }
        return out.toString();
    }

    private static String Stringtobinary(String in) {
        char[] c = in.toCharArray();
        StringBuilder out = new StringBuilder();
        for (char value : c) {
            StringBuilder bin = new StringBuilder(Integer.toBinaryString(value));
            int y = bin.length() % 8;
            while (y != 0) {
                bin.insert(0, 0);
                y = bin.length() % 8;
            }
            out.append(bin);
        }
        return out.toString();
    }

    private static String Binarytostring(String in) {
        StringBuilder out = new StringBuilder();
        String[] numbers = in.split("(?<=\\G.{8})");
        for (int x = 0; x <= numbers.length - 1; x++) {
            char c = (char) Integer.parseInt(numbers[x], 2);
            out.append(c);
        } 
        return out.toString();
    }
}