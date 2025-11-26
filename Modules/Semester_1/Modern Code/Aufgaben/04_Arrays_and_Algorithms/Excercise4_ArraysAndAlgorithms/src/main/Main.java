package main;

import excercises.ArrayStatisticsExcercise;

public class Main
{
    public static void main(String[] args)
    {
        ArrayStatisticsExcercise excercise = new ArrayStatisticsExcercise(10, 1, 100);
        excercise.runExcercise();
        excercise.displayOutput();
    }
}