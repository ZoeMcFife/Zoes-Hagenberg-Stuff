public class DayOfWeek
{
    /**
     * Get the name of a day given its number
     * @param dayNumber the day number (1-7)
     * @return the name of the day, or "Invalid day number" if out of range
     */
    public static String getDayName(int dayNumber) {
        switch (dayNumber) {
            case 1: return "Monday";
            case 2: return "Tuesday";
            case 3: return "Wednesday";
            case 4: return "Thursday";
            case 5: return "Friday";
            case 6: return "Saturday";
            case 7: return "Sunday";
            default: return "Invalid day number";
        }
    }

    /**
     * Get the type of day (Weekday or Weekend)
     * @param dayNumber the day number (1-7)
     * @return "Weekday", "Weekend", or "Invalid" for out-of-range numbers
     */
    public static String getDayType(int dayNumber) {
        if (dayNumber >= 1 && dayNumber <= 5) {
            return "Weekday";
        } else if (dayNumber == 6 || dayNumber == 7) {
            return "Weekend";
        } else {
            return "Invalid";
        }
    }

    public static void main(String[] args)
    {
        // Variable to store day number
        int dayNumber = 3;

        // Get day name and type
        String dayName = getDayName(dayNumber);
        String dayType = getDayType(dayNumber);

        // Display results
        System.out.println("=== Day of Week Processor ===");
        System.out.println("Day number: " + dayNumber);
        System.out.println("Day: " + dayName);
        System.out.println("Type: " + dayType);
    }
}
