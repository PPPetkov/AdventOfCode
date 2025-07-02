package utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashes {
    public static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(str.getBytes());
            BigInteger no = new BigInteger(1, digest);
            return String.format("%032x", no);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Not a valid algorithm", e);
        }
    }
}
