package es.ulpgc.bowling.service;

import es.ulpgc.bowling.entity.Game;
import es.ulpgc.bowling.entity.Player;

import java.util.List;

public interface PlayerService extends BaseService<Player> {

    List<Player> findPlayersByGame(Game game);

    Integer score(Player player);
}
