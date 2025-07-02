package util;

public record DirectedLocation(int x, int y, int dirX, int dirY) {
    public DirectedLocation moveForward() {
        return new DirectedLocation(x + dirX, y + dirY, dirX, dirY);
    }

    public DirectedLocation turn(boolean counterClockwise) {
        int newDirX = 0;
        int newDirY = 0;

        if (dirX == 0 && dirY == 1) newDirX = 1;
        if (dirX == 1 && dirY == 0) newDirY = -1;
        if (dirX == 0 && dirY == -1) newDirX = -1;
        if (dirX == -1 && dirY == 0) newDirY = 1;

        if (counterClockwise) {
            newDirX *= -1;
            newDirY *= -1;
        }

        return new DirectedLocation(x, y, newDirX, newDirY);
    }

    public static DirectedLocation of(int[] coordinates) {
        return new DirectedLocation(coordinates[0], coordinates[1], 0, 1);
    }
}
