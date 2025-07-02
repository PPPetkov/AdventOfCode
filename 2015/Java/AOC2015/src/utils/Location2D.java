package utils;

public record Location2D(int x, int y){
    public Location2D move(char dir) {
        return switch (dir) {
            case '^' -> new Location2D(x + 1, y);
            case 'v' -> new Location2D(x - 1, y);
            case '>' -> new Location2D(x, y + 1);
            case '<' -> new Location2D(x, y - 1);
            default -> throw new IllegalArgumentException("invalid movement direction");
        };
    }

    public static Location2D of(String str) {
        String[] tokens = str.split(",");

        return new Location2D(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
    }
}
