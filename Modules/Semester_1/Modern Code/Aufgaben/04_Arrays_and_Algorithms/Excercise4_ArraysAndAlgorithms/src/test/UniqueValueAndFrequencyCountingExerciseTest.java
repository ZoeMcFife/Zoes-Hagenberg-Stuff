package test;

import excercises.UniqueValueAndFrequencyCountingExercise;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class UniqueValueAndFrequencyCountingExerciseTest
{
    @Test
    void testUniqueValueAndFrequencyCounting()
    {
        int[] testArray = {5, 2, 8, 2, 5, 5, 1, 8};

        HashMap<Integer, Integer> expectedFrequency = new HashMap<>();

        expectedFrequency.put(5, 3);
        expectedFrequency.put(2, 2);
        expectedFrequency.put(8, 2);
        expectedFrequency.put(1, 1);

        UniqueValueAndFrequencyCountingExercise exercise = new UniqueValueAndFrequencyCountingExercise(testArray);
        exercise.runExercise();
        exercise.displayOutput();

        HashMap<Integer, Integer> actualFrequency = exercise.getUniqueValuesFrequency();

        assertEquals(expectedFrequency, actualFrequency);
    }
}
