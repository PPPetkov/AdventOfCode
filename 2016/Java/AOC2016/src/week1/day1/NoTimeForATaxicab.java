package week1.day1;

import utils.DirectedLocation;
import utils.Direction;
import utils.InputParser;
import utils.Location;

import java.util.HashSet;
import java.util.Set;

public class NoTimeForATaxicab {
    public int partOne(String filename) {
        String path = InputParser.parseInputLine(filename);
        DirectedLocation start = new DirectedLocation(0, 0, Direction.NORTH);
        String[] tokens = path.split(",\\s+");
        DirectedLocation end = start;

        for (String token : tokens) {
            end = token.charAt(0) == 'R' ? end.rotateRight() : end.rotateLeft();
            end = end.moveForward(Integer.parseInt(token.substring(1)));
        }

        return start.manhattanDistance(end);
    }

    private DirectedLocation findFirstLocationVisitedTwice(String path, DirectedLocation start) {
        Set<Location> visited = new HashSet<>();
        String[] tokens = path.split(",\\s+");
        DirectedLocation end = start;

        visited.add(Location.of(end));
        for (String token : tokens) {
            end = token.charAt(0) == 'R' ? end.rotateRight() : end.rotateLeft();
            int moves = Integer.parseInt(token.substring(1));

            while (moves-- > 0) {
                end = end.moveForward();
                if (!visited.add(Location.of(end))) {
                    return end;
                }
            }
        }

        return end;
    }

    public int partTwo(String filename) {
        String path = InputParser.parseInputLine(filename);
        DirectedLocation start = new DirectedLocation(0,0, Direction.NORTH);
        return start.manhattanDistance(findFirstLocationVisitedTwice(path, start));
    }
}
