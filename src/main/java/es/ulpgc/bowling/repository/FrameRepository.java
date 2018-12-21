package es.ulpgc.bowling.repository;

import es.ulpgc.bowling.entity.FrameEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FrameRepository extends BaseRepository<FrameEntity> {
    @Modifying
    @Transactional
    @Query("delete from FRAME e where PLAYER_ID = ?1")
    void deleteAllByPlayerId(Integer id);
}
