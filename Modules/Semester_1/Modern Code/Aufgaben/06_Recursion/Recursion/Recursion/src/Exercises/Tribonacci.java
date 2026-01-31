package Exercises;

public class Tribonacci
{
    public static int tribonacciMemoized(int n, int memoSize)
    {
        int[] memo = new int[memoSize];

        return tribonacciMemoized(n, memo);
    }

    private static int tribonacciMemoized(int n, int[] memo)
    {
        if (n <= 1)
        {
            return 0;
        }

        if (n <= 3)
        {
            return 1;
        }

        if (n >= memo.length)
        {
            return tribonacciMemoized(n - 1, memo) + tribonacciMemoized(n - 2, memo) + tribonacciMemoized(n - 3, memo);
        }
        if (memo[n] != 0)
        {
            return memo[n];
        }
        else
        {
            memo[n] = tribonacciMemoized(n - 1, memo) + tribonacciMemoized(n - 2, memo) + tribonacciMemoized(n - 3, memo);
            return memo[n];
        }
    }

    public static int tribonacciMemoized(int n)
    {
        return tribonacciMemoized(n, n + 1);
    }
}
