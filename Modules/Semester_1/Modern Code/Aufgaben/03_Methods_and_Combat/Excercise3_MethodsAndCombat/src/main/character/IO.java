package main.character;

/**
 * Package-level IO import for convenient access.
 * All IO operations delegate to main.util.IO.
 */
public class IO
{
    public static void println(String text)
    {
        main.util.IO.println(text);
    }

    public static void println()
    {
        main.util.IO.println();
    }

    public static void print(String text)
    {
        main.util.IO.print(text);
    }

    public static String readln()
    {
        return main.util.IO.readln();
    }
}
