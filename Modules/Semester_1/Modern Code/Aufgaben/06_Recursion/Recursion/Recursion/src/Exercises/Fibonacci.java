package Exercises;

import UserInterface.UI;

public class Fibonacci
{
    public void compareFibonacciMethodTimes(int n)
    {
        long startTime = System.nanoTime();
        int resultIterative = fibonacciIterative(n);
        long endTime = System.nanoTime();
        long durationIterative = endTime - startTime;

        startTime = System.nanoTime();
        int resultRecursive = fibonacciRecursive(n);
        endTime = System.nanoTime();
        long durationRecursive = endTime - startTime;

        startTime = System.nanoTime();
        int resultMemoized = fibonacciMemoized(n);
        endTime = System.nanoTime();
        long durationMemoized = endTime - startTime;

        startTime = System.nanoTime();
        int resultMemoizedSmall = fibonacciMemoized(n, 5);
        endTime = System.nanoTime();
        long durationMemoizedSmall = endTime - startTime;

        startTime = System.nanoTime();
        int resultMemoizedMedium = fibonacciMemoized(n, 15);
        endTime = System.nanoTime();
        long durationMemoizedMedium = endTime - startTime;

        startTime = System.nanoTime();
        int resultMemoizedLarge = fibonacciMemoized(n, 30);
        endTime = System.nanoTime();
        long durationMemoizedLarge = endTime - startTime;

        UI.printlnBlue("Fibonacci method times for n = " + n + ":");
        UI.printAsteriskSeparatorLine();
        IO.println("Iterative result: " + resultIterative + ", Time: " + durationIterative + " ns");
        IO.println("Recursive result: " + resultRecursive + ", Time: " + durationRecursive + " ns");
        IO.println("Memoized result (n + 1 memo): " + resultMemoized + ", Time: " + durationMemoized + " ns");
        IO.println("Memoized (small memo : 5) result: " + resultMemoizedSmall + ", Time: " + durationMemoizedSmall + " ns");
        IO.println("Memoized (medium memo : 15) result: " + resultMemoizedMedium + ", Time: " + durationMemoizedMedium + " ns");
        IO.println("Memoized (large memo : 30) result: " + resultMemoizedLarge + ", Time: " + durationMemoizedLarge + " ns");
        UI.printAsteriskSeparatorLine();
    }

    public int fibonacciIterative(int n)
    {
        int a = 0, b = 1, c = 1;

        if (n == 0)
            return a;

        if (n == 1)
            return b;

        for (int i = 2; i <= n; i++)
        {
            c = a + b;
            a = b;
            b = c;
        }

        return c;
    }

    public int fibonacciRecursive(int n)
    {
        if (n <= 1)
        {
            return n;
        }

        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public int fibonacciMemoized(int n, int memoSize)
    {
        int[] memo = new int[memoSize];

        return fibonacciMemoized(n, memo);
    }

    private int fibonacciMemoized(int n, int[] memo)
    {
        if (n <= 1)
        {
            return n;
        }

        if (n >= memo.length)
        {
            return fibonacciMemoized(n - 1, memo) + fibonacciMemoized(n - 2, memo);
        }
        if (memo[n] != 0)
        {
            return memo[n];
        }
        else
        {
            memo[n] = fibonacciMemoized(n - 1, memo) + fibonacciMemoized(n - 2, memo);
            return memo[n];
        }
    }

    public int fibonacciMemoized(int n)
    {
        return fibonacciMemoized(n, n + 1);
    }



}
