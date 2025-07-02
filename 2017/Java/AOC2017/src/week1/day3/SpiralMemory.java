package week1.day3;

import utils.Direction;

import java.util.Arrays;

public class SpiralMemory {
    private void printMemory(int[][] memory) {
        for (int[] line : memory) {
            System.out.println(Arrays.toString(line));
        }
    }

    private int getSize(int cell) {
        int size = 1;

        while (size * size < cell) {
            size++;
        }

        return size;
    }

    public int partOne(int cell) {
        int size = getSize(cell);
        int current = size * size;
        boolean isEven = size % 2 == 0;
        int x = isEven ? 0 : size - 1;
        int y = isEven ? 0 : size - 1;
        int centerX = size / 2;
        int centerY = isEven ? centerX - 1 : centerX;
        Direction dir = isEven ? Direction.EAST : Direction.WEST;

        while (current > cell) {
            switch (dir) {
                case NORTH -> x--;
                case SOUTH -> x++;
                case EAST -> y++;
                case WEST -> y--;
            }
            current--;
            if (x == 0 && dir == Direction.NORTH) {
                dir = Direction.EAST;
            } else if (x == size - 1 && dir == Direction.SOUTH) {
                dir = Direction.WEST;
            } else if (y == 0 && dir == Direction.WEST) {
                dir = Direction.NORTH;
            } else if (y == size - 1 && dir == Direction.EAST) {
                dir = Direction.SOUTH;
            }
        }

        return Math.abs(x - centerX) + Math.abs(y - centerY);
    }

    public int partTwo(int input) {
        int size = getSize(input);
        int[][] memory = new int[size][size];
        int x = size / 2;
        int y = size % 2 == 0 ? x - 1 : x;

        int cellNumber = 0;
        Direction dir = Direction.EAST;
        y--;
        int moves = 0;
        for (int i = 1; i <= size; i++) {
            while (cellNumber < i * i) {
                switch (dir) {
                    case NORTH -> x--;
                    case SOUTH -> x++;
                    case EAST -> y++;
                    case WEST -> y--;
                }
                moves++;
                cellNumber++;
                int val = cellNumber == 1 ? 1 : 0;
                for (int dx = -1; dx < 2; dx++) {
                    for (int dy = -1; dy < 2; dy++) {
                        int x2 = x + dx;
                        int y2 = y + dy;
                        if (x2 >= 0 && y2 >= 0 && x2 < size && y2 < size) {
                            val += memory[x2][y2];
                        }
                    }
                }
                if (val > input) {
                    return val;
                }
                memory[x][y] = val;
                if (moves == i && cellNumber != i * i) {
                    dir = switch (dir) {
                        case NORTH -> Direction.WEST;
                        case SOUTH -> Direction.EAST;
                        case EAST -> Direction.NORTH;
                        case WEST -> Direction.SOUTH;
                    };
                    moves = 1;
                }
            }
        }

        return 0;
    }
}
