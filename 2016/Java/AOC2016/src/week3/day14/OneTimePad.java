package week3.day14;

import utils.Hashes;

import java.util.ArrayList;
import java.util.List;

public class OneTimePad {
    private char findFirstTriplet(String str) {
        for (int i = 0; i < str.length() - 2; i++) {
            if (str.charAt(i) == str.charAt(i+1) && str.charAt(i) == str.charAt(i+2)) {
                return str.charAt(i);
            }
        }

        return ' ';
    }

    private boolean containsFiveOf(char c, String str) {
        for (int i = 0; i < str.length() - 4; i++) {
            if (str.charAt(i) == c
                && str.charAt(i+1) == c
                && str.charAt(i+2) == c
                && str.charAt(i+3) == c
                && str.charAt(i+4) == c) {
                return true;
            }
        }

        return false;
    }

    public int partOne(String salt) {
        int index = 0;
        int currentKey = 0;

        String[] cache = new String[1000];

        for (int i = 0; i < 1000; i++) {
            cache[i] = Hashes.getMD5(salt + i);
        }

        while (currentKey < 64) {
            String hash = cache[0];

            for (int i = 0; i < cache.length - 1; i++) {
                cache[i] = cache[i+1];
            }
            cache[cache.length - 1] = Hashes.getMD5(salt + (index + 1000));

            char triplet = findFirstTriplet(hash);
            if (triplet != ' ' && isKey(triplet, cache)) {
                currentKey++;
            }
            index++;
        }

        return index - 1;
    }

    private String stretchKey(String key) {
        for (int i = 0; i < 2016; i++) {
            key = Hashes.getMD5(key);
        }

        return key;
    }

    private boolean isKey(char c, String[] cache) {
        for (String s : cache) {
            if (containsFiveOf(c, s)) {
                return true;
            }
        }

        return false;
    }

    public int partTwo(String salt) {
        int index = 0;
        int currentKey = 0;
        String[] cache = new String[1000];

        for (int i = 0; i < 1000; i++) {
            cache[i] = stretchKey(Hashes.getMD5(salt + i));
        }

        while (currentKey < 64) {
            String hash = cache[0];

            for (int i = 0; i < cache.length - 1; i++) {
                cache[i] = cache[i+1];
            }
            cache[cache.length - 1] = stretchKey(Hashes.getMD5(salt + (index + 1000)));

            char triplet = findFirstTriplet(hash);
            if (triplet != ' ' && isKey(triplet, cache)) {
                currentKey++;
            }
            index++;
        }

        return index - 1;
    }
}
