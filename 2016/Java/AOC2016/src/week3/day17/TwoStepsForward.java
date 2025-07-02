package week3.day17;

import utils.Hashes;
import java.util.ArrayDeque;
import java.util.Queue;

public class TwoStepsForward {
    private boolean isOpen(char c) {
        return "bcdef".indexOf(c) != -1;
    }

    public String partOne(String input) {
        Queue<Movement> queue = new ArrayDeque<>();
        queue.add(new Movement(0, 0, input));

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Movement current = queue.remove();

                if (current.x() == 3 && current.y() == 3) {
                    return current.path().substring(input.length());
                }

                String md5 = Hashes.getMD5(current.path());
                if (isOpen(md5.charAt(0)) && current.x() > 0) {
                    queue.add(new Movement(current.x() - 1, current.y(), current.path() + "U"));
                }
                if (isOpen(md5.charAt(1)) && current.x() < 3) {
                    queue.add(new Movement(current.x() + 1, current.y(), current.path() + "D"));
                }
                if (isOpen(md5.charAt(2)) && current.y() > 0) {
                    queue.add(new Movement(current.x(), current.y() - 1, current.path() + "L"));
                }
                if (isOpen(md5.charAt(3)) && current.y() < 3) {
                    queue.add(new Movement(current.x(), current.y() + 1, current.path() + "R"));
                }
            }
        }
        return "NO PATH FOUND";
    }

    public int partTwo(String input) {
        Queue<Movement> queue = new ArrayDeque<>();
        queue.add(new Movement(0, 0, input));
        int maxLen = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Movement current = queue.remove();

                if (current.x() == 3 && current.y() == 3) {
                    maxLen = current.path().length() - input.length();
                    continue;
                }

                String md5 = Hashes.getMD5(current.path());
                if (isOpen(md5.charAt(0)) && current.x() > 0) {
                    queue.add(new Movement(current.x() - 1, current.y(), current.path() + "U"));
                }
                if (isOpen(md5.charAt(1)) && current.x() < 3) {
                    queue.add(new Movement(current.x() + 1, current.y(), current.path() + "D"));
                }
                if (isOpen(md5.charAt(2)) && current.y() > 0) {
                    queue.add(new Movement(current.x(), current.y() - 1, current.path() + "L"));
                }
                if (isOpen(md5.charAt(3)) && current.y() < 3) {
                    queue.add(new Movement(current.x(), current.y() + 1, current.path() + "R"));
                }
            }
        }

        return maxLen;
    }
}
