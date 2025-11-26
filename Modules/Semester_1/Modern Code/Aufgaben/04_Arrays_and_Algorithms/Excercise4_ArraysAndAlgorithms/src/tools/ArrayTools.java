package tools;

import java.lang.reflect.Array;

public class ArrayTools
{
    /**
     * Swaps two elements in an array.
     *
     * @param array The array containing the elements
     * @param index1 The index of the first element
     * @param index2 The index of the second element
     * @param <T> The type of elements in the array
     */
    public static <T> void swap(T[] array, int index1, int index2)
    {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    /**
     * Generates an array of random integers within a specified range.
     *
     * @param size The size of the array
     * @param minValue The minimum value (inclusive)
     * @param maxValue The maximum value (inclusive)
     * @return An array of random integers
     */
    public static int[] generateRandomIntArray(int size, int minValue, int maxValue)
    {
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
        {
            array[i] = (int)(Math.random() * (maxValue - minValue + 1)) + minValue;
        }
        return array;
    }


}
