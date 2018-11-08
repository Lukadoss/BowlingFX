package es.ulpgc.bowling.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Bowling {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="bowling_id")
    private List<Line> lines;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }
}
