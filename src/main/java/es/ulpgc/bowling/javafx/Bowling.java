package es.ulpgc.bowling.javafx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Bowling {

    Logger logger = LoggerFactory.getLogger(Bowling.class);

    private List<Line> lines;

    public Bowling(List<Line> lines) {
        this.lines = lines;
    }

    public Bowling() {
        this(new ArrayList<>());
    }

    public Bowling addLine(Line line) {
        this.lines.add(line);
        logger.debug("Adding line " + line.toString());
        return this;
    }

    public List<Line> getLines() {
        return this.lines;
    }
}
