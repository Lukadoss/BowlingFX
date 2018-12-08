import es.ulpgc.bowling.javafx.Bowling;
import es.ulpgc.bowling.javafx.Line;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class BowlingTest {

    @Test
    public void constructorEmptyTest() {
        Bowling bowling = new Bowling("");
        assertThat(bowling.getLines().size()).isZero();
    }

    @Test
    public void constructorOneLineTest() {
        List<Line> lines = new ArrayList<>();
        lines.add(new Line());
        Bowling bowling = new Bowling(lines,"");
        assertThat(bowling.getLines().size()).isEqualTo(1);
    }

    @Test
    public void constructorTwoLinesTest() {
        List<Line> lines = new ArrayList<>();
        lines.add(new Line());
        lines.add(new Line());
        Bowling bowling = new Bowling(lines,"");
        assertThat(bowling.getLines().size()).isEqualTo(2);
    }

    @Test
    public void addLineTest() {
        Bowling bowling = new Bowling("");
        bowling.addLine(new Line());
        assertThat(bowling.getLines().size()).isEqualTo(1);
    }
}
