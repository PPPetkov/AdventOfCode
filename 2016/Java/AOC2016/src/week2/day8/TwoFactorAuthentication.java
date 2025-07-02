package week2.day8;

import utils.InputParser;

import java.util.Arrays;
import java.util.List;

public class TwoFactorAuthentication {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 6;

    private char[][] getScreen() {
        char[][] screen = new char[HEIGHT][WIDTH];

        for (int x = 0; x < HEIGHT; x++) {
            for (int y = 0; y < WIDTH; y++) {
                screen[x][y] = '.';
            }
        }

        return screen;
    }

    private void rect(int a, int b, char[][] screen) {
        for (int x = 0; x < b; x++) {
            for (int y = 0; y < a; y++) {
                screen[x][y] = '#';
            }
        }
    }

    private void rotateRow(int x, int b, char[][] screen) {
        char[] row = new char[WIDTH];

        for (int y = 0; y < WIDTH; y++) {
            row[(y + b) % WIDTH] = screen[x][y];
        }

        System.arraycopy(row, 0, screen[x], 0, WIDTH);
    }

    private void rotateColumn(int y, int b, char[][] screen) {
        char[] col = new char[HEIGHT];

        for (int x = 0; x < HEIGHT; x++) {
            col[(x + b) % HEIGHT] = screen[x][y];
        }

        for (int x = 0; x < HEIGHT; x++) {
            screen[x][y] = col[x];
        }
    }

    private void execute(String command, char[][] screen) {
        String[] tokens = command.split("\\s+");

        if (tokens[0].equals("rect")) {
            String[] dimensions = tokens[1].split("x");
            rect(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]), screen);
        } else {
            int a = Integer.parseInt(tokens[2].substring(tokens[2].indexOf('=') + 1));
            int b = Integer.parseInt(tokens[4]);

            if (tokens[1].equals("row")) {
                rotateRow(a, b, screen);
            } else {
                rotateColumn(a, b, screen);
            }
        }
    }

    public int partOne(String filename) {
        List<String> commands = InputParser.parseInput(filename);
        char[][] screen = getScreen();

        for (String command : commands) {
            execute(command, screen);
        }

        int count = 0;
        for (int x = 0; x < HEIGHT; x++) {
            for (int y = 0; y < WIDTH; y++) {
                if (screen[x][y] == '#') {
                    count++;
                }
            }
        }

        return count;
    }

    public int partTwo(String filename) {
        List<String> commands = InputParser.parseInput(filename);
        char[][] screen = getScreen();

        for (String command : commands) {
            execute(command, screen);
        }

        System.out.println(Arrays.deepToString(screen));
        return 0;
    }
}
