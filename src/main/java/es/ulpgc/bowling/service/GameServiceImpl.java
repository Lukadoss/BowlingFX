package es.ulpgc.bowling.service;

//import es.ulpgc.bowling.controllers.GuiController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GameServiceImpl {

    Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private List<PlayerServiceImpl> players;

    private Integer totalScore;
    private String id;

    public GameServiceImpl(ArrayList<PlayerServiceImpl> players) {
        this.startTime = LocalDateTime.now();
        this.endTime = null;
        this.totalScore = 0;
        this.players = players;
        logger.debug("Startin game at time " + startTime);
    }

    public GameServiceImpl() {
        this(new ArrayList<>());
    }

    public GameServiceImpl addPlayer(PlayerServiceImpl player) {
        if (isRunning()) {
            if (!players.contains(player)) {
                this.players.add(player);
                logger.debug("Adding " + player.toString() + " to game " + this.toString());
            } else {
                logger.warn("PlayerServiceImpl " + player.toString() + " is already in game " + this.toString());
            }
        } else {
            logger.warn("GameServiceImpl " + this.toString() + "already finished");
        }
        return this;
    }

    public GameServiceImpl removePlayer(PlayerServiceImpl player) {
        if (isRunning()) {
            if (players.contains(player)) {
                this.players.remove(player);
                logger.debug("Removing " + player.toString() + " from game " + this.toString());
            } else {
                logger.warn("PlayerServiceImpl " + player.toString() + " is not in game " + this.toString());
            }
        } else {
            logger.warn("GameServiceImpl " + this.toString() + "already finished");
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
//    public GameServiceImpl(String name, int players) {
//        bc = new GuiController();
//        if (!name.isEmpty()) this.name = name;
//        else {
//            this.name = "Just a game";
//        }
//        this.players = players;
//
//    }
}
