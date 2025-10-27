import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive unit tests for DiceRollingSimulator
 * Tests dice rolling logic with deterministic input for testability
 */
@DisplayName("Dice Rolling Simulator Tests")
public class DiceRollingSimulatorTest {

    @Test
    @DisplayName("Roll dice returns value between 1 and 6")
    public void testRollDiceRange() {
        // Test multiple rolls to check range
        for (int i = 0; i < 100; i++) {
            int roll = DiceRollingSimulator.rollDice();
            assertTrue(roll >= 1 && roll <= 6,
                "Dice roll should be between 1 and 6, got: " + roll);
        }
    }

    @Test
    @DisplayName("Roll dice produces varied results")
    public void testRollDiceVariety() {
        boolean[] seen = new boolean[7]; // index 0 unused, 1-6 for dice values
        
        // Roll many times to see if we get variety
        for (int i = 0; i < 1000; i++) {
            int roll = DiceRollingSimulator.rollDice();
            seen[roll] = true;
        }
        
        // Check that we've seen multiple different values (at least 4 out of 6)
        int varietyCount = 0;
        for (int i = 1; i <= 6; i++) {
            if (seen[i]) varietyCount++;
        }
        
        assertTrue(varietyCount >= 4,
            "Should see at least 4 different dice values in 1000 rolls, saw: " + varietyCount);
    }

    @Test
    @DisplayName("Roll until target with custom roller - immediate hit")
    public void testRollUntilTargetImmediateHit() {
        int target = 6;
        
        // Custom roller that always returns the target
        java.util.function.Supplier<Integer> alwaysTarget = () -> target;
        
        DiceRollingSimulator.RollResult[] rolls = 
            DiceRollingSimulator.rollUntilTargetWithRoller(target, alwaysTarget);
        
        assertEquals(1, rolls.length, "Should need only 1 roll if first roll hits target");
        assertEquals(1, rolls[0].rollNumber);
        assertEquals(target, rolls[0].value);
    }

    @Test
    @DisplayName("Roll until target with custom roller - hit after 3 rolls")
    public void testRollUntilTargetAfter3Rolls() {
        int target = 6;
        int[] sequence = {3, 1, 4, 6}; // Will hit target on 4th roll
        final int[] index = {0};
        
        java.util.function.Supplier<Integer> sequenceRoller = () -> sequence[index[0]++];
        
        DiceRollingSimulator.RollResult[] rolls = 
            DiceRollingSimulator.rollUntilTargetWithRoller(target, sequenceRoller);
        
        assertEquals(4, rolls.length, "Should need 4 rolls based on sequence");
        
        assertEquals(1, rolls[0].rollNumber);
        assertEquals(3, rolls[0].value);
        
        assertEquals(2, rolls[1].rollNumber);
        assertEquals(1, rolls[1].value);
        
        assertEquals(3, rolls[2].rollNumber);
        assertEquals(4, rolls[2].value);
        
        assertEquals(4, rolls[3].rollNumber);
        assertEquals(6, rolls[3].value);
    }

    @Test
    @DisplayName("Roll until target with custom roller - example from requirements")
    public void testRollUntilTargetRequirementsExample() {
        int target = 6;
        int[] sequence = {3, 1, 4, 6}; // As shown in requirements example
        final int[] index = {0};
        
        java.util.function.Supplier<Integer> sequenceRoller = () -> sequence[index[0]++];
        
        DiceRollingSimulator.RollResult[] rolls = 
            DiceRollingSimulator.rollUntilTargetWithRoller(target, sequenceRoller);
        
        assertEquals(4, rolls.length);
        assertEquals(6, rolls[rolls.length - 1].value, "Last roll should be target");
    }

    @Test
    @DisplayName("Roll numbers increment correctly")
    public void testRollNumbersIncrement() {
        int target = 5;
        int[] sequence = {1, 2, 3, 4, 5};
        final int[] index = {0};
        
        java.util.function.Supplier<Integer> sequenceRoller = () -> sequence[index[0]++];
        
        DiceRollingSimulator.RollResult[] rolls = 
            DiceRollingSimulator.rollUntilTargetWithRoller(target, sequenceRoller);
        
        for (int i = 0; i < rolls.length; i++) {
            assertEquals(i + 1, rolls[i].rollNumber,
                "Roll number should increment from 1");
        }
    }

    @Test
    @DisplayName("Test with target 1")
    public void testWithTarget1() {
        int target = 1;
        int[] sequence = {2, 3, 4, 5, 6, 1};
        final int[] index = {0};
        
        java.util.function.Supplier<Integer> sequenceRoller = () -> sequence[index[0]++];
        
        DiceRollingSimulator.RollResult[] rolls = 
            DiceRollingSimulator.rollUntilTargetWithRoller(target, sequenceRoller);
        
        assertEquals(6, rolls.length);
        assertEquals(1, rolls[rolls.length - 1].value);
    }

    @Test
    @DisplayName("Test with target 3")
    public void testWithTarget3() {
        int target = 3;
        int[] sequence = {1, 2, 3};
        final int[] index = {0};
        
        java.util.function.Supplier<Integer> sequenceRoller = () -> sequence[index[0]++];
        
        DiceRollingSimulator.RollResult[] rolls = 
            DiceRollingSimulator.rollUntilTargetWithRoller(target, sequenceRoller);
        
        assertEquals(3, rolls.length);
        assertEquals(3, rolls[rolls.length - 1].value);
    }

    @Test
    @DisplayName("Test all rolls stored correctly in result")
    public void testAllRollsStoredCorrectly() {
        int target = 6;
        int[] sequence = {1, 2, 3, 4, 5, 6};
        final int[] index = {0};
        
        java.util.function.Supplier<Integer> sequenceRoller = () -> sequence[index[0]++];
        
        DiceRollingSimulator.RollResult[] rolls = 
            DiceRollingSimulator.rollUntilTargetWithRoller(target, sequenceRoller);
        
        assertEquals(6, rolls.length);
        
        for (int i = 0; i < rolls.length; i++) {
            assertEquals(sequence[i], rolls[i].value,
                "Roll value should match sequence");
        }
    }

    @Test
    @DisplayName("Test result always ends with target value")
    public void testResultEndsWithTarget() {
        for (int target = 1; target <= 6; target++) {
            int[] sequence = {1, 2, 3, 4, 5, 6};
            final int[] index = {0};
            
            java.util.function.Supplier<Integer> sequenceRoller = () -> sequence[index[0]++];
            
            DiceRollingSimulator.RollResult[] rolls = 
                DiceRollingSimulator.rollUntilTargetWithRoller(target, sequenceRoller);
            
            assertTrue(rolls.length > 0, "Should have at least one roll");
            assertEquals(target, rolls[rolls.length - 1].value,
                "Last roll should equal target " + target);
        }
    }

    @Test
    @DisplayName("Test RollResult stores correct data")
    public void testRollResultData() {
        DiceRollingSimulator.RollResult result = new DiceRollingSimulator.RollResult(5, 3);
        
        assertEquals(5, result.rollNumber);
        assertEquals(3, result.value);
    }

    @Test
    @DisplayName("Test multiple sequences produce correct roll counts")
    public void testMultipleSequences() {
        int[][] testCases = {
            {1},           // 1 roll
            {2, 1},        // 2 rolls
            {3, 3, 1},     // 3 rolls
            {5, 4, 3, 2, 1} // 5 rolls
        };
        
        for (int[] sequence : testCases) {
            int target = sequence[sequence.length - 1];
            final int[] index = {0};
            
            java.util.function.Supplier<Integer> sequenceRoller = () -> sequence[index[0]++];
            
            DiceRollingSimulator.RollResult[] rolls = 
                DiceRollingSimulator.rollUntilTargetWithRoller(target, sequenceRoller);
            
            assertEquals(sequence.length, rolls.length,
                "Should have " + sequence.length + " rolls");
        }
    }

    @Test
    @DisplayName("Test long sequence before hitting target")
    public void testLongSequence() {
        int target = 1;
        int[] sequence = {2, 3, 4, 5, 6, 2, 3, 4, 5, 6, 2, 3, 1};
        final int[] index = {0};
        
        java.util.function.Supplier<Integer> sequenceRoller = () -> sequence[index[0]++];
        
        DiceRollingSimulator.RollResult[] rolls = 
            DiceRollingSimulator.rollUntilTargetWithRoller(target, sequenceRoller);
        
        assertEquals(13, rolls.length);
        assertEquals(13, rolls[rolls.length - 1].rollNumber);
        assertEquals(1, rolls[rolls.length - 1].value);
    }

    @Test
    @DisplayName("Test roll numbers are sequential starting from 1")
    public void testRollNumbersSequential() {
        int target = 4;
        int[] sequence = {1, 2, 3, 4};
        final int[] index = {0};
        
        java.util.function.Supplier<Integer> sequenceRoller = () -> sequence[index[0]++];
        
        DiceRollingSimulator.RollResult[] rolls = 
            DiceRollingSimulator.rollUntilTargetWithRoller(target, sequenceRoller);
        
        for (int i = 0; i < rolls.length; i++) {
            assertEquals(i + 1, rolls[i].rollNumber,
                "Roll numbers should be sequential starting from 1");
        }
    }

    @Test
    @DisplayName("Statistical test: rollDice produces reasonable distribution")
    public void testRollDiceDistribution() {
        int[] counts = new int[7]; // index 0 unused
        int totalRolls = 6000;
        
        for (int i = 0; i < totalRolls; i++) {
            int roll = DiceRollingSimulator.rollDice();
            counts[roll]++;
        }
        
        // Each value should appear roughly 1000 times (±300 for randomness)
        // This is a loose check to ensure no value is heavily biased
        for (int i = 1; i <= 6; i++) {
            assertTrue(counts[i] > 700 && counts[i] < 1300,
                "Value " + i + " appeared " + counts[i] + " times, expected ~1000 (700-1300)");
        }
    }
}
