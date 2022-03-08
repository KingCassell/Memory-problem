/**
 * @author Dustin Cassell
 * @since 7 Mar 2022
 * Purpose: Tests the test cases I could remember from the interview question as well as a couple of
 *          conditions that I thought should be checked such as an m being too large.
 */

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MinMemoryTest {
    MinimizeMemory mm = new MinimizeMemory();
    List<Integer> process = new ArrayList<>();

    @Test
    public void checkEmptyList() {
        int m = 1;
        assertEquals(0, mm.minimizeMemory(process, m));
    }

    @Test
    public void checkSingleItemList() {
        process.add(4);
        // current processes: [4]
        int m = 1;
        assertEquals(0, mm.minimizeMemory(process, m));
    }

    @Test
    public void checkM1() {
        process.add(4);
        process.add(10);
        process.add(4);
        // current processes: [4, 10, 4]
        int m = 1;
        assertEquals(8, mm.minimizeMemory(process, m));
    }

    @Test
    public void checkVariousMLeft() {
        process.add(4);
        process.add(10);
        process.add(4);
        process.add(3);
        process.add(2);
        // current processes: [4, 10, 4, 3, 2]
        int m = 2;
        assertEquals(9, mm.minimizeMemory(process, m));

        m = 3;
        assertEquals(5, mm.minimizeMemory(process, m));

        m = 4;
        assertEquals(2, mm.minimizeMemory(process, m));
    }

    @Test
    public void checkVariousMRight() {
        process.add(2);
        process.add(3);
        process.add(4);
        process.add(10);
        process.add(4);
        // current processes: [2, 3, 4, 10, 4]
        int m = 2;
        assertEquals(9, mm.minimizeMemory(process, m));

        m = 3;
        assertEquals(5, mm.minimizeMemory(process, m));

        m = 4;
        assertEquals(2, mm.minimizeMemory(process, m));
    }

    @Test
    public void checkTheBigRandomOne() {
        process.add(4);
        process.add(10);
        process.add(4);
        process.add(3);
        process.add(2);
        process.add(13);
        process.add(22);
        process.add(9);
        process.add(10);
        process.add(8);
        process.add(3);
        process.add(32);
        // current processes: [4, 10, 4, 3, 2, 13, 22, 9, 10, 8, 3, 32]
        int m = 2;
        assertEquals(85, mm.minimizeMemory(process, m));

        m = 4;
        assertEquals(66, mm.minimizeMemory(process, m));

        m = 6;
        assertEquals(36, mm.minimizeMemory(process, m));
    }

    @Test
    public void checkMTooLarge() throws Exception {
        process.add(4);
        process.add(10);
        process.add(4);
        // current processes: [4, 10, 4]
        int m = 4;
        assertEquals(-1, mm.minimizeMemory(process, m));
    }

    @Test
    public void checkMonsterCase() {
        // calculations only setup for even n values
        int n = 8402;
        int m = n / 2;
        int val, diff = calc(n) - calc(m);

        for (int i = 1; i <= n; ++i) {
            process.add(i);
        }
        val = calc(n) - diff - 1;
        assertEquals(val, mm.minimizeMemory(process, m));
    }


    /**
     * Purpose: Simple Factorial calculation for the monster test.
     * @param n Any Integer Value.
     * @return The factorial of the provided n value.
     */
    private int calc(int n) {
        if (n == 0) {
            return 1;
        }
        return n + calc(n - 1);
    }

}
