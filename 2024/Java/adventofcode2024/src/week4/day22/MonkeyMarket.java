package week4.day22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MonkeyMarket {
    private static final long PRUNE_VALUE = 16_777_216;

    public static long partOne(String filename) throws IOException {
        long sum = 0;

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while (reader.ready()) {
            sum += generateNthSecretNumber(Long.parseLong(reader.readLine()), 2000);
        }

        return sum;
    }

    private static long generateNthSecretNumber(long val, int n) {
        for (int i = 0; i < n; i++) {
            val = getNextSecretNumber(val);
        }

        return val;
    }

    private static long getNextSecretNumber(long val) {
        val = prune(mix(val, val * 64));
        val = prune(mix(val, val / 32));

        return prune(mix(val, val * 2048));
    }

    private static long mix(long n, long m) {
        return n ^ m;
    }

    private static long prune(long number) {
        return number % PRUNE_VALUE;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(partOne("resources/input.txt"));
    }
}
