package es.ulpgc.bowling.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "BOWLING")
public class BowlingEntity extends BaseEntity {

    @Column
    private String name;

    @OneToMany(cascade=CascadeType.ALL)
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

    @Override
    public String toString() {
        return "BowlingEntity=[id=" + this.id +", name="+ this.name + ", lines=]";
    }
}
