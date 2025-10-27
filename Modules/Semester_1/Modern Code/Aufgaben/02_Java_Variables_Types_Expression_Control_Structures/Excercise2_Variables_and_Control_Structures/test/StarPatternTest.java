import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive unit tests for StarPattern
 * Tests pattern generation with various sizes and edge cases
 */
@DisplayName("Star Pattern Generator Tests")
public class StarPatternTest {

    @Test
    @DisplayName("Generate row 1 should have 1 star")
    public void testGenerateRow1() {
        assertEquals("*", StarPattern.generateRow(1));
    }

    @Test
    @DisplayName("Generate row 2 should have 2 stars")
    public void testGenerateRow2() {
        assertEquals("* *", StarPattern.generateRow(2));
    }

    @Test
    @DisplayName("Generate row 3 should have 3 stars")
    public void testGenerateRow3() {
        assertEquals("* * *", StarPattern.generateRow(3));
    }

    @Test
    @DisplayName("Generate row 5 should have 5 stars")
    public void testGenerateRow5() {
        assertEquals("* * * * *", StarPattern.generateRow(5));
    }

    @Test
    @DisplayName("Generate pattern size 1")
    public void testGeneratePatternSize1() {
        String[] pattern = StarPattern.generatePattern(1);
        assertEquals(1, pattern.length);
        assertEquals("*", pattern[0]);
    }

    @Test
    @DisplayName("Generate pattern size 2")
    public void testGeneratePatternSize2() {
        String[] pattern = StarPattern.generatePattern(2);
        assertEquals(2, pattern.length);
        assertEquals("*", pattern[0]);
        assertEquals("* *", pattern[1]);
    }

    @Test
    @DisplayName("Generate pattern size 3")
    public void testGeneratePatternSize3() {
        String[] pattern = StarPattern.generatePattern(3);
        assertEquals(3, pattern.length);
        assertEquals("*", pattern[0]);
        assertEquals("* *", pattern[1]);
        assertEquals("* * *", pattern[2]);
    }

    @Test
    @DisplayName("Generate pattern size 5 as per requirements")
    public void testGeneratePatternSize5() {
        String[] expected = {
            "*",
            "* *",
            "* * *",
            "* * * *",
            "* * * * *"
        };
        
        String[] pattern = StarPattern.generatePattern(5);
        assertEquals(5, pattern.length);
        
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], pattern[i],
                "Row " + (i + 1) + " should match expected pattern");
        }
    }

    @Test
    @DisplayName("Generate pattern size 7")
    public void testGeneratePatternSize7() {
        String[] pattern = StarPattern.generatePattern(7);
        assertEquals(7, pattern.length);
        
        // Verify each row has the correct number of stars
        for (int i = 0; i < pattern.length; i++) {
            int expectedStars = i + 1;
            long actualStars = pattern[i].chars().filter(ch -> ch == '*').count();
            assertEquals(expectedStars, actualStars,
                "Row " + (i + 1) + " should have " + expectedStars + " stars");
        }
    }

    @Test
    @DisplayName("Generate pattern size 10")
    public void testGeneratePatternSize10() {
        String[] pattern = StarPattern.generatePattern(10);
        assertEquals(10, pattern.length);
        
        // Verify pattern is progressive
        for (int i = 0; i < pattern.length; i++) {
            int expectedStars = i + 1;
            long actualStars = pattern[i].chars().filter(ch -> ch == '*').count();
            assertEquals(expectedStars, actualStars);
        }
    }

    @Test
    @DisplayName("Verify stars are separated by spaces")
    public void testStarsSeparatedBySpaces() {
        String row5 = StarPattern.generateRow(5);
        assertTrue(row5.contains(" "), "Stars should be separated by spaces");
        
        // Check that there's exactly one space between stars
        String[] parts = row5.split(" ");
        assertEquals(5, parts.length, "Should have 5 star parts separated by spaces");
        for (String part : parts) {
            assertEquals("*", part, "Each part should be a single star");
        }
    }

    @Test
    @DisplayName("Verify no trailing or leading spaces")
    public void testNoTrailingOrLeadingSpaces() {
        for (int i = 1; i <= 10; i++) {
            String row = StarPattern.generateRow(i);
            assertEquals(row.trim(), row, 
                "Row " + i + " should not have leading or trailing spaces");
        }
    }

    @Test
    @DisplayName("Verify row length increases with row number")
    public void testRowLengthIncreases() {
        String[] pattern = StarPattern.generatePattern(5);
        
        for (int i = 1; i < pattern.length; i++) {
            assertTrue(pattern[i].length() > pattern[i - 1].length(),
                "Row " + (i + 1) + " should be longer than row " + i);
        }
    }

    @Test
    @DisplayName("Test pattern array length matches requested size")
    public void testPatternArrayLength() {
        for (int size = 1; size <= 20; size++) {
            String[] pattern = StarPattern.generatePattern(size);
            assertEquals(size, pattern.length,
                "Pattern array should have exactly " + size + " rows");
        }
    }

    @Test
    @DisplayName("Test each row has correct star count")
    public void testEachRowStarCount() {
        String[] pattern = StarPattern.generatePattern(10);
        
        for (int i = 0; i < pattern.length; i++) {
            int rowNumber = i + 1;
            long starCount = pattern[i].chars().filter(ch -> ch == '*').count();
            assertEquals(rowNumber, starCount,
                "Row " + rowNumber + " should have exactly " + rowNumber + " stars");
        }
    }

    @Test
    @DisplayName("Verify pattern is right triangle shape")
    public void testRightTriangleShape() {
        String[] pattern = StarPattern.generatePattern(4);
        
        // First row: 1 star
        assertEquals(1, pattern[0].chars().filter(ch -> ch == '*').count());
        
        // Each subsequent row should have one more star
        for (int i = 1; i < pattern.length; i++) {
            long currentStars = pattern[i].chars().filter(ch -> ch == '*').count();
            long previousStars = pattern[i - 1].chars().filter(ch -> ch == '*').count();
            assertEquals(previousStars + 1, currentStars,
                "Each row should have exactly one more star than the previous row");
        }
    }

    @Test
    @DisplayName("Test large pattern size")
    public void testLargePatternSize() {
        int size = 50;
        String[] pattern = StarPattern.generatePattern(size);
        
        assertEquals(size, pattern.length);
        
        // Verify first and last rows
        assertEquals(1, pattern[0].chars().filter(ch -> ch == '*').count(),
            "First row should have 1 star");
        assertEquals(size, pattern[size - 1].chars().filter(ch -> ch == '*').count(),
            "Last row should have " + size + " stars");
    }

    @Test
    @DisplayName("Test pattern consistency across multiple calls")
    public void testPatternConsistency() {
        String[] pattern1 = StarPattern.generatePattern(5);
        String[] pattern2 = StarPattern.generatePattern(5);
        
        assertArrayEquals(pattern1, pattern2,
            "Multiple calls with same size should produce identical patterns");
    }
}
