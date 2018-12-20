package integration;

import es.ulpgc.bowling.entity.GameEntity;
import es.ulpgc.bowling.entity.PlayerEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.stream;
import static org.assertj.core.api.Assertions.assertThat;

public class FullGameTest {

    private GameEntity game;
    private PlayerEntity player1, player2, player3, player4;

    private void rolled(PlayerEntity p, int... rolls) {
        stream(rolls).forEach(pins -> p.roll(pins));
    }

    @Before
    public void init() {
        player1 = new PlayerEntity("Player1");
        player2 = new PlayerEntity("Player2");
        player3 = new PlayerEntity("Player3");
        player4 = new PlayerEntity("Player4");
        game = new GameEntity("Test game", new ArrayList<PlayerEntity>(){{
            add(player1); add(player2); add(player3); add(player4);
        }});
    }

    @Test
    public void beforeStartTest() {
        assertThat(player1.sumScore()).isEqualTo(0);
        assertThat(player2.sumScore()).isEqualTo(0);
        assertThat(player3.sumScore()).isEqualTo(0);
        assertThat(player4.sumScore()).isEqualTo(0);

        assertThat(player1.getFrameCount()).isEqualTo(0);
        assertThat(player2.getFrameCount()).isEqualTo(0);
        assertThat(player3.getFrameCount()).isEqualTo(0);
        assertThat(player4.getFrameCount()).isEqualTo(0);

        assertThat(player1.getRollCount()).isEqualTo(0);
        assertThat(player2.getRollCount()).isEqualTo(0);
        assertThat(player3.getRollCount()).isEqualTo(0);
        assertThat(player4.getRollCount()).isEqualTo(0);

        assertThat(player1.getActualFrame()).isNull();
        assertThat(player2.getActualFrame()).isNull();
        assertThat(player3.getActualFrame()).isNull();
        assertThat(player4.getActualFrame()).isNull();

        assertThat(game.getTotalScore()).isEqualTo(0);
    }

    @Test
    public void afterFirstFrameTest() {
        rolled(player1, 0, 0);
        rolled(player2, 8, 1);
        rolled(player3, 7, 3);
        rolled(player4, 10);

        assertThat(player1.sumScore()).isEqualTo(0);
        assertThat(player2.sumScore()).isEqualTo(9);
        assertThat(player3.sumScore()).isEqualTo(0);
        assertThat(player4.sumScore()).isEqualTo(0);

        assertThat(player1.getFrameCount()).isEqualTo(1);
        assertThat(player2.getFrameCount()).isEqualTo(1);
        assertThat(player3.getFrameCount()).isEqualTo(1);
        assertThat(player4.getFrameCount()).isEqualTo(1);

        assertThat(player1.getRollCount()).isEqualTo(2);
        assertThat(player2.getRollCount()).isEqualTo(2);
        assertThat(player3.getRollCount()).isEqualTo(2);
        assertThat(player4.getRollCount()).isEqualTo(1);

        assertThat(player1.getActualFrame().getRollOne()).isEqualTo(0);
        assertThat(player1.getActualFrame().getRollTwo()).isEqualTo(0);
        assertThat(player2.getActualFrame().getRollOne()).isEqualTo(8);
        assertThat(player2.getActualFrame().getRollTwo()).isEqualTo(1);
        assertThat(player3.getActualFrame().getRollOne()).isEqualTo(7);
        assertThat(player3.getActualFrame().getRollTwo()).isEqualTo(3);
        assertThat(player4.getActualFrame().getRollOne()).isEqualTo(10);
        assertThat(player4.getActualFrame().getRollTwo()).isNull();

        assertThat(game.getTotalScore()).isEqualTo(9);
    }

    @Test
    public void duringSecondFrameTest() {
        rolled(player1, 0, 0, 0);
        rolled(player2, 8, 1, 5);
        rolled(player3, 7, 3, 5);
        rolled(player4, 10, 10);

        assertThat(player1.sumScore()).isEqualTo(0);
        assertThat(player2.sumScore()).isEqualTo(9);
        assertThat(player3.sumScore()).isEqualTo(15);
        assertThat(player4.sumScore()).isEqualTo(0);

        assertThat(player1.getFrameCount()).isEqualTo(2);
        assertThat(player2.getFrameCount()).isEqualTo(2);
        assertThat(player3.getFrameCount()).isEqualTo(2);
        assertThat(player4.getFrameCount()).isEqualTo(2);

        assertThat(player1.getRollCount()).isEqualTo(4);
        assertThat(player2.getRollCount()).isEqualTo(4);
        assertThat(player3.getRollCount()).isEqualTo(4);
        assertThat(player4.getRollCount()).isEqualTo(2);

        assertThat(player1.getActualFrame().getRollOne()).isEqualTo(0);
        assertThat(player1.getActualFrame().getRollTwo()).isNull();
        assertThat(player2.getActualFrame().getRollOne()).isEqualTo(5);
        assertThat(player2.getActualFrame().getRollTwo()).isNull();
        assertThat(player3.getActualFrame().getRollOne()).isEqualTo(5);
        assertThat(player3.getActualFrame().getRollTwo()).isNull();
        assertThat(player4.getActualFrame().getRollOne()).isEqualTo(10);
        assertThat(player4.getActualFrame().getRollTwo()).isNull();

        assertThat(game.getTotalScore()).isEqualTo(24);
    }

    @Test
    public void afterSecondFrameTest() {
        rolled(player1, 0, 0, 0, 0);
        rolled(player2, 8, 1, 5, 5);
        rolled(player3, 7, 3, 5, 2);
        rolled(player4, 10, 10);

        assertThat(player1.sumScore()).isEqualTo(0);
        assertThat(player2.sumScore()).isEqualTo(9);
        assertThat(player3.sumScore()).isEqualTo(22);
        assertThat(player4.sumScore()).isEqualTo(0);

        assertThat(player1.getFrameCount()).isEqualTo(2);
        assertThat(player2.getFrameCount()).isEqualTo(2);
        assertThat(player3.getFrameCount()).isEqualTo(2);
        assertThat(player4.getFrameCount()).isEqualTo(2);

        assertThat(player1.getRollCount()).isEqualTo(4);
        assertThat(player2.getRollCount()).isEqualTo(4);
        assertThat(player3.getRollCount()).isEqualTo(4);
        assertThat(player4.getRollCount()).isEqualTo(2);

        assertThat(player1.getActualFrame().getRollOne()).isEqualTo(0);
        assertThat(player1.getActualFrame().getRollTwo()).isEqualTo(0);
        assertThat(player2.getActualFrame().getRollOne()).isEqualTo(5);
        assertThat(player2.getActualFrame().getRollTwo()).isEqualTo(5);
        assertThat(player3.getActualFrame().getRollOne()).isEqualTo(5);
        assertThat(player3.getActualFrame().getRollTwo()).isEqualTo(2);
        assertThat(player4.getActualFrame().getRollOne()).isEqualTo(10);
        assertThat(player4.getActualFrame().getRollTwo()).isNull();

        assertThat(game.getTotalScore()).isEqualTo(31);
    }

    @Test
    public void afterThirdFrameTest() {
        rolled(player1, 0, 0, 0, 0, 0, 0);
        rolled(player2, 8, 1, 5, 5, 10);
        rolled(player3, 7, 3, 5, 2, 4, 2);
        rolled(player4, 10, 10, 10);

        assertThat(player1.sumScore()).isEqualTo(0);
        assertThat(player2.sumScore()).isEqualTo(29);
        assertThat(player3.sumScore()).isEqualTo(28);
        assertThat(player4.sumScore()).isEqualTo(30);

        assertThat(player1.getFrameCount()).isEqualTo(3);
        assertThat(player2.getFrameCount()).isEqualTo(3);
        assertThat(player3.getFrameCount()).isEqualTo(3);
        assertThat(player4.getFrameCount()).isEqualTo(3);

        assertThat(player1.getRollCount()).isEqualTo(6);
        assertThat(player2.getRollCount()).isEqualTo(5);
        assertThat(player3.getRollCount()).isEqualTo(6);
        assertThat(player4.getRollCount()).isEqualTo(3);

        assertThat(player1.getActualFrame().getRollOne()).isEqualTo(0);
        assertThat(player1.getActualFrame().getRollTwo()).isEqualTo(0);
        assertThat(player2.getActualFrame().getRollOne()).isEqualTo(10);
        assertThat(player2.getActualFrame().getRollTwo()).isNull();
        assertThat(player3.getActualFrame().getRollOne()).isEqualTo(4);
        assertThat(player3.getActualFrame().getRollTwo()).isEqualTo(2);
        assertThat(player4.getActualFrame().getRollOne()).isEqualTo(10);
        assertThat(player4.getActualFrame().getRollTwo()).isNull();

        assertThat(game.getTotalScore()).isEqualTo(87);
    }

    @Test
    public void afterNinthFrameTest() {
        rolled(player1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        rolled(player2, 8, 1, 5, 5, 10, 5, 3, 8, 1, 6, 2, 10, 5, 5, 10);
        rolled(player3, 7, 3, 5, 2, 4, 2, 8, 2, 5, 5, 1, 1, 6, 2, 8, 1, 9, 1);
        rolled(player4, 10, 10, 10, 10, 10, 10, 10, 10, 10);

        assertThat(player1.sumScore()).isEqualTo(0);
        assertThat(player2.sumScore()).isEqualTo(112);
        assertThat(player3.sumScore()).isEqualTo(73);
        assertThat(player4.sumScore()).isEqualTo(210);

        assertThat(player1.getFrameCount()).isEqualTo(9);
        assertThat(player2.getFrameCount()).isEqualTo(9);
        assertThat(player3.getFrameCount()).isEqualTo(9);
        assertThat(player4.getFrameCount()).isEqualTo(9);

        assertThat(player1.getRollCount()).isEqualTo(18);
        assertThat(player2.getRollCount()).isEqualTo(15);
        assertThat(player3.getRollCount()).isEqualTo(18);
        assertThat(player4.getRollCount()).isEqualTo(9);

        assertThat(player1.getActualFrame().getRollOne()).isEqualTo(0);
        assertThat(player1.getActualFrame().getRollTwo()).isEqualTo(0);
        assertThat(player2.getActualFrame().getRollOne()).isEqualTo(10);
        assertThat(player2.getActualFrame().getRollTwo()).isNull();
        assertThat(player3.getActualFrame().getRollOne()).isEqualTo(9);
        assertThat(player3.getActualFrame().getRollTwo()).isEqualTo(1);
        assertThat(player4.getActualFrame().getRollOne()).isEqualTo(10);
        assertThat(player4.getActualFrame().getRollTwo()).isNull();

        assertThat(game.getTotalScore()).isEqualTo(395);
    }

    @Test
    public void duringTenthFrameTest1() {
        rolled(player1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        rolled(player2, 8, 1, 5, 5, 10, 5, 3, 8, 1, 6, 2, 10, 5, 5, 10, 5);
        rolled(player3, 7, 3, 5, 2, 4, 2, 8, 2, 5, 5, 1, 1, 6, 2, 8, 1, 9, 1, 10);
        rolled(player4, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);

        assertThat(player1.sumScore()).isEqualTo(0);
        assertThat(player2.sumScore()).isEqualTo(112);
        assertThat(player3.sumScore()).isEqualTo(93);
        assertThat(player4.sumScore()).isEqualTo(240);

        assertThat(player1.getFrameCount()).isEqualTo(10);
        assertThat(player2.getFrameCount()).isEqualTo(10);
        assertThat(player3.getFrameCount()).isEqualTo(10);
        assertThat(player4.getFrameCount()).isEqualTo(10);

        assertThat(player1.getRollCount()).isEqualTo(21);
        assertThat(player2.getRollCount()).isEqualTo(18);
        assertThat(player3.getRollCount()).isEqualTo(21);
        assertThat(player4.getRollCount()).isEqualTo(12);

        assertThat(player1.getActualFrame().getRollOne()).isEqualTo(0);
        assertThat(player1.getActualFrame().getRollTwo()).isNull();
        assertThat(player2.getActualFrame().getRollOne()).isEqualTo(5);
        assertThat(player2.getActualFrame().getRollTwo()).isNull();
        assertThat(player3.getActualFrame().getRollOne()).isEqualTo(10);
        assertThat(player3.getActualFrame().getRollTwo()).isNull();
        assertThat(player4.getActualFrame().getRollOne()).isEqualTo(10);
        assertThat(player4.getActualFrame().getRollTwo()).isNull();

        assertThat(game.getTotalScore()).isEqualTo(445);
    }

    @Test
    public void duringTenthFrameTest2() {
        rolled(player1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        rolled(player2, 8, 1, 5, 5, 10, 5, 3, 8, 1, 6, 2, 10, 5, 5, 10, 5, 5);
        rolled(player3, 7, 3, 5, 2, 4, 2, 8, 2, 5, 5, 1, 1, 6, 2, 8, 1, 9, 1, 10, 2);
        rolled(player4, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);

        assertThat(player1.sumScore()).isEqualTo(0);
        assertThat(player2.sumScore()).isEqualTo(132);
        assertThat(player3.sumScore()).isEqualTo(93);
        assertThat(player4.sumScore()).isEqualTo(270);

        assertThat(player1.getFrameCount()).isEqualTo(10);
        assertThat(player2.getFrameCount()).isEqualTo(10);
        assertThat(player3.getFrameCount()).isEqualTo(10);
        assertThat(player4.getFrameCount()).isEqualTo(10);

        assertThat(player1.getRollCount()).isEqualTo(21);
        assertThat(player2.getRollCount()).isEqualTo(18);
        assertThat(player3.getRollCount()).isEqualTo(21);
        assertThat(player4.getRollCount()).isEqualTo(12);

        assertThat(player1.getActualFrame().getRollOne()).isEqualTo(0);
        assertThat(player1.getActualFrame().getRollTwo()).isEqualTo(0);
        assertThat(player2.getActualFrame().getRollOne()).isEqualTo(5);
        assertThat(player2.getActualFrame().getRollTwo()).isEqualTo(5);
        assertThat(player3.getActualFrame().getRollOne()).isEqualTo(10);
        assertThat(player3.getActualFrame().getRollTwo()).isEqualTo(2);
        assertThat(player4.getActualFrame().getRollOne()).isEqualTo(10);
        assertThat(player4.getActualFrame().getRollTwo()).isEqualTo(10);

        assertThat(game.getTotalScore()).isEqualTo(495);
    }

    @Test
    public void afterTenthFrameTest() {
        rolled(player1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        rolled(player2, 8, 1, 5, 5, 10, 5, 3, 8, 1, 6, 2, 10, 5, 5, 10, 5, 5, 5);
        rolled(player3, 7, 3, 5, 2, 4, 2, 8, 2, 5, 5, 1, 1, 6, 2, 8, 1, 9, 1, 10, 2, 8);
        rolled(player4, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);

        assertThat(player1.sumScore()).isEqualTo(0);
        assertThat(player2.sumScore()).isEqualTo(147);
        assertThat(player3.sumScore()).isEqualTo(113);
        assertThat(player4.sumScore()).isEqualTo(300);

        assertThat(player1.getFrameCount()).isEqualTo(10);
        assertThat(player2.getFrameCount()).isEqualTo(10);
        assertThat(player3.getFrameCount()).isEqualTo(10);
        assertThat(player4.getFrameCount()).isEqualTo(10);

        assertThat(player1.getRollCount()).isEqualTo(21);
        assertThat(player2.getRollCount()).isEqualTo(18);
        assertThat(player3.getRollCount()).isEqualTo(21);
        assertThat(player4.getRollCount()).isEqualTo(12);

        assertThat(player1.getActualFrame().getRollOne()).isEqualTo(0);
        assertThat(player1.getActualFrame().getRollTwo()).isEqualTo(0);
        assertThat(player1.getActualFrame().getRollThree()).isNull();
        assertThat(player2.getActualFrame().getRollOne()).isEqualTo(5);
        assertThat(player2.getActualFrame().getRollTwo()).isEqualTo(5);
        assertThat(player2.getActualFrame().getRollThree()).isEqualTo(5);
        assertThat(player3.getActualFrame().getRollOne()).isEqualTo(10);
        assertThat(player3.getActualFrame().getRollTwo()).isEqualTo(2);
        assertThat(player3.getActualFrame().getRollThree()).isEqualTo(8);
        assertThat(player4.getActualFrame().getRollOne()).isEqualTo(10);
        assertThat(player4.getActualFrame().getRollTwo()).isEqualTo(10);
        assertThat(player4.getActualFrame().getRollThree()).isEqualTo(10);

        assertThat(game.getTotalScore()).isEqualTo(560);
    }
}
