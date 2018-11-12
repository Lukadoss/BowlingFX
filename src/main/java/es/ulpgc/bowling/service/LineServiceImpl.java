package es.ulpgc.bowling.service;

import es.ulpgc.bowling.entity.Bowling;
import es.ulpgc.bowling.entity.Line;
import es.ulpgc.bowling.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.Collection;

@Service
public class LineServiceImpl extends BaseServiceImpl<Line> implements LineService {

    @Autowired
    LineRepository repository;

    @Override
    public CrudRepository<Line, Long> getRepository() {
        return repository;
    }

    @Override
    public void specificValidator(Line entity, Errors errors) {

    }

    @Override
    public Collection<Line> findLinesByBowling(Bowling bowling) {
        return null;
    }

    @Override
    public Collection<Line> findLinesThatHavePlayingGame(Bowling bowling) {
        return null;
    }

    @Override
    public Line findByLineNumber(Integer lineNumber) {
        return null;
    }
}
