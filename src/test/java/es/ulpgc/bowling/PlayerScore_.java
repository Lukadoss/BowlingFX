package es.ulpgc.bowling;

import es.ulpgc.bowling.models.PlayerScore;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerScore_ {

    private PlayerScore playerScore;

    @Before
    public void setUp() {
        playerScore = new PlayerScore("mario");
    }

    @Test
    public void given_new_player_score_frames_should_be_zero() {
        assertThat(playerScore.frames().size()).isZero();
    }

    @Test
    public void given_one_roll_frames_should_be_one() {
        playerScore.roll(4);
        assertThat(playerScore.frames().size()).isEqualTo(1);
        assertThat(playerScore.frames().get(0).score()).isNull();
    }

    @Test
    public void given_two_rolls_frame_score_should_be_the_sum_of_them() {
        playerScore.roll(4).roll(3);
        assertThat(playerScore.frames().size()).isEqualTo(1);
        assertThat(playerScore.frames().get(0).score()).isEqualTo(7);
    }

    @Test
    public void given_three_rolls_frames_should_be_two() {
        playerScore.roll(4).roll(3).roll(8);
        assertThat(playerScore.frames().size()).isEqualTo(2);
        assertThat(playerScore.frames().get(0).score()).isEqualTo(7);
        assertThat(playerScore.frames().get(1).score()).isNull();
    }

    @Test
    public void given_a_spare_frame_score_should_be_null() {
        playerScore.roll(4).roll(6);
        assertThat(playerScore.frames().size()).isEqualTo(1);
        assertThat(playerScore.frames().get(0).score()).isNull();
    }

    @Test
    public void given_a_spare_and_a_roll_frame_score_should_be_the_sum_of_them() {
        playerScore.roll(4).roll(6).roll(5);
        assertThat(playerScore.frames().size()).isEqualTo(2);
        assertThat(playerScore.frames().get(0).score()).isEqualTo(15);
        assertThat(playerScore.frames().get(1).score()).isNull();
    }

    @Test
    public void given_a_strike_and_a_roll_frame_score_should_be_null() {
        playerScore.roll(10).roll(9);
        assertThat(playerScore.frames().size()).isEqualTo(2);
        assertThat(playerScore.frames().get(0).score()).isNull();
        assertThat(playerScore.frames().get(1).score()).isNull();
    }

    @Test
    public void given_a_strike_and_a_spare_frame_score_should_be_20() {
        playerScore.roll(10).roll(9).roll(1);
        assertThat(playerScore.frames().size()).isEqualTo(2);
        assertThat(playerScore.frames().get(0).score()).isEqualTo(20);
        assertThat(playerScore.frames().get(1).score()).isNull();
    }

    @Test
    public void given_a_perfect_game_total_score_should_be_300() {
        playerScore.roll(10).roll(10).roll(10).roll(10).roll(10).roll(10).roll(10).roll(10).roll(10).roll(10).roll(10).roll(10);
        assertThat(playerScore.frames().size()).isEqualTo(10);
        assertThat(playerScore.frames().stream().mapToInt(PlayerScore.Frame::score).sum()).isEqualTo(300);
    }

}
