package week2.day10;

public class Bot {
    public int number;
    public Bot higherBot;
    public Bot lowerBot;
    public int chip1;
    public int chip2;
    private boolean hasGivenChips;
    private boolean isOutput;

    public Bot(int number, boolean isOutput) {
        this.number = number;
        chip1 = -1;
        chip2 = -1;
        hasGivenChips = false;
        this.isOutput = isOutput;
    }

    public void setChip(int value) {
        if (chip1 == -1) {
            chip1 = value;
        } else if (chip2 == -1){
            chip2 = value;
        }
    }

    public void giveChips() {
        if (chip1 == -1 || chip2 == -1 || hasGivenChips || isOutput) {
            return;
        }

        if (higherBot != null) {
            higherBot.setChip(Math.max(chip1, chip2));
            higherBot.giveChips();
        }

        if (lowerBot != null) {
            lowerBot.setChip(Math.min(chip1, chip2));
            lowerBot.giveChips();
        }

        hasGivenChips = true;
    }

    public boolean isSearchedFor(int low, int high) {
        return Math.min(chip1, chip2) == low && Math.max(chip1, chip2) == high && !isOutput;
    }
}
