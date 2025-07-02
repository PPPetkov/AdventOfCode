package week2.day8;

public enum Comparison {
    EQUAL("=="),
    NOT_EQUAL("!="),
    LESS_THAN("<"),
    LESS_THAN_EQ("<="),
    GREATER_THAN(">"),
    GREATER_THAN_EQ(">=");

    private String symbol;

    Comparison(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Comparison fromString(String s) {
        return switch (s) {
            case "==" -> Comparison.EQUAL;
            case "!=" -> Comparison.NOT_EQUAL;
            case "<" -> Comparison.LESS_THAN;
            case "<=" -> Comparison.LESS_THAN_EQ;
            case ">" -> Comparison.GREATER_THAN;
            case ">=" -> Comparison.GREATER_THAN_EQ;
            default -> throw new IllegalArgumentException("String does not represent a valid comparison");
        };
    }
}
