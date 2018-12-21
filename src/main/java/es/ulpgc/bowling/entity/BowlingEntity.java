package es.ulpgc.bowling.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * BowlingEntity representing Bowling bar object
 * @author David Bohmann
 */
@Entity(name = "BOWLING")
public class BowlingEntity extends BaseEntity {

    /*
     * Fields
     */
    /**
     * Name of Bowling bar
     */
    @Column
    private String name;

    /**
     * List of lines
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "bowling_id")
    @Fetch(FetchMode.SELECT)
    private List<LineEntity> lines;

    /*
     * Getters and setters
     */

    /**
     * Get name of Bowling bar
     * @return name of Bowling bar
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of Bowling bar
     * @param name Name of Bowling bar
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get list of lines
     * @return List of lines
     */
    public List<LineEntity> getLines() {
        return lines;
    }

    /**
     * Set list of lines
     * @param lines List of lines
     */
    public void setLines(List<LineEntity> lines) {
        this.lines = lines;
    }

    /*
     * Constructors
     */

    /**
     * Empty constructor, create empty list of lines
     */
    public BowlingEntity() {
        this(new ArrayList<>());
    }

    /**
     * Constructor with list of lines, Unknown name
     * @param lines List of lines
     */
    public BowlingEntity(List<LineEntity> lines) {
        this(lines, "Unknown");
    }

    /**
     * Constructor with name and list of lines
     * @param lines list of lines
     * @param name name of Bowling bar
     */
    public BowlingEntity(List<LineEntity> lines, String name) {
        this.lines = lines;
        this.name = name;
    }

    /*
     * Additional methods
     */

    /**
     * Add line to list of lines
     * @param line Line to add
     * @return BowlingEntity with added line
     */
    public BowlingEntity addLine(LineEntity line) {
        this.lines.add(line);
        return this;
    }

    /*
     * For debug purposes only
     */

    /**
     * toString method for debug purposes
     * @return String representation of BowlingEntity
     */
    @Override
    public String toString() {
        return "BowlingEntity=[id=" + this.id + ", name=" + this.name + "]";
    }
}
