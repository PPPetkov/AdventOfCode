package week4.day21;

import java.util.ArrayList;
import java.util.List;

public class RPGSimulator {
    private final List<Item> weapons;
    private final List<Item> armors;
    private final List<Item> rings;

    public RPGSimulator() {
        weapons = new ArrayList<>();
        armors = new ArrayList<>();
        rings = new ArrayList<>();

        weapons.add(new Item("Dagger", 8, 4, 0));
        weapons.add(new Item("Shortsword", 10, 5, 0));
        weapons.add(new Item("Warhammer", 25, 6, 0));
        weapons.add(new Item("Longsword", 40, 7, 0));
        weapons.add(new Item("Greataxe", 74, 8, 0));

        armors.add(new Item("Leather", 13, 0, 1));
        armors.add(new Item("Chainmail", 31, 0, 2));
        armors.add(new Item("Splintmail", 53, 0, 3));
        armors.add(new Item("Bandedmail", 75, 0, 4));
        armors.add(new Item("Platemail", 102, 0, 5));

        rings.add(new Item("Damage +1", 25, 1, 0));
        rings.add(new Item("Damage +2", 50, 2, 0));
        rings.add(new Item("Damage +3", 100, 3, 0));
        rings.add(new Item("Defense +1", 20, 0, 1));
        rings.add(new Item("Defense +2", 40, 0, 2));
        rings.add(new Item("Defense +3", 80, 0, 3));
    }

    private void updateStats(Item item, int[] player) {
        if (item != null) {
            player[1] += item.damage();
            player[2] += item.armor();
        }
    }

    private int calculateLoadoutCost(Item weapon, Item armor, Item ringLeft, Item ringRight) {
        int cost = weapon.cost();

        cost += armor == null ? 0 : armor.cost();
        cost += ringLeft == null ? 0 : ringLeft.cost();
        cost += ringRight == null ? 0 : ringRight.cost();

        return cost;
    }

    private boolean canBeatBoss(Item weapon, Item armor, Item ringLeft, Item ringRight, int[] boss) {
        int[] player = new int[]{100, 0, 0};
        updateStats(weapon, player);
        updateStats(armor, player);
        updateStats(ringLeft, player);
        updateStats(ringRight, player);

        int bossDMG = Math.max(boss[1] - player[2], 1);
        int playerDMG = Math.max(player[1] - boss[2], 1);
        int bossHP = boss[0];
        boolean playerTurn = true;

        while(player[0] > 0 && bossHP > 0) {
            if (playerTurn) {
                bossHP -= playerDMG;
            } else {
                player[0] -= bossDMG;
            }

            playerTurn = !playerTurn;
        }

        return bossHP <= 0;
    }

    public int partOne(int[] boss) {
        int cost = Integer.MAX_VALUE;

        for (Item weapon : weapons) {
            for (int i = -1; i < armors.size(); i++) {
                Item armor = i == -1 ? null : armors.get(i);
                for (int j = -1; j < rings.size(); j++) {
                    Item ringLeft = j == -1 ? null : rings.get(j);
                    for (int k = j; k < rings.size(); k++) {
                        Item ringRight = k == -1 ? null : rings.get(k);
                        if (ringRight != null && j == k) {
                            continue;
                        }

                        if (canBeatBoss(weapon, armor, ringLeft, ringRight, boss)) {
                            cost = Math.min(calculateLoadoutCost(weapon, armor, ringLeft, ringRight), cost);
                        }
                    }
                }
            }
        }
        return cost;
    }

    public int partTwo(int[] boss) {
        int cost = Integer.MIN_VALUE;

        for (Item weapon : weapons) {
            for (int i = -1; i < armors.size(); i++) {
                Item armor = i == -1 ? null : armors.get(i);
                for (int j = -1; j < rings.size(); j++) {
                    Item ringLeft = j == -1 ? null : rings.get(j);
                    for (int k = j; k < rings.size(); k++) {
                        Item ringRight = k == -1 ? null : rings.get(k);
                        if (ringRight != null && j == k) {
                            continue;
                        }

                        if (!canBeatBoss(weapon, armor, ringLeft, ringRight, boss)) {
                            cost = Math.max(calculateLoadoutCost(weapon, armor, ringLeft, ringRight), cost);
                        }
                    }
                }
            }
        }
        return cost;
    }
}
