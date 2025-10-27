package main;

public class Main
{
    public static void main(String[] args)
    {
        RectangleCalculator rectangleCalculator = new RectangleCalculator();
        rectangleCalculator.calculateRectangleProperties(5, 3);

        IO.println();

        FizzBuzz fizzBuzz = new FizzBuzz(15);
        fizzBuzz.runFizzBuzz();
    }
}