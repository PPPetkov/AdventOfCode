package week4.day22;

public class WizardSimulatorState {
    public int bossHP;
    public int bossDMG;
    public int playerHP;
    public int mana;
    public int spent;
    public int shield;
    public int poison;
    public int recharge;
    public boolean playerTurn;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        WizardSimulatorState that = (WizardSimulatorState) o;
        return bossHP == that.bossHP && bossDMG == that.bossDMG && playerHP == that.playerHP && mana == that.mana && spent == that.spent && shield == that.shield && poison == that.poison && recharge == that.recharge && playerTurn == that.playerTurn;
    }

    @Override
    public int hashCode() {
        int result = bossHP;
        result = 31 * result + bossDMG;
        result = 31 * result + playerHP;
        result = 31 * result + mana;
        result = 31 * result + spent;
        result = 31 * result + shield;
        result = 31 * result + poison;
        result = 31 * result + recharge;
        result = 31 * result + Boolean.hashCode(playerTurn);
        return result;
    }

    public WizardSimulatorState(int bossHP, int bossDMG, int playerHP, int mana, int spent, int shield, int poison, int recharge, boolean playerTurn) {
        this.bossHP = bossHP;
        this.bossDMG = bossDMG;
        this.playerHP = playerHP;
        this.mana = mana;
        this.spent = spent;
        this.shield = shield;
        this.poison = poison;
        this.recharge = recharge;
        this.playerTurn = playerTurn;
    }
}
