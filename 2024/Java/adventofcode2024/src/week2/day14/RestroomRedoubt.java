package week2.day14;

import util.Location;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RestroomRedoubt {
    private static final int WIDTH = 101;
    private static final int HEIGHT = 103;

    public static int partOne(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        int seconds = 100;
        int[] quadrants = new int[4];
        while (reader.ready()) {
            String[] tokens = reader.readLine().split("[=,\\s]");
            System.out.println(Arrays.toString(tokens));
            int px = Integer.parseInt(tokens[1]);
            int py = Integer.parseInt(tokens[2]);

            px += seconds * Integer.parseInt(tokens[4]);
            px %= WIDTH;
            if (px < 0) px += WIDTH;

            py += seconds * Integer.parseInt(tokens[5]);
            py %= HEIGHT;
            if (py < 0) py += HEIGHT;

            int quadrant = findQuadrant(px, py);
            if (quadrant != -1) {
                quadrants[quadrant]++;
            }
        }

        int safetyScore = 1;
        for (int robotCount : quadrants) {
            safetyScore *= robotCount;
        }

        return safetyScore;
    }

    private static int findQuadrant(int x, int y) {
        int midX = WIDTH / 2;
        int midY = HEIGHT / 2;

        if (x < midX && y < midY) return 0;
        if (x > midX && y < midY) return 1;
        if (x < midX && y > midY) return 2;
        if (x > midX && y > midY) return 3;

        return -1;
    }

    public static int partTwo(String filename) throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            List<Location> locations = new ArrayList<>();
            List<Location> velocities = new ArrayList<>();

            while (reader.ready()) {
                String[] tokens = reader.readLine().split("[=,\\s]");
                locations.add(new Location(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
                velocities.add(new Location(Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5])));
            }

            int seconds = 1;
            while (true) {
                for (int i = 0; i < locations.size(); i++) {
                    Location r = locations.get(i);
                    Location v = velocities.get(i);
                    int x = r.x() + v.x();
                    x %= WIDTH;
                    if (x < 0) x += WIDTH;

                    int y = r.y() + v.y();
                    y %= HEIGHT;
                    if (y < 0) y += HEIGHT;

                    locations.set(i, new Location(x, y));
                }

                HashSet<Location> robots = new HashSet<>(locations);
                if (robots.size() == locations.size()) {
                    printTree(robots);
                    return seconds;
                }
                seconds++;
            }

    }

    private static void printTree(Set<Location> robots) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (robots.contains(new Location(j, i))) {
                    System.out.print('+');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(partTwo("resources/input.txt"));
    }
}
