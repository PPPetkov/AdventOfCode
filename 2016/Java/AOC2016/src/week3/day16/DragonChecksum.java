package week3.day16;

public class DragonChecksum {
    private void nextState(StringBuilder init) {
        StringBuilder b = new StringBuilder(init).reverse();
        init.append('0');

        for (int i = 0; i < b.length(); i++) {
            init.append(b.charAt(i) == '0' ? '1' : '0');
        }
    }

    private StringBuilder findChecksum(StringBuilder init) {
        StringBuilder checksum = new StringBuilder();

        for (int i = 0; i < init.length() - 1; i += 2) {
            checksum.append(init.charAt(i) == init.charAt(i + 1) ? '1' : '0');
        }

        return checksum;
    }

    public String partOne(String init, int len) {
        StringBuilder data = new StringBuilder(init);

        while (data.length() < len) {
            nextState(data);
        }

        data = new StringBuilder(data.substring(0, len));

        while (data.length() % 2 == 0) {
            data = findChecksum(data);
        }

        return data.toString();
    }

    public String partTwo(String init, int len) {
        return partOne(init, len);
    }
}
