package week3.day16;

import utils.InputParser;

import java.util.Arrays;

public class PermutationPromenade {
    private void spin(int count, char[] programs) {
        char[] temp = new char[programs.length];
        for (int i = 0; i < programs.length; i++) {
            temp[(i + count) % programs.length] = programs[i];
        }

        System.arraycopy(temp, 0, programs, 0, programs.length);
    }

    private void exchange(String move, char[] programs) {
        int index = move.indexOf('/');
        int a = Integer.parseInt(move.substring(0, index));
        int b = Integer.parseInt(move.substring(index + 1));

        char temp = programs[a];
        programs[a] = programs[b];
        programs[b] = temp;
    }

    private void partner(String move, char[] programs) {
        char a = move.charAt(0);
        char b = move.charAt(2);
        int indexA = -1;
        int indexB = -1;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i] == a) {
                indexA = i;
            }
            if (programs[i] == b) {
                indexB = i;
            }
            if (indexA != -1 && indexB != -1) {
                break;
            }
        }

        char temp = programs[indexA];
        programs[indexA] = programs[indexB];
        programs[indexB] = temp;
    }

    private void dance(char[] programs, String[] moves) {
        for (String move : moves) {
            char command = move.charAt(0);

            if (command == 's') {
                spin(Integer.parseInt(move.substring(1)), programs);
            } else if (command == 'x') {
                exchange(move.substring(1), programs);
            } else if (command == 'p') {
                partner(move.substring(1), programs);
            }
        }
    }

    public String partOne(String filename) {
        char[] programs = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p'};
        String[] moves = InputParser.parseInputLine(filename).split(",");
        dance(programs, moves);

        return String.valueOf(programs);
    }

    public String partTwo(String filename) {
        char[] start = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p'};
        char[] programs = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p'};
        String[] moves = InputParser.parseInputLine(filename).split(",");

        int n = 1_000_000_000;
        int count = 0;
        while (n > 0) {
            dance(programs, moves);
            count++;
            n--;
            if (Arrays.equals(start, programs)) {
                while (n > count) {
                    n -= count;
                }
            }
            System.out.println(n);
        }

        return String.valueOf(programs);
    }
}
