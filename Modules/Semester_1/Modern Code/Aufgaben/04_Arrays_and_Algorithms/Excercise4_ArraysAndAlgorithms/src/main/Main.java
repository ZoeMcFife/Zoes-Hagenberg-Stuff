package main;

import excercises.ArrayReversalExcercise;
import excercises.ArrayStatisticsExcercise;

public class Main
{
    public static void main(String[] args)
    {
        ArrayStatisticsExcercise statisticsExcercise = new ArrayStatisticsExcercise(10, 1, 100);
        statisticsExcercise.runExcercise();
        statisticsExcercise.displayOutput();

        ArrayReversalExcercise reversalExcercise = new ArrayReversalExcercise(10, 1, 100);
        reversalExcercise.runExcercise();
        reversalExcercise.displayOutput();
    }
}