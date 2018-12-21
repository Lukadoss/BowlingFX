package es.ulpgc.bowling.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * FrameEntity representing Frame
 *
 * @author David Bohmann
 */
@Entity(name = "FRAME")
public class FrameEntity extends BaseEntity {

    /*
     * Fields
     */
    /**
     * PlayerEntity - parent object
     */
    @ManyToOne
    private PlayerEntity player;

    /**
     * Roll index of frame
     */
    @Column
    private Integer roll_index;

    /**
     * Frame index of frame
     */
    @Column
    private Integer frame_index;

    /**
     * First roll
     */
    @Column
    private Integer roll_one;

    /**
     * Second roll
     */
    @Column
    private Integer roll_two;

    /**
     * Third roll (only on last frame)
     */
    @Column
    private Integer roll_three;

    /*
     * Getters and setters
     */

    /**
     * Get player
     *
     * @return Player
     */
    public PlayerEntity getPlayer() {
        return player;
    }

    /**
     * Get roll index
     *
     * @return Roll index
     */
    public Integer getRollIndex() {
        return roll_index;
    }

    /**
     * Get frame index
     *
     * @return Frame index
     */
    public Integer getFrameIndex() {
        return frame_index;
    }

    /**
     * Get first roll
     *
     * @return First roll
     */
    public Integer getRollOne() {
        return roll_one;
    }

    /**
     * Set first roll
     *
     * @param roll_one First roll
     */
    public void setRollOne(Integer roll_one) {
        this.roll_one = roll_one;
    }

    /**
     * Get second roll
     *
     * @return Second roll
     */
    public Integer getRollTwo() {
        return roll_two;
    }

    /**
     * Set second roll
     *
     * @param roll_two Second roll
     */
    public void setRollTwo(Integer roll_two) {
        this.roll_two = roll_two;
    }

    /**
     * Get third roll
     *
     * @return Third roll
     */
    public Integer getRollThree() {
        return roll_three;
    }

    /**
     * Set third roll
     *
     * @param roll_three Third roll
     */
    public void setRollThree(Integer roll_three) {
        this.roll_three = roll_three;
    }

    /*
     * Constructors
     */

    /**
     * Constructor with player as parent object, roll index and frame index
     *
     * @param player      PlayerEntity
     * @param roll_index  Roll index
     * @param frame_index Frame index
     */
    public FrameEntity(PlayerEntity player, int roll_index, int frame_index) {
        this.roll_index = roll_index;
        this.frame_index = frame_index;
        this.player = player;
    }

    /**
     * Empty constructor - for Hibernate purposes only, dont use
     */
    public FrameEntity() {
        //just for Hibernate purposes
    }

    /*
     * Additional methods
     */

    /**
     * Compute score of the frame
     *
     * @return score of frame
     */
    public Integer score() {
        if (!isTerminated()) return null;
        if (isLastFrame() && (isStrike() || isSpare()) && getRollThree() != null)
            return roll(roll_index) + roll(roll_index + 1) + roll(roll_index + 2);
        if (isSpare()) return roll(roll_index) + roll(roll_index + 1) + roll(roll_index + 2);
        if (isStrike()) {
            if (player.getFrameOnIndex(frame_index + 1) == null) return null;
            if (player.getFrameOnIndex(frame_index + 1).isStrike() && (player.getFrameOnIndex(frame_index + 2)) == null && !player.getFrameOnIndex(frame_index + 1).isLastFrame())
                return null;
            return roll(roll_index) + roll(roll_index + 1) + roll(roll_index + 2);
        }
        return roll(roll_index) + roll(roll_index + 1);
    }

    /**
     * Is the frame last frame
     *
     * @return boolean indicating last frame
     */
    public boolean isLastFrame() {
        return this.frame_index == 9;
    }

    /**
     * Is the frame Spare
     *
     * @return boolean indicating Spare
     */
    public boolean isSpare() {
        if (roll_two == null) return false;
        return roll(roll_index) + roll(roll_index + 1) == 10;
    }

    /**
     * Is the frame Strike
     *
     * @return boolean indicating Strike
     */
    public boolean isStrike() {
        return roll(roll_index) == 10;
    }

    /*
     * Additional private methods
     */

    /**
     * Get roll on given roll index
     *
     * @param roll_index Roll index
     * @return Roll on the index
     */
    private Integer roll(int roll_index) {
        return player.getRolls().get(roll_index);
    }

    /**
     * Is the frame terminated
     *
     * @return boolean indicating terminated frame
     */
    private boolean isTerminated() {
        return this.roll_index != player.getRolls().size() - rollsToTerminate();
    }

    /**
     * How many rolls to terminate the frame
     *
     * @return Nubmer of rolls to terminate frame
     */
    private int rollsToTerminate() {
        return isSpare() || isStrike() ? 2 : 1;
    }

    /*
     * For debug purposes only
     */

    /**
     * toString method for debug purposes
     *
     * @return String representation of FrameEntity
     */
    @Override
    public String toString() {
        return "[FrameEntity=" + this.id + ", roll 1=" + this.roll_one + ", roll 2=" +
                this.roll_two + ", roll 3=" + this.roll_three + ", score=" + score() + "]";
    }
}
