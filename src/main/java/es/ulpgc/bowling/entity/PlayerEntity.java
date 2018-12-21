package es.ulpgc.bowling.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PlayerEntity representing Player
 * @author David Bohmann
 */
@Entity(name = "PLAYER")
public class PlayerEntity extends BaseEntity {

    /*
     * Fields
     */
    /**
     * Name of player
     */
    @Column
    private String name;

    /**
     * Game - parent object
     */
    @ManyToOne
    private GameEntity game;

    /**
     * List of frames
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    @Fetch(FetchMode.SELECT)
    private List<FrameEntity> frames;

    /**
     * List of rolls
     */
    @Transient
    private List<Integer> rolls;

    /**
     * count of frames and count of rolls
     */
    @Transient
    private int frameCount, rollCount;

    /**
     * Info about current frame
     */
    @Transient
    private FrameEntity actualFrame;

    /*
     * Getters and setters
     */

    /**
     * Get name of player
     * @return Name of player
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of player
     * @param name Name of player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get game
     * @return GameEntity
     */
    public GameEntity getGame() {
        return game;
    }

    /**
     * Set game
     * @param game GameEntity
     */
    public void setGame(GameEntity game) {
        this.game = game;
    }

    /**
     * Get list of frames
     * @return List of frames
     */
    public List<FrameEntity> getFrames() {
        return frames;
    }

    /**
     * Get list of Rolls
     * @return List of Rolls
     */
    public List<Integer> getRolls() {
        if (rolls == null || rolls.isEmpty()) {
            setupRolls();
        }
        return rolls;
    }

    /**
     * Get count of frames
     * @return Count of frames
     */
    public int getFrameCount() {
        return frameCount;
    }

    /**
     * Get count of rolls
     * @return Count of rolls
     */
    public int getRollCount() {
        return rollCount;
    }

    /**
     * Get current frame
     * @return Current frame
     */
    public FrameEntity getActualFrame() {
        return actualFrame;
    }

    /*
     * Constructors
     */

    /**
     * Empty constructor, default name
     */
    public PlayerEntity() {
        this("Unknown");
    }

    /**
     * Constructor with name of the player
     * @param name Name of player
     */
    public PlayerEntity(String name) {
        this.name = name;
        this.rolls = new ArrayList<>();
        this.frames = new ArrayList<>();
        frameCount = 0;
        rollCount = 0;
    }

    /*
     * Additional methods
     */

    /**
     * Update frames after each roll
     */
    public void updateFrames() {
        if (rollCount < rolls.size()) {
            actualFrame = new FrameEntity(this, rollCount, frameCount);
            frames.add(actualFrame);
            actualFrame.setRollOne(rolls.get(rollCount));

            rollCount += actualFrame.isLastFrame() ? 3 : (actualFrame.isStrike()) ? 1 : 2;
            frameCount++;
        } else if (actualFrame.isLastFrame()) {
            if (rollCount == rolls.size() && (actualFrame.isSpare() || actualFrame.isStrike()))
                actualFrame.setRollThree(rolls.get(rollCount - 1));
            else actualFrame.setRollTwo(rolls.get(rollCount - 2));
        } else actualFrame.setRollTwo(rolls.get(rollCount - 1));
    }

    /**
     * Method to roll the pins
     * @param pins Number of pins to roll
     * @return PlayerEntity after roll
     */
    public PlayerEntity roll(int pins) {
        rolls.add(pins);
        updateFrames();
        return this;
    }

    /**
     * Get frame on specified indec
     * @param i index
     * @return Frame on index
     */
    public FrameEntity getFrameOnIndex(int i) {
        if (getFrames().size() <= i) return null;
        return getFrames().get(i);
    }

    /**
     * Get score until specified frame
     * @param frame number of frame
     * @return Score until frame
     */
    public Integer sumScore(int frame) {
        Integer sum = 0;
        for (int i = 0; i <= frame; i++) {
            if (getFrameOnIndex(i).score() == null) {
                return null;
            }
            sum += getFrameOnIndex(i).score();
        }
        return sum;
    }

    /**
     * Get complete score
     * @return Complete score
     */
    public Integer sumScore() {
        Integer sum = 0;
        for (FrameEntity f : frames) {
            if (f.score() != null) sum += f.score();
        }
        return sum;
    }

    /**
     * Clear this entity from memory
     */
    public void clearEntity(){
        this.rolls = new ArrayList<>();
        this.frames = new ArrayList<>();
        frameCount=0;
        rollCount=0;
    }

    /*
     * Additional private methods
     */

    /**
     * Generate list of rolls from list of frames
     * Method is used when pulling data from DB
     */
    private void setupRolls() {
        for (FrameEntity f : frames) {
            rolls.add(f.getRollOne());
            if (f.getRollTwo() != null) {
                rolls.add(f.getRollTwo());
            }
            if (f.getRollThree() != null) {
                rolls.add(f.getRollThree());
            }
        }
    }

    /*
     * For debug purposes only
     */

    /**
     * toString method for debug purposes
     * @return String representation of PlayerEntity
     */
    @Override
    public String toString() {
        getRolls();
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        for (int i = 0; i < rolls.size(); i++) {
            sb.append(" " + rolls.get(i));
        }
        return sb.toString();
    }
}
