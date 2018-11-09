package es.ulpgc.bowling.service;

import es.ulpgc.bowling.entity.Bowling;
import es.ulpgc.bowling.entity.Line;

import java.util.Collection;

public interface LineService {

    Collection<Line> findLinesByBowling(Bowling bowling);

    Collection<Line> findLinesThatHavePlayingGame(Bowling bowling);

    Line add(Bowling bowling);

    Line findByLineNumber(Integer lineNumber);

    Line findById(Long id);
}
