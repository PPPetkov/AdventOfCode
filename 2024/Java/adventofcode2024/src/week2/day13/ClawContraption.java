package week2.day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ClawContraption {
    private static final int PRICE_A = 3;
    private static final int PRICE_B = 1;
    private static final int MAX_BUTTON_PRESSES = 100;
    private static final long VALUE_TO_ADD = 10000000000000L;

    public static long partOne(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        long totalTokens = 0;

        while (reader.ready()) {
            totalTokens += findFewestTokens(
                    getButtonValues(reader.readLine()),
                    getButtonValues(reader.readLine()),
                    getPrizeLocation(reader.readLine())
            );
            reader.readLine();
        }

        return totalTokens;
    }

    private static long findFewestTokens(long[] A, long[] B, long[] prize) {
        long divisor = A[0] * B[1] - A[1] * B[0];
        long x = prize[0] * B[1] - B[0] * prize[1];
        long y = A[0] * prize[1] - prize[0] * A[1];

        if (x % divisor == 0 && y % divisor == 0) {
            long aPressCount = x / divisor;
            long bPressCount = y / divisor;

            return aPressCount * PRICE_A + bPressCount * PRICE_B;
        }

        return 0;
    }

    private static long[] getButtonValues(String buttonData) {
        String[] tokens = buttonData.split("[+,\\s]");
        return new long[]{Long.parseLong(tokens[3]), Long.parseLong(tokens[6])};
    }

    private static long[] getPrizeLocation(String prizeCoordinates) {
        String[] tokens = prizeCoordinates.split("[\\s=,]");
        return new long[]{Long.parseLong(tokens[2]), Long.parseLong(tokens[5])};
    }

    public static long partTwo(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        long totalTokens = 0;

        while (reader.ready()) {
            totalTokens += findFewestTokens(
                    getButtonValues(reader.readLine()),
                    getButtonValues(reader.readLine()),
                    getPrizeLocation2(reader.readLine())
            );
            reader.readLine();
        }

        return totalTokens;
    }

    private static long[] getPrizeLocation2(String prizeCoordinates) {
        String[] tokens = prizeCoordinates.split("[\\s=,]");
        return new long[]{Long.parseLong(tokens[2]) + VALUE_TO_ADD, Long.parseLong(tokens[5]) + VALUE_TO_ADD};
    }

    public static void main(String[] args) throws IOException {
        System.out.println(partTwo("resources/input.txt"));
    }
}
