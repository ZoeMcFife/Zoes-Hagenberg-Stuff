package excercises;

import tools.IoTools;

import java.util.*;

/**
 * Exercise 5 (4 points) Unique Values and Frequency Counting
 * Create a method that finds all unique values in an array and counts how often each value occurs. The method does not need to return anything and can just display the results directly.
 *
 * 1. Create an array that may contain duplicate values
 * 2. Find all unique values (like a set — each value appears only once in the result)
 * 3. Count how many times each unique value appears in the original array
 * 4. Display each unique value and its frequency
 *
 * **Expected Output:**
 * ```java
 * 1 === Unique Values and Frequency ===
 * 2 Original array: [5, 2, 8, 2, 5, 5, 1, 8]
 * 3 Unique values and frequencies:
 * 4 5 appears 3 times
 * 5 2 appears 2 times
 * 6 8 appears 2 times
 * 7 1 appears 1 time
 * ```
 */
public class UniqueValueAndFrequencyCountingExcercise extends Exercise
{
    private HashMap<Integer,Integer> uniqueValuesFrequency = new HashMap<>();

    /**
     * Creates an UniqueValueAndFrequencyCountingExcercise with a randomly generated array.
     *
     * @param arrayLength Length of the array
     * @param minValue    minimum value for random integers
     * @param maxValue    maximum value for random integers
     */
    public UniqueValueAndFrequencyCountingExcercise(int arrayLength, int minValue, int maxValue)
    {
        super(arrayLength, minValue, maxValue);
    }

    /**
     * Creates an UniqueValueAndFrequencyCountingExcercise with the provided array.
     *
     * @param inputArray integer array
     */
    public UniqueValueAndFrequencyCountingExcercise(int[] inputArray)
    {
        super(inputArray);
    }

    /**
     * Starts the excercise logic
     */
    @Override
    public void runExercise()
    {
        setUniqueValuesFrequency(countFrequencies(getInputArray()));
    }

    /**
     * Displays the output of the excercise
     */
    @Override
    public void displayOutput()
    {
        IoTools.printHeading("Unique Values and Frequency");
        IO.println("Original array: " + java.util.Arrays.toString(getInputArray()));
        IO.println("Unique values and frequencies:");

        for (Map.Entry<Integer,Integer> entry : uniqueValuesFrequency.entrySet())
        {
            int value = entry.getKey();
            int frequency = entry.getValue();
            String timeOrTimes = frequency == 1 ? "time" : "times";
            IO.println(value + " appears " + frequency + " " + timeOrTimes);
        }
    }

    private HashMap<Integer,Integer> countFrequencies(int[] array)
    {
        HashMap<Integer,Integer> frequencyHashMap = new HashMap<>();

        for (int value : array)
        {
            frequencyHashMap.merge(value, 1, Integer::sum);
        }
        return frequencyHashMap;
    }

    public HashMap<Integer,Integer> getUniqueValuesFrequency() {
        return uniqueValuesFrequency;
    }

    public void setUniqueValuesFrequency(HashMap<Integer,Integer> uniqueValuesFrequency) {
        this.uniqueValuesFrequency = uniqueValuesFrequency;
    }
}
