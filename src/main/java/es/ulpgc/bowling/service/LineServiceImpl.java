//package es.ulpgc.bowling.service;
//
//import es.ulpgc.bowling.entity.BowlingEntity;
//import es.ulpgc.bowling.entity.LineEntity;
//import es.ulpgc.bowling.repository.LineRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.validation.Errors;
//
//import java.util.Collection;
//
//@Service
//public class LineServiceImpl extends BaseServiceImpl<LineEntity> implements LineService {
//
//    @Autowired
//    LineRepository repository;
//
//    @Override
//    public CrudRepository<LineEntity, Long> getRepository() {
//        return repository;
//    }
//
//    @Override
//    public void specificValidator(LineEntity entity, Errors errors) {
//
//    }
//
//    @Override
//    public Collection<LineEntity> findLinesByBowling(BowlingEntity bowling) {
//        return null;
//    }
//
//    @Override
//    public Collection<LineEntity> findLinesThatHavePlayingGame(BowlingEntity bowling) {
//        return null;
//    }
//
//    @Override
//    public LineEntity findByLineNumber(Integer lineNumber) {
//        return null;
//    }
//}
