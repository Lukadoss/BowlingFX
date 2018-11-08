package es.ulpgc.bowling.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class BowlingService {

    Logger logger = LoggerFactory.getLogger(BowlingService.class);

    private List<LineService> lines;

    public BowlingService(List<LineService> lines) {
        this.lines = lines;
    }

    public BowlingService() {
        this(new ArrayList<>());
    }

    public BowlingService addLine(LineService line) {
        this.lines.add(line);
        logger.debug("Adding line " + line.toString());
        return this;
    }

    public List<LineService> getLines() {
        return this.lines;
    }
}
