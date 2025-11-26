package excercises;

import tools.IoTools;

import java.util.Arrays;

public class ArrayReversalExcercise extends Excercise
{

    int[] reversedArray = new int[0];

    /**
     * Creates an ArrayReversalExcercise with a randomly generated array.
     *
     * @param arrayLength Length of the array
     * @param minValue    minimum value for random integers
     * @param maxValue    maximum value for random integers
     */
    public ArrayReversalExcercise(int arrayLength, int minValue, int maxValue)
    {
        super(arrayLength, minValue, maxValue);
    }

    /**
     * Creates an ArrayReversalExcercise with the provided array.
     *
     * @param inputArray integer array
     */
    public ArrayReversalExcercise(int[] inputArray)
    {
        super(inputArray);
    }

    /**
     * Starts the excercise logic
     */
    @Override
    public void runExcercise()
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
