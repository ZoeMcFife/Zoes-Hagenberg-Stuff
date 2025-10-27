import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive unit tests for CollatzConjecture
 * Tests sequence generation, step counting, and edge cases
 */
@DisplayName("Collatz Conjecture Tests")
public class CollatzConjectureTest {

    @Test
    @DisplayName("Get next value for even number")
    public void testGetNextCollatzValueEven() {
        assertEquals(8, CollatzConjecture.getNextCollatzValue(16));
        assertEquals(4, CollatzConjecture.getNextCollatzValue(8));
        assertEquals(2, CollatzConjecture.getNextCollatzValue(4));
        assertEquals(1, CollatzConjecture.getNextCollatzValue(2));
    }

    @Test
    @DisplayName("Get next value for odd number")
    public void testGetNextCollatzValueOdd() {
        assertEquals(16, CollatzConjecture.getNextCollatzValue(5));
        assertEquals(10, CollatzConjecture.getNextCollatzValue(3));
        assertEquals(22, CollatzConjecture.getNextCollatzValue(7));
        assertEquals(28, CollatzConjecture.getNextCollatzValue(9));
    }

    @Test
    @DisplayName("Count steps for number 5 as per requirements")
    public void testCountSteps5() {
        assertEquals(5, CollatzConjecture.countSteps(5));
    }

    @Test
    @DisplayName("Count steps for number 1 (already at target)")
    public void testCountSteps1() {
        assertEquals(0, CollatzConjecture.countSteps(1));
    }

    @Test
    @DisplayName("Count steps for number 2")
    public void testCountSteps2() {
        assertEquals(1, CollatzConjecture.countSteps(2));
    }

    @Test
    @DisplayName("Count steps for number 4")
    public void testCountSteps4() {
        assertEquals(2, CollatzConjecture.countSteps(4));
    }

    @Test
    @DisplayName("Count steps for number 8")
    public void testCountSteps8() {
        assertEquals(3, CollatzConjecture.countSteps(8));
    }

    @Test
    @DisplayName("Count steps for number 16")
    public void testCountSteps16() {
        assertEquals(4, CollatzConjecture.countSteps(16));
    }

    @Test
    @DisplayName("Generate sequence for 5 as per requirements")
    public void testGenerateSequence5() {
        CollatzConjecture.CollatzStep[] sequence = CollatzConjecture.generateCollatzSequence(5);
        
        assertEquals(5, sequence.length, "Should have 5 steps");
        
        // Verify sequence: 5 -> 16 -> 8 -> 4 -> 2 -> 1
        assertEquals(5, sequence[0].value);
        assertFalse(sequence[0].isEven);
        assertEquals(16, sequence[0].nextValue);
        
        assertEquals(16, sequence[1].value);
        assertTrue(sequence[1].isEven);
        assertEquals(8, sequence[1].nextValue);
        
        assertEquals(8, sequence[2].value);
        assertTrue(sequence[2].isEven);
        assertEquals(4, sequence[2].nextValue);
        
        assertEquals(4, sequence[3].value);
        assertTrue(sequence[3].isEven);
        assertEquals(2, sequence[3].nextValue);
        
        assertEquals(2, sequence[4].value);
        assertTrue(sequence[4].isEven);
        assertEquals(1, sequence[4].nextValue);
    }

    @Test
    @DisplayName("Generate sequence for 1 (empty sequence)")
    public void testGenerateSequence1() {
        CollatzConjecture.CollatzStep[] sequence = CollatzConjecture.generateCollatzSequence(1);
        assertEquals(0, sequence.length, "Starting at 1 should produce empty sequence");
    }

    @Test
    @DisplayName("Generate sequence for 3")
    public void testGenerateSequence3() {
        CollatzConjecture.CollatzStep[] sequence = CollatzConjecture.generateCollatzSequence(3);
        
        // 3 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
        assertEquals(7, sequence.length);
        
        assertEquals(3, sequence[0].value);
        assertEquals(10, sequence[0].nextValue);
        
        assertEquals(10, sequence[1].value);
        assertEquals(5, sequence[1].nextValue);
        
        // Verify last step
        assertEquals(2, sequence[6].value);
        assertEquals(1, sequence[6].nextValue);
    }

    @Test
    @DisplayName("Generate sequence for 7")
    public void testGenerateSequence7() {
        CollatzConjecture.CollatzStep[] sequence = CollatzConjecture.generateCollatzSequence(7);
        
        // 7 -> 22 -> 11 -> 34 -> 17 -> 52 -> 26 -> 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
        assertEquals(16, sequence.length);
        
        assertEquals(7, sequence[0].value);
        assertFalse(sequence[0].isEven);
        assertEquals(22, sequence[0].nextValue);
    }

    @Test
    @DisplayName("Verify odd/even classification in sequence")
    public void testOddEvenClassification() {
        CollatzConjecture.CollatzStep[] sequence = CollatzConjecture.generateCollatzSequence(5);
        
        for (CollatzConjecture.CollatzStep step : sequence) {
            boolean expectedEven = (step.value % 2 == 0);
            assertEquals(expectedEven, step.isEven,
                "Value " + step.value + " should be classified as " + 
                (expectedEven ? "even" : "odd"));
        }
    }

    @Test
    @DisplayName("Verify next value calculation follows Collatz rules")
    public void testNextValueFollowsRules() {
        CollatzConjecture.CollatzStep[] sequence = CollatzConjecture.generateCollatzSequence(7);
        
        for (CollatzConjecture.CollatzStep step : sequence) {
            int expectedNext;
            if (step.isEven) {
                expectedNext = step.value / 2;
            } else {
                expectedNext = 3 * step.value + 1;
            }
            assertEquals(expectedNext, step.nextValue,
                "Next value for " + step.value + " should follow Collatz rules");
        }
    }

    @Test
    @DisplayName("Test sequence always ends at 1")
    public void testSequenceEndsAt1() {
        int[] testNumbers = {2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20, 27, 100};
        
        for (int num : testNumbers) {
            CollatzConjecture.CollatzStep[] sequence = CollatzConjecture.generateCollatzSequence(num);
            if (sequence.length > 0) {
                assertEquals(1, sequence[sequence.length - 1].nextValue,
                    "Sequence starting from " + num + " should end at 1");
            }
        }
    }

    @Test
    @DisplayName("Test count steps matches sequence length")
    public void testCountStepsMatchesSequenceLength() {
        int[] testNumbers = {2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20, 27};
        
        for (int num : testNumbers) {
            int stepCount = CollatzConjecture.countSteps(num);
            CollatzConjecture.CollatzStep[] sequence = CollatzConjecture.generateCollatzSequence(num);
            assertEquals(stepCount, sequence.length,
                "Step count should match sequence length for " + num);
        }
    }

    @Test
    @DisplayName("Test powers of 2 have logarithmic steps")
    public void testPowersOf2() {
        // Powers of 2 should take exactly log2(n) steps
        assertEquals(1, CollatzConjecture.countSteps(2));   // 2^1
        assertEquals(2, CollatzConjecture.countSteps(4));   // 2^2
        assertEquals(3, CollatzConjecture.countSteps(8));   // 2^3
        assertEquals(4, CollatzConjecture.countSteps(16));  // 2^4
        assertEquals(5, CollatzConjecture.countSteps(32));  // 2^5
        assertEquals(6, CollatzConjecture.countSteps(64));  // 2^6
    }

    @Test
    @DisplayName("Test known difficult number 27")
    public void testDifficultNumber27() {
        // 27 is known to take many steps (111 steps)
        int steps = CollatzConjecture.countSteps(27);
        assertEquals(111, steps);
        
        CollatzConjecture.CollatzStep[] sequence = CollatzConjecture.generateCollatzSequence(27);
        assertEquals(111, sequence.length);
    }

    @Test
    @DisplayName("Verify sequence values are always positive")
    public void testSequenceValuesPositive() {
        int[] testNumbers = {3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 27};
        
        for (int num : testNumbers) {
            CollatzConjecture.CollatzStep[] sequence = CollatzConjecture.generateCollatzSequence(num);
            for (CollatzConjecture.CollatzStep step : sequence) {
                assertTrue(step.value > 0, 
                    "All values in sequence should be positive");
                assertTrue(step.nextValue > 0, 
                    "All next values in sequence should be positive");
            }
        }
    }

    @Test
    @DisplayName("Test getNextCollatzValue formula for even numbers")
    public void testEvenFormulaCorrectness() {
        int[] evenNumbers = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
        
        for (int n : evenNumbers) {
            int expected = n / 2;
            assertEquals(expected, CollatzConjecture.getNextCollatzValue(n),
                "For even number " + n + ", next should be " + n + "/2 = " + expected);
        }
    }

    @Test
    @DisplayName("Test getNextCollatzValue formula for odd numbers")
    public void testOddFormulaCorrectness() {
        int[] oddNumbers = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        
        for (int n : oddNumbers) {
            int expected = 3 * n + 1;
            assertEquals(expected, CollatzConjecture.getNextCollatzValue(n),
                "For odd number " + n + ", next should be 3*" + n + "+1 = " + expected);
        }
    }

    @Test
    @DisplayName("Test sequence starts with the given number")
    public void testSequenceStartsWithGivenNumber() {
        int[] testNumbers = {5, 7, 10, 15, 20};
        
        for (int num : testNumbers) {
            CollatzConjecture.CollatzStep[] sequence = CollatzConjecture.generateCollatzSequence(num);
            if (sequence.length > 0) {
                assertEquals(num, sequence[0].value,
                    "Sequence should start with the given number " + num);
            }
        }
    }
}
