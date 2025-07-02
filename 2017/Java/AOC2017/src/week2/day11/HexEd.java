package week2.day11;

import utils.InputParser;
import utils.Location;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class HexEd {
    private Location getChildLocation(String[] directions) {
        int x = 0;
        int y = 0;

        for (String direction : directions) {
            switch (direction) {
                case "n": x-=2; break;
                case "ne": x--; y++; break;
                case "se": x++; y++; break;
                case "s": x+=2; break;
                case "sw": x++; y--; break;
                case "nw": x--; y--; break;
            }
        }

        return new Location(x, y);
    }

    public int partOne(String filename) {
        Location child = getChildLocation(InputParser.parseInputLine(filename).split(","));
        return  (Math.abs(child.x()) + Math.abs(child.y())) / 2;
    }

    public int partTwo(String filename) {
        String[] directions = InputParser.parseInputLine(filename).split(",");
        int x = 0;
        int y = 0;
        int maxDistance = 0;
        for (String direction : directions) {
            switch (direction) {
                case "n": x-=2; break;
                case "ne": x--; y++; break;
                case "se": x++; y++; break;
                case "s": x+=2; break;
                case "sw": x++; y--; break;
                case "nw": x--; y--; break;
            }

            maxDistance = Math.max(maxDistance, (Math.abs(x) + Math.abs(y)) / 2);
        }

        return maxDistance;
    }
}
