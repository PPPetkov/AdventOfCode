package week4.day20;

import java.math.BigDecimal;

public class Particle {
    private int x;
    private int y;
    private int z;

    private int vx;
    private int vy;
    private int vz;

    private final int ax;
    private final int ay;
    private final int az;

    public Particle(int x, int y, int z, int vx, int vy, int vz, int ax, int ay, int az) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
        this.ax = ax;
        this.ay = ay;
        this.az = az;
    }

    public void move() {
        vx += ax;
        vy += ay;
        vz += az;

        x += vx;
        y += vy;
        z += vz;
    }

    public int getManhattanDistanceFromOrigin() {
        return getManhattanDistance(0,0,0);
    }

    public int getManhattanDistance(int x, int y, int z) {
        return Math.abs(this.x - x) + Math.abs(this.y - y) + Math.abs(this.z - z);
    }

    public BigDecimal getAccelerationMagnitude() {
        return BigDecimal.valueOf(Math.sqrt(ax*ax + ay*ay + az*az));
    }

    public BigDecimal getVelocityMagnitude() {
        return BigDecimal.valueOf(Math.sqrt(vx*vx + vy*vy + vz*vz));
    }

    public boolean collidesWith(Particle other) {
        return x == other.x && y == other.y && z == other.z;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Particle particle = (Particle) o;
        return x == particle.x && y == particle.y && z == particle.z && vx == particle.vx && vy == particle.vy && vz == particle.vz && ax == particle.ax && ay == particle.ay && az == particle.az;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        result = 31 * result + vx;
        result = 31 * result + vy;
        result = 31 * result + vz;
        result = 31 * result + ax;
        result = 31 * result + ay;
        result = 31 * result + az;
        return result;
    }
}
