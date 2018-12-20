package junit;

import es.ulpgc.bowling.entity.BowlingEntity;
import es.ulpgc.bowling.entity.LineEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class BowlingTest {

    private BowlingEntity bowling;
    private LineEntity line1;
    private LineEntity line2;

    @Before
    public void init() {
        line1 = new LineEntity();
        line2 = new LineEntity();
    }

    @Test
    public void constructorEmptyTest() {
        bowling = new BowlingEntity();
        assertThat(bowling.getLines().size()).isZero();
        assertThat(bowling.getName()).isEqualTo("Unknown");
    }

    @Test
    public void constructorOneLineTest() {
        List<LineEntity> lines = new ArrayList<LineEntity>() {{add(line1);}};
        bowling = new BowlingEntity(lines);
        assertThat(bowling.getLines().size()).isEqualTo(1);
        assertThat(bowling.getName()).isEqualTo("Unknown");
    }

    @Test
    public void constructorTwoLinesTest() {
        List<LineEntity> lines = new ArrayList<LineEntity>() {{add(line1); add(line2);}};
        bowling = new BowlingEntity(lines);
        assertThat(bowling.getLines().size()).isEqualTo(2);
        assertThat(bowling.getName()).isEqualTo("Unknown");
    }

    @Test
    public void constructorOneLineWithNameTest() {
        List<LineEntity> lines = new ArrayList<LineEntity>() {{add(line1);}};
        bowling = new BowlingEntity(lines, "BowlingOne");
        assertThat(bowling.getLines().size()).isEqualTo(1);
        assertThat(bowling.getName()).isEqualTo("BowlingOne");
    }

    @Test
    public void addLineTest() {
        bowling = new BowlingEntity();
        bowling.addLine(line1);
        assertThat(bowling.getLines().size()).isEqualTo(1);
    }

    @Test
    public void setNameTest() {
        bowling = new BowlingEntity();
        bowling.setName("NewName");
        assertThat(bowling.getName()).isEqualTo("NewName");
    }

    @Test
    public void setLinesTest() {
        List<LineEntity> lines = new ArrayList<LineEntity>() {{add(line1); add(line2);}};
        bowling = new BowlingEntity();
        bowling.setLines(lines);
        assertThat(bowling.getLines().size()).isEqualTo(2);
    }
}
