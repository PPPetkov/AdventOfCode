package week1.day2;

public class Present {
    private final int length;
    private final int width;
    private final int height;

    public Present(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public static Present of(String dimensions) {
        String[] tokens = dimensions.split("x");

        return new Present(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
    }

    public int calculateRequiredPaper() {
        return calculateSurfaceArea() + getSmallestSideArea();
    }

    private int getSmallestSideArea() {
        return Math.min(length * width, Math.min(width * height, length * height));
    }

    private int calculateSurfaceArea() {
        return 2 * length * width + 2 * width * height + 2 * length * height;
    }

    public int calculateRequiredRibbon() {
        return calculateVolume() + getSmallestSidePerimeter();
    }

    private int getSmallestSidePerimeter() {
        return Math.min(2 * (length + width),
                Math.min(2 * (length + height),
                        2 * (height + width)));
    }

    private int calculateVolume() {
        return width * height * length;
    }
}
