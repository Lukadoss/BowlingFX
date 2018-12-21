package es.ulpgc.bowling.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * GameEntity representing Game
 *
 * @author David Bohmann
 */
@Entity(name = "GAME")
public class GameEntity extends BaseEntity {

    /*
     * Fields
     */
    /**
     * List of players
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    @Fetch(FetchMode.SELECT)
    private List<PlayerEntity> players;

    /**
     * Line - parent object
     */
    @ManyToOne
    private LineEntity line;

    /**
     * Name of game
     */
    @Column
    private String name;

    /**
     * Start time of game
     */
    @Column
    private LocalDateTime started;

    /**
     * End time of game
     */
    @Column
    private LocalDateTime ended;

    /*
     * Getters and setters
     */

    /**
     * Get list of players
     *
     * @return List of players
     */
    public List<PlayerEntity> getPlayers() {
        return players;
    }

    /**
     * Set list of players
     *
     * @param players List of players
     */
    public void setPlayers(List<PlayerEntity> players) {
        this.players = players;
    }

    /**
     * Get line
     *
     * @return LineEntity
     */
    public LineEntity getLine() {
        return line;
    }

    /**
     * Set line
     *
     * @param line LineEntity
     */
    public void setLine(LineEntity line) {
        this.line = line;
    }

    /**
     * Get name of game
     *
     * @return Name of game
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of game
     *
     * @param name Name of game
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get start time of game
     *
     * @return Start time of game
     */
    public LocalDateTime getStarted() {
        return started;
    }

    /**
     * Get end time of game
     *
     * @return End time of game
     */
    public LocalDateTime getEnded() {
        return ended;
    }

    /*
     * Constructors
     */

    /**
     * Empty constructor, default name and empty list of players
     */
    public GameEntity() {
        this("Unnamed", new ArrayList<>());
    }

    /**
     * Constructor with name of game and list of players
     *
     * @param name    Name of game
     * @param players List of players
     */
    public GameEntity(String name, ArrayList<PlayerEntity> players) {
        this.started = LocalDateTime.now();
        this.ended = null;
        this.name = name;
        this.players = players;
    }

    /*
     * Additional methods
     */

    /**
     * End game - set end time to now
     */
    public void endGame() {
        this.ended = LocalDateTime.now();
    }

    /**
     * Check if game is still running
     *
     * @return true if game is running
     */
    public boolean isRunning() {
        return this.ended == null;
    }

    /**
     * Get formatted difference between end and start time of game, if game still running, return message about running
     *
     * @return String representation of duration of the game.
     */
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

    /**
     * Add player to the game, if he is not there already
     *
     * @param player PlayerEntity
     * @return GameEntity with added player
     */
    public GameEntity addPlayer(PlayerEntity player) {
        if (isRunning()) {
            if (!players.contains(player)) {
                this.players.add(player);
            }
        }
        return this;
    }

    /**
     * Remove player from the game, if he is there
     *
     * @param player PlayerEntity
     * @return GameEntity with removed player
     */
    public GameEntity removePlayer(PlayerEntity player) {
        if (isRunning()) {
            if (players.contains(player)) {
                this.players.remove(player);
            }
        }
        return this;
    }

    /**
     * Get sum of scores of players of the game
     *
     * @return score of the game
     */
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

    /**
     * toString method for debug purposes
     *
     * @return String representation of GameEntity
     */
    @Override
    public String toString() {
        return "GameEntity=[id=" + this.id + ", line=" + this.line.toString() + ", started=" + this.started + ", ended=" + this.ended + ", players=" +
                this.players + "]";
    }
}
