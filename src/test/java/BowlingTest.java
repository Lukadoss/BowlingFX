import es.ulpgc.bowling.service.BowlingService;
import es.ulpgc.bowling.service.LineService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class BowlingTest {

    @Test
    public void constructorEmptyTest() {
        BowlingService bowling = new BowlingService();
        assertThat(bowling.getLines().size()).isZero();
    }

    @Test
    public void constructorOneLineTest() {
        List<LineService> lines = new ArrayList<>();
        lines.add(new LineService(1));
        BowlingService bowling = new BowlingService(lines);
        assertThat(bowling.getLines().size()).isEqualTo(1);
    }

    @Test
    public void constructorTwoLinesTest() {
        List<LineService> lines = new ArrayList<>();
        lines.add(new LineService(1));
        lines.add(new LineService(2));
        BowlingService bowling = new BowlingService(lines);
        assertThat(bowling.getLines().size()).isEqualTo(2);
    }

    @Test
    public void addLineTest() {
        BowlingService bowling = new BowlingService();
        bowling.addLine(new LineService(1));
        assertThat(bowling.getLines().size()).isEqualTo(1);
    }
}
