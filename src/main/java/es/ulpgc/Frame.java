package es.ulpgc;

public class Frame {
    private int index;
    private Player player;

    public Frame(Player player, int index) {
        this.index = index;
        this.player = player;
    }

    public Integer score() {
        if (!isTerminated()) return null;
        if (isSpare()) return roll(index) + roll(index + 1) + roll(index + 2);
        return roll(index) + roll(index + 1);
    }

    private Integer roll(int index) {
        return player.getRolls().get(index);
    }

    private boolean isTerminated() {
        return this.index != player.getRolls().size() - rollsToTerminate();

    }

    private int rollsToTerminate() {
        return isSpare() || isStrike() ? 2 : 1;
    }

    private boolean isSpare() {
        if (index + 1 >= player.getRolls().size()) return false;
        return roll(index) + roll(index + 1) == 10;

    }

    public boolean isStrike() {
        return roll(index) == 10;
    }
}
