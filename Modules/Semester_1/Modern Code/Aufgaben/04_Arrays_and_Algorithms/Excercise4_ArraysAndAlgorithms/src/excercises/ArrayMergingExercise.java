package excercises;

import tools.ArrayTools;
import tools.IoTools;

/**
 * Exercise 4 (4 points) Merging Two Arrays
 * Create a method that merges two arrays into one and returns the merged array.
 *
 * 1. Create two arrays of integers
 *
 * 2. Create a method that merges them into a single array and returns the merged array
 *
 * 3. Display all three arrays (the two original arrays and the merged result)
 *
 * 4. Test with arrays of different sizes
 *
 * **Expected Output:**
 * ```java
 * 1 === Merging Two Arrays ===
 * 2 Array 1: [10, 20, 30]
 * 3 Array 2: [40, 50, 60, 70]
 * 4 Merged array: [10, 20, 30, 40, 50, 60, 70]
 * ```
 */
public class ArrayMergingExercise extends  Exercise
{
    /** Second input array to be merged with the first input array*/
    private int[] inputArray2;

    /** Merged output array*/
    private int[] mergedArray;

    /**
     * Creates an ArrayMergingExercise with a randomly generated array.
     *
     * @param arrayLength Length of the array
     * @param minValue    minimum value for random integers
     * @param maxValue    maximum value for random integers
     * @param arrayLength2 Length of the second array
     * @param minValue2    minimum value for random integers in the second array
     * @param maxValue2    maximum value for random integers in the second array
     */
    public ArrayMergingExercise(int arrayLength, int minValue, int maxValue, int arrayLength2, int minValue2, int maxValue2)
    {
        super(arrayLength, minValue, maxValue);
        setInputArray2(ArrayTools.generateRandomIntArray(arrayLength2, minValue2, maxValue2));
    }

    /**
     * Creates an ArrayMergingExercise with the provided array.
     *
     * @param inputArray integer array
     * @param inputArray2 second integer array
     */
    public ArrayMergingExercise(int[] inputArray, int[] inputArray2)
    {
        super(inputArray);
        setInputArray2(inputArray2);
    }

    /**
     * Starts the excercise logic
     */
    @Override
    public void runExercise()
    {
        setMergedArray(mergeArrays(getInputArray(), getInputArray2()));
    }

    /**
     * Displays the output of the excercise
     */
    @Override
    public void displayOutput()
    {
        IoTools.printHeading("Merging Two Arrays");
        IO.println("Array 1: " + java.util.Arrays.toString(getInputArray()));
        IO.println("Array 2: " + java.util.Arrays.toString(getInputArray2()));
        IO.println("Merged array: " + java.util.Arrays.toString(getMergedArray()));
    }

    /**
     * Merges two integer arrays into one.
     * @param array1 First integer array
     * @param array2 Second integer array
     * @return Merged integer array
     */
    private int[] mergeArrays(int[] array1, int[] array2)
    {
        int length1 = array1.length;
        int length2 = array2.length;
        int[] mergedArray = new int[length1 + length2];

        System.arraycopy(array1, 0, mergedArray, 0, length1);
        System.arraycopy(array2, 0, mergedArray, length1, length2);

        return mergedArray;
    }

    public int[] getInputArray2()
    {
        return inputArray2;
    }

    public void setInputArray2(int[] inputArray2)
    {
        this.inputArray2 = inputArray2;
    }

    public int[] getMergedArray()
    {
        return mergedArray;
    }

    public void setMergedArray(int[] mergedArray)
    {
        this.mergedArray = mergedArray;
    }
}
