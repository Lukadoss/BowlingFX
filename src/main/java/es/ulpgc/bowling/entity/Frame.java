package es.ulpgc.bowling.entity;

import javax.persistence.*;

@Entity
public class Frame extends BaseEntity {

    @ManyToOne
    private Player player;

    @Column
    private Integer rollIndex;

    @Column
    private Integer frameIndex;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
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
}
