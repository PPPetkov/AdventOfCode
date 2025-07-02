package week2.day6.lights;

import utils.Location2D;

public interface Lights {
    void toggle(Location2D start, Location2D end);
    void turnOn(Location2D start, Location2D end);
    void turnOff(Location2D start, Location2D end);

    int getTotalBrightness();
}
