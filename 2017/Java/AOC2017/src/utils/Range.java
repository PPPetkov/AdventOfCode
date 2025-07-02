package utils;

public record Range(long lowerBound, long upperBound) {
    public boolean isInRange(long n) {
        return n >= lowerBound && n <= upperBound;
    }

    public static Range of(String str) {
        String[] tokens = str.split("-");
        return new Range(Long.parseLong(tokens[0]), Long.parseLong(tokens[1]));
    }
}
