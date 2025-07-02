package week3.day20;

import utils.InputParser;
import utils.Range;

import java.util.List;

public class FirewallRules {
    private static final long MAX_ADDRESS = 4_294_967_295L;
    private static final long MIN_ADDRESS = 0;

    private long rangeUpperBound(long address, List<Range> blacklist) {
        for (Range range : blacklist) {
            if (range.isInRange(address)) {
                return range.upperBound();
            }
        }

        return -1;
    }

    public long partOne(String filename) {
        List<Range> blacklist = InputParser.parseInput(filename).stream().map(Range::of).toList();

        long n = MIN_ADDRESS;
        while (true) {
            long bound = rangeUpperBound(n, blacklist);
            if (bound == -1) {
                return n;
            } else {
                n = bound + 1;
            }
        }
    }

    public long partTwo(String filename) {
        List<Range> blacklist = InputParser.parseInput(filename).stream().map(Range::of).toList();

        long count = 0;
        long n = MIN_ADDRESS;
        while (n <= MAX_ADDRESS) {
            long bound = rangeUpperBound(n, blacklist);
            if (bound == -1) {
                count++;
                n++;
            } else {
                n = bound + 1;
            }
        }

        return count;
    }
}
