package es.ulpgc.bowling.repository;

import es.ulpgc.bowling.entity.GameEntity;

import java.util.List;

/**
 * GameRepository class is a repository for GameEntity class
 *
 * @author David Bohmann
 */
public interface GameRepository extends BaseRepository<GameEntity> {
    List<GameEntity> findAllByIdGreaterThanEqualOrderByStartedDesc(Integer id);
}
