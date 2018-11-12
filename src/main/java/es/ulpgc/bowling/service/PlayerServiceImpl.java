package es.ulpgc.bowling.service;

import es.ulpgc.bowling.entity.Bowling;
import es.ulpgc.bowling.entity.Game;
import es.ulpgc.bowling.entity.Line;
import es.ulpgc.bowling.entity.Player;
import es.ulpgc.bowling.repository.PlayerRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.Collection;
import java.util.List;

@Service
public class PlayerServiceImpl extends BaseServiceImpl<Player> implements PlayerService {

    @Autowired
    PlayerRepository repository;

    @Override
    public CrudRepository<Player, Long> getRepository() {
        return repository;
    }

    @Override
    public void specificValidator(Player entity, Errors errors) {

    }

    @Override
    public List<Player> findPlayersByGame(Game game) {
        return null;
    }

    @Override
    public Integer score(Player player) {
        return null;
    }
}
