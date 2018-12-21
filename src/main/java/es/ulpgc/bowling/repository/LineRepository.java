package es.ulpgc.bowling.repository;

import es.ulpgc.bowling.entity.LineEntity;

import java.util.List;

/**
 * LineRepository class is a repository for LineEntity class
 *
 * @author David Bohmann
 */
public interface LineRepository extends BaseRepository<LineEntity> {
    List<LineEntity> findByBowlingId(Integer id);
}
