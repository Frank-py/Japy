//package Java;
package Japy.src.Java;

import java.math.*;
import java.util.*;

public class encry {
    public static int p;
    public static int g;
    public static int a;
    public static BigInteger A;

    public String[] newkey(String[] in) {
        Random randy = new Random();

        if (in.length == 0) {
            // Neu erstellte p und g
            do {
                p = randy.nextInt(1000000000);
            } while (!isPrime(p) | p < 1000000);
            g = randy.nextInt(p - 1);

        } else {
            p = Integer.parseInt(in[0]);
            g = Integer.parseInt(in[1]);
        }

        BigInteger gg = BigInteger.valueOf(g);
        BigInteger pp = BigInteger.valueOf(p);

        a = randy.nextInt(1000000000);
        BigInteger rando = BigInteger.valueOf(a);
        BigInteger A = gg.modPow(rando, pp);

        String[] numbers = { pp.toString(10), gg.toString(10), A.toString(10) };

        return numbers;
    }

    public String endnewkey(String[] in) {
        String key = "";

        if (in.length == 3) {
            key = new BigInteger(in[2]).modPow(new BigInteger(in[1]), new BigInteger(in[0])).toString(10);

        } else {

        }

        return key;
    }

    public static String encryption(String in, String key) {
        int keyInt = Integer.parseInt(key);
        key = Integer.toBinaryString(keyInt);

        in = Stringtobinary(in);
        String res = crypt(in, key);
        return res;
    }

    public static String decryption(String in, String key) {
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