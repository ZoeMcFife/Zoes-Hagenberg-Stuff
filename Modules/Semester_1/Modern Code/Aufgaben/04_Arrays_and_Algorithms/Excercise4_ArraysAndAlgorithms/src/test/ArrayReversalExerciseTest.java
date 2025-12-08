package test;

import excercises.ArrayReversalExercise;
import org.junit.jupiter.api.Test;

public class ArrayReversalExerciseTest
{

    @Test
    void testArrayReversalOdd()
    {
        int[] inputArray = {10, 20, 30, 40, 50};

        ArrayReversalExercise excercise = new ArrayReversalExercise(inputArray);

        excercise.runExercise();

        int[] reversedArray = excercise.getReversedArray();

        int[] expectedArray = {50, 40, 30, 20, 10};

        for (int i = 0; i < expectedArray.length; i++)
        {
            assert(reversedArray[i] == expectedArray[i]);
        }
    }

    @Test
    void testArrayReversalEven()
    {
        int[] inputArray = {1, 2, 3, 4, 5, 6};

        ArrayReversalExercise excercise = new ArrayReversalExercise(inputArray);

        excercise.runExercise();

        int[] reversedArray = excercise.getReversedArray();

        int[] expectedArray = {6, 5, 4, 3, 2, 1};

        for (int i = 0; i < expectedArray.length; i++)
        {
            assert (reversedArray[i] == expectedArray[i]);
        }
    }
}
