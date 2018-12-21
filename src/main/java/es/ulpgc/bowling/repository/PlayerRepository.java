package es.ulpgc.bowling.repository;

import es.ulpgc.bowling.entity.PlayerEntity;

import java.util.List;

/**
 * PlayerRepository class is a repository for PlayerEntity class
 *
 * @author David Bohmann
 */
public interface PlayerRepository extends BaseRepository<PlayerEntity> {
    List<PlayerEntity> findByIdGreaterThanEqualOrderByNameDesc(Integer id);
}
