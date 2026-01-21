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

        // only if number is 0
        if (number == 0)
        {
            if (str.isEmpty())
            {
                return "Zero";
            }

            return str.toString().trim();
        }

        if (number > 999)
        {
            int firstDigit = getFirstDigit(number);
            str.append(convertNumberToWord(firstDigit)).append(" Thousand ");
        }

        if (number > 99)
        {
            int firstDigit = getFirstDigit(number);
            str.append(convertNumberToWord(firstDigit)).append(" Hundred ");
        }

        if (number > 9)
        {
            str.append(convertTensToWord((number / 10) * 10)).append(" ");

            str.append(convertNumberToWord(getLastDigit(number))).append(" ");
        }

        if (number <= 9)
        {
            str.append(convertNumberToWord(number)).append(" ");
        }

        return numberToWords(removeFirstDigit(number));
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

    private static String convertTensToWord(int number)
    {
        if (number < 10 || number > 99)
        {
            throw new IllegalArgumentException("Number must be between 10 and 99");
        }

        String[] words = {
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
            "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen",
            "Twenty", "Thirty", "Forty", "Fifty", "Sixty",
            "Seventy", "Eighty", "Ninety"
        };

        if (number < 20)
        {
            return words[number - 10];
        }
        else
        {
            int firstDigit = getFirstDigit(number);
            return words[8 + firstDigit];
        }
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
