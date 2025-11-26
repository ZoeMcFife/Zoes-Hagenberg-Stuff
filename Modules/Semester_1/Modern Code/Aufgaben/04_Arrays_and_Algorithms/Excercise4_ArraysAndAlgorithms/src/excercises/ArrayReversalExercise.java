package excercises;

import tools.IoTools;

import java.util.Arrays;

/**
 * Exercise 2 (4 points) Array Reversal: In-Place Algorithm
 * Create a method that reverses an array in-place (without using a second array) and returns the reversed array.
 *
 * 1. Create an array of integers
 *
 * 2. Create a method that reverses the array in-place by swapping elements and returns the reversed array
 *
 * 3. Display the array before and after reversal
 *
 * 4. **Important:** You must NOT create a second array. All operations must be done on the original array.
 *
 * **Expected Output:**
 *
 *
 * **Expected Output:**
 * ```java
 * 1 === Array Reversal ===
 * 2 Original array: [10, 20, 30, 40, 50]
 * 3 Reversed array: [50, 40, 30, 20, 10]
 * ```
 */

public class ArrayReversalExercise extends Exercise
{

    int[] reversedArray = new int[0];

    /**
     * Creates an ArrayReversalExcercise with a randomly generated array.
     *
     * @param arrayLength Length of the array
     * @param minValue    minimum value for random integers
     * @param maxValue    maximum value for random integers
     */
    public ArrayReversalExercise(int arrayLength, int minValue, int maxValue)
    {
        super(arrayLength, minValue, maxValue);
    }

    /**
     * Creates an ArrayReversalExcercise with the provided array.
     *
     * @param inputArray integer array
     */
    public ArrayReversalExercise(int[] inputArray)
    {
        super(inputArray);
    }

    /**
     * Starts the excercise logic
     */
    @Override
    public void runExercise()
    {
        reversedArray = reverseArray(getInputArray());
    }

    /**
     * Displays the output of the excercise
     */
    @Override
    public void displayOutput()
    {
        IoTools.printHeading("Array Reversal");
        IO.println("Original Array: " + Arrays.toString(getInputArray()));
        IO.println("Reversed Array: " + Arrays.toString(reversedArray));
    }

    /**
     * Reverses the given array without modifying the original array. (we don't want side effects do we :) )
     * @param array integer array to reverse
     * @return new integer array with elements in reverse order
     */
    private int[] reverseArray(int[] array)
    {
        int[] arrayCopy = Arrays.copyOf(array, array.length);

        int temp;

        for (int i = 0; i < arrayCopy.length / 2; i++)
        {
            temp = arrayCopy[i];
            arrayCopy[i] = arrayCopy[arrayCopy.length - 1 - i];
            arrayCopy[arrayCopy.length - 1 - i] = temp;
        }

        return arrayCopy;
    }

    public int[] getReversedArray()
    {
        return reversedArray;
    }
}
