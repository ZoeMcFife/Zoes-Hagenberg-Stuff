package excercises;

import tools.ArrayTools;

public abstract class Exercise
{
    /** Input array for the excercise */
    private int[] inputArray;

    /**
     * Creates an Excercise with a randomly generated array.
     * @param arrayLength Length of the array
     * @param minValue minimum value for random integers
     * @param maxValue maximum value for random integers
     */
    public Exercise(int arrayLength, int minValue, int maxValue)
    {
        setInputArray(ArrayTools.generateRandomIntArray(arrayLength, minValue, maxValue));
    }

    /**
     * Creates an Excercise with the provided array.
     * @param inputArray integer array
     */
    public Exercise(int[] inputArray)
    {
        setInputArray(inputArray);
    }

    /** Sets the input array for the excercise
     * @param inputArray integer array
     */
    protected void setInputArray(int[] inputArray)
    {
        this.inputArray = inputArray;
    }

    /** Gets the input array for the excercise
     * @return integer array
     */
    public int[] getInputArray()
    {
        return inputArray;
    }

    /**
     * Starts the excercise logic
     */
    public abstract void runExcercise();

    /**
     * Displays the output of the excercise
     */
    public abstract void displayOutput();
}
