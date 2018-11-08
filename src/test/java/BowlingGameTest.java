import es.ulpgc.bowling.service.PlayerService;
import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.stream;
import static org.assertj.core.api.Assertions.assertThat;


public class BowlingGameTest {

    private PlayerService playerScore(int... rolls){
        PlayerService playerScore = new PlayerService("Luis");
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
        assertThat(playerScore(0).sumScore(0)).isNull();
    }

    @Test
    public void given_two_rolls_frames_should_be_one_and_score_is_sum_of_rolls() {
        assertThat(playerScore(0, 2).frames().size()).isEqualTo(1);
        assertThat(playerScore(0, 2).sumScore(0)).isEqualTo(2);
    }

    @Test
    public void given_three_rolls_frames_should_be_one_and_score_is_sum_of_rolls() {
        assertThat(playerScore(0, 2, 7).frames().size()).isEqualTo(2);
        assertThat(playerScore(0, 2, 7).sumScore(0)).isEqualTo(2);
        assertThat(playerScore(0, 2, 7).sumScore(1)).isNull();
    }

    @Test
    public void given_a_spare_in_the_last_frame_score_should_be_null() {
        assertThat(playerScore(0, 2, 7, 3).frames().size()).isEqualTo(2);
        assertThat(playerScore(0, 2, 7, 3).sumScore(0)).isEqualTo(2);
        assertThat(playerScore(0, 2, 7, 3).sumScore(1)).isNull();
    }

    @Test
    public void given_a_spare_in_a_frame_score_should_be_the_following_roll() {
        assertThat(playerScore(0, 2, 7, 3, 8).frames().size()).isEqualTo(3);
        assertThat(playerScore(0, 2, 7, 3, 8).sumScore(0)).isEqualTo(2);
        assertThat(playerScore(0, 2, 7, 3, 8).sumScore(1)).isEqualTo(20);
        assertThat(playerScore(0, 2, 7, 3, 8).sumScore(2)).isNull();
    }

    @Test
    public void given_a_spare_followed_by_strike_score_should_be_the_following_roll() {
        assertThat(playerScore(0, 2, 7, 3, 10).frames().size()).isEqualTo(3);
        assertThat(playerScore(0, 2, 7, 3, 10).sumScore(0)).isEqualTo(2);
        assertThat(playerScore(0, 2, 7, 3, 10).sumScore(1)).isEqualTo(22);
        assertThat(playerScore(0, 2, 7, 3, 10).sumScore(2)).isNull();
    }

    @Test
    public void given_a_strike_followed_by_roll_frame_should_be_null() {
        assertThat(playerScore(10, 3).frames().size()).isEqualTo(2);
        assertThat(playerScore(10, 3).sumScore(0)).isNull();
        assertThat(playerScore(10, 3).sumScore(1)).isNull();
    }

    @Test
    public void given_a_strike_followed_by_two_rolls_frame_should_be_two_and_score_sum_of_strike_and_second_roll() {
        assertThat(playerScore(10, 3, 3).frames().size()).isEqualTo(2);
        assertThat(playerScore(10, 3, 3).sumScore(0)).isEqualTo(16);
        assertThat(playerScore(10, 3, 3).sumScore(1)).isEqualTo(22);
    }

    @Test
    public void given_a_strike_followed_by_spare_frame_should_be_two_and_score_of_second_null() {
        assertThat(playerScore(10, 3, 7).frames().size()).isEqualTo(2);
        assertThat(playerScore(10, 3, 7).sumScore(0)).isEqualTo(20);
        assertThat(playerScore(10, 3, 7).sumScore(1)).isNull();
    }

    @Test
    public void given_a_strike_followed_by_spare_and_roll_frame_should_be_three_and_score_of_second_sum_of_strike_and_spare() {
        assertThat(playerScore(10, 3, 7, 5).frames().size()).isEqualTo(3);
        assertThat(playerScore(10, 3, 7, 5).sumScore(0)).isEqualTo(20);
        assertThat(playerScore(10, 3, 7, 5).sumScore(1)).isEqualTo(35);
        assertThat(playerScore(10, 3, 7, 5).sumScore(2)).isNull();
    }

    @Test
    public void given_a_strike_followed_by_strike_frame_should_be_two_and_score_null() {
        assertThat(playerScore(10, 10).frames().size()).isEqualTo(2);
        assertThat(playerScore(10, 10).sumScore(0)).isNull();
        assertThat(playerScore(10, 10).sumScore(1)).isNull();
    }

    @Test
    public void given_a_strike_followed_by_strike_followed_by_spare_frame_should_be_three_and_score_of_spare_null() {
        assertThat(playerScore(10, 10, 3, 7).frames().size()).isEqualTo(3);
        assertThat(playerScore(10, 10, 3, 7).sumScore(0)).isEqualTo(23);
        assertThat(playerScore(10, 10, 3, 7).sumScore(1)).isEqualTo(43);
        assertThat(playerScore(10, 10, 3, 7).sumScore(2)).isNull();
    }

    @Test
    public void given_a_strike_followed_by_strike_followed_by_rolls_frame_should_be_three_and_score_of_roll() {
        assertThat(playerScore(10, 10, 3, 3).frames().size()).isEqualTo(3);
        assertThat(playerScore(10, 10, 3, 3).sumScore(0)).isEqualTo(23);
        assertThat(playerScore(10, 10, 3, 3).sumScore(1)).isEqualTo(39);
        assertThat(playerScore(10, 10, 3, 3).sumScore(2)).isEqualTo(45);
    }

    @Test
    public void given_a_multiple_strike_followed_by_strike() {
        assertThat(playerScore(10, 10, 10, 10).frames().size()).isEqualTo(4);
        assertThat(playerScore(10, 10, 10, 10).sumScore(0)).isEqualTo(30);
        assertThat(playerScore(10, 10, 10, 10).sumScore(1)).isEqualTo(60);
        assertThat(playerScore(10, 10, 10, 10).sumScore(2)).isNull();
        assertThat(playerScore(10, 10, 10, 10).sumScore(3)).isNull();
    }

    @Test
    public void given_full_game_no_strikes_and_spares_frames_should_be_10_and_score_sum_of_rolls() {
        assertThat(playerScore(1, 5, 2, 4, 3, 3, 4, 2, 5, 1, 6, 0, 0, 6, 1, 5, 2, 4, 5, 4).frames().size()).isEqualTo(10);
        assertThat(playerScore(1, 5, 2, 4, 3, 3, 4, 2, 5, 1, 6, 0, 0, 6, 1, 5, 2, 4, 5, 4).sumScore(0)).isEqualTo(6);
        assertThat(playerScore(1, 5, 2, 4, 3, 3, 4, 2, 5, 1, 6, 0, 0, 6, 1, 5, 2, 4, 5, 4).sumScore(1)).isEqualTo(12);
        assertThat(playerScore(1, 5, 2, 4, 3, 3, 4, 2, 5, 1, 6, 0, 0, 6, 1, 5, 2, 4, 5, 4).sumScore(2)).isEqualTo(18);
        assertThat(playerScore(1, 5, 2, 4, 3, 3, 4, 2, 5, 1, 6, 0, 0, 6, 1, 5, 2, 4, 5, 4).sumScore(3)).isEqualTo(24);
        assertThat(playerScore(1, 5, 2, 4, 3, 3, 4, 2, 5, 1, 6, 0, 0, 6, 1, 5, 2, 4, 5, 4).sumScore(4)).isEqualTo(30);
        assertThat(playerScore(1, 5, 2, 4, 3, 3, 4, 2, 5, 1, 6, 0, 0, 6, 1, 5, 2, 4, 5, 4).sumScore(5)).isEqualTo(36);
        assertThat(playerScore(1, 5, 2, 4, 3, 3, 4, 2, 5, 1, 6, 0, 0, 6, 1, 5, 2, 4, 5, 4).sumScore(6)).isEqualTo(42);
        assertThat(playerScore(1, 5, 2, 4, 3, 3, 4, 2, 5, 1, 6, 0, 0, 6, 1, 5, 2, 4, 5, 4).sumScore(7)).isEqualTo(48);
        assertThat(playerScore(1, 5, 2, 4, 3, 3, 4, 2, 5, 1, 6, 0, 0, 6, 1, 5, 2, 4, 5, 4).sumScore(8)).isEqualTo(54);
        assertThat(playerScore(1, 5, 2, 4, 3, 3, 4, 2, 5, 1, 6, 0, 0, 6, 1, 5, 2, 4, 5, 4).sumScore(9)).isEqualTo(63);
    }

    @Test
    public void given_full_game_only_nonconsecutive_spares() {
        assertThat(playerScore(1, 9, 2, 2, 5, 5, 6, 1, 3, 7, 2, 2, 6, 4, 9, 0, 9, 1, 4, 4).frames().size()).isEqualTo(10);
        assertThat(playerScore(1, 9, 2, 2, 5, 5, 6, 1, 3, 7, 2, 2, 6, 4, 9, 0, 9, 1, 4, 4).sumScore(0)).isEqualTo(12);
        assertThat(playerScore(1, 9, 2, 2, 5, 5, 6, 1, 3, 7, 2, 2, 6, 4, 9, 0, 9, 1, 4, 4).sumScore(1)).isEqualTo(16);
        assertThat(playerScore(1, 9, 2, 2, 5, 5, 6, 1, 3, 7, 2, 2, 6, 4, 9, 0, 9, 1, 4, 4).sumScore(2)).isEqualTo(32);
        assertThat(playerScore(1, 9, 2, 2, 5, 5, 6, 1, 3, 7, 2, 2, 6, 4, 9, 0, 9, 1, 4, 4).sumScore(3)).isEqualTo(39);
        assertThat(playerScore(1, 9, 2, 2, 5, 5, 6, 1, 3, 7, 2, 2, 6, 4, 9, 0, 9, 1, 4, 4).sumScore(4)).isEqualTo(51);
        assertThat(playerScore(1, 9, 2, 2, 5, 5, 6, 1, 3, 7, 2, 2, 6, 4, 9, 0, 9, 1, 4, 4).sumScore(5)).isEqualTo(55);
        assertThat(playerScore(1, 9, 2, 2, 5, 5, 6, 1, 3, 7, 2, 2, 6, 4, 9, 0, 9, 1, 4, 4).sumScore(6)).isEqualTo(74);
        assertThat(playerScore(1, 9, 2, 2, 5, 5, 6, 1, 3, 7, 2, 2, 6, 4, 9, 0, 9, 1, 4, 4).sumScore(7)).isEqualTo(83);
        assertThat(playerScore(1, 9, 2, 2, 5, 5, 6, 1, 3, 7, 2, 2, 6, 4, 9, 0, 9, 1, 4, 4).sumScore(8)).isEqualTo(97);
        assertThat(playerScore(1, 9, 2, 2, 5, 5, 6, 1, 3, 7, 2, 2, 6, 4, 9, 0, 9, 1, 4, 4).sumScore(9)).isEqualTo(105);
    }

    @Test
    public void given_full_game_consecutive_spares() {
        assertThat(playerScore(1, 9, 2, 8, 5, 5, 6, 4, 3, 7, 2, 8, 6, 4, 9, 1, 9, 1, 4, 6, 4).frames().size()).isEqualTo(10);
        assertThat(playerScore(1, 9, 2, 8, 5, 5, 6, 4, 3, 7, 2, 8, 6, 4, 9, 1, 9, 1, 4, 6, 4).sumScore(0)).isEqualTo(12);
        assertThat(playerScore(1, 9, 2, 8, 5, 5, 6, 4, 3, 7, 2, 8, 6, 4, 9, 1, 9, 1, 4, 6, 4).sumScore(1)).isEqualTo(27);
        assertThat(playerScore(1, 9, 2, 8, 5, 5, 6, 4, 3, 7, 2, 8, 6, 4, 9, 1, 9, 1, 4, 6, 4).sumScore(2)).isEqualTo(43);
        assertThat(playerScore(1, 9, 2, 8, 5, 5, 6, 4, 3, 7, 2, 8, 6, 4, 9, 1, 9, 1, 4, 6, 4).sumScore(3)).isEqualTo(56);
        assertThat(playerScore(1, 9, 2, 8, 5, 5, 6, 4, 3, 7, 2, 8, 6, 4, 9, 1, 9, 1, 4, 6, 4).sumScore(4)).isEqualTo(68);
        assertThat(playerScore(1, 9, 2, 8, 5, 5, 6, 4, 3, 7, 2, 8, 6, 4, 9, 1, 9, 1, 4, 6, 4).sumScore(5)).isEqualTo(84);
        assertThat(playerScore(1, 9, 2, 8, 5, 5, 6, 4, 3, 7, 2, 8, 6, 4, 9, 1, 9, 1, 4, 6, 4).sumScore(6)).isEqualTo(103);
        assertThat(playerScore(1, 9, 2, 8, 5, 5, 6, 4, 3, 7, 2, 8, 6, 4, 9, 1, 9, 1, 4, 6, 4).sumScore(7)).isEqualTo(122);
        assertThat(playerScore(1, 9, 2, 8, 5, 5, 6, 4, 3, 7, 2, 8, 6, 4, 9, 1, 9, 1, 4, 6, 4).sumScore(8)).isEqualTo(136);
        assertThat(playerScore(1, 9, 2, 8, 5, 5, 6, 4, 3, 7, 2, 8, 6, 4, 9, 1, 9, 1, 4, 6, 4).sumScore(9)).isEqualTo(150);
    }

    @Test
    public void given_perfect_game() {
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).frames().size()).isEqualTo(10);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(0)).isEqualTo(30);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(1)).isEqualTo(60);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(2)).isEqualTo(90);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(3)).isEqualTo(120);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(4)).isEqualTo(150);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(5)).isEqualTo(180);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(6)).isEqualTo(210);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(7)).isEqualTo(240);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(8)).isEqualTo(270);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(9)).isEqualTo(300);
    }

    @Test
    public void given_perfect_game_missing_last_frame() {
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10).frames().size()).isEqualTo(10);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(0)).isEqualTo(30);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(1)).isEqualTo(60);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(2)).isEqualTo(90);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(3)).isEqualTo(120);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(4)).isEqualTo(150);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(5)).isEqualTo(180);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(6)).isEqualTo(210);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(7)).isEqualTo(240);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(8)).isNull();
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(9)).isNull();
    }

    @Test
    public void given_perfect_game_missing_last_frame_2() {
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).frames().size()).isEqualTo(10);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(0)).isEqualTo(30);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(1)).isEqualTo(60);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(2)).isEqualTo(90);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(3)).isEqualTo(120);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(4)).isEqualTo(150);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(5)).isEqualTo(180);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(6)).isEqualTo(210);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(7)).isEqualTo(240);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(8)).isEqualTo(270);
        assertThat(playerScore(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).sumScore(9)).isNull();
    }
}
