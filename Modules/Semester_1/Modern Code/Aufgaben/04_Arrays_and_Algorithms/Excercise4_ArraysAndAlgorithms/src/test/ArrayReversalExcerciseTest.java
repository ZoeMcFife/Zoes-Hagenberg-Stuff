package test;

import excercises.ArrayReversalExcercise;
import excercises.ArrayStatisticsExcercise;
import org.junit.jupiter.api.Test;

public class ArrayReversalExcerciseTest
{

    @Test
    void testArrayReversal()
    {
        int[] inputArray = {10, 20, 30, 40, 50};

        ArrayReversalExcercise excercise = new ArrayReversalExcercise(inputArray);

        excercise.runExcercise();

        int[] reversedArray = excercise.getReversedArray();

        int[] expectedArray = {50, 40, 30, 20, 10};

        for (int i = 0; i < expectedArray.length; i++)
        {
            assert(reversedArray[i] == expectedArray[i]);
        }
    }

}
