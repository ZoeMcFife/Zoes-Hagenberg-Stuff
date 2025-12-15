package main;

import excercises.*;

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

        IO.println();

        UniqueValueAndFrequencyCountingExercise uniqueValueExcercise = new UniqueValueAndFrequencyCountingExercise(15, 1, 10);
        uniqueValueExcercise.runExercise();
        uniqueValueExcercise.displayOutput();

        IO.println();

        ArraySplittingExercise splittingExcercise = new ArraySplittingExercise(10, 1, 100);
        splittingExcercise.runExercise();
        splittingExcercise.displayOutput();

        IO.println();

        ExamExercise examExcercise = new ExamExercise(new String[]{"3 dogs", "5 cats", "8 dogs", "1 chicken", "2 chicken"});
        examExcercise.runExercise();
    }
}