package excercises;

import tools.IoTools;

import java.util.Arrays;

/**
 * Exercise 1 (4 points) Array Statistics: Mean, Median, Min, Max
 * Create a method that calculates statistical measures of an array. The method does not need to return anything and can just display the results directly.
 *
 * 1. Create an array of integers (you can initialize it with values or use random values)
 *
 * 2. Calculate and display:
 *    - **Mean (Average):** Sum of all elements divided by the number of elements
 *    - **Median:** The middle value when the array is sorted. If the array has an even number of elements, take the average of the two middle values
 *    - **Minimum:** The smallest value in the array
 *    - **Maximum:** The largest value in the array
 *
 * 3. Test with arrays of different sizes (including odd and even lengths for median)
 *
 * 4. Use proper variable names and include comments
 *
 * **Expected Output:**
 * ```java
 * 1 === Array Statistics ===
 * 2 Array: [15, 8, 23, 4, 42, 11, 19]
 * 3 Mean: 17.43
 * 4 Median: 15.0
 * 5 Minimum: 4
 * 6 Maximum: 42
 * ```
 */

public class ArrayStatisticsExercise extends Exercise
{
    private double mean = 0.0;
    private double median = 0.0;
    private int minimum = 0;
    private int maximum = 0;

    /**
     * Creates an ArrayStatisticsExcercise with a randomly generated array.
     * @param arrayLength Length of the array
     * @param minValue minimum value for random integers
     * @param maxValue maximum value for random integers
     */
    public ArrayStatisticsExercise(int arrayLength, int minValue, int maxValue)
    {
        super(arrayLength, minValue, maxValue);
    }

    /**
     * Creates an ArrayStatisticsExcercise with the provided array.
     * @param inputArray integer array
     */
    public ArrayStatisticsExercise(int[] inputArray)
    {
        super(inputArray);
    }

    @Override
    public void runExcercise()
    {
        calculateMean();
        calculateMedian();
        calculateMinAndMax();
    }

    @Override
    public void displayOutput()
    {
        IoTools.printHeading("Array Statistics");

        IO.println("Arrays: " + Arrays.toString(getInputArray()));
        IO.println("Mean: " + String.format("%.2f", getMean()));
        IO.println("Median: " + String.format("%.2f", getMedian()));
        IO.println("Minimum: " + getMinimum());
        IO.println("Maximum: " + getMaximum());
    }

    /**
     * Calculates the mean of the array and sets the mean field.
     */
    public void calculateMean()
    {
        int sum = 0;

        for (int num : getInputArray())
        {
            sum += num;
        }

        setMean((double) sum / getInputArray().length);
    }

    public double getMean()
    {
        return  mean;
    }

    public void setMean(double mean)
    {
        this.mean = mean;
    }

    /**
     * Calculates the median of the array and sets the median field.
     * Doesn't modify the original array; it creates a sorted copy for median calculation.
     */
    public void calculateMedian()
    {
        int[] sortedArray = Arrays.copyOf(getInputArray(), getInputArray().length);
        Arrays.sort(sortedArray);

        if (sortedArray.length % 2 == 0)
        {
            int mid1 = sortedArray.length / 2;
            int mid2 = mid1 - 1;
            setMedian((sortedArray[mid1] + sortedArray[mid2]) / 2.0);
        }
        else
        {
            int mid = sortedArray.length / 2;
            setMedian(sortedArray[mid]);
        }
    }

    public double getMedian()
    {
        return median;
    }

    public void setMedian(double median)
    {
        this.median = median;
    }

    /**
     * Calculates the minimum and maximum values in the array and sets the respective fields.
     */
    public void calculateMinAndMax()
    {
        int min = getInputArray()[0];
        int max = getInputArray()[0];

        for (int num : getInputArray())
        {
            if (num < min)
            {
                min = num;
            }
            if (num > max)
            {
                max = num;
            }
        }

        minimum = min;
        maximum = max;
    }

    /**
     * Gets the minimum value of the array.
     * @return minimum value
     */
    public int getMinimum()
    {
        return minimum;
    }

    /**
     * Gets the maximum value of the array.
     * @return maximum value
     */
    public int getMaximum()
    {
        return maximum;
    }
}
