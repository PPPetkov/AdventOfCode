package week4.day24;

import utils.InputParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ElectromagneticMoat {
    private List<Component> getComponents(List<String> data) {
        List<Component> components = new ArrayList<>();

        for (String line : data) {
            components.add(Component.of(line));
        }

        return components;
    }

    private int findStrongestBridge(List<Component> components, Set<Component> used, int port, int strength) {
        int strongest = strength;

        for (Component component : components) {
            if (!used.contains(component)) {
                int p = component.hasNeededPort(port);
                if (p != -1) {
                    Set<Component> s = new HashSet<>(used);
                    s.add(component);
                    strongest = Math.max(strongest, findStrongestBridge(components, s, p, strength + component.getStrength()));
                }
            }
        }

        return strongest;
    }

    private int findStrongestBridgeWithLength(List<Component> components, Set<Component> used, int port, int strength, int length) {
        if (used.size() == length) {
            return strength;
        }
        int strongest = 0;

        for (Component component : components) {
            if (!used.contains(component)) {
                int p = component.hasNeededPort(port);
                if (p != -1) {
                    Set<Component> s = new HashSet<>(used);
                    s.add(component);
                    strongest = Math.max(strongest, findStrongestBridgeWithLength(components, s, p, strength + component.getStrength(), length));
                }
            }
        }

        return strongest;
    }

    public int partOne(String filename) {
        List<Component> components = getComponents(InputParser.parseInput(filename));
        return findStrongestBridge(components, new HashSet<>(), 0, 0);
    }

    public int partTwo(String filename) {
        List<Component> components = getComponents(InputParser.parseInput(filename));
        int strength = 0;
        for (int i = components.size(); i > 0; i--) {
            strength = findStrongestBridgeWithLength(components, new HashSet<>(), 0, 0, i);
            if (strength > 0) {
                System.out.println(i);
                break;
            }
        }
        return strength;
    }
}
