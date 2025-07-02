package week2.day11;

import java.util.Set;

public class CorporatePolicy {
    private static final Set<Character> forbiddenLetters = Set.of('i', 'o', 'l');

    private void incrementPassword(StringBuilder password, int index) {
        if (index < 0) {
            return;
        }

        if(password.charAt(index) == 'z') {
            password.setCharAt(index--, 'a');
            incrementPassword(password, index);
        } else {
            password.setCharAt(index, (char) (password.charAt(index) + 1));
        }
    }

    private boolean containsIncreasingStraight(StringBuilder password) {
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i) == password.charAt(i+1) - 1 &&
                password.charAt(i+1) == password.charAt(i+2) - 1) {
                return true;
            }
        }

        return false;
    }

    private boolean containsForbiddenLetters(StringBuilder password) {
        for (int i = 0; i < password.length(); i++) {
            if (forbiddenLetters.contains(password.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    private boolean containsTwoPairs(StringBuilder password) {
        int count = 0;

        for (int i = 0; i < password.length() - 1; i++) {
            if (password.charAt(i) == password.charAt(i + 1)) {
                count++;
                i++;
            }
            if (count > 1) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidPassword(StringBuilder password) {
        return containsIncreasingStraight(password) && !containsForbiddenLetters(password) && containsTwoPairs(password);
    }

    private String findNextPassword(String input) {
        StringBuilder password = new StringBuilder(input);

        do {
            incrementPassword(password, password.length() - 1);
        } while (!isValidPassword(password));

        return password.toString();
    }

    public String partOne(String input) {
        return findNextPassword(input);
    }

    public String partTwo(String input) {
        return findNextPassword(findNextPassword(input));
    }
}
