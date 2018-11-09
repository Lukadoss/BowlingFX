package es.ulpgc.bowling.service;

import es.ulpgc.bowling.entity.BaseEntity;
import es.ulpgc.bowling.exception.ObjectNotFoundException;
import es.ulpgc.bowling.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Validator validator;

    @Override
    @Transactional(readOnly = true)
    public List<T> getAll() {
        Iterable<T> entities = getRepository().findAll();
        List<T> result = new ArrayList<>();
        entities.forEach(result::add);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public T get(Long id) throws ObjectNotFoundException {
        Optional<T> entity = getRepository().findById(id);
        if (entity.isPresent()) {
            T result = entity.get();
            return result;
        } else {
            throw new ObjectNotFoundException("Entity with id " + id + " not found.");
        }
    }

    @Override
    @Transactional
    public T add(@Valid T entity, Errors errors) {
        validator.validate(entity, errors);
        specificValidator(entity, errors);
        if (errors.hasErrors()) {
            logger.info("Validation error on {} - {}", entity, errors);
            throw new ValidationException(errors);
        }

        //entity.setUpdateTime(LocalDateTime.now());
        T result = getRepository().save(entity);
        return result;
    }

    @Override
    @Transactional
    public void delete(Long id) throws ObjectNotFoundException {
        if (id != null) {
            //perform update to set update time
            T entity = get(id);
            Errors errors = new BeanPropertyBindingResult(entity, "entity");
            //entity.setUpdateTime(LocalDateTime.now());
            T result = update(entity, errors);
            getRepository().delete(result);
        } else {
            throw new ObjectNotFoundException("Entity must have id set.");
        }
    }

    @Override
    @Transactional
    public void delete(T object) throws ObjectNotFoundException {
        delete(object.getId());
    }

    @Override
    @Transactional
    public T update(T entity, Errors errors) throws ObjectNotFoundException {
        if (entity.getId() == null) {
            throw new ObjectNotFoundException("Entity to update must have id set.");
        } else {
            validator.validate(entity, errors);
            specificValidator(entity, errors);
            if (errors.hasErrors()) {
                throw new ValidationException(errors);
            }
            if (exists(entity.getId())) {
                //entity.setUpdateTime(LocalDateTime.now());
                T result = getRepository().save(entity);
                return result;
            } else {
                throw new ObjectNotFoundException("Entity to update must have id set.");
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Long id) {
        boolean exists = (id != null) && getRepository().existsById(id);
        return exists;
    }

    @Override
    @Transactional(readOnly = true)
    public T find(Long id) {
        Optional<T> found = getRepository().findById(id);
        final T result;
        if (found.isPresent()) {
            result = found.get();
        } else {
            result = null;
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        long count = getRepository().count();
        return count;
    }

    public abstract CrudRepository<T, Long> getRepository();

    /**
     * Entity specific validator. If general validation should not be run throw {@link ValidationException}.
     */
    public abstract void specificValidator(T entity, Errors errors);

}

