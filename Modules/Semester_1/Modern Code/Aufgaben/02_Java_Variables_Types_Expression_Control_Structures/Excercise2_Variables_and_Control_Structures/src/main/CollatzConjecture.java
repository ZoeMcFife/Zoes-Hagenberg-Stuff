package main;

public class CollatzConjecture
{
    // either divides by 2 or multiplies by 3 and adds 1
    public int processNumber(int number)
    {
        if (number % 2 == 0)
        {
            return number / 2;
        }
        else
        {
            return number * 3 + 1;
        }
    }

    public int runCollatzConjecture(int startingNumber)
    {
        IO.println("=== Collatz Conjecture ===");
        IO.println("Starting with " + startingNumber);

        int steps = 0;
        int number = startingNumber;

        while (number != 1)
        {
            number = processNumber(number);
            steps++;
        }

        IO.println("Reached 1 in " + steps + " steps!");

        return steps;
    }
}

