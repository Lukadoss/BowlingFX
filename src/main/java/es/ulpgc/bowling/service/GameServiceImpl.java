package es.ulpgc.bowling.service;

import es.ulpgc.bowling.entity.Game;
import es.ulpgc.bowling.entity.Line;
import es.ulpgc.bowling.entity.Player;
import es.ulpgc.bowling.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
public class GameServiceImpl extends BaseServiceImpl<Game> implements GameService{

    @Autowired
    GameRepository repository;

    @Autowired
    PlayerService playerService;

    @Override
    public CrudRepository<Game, Long> getRepository() {
        return repository;
    }

    @Override
    public void specificValidator(Game entity, Errors errors) {

    }

    @Override
    public Game addPlayer(Game g, Player p) {

        if (isRunning(g)) {
            if (!g.getPlayers().contains(p)) {
                g.getPlayers().add(p);

            } else {
//                logger.warn("Player " + player.toString() + " is already in game " + this.toString());
            }
        } else {
//            logger.warn("Game " + this.toString() + "already finished");
        }
        return g;
    }

    @Override
    public Game removePlayer(Game g, Player p) {
        if (isRunning(g)) {
            if (g.getPlayers().contains(p)) {
                g.getPlayers().remove(p);
//                logger.debug("Removing " + player.toString() + " from game " + this.toString());
            } else {
//                logger.warn("Player " + player.toString() + " is not in game " + this.toString());
            }
        } else {
//            logger.warn("Game " + this.toString() + "already finished");
        }
        return g;
    }

    @Override
    public Game startGame(Game g) {
        if (g == null) {
            g = new Game();
            g.setStarted(LocalDateTime.now());
            g.setEnded(null);
        }
        repository.save(g);
        return g;
    }

    @Override
    public Game endGame(Game g) {
        g.setEnded(LocalDateTime.now());
        repository.save(g);
        return g;
    }

    @Override
    public Boolean isRunning(Game g) {
        return g.getEnded() == null;
    }

    public PlayerService getPlayerService() {
        return this.playerService;
    }
}
