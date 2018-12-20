package es.ulpgc.bowling.entity;

import javax.persistence.*;

@Entity(name = "FRAME")
public class FrameEntity extends BaseEntity {

    /*
     * Fields
     */
    @ManyToOne
    private PlayerEntity player;

    @Column
    private Integer roll_index;

    @Column
    private Integer frame_index;

    @Column
    private Integer roll_one;

    @Column
    private Integer roll_two;

    @Column
    private Integer roll_three;

    /*
     * Getters and setters
     */
    public PlayerEntity getPlayer() {
        return player;
    }

    public Integer getRollIndex() {
        return roll_index;
    }

    public Integer getFrameIndex() {
        return frame_index;
    }

    public Integer getRollOne() {
        return roll_one;
    }

    public void setRollOne(Integer roll_one) {
        this.roll_one = roll_one;
    }

    public Integer getRollTwo() {
        return roll_two;
    }

    public void setRollTwo(Integer roll_two) {
        this.roll_two = roll_two;
    }

    public Integer getRollThree() {
        return roll_three;
    }

    public void setRollThree(Integer roll_three) {
        this.roll_three = roll_three;
    }

    /*
     * Constructors
     */
    public FrameEntity(PlayerEntity player, int roll_index, int frame_index) {
        this.roll_index = roll_index;
        this.frame_index = frame_index;
        this.player = player;
    }

    /*
     * Additional methods
     */
    public Integer score() {
        if (!isTerminated()) return null;
        if (isLastFrame() && (isStrike() || isSpare()) && getRollThree() != null) return roll(roll_index) + roll(roll_index + 1) + roll(roll_index + 2);
        if (isSpare()) return roll(roll_index) + roll(roll_index + 1) + roll(roll_index + 2);
        if (isStrike()) {
            if (player.getFrameOnIndex(frame_index + 1) == null) return null;
            if (player.getFrameOnIndex(frame_index + 1).isStrike() && (player.getFrameOnIndex(frame_index + 2)) == null && !player.getFrameOnIndex(frame_index + 1).isLastFrame())
                return null;
            return roll(roll_index) + roll(roll_index + 1) + roll(roll_index + 2);
        }
        return roll(roll_index) + roll(roll_index + 1);
    }

    public boolean isLastFrame() {
        return this.frame_index == 9;
    }

    public boolean isSpare() {
        if (roll_index + 1 >= player.getRolls().size()) return false;
        return roll(roll_index) + roll(roll_index + 1) == 10;
    }

    public boolean isStrike() {
        return roll(roll_index) == 10;
    }

    /*
     * Additional private methods
     */
    private Integer roll(int roll_index) {
        return player.getRolls().get(roll_index);
    }

    private boolean isTerminated() {
        return this.roll_index != player.getRolls().size() - rollsToTerminate();
    }

    private int rollsToTerminate() {
        return isSpare() || isStrike() ? 2 : 1;
    }

    /*
     * For debug purposes only
     */
    @Override
    public String toString() {
        return "[FrameEntity=" + this.id + ", roll 1=" + this.roll_one + ", roll 2=" +
                this.roll_two + ", roll 3=" + this.roll_three + ", score=" + score() + "]";
    }
}
