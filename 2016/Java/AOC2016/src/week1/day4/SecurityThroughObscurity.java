package week1.day4;

import utils.InputParser;

import java.util.ArrayList;
import java.util.List;

public class SecurityThroughObscurity {
    public int partOne(String filename) {
        List<String> rooms = InputParser.parseInput(filename);

        int sum = 0;
        for (String room : rooms) {
            RoomEncryption encryption = RoomEncryption.of(room);
            if (encryption.isRealRoom()) {
                sum += encryption.getSectorID();
            }
        }

        return sum;
    }

    public int partTwo(String filename) {
        List<String> rooms = InputParser.parseInput(filename);
        List<RoomEncryption> realRooms = new ArrayList<>();

        for (String room : rooms) {
            RoomEncryption encryption = RoomEncryption.of(room);
            if (encryption.isRealRoom()) {
                realRooms.add(encryption);
            }
        }

        for (RoomEncryption room : realRooms) {
            if (room.decryptName().contains("northpole object")) {
                return room.getSectorID();
            }
        }

        return -1;
    }
}
