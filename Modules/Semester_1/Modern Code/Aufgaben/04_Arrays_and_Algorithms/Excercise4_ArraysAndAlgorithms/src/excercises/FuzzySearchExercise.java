package excercises;

import tools.IoTools;

import java.util.Arrays;

/**
 * Exercise 3 (4 points) Fuzzy Search: Find Value or Closest Value
 * Create a method that searches for a target value in an array. If the exact value is found, return its index. If not found, return the index of the closest value. The method should return the index (you may display output within the method).
 *
 * 1. Create an array of integers
 *
 * 2. Create a method that searches for a target value and returns the index (exact match or closest)
 *
 * 3. Display the search results
 *
 * 4. Test with values that exist and values that don’t exist
 *
 * **Expected Output:**
 * ```java
 * 1 === Fuzzy Search ===
 * 2 Array: [15, 8, 23, 4, 42, 11, 19]
 * 3 Searching for: 20
 * 4 Exact match not found.
 * 5 Closest value: 19 at index 6
 * 6 Difference: 1
 *
 * 8 Searching for: 8
 * 9 Found exact match at index 1
 * ```
 */

public class FuzzySearchExercise extends Exercise
{
    private FuzzySearchResult fuzzySearchResult;
    private int targetValue;

    /**
     * Creates an FuzzySearchExcercise with a randomly generated array.
     * @param arrayLength Length of the array
     * @param minValue minimum value for random integers
     * @param maxValue maximum value for random integers
     * @param targetValue target value to search for
     */
    public FuzzySearchExercise(int arrayLength, int minValue, int maxValue, int targetValue)
    {
        super(arrayLength, minValue, maxValue);
        setTargetValue(targetValue);
    }

    /**
     * Creates an FuzzySearchExcercise with the provided array.
     * @param array integer array
     * @param targetValue target value to search for
     */
    public FuzzySearchExercise(int[] array, int targetValue)
    {
        super(array);
        setTargetValue(targetValue);
    }

    @Override
    public void runExcercise()
    {
        setFuzzySearchResult(fuzzySearch(getTargetValue(), getInputArray()));
    }

    @Override
    public void displayOutput()
    {
        IoTools.printHeading("Fuzzy Search");
        IO.println("Array: " + Arrays.toString(getInputArray()));
        IO.println("Searching for: " + getTargetValue());

        if (fuzzySearchResult.foundExactMatch())
        {
            IO.println("Found exact match at index " + fuzzySearchResult.bestMatchIndex());
        }
        else
        {
            IO.println("Exact match not found.");
            IO.println("Closest value: " + fuzzySearchResult.bestMatchValue() + " at index " + fuzzySearchResult.bestMatchIndex());
            IO.println("Difference: " + Math.abs(fuzzySearchResult.bestMatchValue() - getTargetValue()));
        }
    }

    /**
     * Performs a fuzzy search for the target value in the array.
     * @param target target value to search for
     * @param array array to search within
     * @return FuzzySearchResult containing search results
     */
    private FuzzySearchResult fuzzySearch(int target, int[] array)
    {
        int difference = Integer.MAX_VALUE;
        int closestIndex = -1;

        for (int i = 0; i < array.length; i++)
        {
           if (array[i] == target)
           {
               return new FuzzySearchResult(true, i, array[i]);
           }

           int currentDifference = Math.abs(array[i] - target);

           if (currentDifference < difference)
           {
               difference = currentDifference;
               closestIndex = i;
           }
        }

        return new FuzzySearchResult(false, closestIndex, array[closestIndex]);
    }

    public void setTargetValue(int targetValue)
    {
        this.targetValue = targetValue;
    }

    public int getTargetValue()
    {
        return targetValue;
    }

    public void setFuzzySearchResult(FuzzySearchResult fuzzySearchResult)
    {
        this.fuzzySearchResult = fuzzySearchResult;
    }

    public FuzzySearchResult getFuzzySearchResult()
    {
        return fuzzySearchResult;
    }
}
