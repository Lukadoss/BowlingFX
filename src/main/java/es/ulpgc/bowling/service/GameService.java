package es.ulpgc.bowling.service;

import es.ulpgc.bowling.entity.Game;
import es.ulpgc.bowling.entity.Line;
import es.ulpgc.bowling.entity.Player;

import java.util.Collection;
import java.util.List;

public interface GameService extends BaseService<Game> {

    Game addPlayer(Game g, Player p);

    Game removePlayer(Game g, Player p);

    Game startGame(Game g);

    Game endGame(Game g);

    Boolean isRunning(Game g);
}
