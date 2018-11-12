package es.ulpgc.bowling.service;

import es.ulpgc.bowling.entity.Game;
import es.ulpgc.bowling.entity.Line;

import java.util.Collection;

public interface GameService extends BaseService<Game> {

    Collection<Game> findGamesByLine(Line line);

    Collection<Game> findRunningGames();

    Game findRunningGameOnLine(Line line);

    Integer findLineNumber(Game game);

    Game endGame(Game game);

    Boolean isRunning(Game game);

    Integer score(Game game);
}
