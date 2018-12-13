package es.ulpgc.bowling.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "GAME")
public class GameEntity extends BaseEntity {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    @Fetch(FetchMode.SELECT)
    private List<PlayerEntity> players;

    @ManyToOne
    private LineEntity line;

    @Column
    private String name;

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

    public GameEntity(ArrayList<PlayerEntity> players) {
        this.started = LocalDateTime.now();
        this.ended = null;
        this.players = players;
    }

    public GameEntity() {
        this(new ArrayList<>());
    }

    public GameEntity addPlayer(PlayerEntity player) {
        if (isRunning()) {
            if (!players.contains(player)) {
                this.players.add(player);
            }
        }

        return this;
    }

    public GameEntity removePlayer(PlayerEntity player) {
        if (isRunning()) {
            if (players.contains(player)) {
                this.players.remove(player);
            }
        }
        return this;
    }

    public void endGame() {
        this.ended = LocalDateTime.now();
    }

    public boolean isRunning() {
        return this.ended == null;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalScore() {
        Integer sum = 0;
        for (PlayerEntity p : players) {
            if (p.sumScore() != null) sum += p.sumScore();
        }
        return sum;
    }

    @Override
    public String toString() {
        return "GameEntity=[id=" + this.id + ", line=" + this.line.toString() + ", started=" + this.started + ", ended=" + this.ended + ", players=" +
                this.players + "]";
    }
}
