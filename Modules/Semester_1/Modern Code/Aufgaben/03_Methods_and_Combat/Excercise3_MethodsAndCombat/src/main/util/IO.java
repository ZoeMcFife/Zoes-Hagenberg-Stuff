package main.util;

import java.util.Scanner;

/**
 * Utility class for console input/output operations.
 * Provides static methods for printing to console and reading user input.
 * This class serves as a wrapper around System.out and Scanner for consistent I/O handling.
 */
public class IO
{
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Prints a line of text to the console followed by a newline.
     * 
     * @param text The text to print
     */
    public static void println(String text)
    {
        System.out.println(text);
    }

    /**
     * Prints a blank line to the console.
     */
    public static void println()
    {
        System.out.println();
    }

    /**
     * Prints text to the console without a trailing newline.
     * 
     * @param text The text to print
     */
    public static void print(String text)
    {
        System.out.print(text);
    }

    /**
     * Reads a line of input from the console.
     * Blocks until the user enters a line and presses Enter.
     * 
     * @return The line read from console input
     */
    public static String readln()
    {
        return scanner.nextLine();
    }
}
