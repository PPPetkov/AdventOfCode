package week4.day22;

import java.util.HashMap;
import java.util.Map;

public class WizardSimulator {
    private int findMinimumMana(WizardSimulatorState state, Map<WizardSimulatorState, Integer> cache) {
        if (state == null) {
            return Integer.MAX_VALUE;
        }
        if (cache.containsKey(state)) {
            return cache.get(state);
        }
        if (state.playerHP <= 0) {
            cache.put(state, Integer.MAX_VALUE);
            return Integer.MAX_VALUE;
        }

        int bossHP = state.bossHP;
        int playerHP = state.playerHP;
        int mana = state.mana;
        int spent = state.spent;
        int shield = state.shield;
        int poison = state.poison;
        int recharge = state.recharge;

        if (poison > 0) {
            poison--;
            bossHP -= 3;
        }
        if (bossHP <= 0) {
            cache.put(state, state.spent);
            return state.spent;
        }
        if (recharge > 0) {
            recharge--;
            mana += 101;
        }
        if (mana < 53) {
            cache.put(state, Integer.MAX_VALUE);
            return Integer.MAX_VALUE;
        }
        int armor = 0;
        if (shield > 0) {
            armor = 7;
            shield--;
        }

        if (!state.playerTurn) {
            playerHP -= Math.max(state.bossDMG - armor, 1);
            WizardSimulatorState bossTurn = new WizardSimulatorState(bossHP, state.bossDMG, playerHP, mana, spent, shield, poison, recharge, true);
            int result = findMinimumMana(bossTurn, cache);
            cache.put(bossTurn, result);
            return result;
        } else {
            WizardSimulatorState castMissile = new WizardSimulatorState(bossHP - 4, state.bossDMG, playerHP, mana - 53, spent + 53, shield, poison, recharge, false);
            WizardSimulatorState castDrain = new WizardSimulatorState(bossHP - 2, state.bossDMG, playerHP + 2, mana - 73, spent + 73, shield, poison, recharge, false);
            WizardSimulatorState castShield = shield != 0 ? null : new WizardSimulatorState(bossHP, state.bossDMG, playerHP, mana - 113, spent + 113, 6, poison, recharge, false);
            WizardSimulatorState castPoison = poison != 0 ? null : new WizardSimulatorState(bossHP, state.bossDMG, playerHP, mana - 173, spent + 173, shield, 6, recharge, false);
            WizardSimulatorState castRecharge = recharge != 0 ? null : new WizardSimulatorState(bossHP, state.bossDMG, playerHP, mana - 229, spent + 229, shield, poison, 5, false);

            int missileResult = findMinimumMana(castMissile, cache);
            cache.put(castMissile, missileResult);

            int drainResult = findMinimumMana(castDrain, cache);
            cache.put(castDrain, drainResult);

            int shieldResult = findMinimumMana(castShield, cache);
            cache.put(castShield, shieldResult);

            int poisonResult = findMinimumMana(castPoison, cache);
            cache.put(castPoison, poisonResult);

            int rechargeResult = findMinimumMana(castRecharge, cache);
            cache.put(castRecharge, rechargeResult);

            int result = Math.min(missileResult, Math.min(drainResult, Math.min(shieldResult, Math.min(poisonResult, rechargeResult))));
            cache.put(state, result);

            return result;
        }
    }

    public int partOne(int[] boss) {
        return findMinimumMana(new WizardSimulatorState(boss[0], boss[1], 50, 500, 0, 0, 0, 0, true), new HashMap<>());
    }

    private int findMinimumManaHard(WizardSimulatorState state, Map<WizardSimulatorState, Integer> cache) {
        if (state == null) {
            return Integer.MAX_VALUE;
        }
        if (cache.containsKey(state)) {
            return cache.get(state);
        }

        int bossHP = state.bossHP;
        int playerHP = state.playerHP;
        int mana = state.mana;
        int spent = state.spent;
        int shield = state.shield;
        int poison = state.poison;
        int recharge = state.recharge;

        if(state.playerTurn) {
            playerHP--;
        }
        if (state.playerHP <= 0) {
            cache.put(state, Integer.MAX_VALUE);
            return Integer.MAX_VALUE;
        }

        if (poison > 0) {
            poison--;
            bossHP -= 3;
        }
        if (bossHP <= 0) {
            cache.put(state, state.spent);
            return state.spent;
        }
        if (recharge > 0) {
            recharge--;
            mana += 101;
        }
        if (mana < 53) {
            cache.put(state, Integer.MAX_VALUE);
            return Integer.MAX_VALUE;
        }
        int armor = 0;
        if (shield > 0) {
            armor = 7;
            shield--;
        }

        if (!state.playerTurn) {
            playerHP -= Math.max(state.bossDMG - armor, 1);
            WizardSimulatorState bossTurn = new WizardSimulatorState(bossHP, state.bossDMG, playerHP, mana, spent, shield, poison, recharge, true);
            int result = findMinimumManaHard(bossTurn, cache);
            cache.put(bossTurn, result);
            return result;
        } else {
            WizardSimulatorState castMissile = new WizardSimulatorState(bossHP - 4, state.bossDMG, playerHP, mana - 53, spent + 53, shield, poison, recharge, false);
            WizardSimulatorState castDrain = new WizardSimulatorState(bossHP - 2, state.bossDMG, playerHP + 2, mana - 73, spent + 73, shield, poison, recharge, false);
            WizardSimulatorState castShield = shield != 0 ? null : new WizardSimulatorState(bossHP, state.bossDMG, playerHP, mana - 113, spent + 113, 6, poison, recharge, false);
            WizardSimulatorState castPoison = poison != 0 ? null : new WizardSimulatorState(bossHP, state.bossDMG, playerHP, mana - 173, spent + 173, shield, 6, recharge, false);
            WizardSimulatorState castRecharge = recharge != 0 ? null : new WizardSimulatorState(bossHP, state.bossDMG, playerHP, mana - 229, spent + 229, shield, poison, 5, false);

            int missileResult = findMinimumManaHard(castMissile, cache);
            cache.put(castMissile, missileResult);

            int drainResult = findMinimumManaHard(castDrain, cache);
            cache.put(castDrain, drainResult);

            int shieldResult = findMinimumManaHard(castShield, cache);
            cache.put(castShield, shieldResult);

            int poisonResult = findMinimumManaHard(castPoison, cache);
            cache.put(castPoison, poisonResult);

            int rechargeResult = findMinimumManaHard(castRecharge, cache);
            cache.put(castRecharge, rechargeResult);

            int result = Math.min(missileResult, Math.min(drainResult, Math.min(shieldResult, Math.min(poisonResult, rechargeResult))));
            cache.put(state, result);

            return result;
        }
    }

    public int partTwo(int[] boss) {
        return findMinimumManaHard(new WizardSimulatorState(boss[0], boss[1], 50, 500, 0, 0, 0, 0, true), new HashMap<>());
    }
}
