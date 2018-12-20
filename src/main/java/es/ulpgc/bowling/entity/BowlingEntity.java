package es.ulpgc.bowling.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "BOWLING")
public class BowlingEntity extends BaseEntity {

    /*
     * Fields
     */
    @Column
    private String name;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="bowling_id")
    @Fetch(FetchMode.SELECT)
    private List<LineEntity> lines;

    /*
     * Getters and setters
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LineEntity> getLines() {
        return lines;
    }

    public void setLines(List<LineEntity> lines) {
        this.lines = lines;
    }

    /*
     * Constructors
     */
    public BowlingEntity(){
        this(new ArrayList<>());
    }

    public BowlingEntity(List<LineEntity> lines) {
        this(lines, "Unknown");
    }

    public BowlingEntity(List<LineEntity> lines, String name) {
        this.lines = lines;
        this.name = name;
    }

    /*
     * Additional methods
     */
    public BowlingEntity addLine(LineEntity line) {
        this.lines.add(line);
        return this;
    }

    /*
     * For debug purposes only
     */
    @Override
    public String toString() {
        return "BowlingEntity=[id=" + this.id +", name="+ this.name + "]";
    }
}
