package es.ulpgc.bowling.javafx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Bowling {

    Logger logger = LoggerFactory.getLogger(Bowling.class);

    private List<Line> lines;
    private String name;

    public Bowling(List<Line> lines, String name) {
        this.lines = lines;
        this.name = name;
    }

    public Bowling(String name) {
        this(new ArrayList<>(), name);
    }

    public Bowling addLine(Line line) {
        this.lines.add(line);
        logger.debug("Adding line " + line.toString());
        return this;
    }

    public String getName() {
        return name;
    }

    public List<Line> getLines() {
        return this.lines;
    }
}
