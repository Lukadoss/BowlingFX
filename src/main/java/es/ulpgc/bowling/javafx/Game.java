package es.ulpgc.bowling.javafx;

//import es.ulpgc.bowling.controllers.GuiController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Game {

    Logger logger = LoggerFactory.getLogger(Game.class);

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private List<Player> players;

    private Integer totalScore;
    private String id;

    public Game(ArrayList<Player> players) {
        this.startTime = LocalDateTime.now();
        this.endTime = null;
        this.totalScore = 0;
        this.players = players;
        logger.debug("Startin game at time " + startTime);
    }

    public Game() {
        this(new ArrayList<>());
    }

    public Game addPlayer(Player player) {
        if (isRunning()) {
            if (!players.contains(player)) {
                this.players.add(player);
                logger.debug("Adding " + player.toString() + " to game " + this.toString());
            } else {
                logger.warn("Player " + player.toString() + " is already in game " + this.toString());
            }
        } else {
            logger.warn("Game " + this.toString() + "already finished");
        }
        return this;
    }

    public Game removePlayer(Player player) {
        if (isRunning()) {
            if (players.contains(player)) {
                this.players.remove(player);
                logger.debug("Removing " + player.toString() + " from game " + this.toString());
            } else {
                logger.warn("Player " + player.toString() + " is not in game " + this.toString());
            }
        } else {
            logger.warn("Game " + this.toString() + "already finished");
        }
        return this;
    }

    public void endGame() {
        this.endTime = LocalDateTime.now();
        logger.debug("Ending game at time " + endTime);
    }

    public boolean isRunning() {
        return this.endTime == null;
    }
 //    String name;
//    int players;
//    GuiController bc;
//
//    public Game(String name, int players) {
//        bc = new GuiController();
//        if (!name.isEmpty()) this.name = name;
//        else {
//            this.name = "Just a game";
//        }
//        this.players = players;
//
//    }
}
