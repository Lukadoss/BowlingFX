package es.ulpgc.bowling.service;

import es.ulpgc.bowling.entity.Game;
import es.ulpgc.bowling.entity.Player;

import java.util.List;

public interface PlayerService {

    List<Player> findPlayersByGame(Game game);

    Player add(Game game);

    Player remove(Game game);

    Integer score(Player player);
}
