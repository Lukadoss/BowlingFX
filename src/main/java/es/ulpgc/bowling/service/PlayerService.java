package es.ulpgc.bowling.service;

import es.ulpgc.bowling.entity.Game;
import es.ulpgc.bowling.entity.Player;
import es.ulpgc.bowling.entity.Frame;

import java.util.List;

public interface PlayerService extends BaseService<Player> {

    List<Player> findPlayersByGame(Game game);

    Integer score(Player p);

    Integer scoreUntilFrame(Player p, int frame);

    Frame getFrame(Player p, int i);

    List<Frame> getFrames(Player p);

    List<Integer> getRolls(Player p);
}
