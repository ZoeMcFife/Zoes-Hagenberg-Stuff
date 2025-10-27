package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import main.RectangleCalculator;

public class RectangleCalculatorTest
{
    @Test
    public void testCalculateArea()
    {
        RectangleCalculator calculator = new RectangleCalculator();
        double length = 4.0;
        double width = 46.0;
        double expectedArea = 184.0;
        double actualArea = calculator.calculateArea(length, width);
        assertEquals(expectedArea, actualArea, "Area calculation is incorrect");
    }

    @Test
    public void testCalculatePerimeter()
    {
        RectangleCalculator calculator = new RectangleCalculator();
        double length = 5.0;
        double width = 3.0;
        double expectedPerimeter = 16.0;
        double actualPerimeter = calculator.calculatePerimeter(length, width);
        assertEquals(expectedPerimeter, actualPerimeter, "Perimeter calculation is incorrect");
    }

    @Test
    public void testCalculateDiagonal()
    {
        RectangleCalculator calculator = new RectangleCalculator();
        double length = 5.0;
        double width = 3.0;
        double expectedDiagonal = Math.sqrt(34); // sqrt(5^2 + 3^2)
        double actualDiagonal = calculator.calculateDiagonal(length, width);
        assertEquals(expectedDiagonal, actualDiagonal, "Diagonal calculation is incorrect");
    }
}
