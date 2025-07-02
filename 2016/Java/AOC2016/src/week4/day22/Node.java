package week4.day22;

public record Node (String name, int size, int used, int avail, int pct){
    public static Node of(String str) {
        String[] tokens = str.trim().split("\\s+");
        String name = tokens[0].substring(tokens[0].lastIndexOf('/') + 1);
        int size = Integer.parseInt(tokens[1].substring(0, tokens[1].length() - 1));
        int used = Integer.parseInt(tokens[2].substring(0, tokens[2].length() - 1));
        int avail = Integer.parseInt(tokens[3].substring(0, tokens[3].length() - 1));
        int pct = Integer.parseInt(tokens[4].substring(0, tokens[4].length() - 1));

        return new Node(name, size, used, avail, pct);
    }
}
