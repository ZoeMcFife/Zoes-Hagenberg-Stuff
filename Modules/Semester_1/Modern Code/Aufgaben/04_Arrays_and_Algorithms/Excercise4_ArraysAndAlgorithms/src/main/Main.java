package main;

import excercises.ArrayReversalExercise;
import excercises.ArrayStatisticsExercise;

public class Main
{
    public static void main(String[] args)
    {
        ArrayStatisticsExercise statisticsExcercise = new ArrayStatisticsExercise(10, 1, 100);
        statisticsExcercise.runExcercise();
        statisticsExcercise.displayOutput();

        ArrayReversalExercise reversalExcercise = new ArrayReversalExercise(10, 1, 100);
        reversalExcercise.runExcercise();
        reversalExcercise.displayOutput();
    }
}