package es.ulpgc.bowling.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LineService {

    Logger logger = LoggerFactory.getLogger(LineService.class);

    private Integer lineNumber;
    private List<GameService> games;

    public LineService(int lineNumber) {
        this(lineNumber, new ArrayList<>());
    }

    public LineService(int lineNumber, ArrayList<GameService> games) {
        this.lineNumber = lineNumber;
        this.games = games;
    }

    public LineService addGame(GameService game) {
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

    public GameService getRunningGame() {
        for (GameService game : games) {
            if (game.isRunning()) return game;
        }
        return null;
    }

    public Integer getLineNumber() {
        return this.lineNumber;
    }

    public List<GameService> getGames() {
        return this.games;
    }
}
