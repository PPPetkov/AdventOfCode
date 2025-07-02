package week4.day24;

public class Component {
    private final int portA;
    private final int portB;

    public Component(int a, int b) {
        portA = a;
        portB = b;
    }

    public int hasNeededPort(int port) {
        if (portA == port) {
            return portB;
        }
        if (portB == port) {
            return portA;
        }
        return -1;
    }

    public int getStrength() {
        return portA + portB;
    }

    public static Component of(String data) {
        String[] tokens = data.split("/");
        return new Component(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Component component = (Component) o;
        return portA == component.portA && portB == component.portB;
    }

    @Override
    public int hashCode() {
        int result = portA;
        result = 31 * result + portB;
        return result;
    }
}
