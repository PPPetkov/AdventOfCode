package week2.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class State {
    private final int floor;
    private final int[] chips;
    private final int[] generators;

    public State(int floor, int[] chips, int[] generators) {
        this.floor = floor;
        this.chips = Arrays.copyOf(chips, chips.length);
        this.generators = Arrays.copyOf(generators, generators.length);
    }

    public boolean isFinalState() {
        for (int i = 0; i < chips.length; i++) {
            if (chips[i] != 4 || generators[i] != 4) {
                return false;
            }
        }

        return floor == 4;
    }

    private boolean isValidFloor(int floor) {
        return isValidFloor(floor, chips, generators);
    }

    private boolean isValidFloor(int floor, int[] chips, int[] generators) {
        boolean hasUnmatchedChip = false;
        boolean hasGenerator = false;

        for (int i = 0; i < chips.length; i++) {
            if (chips[i] == floor && generators[i] != floor) {
                hasUnmatchedChip = true;
            }
            if (generators[i] == floor) {
                hasGenerator = true;
            }
        }

        return !hasUnmatchedChip || !hasGenerator;
    }

    public boolean isValidState() {
        return isValidState(floor, chips, generators);
    }

    private List<Integer> getParts() {
        List<Integer> parts = new ArrayList<>();

        for (int i = 0; i < chips.length; i++) {
            if (chips[i] == floor) {
                parts.add(i);
            }
            if (generators[i] == floor) {
                parts.add(i + chips.length);
            }
        }

        return parts;
    }

    private boolean floorsBelowEmpty() {
        for (int i = 0; i < chips.length; i++) {
            if (chips[i] < floor || generators[i] < floor) {
                return false;
            }
        }

        return true;
    }

    private void takeOne(List<State> states, List<Integer> parts, boolean emptyBelow) {
        for (int part : parts) {
            int[] newGenerators = Arrays.copyOf(generators, generators.length);
            int[] newChips = Arrays.copyOf(chips, chips.length);
            if (floor > 1 && !emptyBelow) {
                if (part >= chips.length) {
                    newGenerators[part - chips.length] = floor - 1;
                } else {
                    newChips[part] = floor - 1;
                }
                if (isValidState(floor - 1, newChips, newGenerators)) {
                    states.add(new State(floor - 1, newChips, newGenerators));
                }
            }
            if (floor < 4) {
                if (part >= chips.length) {
                    newGenerators[part - chips.length] = floor + 1;
                } else {
                    newChips[part] = floor + 1;
                }
                if (isValidState(floor + 1, newChips, newGenerators)) {
                    states.add(new State(floor + 1, newChips, newGenerators));
                }
            }
        }
    }

    private boolean isPair(int first, int second) {
        return first - chips.length == second || second - chips.length == first;
    }

    private boolean isValidState(int floor, int[] chips, int[] generators) {
        return (floor == 1 || isValidFloor(floor - 1, chips, generators)) &&
                isValidFloor(floor, chips, generators) &&
                (floor == 4 || isValidFloor(floor + 1, chips, generators));
    }

    private void takeTwo(List<State> states, List<Integer> parts, boolean emptyBelow) {
        boolean foundPair = false;
        for (int i = 0; i < parts.size() - 1; i++) {
            int first = parts.get(i);
            for (int j = i + 1; j < parts.size(); j++) {
                int second = parts.get(j);
                if (isPair(first, second)) {
                    if (foundPair) {
                        continue;
                    }
                    foundPair = true;
                }
                int[] newChips = Arrays.copyOf(chips, chips.length);
                int[] newGenerators = Arrays.copyOf(generators, generators.length);

                if (floor > 1 && !emptyBelow) {
                    if (first >= chips.length) {
                        newGenerators[first - chips.length] = floor - 1;
                    } else {
                        newChips[first] = floor - 1;
                    }
                    if (second >= chips.length) {
                        newGenerators[second - chips.length] = floor - 1;
                    } else {
                        newChips[second] = floor - 1;
                    }

                    if (isValidState(floor - 1, newChips, newGenerators)) {
                        states.add(new State(floor - 1, newChips, newGenerators));
                    }
                }

                if (floor < 4) {
                    if (first >= chips.length) {
                        newGenerators[first - chips.length] = floor + 1;
                    } else {
                        newChips[first] = floor + 1;
                    }
                    if (second >= chips.length) {
                        newGenerators[second - chips.length] = floor + 1;
                    } else {
                        newChips[second] = floor + 1;
                    }

                    if (isValidState(floor + 1, newChips, newGenerators)) {
                        states.add(new State(floor + 1, newChips, newGenerators));
                    }
                }
            }
        }
    }

    public List<State> getNextStates() {
        List<State> states = new ArrayList<>();
        boolean emptyBelow = floorsBelowEmpty();

        List<Integer> parts = getParts();
        takeOne(states, parts, emptyBelow);
        takeTwo(states, parts, emptyBelow);

        return states;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;
        return floor == state.floor && Arrays.equals(chips, state.chips) && Arrays.equals(generators, state.generators);
    }

    @Override
    public int hashCode() {
        int result = floor;
        result = 31 * result + Arrays.hashCode(chips);
        result = 31 * result + Arrays.hashCode(generators);
        return result;
    }
}
