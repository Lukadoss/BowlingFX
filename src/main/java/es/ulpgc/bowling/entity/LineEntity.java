package es.ulpgc.bowling.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * LineEntity representing Line object
 * @author David Bohmann
 */
@Entity(name = "LINE")
public class LineEntity extends BaseEntity {

    /*
     * Fields
     */
    /**
     * Bowling bar - parent of line
     */
    @ManyToOne
    private BowlingEntity bowling;

    /**
     * List of games
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "line_id")
    @Fetch(FetchMode.SELECT)
    private List<GameEntity> games;

    /*
     * Getters and setters
     */

    /**
     * Get bowling
     * @return BowlingEntity
     */
    public BowlingEntity getBowling() {
        return bowling;
    }

    /**
     * Set bowling
     * @param bowling BowlingEntity
     */
    public void setBowling(BowlingEntity bowling) {
        this.bowling = bowling;
    }

    /**
     * Get games
     * @return list of games
     */
    public List<GameEntity> getGames() {
        return games;
    }

    /**
     * Set games
     * @param games List of games
     */
    public void setGames(List<GameEntity> games) {
        this.games = games;
    }

    /*
     * Constructors
     */

    /**
     * Empty constructor, create empty list of games
     */
    public LineEntity() {
        this(new ArrayList<>());
    }

    /**
     * Constructor with list of games
     * @param games List of games
     */
    public LineEntity(List<GameEntity> games) {
        this.games = games;
    }

    /*
     * Additional methods
     */

    /**
     * Find game that is currently running on line
     * @return
     */
    public GameEntity getRunningGame() {
        for (GameEntity game : games) {
            if (game.isRunning()) return game;
        }
        return null;
    }

    /**
     * Find name of running game for GUI
     * @return name of running game
     */
    public String getRunningGameName() {
        if (getRunningGame() != null) return getRunningGame().getName();
        return null;
    }

    /*
     * For debug purposes only
     */

    /**
     * toString method for debug purposes
     * @return String representation of LineEntity
     */
    @Override
    public String toString() {
        return "LineEntity=[id=" + this.id + ", bowling=" + bowling.toString() + "]";
    }
}
