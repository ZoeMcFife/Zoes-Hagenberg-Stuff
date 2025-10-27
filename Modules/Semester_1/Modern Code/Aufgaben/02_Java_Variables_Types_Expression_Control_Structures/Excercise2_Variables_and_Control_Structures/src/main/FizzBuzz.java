package main;

public class FizzBuzz
{
    private final int maxCount;

    public FizzBuzz(int maxCount)
    {
        this.maxCount = maxCount;
    }

    // returns the fizz buzz depending on the number
    public String getFizzBuzz(int number)
    {
        if (number % 3 == 0 && number % 5 == 0)
        {
            return "FizzBuzz";
        }
        else if (number % 3 == 0)
        {
            return "Fizz";
        }
        else if (number % 5 == 0)
        {
            return "Buzz";
        }
        else
        {
            return Integer.toString(number);
        }
    }

    // runs the FizzBuzz loop
    public void runFizzBuzz()
    {
        IO.println("=== FizzBuzz Game ===");
        IO.println("Counting from 1 to " + maxCount);

        for (int i = 1; i <= maxCount; i++)
        {
            IO.println(getFizzBuzz(i));
        }
    }

}
