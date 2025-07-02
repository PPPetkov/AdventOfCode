package week1.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CeresSearch {
    private static long countWord(String word, List<String> wordSearch) {
        long count = 0;
        int rows = wordSearch.size();
        int cols = wordSearch.getFirst().length();
        int wordLen = word.length();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j > 2) {
                    int k = 0;
                    while (k < wordLen && word.charAt(k) == wordSearch.get(i).charAt(j - k)) {
                        k++;
                    }
                    if (k == wordLen) {
                        count++;
                    }
                }

                if (j < 137) {
                    int k = 0;
                    while (k < wordLen && word.charAt(k) == wordSearch.get(i).charAt(j + k)) {
                        k++;
                    }
                    if (k == wordLen) {
                        count++;
                    }
                }

                if (i > 2) {
                    int k = 0;
                    while (k < wordLen && word.charAt(k) == wordSearch.get(i - k).charAt(j)) {
                        k++;
                    }
                    if (k == wordLen) {
                        count++;
                    }
                }

                if (i < 137) {
                    int k = 0;
                    while (k < wordLen && word.charAt(k) == wordSearch.get(i + k).charAt(j)) {
                        k++;
                    }
                    if (k == wordLen) {
                        count++;
                    }
                }

                if (i < 137 && j < 137) {
                    int k = 0;
                    while (k < wordLen && word.charAt(k) == wordSearch.get(i + k).charAt(j + k)) {
                        k++;
                    }
                    if (k == wordLen) {
                        count++;
                    }
                }

                if (i < 137 && j > 2) {
                    int k = 0;
                    while (k < wordLen && word.charAt(k) == wordSearch.get(i + k).charAt(j - k)) {
                        k++;
                    }
                    if (k == wordLen) {
                        count++;
                    }
                }

                if (i > 2 && j > 2) {
                    int k = 0;
                    while (k < wordLen && word.charAt(k) == wordSearch.get(i - k).charAt(j - k)) {
                        k++;
                    }
                    if (k == wordLen) {
                        count++;
                    }
                }

                if (i > 2 && j < 137) {
                    int k = 0;
                    while (k < wordLen && word.charAt(k) == wordSearch.get(i - k).charAt(j + k)) {
                        k++;
                    }
                    if (k == wordLen) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private static long countXMAS(List<String> wordSearch) {
        int rows = wordSearch.size();
        int cols = wordSearch.getFirst().length();
        long count = 0;

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (wordSearch.get(i).charAt(j) == 'A') {
                    char topLeft = wordSearch.get(i - 1).charAt(j - 1);
                    char topRight = wordSearch.get(i - 1).charAt(j + 1);
                    char bottomLeft = wordSearch.get(i + 1).charAt(j - 1);
                    char bottomRight = wordSearch.get(i + 1).charAt(j + 1);

                    if ((topLeft == 'M' || topLeft == 'S')
                        && (topRight == 'M' || topRight == 'S')
                        && (bottomLeft == 'M' || bottomLeft == 'S')
                        && (bottomRight == 'M' || bottomRight == 'S')
                        && topLeft != bottomRight && topRight != bottomLeft) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public static long partOne(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<String> wordSearch = new ArrayList<>();

        while (reader.ready()) {
            wordSearch.add(reader.readLine());
        }

        return countXMAS(wordSearch);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(partOne("resources/input.txt"));
    }
}
