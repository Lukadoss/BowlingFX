package es.ulpgc.bowling.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Line extends BaseEntity {

    @ManyToOne
    private Bowling bowling;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="line_id")
    private List<Game> games;

    public Bowling getBowling() {
        return bowling;
    }

    public void setBowling(Bowling bowling) {
        this.bowling = bowling;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
