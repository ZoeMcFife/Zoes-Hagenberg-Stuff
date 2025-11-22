package main;

/**
 * Simple I/O utility class for console input/output operations.
 * Provides static methods for printing to console with a consistent interface.
 */
public class IO
{
    /**
     * Prints a message to the console without a newline.
     * 
     * @param message The message to print
     */
    public static void print(String message)
    {
        System.out.print(message);
    }

    /**
     * Prints a message to the console with a newline.
     * 
     * @param message The message to print
     */
    public static void println(String message)
    {
        System.out.println(message);
    }

    /**
     * Prints an empty line to the console.
     */
    public static void println()
    {
        System.out.println();
    }
}
