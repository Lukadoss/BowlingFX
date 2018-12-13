package es.ulpgc.bowling.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity(name = "LINE")
public class LineEntity extends BaseEntity {

    @ManyToOne
    private BowlingEntity bowling;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="line_id")
    @Fetch(FetchMode.SELECT)
    private List<GameEntity> games;

    public LineEntity() {}

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

    public GameEntity getRunningGame() {
        for (GameEntity game : games) {
            if (game.isRunning()) return game;
        }
        return null;
    }

    public String getRunningGameName() {
        if (getRunningGame() != null) return getRunningGame().getName();
        return null;
    }

    @Override
    public String toString() {
        return "LineEntity=[id=" + this.id +", bowling=" + bowling.toString() + "]";
    }
}
