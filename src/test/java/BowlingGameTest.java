import es.ulpgc.Game;
import es.ulpgc.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.stream;
import static org.assertj.core.api.Assertions.assertThat;


public class BowlingGameTest {

    private Player playerScore(int... rolls){
        Player playerScore = new Player("Luis");
        stream(rolls).forEach(pins -> playerScore.roll(pins));
        return playerScore;
    }

    @Before
    public void setUp() throws Exception{

    }
    
    @Test
    public void given_no_rolls_frames_should_be_zero() {
        assertThat(playerScore().frames().size()).isZero();
    }

    @Test
    public void given_one_roll_frames_should_be_one_and_score_is_null() {
        assertThat(playerScore(0).frames().size()).isEqualTo(1);
        assertThat(playerScore(0).frame(0).score()).isNull();
    }

    @Test
    public void given_two_rolls_frames_should_be_one_and_score_is_sum_of_rolls() {
        assertThat(playerScore(0, 2).frames().size()).isEqualTo(1);
        assertThat(playerScore(0, 2).frame(0).score()).isEqualTo(2);
    }

    @Test
    public void given_three_rolls_frames_should_be_one_and_score_is_sum_of_rolls() {
        assertThat(playerScore(0, 2, 7).frames().size()).isEqualTo(2);
        assertThat(playerScore(0, 2, 7).frame(0).score()).isEqualTo(2);
        assertThat(playerScore(0, 2, 7).frame(1).score()).isNull();
    }

    @Test
    public void given_a_spare_in_the_last_frame_score_should_be_null() {
        assertThat(playerScore(0, 2, 7, 3).frames().size()).isEqualTo(2);
        assertThat(playerScore(0, 2, 7, 3).frame(0).score()).isEqualTo(2);
        assertThat(playerScore(0, 2, 7, 3).frame(1).score()).isNull();
    }

    @Test
    public void given_a_spare_in_a_frame_score_should_be_the_following_roll() {
        assertThat(playerScore(0, 2, 7, 3, 8).frames().size()).isEqualTo(3);
        assertThat(playerScore(0, 2, 7, 3, 8).frame(0).score()).isEqualTo(2);
        assertThat(playerScore(0, 2, 7, 3, 8).frame(1).score()).isEqualTo(18);
        assertThat(playerScore(0, 2, 7, 3, 8).frame(2).score()).isNull();
    }

    @Test
    public void given_a_strike_followed_by_roll_frame_should_be_null() {
        assertThat(playerScore(10, 3).frames().size()).isEqualTo(2);
        assertThat(playerScore(10, 3).frame(0).score()).isNull();
        assertThat(playerScore(10, 3).frame(1).score()).isNull();
    }
}
