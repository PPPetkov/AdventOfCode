package week2.day7;

import utils.InputParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class RecursiveCircus {
    private Map<String, Program> buildTower(List<String> input) {
        Map<String, Program> tower = new HashMap<>();

        for (String str : input) {
            String[] tokens = str.split("\\s+");
            Program p;
            int weight = Integer.parseInt(tokens[1].substring(1, tokens[1].length() - 1));
            if (tower.get(tokens[0]) == null) {
                p = new Program(tokens[0], weight);
                tower.put(tokens[0], p);
            } else {
                p = tower.get(tokens[0]);
                p.setWeight(weight);
            }
            if (tokens.length > 2) {
                List<Program> programs = p.getHeldPrograms();
                for (int i = 3; i < tokens.length; i++) {
                    String name = i == tokens.length - 1 ? tokens[i] : tokens[i].substring(0, tokens[i].length() - 1);
                    Program program;
                    if (tower.get(name) == null) {
                        program = new Program(name, -1);
                        tower.put(name, program);
                    } else {
                        program = tower.get(name);
                    }
                    programs.add(program);
                    program.setProgramBelow(p);
                }
            }
        }

        return tower;
    }

    private String getBottomProgramName(Map<String, Program> tower, String start) {
        Program p = tower.get(start);
        while (p.getProgramBelow() != null) {
            p = p.getProgramBelow();
        }

        return p.getName();
    }

    public String partOne(String filename) {
        List<String> input = InputParser.parseInput(filename);
        String name = input.getFirst().substring(0, input.getFirst().indexOf(" "));
        return getBottomProgramName(buildTower(input), name);
    }

    private void findWeight(Map<Program, Integer> cache, Program root) {
        int weight = root.getWeight();

        for (Program program : root.getHeldPrograms()) {
            if (!cache.containsKey(program)) {
                findWeight(cache, program);
            }
            weight += cache.get(program);
        }

        cache.put(root, weight);
    }

    private int findMinority(Map<Integer, Integer> map) {
        int minority = -1;
        int encounters = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (encounters > entry.getValue()) {
                minority = entry.getKey();
                encounters = entry.getValue();
            }
        }

        return minority;
    }

    private int findMajority(Map<Integer, Integer> map) {
        int majority = -1;
        int encounters = Integer.MIN_VALUE;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (encounters < entry.getValue()) {
                majority = entry.getKey();
                encounters = entry.getValue();
            }
        }

        return majority;
    }

    private int findIdealWeight(Program program, Map<Program, Integer> weights) {
        List<Program> held = program.getHeldPrograms();
        if (held.isEmpty()) {
            return program.getWeight();
        }

        if (held.size() == 1) {
            return findIdealWeight(held.getFirst(), weights);
        }

        Map<Integer, Integer> encounters = new HashMap<>();

        for (Program p : held) {
            int weight = weights.get(p);
            encounters.put(weight, encounters.getOrDefault(weight, 0) + 1);
        }

        int minority = findMinority(encounters);
        int majority = findMajority(encounters);
        for (Program p : held) {
            if (weights.get(p) == minority) {
                return findIdealWeight(p, weights, majority);
            }
        }

        return program.getWeight();
    }

    private int findIdealWeight(Program program, Map<Program, Integer> weights, int targetWeight) {
        List<Program> held = program.getHeldPrograms();
        if (held.isEmpty()) {
            return targetWeight;
        }

        int target = (targetWeight - program.getWeight()) / held.size();
        if (held.size() == 1) {
            return findIdealWeight(held.getFirst(), weights, target);
        }

        boolean anyMatch = false;
        for (Program p : held) {
            if (weights.get(p) == target) {
                anyMatch = true;
            } else {
                if (anyMatch) {
                    return findIdealWeight(p, weights, target);
                }
            }

        }

        return targetWeight - weights.get(held.getFirst()) * held.size();
    }

    public int partTwo(String filename) {
        List<String> input = InputParser.parseInput(filename);
        String name = input.getFirst().substring(0, input.getFirst().indexOf(" "));
        Map<String, Program> tower = buildTower(input);
        Program bottom =  tower.get(getBottomProgramName(tower, name));
        Map<Program, Integer> weights = new HashMap<>();
        findWeight(weights, bottom);

        return findIdealWeight(bottom, weights);
    }
}
