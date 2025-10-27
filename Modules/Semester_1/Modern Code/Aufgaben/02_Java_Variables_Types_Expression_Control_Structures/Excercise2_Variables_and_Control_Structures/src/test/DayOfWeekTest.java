package test;
import static org.junit.jupiter.api.Assertions.*;

import main.DayOfWeek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DayOfWeekTest
{

    private DayOfWeek dayOfWeek;

    @BeforeEach
    void setUp()
    {
        dayOfWeek = new DayOfWeek();
    }

    // ------------------------------
    // Tests for isDayValid()
    // ------------------------------

    @Test
    void testIsDayValid_withValidDays()
    {
        for (int i = 1; i <= 7; i++)
        {
            assertTrue(dayOfWeek.isDayValid(i), "Day " + i + " should be valid");
        }
    }

    @Test
    void testIsDayValid_withInvalidDays()
    {
        assertFalse(dayOfWeek.isDayValid(0), "Day 0 should be invalid");
        assertFalse(dayOfWeek.isDayValid(8), "Day 8 should be invalid");
        assertFalse(dayOfWeek.isDayValid(-3), "Negative days should be invalid");
    }

    // ------------------------------
    // Tests for getWeekDayName()
    // ------------------------------

    @Test
    void testGetWeekDayName_validDays()
    {
        assertEquals("Monday", dayOfWeek.getWeekDayName(1));
        assertEquals("Tuesday", dayOfWeek.getWeekDayName(2));
        assertEquals("Wednesday", dayOfWeek.getWeekDayName(3));
        assertEquals("Thursday", dayOfWeek.getWeekDayName(4));
        assertEquals("Friday", dayOfWeek.getWeekDayName(5));
        assertEquals("Saturday", dayOfWeek.getWeekDayName(6));
        assertEquals("Sunday", dayOfWeek.getWeekDayName(7));
    }

    @Test
    void testGetWeekDayName_invalidDays()
    {
        assertEquals("Invalid", dayOfWeek.getWeekDayName(0));
        assertEquals("Invalid", dayOfWeek.getWeekDayName(8));
    }

    // ------------------------------
    // Tests for getDayType()
    // ------------------------------

    @Test
    void testGetDayType_weekdays()
    {
        for (int i = 1; i <= 5; i++)
        {
            assertEquals("Weekday", dayOfWeek.getDayType(i), "Day " + i + " should be a Weekday");
        }
    }

    @Test
    void testGetDayType_weekends()
    {
        assertEquals("Weekend", dayOfWeek.getDayType(6));
        assertEquals("Weekend", dayOfWeek.getDayType(7));
    }

    @Test
    void testGetDayType_invalid()
    {
        assertEquals("Invalid", dayOfWeek.getDayType(0));
        assertEquals("Invalid", dayOfWeek.getDayType(9));
    }

}
