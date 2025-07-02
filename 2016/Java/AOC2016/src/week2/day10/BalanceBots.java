package week2.day10;

import utils.InputParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalanceBots {
    private void executeInstruction(String instruction, Map<Integer, Bot> bots) {
        String[] tokens = instruction.split("\\s+");
        int botNumber;
        if (tokens[0].equals("value")) {
            botNumber = Integer.parseInt(tokens[5]);

            bots.putIfAbsent(botNumber, new Bot(botNumber, false));
            bots.get(botNumber).setChip(Integer.parseInt(tokens[1]));
        } else {
            botNumber = Integer.parseInt(tokens[1]);
            bots.putIfAbsent(botNumber, new Bot(botNumber, false));
            Bot bot = bots.get(botNumber);

            int low = Integer.parseInt(tokens[6]);
            if (tokens[5].equals("bot")) {
                bots.putIfAbsent(low, new Bot(low, false));
                bot.lowerBot = bots.get(low);
            } else {
                bots.putIfAbsent(-low - 1, new Bot(-low - 1, true));
                bot.lowerBot = bots.get(-low - 1);
            }

            int high = Integer.parseInt(tokens[11]);
            if (tokens[10].equals("bot")) {
                bots.putIfAbsent(high, new Bot(high, false));
                bot.higherBot = bots.get(high);
            } else {
                bots.putIfAbsent(-high - 1, new Bot(-high - 1, true));
                bot.higherBot = bots.get(-high - 1);
            }
        }
    }

    private Map<Integer, Bot> initBots(List<String> instructions) {
        Map<Integer, Bot> bots = new HashMap<>();

        for (String instruction : instructions) {
            executeInstruction(instruction, bots);
        }

        return bots;
    }

    public int partOne(String filename) {
        Map<Integer, Bot> bots = initBots(InputParser.parseInput(filename));

        for (Bot bot : bots.values()) {
            bot.giveChips();
        }

        for (Map.Entry<Integer, Bot> entry : bots.entrySet()) {
            if (entry.getValue().isSearchedFor(17, 61)) {
                return entry.getKey();
            }
        }

        return -1;
    }

    public int partTwo(String filename) {
        Map<Integer, Bot> bots = initBots(InputParser.parseInput(filename));

        for (Bot bot : bots.values()) {
            bot.giveChips();
        }

        return bots.get(-1).chip1 * bots.get(-2).chip1 * bots.get(-3).chip1;
    }
}
