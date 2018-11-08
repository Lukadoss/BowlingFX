package es.ulpgc.bowling.repository;

import es.ulpgc.bowling.entity.Game;
import org.springframework.data.repository.Repository;

public interface GameRepository extends Repository<Game, Long> {
}
