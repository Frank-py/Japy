//package Japy.src.Java.examplecodes;
package Java.examplecodes;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class encryption {
  public static void main(String[] args) {
    Scanner scanny = new Scanner(System.in);
    Random randy = new Random();
    String key = "";
    int ran;
  
    boolean create = scanny.nextBoolean();
    if (create) {
    
      int g = scanny.nextInt();
    
      int p = scanny.nextInt();
      if (p == 0) {
        do {
          p = randy.nextInt(1000000000);
        } while (!isPrime(p) | p < 1000000);
      }
      if (g == 0) {
        g = randy.nextInt(p - 1);
      }
      BigInteger gg = BigInteger.valueOf(g);
      BigInteger pp = BigInteger.valueOf(p);
      do {
        ran = randy.nextInt(1000000000);
      } while (ran <= 1);
      BigInteger rando = BigInteger.valueOf(ran);
      BigInteger A = gg.modPow(rando, pp);
    
    
      int B = scanny.nextInt();
      BigInteger BB = BigInteger.valueOf(B);
      BigInteger key2 = BB.modPow(rando, pp);
      key = key2.toString(10);
    
    } else {
    
      key = scanny.next();
    }

  
    boolean crypt = scanny.nextBoolean();
    
    Scanner scanny2 = new Scanner(System.in);
    int keyInt = Integer.parseInt(key);
    key = Integer.toBinaryString(keyInt);
  
    String mess = scanny2.nextLine();
    String res = "";
    scanny.close();
    scanny2.close();

    if (crypt) {
      mess = crypt(mess, key);
      res = Binarytostring(mess);
    } else {
      mess = Stringtobinary(mess);
      res = crypt(mess, key);
    }
  
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

  static boolean isPrime(double n) {
    if (n <= 1)
      return false;
    for (int i = 2; i < n; i++)
      if (n % i == 0)
        return false;
    return true;
  }
}