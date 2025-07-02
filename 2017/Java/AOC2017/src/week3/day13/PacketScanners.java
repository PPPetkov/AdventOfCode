package week3.day13;

import utils.InputParser;

import java.util.List;

public class PacketScanners {
    private int[] generateFirewall(List<String> input) {
        int[] firewall = new int[Integer.parseInt(input.getLast().split(": ")[0]) + 1];

        int depth = 0;
        for (String str : input) {
            String[] tokens = str.split(": ");
            int currentDepth = Integer.parseInt(tokens[0]);

            while (depth < currentDepth) {
                firewall[depth++] = 0;
            }

            firewall[depth++] = Integer.parseInt(tokens[1]);
        }

        return firewall;
    }

    public int partOne(String filename) {
        int[] firewall = generateFirewall(InputParser.parseInput(filename));
        int severity = 0;

        for (int depth = 0; depth < firewall.length; depth++) {
            if (depth % (2 * (firewall[depth] - 1)) == 0) {
                severity += depth * firewall[depth];
            }
        }

        return severity;
    }

    private int gotCaught(int[] firewall, int delay) {
        for (int depth = 0; depth < firewall.length; depth++) {
            if (firewall[depth] > 0 && (depth + delay) % (2 * (firewall[depth] - 1)) == 0) {
                return depth;
            }
        }

        return -1;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private int lcm(int a, int b) {
        return (a / gcd(a, b)) * b; // More efficient!
    }

    public int partTwo(String filename) {
        int[] firewall = generateFirewall(InputParser.parseInput(filename));
        int delay = 0;
        while (true) {
            System.out.println(delay);
            int catcher = gotCaught(firewall, delay);

            if (catcher == -1) {
                return delay;
            }

            delay++;
        }
    }
}
