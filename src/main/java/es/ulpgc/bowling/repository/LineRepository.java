package es.ulpgc.bowling.repository;

import es.ulpgc.bowling.entity.LineEntity;

import java.util.List;

public interface LineRepository extends BaseRepository<LineEntity> {
    List<LineEntity> findByBowlingId(Integer id);
}
