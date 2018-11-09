package es.ulpgc.bowling.service;

import es.ulpgc.bowling.entity.Bowling;
import es.ulpgc.bowling.repository.BowlingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.validation.Errors;

public class BowlingServiceImpl extends BaseServiceImpl<Bowling> implements BowlingService {

    @Autowired
    BowlingRepository repository;

    @Override
    public CrudRepository<Bowling, Long> getRepository() {
        return repository;
    }

    @Override
    public void specificValidator(Bowling entity, Errors errors) {
    }
}
