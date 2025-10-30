package main;

public class DayOfWeek
{
    public boolean isDayValid(int day)
    {
        return day <= 7 && day >= 1;
    }

    public String getWeekDayName(int day)
    {
        // map days to day names using a switch statement
        return switch (day) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            case 7 -> "Sunday";
            default -> "Invalid";
        };

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
