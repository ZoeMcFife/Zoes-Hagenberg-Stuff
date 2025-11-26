package main;

import excercises.ArrayReversalExercise;
import excercises.ArrayStatisticsExercise;
import excercises.FuzzySearchExercise;

public class Main
{
    public static void main(String[] args)
    {
        ArrayStatisticsExercise statisticsExcercise = new ArrayStatisticsExercise(10, 1, 100);
        statisticsExcercise.runExcercise();
        statisticsExcercise.displayOutput();

        IO.println();

        ArrayReversalExercise reversalExcercise = new ArrayReversalExercise(10, 1, 100);
        reversalExcercise.runExcercise();
        reversalExcercise.displayOutput();

        IO.println();

        FuzzySearchExercise fuzzySearchExcercise = new FuzzySearchExercise(10, 1, 100, 50);
        fuzzySearchExcercise.runExcercise();
        fuzzySearchExcercise.displayOutput();

        IO.println();
    }
}