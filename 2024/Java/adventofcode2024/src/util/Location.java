package util;

public record Location(int x, int y) {
    public static Location of(DirectedLocation l) {
        return new Location(l.x(), l.y());
    }

    public int getManhattanDistance(Location other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y);
    }
}
