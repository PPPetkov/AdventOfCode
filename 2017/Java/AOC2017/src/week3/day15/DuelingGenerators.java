package week3.day15;

import utils.InputParser;
import java.util.List;
public class DuelingGenerators {
    public int partOne(String filename) {
        long factorA = 16807;
        long factorB = 48271;
        long divisor = 2_147_483_647;

        List<String> init = InputParser.parseInput(filename);
        long a = Integer.parseInt(init.getFirst().split("\\s+")[4]);
        long b = Integer.parseInt(init.getLast().split("\\s+")[4]);

        int n = 40_000_000;
        int count = 0;

        while (n-- > 0) {
            if ((a & 0xffff) == (b & 0xffff)) {
                count++;
            }
            a = (a * factorA) % divisor;
            b = (b * factorB) % divisor;
        }

        return count;
    }

    public int partTwo(String filename) {
        long factorA = 16807;
        long factorB = 48271;
        long divisor = 2_147_483_647;

        List<String> init = InputParser.parseInput(filename);
        long a = Integer.parseInt(init.getFirst().split("\\s+")[4]);
        long b = Integer.parseInt(init.getLast().split("\\s+")[4]);

        int n = 5_000_000;
        int count = 0;

        while (n-- > 0) {
            if ((a & 0xffff) == (b & 0xffff)) {
                count++;
            }

            do {
                a = (a * factorA) % divisor;
            } while (a % 4 != 0);

            do {
                b = (b * factorB) % divisor;
            } while (b % 8 != 0);
        }

        return count;
    }
}
