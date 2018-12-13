package es.ulpgc.bowling.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "PLAYER")
public class PlayerEntity extends BaseEntity {

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
        if(rolls == null || rolls.isEmpty()) {
            setupRolls();
        }
        return rolls;
    }

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

    public void setRolls(List<Integer> rolls) {
        this.rolls = rolls;
    }

    public List<FrameEntity> getFrames() {
        return frames;
    }

    public PlayerEntity(){
        this("Unknown");
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

    public Integer sumScore() {
        Integer sum = 0;
        for (FrameEntity f : frames) {
            if (f.score() != null) sum += f.score();
        }
        return sum;
    }

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
