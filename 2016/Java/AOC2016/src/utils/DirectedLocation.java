package utils;

import java.util.Objects;

public class DirectedLocation {
    private final int x;
    private final int y;
    private final Direction dir;

    public DirectedLocation(int x, int y) {
        this(x, y, Direction.NORTH);
    }

    public DirectedLocation(int x, int y, Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public DirectedLocation rotateLeft() {
        Direction rotated = dir;
        switch (dir) {
            case NORTH -> rotated = Direction.WEST;
            case WEST  -> rotated = Direction.SOUTH;
            case SOUTH -> rotated = Direction.EAST;
            case EAST  -> rotated = Direction.NORTH;
        }
        return new DirectedLocation(x, y, rotated);
    }

    public DirectedLocation rotateRight() {
        Direction rotated = dir;
        switch (dir) {
            case NORTH -> rotated = Direction.EAST;
            case EAST  -> rotated = Direction.SOUTH;
            case SOUTH -> rotated = Direction.WEST;
            case WEST  -> rotated = Direction.NORTH;
        }
        return new DirectedLocation(x, y, rotated);
    }

    public DirectedLocation moveForward() {
        return new DirectedLocation(x + dir.dx, y + dir.dy, dir);
    }

    public DirectedLocation moveForward(int dist) {
        return new DirectedLocation(x + dir.dx * dist, y + dir.dy * dist, dir);
    }

    public int manhattanDistance(DirectedLocation other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y);
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public Direction dir() {
        return dir;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        DirectedLocation that = (DirectedLocation) o;
        return x == that.x && y == that.y && dir == that.dir;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + Objects.hashCode(dir);
        return result;
    }
}
