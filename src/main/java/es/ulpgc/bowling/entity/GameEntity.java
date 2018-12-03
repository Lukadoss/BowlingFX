package es.ulpgc.bowling.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "GAME")
public class GameEntity extends BaseEntity {

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="game_id")
    private List<PlayerEntity> players;

    @ManyToOne
    private LineEntity line;

    @Column
    private LocalDateTime started;

    @Column
    private LocalDateTime ended;

    public List<PlayerEntity> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerEntity> players) {
        this.players = players;
    }

    public LineEntity getLine() {
        return line;
    }

    public void setLine(LineEntity line) {
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
        return "GameEntity=[id=" + this.id +", line="+ line.toString() + ", started=" + started + ", ended=" + ended + ", players=]";
    }
}
