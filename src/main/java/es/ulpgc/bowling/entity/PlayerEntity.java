package es.ulpgc.bowling.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "PLAYER")
public class PlayerEntity extends BaseEntity {

    /*
     * Fields
     */
    @Column
    private String name;

    @ManyToOne
    private GameEntity game;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    @Fetch(FetchMode.SELECT)
    private List<FrameEntity> frames;

    @Transient
    private List<Integer> rolls;

    @Transient
    private int frameCount, rollCount;

    @Transient
    private FrameEntity actualFrame;

    /*
     * Getters and setters
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
    }

    public List<FrameEntity> getFrames() {
        return frames;
    }

    public void setFrames(List<FrameEntity> frames) {
        this.frames = frames;
    }

    public List<Integer> getRolls() {
        if(rolls == null || rolls.isEmpty()) {
            setupRolls();
        }
        return rolls;
    }

    public void setRolls(List<Integer> rolls) {
        this.rolls = rolls;
    }

    public int getFrameCount() {
        return frameCount;
    }

    public void setFrameCount(int frameCount) {
        this.frameCount = frameCount;
    }

    public int getRollCount() {
        return rollCount;
    }

    public void setRollCount(int rollCount) {
        this.rollCount = rollCount;
    }

    public FrameEntity getActualFrame() {
        return actualFrame;
    }

    public void setActualFrame(FrameEntity actualFrame) {
        this.actualFrame = actualFrame;
    }

    /*
     * Constructors
     */
    public PlayerEntity(){
        this("Unknown");
    }

    public PlayerEntity(String name) {
        this.name = name;
        this.rolls = new ArrayList<>();
        this.frames = new ArrayList<>();
        frameCount =0;
        rollCount =0;
    }

    /*
     * Additional methods
     */
    public void updateFrames(){
        if(rollCount < rolls.size()) {
            actualFrame = new FrameEntity(this, rollCount, frameCount);
            frames.add(actualFrame);
            actualFrame.setRollOne(rolls.get(rollCount));

            rollCount += actualFrame.isLastFrame() ? 3 : (actualFrame.isStrike()) ? 1 : 2;
            frameCount++;
        } else if (actualFrame.isLastFrame()) {
            if (rollCount == rolls.size() && (actualFrame.isSpare() || actualFrame.isStrike())) actualFrame.setRollThree(rolls.get(rollCount - 1));
            else actualFrame.setRollTwo(rolls.get(rollCount - 2));
        } else actualFrame.setRollTwo(rolls.get(rollCount - 1));
    }

    public PlayerEntity roll(int pins) {
        rolls.add(pins);
        updateFrames();
        return this;
    }

    public FrameEntity getFrameOnIndex(int i) {
        if (getFrames().size() <= i) return null;
        return getFrames().get(i);
    }

    public Integer sumScore(int frame) {
        Integer sum = 0;
        for (int i = 0; i <= frame; i++) {
            if (getFrameOnIndex(i).score() == null) { return null; }
            sum += getFrameOnIndex(i).score();
        }
        return sum;
    }

    public Integer sumScore() {
        Integer sum = 0;
        for (FrameEntity f : frames) {
            if (f.score() != null) sum += f.score();
        }
        return sum;
    }

    /*
     * Additional private methods
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
