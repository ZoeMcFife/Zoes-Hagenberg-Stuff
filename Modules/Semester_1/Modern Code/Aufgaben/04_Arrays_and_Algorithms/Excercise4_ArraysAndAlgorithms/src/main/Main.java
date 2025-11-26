package main;

import excercises.ArrayMergingExercise;
import excercises.ArrayReversalExercise;
import excercises.ArrayStatisticsExercise;
import excercises.FuzzySearchExercise;

public class Main
{
    public static void main(String[] args)
    {
        ArrayStatisticsExercise statisticsExcercise = new ArrayStatisticsExercise(10, 1, 100);
        statisticsExcercise.runExercise();
        statisticsExcercise.displayOutput();

        IO.println();

        ArrayReversalExercise reversalExcercise = new ArrayReversalExercise(10, 1, 100);
        reversalExcercise.runExercise();
        reversalExcercise.displayOutput();

        IO.println();

        FuzzySearchExercise fuzzySearchExcercise = new FuzzySearchExercise(10, 1, 100, 50);
        fuzzySearchExcercise.runExercise();
        fuzzySearchExcercise.displayOutput();

        IO.println();

        ArrayMergingExercise mergingExcercise = new ArrayMergingExercise(5, 1, 100, 5, 1, 100);
        mergingExcercise.runExercise();
        mergingExcercise.displayOutput();
    }
}