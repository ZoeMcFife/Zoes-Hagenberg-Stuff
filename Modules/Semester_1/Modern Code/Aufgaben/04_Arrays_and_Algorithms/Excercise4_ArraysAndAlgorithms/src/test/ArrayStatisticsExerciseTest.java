package test;

import excercises.ArrayStatisticsExercise;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStatisticsExerciseTest
{
    private ArrayStatisticsExercise excercise;

    // Tests for Mean calculation

    @Test
    void testMeanWithOddLengthArray()
    {
        int[] testArray = {10, 20, 30, 40, 50};
        excercise = new ArrayStatisticsExercise(testArray);
        excercise.calculateMean();

        // Mean = (10 + 20 + 30 + 40 + 50) / 5 = 150 / 5 = 30.0
        assertEquals(30.0, excercise.getMean(), 0.001);
    }

    @Test
    void testMeanWithEvenLengthArray()
    {
        int[] testArray = {10, 20, 30, 40};
        excercise = new ArrayStatisticsExercise(testArray);
        excercise.calculateMean();

        // Mean = (10 + 20 + 30 + 40) / 4 = 100 / 4 = 25.0
        assertEquals(25.0, excercise.getMean(), 0.001);
    }

    @Test
    void testMeanWithExpectedOutputArray()
    {
        // Array from the expected output: [15, 8, 23, 4, 42, 11, 19]
        int[] testArray = {15, 8, 23, 4, 42, 11, 19};
        excercise = new ArrayStatisticsExercise(testArray);
        excercise.calculateMean();

        // Mean = (15 + 8 + 23 + 4 + 42 + 11 + 19) / 7 = 122 / 7 = 17.428...
        assertEquals(17.43, excercise.getMean(), 0.01);
    }

    @Test
    void testMeanWithSingleElement()
    {
        int[] testArray = {42};
        excercise = new ArrayStatisticsExercise(testArray);
        excercise.calculateMean();

        assertEquals(42.0, excercise.getMean(), 0.001);
    }

    // Tests for Median calculation

    @Test
    void testMedianWithOddLengthArray()
    {
        int[] testArray = {10, 20, 30, 40, 50};
        excercise = new ArrayStatisticsExercise(testArray);
        excercise.calculateMedian();

        // Sorted: [10, 20, 30, 40, 50], Median = 30
        assertEquals(30.0, excercise.getMedian(), 0.001);
    }

    @Test
    void testMedianWithEvenLengthArray()
    {
        int[] testArray = {10, 20, 30, 40};
        excercise = new ArrayStatisticsExercise(testArray);
        excercise.calculateMedian();

        // Sorted: [10, 20, 30, 40], Median = (20 + 30) / 2 = 25.0
        assertEquals(25.0, excercise.getMedian(), 0.001);
    }

    @Test
    void testMedianWithUnsortedOddLengthArray()
    {
        int[] testArray = {50, 10, 30, 20, 40};
        excercise = new ArrayStatisticsExercise(testArray);
        excercise.calculateMedian();

        // Sorted: [10, 20, 30, 40, 50], Median = 30
        assertEquals(30.0, excercise.getMedian(), 0.001);
    }

    @Test
    void testMedianWithUnsortedEvenLengthArray()
    {
        int[] testArray = {40, 10, 30, 20};
        excercise = new ArrayStatisticsExercise(testArray);
        excercise.calculateMedian();

        // Sorted: [10, 20, 30, 40], Median = (20 + 30) / 2 = 25.0
        assertEquals(25.0, excercise.getMedian(), 0.001);
    }

    @Test
    void testMedianWithExpectedOutputArray()
    {
        // Array from the expected output: [15, 8, 23, 4, 42, 11, 19]
        int[] testArray = {15, 8, 23, 4, 42, 11, 19};
        excercise = new ArrayStatisticsExercise(testArray);
        excercise.calculateMedian();

        // Sorted: [4, 8, 11, 15, 19, 23, 42], Median = 15
        assertEquals(15.0, excercise.getMedian(), 0.001);
    }

    @Test
    void testMedianWithSingleElement()
    {
        int[] testArray = {42};
        excercise = new ArrayStatisticsExercise(testArray);
        excercise.calculateMedian();

        assertEquals(42.0, excercise.getMedian(), 0.001);
    }

    @Test
    void testMedianWithTwoElements()
    {
        int[] testArray = {10, 20};
        excercise = new ArrayStatisticsExercise(testArray);
        excercise.calculateMedian();

        // Median = (10 + 20) / 2 = 15.0
        assertEquals(15.0, excercise.getMedian(), 0.001);
    }

    // Tests for Min and Max calculation

    @Test
    void testMinAndMaxWithSortedArray()
    {
        int[] testArray = {10, 20, 30, 40, 50};
        excercise = new ArrayStatisticsExercise(testArray);
        excercise.calculateMinAndMax();

        assertEquals(10, excercise.getMinimum());
        assertEquals(50, excercise.getMaximum());
    }

    @Test
    void testMinAndMaxWithUnsortedArray()
    {
        int[] testArray = {30, 10, 50, 20, 40};
        excercise = new ArrayStatisticsExercise(testArray);
        excercise.calculateMinAndMax();

        assertEquals(10, excercise.getMinimum());
        assertEquals(50, excercise.getMaximum());
    }

    @Test
    void testMinAndMaxWithExpectedOutputArray()
    {
        // Array from the expected output: [15, 8, 23, 4, 42, 11, 19]
        int[] testArray = {15, 8, 23, 4, 42, 11, 19};
        excercise = new ArrayStatisticsExercise(testArray);
        excercise.calculateMinAndMax();

        assertEquals(4, excercise.getMinimum());
        assertEquals(42, excercise.getMaximum());
    }

    @Test
    void testMinAndMaxWithSingleElement()
    {
        int[] testArray = {42};
        excercise = new ArrayStatisticsExercise(testArray);
        excercise.calculateMinAndMax();

        assertEquals(42, excercise.getMinimum());
        assertEquals(42, excercise.getMaximum());
    }

    @Test
    void testMinAndMaxWithNegativeNumbers()
    {
        int[] testArray = {-10, -5, 0, 5, 10};
        excercise = new ArrayStatisticsExercise(testArray);
        excercise.calculateMinAndMax();

        assertEquals(-10, excercise.getMinimum());
        assertEquals(10, excercise.getMaximum());
    }

    @Test
    void testMinAndMaxWithAllSameValues()
    {
        int[] testArray = {5, 5, 5, 5, 5};
        excercise = new ArrayStatisticsExercise(testArray);
        excercise.calculateMinAndMax();

        assertEquals(5, excercise.getMinimum());
        assertEquals(5, excercise.getMaximum());
    }

    // Test runExcercise method (integration test)

    @Test
    void testRunExcerciseCalculatesAllStatistics()
    {
        // Array from the expected output: [15, 8, 23, 4, 42, 11, 19]
        int[] testArray = {15, 8, 23, 4, 42, 11, 19};
        excercise = new ArrayStatisticsExercise(testArray);
        excercise.runExcercise();

        // Verify all statistics are calculated correctly
        assertEquals(17.43, excercise.getMean(), 0.01);
        assertEquals(15.0, excercise.getMedian(), 0.001);
        assertEquals(4, excercise.getMinimum());
        assertEquals(42, excercise.getMaximum());
    }

    // Test that original array is not modified during median calculation

    @Test
    void testMedianDoesNotModifyOriginalArray()
    {
        int[] testArray = {50, 10, 30, 20, 40};
        excercise = new ArrayStatisticsExercise(testArray);
        excercise.calculateMedian();

        // Original array should remain unchanged
        int[] expectedArray = {50, 10, 30, 20, 40};
        assertArrayEquals(expectedArray, excercise.getInputArray());
    }

    // Test constructor with random array

    @Test
    void testRandomArrayConstructor()
    {
        excercise = new ArrayStatisticsExercise(5, 1, 100);

        // Verify array has correct length
        assertEquals(5, excercise.getInputArray().length);

        // Verify all values are within range
        for (int value : excercise.getInputArray())
        {
            assertTrue(value >= 1 && value <= 100);
        }
    }
}
