package week4.day22;

import utils.DirectedLocation;
import utils.Direction;
import utils.InputParser;
import utils.Location;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SporificaVirus {
    private Set<Location> getInfectedNodes(List<String> grid) {
        Set<Location> infected = new HashSet<>();
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).length(); j++) {
                if (grid.get(i).charAt(j) == '#') {
                    infected.add(new Location(i, j));
                }
            }
        }
        return infected;
    }

    public int partOne(String filename, int bursts) {
        List<String> grid = InputParser.parseInput(filename);
        Set<Location> infected = getInfectedNodes(grid);

        DirectedLocation current = new DirectedLocation(grid.size() / 2, grid.getFirst().length() / 2, Direction.NORTH);
        int infections = 0;
        while (bursts-- > 0) {
            Location directionless = Location.of(current);

            current = infected.contains(directionless) ? current.rotateRight() : current.rotateLeft();
            if (infected.add(directionless)) {
                infections++;
            } else {
                infected.remove(directionless);
            }

            current = current.moveForward();
        }

        return infections;
    }

    public int partTwo(String filename, int bursts) {
        List<String> grid = InputParser.parseInput(filename);
        Set<Location> infected = getInfectedNodes(grid);
        Set<Location> flagged = new HashSet<>();
        Set<Location> weakened = new HashSet<>();

        DirectedLocation current = new DirectedLocation(grid.size() / 2, grid.getFirst().length() / 2, Direction.NORTH);
        int infections = 0;
        while (bursts-- > 0) {
            Location directionless = Location.of(current);

            if (infected.contains(directionless)) {
                current = current.rotateRight();
            } else if (flagged.contains(directionless)) {
                current = current.rotateRight().rotateRight();
            } else if (weakened.contains(directionless)) {
                current = current;
            } else {
                current = current.rotateLeft();
            }

            if (infected.contains(directionless)) {
                flagged.add(directionless);
                infected.remove(directionless);
            } else if (weakened.contains(directionless)) {
                infections++;
                weakened.remove(directionless);
                infected.add(directionless);
            } else if (flagged.contains(directionless)) {
                flagged.remove(directionless);
            } else {
                weakened.add(directionless);
            }

            current = current.moveForward();
        }

        return infections;
    }
}
