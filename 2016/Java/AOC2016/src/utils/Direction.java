package utils;

public enum Direction {
    NORTH(-1,0),
    SOUTH(1,0),
    EAST(0,1),
    WEST(0,-1);

    public final int dx;
    public final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
