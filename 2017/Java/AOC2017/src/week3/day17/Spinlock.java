package week3.day17;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Spinlock {
    public int partOne(int step) {
        List<Integer> lock = new ArrayList<>();
        lock.add(0);

        int n = 1;
        int pos = 1;

        while (n <= 2017) {
            lock.add(pos, n++);
            if (n != 2018) {
                pos = (pos + step) % lock.size() + 1;
            }
        }

        return lock.get(pos + 1);
    }

    public int partTwo(int step) {
        int n = 1;
        int pos = 1;
        int x = 0;
        while (n <= 50_000_000) {
            System.out.println(n);
            if (pos == 1) {
                x = n;
            }
            n++;
            pos = (pos + step) % n + 1;
        }

        return x;
    }
}
