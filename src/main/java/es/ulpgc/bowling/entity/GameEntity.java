package es.ulpgc.bowling.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "GAME")
public class GameEntity extends BaseEntity {

    /*
     * Fields
     */
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

    /*
     * Getters and setters
     */
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStarted() {
        return started;
    }

    public LocalDateTime getEnded() {
        return ended;
    }

    /*
     * Constructors
     */
    public GameEntity() {
        this("Unnamed", new ArrayList<>());
    }

    public GameEntity(String name, ArrayList<PlayerEntity> players) {
        this.started = LocalDateTime.now();
        this.ended = null;
        this.name = name;
        this.players = players;
    }

    /*
     * Additional methods
     */
    public void endGame() {
        this.ended = LocalDateTime.now();
    }

    public boolean isRunning() {
        return this.ended == null;
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

    public Integer getTotalScore() {
        Integer sum = 0;
        for (PlayerEntity p : players) {
            if (p.sumScore() != null) sum += p.sumScore();
        }
        return sum;
    }

    /*
     * For debug purposes only
     */
    @Override
    public String toString() {
        return "GameEntity=[id=" + this.id + ", line=" + this.line.toString() + ", started=" + this.started + ", ended=" + this.ended + ", players=" +
                this.players + "]";
    }
}
