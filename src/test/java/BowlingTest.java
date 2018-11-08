import es.ulpgc.bowling.service.BowlingServiceImpl;
import es.ulpgc.bowling.service.LineServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class BowlingTest {

    @Test
    public void constructorEmptyTest() {
        BowlingServiceImpl bowling = new BowlingServiceImpl();
        assertThat(bowling.getLines().size()).isZero();
    }

    @Test
    public void constructorOneLineTest() {
        List<LineServiceImpl> lines = new ArrayList<>();
        lines.add(new LineServiceImpl(1));
        BowlingServiceImpl bowling = new BowlingServiceImpl(lines);
        assertThat(bowling.getLines().size()).isEqualTo(1);
    }

    @Test
    public void constructorTwoLinesTest() {
        List<LineServiceImpl> lines = new ArrayList<>();
        lines.add(new LineServiceImpl(1));
        lines.add(new LineServiceImpl(2));
        BowlingServiceImpl bowling = new BowlingServiceImpl(lines);
        assertThat(bowling.getLines().size()).isEqualTo(2);
    }

    @Test
    public void addLineTest() {
        BowlingServiceImpl bowling = new BowlingServiceImpl();
        bowling.addLine(new LineServiceImpl(1));
        assertThat(bowling.getLines().size()).isEqualTo(1);
    }
}
