package week3.day19;

public class AnElephantNamedJoseph {
    private int findNextElf(boolean[] elves, int current) {
        while (elves[current]) {
            current++;
            if (current == elves.length) {
                current = 0;
            }
        }

        return current;
    }

    public int partOne(int totalElves) {
        boolean[] elves = new boolean[totalElves];

        int current = 0;
        while (true) {
            int next = findNextElf(elves, (current + 1) % elves.length);
            if (next == current) {
                return current + 1;
            }
            elves[next] = true;

            current = findNextElf(elves, next);
        }
    }

    public int partTwo(int totalElves) {
        return 0;
    }
}
