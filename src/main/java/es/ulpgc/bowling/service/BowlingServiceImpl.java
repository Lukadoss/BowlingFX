//package es.ulpgc.bowling.service;
//
//import es.ulpgc.bowling.entity.BowlingEntity;
//import es.ulpgc.bowling.repository.BowlingRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.validation.Errors;
//
//@Service
//public class BowlingServiceImpl extends BaseServiceImpl<BowlingEntity> implements BowlingService {
//
//    @Autowired
//    BowlingRepository repository;
//
//    @Override
//    public CrudRepository<BowlingEntity, Long> getRepository() {
//        return repository;
//    }
//
//    @Override
//    public void specificValidator(BowlingEntity entity, Errors errors) {
//    }
//}
