package excercises;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercise 6 (4 points) Array Splitting: Evens and Odds
 * Create a method that splits an array into two arrays: one containing all even numbers and one containing all odd numbers. The method does not need to return anything and can just display the results directly.
 *
 * 1. Create an array of integers
 * 2. Split it into two arrays:
 *    - One array with all even numbers
 *    - One array with all odd numbers
 * 3. Preserve the original order within each group
 * 4. Display the original array and both resulting arrays
 *
 * **Expected Output:**
 *
 * ```java
 * 1 === Array Splitting: Evens and Odds ===
 * 2 Original array: [15, 8, 23, 4, 42, 11, 19]
 * 3 Even numbers: [8, 4, 42]
 * 4 Odd numbers: [15, 23, 11, 19]
 * ```
 */
public class ArraySplittingExercise extends Exercise
{
    private ArraySplittingResult arraySplittingResult;

    /**
     * Creates an ArraySplittingExercise with a randomly generated array.
     *
     * @param arrayLength Length of the array
     * @param minValue    minimum value for random integers
     * @param maxValue    maximum value for random integers
     */
    public ArraySplittingExercise(int arrayLength, int minValue, int maxValue)
    {
        super(arrayLength, minValue, maxValue);
    }

    /**
     * Creates an ArraySplittingExercise with the provided array.
     *
     * @param inputArray integer array
     */
    public ArraySplittingExercise(int[] inputArray)
    {
        super(inputArray);
    }

    /**
     * Starts the excercise logic
     */
    @Override
    public void runExercise()
    {
        setArraySplittingResult(splitArray(getInputArray()));
    }

    /**
     * Displays the output of the excercise
     */
    @Override
    public void displayOutput()
    {
        IO.println("Array Splitting Exercise: Even and Odds");
        IO.println("Original array: " + java.util.Arrays.toString(getInputArray()));
        IO.println("Even numbers: " + java.util.Arrays.toString(getArraySplittingResult().evenArray()));
        IO.println("Odd numbers: " + java.util.Arrays.toString(getArraySplittingResult().oddArray()));
    }

    /**
     * Splits the input array into even and odd numbers.
     *
     * @param array Input integer array
     * @return ArraySplittingResult containing even and odd arrays
     */
    private ArraySplittingResult splitArray(int[] array)
    {
        // does this even count? im just using lists instead of arrays.
        List<Integer> evenList = new ArrayList<Integer>();
        List<Integer> oddList = new ArrayList<Integer>();

        for (int num : array)
        {
            if (num % 2 == 0)
            {
                evenList.add(num);
            }
            else
            {
                oddList.add(num);
            }
        }

        int[] evenArray = evenList.stream().mapToInt(Integer::intValue).toArray();
        int[] oddArray = oddList.stream().mapToInt(Integer::intValue).toArray();

        return new ArraySplittingResult(evenArray, oddArray);

    }


    public ArraySplittingResult getArraySplittingResult() {
        return arraySplittingResult;
    }

    public void setArraySplittingResult(ArraySplittingResult arraySplittingResult) {
        this.arraySplittingResult = arraySplittingResult;
    }
}
