package week3.day19;

import utils.InputParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MedicineForRudolph {
    private List<Substitution> getSubstitutions(List<String> input) {
        List<Substitution> subs = new ArrayList<>();

        for (int i = 0; i < input.size() - 2; i++) {
            subs.add(Substitution.of(input.get(i)));
        }

        return subs;
    }

    private Set<String> findAllSubstitutedMolecules(String molecule, List<Substitution> substitutions) {
        Set<String> result = new HashSet<>();

        for (Substitution s : substitutions) {
            result.addAll(s.findAllSubstitutedStrings(molecule));
        }

        return result;
    }

    public int partOne(String filename) {
        List<String> input = InputParser.parseInput(filename);

        return findAllSubstitutedMolecules(input.getLast(), getSubstitutions(input)).size();
    }

    public int partTwo(String filename) {
        return 0;
    }
}
