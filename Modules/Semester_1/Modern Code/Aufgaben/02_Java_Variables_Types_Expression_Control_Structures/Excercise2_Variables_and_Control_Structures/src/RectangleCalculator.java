public class RectangleCalculator
{
    /**
     * Calculate the area of a rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @return the area
     */
    public static int calculateArea(int width, int height) {
        return width * height;
    }

    /**
     * Calculate the perimeter of a rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @return the perimeter
     */
    public static int calculatePerimeter(int width, int height) {
        return 2 * (width + height);
    }

    /**
     * Calculate the diagonal length of a rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @return the diagonal length
     */
    public static double calculateDiagonal(int width, int height) {
        return Math.sqrt(width * width + height * height);
    }

    public static void main(String[] args)
    {
        // Declare variables for width and height
        int width = 5;
        int height = 3;

        // Calculate area, perimeter, and diagonal
        int area = calculateArea(width, height);
        int perimeter = calculatePerimeter(width, height);
        double diagonal = calculateDiagonal(width, height);

        // Display results with proper formatting
        System.out.println("=== Rectangle Calculator ===");
        System.out.println("Width: " + width);
        System.out.println("Height: " + height);
        System.out.println("Area: " + area);
        System.out.println("Perimeter: " + perimeter);
        System.out.printf("Diagonal length: %.2f%n", diagonal);
    }
}
