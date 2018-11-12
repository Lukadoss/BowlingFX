package es.ulpgc.bowling.service;

import es.ulpgc.bowling.entity.Game;
import es.ulpgc.bowling.entity.Line;
import es.ulpgc.bowling.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.Collection;

@Service
public class GameServiceImpl extends BaseServiceImpl<Game> implements GameService{

    @Autowired
    GameRepository repository;

    @Override
    public CrudRepository<Game, Long> getRepository() {
        return repository;
    }

    @Override
    public void specificValidator(Game entity, Errors errors) {

    }

    @Override
    public Collection<Game> findGamesByLine(Line line) {
        return null;
    }

    @Override
    public Collection<Game> findRunningGames() {
        return null;
    }

    @Override
    public Game findRunningGameOnLine(Line line) {
        return null;
    }

    @Override
    public Integer findLineNumber(Game game) {
        return null;
    }

    @Override
    public Game endGame(Game game) {
        return null;
    }

    @Override
    public Boolean isRunning(Game game) {
        return null;
    }

    @Override
    public Integer score(Game game) {
        return null;
    }
}
