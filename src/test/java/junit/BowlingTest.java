package junit;

import es.ulpgc.bowling.entity.BowlingEntity;
import es.ulpgc.bowling.entity.LineEntity;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class BowlingTest {

    @Test
    public void constructorEmptyTest() {
        BowlingEntity bowling = new BowlingEntity();
        assertThat(bowling.getLines().size()).isZero();
    }

    @Test
    public void constructorOneLineTest() {
        List<LineEntity> lines = new ArrayList<>();
        lines.add(new LineEntity());
        BowlingEntity bowling = new BowlingEntity(lines);
        assertThat(bowling.getLines().size()).isEqualTo(1);
    }

    @Test
    public void constructorTwoLinesTest() {
        List<LineEntity> lines = new ArrayList<>();
        lines.add(new LineEntity());
        lines.add(new LineEntity());
        BowlingEntity bowling = new BowlingEntity(lines);
        assertThat(bowling.getLines().size()).isEqualTo(2);
    }

    @Test
    public void addLineTest() {
        BowlingEntity bowling = new BowlingEntity();
        bowling.addLine(new LineEntity());
        assertThat(bowling.getLines().size()).isEqualTo(1);
    }
}
