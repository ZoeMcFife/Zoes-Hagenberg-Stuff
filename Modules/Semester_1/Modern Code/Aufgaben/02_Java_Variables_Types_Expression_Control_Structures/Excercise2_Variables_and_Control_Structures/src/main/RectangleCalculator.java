package main;

public class RectangleCalculator
{
    public double calculateArea(double width, double height)
    {
        return width * height;
    }

    public double calculatePerimeter(double width, double height)
    {
        return width * 2 + height * 2;
    }

    public double calculateDiagonal(double width, double height)
    {
        return Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
    }

    public void calculateRectangleProperties(double width, double height)
    {
        IO.println("=== Rectangle Calculator ===");
        IO.println("Width: \t" + width);
        IO.println("Height: \t" + height);
        IO.println("Area: \t" + calculateArea(width, height));
        IO.println("Perimeter: \t" + calculatePerimeter(width, height));
        IO.println("Diagonal: \t" + calculateDiagonal(width, height));
    }

}
