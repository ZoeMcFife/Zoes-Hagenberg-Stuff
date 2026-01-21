package UserInterface.Screens;

import Exercises.Fibonacci;
import UserInterface.Screen;
import UserInterface.UI;

public class FibonacciScreen extends Screen
{
    @Override
    public void startScreen()
    {
        UI.clearScreen();

        UI.printlnBlue("=== Fibonacci ===");

        UI.printBlankSeparatorLine();

        Fibonacci fibonacci = new Fibonacci();

        UI.printlnBlue("Iterative Approach:");

        UI.printlnCyan("F(8) = " + fibonacci.fibonacciIterative(8));
        UI.printlnCyan("F(7) = " + fibonacci.fibonacciIterative(7));
        UI.printlnCyan("F(6) = " + fibonacci.fibonacciIterative(6));
        UI.printlnCyan("F(5) = " + fibonacci.fibonacciIterative(5));
        UI.printlnCyan("F(4) = " + fibonacci.fibonacciIterative(4));
        UI.printlnCyan("F(3) = " + fibonacci.fibonacciIterative(3));
        UI.printlnCyan("F(2) = " + fibonacci.fibonacciIterative(2));
        UI.printlnCyan("F(1) = " + fibonacci.fibonacciIterative(1));
        UI.printlnCyan("F(0) = " + fibonacci.fibonacciIterative(0));

        UI.printBlankSeparatorLine();

        UI.printlnBlue("Recursive Approach:");

        UI.printlnCyan("F(8) = " + fibonacci.fibonacciRecursive(8));
        UI.printlnCyan("F(7) = " + fibonacci.fibonacciRecursive(7));
        UI.printlnCyan("F(6) = " + fibonacci.fibonacciRecursive(6));
        UI.printlnCyan("F(5) = " + fibonacci.fibonacciRecursive(5));
        UI.printlnCyan("F(4) = " + fibonacci.fibonacciRecursive(4));
        UI.printlnCyan("F(3) = " + fibonacci.fibonacciRecursive(3));
        UI.printlnCyan("F(2) = " + fibonacci.fibonacciRecursive(2));
        UI.printlnCyan("F(1) = " + fibonacci.fibonacciRecursive(1));
        UI.printlnCyan("F(0) = " + fibonacci.fibonacciRecursive(0));

        UI.printBlankSeparatorLine();

        UI.printlnBlue("Memoized Approach:");

        UI.printlnCyan("F(8) = " + fibonacci.fibonacciMemoized(8));
        UI.printlnCyan("F(7) = " + fibonacci.fibonacciMemoized(7));
        UI.printlnCyan("F(6) = " + fibonacci.fibonacciMemoized(6));
        UI.printlnCyan("F(5) = " + fibonacci.fibonacciMemoized(5));
        UI.printlnCyan("F(4) = " + fibonacci.fibonacciMemoized(4));
        UI.printlnCyan("F(3) = " + fibonacci.fibonacciMemoized(3));
        UI.printlnCyan("F(2) = " + fibonacci.fibonacciMemoized(2));
        UI.printlnCyan("F(1) = " + fibonacci.fibonacciMemoized(1));
        UI.printlnCyan("F(0) = " + fibonacci.fibonacciMemoized(0));

        UI.printBlankSeparatorLine();

        UI.printlnBlue("Time Comparison:");
        fibonacci.compareFibonacciMethodTimes(60);

        UI.waitForEnterKey();
    }
}
