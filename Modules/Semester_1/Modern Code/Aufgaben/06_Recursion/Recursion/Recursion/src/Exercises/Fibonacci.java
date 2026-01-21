package Exercises;

import UserInterface.UI;

public class Fibonacci
{
    public void compareFibonacciMethodTimes(int n)
    {
        UI.printlnBlue("Fibonacci method times for n = " + n + ":");
        UI.printAsteriskSeparatorLine();

        Runnable iterativeTask = () -> {
            long start = System.nanoTime();
            int result = fibonacciIterative(n);
            long duration = System.nanoTime() - start;
            IO.println("Iterative result: " + result + ", Time: " + duration + " ns");
        };

        Runnable recursiveTask = () -> {
            long start = System.nanoTime();
            int result = fibonacciRecursive(n);
            long duration = System.nanoTime() - start;
            IO.println("Recursive result: " + result + ", Time: " + duration + " ns");
        };

        Runnable memoizedTask = () -> {
            long start = System.nanoTime();
            int result = fibonacciMemoized(n);
            long duration = System.nanoTime() - start;
            IO.println("Memoized result (n + 1 memo): " + result + ", Time: " + duration + " ns");
        };

        Runnable memoSmallTask = () -> {
            long start = System.nanoTime();
            int result = fibonacciMemoized(n, 5);
            long duration = System.nanoTime() - start;
            IO.println("Memoized (small memo : 5) result: " + result + ", Time: " + duration + " ns");
        };

        Runnable memoMediumTask = () -> {
            long start = System.nanoTime();
            int result = fibonacciMemoized(n, 15);
            long duration = System.nanoTime() - start;
            IO.println("Memoized (medium memo : 15) result: " + result + ", Time: " + duration + " ns");
        };

        Runnable memoLargeTask = () -> {
            long start = System.nanoTime();
            int result = fibonacciMemoized(n, 30);
            long duration = System.nanoTime() - start;
            IO.println("Memoized (large memo : 30) result: " + result + ", Time: " + duration + " ns");
        };

        Thread t1 = new Thread(iterativeTask);
        Thread t2 = new Thread(recursiveTask);
        Thread t3 = new Thread(memoizedTask);
        Thread t4 = new Thread(memoSmallTask);
        Thread t5 = new Thread(memoMediumTask);
        Thread t6 = new Thread(memoLargeTask);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        try
        {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

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
