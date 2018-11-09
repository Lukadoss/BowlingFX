package es.ulpgc.bowling.service;

import es.ulpgc.bowling.entity.Game;
import es.ulpgc.bowling.entity.Line;

import java.util.Collection;

public interface GameService {

    Collection<Game> findGamesByLine(Line line);

    Game add(Line line);

    Collection<Game> findRunningGames();

    Game findRunningGameOnLine(Line line);

    Integer findLineNumber(Game game);

    Game findGameById(Long id);

    Game endGame(Game game);

    Boolean isRunning(Game game);

    Integer score(Game game);
}
