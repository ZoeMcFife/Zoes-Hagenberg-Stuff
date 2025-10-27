package main;

public class DayOfWeek
{
    public boolean isDayValid(int day)
    {
        if (day > 7 || day < 1)
        {
            return false;
        }

        return true;
    }

    public String getWeekDayName(int day)
    {
        // map days to day names using a switch statement
        switch (day)
        {
            case 1:
                return "Monday";
            case  2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case  5:
                return "Friday";
            case  6:
                return "Saturday";
            case  7:
                return "Sunday";
        }

        return "Invalid";
    }

    public String getDayType(int day)
    {
        if (day == 6 || day == 7)
        {
            return "Weekend";
        }
        else if (day >= 1 && day <= 5)
        {
            return "Weekday";
        }
        else
        {
            return "Invalid";
        }
    }

    public void checkDay(int day)
    {
        if (!isDayValid(day))
        {
            IO.println("Invalid day");
            return;
        }

        IO.println("=== Day of Week Processor ===");
        IO.println("Day Number: \t" + day);
        IO.println("Day: \t" + getWeekDayName(day));
        IO.println("Type: \t" + getDayType(day));
    }
}
