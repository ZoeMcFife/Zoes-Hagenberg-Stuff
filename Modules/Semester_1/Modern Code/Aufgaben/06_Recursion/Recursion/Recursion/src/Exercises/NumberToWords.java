package Exercises;

public class NumberToWords
{
    private static final int MAX_NUMBER = 9999;

    public static String numberToWords(int number)
    {
        return numberToWords(number, new StringBuilder());
    }

    private static String numberToWords(int number, StringBuilder str)
    {
        if (number < 0 || number > MAX_NUMBER)
        {
            throw new IllegalArgumentException("Number must be between 0 and " + MAX_NUMBER);
        }

        // thousands

        if (number >= 1000)
        {
            str.append(convertNumberToWord(getFirstDigit(number))).append(" Thousand ");

            return numberToWords(removeFirstDigit(number), str);
        }

        // hundreds
        if (number >= 100)
        {
            str.append(convertNumberToWord(getFirstDigit(number))).append(" Hundred ");
            return numberToWords(removeFirstDigit(number), str);
        }

        // tens
        if (number >= 20)
        {
            str.append(convertTensToWord(number)).append(" ");
            return numberToWords(removeFirstDigit(number), str);
        }

        // teens
        if (number >= 10)
        {
            str.append(convertTeensToWord(number)).append(" ");
            return str.toString().trim();
        }

        // units
        if (number > 0)
        {
            str.append(convertNumberToWord(number)).append(" ");
            return str.toString().trim();
        }

        if (str.isEmpty())
        {
            str.append(convertNumberToWord(0)).append(" ");
            return str.toString().trim();
        }

        return str.toString().trim();
    }

    private static String convertNumberToWord(int number)
    {
        if (number < 0 || number > 9)
        {
            throw new IllegalArgumentException("Number must be between 0 and 9");
        }

        String[] words = {
            "Zero", "One", "Two", "Three", "Four",
            "Five", "Six", "Seven", "Eight", "Nine"
        };

        return words[number];
    }

    private static String convertTeensToWord(int number)
    {
        if (number < 10 || number > 19)
        {
            throw new IllegalArgumentException("Number must be between 10 and 19");
        }

        String[] words = {
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
            "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
        };

        return words[number - 10];
    }

    private static String convertTensToWord(int number)
    {
        if (number < 20 || number > 99)
        {
            throw new IllegalArgumentException("Number must be between 10 and 99");
        }

        String[] words = {
            "Twenty", "Thirty", "Forty", "Fifty", "Sixty",
            "Seventy", "Eighty", "Ninety"
        };

        return words[(number / 10) - 2];
    }

    private static int getFirstDigit(int number)
    {
        while (number >= 10)
        {
            number /= 10;
        }
        return number;
    }

    private static int getLastDigit(int number)
    {
        return number % 10;
    }

    public static int removeFirstDigit(int number)
    {
        int divisor = 1;

        while (number / divisor >= 10)
        {
            divisor *= 10;
        }

        return number % divisor;
    }

}
