package excercises;

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
    private int[] array;

    /**
     * Creates an FuzzySearchExcercise with a randomly generated array.
     * @param arrayLength Length of the array
     * @param minValue minimum value for random integers
     * @param maxValue maximum value for random integers
     */
    public FuzzySearchExercise(int arrayLength, int minValue, int maxValue)
    {
        super(arrayLength, minValue, maxValue);
    }

    /**
     * Creates an FuzzySearchExcercise with the provided array.
     * @param array integer array
     */
    public FuzzySearchExercise(int[] array)
    {
        super(array);
    }

    @Override
    public void runExcercise()
    {

    }

    @Override
    public void displayOutput()
    {

    }
}
