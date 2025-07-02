package week4.day22;

import utils.InputParser;

import java.util.List;

public class GridComputing {
    private boolean isViablePair(Node a, Node b) {
        return !a.equals(b) && a.used() > 0 && a.used() <= b.avail();
    }

    public int partOne(String filename) {
        List<Node> nodes = InputParser.parseInput(filename).stream().skip(2).map(Node::of).toList();

        int count = 0;
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if (isViablePair(nodes.get(i), nodes.get(j))) {
                    count++;
                }
            }
        }

        return count;
    }

    public int partTwo(String filename) {
        return 0;
    }
}
