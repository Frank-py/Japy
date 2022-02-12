//package Java;
package Java.examplecodes;

import java.math.*;
import java.util.*;

public class encryption {
  public static void main(String[] args) {
    Scanner scanny = new Scanner(System.in);
    Random randy = new Random();
    String key = "";
    int ran;
    System.out.println("Do you want to create key for \"Diffie-Hellman-Schlüsselaustauschverfahren\"?");
    boolean create = scanny.nextBoolean();
    if (create) {
      System.out.println("Enter g or enter 0 to generate g");
      int g = scanny.nextInt();
      System.out.println("Enter the primenumber p or 0 to generate p");
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
      System.out.println("\n g = " + g + "\n p = " + p + "\n A = " + A + "\n a = " + ran);
      System.out.println("Pass A to your Friend and enter your friends Number B");
      int B = scanny.nextInt();
      BigInteger BB = BigInteger.valueOf(B);
      BigInteger key2 = BB.modPow(rando, pp);
      key = key2.toString(10);
      System.out.println("Your private key is: " + key);
    } else {
      System.out.println("Enter crypt key:");
      key = scanny.next();
    }

    System.out.println("type true to decrypt (entschlüsseln), type false to encrypt (verschlüsseln)");
    boolean crypt = scanny.nextBoolean();
    scanny.close();
    Scanner scanny2 = new Scanner(System.in);
    int keyInt = Integer.parseInt(key);
    key = Integer.toBinaryString(keyInt);
    System.out.println("Enter Message:");
    String mess = scanny2.nextLine();
    String res = "";

    if (crypt) {
      mess = crypt(mess, key);
      res = Binarytostring(mess);
    } else {
      mess = Stringtobinary(mess);
      res = crypt(mess, key);
    }
    System.out.println(res);
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