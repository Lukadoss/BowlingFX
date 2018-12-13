package es.ulpgc.bowling.repository;

import es.ulpgc.bowling.entity.GameEntity;

import java.util.List;

public interface GameRepository extends BaseRepository<GameEntity> {
    List<GameEntity> findByLineId(Integer id);
    List<GameEntity> findAllByIdGreaterThanEqualOrderByStartedDesc(Integer id);
}
