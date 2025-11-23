package main.ui;

import java.util.Scanner;

/**
 * Simple IO utility class for printing output and reading input.
 * This is a placeholder to allow the code to compile and tests to run.
 */
public class IO
{
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Prints a line of text to the console.
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
     * Prints text to the console without a newline.
     * @param text The text to print
     */
    public static void print(String text)
    {
        System.out.print(text);
    }

    /**
     * Reads a line of input from the console.
     * @return The line read
     */
    public static String readln()
    {
        return scanner.nextLine();
    }
}
