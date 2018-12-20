package junit;

import es.ulpgc.bowling.entity.FrameEntity;
import es.ulpgc.bowling.entity.GameEntity;
import es.ulpgc.bowling.entity.PlayerEntity;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    private PlayerEntity player;
    private GameEntity game;

    @Before
    public void init() {
        game = new GameEntity();
    }

    @Test
    public void testConstructorName() {
        player = new PlayerEntity("John");
        assertThat(player.getName()).isEqualTo("John");
        assertThat(player.getFrames()).isEmpty();
        assertThat(player.getRolls()).isEmpty();
    }

    @Test
    public void testConstructorNoName() {
        player = new PlayerEntity();
        assertThat(player.getName()).isEqualTo("Unknown");
        assertThat(player.getFrames()).isEmpty();
        assertThat(player.getRolls()).isEmpty();
    }

    @Test
    public void testSetName() {
        player = new PlayerEntity();
        player.setName("John");
        assertThat(player.getName()).isEqualTo("John");
    }

    @Test
    public void testSetGame() {
        player = new PlayerEntity();
        player.setGame(game);
        assertThat(player.getGame()).isEqualTo(game);
    }


}
