package se.js.aof2015.day4;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class App {
    public static void main(String[] args) {
        // 117946
 //       System.out.println(findLowestNumberForAdventCoin("ckczppom", 5));
        System.out.println(findLowestNumberForAdventCoin("ckczppom", 6));
    }

    public static int findLowestNumberForAdventCoin(String secret, int zeros) {
        int num = 0;
        String hash="";
        while(true){
            var regexp = "0{" + zeros + "}.*";
            if (!(!hash.matches(regexp) && num < 5_000_000)) break;
            hash = hashing(secret + (++num));
        }
        return num;
    }
    static String hashing(String input){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            return toHex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    static String toHex(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        return String.format("%0" + (bytes.length << 1) + "X", bi);
    }

}
