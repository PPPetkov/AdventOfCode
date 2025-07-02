package week3.day18;

import utils.InputParser;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Duet {
    private boolean isNumeric(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    public long partOne(String filename) {
        List<String> instructions = InputParser.parseInput(filename);
        Map<String, Long> registers = new HashMap<>();
        long lastPlayed = 0;
        for (int i = 0; i < instructions.size(); i++) {
            String[] tokens = instructions.get(i).split("\\s+");
            long x;
            if (!isNumeric(tokens[1])) {
                registers.putIfAbsent(tokens[1], 0l);
                x = registers.get(tokens[1]);
            } else {
                x = Long.parseLong(tokens[1]);
            }

            if (tokens[0].equals("snd")) {
                lastPlayed = x;
            } else if (tokens[0].equals("rcv")) {
                if (x != 0) {
                    return lastPlayed;
                }
            } else {
                long y;
                if (!isNumeric(tokens[2])) {
                    registers.putIfAbsent(tokens[2], 0l);
                    y = registers.get(tokens[2]);
                } else {
                    y = Integer.parseInt(tokens[2]);
                }

                switch (tokens[0]) {
                    case "set" -> registers.put(tokens[1], y);
                    case "add" -> registers.put(tokens[1], x + y);
                    case "mul" -> registers.put(tokens[1], x * y);
                    case "mod" -> registers.put(tokens[1], x % y);
                    default -> {
                        if (x > 0) {
                            i += (int) (y - 1);
                        }
                    }
                }
            }
        }

        return 0;
    }

    public int partTwo(String filename) {
        List<String> instructions = InputParser.parseInput(filename);
        Map<String, Long> registers0 = new HashMap<>();
        registers0.put("p", 0L);
        Map<String, Long> registers1 = new HashMap<>();
        registers1.put("p", 1L);
        Queue<Long> queue0 = new ArrayDeque<>();
        Queue<Long> queue1 = new ArrayDeque<>();
        boolean inProgram0 = true;
        int sent = 0;
        int i = 0;
        int j = 0;

        Map<String, Long> registers = registers0;
        Queue<Long> recieve = queue0;
        Queue<Long> send = queue1;

        while (i < instructions.size() && j < instructions.size()) {
            System.out.println(sent);
            int crr = inProgram0 ? i : j;
            String[] tokens = instructions.get(crr).split("\\s+");

            long x;
            if (!isNumeric(tokens[1])) {
                registers.putIfAbsent(tokens[1], 0l);
                x = registers.get(tokens[1]);
            } else {
                x = Long.parseLong(tokens[1]);
            }

            if (tokens[0].equals("snd")) {
                send.add(x);
                if (!inProgram0) {
                    sent++;
                }
            } else if (tokens[0].equals("rcv")) {
                if (recieve.isEmpty()) {
                    inProgram0 = !inProgram0;
                    recieve = inProgram0 ? queue0 : queue1;
                    send = inProgram0 ? queue1 : queue0;
                    registers = inProgram0 ? registers0 : registers1;
                    if (recieve.isEmpty() && (inProgram0 ? i : j) != 0) {
                        break;
                    }
                    continue;
                } else {
                    registers.put(tokens[1], recieve.remove());
                }
            } else {
                long y;
                if (!isNumeric(tokens[2])) {
                    registers.putIfAbsent(tokens[2], 0l);
                    y = registers.get(tokens[2]);
                } else {
                    y = Integer.parseInt(tokens[2]);
                }

                switch (tokens[0]) {
                    case "set" -> registers.put(tokens[1], y);
                    case "add" -> registers.put(tokens[1], x + y);
                    case "mul" -> registers.put(tokens[1], x * y);
                    case "mod" -> registers.put(tokens[1], x % y);
                    default -> {
                        if (x > 0) {
                            crr += (int) y;
                            if (inProgram0) {
                                i = crr;
                            } else {
                                j = crr;
                            }
                            continue;
                        }
                    }
                }
            }
            if (inProgram0) {
                i++;
            } else {
                j++;
            }
        }

        return sent;
    }
}
