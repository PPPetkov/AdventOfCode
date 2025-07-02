package utils;

public record Location(int x, int y) {
    public static Location of(DirectedLocation location) {
        return new Location(location.x(), location.y());
    }

    public Location move(Direction dir) {
        return new Location(x + dir.dx, y + dir.dy);
    }
}
