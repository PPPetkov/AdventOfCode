package week2.day7;

import utils.InputParser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InternetProtocolV7 {
    private boolean supportsTLS(String ip) {
        boolean hasABBA = false;
        boolean inHypernet = false;

        for (int i = 0; i < ip.length() - 3; i++) {
            char c = ip.charAt(i);
            if (c == '[' || c == ']') {
                inHypernet = !inHypernet;
                continue;
            }

            if (c == ip.charAt(i + 3) && ip.charAt(i + 1) == ip.charAt(i + 2) && c != ip.charAt(i + 1)) {
                if (inHypernet) {
                    return false;
                } else {
                    hasABBA = true;
                }
            }
        }

        return hasABBA;
    }

    public int partOne(String filename) {
        List<String> input = InputParser.parseInput(filename);
        int count = 0;

        for (String ip : input) {
            if (supportsTLS(ip)) {
                count++;
            }
        }

        return count;
    }

    private boolean supportsSSL(String ip) {
        Set<String> abas = new HashSet<>();
        Set<String> babs = new HashSet<>();
        boolean inHypernet = false;

        for (int i = 0; i < ip.length() - 2; i++) {
            char c1 = ip.charAt(i);
            char c2 = ip.charAt(i + 1);
            char c3 = ip.charAt(i + 2);

            if (c1 == '[' || c1 == ']') {
                inHypernet = !inHypernet;
                continue;
            }

            if (c2 == '[' || c3 == '[' || c2 == ']' || c3 == ']') {
                continue;
            }

            if (c1 == c3 && c1 != c2) {
                StringBuilder aba = new StringBuilder();

                if (inHypernet) {
                    aba.append(c2);
                    aba.append(c1);
                    aba.append(c2);

                    String abaStr = aba.toString();
                    babs.add(abaStr);
                    if (abas.contains(abaStr)) {
                        return true;
                    }
                } else {
                    aba.append(c1);
                    aba.append(c2);
                    aba.append(c1);

                    String abaStr = aba.toString();
                    abas.add(abaStr);
                    if (babs.contains(abaStr)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public int partTwo(String filename) {
        List<String> input = InputParser.parseInput(filename);
        int count = 0;

        for (String ip : input) {
            if (supportsSSL(ip)) {
                count++;
            }
        }

        return count;
    }
}
