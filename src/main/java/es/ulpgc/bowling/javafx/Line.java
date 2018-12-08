package es.ulpgc.bowling.javafx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Line {

    Logger logger = LoggerFactory.getLogger(Line.class);

    private List<Game> games;

    private int fakeID;

    public Line() {
        this(new ArrayList<>());
    }

    public Line(ArrayList<Game> games) {
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

    public String getRunningGameName() {
        if (getRunningGame() != null) return getRunningGame().getName();
        return "No game playing";
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void setFakeID(int id) {
        this.fakeID = id;
    }

    public int getFakeID() {
        return fakeID;
    }

    public List<Game> getGames() {
        return this.games;
    }
}
