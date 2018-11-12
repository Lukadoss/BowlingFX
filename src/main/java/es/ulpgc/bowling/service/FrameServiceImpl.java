package es.ulpgc.bowling.service;

import es.ulpgc.bowling.entity.Frame;
import es.ulpgc.bowling.repository.FrameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
public class FrameServiceImpl extends BaseServiceImpl<Frame> implements FrameService {

    @Autowired
    FrameRepository repository;

    @Override
    public CrudRepository<Frame, Long> getRepository() {
        return repository;
    }

    @Override
    public void specificValidator(Frame entity, Errors errors) {

    }
}
