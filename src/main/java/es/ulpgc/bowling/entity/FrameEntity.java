package es.ulpgc.bowling.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "FRAME")
public class FrameEntity extends BaseEntity {

    @ManyToOne
    private PlayerEntity player;

    @Column
    private Integer rollIndex;

    @Column
    private Integer frameIndex;

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public Integer getRollIndex() {
        return rollIndex;
    }

    public void setRollIndex(Integer rollIndex) {
        this.rollIndex = rollIndex;
    }

    public Integer getFrameIndex() {
        return frameIndex;
    }

    public void setFrameIndex(Integer frameIndex) {
        this.frameIndex = frameIndex;
    }

    public FrameEntity(PlayerEntity player, int rollIndex, int frameIndex) {
        this.rollIndex = rollIndex;
        this.frameIndex = frameIndex;
        this.player = player;
    }

    public Integer score() {
        if (!isTerminated()) return null;
        if (isLastFrame() && (isStrike() || isSpare())) return roll(rollIndex) + roll(rollIndex + 1) + roll(rollIndex + 2);
        if (isSpare()) return roll(rollIndex) + roll(rollIndex + 1) + roll(rollIndex + 2);
        if (isStrike()) {
            if (player.frame(frameIndex + 1) == null) return null;
            if (player.frame(frameIndex + 1).isStrike() && (player.frame(frameIndex + 2)) == null && !player.frame(frameIndex + 1).isLastFrame()) return null;
            return roll(rollIndex) + roll(rollIndex + 1) + roll(rollIndex + 2);
        }
        return roll(rollIndex) + roll(rollIndex + 1);
    }

    public boolean isLastFrame() {
        return this.frameIndex == 9;
    }

    private Integer roll(int rollIndex) {
        return player.getRolls().get(rollIndex);
    }

    private boolean isTerminated() {
        return this.rollIndex != player.getRolls().size() - rollsToTerminate();
    }

    private int rollsToTerminate() {
        return isSpare() || isStrike() ? 2 : 1;
    }

    public boolean isSpare() {
        if (rollIndex + 1 >= player.getRolls().size()) return false;
        return roll(rollIndex) + roll(rollIndex + 1) == 10;
    }

    public boolean isStrike() {
        return roll(rollIndex) == 10;
    }

    @Override
    public String toString(){
        return String.format("FrameEntity %d, score %d", frameIndex, score());
    }
}
