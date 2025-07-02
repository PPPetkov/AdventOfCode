package week3.day18;

import utils.InputParser;

import java.util.Arrays;
import java.util.List;

public class GifForYourYard {
    private boolean[][] getLights(List<String> input) {
        boolean[][] lights = new boolean[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            String row = input.get(i);
            lights[i] = new boolean[row.length()];
            for (int j = 0; j < row.length(); j++) {
                lights[i][j] = row.charAt(j) == '#';
            }
        }

        return lights;
    }


    private int countNeighbours(boolean[][] lights, int x, int y) {
        int count = 0;

        for (int dx = -1; dx < 2; dx++) {
            for (int dy = -1; dy < 2; dy++) {
                if (dx != 0 || dy != 0) {
                    int x2 = x + dx;
                    int y2 = y + dy;

                    if (x2 >= 0 && y2 >= 0 && x2 < lights.length && y2 < lights[x2].length) {
                        if (lights[x2][y2]) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }

    private boolean[][] nextStep(boolean[][] lights) {
        boolean[][] result = new boolean[lights.length][lights[0].length];

        for (int i = 0; i < lights.length; i++) {
            for (int j = 0; j < lights[i].length; j++) {
                int litNeighbours = countNeighbours(lights, i, j);
                if (lights[i][j] && litNeighbours != 2 && litNeighbours != 3) {
                    result[i][j] = false;
                } else if (!lights[i][j] && litNeighbours == 3) {
                    result[i][j] = true;
                } else {
                    result[i][j] = lights[i][j];
                }
            }
        }

        return result;
    }

    private int countLitLights(boolean[][] lights) {
        int count = 0;
        for (boolean[] row : lights) {
            for (boolean light : row) {
                if (light) {
                    count++;
                }
            }
        }

        return count;
    }

    public int partOne(String filename) {
        boolean[][] lights = getLights(InputParser.parseInput(filename));

        for (int i = 0; i < 100; i++) {
            lights = nextStep(lights);
        }

        return countLitLights(lights);
    }

    private boolean isCorner(int x, int y, boolean[][] lights) {
        return (x == 0 && y == 0) ||
                (x == 0 && y == lights[0].length - 1) ||
                (x == lights.length - 1 && y == 0) ||
                (x == lights.length - 1 && y == lights[x].length - 1);
    }

    private boolean[][] nextStepTwo(boolean[][] lights) {
        boolean[][] result = new boolean[lights.length][lights[0].length];

        for (int i = 0; i < lights.length; i++) {
            for (int j = 0; j < lights[i].length; j++) {
                if (isCorner(i, j, lights)) {
                    result[i][j] = true;
                } else {
                    int litNeighbours = countNeighbours(lights, i, j);
                    if (lights[i][j] && litNeighbours != 2 && litNeighbours != 3) {
                        result[i][j] = false;
                    } else if (!lights[i][j] && litNeighbours == 3) {
                        result[i][j] = true;
                    } else {
                        result[i][j] = lights[i][j];
                    }
                }
            }
        }

        return result;
    }

    public int partTwo(String filename) {
        boolean[][] lights = getLights(InputParser.parseInput(filename));

        for (int i = 0; i < 100; i++) {
            lights = nextStepTwo(lights);
        }

        return countLitLights(lights);
    }
}
