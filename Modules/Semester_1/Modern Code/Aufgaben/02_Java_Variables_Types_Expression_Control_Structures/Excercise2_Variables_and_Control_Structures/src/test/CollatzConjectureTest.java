package test;

import main.CollatzConjecture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CollatzConjectureTest
{

    private CollatzConjecture collatz;

    @BeforeEach
    void setUp()
    {
        collatz = new CollatzConjecture();
    }

    @Test
    void testProcessNumberEven()
    {
        assertEquals(5, collatz.processNumber(10),
                "Even number should be divided by 2");
    }

    @Test
    void testProcessNumberOdd()
    {
        assertEquals(10, collatz.processNumber(3),
                "Odd number should be 3 * n + 1");
    }

    @Test
    void testRunCollatzConjectureFor6()
    {
        // The Collatz sequence for 6 is: 6, 3, 10, 5, 16, 8, 4, 2, 1 → 8 steps
        assertEquals(8, collatz.runCollatzConjecture(6),
                "Collatz sequence starting from 6 should take 8 steps");
    }

    @Test
    void testRunCollatzConjectureFor1()
    {
        // Already at 1 → 0 steps
        assertEquals(0, collatz.runCollatzConjecture(1),
                "Starting with 1 should take 0 steps");
    }
}
