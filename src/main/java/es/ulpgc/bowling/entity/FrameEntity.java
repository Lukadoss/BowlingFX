package es.ulpgc.bowling.entity;

import javax.persistence.*;

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
}
