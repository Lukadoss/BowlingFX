package es.ulpgc.bowling.entity;

import javax.persistence.*;
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

    @Override
    public String toString() {
        return "PlayerEntity=[id=" + this.id + ", name=" + name + ", game=" + game + ", frames=]";
    }
}
