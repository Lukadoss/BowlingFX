package es.ulpgc.bowling.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Bowling extends BaseEntity {

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="bowling_id")
    private List<Line> lines;

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return "Bowling=[id=" + this.id +", lines=]";
    }
}
