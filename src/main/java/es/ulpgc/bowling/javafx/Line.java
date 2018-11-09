package es.ulpgc.bowling.javafx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Line {

    Logger logger = LoggerFactory.getLogger(Line.class);

    private Integer lineNumber;
    private List<Game> games;

    public Line(int lineNumber) {
        this(lineNumber, new ArrayList<>());
    }

    public Line(int lineNumber, ArrayList<Game> games) {
        this.lineNumber = lineNumber;
        this.games = games;
    }

    public Line addGame(Game game) {
        if (!hasRunningGame()) {
            this.games.add(game);
            logger.debug("Starting new game");
        } else {
            logger.warn("On this line is a game in progress");
        }
        return this;
    }

    private boolean hasRunningGame() {
        return getRunningGame() != null;
    }

    public Game getRunningGame() {
        for (Game game : games) {
            if (game.isRunning()) return game;
        }
        return null;
    }

    public Integer getLineNumber() {
        return this.lineNumber;
    }

    public List<Game> getGames() {
        return this.games;
    }
}
