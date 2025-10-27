package main;

public class StarPattern
{
    private final int patternSize;

    public StarPattern(int patternSize)
    {
        this.patternSize = patternSize;
    }

    public void generatePattern()
    {
        IO.println("=== Star Pattern Generator ===");
        IO.println("Pattern size: " + patternSize);

        for (int i = 1; i <= patternSize; i++)
        {
            for (int j = 1; j <= i; j++)
            {
                IO.print("* ");
            }

            IO.println("");
        }
    }
}
