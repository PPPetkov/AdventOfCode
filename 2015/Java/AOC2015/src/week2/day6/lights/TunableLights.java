package week2.day6.lights;

import utils.Location2D;

public class TunableLights implements Lights{
    private final int[][] lights;

    public TunableLights(int size) {
        lights = new int[size][size];
    }

    @Override
    public void toggle(Location2D start, Location2D end) {
        for (int x = start.x(); x <= end.x(); x++) {
            for (int y = start.y(); y <= end.y(); y++) {
                lights[x][y] += 2;
            }
        }
    }

    @Override
    public void turnOn(Location2D start, Location2D end) {
        for (int x = start.x(); x <= end.x(); x++) {
            for (int y = start.y(); y <= end.y(); y++) {
                lights[x][y]++;
            }
        }
    }

    @Override
    public void turnOff(Location2D start, Location2D end) {
        for (int x = start.x(); x <= end.x(); x++) {
            for (int y = start.y(); y <= end.y(); y++) {
                if(lights[x][y] > 0) {
                    lights[x][y]--;
                }
            }
        }
    }

    @Override
    public int getTotalBrightness() {
        int brightness = 0;
        for (int x = 0; x < lights.length; x++) {
            for (int y = 0; y < lights.length; y++) {
                brightness += lights[x][y];
            }
        }

        return brightness;
    }
}
