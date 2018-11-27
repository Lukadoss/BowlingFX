package es.ulpgc.bowling.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Game extends BaseEntity {

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="game_id")
    private List<Player> players;

    @ManyToOne
    private Line line;

    @Column
    private LocalDateTime started;

    @Column
    private LocalDateTime ended;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public LocalDateTime getStarted() {
        return started;
    }

    public void setStarted(LocalDateTime started) {
        this.started = started;
    }

    public LocalDateTime getEnded() {
        return ended;
    }

    public void setEnded(LocalDateTime ended) {
        this.ended = ended;
    }

    @Override
    public String toString() {
        return "Game=[id=" + this.id +", line="+ line.toString() + ", started=" + started + ", ended=" + ended + ", players=]";
    }
}
