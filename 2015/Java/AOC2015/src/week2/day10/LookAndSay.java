package week2.day10;

public class LookAndSay {
    private StringBuilder getNext(int remaining, StringBuilder prev) {
        if (remaining == 0) {
            return prev;
        }

        StringBuilder next = new StringBuilder();

        int count = 1;
        char current = prev.charAt(0);
        for (int i = 1; i < prev.length(); i++) {
            if (prev.charAt(i) == current) {
                count++;
            } else {
                next.append(count);
                next.append(current);
                current = prev.charAt(i);
                count = 1;
            }
        }

        next.append(count);
        next.append(current);
        //System.out.println(remaining);
        return getNext(remaining-1, next);
    }

    public int partOne(String input) {
        return getNext(40, new StringBuilder(input)).length();
    }

    public int partTwo(String input) {
        return getNext(50, new StringBuilder(input)).length();
    }
}
