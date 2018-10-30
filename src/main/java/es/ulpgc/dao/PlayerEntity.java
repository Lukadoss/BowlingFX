package es.ulpgc.dao;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class PlayerEntity {

    private String name;

    private GameEntity game;

    private List<FrameEntity> frames;
}
