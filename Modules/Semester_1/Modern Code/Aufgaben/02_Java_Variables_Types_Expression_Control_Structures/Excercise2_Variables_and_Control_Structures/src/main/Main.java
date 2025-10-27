package main;

/* Some Unit tests were generated using AI (But manually verified) for convenience. */

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

        IO.println();

        CollatzConjecture collatzConjecture = new CollatzConjecture();
        collatzConjecture.runCollatzConjecture(5);

        IO.println();

        DiceRollingSimulator diceRollingSimulator = new DiceRollingSimulator();
        diceRollingSimulator.simulateRollsUntilTarget(6);
    }
}