package es.ulpgc.bowling.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "LINE")
public class LineEntity extends BaseEntity {

    @ManyToOne
    private BowlingEntity bowling;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="line_id")
    private List<GameEntity> games;

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

    @Override
    public String toString() {
        return "LineEntity=[id=" + this.id +", bowling=" + bowling.toString() + ", games=]";
    }
}
