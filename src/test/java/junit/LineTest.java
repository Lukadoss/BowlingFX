package junit;

import es.ulpgc.bowling.entity.BowlingEntity;
import es.ulpgc.bowling.entity.GameEntity;
import es.ulpgc.bowling.entity.LineEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class LineTest {

    private LineEntity line;
    private GameEntity game1;
    private GameEntity game2;
    private BowlingEntity bowling;

    @Before
    public void init() {
        game1 = new GameEntity();
        game2 = new GameEntity();
        bowling = new BowlingEntity();
    }

    @Test
    public void testConstructorEmpty() {
        line = new LineEntity();
        assertThat(line.getGames()).isEmpty();
    }

    @Test
    public void testConstructorOneGame() {
        ArrayList<GameEntity> games = new ArrayList<GameEntity>() {{add(game1); }};
        line = new LineEntity(games);
        assertThat(line.getGames().size()).isEqualTo(1);
    }

    @Test
    public void testConstructorTwoGames() {
        ArrayList<GameEntity> games = new ArrayList<GameEntity>() {{add(game1); add(game2);}};
        line = new LineEntity(games);
        assertThat(line.getGames().size()).isEqualTo(2);
    }

    @Test
    public void testSetGames() {
        ArrayList<GameEntity> games = new ArrayList<GameEntity>() {{add(game1); }};
        line = new LineEntity();
        line.setGames(games);
        assertThat(line.getGames().size()).isEqualTo(1);
    }

    @Test
    public void testGetRunningGame1() {
        ArrayList<GameEntity> games = new ArrayList<GameEntity>() {{add(game1); }};
        line = new LineEntity(games);
        assertThat(line.getRunningGame()).isEqualTo(game1);
    }

    @Test
    public void testGetRunningGame2() {
        game1.endGame();
        ArrayList<GameEntity> games = new ArrayList<GameEntity>() {{add(game1); }};
        line = new LineEntity(games);
        assertThat(line.getRunningGame()).isNull();
    }

    @Test
    public void testSetBowling() {
        line = new LineEntity();
        line.setBowling(bowling);
        assertThat(line.getBowling()).isEqualTo(bowling);
    }
}
