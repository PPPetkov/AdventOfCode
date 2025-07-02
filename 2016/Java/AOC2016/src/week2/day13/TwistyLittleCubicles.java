package week2.day13;

import utils.Direction;
import utils.Location;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class TwistyLittleCubicles {
    private final int favoriteNumber;

    public TwistyLittleCubicles(int favoriteNumber) {
        this.favoriteNumber = favoriteNumber;
    }

    private int bitSum(int n) {
        int count = 0;
        while (n > 0) {
            count += n % 2;
            n /= 2;
        }

        return count;
    }

    private boolean isWall(Location l) {
        if (l.x() < 0 || l.y() < 0) {
            return true;
        }

        int sum = l.x()*l.x() + 3 * l.x() + 2 * l.x() * l.y() + l.y() + l.y()*l.y() + favoriteNumber;
        return bitSum(sum) % 2 == 1;
    }

    public int partOne(Location start, Location goal) {
        Set<Location> visited = new HashSet<>();
        Queue<Location> queue = new ArrayDeque<>();
        queue.add(start);
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                size--;
                Location current = queue.poll();
                if (current == null) {
                    return -1;
                }
                if (current.equals(goal)) {
                    return steps;
                }

                if (!visited.contains(current)) {
                    visited.add(current);

                    if (isWall(current)) {
                        continue;
                    }

                    for (Direction dir : Direction.values()) {
                        queue.add(current.move(dir));
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    public int partTwo(Location start) {
        Set<Location> visited = new HashSet<>();
        Queue<Location> queue = new ArrayDeque<>();
        Set<Location> unique = new HashSet<>();
        queue.add(start);

        int steps = 0;
        while (steps <= 50) {
            int size = queue.size();
            while (size > 0) {
                size--;
                Location current = queue.poll();
                if (current == null) {
                    return -1;
                }

                if (!visited.contains(current)) {
                    visited.add(current);
                    if (!isWall(current)) {
                        unique.add(current);
                        for (Direction dir : Direction.values()) {
                            queue.add(current.move(dir));
                        }
                    }
                }
            }
            steps++;
        }
        return unique.size();
    }
}
