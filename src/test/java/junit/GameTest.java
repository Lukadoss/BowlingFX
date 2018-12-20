package junit;

import es.ulpgc.bowling.entity.GameEntity;
import es.ulpgc.bowling.entity.LineEntity;
import es.ulpgc.bowling.entity.PlayerEntity;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    private GameEntity game;
    private PlayerEntity player2;
    private PlayerEntity player1;
    private LineEntity line;

    @Before
    public void init() {
        player1 = new PlayerEntity("John");
        player2 = new PlayerEntity("Jack");
        line = new LineEntity();
    }

    @Test
    public void testConstructorNoParams() {
        game = new GameEntity();
        assertThat(game.getName()).isEqualTo("Unnamed");
        assertThat(game.getPlayers()).isEmpty();
        assertThat(game.getStarted()).isBeforeOrEqualTo(LocalDateTime.now());
        assertThat(game.getEnded()).isNull();
    }

    @Test
    public void testConstructorWithParams() {
        game = new GameEntity("NewGame", new ArrayList<PlayerEntity>(){{ add(player1); add(player2); }});
        assertThat(game.getName()).isEqualTo("NewGame");
        assertThat(game.getPlayers()).contains(player1, player2);
        assertThat(game.getStarted()).isBeforeOrEqualTo(LocalDateTime.now());
        assertThat(game.getEnded()).isNull();
    }

    @Test
    public void testSetPlayers() {
        game = new GameEntity();
        List<PlayerEntity> players = new ArrayList<PlayerEntity>(){{ add(player1); add(player2); }};
        game.setPlayers(players);
        assertThat(game.getPlayers()).contains(player1, player2);
    }

    @Test
    public void testSetLine() {
        game = new GameEntity();
        game.setLine(line);
        assertThat(game.getLine()).isEqualTo(line);
    }

    @Test
    public void testSetName() {
        game = new GameEntity();
        game.setName("NewGame");
        assertThat(game.getName()).isEqualTo("NewGame");
    }

    @Test
    public void testIsRunning() {
        game = new GameEntity("NewGame", new ArrayList<PlayerEntity>(){{ add(player1); add(player2); }});
        assertThat(game.isRunning()).isTrue();
        game.endGame();
        assertThat(game.isRunning()).isFalse();
    }

    @Test
    public void testGameDuration() {
        game = new GameEntity("NewGame", new ArrayList<PlayerEntity>(){{ add(player1); add(player2); }});
        assertThat(game.getGameDuration()).isEqualTo("Still running");
        game.endGame();
        assertThat(game.getGameDuration()).containsPattern(Pattern.compile("[0-9]+?h [0-9]+?m [0-9]+?s"));
    }

    @Test
    public void testAddPlayerExist() {
        game = new GameEntity("NewGame", new ArrayList<PlayerEntity>(){{ add(player1); }});
        assertThat(game.getPlayers().contains(player1)).isTrue();
        assertThat(game.getPlayers().contains(player2)).isFalse();
        game.addPlayer(player1);
        assertThat(game.getPlayers().contains(player1)).isTrue();
        assertThat(game.getPlayers().contains(player2)).isFalse();
    }

    @Test
    public void testAddPlayerNonExist() {
        game = new GameEntity("NewGame", new ArrayList<PlayerEntity>(){{ add(player1); }});
        assertThat(game.getPlayers().contains(player1)).isTrue();
        assertThat(game.getPlayers().contains(player2)).isFalse();
        game.addPlayer(player2);
        assertThat(game.getPlayers().contains(player1)).isTrue();
        assertThat(game.getPlayers().contains(player2)).isTrue();
    }

    @Test
    public void testRemovePlayerExist() {
        game = new GameEntity("NewGame", new ArrayList<PlayerEntity>(){{ add(player1); }});
        assertThat(game.getPlayers().contains(player1)).isTrue();
        assertThat(game.getPlayers().contains(player2)).isFalse();
        game.removePlayer(player1);
        assertThat(game.getPlayers().contains(player1)).isFalse();
        assertThat(game.getPlayers().contains(player2)).isFalse();
    }

    @Test
    public void testRemovePlayerNotExist() {
        game = new GameEntity("NewGame", new ArrayList<PlayerEntity>(){{ add(player1); }});
        assertThat(game.getPlayers().contains(player1)).isTrue();
        assertThat(game.getPlayers().contains(player2)).isFalse();
        game.removePlayer(player2);
        assertThat(game.getPlayers().contains(player1)).isTrue();
        assertThat(game.getPlayers().contains(player2)).isFalse();
    }


}
