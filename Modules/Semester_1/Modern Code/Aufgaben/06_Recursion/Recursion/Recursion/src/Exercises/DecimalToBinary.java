package Exercises;

public class DecimalToBinary
{
    public static String decimalToBinary(int decimal)
    {
        return decimalToBinary(decimal, new StringBuilder());
    }

    private static String decimalToBinary(int decimal, StringBuilder binary)
    {
        // Base case
        if (decimal == 0)
        {
            // Handle the case where the input decimal number is 0
            if (binary.isEmpty())
            {
                return "0";
            }

            // Reverse the binary string since its constructed backwards
            return binary.reverse().toString();
        }

        binary.append(decimal % 2);

        return decimalToBinary(decimal / 2, binary);
    }
}
