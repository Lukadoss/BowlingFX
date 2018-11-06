package es.ulpgc.bowling.dao;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class GameEntity {

    private List<PlayerEntity> players;
}
