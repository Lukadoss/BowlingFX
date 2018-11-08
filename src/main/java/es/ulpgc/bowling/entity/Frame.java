package es.ulpgc.bowling.entity;

import javax.persistence.*;

@Entity
public class Frame {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;

    @ManyToOne
    private Player player;

    private Integer rollIndex;

    private Integer frameIndex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
