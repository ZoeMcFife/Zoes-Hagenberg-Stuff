package test;

import excercises.ArrayReversalExercise;
import org.junit.jupiter.api.Test;

public class ArrayReversalExerciseTest
{

    @Test
    void testArrayReversal()
    {
        int[] inputArray = {10, 20, 30, 40, 50};

        ArrayReversalExercise excercise = new ArrayReversalExercise(inputArray);

        excercise.runExcercise();

        int[] reversedArray = excercise.getReversedArray();

        int[] expectedArray = {50, 40, 30, 20, 10};

        for (int i = 0; i < expectedArray.length; i++)
        {
            assert(reversedArray[i] == expectedArray[i]);
        }
    }

}
