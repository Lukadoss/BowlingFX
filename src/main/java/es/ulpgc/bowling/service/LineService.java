package es.ulpgc.bowling.service;

import es.ulpgc.bowling.entity.Bowling;
import es.ulpgc.bowling.entity.Line;

import java.util.Collection;

public interface LineService extends BaseService<Line> {

    Collection<Line> findLinesByBowling(Bowling bowling);

    Collection<Line> findLinesThatHavePlayingGame(Bowling bowling);

    Line findByLineNumber(Integer lineNumber);
}
