package es.ulpgc.bowling.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Base repository is a parent to all repository classes in the project
 *
 * @param <T> Entity class
 * @author David Bohmann
 */
@NoRepositoryBean
public interface BaseRepository<T> extends CrudRepository<T, Integer> {

}
