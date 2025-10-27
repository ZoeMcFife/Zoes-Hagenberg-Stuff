public class FizzBuzz
{
    /**
     * Get the FizzBuzz value for a given number
     * @param number the number to check
     * @return "FizzBuzz" if divisible by both 3 and 5,
     *         "Fizz" if divisible by 3,
     *         "Buzz" if divisible by 5,
     *         the number as a string otherwise
     */
    public static String getFizzBuzzValue(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return "FizzBuzz";
        } else if (number % 3 == 0) {
            return "Fizz";
        } else if (number % 5 == 0) {
            return "Buzz";
        } else {
            return String.valueOf(number);
        }
    }

    public static void main(String[] args)
    {
        // Variable to store the maximum number
        int maxNumber = 15;

        // Display header
        System.out.println("=== FizzBuzz Game ===");
        System.out.println("Counting from 1 to " + maxNumber + ":");

        // Loop through numbers from 1 to maxNumber
        for (int i = 1; i <= maxNumber; i++) {
            System.out.println(getFizzBuzzValue(i));
        }
    }
}
