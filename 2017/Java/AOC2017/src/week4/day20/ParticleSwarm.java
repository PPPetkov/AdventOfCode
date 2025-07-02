package week4.day20;

import utils.InputParser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParticleSwarm {
    private List<Particle> getParticles(List<String> data) {
        List<Particle> particles = new ArrayList<>();

        for (String line : data) {
            String[] tokens = line.split("[<>]");
            String[] position = tokens[1].split(",");
            String[] velocity = tokens[3].split(",");
            String[] acceleration = tokens[5].split(",");

            particles.add(new Particle(
                    Integer.parseInt(position[0]), Integer.parseInt(position[1]), Integer.parseInt(position[2]),
                    Integer.parseInt(velocity[0]), Integer.parseInt(velocity[1]), Integer.parseInt(velocity[2]),
                    Integer.parseInt(acceleration[0]), Integer.parseInt(acceleration[1]), Integer.parseInt(acceleration[2])));
        }

        return particles;
    }

    private int findSlowestParticle(List<Particle> particles) {
        if (particles.isEmpty()) {
            return 0;
        }
        BigDecimal slowestAcc = particles.getFirst().getAccelerationMagnitude();
        BigDecimal slowestVel = particles.getFirst().getVelocityMagnitude();
        int index = 0;

        for (int i = 1; i < particles.size(); i++) {
            Particle current = particles.get(i);
            BigDecimal acc = current.getAccelerationMagnitude();
            BigDecimal vel = current.getVelocityMagnitude();

            int comparison = acc.compareTo(slowestAcc);
            if (comparison < 0 || (comparison == 0 && vel.compareTo(slowestVel) < 0)) {
                slowestVel = vel;
                slowestAcc = acc;
                index = i;
            }
        }

        return index;
    }

    public int partOne(String filename) {
        return findSlowestParticle(getParticles(InputParser.parseInput(filename)));
    }

    public int partTwo(String filename) {
        List<Particle> particles = getParticles(InputParser.parseInput(filename));

        for (int n = 0; n < 1_000; n++) {
            Set<Particle> collisions = new HashSet<>();
            for (int i = 0; i < particles.size() - 1; i++) {
                Particle current = particles.get(i);
                for (int j = i + 1; j < particles.size(); j++) {
                    if (current.collidesWith(particles.get(j))) {
                        collisions.add(current);
                        collisions.add(particles.get(j));
                    }
                }
            }

            List<Particle> next = new ArrayList<>();
            for (Particle particle : particles) {
                if (!collisions.contains(particle)) {
                    particle.move();
                    next.add(particle);
                }
            }
            particles = next;
        }

        return particles.size();
    }
}
