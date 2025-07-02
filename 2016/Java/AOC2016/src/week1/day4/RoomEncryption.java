package week1.day4;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class RoomEncryption {
    private final String name;
    private final int sectorID;
    private final String checksum;
    private final Map<Character, Integer> histogram;
    private boolean isReal;

    public RoomEncryption(String name, int sectorID, String checksum) {
        this.name = name;
        this.sectorID = sectorID;
        this.checksum = checksum;
        histogram = new HashMap<>();
        isReal = false;
    }

    private void fillHistogram() {
        for (char c : name.toCharArray()) {
            if(c != '-') {
                histogram.putIfAbsent(c, 0);
                histogram.put(c, histogram.get(c)+1);
            }
        }
    }

    public boolean isRealRoom() {
        if (!histogram.isEmpty()) {
            return isReal;
        }

        fillHistogram();

        for (int i = 1; i < checksum.length() - 2; i++) {
            char first = checksum.charAt(i);
            char second = checksum.charAt(i+1);

            if (!histogram.containsKey(first) || !histogram.containsKey(second)) {
                return false;
            }

            if (histogram.get(first) < histogram.get(second)) {
                return false;
            }
            if (histogram.get(first).equals(histogram.get(second)) && first >= second) {
                return false;
            }
        }

        isReal = true;
        return true;
    }

    public static RoomEncryption of(String str) {
        int idStart = str.lastIndexOf('-') + 1;
        int checksumStart = str.indexOf('[');

        return new RoomEncryption(str.substring(0, idStart),
                Integer.parseInt(str.substring(idStart, checksumStart)),
                str.substring(checksumStart));
    }

    public int getSectorID() {
        return sectorID;
    }

    public String decryptName() {
        StringBuilder decrypted = new StringBuilder();

        for (char c : name.toCharArray()) {
            if (c == '-') {
                decrypted.append(' ');
            } else {
                c = (char) ('a' + (c + sectorID - 'a') % 26);
                decrypted.append(c);
            }
        }

        return decrypted.toString();
    }
}
