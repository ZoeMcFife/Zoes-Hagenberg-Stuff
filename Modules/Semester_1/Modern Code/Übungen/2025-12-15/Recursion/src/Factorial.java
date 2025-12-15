public class Factorial
{
    public static long factorial(long n)
    {
        if (n <= 1)
        {
            return 1;
        }

        int[] array = new int[(int)n];

        return n * factorial(n - 1);
    }

    public static long factorialIterative(long n)
    {
        long result = 1;
        for (int i = 2; i <= n; i++)
        {
            result *= i;
        }
        return result;
    }
}
