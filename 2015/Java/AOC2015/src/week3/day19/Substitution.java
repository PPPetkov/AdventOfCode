package week3.day19;

import java.util.HashSet;
import java.util.Set;

public record Substitution(String origin, String replacement) {
    public Set<String> findAllSubstitutedStrings(String str) {
        Set<String> result = new HashSet<>();

        int low = str.indexOf(origin);
        int high = str.length();

        while (low != -1) {
            result.add(str.substring(0, low) + str.substring(low, high).replaceFirst(origin, replacement));
            low = str.indexOf(origin, low + origin.length(), high);
        }

        return result;
    }

    public static Substitution of(String str) {
        String[] tokens = str.split("\\s+");

        return new Substitution(tokens[0], tokens[2]);
    }
}
