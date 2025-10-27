import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive unit tests for DayOfWeek
 * Tests day name retrieval, day type classification, and invalid input handling
 */
@DisplayName("Day of Week Processor Tests")
public class DayOfWeekTest {

    @Test
    @DisplayName("Get day name for Monday (1)")
    public void testGetDayNameMonday() {
        assertEquals("Monday", DayOfWeek.getDayName(1));
    }

    @Test
    @DisplayName("Get day name for Tuesday (2)")
    public void testGetDayNameTuesday() {
        assertEquals("Tuesday", DayOfWeek.getDayName(2));
    }

    @Test
    @DisplayName("Get day name for Wednesday (3)")
    public void testGetDayNameWednesday() {
        assertEquals("Wednesday", DayOfWeek.getDayName(3));
    }

    @Test
    @DisplayName("Get day name for Thursday (4)")
    public void testGetDayNameThursday() {
        assertEquals("Thursday", DayOfWeek.getDayName(4));
    }

    @Test
    @DisplayName("Get day name for Friday (5)")
    public void testGetDayNameFriday() {
        assertEquals("Friday", DayOfWeek.getDayName(5));
    }

    @Test
    @DisplayName("Get day name for Saturday (6)")
    public void testGetDayNameSaturday() {
        assertEquals("Saturday", DayOfWeek.getDayName(6));
    }

    @Test
    @DisplayName("Get day name for Sunday (7)")
    public void testGetDayNameSunday() {
        assertEquals("Sunday", DayOfWeek.getDayName(7));
    }

    @Test
    @DisplayName("Get day name for all valid days")
    public void testGetDayNameAllValid() {
        String[] expectedDays = {"Monday", "Tuesday", "Wednesday", "Thursday", 
                                 "Friday", "Saturday", "Sunday"};
        
        for (int i = 0; i < expectedDays.length; i++) {
            assertEquals(expectedDays[i], DayOfWeek.getDayName(i + 1),
                "Day " + (i + 1) + " should be " + expectedDays[i]);
        }
    }

    @Test
    @DisplayName("Get day type for weekdays (1-5)")
    public void testGetDayTypeWeekdays() {
        assertEquals("Weekday", DayOfWeek.getDayType(1), "Monday should be a Weekday");
        assertEquals("Weekday", DayOfWeek.getDayType(2), "Tuesday should be a Weekday");
        assertEquals("Weekday", DayOfWeek.getDayType(3), "Wednesday should be a Weekday");
        assertEquals("Weekday", DayOfWeek.getDayType(4), "Thursday should be a Weekday");
        assertEquals("Weekday", DayOfWeek.getDayType(5), "Friday should be a Weekday");
    }

    @Test
    @DisplayName("Get day type for weekend (6-7)")
    public void testGetDayTypeWeekend() {
        assertEquals("Weekend", DayOfWeek.getDayType(6), "Saturday should be Weekend");
        assertEquals("Weekend", DayOfWeek.getDayType(7), "Sunday should be Weekend");
    }

    @Test
    @DisplayName("Handle invalid day number 0")
    public void testInvalidDayNumber0() {
        assertEquals("Invalid day number", DayOfWeek.getDayName(0));
        assertEquals("Invalid", DayOfWeek.getDayType(0));
    }

    @Test
    @DisplayName("Handle invalid day number 8")
    public void testInvalidDayNumber8() {
        assertEquals("Invalid day number", DayOfWeek.getDayName(8));
        assertEquals("Invalid", DayOfWeek.getDayType(8));
    }

    @Test
    @DisplayName("Handle negative day numbers")
    public void testNegativeDayNumbers() {
        assertEquals("Invalid day number", DayOfWeek.getDayName(-1));
        assertEquals("Invalid", DayOfWeek.getDayType(-1));
        
        assertEquals("Invalid day number", DayOfWeek.getDayName(-5));
        assertEquals("Invalid", DayOfWeek.getDayType(-5));
    }

    @Test
    @DisplayName("Handle large invalid day numbers")
    public void testLargeInvalidDayNumbers() {
        assertEquals("Invalid day number", DayOfWeek.getDayName(100));
        assertEquals("Invalid", DayOfWeek.getDayType(100));
        
        assertEquals("Invalid day number", DayOfWeek.getDayName(365));
        assertEquals("Invalid", DayOfWeek.getDayType(365));
    }

    @Test
    @DisplayName("Verify Wednesday example from requirements")
    public void testWednesdayExample() {
        int dayNumber = 3;
        assertEquals("Wednesday", DayOfWeek.getDayName(dayNumber));
        assertEquals("Weekday", DayOfWeek.getDayType(dayNumber));
    }

    @Test
    @DisplayName("Verify all weekdays are classified correctly")
    public void testAllWeekdaysClassification() {
        for (int day = 1; day <= 5; day++) {
            assertEquals("Weekday", DayOfWeek.getDayType(day),
                "Day " + day + " should be classified as Weekday");
        }
    }

    @Test
    @DisplayName("Verify all weekend days are classified correctly")
    public void testAllWeekendClassification() {
        assertEquals("Weekend", DayOfWeek.getDayType(6), 
            "Day 6 (Saturday) should be classified as Weekend");
        assertEquals("Weekend", DayOfWeek.getDayType(7), 
            "Day 7 (Sunday) should be classified as Weekend");
    }

    @Test
    @DisplayName("Test boundary values")
    public void testBoundaryValues() {
        // First valid day
        assertEquals("Monday", DayOfWeek.getDayName(1));
        assertEquals("Weekday", DayOfWeek.getDayType(1));
        
        // Last valid day
        assertEquals("Sunday", DayOfWeek.getDayName(7));
        assertEquals("Weekend", DayOfWeek.getDayType(7));
        
        // Just before first valid
        assertEquals("Invalid day number", DayOfWeek.getDayName(0));
        assertEquals("Invalid", DayOfWeek.getDayType(0));
        
        // Just after last valid
        assertEquals("Invalid day number", DayOfWeek.getDayName(8));
        assertEquals("Invalid", DayOfWeek.getDayType(8));
    }

    @Test
    @DisplayName("Consistency check: name and type should match")
    public void testNameAndTypeConsistency() {
        // Check that weekday names correspond to Weekday type
        for (int day = 1; day <= 5; day++) {
            String dayName = DayOfWeek.getDayName(day);
            String dayType = DayOfWeek.getDayType(day);
            assertNotEquals("Invalid day number", dayName);
            assertEquals("Weekday", dayType,
                dayName + " should be classified as Weekday");
        }
        
        // Check that weekend names correspond to Weekend type
        for (int day = 6; day <= 7; day++) {
            String dayName = DayOfWeek.getDayName(day);
            String dayType = DayOfWeek.getDayType(day);
            assertNotEquals("Invalid day number", dayName);
            assertEquals("Weekend", dayType,
                dayName + " should be classified as Weekend");
        }
    }
}
