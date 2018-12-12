package es.ulpgc.bowling.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "BOWLING")
public class BowlingEntity extends BaseEntity {

    @Column
    private String name;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="bowling_id")
    private List<LineEntity> lines;

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

    public BowlingEntity(List<LineEntity> lines, String name) {
        this.lines = lines;
        this.name = name;
    }

    public BowlingEntity(List<LineEntity> lines) {
        this(lines, "");
    }

    public BowlingEntity(String name) {
        this(new ArrayList<>(), name);
    }

    public BowlingEntity(){
        this("");
    }

    public BowlingEntity addLine(LineEntity line) {
        this.lines.add(line);
        return this;
    }

    @Override
    public String toString() {
        return "BowlingEntity=[id=" + this.id +", name="+ this.name + "]";
    }
}
