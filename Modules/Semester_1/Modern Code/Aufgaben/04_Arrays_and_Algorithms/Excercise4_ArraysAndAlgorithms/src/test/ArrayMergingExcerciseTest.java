package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import excercises.ArrayMergingExercise;

public class ArrayMergingExcerciseTest
{
    @Test
    void testArrayMergingSmallArrays()
    {
        int[] array1 = {10, 20, 30};
        int[] array2 = {40, 50, 60, 70};
        int[] expectedMergedArray = {10, 20, 30, 40, 50, 60, 70};

        ArrayMergingExercise excercise = new ArrayMergingExercise(array1, array2);
        excercise.runExercise();
        int[] mergedArray = excercise.getMergedArray();

        assertArrayEquals(expectedMergedArray, mergedArray);
    }

    @Test
    void testArrayMergingMediumArraysOfDifferentSizes()
    {
        int[] array1 = {1, 3, 5, 7, 9, 10, 11};
        int[] array2 = {2, 4, 6};
        int[] expectedMergedArray = {1, 3, 5, 7, 9, 10, 11, 2, 4, 6};

        ArrayMergingExercise excercise = new ArrayMergingExercise(array1, array2);
        excercise.runExercise();
        int[] mergedArray = excercise.getMergedArray();

        assertArrayEquals(expectedMergedArray, mergedArray);
    }

    @Test
    void testArrayMergingLargeArraysOfDifferentSizes()
    {
        int[] array1 = new int[1000];
        int[] array2 = new int[500];
        int[] expectedMergedArray = new int[1500];

        for (int i = 0; i < 1000; i++)
        {
            array1[i] = i;
            expectedMergedArray[i] = i;
        }
        for (int i = 0; i < 500; i++)
        {
            array2[i] = i + 1000;
            expectedMergedArray[i + 1000] = i + 1000;
        }

        ArrayMergingExercise excercise = new ArrayMergingExercise(array1, array2);
        excercise.runExercise();
        int[] mergedArray = excercise.getMergedArray();

        assertArrayEquals(expectedMergedArray, mergedArray);
    }

    @Test
    void testArrayMergingWithEmptyArray()
    {
        int[] array1 = {1, 2, 3};
        int[] array2 = {};
        int[] expectedMergedArray = {1, 2, 3};

        ArrayMergingExercise excercise = new ArrayMergingExercise(array1, array2);
        excercise.runExercise();
        int[] mergedArray = excercise.getMergedArray();

        assertArrayEquals(expectedMergedArray, mergedArray);
    }

    @Test
    void testArrayMergingBothEmptyArrays()
    {
        int[] array1 = {};
        int[] array2 = {};
        int[] expectedMergedArray = {};

        ArrayMergingExercise excercise = new ArrayMergingExercise(array1, array2);
        excercise.runExercise();
        int[] mergedArray = excercise.getMergedArray();

        assertArrayEquals(expectedMergedArray, mergedArray);
    }
}
