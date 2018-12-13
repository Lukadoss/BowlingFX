package es.ulpgc.bowling.entity;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "GAME")
public class GameEntity extends BaseEntity {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    private List<PlayerEntity> players;

    @ManyToOne
    private LineEntity line;

    @Column
    private String name;

    @Column
    private LocalDateTime started;

    @Column
    private LocalDateTime ended;

    @Column
    private Integer totalScore;

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

    public String getGameDuration() {
        if (!isRunning()) {
            Duration duration = Duration.between(started, ended);

            long seconds = duration.getSeconds();

            long hours = seconds / 3600;
            long minutes = ((seconds % 3600) / 60);
            long secs = (seconds % 60);
            return hours + "h " + minutes + "m " + secs + "s";
        }
        return "Still running";
    }


    public GameEntity(String name, ArrayList<PlayerEntity> players) {
        this.started = LocalDateTime.now();
        this.ended = null;
        this.totalScore = 0;
        this.name = name;
        this.players = players;
    }

    public GameEntity() {}

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

    public void setTotalScore(int score) {
        this.totalScore = score;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "GameEntity=[id=" + this.id + ", line=" + this.line.toString() + ", started=" + this.started + ", ended=" + this.ended + ", players=" +
                this.players + "]";
    }
}
