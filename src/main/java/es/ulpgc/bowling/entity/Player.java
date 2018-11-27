package es.ulpgc.bowling.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Player extends BaseEntity {

    @Column
    private String name;

    @ManyToOne
    private Game game;

    @ElementCollection
    @CollectionTable(name="Rolls", joinColumns=@JoinColumn(name="player_id"))
    @Column
    private List<Integer> rolls;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="player_id")
    private List<Frame> frames;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Integer> getRolls() {
        return rolls;
    }

    public void setRolls(List<Integer> rolls) {
        this.rolls = rolls;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public void setFrames(List<Frame> frames) {
        this.frames = frames;
    }

    @Override
    public String toString() {
        return "Player=[id=" + this.id + ", name=" + name + ", game=" + game + ", frames=]";
    }
}
