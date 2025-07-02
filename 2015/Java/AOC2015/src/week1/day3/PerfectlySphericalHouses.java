package week1.day3;

import utils.InputParser;
import utils.Location2D;

import java.util.HashSet;
import java.util.Set;

public class PerfectlySphericalHouses {
    public int partOne(String filename) {
        char[] input = InputParser.parseInputLine(filename).toCharArray();
        Set<Location2D> visited = new HashSet<>();
        Location2D current = new Location2D(0, 0);
        visited.add(current);

        for (char c : input) {
            current = current.move(c);
            visited.add(current);
        }

        return visited.size();
    }

    public int partTwo(String filename) {
        char[] input = InputParser.parseInputLine(filename).toCharArray();
        Set<Location2D> visited = new HashSet<>();
        Location2D current = new Location2D(0, 0);
        Location2D currentRobot = new Location2D(0, 0);
        visited.add(current);

        for (int i = 0; i < input.length - 1; i += 2) {
            current = current.move(input[i]);
            currentRobot = currentRobot.move(input[i+1]);

            visited.add(current);
            visited.add(currentRobot);
        }

        return visited.size();
    }
}
