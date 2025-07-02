package week2.day6.lights;

import utils.Location2D;

public class OnOffLights implements Lights{
    private boolean[][] lights;

    public OnOffLights(int size) {
        lights = new boolean[size][size];
    }

    @Override
    public void toggle(Location2D start, Location2D end) {
        for (int x = start.x(); x <= end.x(); x++) {
            for (int y = start.y(); y <= end.y(); y++) {
                lights[x][y] = !lights[x][y];
            }
        }
    }

    @Override
    public void turnOn(Location2D start, Location2D end) {
        for (int x = start.x(); x <= end.x(); x++) {
            for (int y = start.y(); y <= end.y(); y++) {
                lights[x][y] = true;
            }
        }
    }

    @Override
    public void turnOff(Location2D start, Location2D end) {
        for (int x = start.x(); x <= end.x(); x++) {
            for (int y = start.y(); y <= end.y(); y++) {
                lights[x][y] = false;
            }
        }
    }

    @Override
    public int getTotalBrightness() {
        int brightness = 0;
        for (int x = 0; x < lights.length; x++) {
            for (int y = 0; y < lights.length; y++) {
                if (lights[x][y]) {
                    brightness++;
                }
            }
        }

        return brightness;
    }
}
