package es.ulpgc.bowling.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "PLAYER")
public class PlayerEntity extends BaseEntity {

    @Column
    private String name;

    @ManyToOne
    private GameEntity game;

    @ElementCollection
    @CollectionTable(name="ROLLS", joinColumns=@JoinColumn(name="player_id"))
    @Column
    private List<Integer> rolls;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="player_id")
    private List<FrameEntity> frames;

    @Column
    private Integer maxScore;

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

    public List<Integer> getRolls() {
        return rolls;
    }

    public void setRolls(List<Integer> rolls) {
        this.rolls = rolls;
    }

    public List<FrameEntity> getFrames() {
        return frames;
    }
    public void setFrames(List<FrameEntity> frames) {
        this.frames = frames;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public PlayerEntity(String name) {
        this.name = name;
        this.rolls = new ArrayList<>();
    }

    public List<FrameEntity> frames() {
        ArrayList<FrameEntity> frames = new ArrayList<>();
        int frameCnt = 0;
        for (int rollCnt = 0; rollCnt < rolls.size();) {
            FrameEntity frame = new FrameEntity(this, rollCnt, frameCnt);
            frames.add(frame);
            rollCnt += frame.isLastFrame() ? 3 : (frame.isStrike()) ? 1 : 2;
            frameCnt++;
        }
        return frames;
    }

    public PlayerEntity roll(int pins) {
        rolls.add(pins);
        return this;
    }

    public FrameEntity frame(int i) {
        if (frames().size() <= i) return null;
        return frames().get(i);
    }

    public Integer sumScore(int frame) {
        Integer sum = 0;
        for (int i = 0; i <= frame; i++) {
            if (frame(i).score() == null) { return null; }
            sum += frame(i).score();
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        for (int i = 0; i < rolls.size(); i++) {
            sb.append(" " + rolls.get(i));
        }
        return sb.toString();
    }
}
