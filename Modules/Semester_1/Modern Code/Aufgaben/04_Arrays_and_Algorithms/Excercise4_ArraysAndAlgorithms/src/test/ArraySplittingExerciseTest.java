package test;

import excercises.ArraySplittingExercise;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArraySplittingExerciseTest
{
    @Test
    void testArraySplittingExerciseWithExampleArray()
    {
        int[] inputArray = {15, 8, 23, 4, 42, 11, 19};

        int[] expectedEvenArray = {8, 4, 42};
        int[] expectedOddArray = {15, 23, 11, 19};

        testArraySplittingExercise(inputArray, expectedEvenArray, expectedOddArray);
    }

    private void testArraySplittingExercise(int[] inputArray, int[] expectedEvenArray, int[] expectedOddArray)
    {
        ArraySplittingExercise splittingExcercise = new ArraySplittingExercise(inputArray);

        splittingExcercise.runExercise();
        splittingExcercise.displayOutput();

        int[] actualEvenArray = splittingExcercise.getArraySplittingResult().evenArray();
        int[] actualOddArray = splittingExcercise.getArraySplittingResult().oddArray();

        assertArrayEquals(expectedEvenArray, actualEvenArray);
        assertArrayEquals(expectedOddArray, actualOddArray);
    }

    @Test
    void testArraySplittingExerciseWithNoEvenNumbers()
    {
        int[] inputArray = {1, 3, 5, 7, 9};

        int[] expectedEvenArray = {};
        int[] expectedOddArray = {1, 3, 5, 7, 9};

        testArraySplittingExercise(inputArray, expectedEvenArray, expectedOddArray);
    }

    @Test
    void testArraySplittingExerciseWithNoOddNumbers()
    {
        int[] inputArray = {2, 4, 6, 8, 10};

        int[] expectedEvenArray = {2, 4, 6, 8, 10};
        int[] expectedOddArray = {};

        testArraySplittingExercise(inputArray, expectedEvenArray, expectedOddArray);
    }

}
