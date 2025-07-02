package week3.day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WarehouseWoes {
    private static final int[][] DIRECTIONS = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1},
    };

    public static long partOne(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<StringBuffer> warehouse = parseWarehouseLayout(reader);
        moveRobotOne(warehouse, parseRobotMovements(reader));

        return calculateCoordinateSum(warehouse);
    }

    private static void moveRobotOne(List<StringBuffer> warehouse, List<String> movements) {
        int[] robot = findRobotLocation(warehouse);

        for (String moves : movements) {
            for (int i = 0; i < moves.length(); i++) {
                int[] direction = DIRECTIONS[getDirectionIndex(moves.charAt(i))];
                int x = robot[0] + direction[0];
                int y = robot[1] + direction[1];

                if (warehouse.get(x).charAt(y) == '.') {
                    warehouse.get(x).setCharAt(y, '@');
                    warehouse.get(robot[0]).setCharAt(robot[1], '.');
                    robot[0] = x;
                    robot[1] = y;
                }

                if (warehouse.get(x).charAt(y) == 'O') {
                    int lastX = x;
                    int lastY = y;
                    while (warehouse.get(lastX).charAt(lastY) == 'O') {
                        lastX += direction[0];
                        lastY += direction[1];
                    }
                    if (warehouse.get(lastX).charAt(lastY) == '.') {
                        warehouse.get(robot[0]).setCharAt(robot[1], '.');
                        warehouse.get(x).setCharAt(y, '@');
                        warehouse.get(lastX).setCharAt(lastY, 'O');
                        robot[0] = x;
                        robot[1] = y;
                    }
                }
            }
        }
    }

    private static void printWarehouse(List<StringBuffer> warehouse) {
        for (StringBuffer b : warehouse) {
            System.out.println(b);
        }
        System.out.println();
    }

    private static long calculateCoordinateSum(List<StringBuffer> warehouse) {
        long sum = 0;
        for (int i = 0; i < warehouse.size(); i++) {
            for (int j = 0; j < warehouse.getFirst().length(); j++) {
                if (warehouse.get(i).charAt(j) == 'O') {
                    sum += 100L * i + j;
                }
            }
        }

        return sum;
    }

    private static int[] findRobotLocation(List<StringBuffer> warehouse) {
        for (int i = 0; i < warehouse.size(); i++) {
            StringBuffer b = warehouse.get(i);
            int j = b.indexOf("@");

            if (j != -1) {
                return new int[]{i, j};
            }
        }

        return new int[] {0, 0};
    }

    private static int getDirectionIndex(char c) {
        return switch (c) {
            case '>' -> 0;
            case 'v' -> 1;
            case '^' -> 2;
            case '<' -> 3;
            default -> -1;
        };
    }

    private static List<StringBuffer> parseWarehouseLayout(BufferedReader reader) throws IOException {
        List<StringBuffer> layout = new ArrayList<>();
        while (true) {
            String line = reader.readLine();
            if (line.isEmpty()) {
                return layout;
            }
            layout.add(new StringBuffer(line));
        }
    }

    private static List<String> parseRobotMovements(BufferedReader reader) throws IOException {
        List<String> movements = new ArrayList<>();
        while (reader.ready()) {
            movements.add(reader.readLine());
        }
        return movements;
    }

    public static long partTwo(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<StringBuffer> warehouse = expandWarehouse(parseWarehouseLayout(reader));
        return moveRobotTwo(warehouse, parseRobotMovements(reader));

    }

    private static long moveRobotTwo(List<StringBuffer> warehouse, List<String> movements) {
        int[] robot = findRobotLocation(warehouse);
        for (String moves : movements) {
            for (int i = 0; i < moves.length(); i++) {
                char dir = moves.charAt(i);
                int[] direction = DIRECTIONS[getDirectionIndex(dir)];
                int x = robot[0] + direction[0];
                int y = robot[1] + direction[1];

                char currentSymbol = warehouse.get(x).charAt(y);
                if (currentSymbol == '.') {
                    warehouse.get(x).setCharAt(y, '@');
                    warehouse.get(robot[0]).setCharAt(robot[1], '.');
                    robot[0] = x;
                    robot[1] = y;
                }

                if (currentSymbol == '[' || currentSymbol == ']') {
                    if (dir == '>' || dir == '<') {
                        moveBoxSideways(warehouse, x, y, direction, robot);
                    } else {
                        if (currentSymbol == '[' && canPush(warehouse, y, y + 1, x, direction[0])) {
                            pushVertically(warehouse, y, y + 1, x, direction[0]);
                            warehouse.get(x).setCharAt(y, '@');
                            warehouse.get(robot[0]).setCharAt(y, '.');
                            robot[0] = x;
                        } else if (currentSymbol == ']' && canPush(warehouse, y - 1, y, x, direction[0])) {
                            pushVertically(warehouse, y - 1, y, x, direction[0]);
                            warehouse.get(x).setCharAt(y, '@');
                            warehouse.get(robot[0]).setCharAt(y, '.');
                            robot[0] = x;
                        }
                    }
                }
                //printWarehouse(warehouse);
            }
        }

        return calculateCoordinateSumTwo(warehouse);
    }

    private static void moveBoxSideways(List<StringBuffer> warehouse, int x, int y, int[] direction, int[] robot) {
        while (warehouse.get(x).charAt(y) == '[' || warehouse.get(x).charAt(y) == ']') {
            y += direction[1];
        }

        if (warehouse.get(x).charAt(y) != '#') {
            while (y != robot[1]) {
                warehouse.get(x).setCharAt(y, warehouse.get(x).charAt(y - direction[1]));
                y -= direction[1];
            }
            warehouse.get(x).setCharAt(y, '.');
            robot[1] = y + direction[1];
        }
    }

    private static long calculateCoordinateSumTwo(List<StringBuffer> warehouse) {
        long sum = 0;
        for (int i = 0; i < warehouse.size(); i++) {
            for (int j = 0; j < warehouse.getFirst().length(); j++) {
                if (warehouse.get(i).charAt(j) == '[') {
                    sum += 100L * i + j;
                }
            }
        }

        return sum;
    }

    private static List<StringBuffer> expandWarehouse(List<StringBuffer> warehouse) {
        List<StringBuffer> expanded = new ArrayList<>();
        for (StringBuffer buffer : warehouse) {
            StringBuffer line = new StringBuffer();
            for (int i = 0; i < buffer.length(); i++) {
                switch (buffer.charAt(i)) {
                    case '#' -> line.append("##");
                    case '.' -> line.append("..");
                    case 'O' -> line.append("[]");
                    case '@' -> line.append("@.");
                }
            }
            expanded.add(line);
        }

        return expanded;
    }

    private static void push(List<StringBuffer> warehouse, int left, int right, int row, int dir) {
        warehouse.get(row + dir).setCharAt(left, '[');
        warehouse.get(row + dir).setCharAt(right, ']');
        warehouse.get(row).setCharAt(left, '.');
        warehouse.get(row).setCharAt(right, '.');
    }

    private static boolean pushVertically(List<StringBuffer> warehouse, int left, int right, int row, int dir) {
        char nextLeft = warehouse.get(row + dir).charAt(left);
        char nextRight = warehouse.get(row + dir).charAt(right);

        if (nextLeft == '#' || nextRight == '#') {
            return false;
        }

        if (nextLeft == '.' && nextRight == '.') {
            push(warehouse, left, right, row, dir);
            return true;
        }

        if (nextLeft == '[' && nextRight == ']') {
            if (pushVertically(warehouse, left, right, row + dir, dir)) {
                push(warehouse, left, right, row, dir);
                return true;
            } else {
                return false;
            }
        }

        if (nextLeft == ']' && nextRight == '[') {
            if (pushVertically(warehouse, left - 1, left, row + dir, dir) &&
            pushVertically(warehouse, right, right + 1, row + dir, dir)) {
                push(warehouse, left, right, row, dir);
                return true;
            } else {
                return false;
            }
        } else if (nextLeft == ']') {
            if (pushVertically(warehouse, left - 1, left, row + dir, dir)) {
                push(warehouse, left, right, row, dir);
                return true;
            } else {
                return false;
            }
        } else if (nextRight == '[') {
            if (pushVertically(warehouse, right, right + 1, row + dir, dir)) {
                push(warehouse, left, right, row, dir);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private static boolean canPush(List<StringBuffer> warehouse, int left, int right, int row, int dir) {
        char nextLeft = warehouse.get(row + dir).charAt(left);
        char nextRight = warehouse.get(row + dir).charAt(right);

        if (nextLeft == '#' || nextRight == '#') {
            return false;
        }

        if (nextLeft == '.' && nextRight == '.') {
            return true;
        }

        if (nextLeft == '[' && nextRight == ']') {
            return canPush(warehouse, left, right, row + dir, dir);
        }

        if (nextLeft == ']' && nextRight == '[') {
            return canPush(warehouse, left - 1, left, row + dir, dir) && canPush(warehouse, right, right + 1, row + dir, dir);
        } else if (nextLeft == ']') {
            return canPush(warehouse, left - 1, left, row + dir, dir);
        } else if (nextRight == '[') {
            return canPush(warehouse, right, right + 1, row + dir, dir);
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(partTwo("resources/input.txt"));
    }

}
