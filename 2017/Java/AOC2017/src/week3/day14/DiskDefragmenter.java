package week3.day14;

import utils.Location;
import week2.day10.KnotHash;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class DiskDefragmenter {
    private String getBinaryRepresentation(char hexDigit) {
        return switch (hexDigit) {
            case '0' -> "0000";
            case '1' -> "0001";
            case '2' -> "0010";
            case '3' -> "0011";
            case '4' -> "0100";
            case '5' -> "0101";
            case '6' -> "0110";
            case '7' -> "0111";
            case '8' -> "1000";
            case '9' -> "1001";
            case 'a' -> "1010";
            case 'b' -> "1011";
            case 'c' -> "1100";
            case 'd' -> "1101";
            case 'e' -> "1110";
            case 'f' -> "1111";
            default -> "";
        };
    }

    public int partOne(String input) {
        boolean[][] disk = getDisk(input);
        int usedSquares = 0;
        for (boolean[] row : disk) {
            for (boolean space : row) {
                if (space) {
                    usedSquares++;
                }
            }
        }

        return usedSquares;
    }

    private boolean[] getRow(String row) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < row.length(); i++) {
            builder.append(getBinaryRepresentation(row.charAt(i)));
        }

        boolean[] result = new boolean[builder.length()];
        for (int i = 0; i < builder.length(); i++) {
            result[i] = builder.charAt(i) == '1';
        }
        return result;
    }

    private boolean[][] getDisk(String input) {
        boolean[][] disk = new boolean[128][];
        KnotHash knotHash = new KnotHash();
        for (int i = 0; i < 128; i++) {
            disk[i] = getRow(knotHash.getKnotHash(input + '-' + i));
        }

        return disk;
    }

    private boolean inBounds(Location location) {
        return location.x() >= 0 && location.y() >= 0 && location.x() < 128 && location.y() < 128;
    }

    private void addRegion(Location start, Set<Location> visited, boolean[][] disk) {
        Queue<Location> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Location current = queue.remove();

                if (visited.contains(current) || !inBounds(current) || !disk[current.x()][current.y()]) {
                    continue;
                }
                visited.add(current);
                queue.add(new Location(current.x() + 1, current.y()));
                queue.add(new Location(current.x() - 1, current.y()));
                queue.add(new Location(current.x(), current.y() + 1));
                queue.add(new Location(current.x(), current.y() - 1));
            }
        }
    }

    public int partTwo(String input) {
        boolean[][] disk = getDisk(input);
        Set<Location> visited = new HashSet<>();
        int regions = 0;

        for (int x = 0; x < disk.length; x++) {
            for (int y = 0; y < disk[x].length; y++) {
                Location start = new Location(x, y);
                if (visited.contains(start) || !disk[x][y]) {
                    continue;
                }
                regions++;
                addRegion(start, visited, disk);
            }
        }

        return regions;
    }
}
