package es.ulpgc.bowling.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class BowlingServiceImpl {

    Logger logger = LoggerFactory.getLogger(BowlingServiceImpl.class);

    private List<LineServiceImpl> lines;

    public BowlingServiceImpl(List<LineServiceImpl> lines) {
        this.lines = lines;
    }

    public BowlingServiceImpl() {
        this(new ArrayList<>());
    }

    public BowlingServiceImpl addLine(LineServiceImpl line) {
        this.lines.add(line);
        logger.debug("Adding line " + line.toString());
        return this;
    }

    public List<LineServiceImpl> getLines() {
        return this.lines;
    }
}
