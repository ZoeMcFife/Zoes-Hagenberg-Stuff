package test;

import excercises.FuzzySearchExercise;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FuzzySearchExerciseTest
{
    @Test
    void testFuzzySearchNoMatchExists()
    {
        int[] testArray = {15, 8, 23, 4, 42, 11, 19};
        int targetValue = 20;
        int expectedClosestValue = 19;
        int expectedClosestIndex = 6;

        FuzzySearchExercise fuzzySearchExercise = new FuzzySearchExercise(testArray, targetValue);
        fuzzySearchExercise.runExcercise();

        assertEquals(expectedClosestValue, fuzzySearchExercise.getFuzzySearchResult().bestMatchValue());
        assertFalse(fuzzySearchExercise.getFuzzySearchResult().foundExactMatch());
        assertEquals(expectedClosestIndex, fuzzySearchExercise.getFuzzySearchResult().bestMatchIndex());
    }

    @Test
    void testFuzzySearchExactMatchExists()
    {
        int[] testArray = {15, 8, 23, 4, 42, 11, 19};
        int targetValue = 8;
        int expectedIndex = 1;

        FuzzySearchExercise fuzzySearchExercise = new FuzzySearchExercise(testArray, targetValue);
        fuzzySearchExercise.runExcercise();

        assertTrue(fuzzySearchExercise.getFuzzySearchResult().foundExactMatch());
        assertEquals(expectedIndex, fuzzySearchExercise.getFuzzySearchResult().bestMatchIndex());
        assertEquals(targetValue, fuzzySearchExercise.getFuzzySearchResult().bestMatchValue());
    }

}
