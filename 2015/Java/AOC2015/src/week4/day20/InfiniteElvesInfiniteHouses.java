package week4.day20;

public class InfiniteElvesInfiniteHouses {
    public int partOne(int goal) {
        int size = goal / 10;
        int[] houses = new int[size + 1];

        for (int i = 1; i <= size; i++) {
            for (int j = i; j <= size; j += i) {
                houses[j] += 10 * i;
            }
        }

        for (int i = 0; i <= size; i++) {
            if (houses[i] >= goal) {
                return i;
            }
        }

        return 0;
    }

    public int partTwo(int goal) {
        int size = goal / 11;
        int[] houses = new int[size + 1];

        for (int i = 1; i <= size; i++) {
            int j = i;
            for (int k = 0; k < 50 && j <= size; k++) {
                houses[j] += i * 11;
                j += i;
            }
        }

        for (int i = 1; i <= size; i++) {
            if (houses[i] >= goal) {
                return i;
            }
        }

        return 0;
    }
}
