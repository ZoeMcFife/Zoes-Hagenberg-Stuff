package main;

public class DiceRollingSimulator
{
    // random number between 1 and 6
    public int rollDice()
    {
        return (int)(Math.random() * 6) + 1;
    }

    public boolean isInputValid(int targetRoll)
    {
        if (targetRoll >= 1 && targetRoll <= 6)
        {
            return true;
        }

        return false;
    }

    public int simulateRollsUntilTarget(int targetRoll)
    {
        if (!isInputValid(targetRoll))
        {
            IO.println("Invalid Input");
            return -1;
        }

        IO.println("=== Dice Rolling Simulator ===");
        IO.println("Target number " + targetRoll);

        int diceRoll = -1;
        int count = 0;

        while (diceRoll != targetRoll)
        {
            diceRoll = rollDice();
            count++;

            IO.println("Roll " + count + ": \t" + diceRoll);
        }

        IO.println("Found target number " + targetRoll + " in " + count + " rolls!");

        return count;
    }
}
