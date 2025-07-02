package week2.day7;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Program {
    private final String name;
    private int weight;
    private final List<Program> heldPrograms;
    private Program programBelow;

    public Program(String name, int weight) {
        this.name = name;
        this.weight = weight;
        heldPrograms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public List<Program> getHeldPrograms() {
        return heldPrograms;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Program getProgramBelow() {
        return programBelow;
    }

    public void setProgramBelow(Program program) {
        programBelow = program;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Program program = (Program) o;
        return weight == program.weight && Objects.equals(name, program.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + weight;
        return result;
    }
}
