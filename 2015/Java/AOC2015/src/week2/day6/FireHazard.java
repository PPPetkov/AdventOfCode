package week2.day6;

import utils.InputParser;
import utils.Location2D;
import week2.day6.lights.Lights;
import week2.day6.lights.OnOffLights;
import week2.day6.lights.TunableLights;

import java.util.List;

public class FireHazard {
    private int calculateBrightness(List<String> instructions, Lights lights) {
        for (String instruction : instructions) {
            String[] tokens = instruction.split("\\s+");

            if (tokens[0].equals("toggle")) {
                lights.toggle(Location2D.of(tokens[1]), Location2D.of(tokens[3]));
            } else if (tokens[0].equals("turn")) {
                if (tokens[1].equals("on")) {
                    lights.turnOn(Location2D.of(tokens[2]), Location2D.of(tokens[4]));
                } else {
                    lights.turnOff(Location2D.of(tokens[2]), Location2D.of(tokens[4]));
                }
            }
        }

        return lights.getTotalBrightness();
    }

    public int partOne(String filename) {
        return calculateBrightness(InputParser.parseInput(filename), new OnOffLights(1000));
    }

    public int partTwo(String filename) {
        return calculateBrightness(InputParser.parseInput(filename), new TunableLights(1000));
    }
}
