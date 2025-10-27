import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive unit tests for RectangleCalculator
 * Tests all calculation methods with various inputs including edge cases
 */
@DisplayName("Rectangle Calculator Tests")
public class RectangleCalculatorTest {

    @Test
    @DisplayName("Calculate area with positive dimensions")
    public void testCalculateAreaPositive() {
        assertEquals(15, RectangleCalculator.calculateArea(5, 3));
        assertEquals(20, RectangleCalculator.calculateArea(4, 5));
        assertEquals(100, RectangleCalculator.calculateArea(10, 10));
    }

    @Test
    @DisplayName("Calculate area with unit dimensions")
    public void testCalculateAreaUnit() {
        assertEquals(1, RectangleCalculator.calculateArea(1, 1));
        assertEquals(5, RectangleCalculator.calculateArea(1, 5));
        assertEquals(7, RectangleCalculator.calculateArea(7, 1));
    }

    @Test
    @DisplayName("Calculate area with large dimensions")
    public void testCalculateAreaLarge() {
        assertEquals(1000000, RectangleCalculator.calculateArea(1000, 1000));
        assertEquals(12345000, RectangleCalculator.calculateArea(12345, 1000));
    }

    @Test
    @DisplayName("Calculate perimeter with positive dimensions")
    public void testCalculatePerimeterPositive() {
        assertEquals(16, RectangleCalculator.calculatePerimeter(5, 3));
        assertEquals(18, RectangleCalculator.calculatePerimeter(4, 5));
        assertEquals(40, RectangleCalculator.calculatePerimeter(10, 10));
    }

    @Test
    @DisplayName("Calculate perimeter with unit dimensions")
    public void testCalculatePerimeterUnit() {
        assertEquals(4, RectangleCalculator.calculatePerimeter(1, 1));
        assertEquals(12, RectangleCalculator.calculatePerimeter(1, 5));
        assertEquals(16, RectangleCalculator.calculatePerimeter(7, 1));
    }

    @Test
    @DisplayName("Calculate diagonal with 3-4-5 right triangle")
    public void testCalculateDiagonal345Triangle() {
        assertEquals(5.0, RectangleCalculator.calculateDiagonal(3, 4), 0.001);
        assertEquals(5.0, RectangleCalculator.calculateDiagonal(4, 3), 0.001);
    }

    @Test
    @DisplayName("Calculate diagonal with 5-12-13 right triangle")
    public void testCalculateDiagonal51213Triangle() {
        assertEquals(13.0, RectangleCalculator.calculateDiagonal(5, 12), 0.001);
        assertEquals(13.0, RectangleCalculator.calculateDiagonal(12, 5), 0.001);
    }

    @Test
    @DisplayName("Calculate diagonal with example from requirements")
    public void testCalculateDiagonalExample() {
        double diagonal = RectangleCalculator.calculateDiagonal(5, 3);
        assertEquals(5.83, diagonal, 0.01);
    }

    @Test
    @DisplayName("Calculate diagonal with square")
    public void testCalculateDiagonalSquare() {
        assertEquals(Math.sqrt(2), RectangleCalculator.calculateDiagonal(1, 1), 0.001);
        assertEquals(10 * Math.sqrt(2), RectangleCalculator.calculateDiagonal(10, 10), 0.001);
    }

    @Test
    @DisplayName("Calculate diagonal with unit dimensions")
    public void testCalculateDiagonalUnit() {
        assertEquals(1.0, RectangleCalculator.calculateDiagonal(1, 0), 0.001);
        assertEquals(1.0, RectangleCalculator.calculateDiagonal(0, 1), 0.001);
    }

    @Test
    @DisplayName("Verify area formula: width × height")
    public void testAreaFormulaCorrectness() {
        for (int w = 1; w <= 10; w++) {
            for (int h = 1; h <= 10; h++) {
                assertEquals(w * h, RectangleCalculator.calculateArea(w, h),
                    "Area should equal width × height");
            }
        }
    }

    @Test
    @DisplayName("Verify perimeter formula: 2 × (width + height)")
    public void testPerimeterFormulaCorrectness() {
        for (int w = 1; w <= 10; w++) {
            for (int h = 1; h <= 10; h++) {
                assertEquals(2 * (w + h), RectangleCalculator.calculatePerimeter(w, h),
                    "Perimeter should equal 2 × (width + height)");
            }
        }
    }

    @Test
    @DisplayName("Verify diagonal follows Pythagorean theorem")
    public void testDiagonalPythagoreanTheorem() {
        int width = 8;
        int height = 6;
        double diagonal = RectangleCalculator.calculateDiagonal(width, height);
        double expected = Math.sqrt(width * width + height * height);
        assertEquals(expected, diagonal, 0.0001,
            "Diagonal should follow Pythagorean theorem: √(w² + h²)");
    }
}
