package test;

import main.DiceRollingSimulator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiceRollingSimulatorTest
{
        private DiceRollingSimulator simulator;

        @BeforeEach
        void setUp()
        {
            simulator = new DiceRollingSimulator();
        }

        @Test
        void testRollDiceRange()
        {
            // Test several rolls to ensure all results are within 1–6
            for (int i = 0; i < 100; i++)
            {
                int roll = simulator.rollDice();
                assertTrue(roll >= 1 && roll <= 6,
                        "Roll must be between 1 and 6 but got " + roll);
            }
        }

        @Test
        void testIsInputValidTrue()
        {
            for (int i = 1; i <= 6; i++)
            {
                assertTrue(simulator.isInputValid(i),
                        "Expected " + i + " to be valid");
            }
        }

        @Test
        void testIsInputValidFalse()
        {
            int[] invalidValues = {0, -1, 7, 10, 999};
            for (int v : invalidValues)
            {
                assertFalse(simulator.isInputValid(v),
                        "Expected " + v + " to be invalid");
            }
        }

        @Test
        void testSimulateRollsUntilTargetInvalidInput()
        {
            // Invalid input should return -1
            int result = simulator.simulateRollsUntilTarget(9);
            assertEquals(-1, result, "Invalid input should return -1");
        }

        @Test
        void testSimulateRollsUntilTargetValidInput()
        {
            // Because rollDice() is random, we can only check that result > 0
            int result = simulator.simulateRollsUntilTarget(3);
            assertTrue(result > 0, "Should take at least one roll to reach the target");
        }
}
