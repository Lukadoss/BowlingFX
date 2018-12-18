package junit;

import es.ulpgc.bowling.entity.PlayerEntity;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    PlayerEntity player;

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

}
