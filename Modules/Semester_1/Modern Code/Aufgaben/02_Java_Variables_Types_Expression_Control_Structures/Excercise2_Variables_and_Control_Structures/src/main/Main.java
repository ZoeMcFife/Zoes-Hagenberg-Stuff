package main;

import test.DayOfWeekTest;

public class Main
{
    public static void main(String[] args)
    {
        RectangleCalculator rectangleCalculator = new RectangleCalculator();
        rectangleCalculator.calculateRectangleProperties(5, 3);

        IO.println();

        FizzBuzz fizzBuzz = new FizzBuzz(15);
        fizzBuzz.runFizzBuzz();

        IO.println();

        DayOfWeek dayOfWeek = new DayOfWeek();
        dayOfWeek.checkDay(3);

        IO.println();

        StarPattern starPattern = new StarPattern(5);
        starPattern.generatePattern();
    }
}