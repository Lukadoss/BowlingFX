package es.ulpgc.bowling.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "LINE")
public class LineEntity extends BaseEntity {

    /*
     * Fields
     */
    @ManyToOne
    private BowlingEntity bowling;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="line_id")
    @Fetch(FetchMode.SELECT)
    private List<GameEntity> games;

    /*
     * Getters and setters
     */
    public BowlingEntity getBowling() {
        return bowling;
    }

    public void setBowling(BowlingEntity bowling) {
        this.bowling = bowling;
    }

    public List<GameEntity> getGames() {
        return games;
    }

    public void setGames(List<GameEntity> games) {
        this.games = games;
    }

    /*
     * Constructors
     */
    public LineEntity() {
        this(new ArrayList<>());
    }

    public LineEntity(List<GameEntity> games) {
        this.games = games;
    }

    /*
     * Additional methods
     */
    public GameEntity getRunningGame() {
        for (GameEntity game : games) {
            if (game.isRunning()) return game;
        }
        return null;
    }

    /*
     * For debug purposes only
     */
    @Override
    public String toString() {
        return "LineEntity=[id=" + this.id +", bowling=" + bowling.toString() + "]";
    }
}
