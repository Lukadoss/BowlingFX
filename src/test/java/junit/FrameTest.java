package junit;

import es.ulpgc.bowling.entity.FrameEntity;
import es.ulpgc.bowling.entity.PlayerEntity;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class FrameTest {

    public FrameEntity frame;
    public PlayerEntity player;

    @Before
    public void init() {
        player = new PlayerEntity();
    }

    @Test
    public void constructorTest1() {
        frame = new FrameEntity(player, 0, 0);
        assertThat(frame.getPlayer()).isEqualTo(player);
        assertThat(frame.getRollIndex()).isZero();
        assertThat(frame.getFrameIndex()).isZero();
    }

    @Test
    public void constructorTest2() {
        frame = new FrameEntity(player, 18, 9);
        assertThat(frame.getRollIndex()).isEqualTo(18);
        assertThat(frame.getFrameIndex()).isEqualTo(9);
    }

    @Test
    public void isLastFrameTest1() {
        frame = new FrameEntity(player, 0, 0);
        assertThat(frame.isLastFrame()).isFalse();
    }

    @Test
    public void isLastFrameTest2() {
        frame = new FrameEntity(player, 18, 9);
        assertThat(frame.isLastFrame()).isTrue();
    }

    @Test
    public void setRollsTest1() {
        frame = new FrameEntity(player, 0, 0);
        frame.setRollOne(1);
        frame.setRollTwo(2);
        assertThat(frame.getRollOne()).isEqualTo(1);
        assertThat(frame.getRollTwo()).isEqualTo(2);
    }

    @Test
    public void setRollsTest2() {
        frame = new FrameEntity(player, 18, 9);
        frame.setRollOne(10);
        frame.setRollTwo(2);
        frame.setRollThree(5);
        assertThat(frame.getRollOne()).isEqualTo(10);
        assertThat(frame.getRollTwo()).isEqualTo(2);
        assertThat(frame.getRollThree()).isEqualTo(5);
    }
}
