//package Java;
package Japy.src.Java;

import java.math.*;
import java.util.*;

public class encry {
    public static int p;
    public static int g;
    public static int a;
    public static BigInteger A;

    public String[] newkey() {
        Random randy = new Random();
        
        do {
            p = randy.nextInt(1000000000);
        } while (!isPrime(p) | p < 1000000);
        g = randy.nextInt(p - 1);

        BigInteger gg = BigInteger.valueOf(g);
        BigInteger pp = BigInteger.valueOf(p);

        a = randy.nextInt(1000000000);
        BigInteger rando = BigInteger.valueOf(a);
        BigInteger A = gg.modPow(rando, pp);
        
        String[] numbers = {pp.toString(10), gg.toString(10), A.toString(10)};

        return numbers;
    }

    public String endnewkey(String[] in){
        String key = "";

        if(in.length == 1){

        } else{

        }

        return key;
    }

    static boolean isPrime(double n) {
        if (n <= 1)
            return false;
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;
        return true;
    }
}