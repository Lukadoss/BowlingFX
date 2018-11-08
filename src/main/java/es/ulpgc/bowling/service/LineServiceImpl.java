package es.ulpgc.bowling.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LineServiceImpl {

    Logger logger = LoggerFactory.getLogger(LineServiceImpl.class);

    private Integer lineNumber;
    private List<GameServiceImpl> games;

    public LineServiceImpl(int lineNumber) {
        this(lineNumber, new ArrayList<>());
    }

    public LineServiceImpl(int lineNumber, ArrayList<GameServiceImpl> games) {
        this.lineNumber = lineNumber;
        this.games = games;
    }

    public LineServiceImpl addGame(GameServiceImpl game) {
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

    public GameServiceImpl getRunningGame() {
        for (GameServiceImpl game : games) {
            if (game.isRunning()) return game;
        }
        return null;
    }

    public Integer getLineNumber() {
        return this.lineNumber;
    }

    public List<GameServiceImpl> getGames() {
        return this.games;
    }
}
