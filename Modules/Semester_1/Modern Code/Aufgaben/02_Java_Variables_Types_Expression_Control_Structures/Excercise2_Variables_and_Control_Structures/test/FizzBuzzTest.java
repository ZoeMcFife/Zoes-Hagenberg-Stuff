import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive unit tests for FizzBuzz
 * Tests the FizzBuzz logic with all divisibility cases
 */
@DisplayName("FizzBuzz Game Tests")
public class FizzBuzzTest {

    @Test
    @DisplayName("Numbers divisible by 3 should return 'Fizz'")
    public void testFizz() {
        assertEquals("Fizz", FizzBuzz.getFizzBuzzValue(3));
        assertEquals("Fizz", FizzBuzz.getFizzBuzzValue(6));
        assertEquals("Fizz", FizzBuzz.getFizzBuzzValue(9));
        assertEquals("Fizz", FizzBuzz.getFizzBuzzValue(12));
        assertEquals("Fizz", FizzBuzz.getFizzBuzzValue(18));
        assertEquals("Fizz", FizzBuzz.getFizzBuzzValue(21));
        assertEquals("Fizz", FizzBuzz.getFizzBuzzValue(27));
        assertEquals("Fizz", FizzBuzz.getFizzBuzzValue(33));
    }

    @Test
    @DisplayName("Numbers divisible by 5 should return 'Buzz'")
    public void testBuzz() {
        assertEquals("Buzz", FizzBuzz.getFizzBuzzValue(5));
        assertEquals("Buzz", FizzBuzz.getFizzBuzzValue(10));
        assertEquals("Buzz", FizzBuzz.getFizzBuzzValue(20));
        assertEquals("Buzz", FizzBuzz.getFizzBuzzValue(25));
        assertEquals("Buzz", FizzBuzz.getFizzBuzzValue(35));
        assertEquals("Buzz", FizzBuzz.getFizzBuzzValue(40));
        assertEquals("Buzz", FizzBuzz.getFizzBuzzValue(50));
    }

    @Test
    @DisplayName("Numbers divisible by both 3 and 5 should return 'FizzBuzz'")
    public void testFizzBuzz() {
        assertEquals("FizzBuzz", FizzBuzz.getFizzBuzzValue(15));
        assertEquals("FizzBuzz", FizzBuzz.getFizzBuzzValue(30));
        assertEquals("FizzBuzz", FizzBuzz.getFizzBuzzValue(45));
        assertEquals("FizzBuzz", FizzBuzz.getFizzBuzzValue(60));
        assertEquals("FizzBuzz", FizzBuzz.getFizzBuzzValue(75));
        assertEquals("FizzBuzz", FizzBuzz.getFizzBuzzValue(90));
    }

    @Test
    @DisplayName("Numbers not divisible by 3 or 5 should return the number as string")
    public void testRegularNumbers() {
        assertEquals("1", FizzBuzz.getFizzBuzzValue(1));
        assertEquals("2", FizzBuzz.getFizzBuzzValue(2));
        assertEquals("4", FizzBuzz.getFizzBuzzValue(4));
        assertEquals("7", FizzBuzz.getFizzBuzzValue(7));
        assertEquals("8", FizzBuzz.getFizzBuzzValue(8));
        assertEquals("11", FizzBuzz.getFizzBuzzValue(11));
        assertEquals("13", FizzBuzz.getFizzBuzzValue(13));
        assertEquals("14", FizzBuzz.getFizzBuzzValue(14));
    }

    @Test
    @DisplayName("Test complete sequence from 1 to 15 as per requirements")
    public void testSequence1To15() {
        String[] expected = {
            "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8",
            "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz"
        };
        
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], FizzBuzz.getFizzBuzzValue(i + 1),
                "FizzBuzz value for " + (i + 1) + " should be " + expected[i]);
        }
    }

    @Test
    @DisplayName("Test larger numbers divisible by 3")
    public void testLargeFizz() {
        assertEquals("Fizz", FizzBuzz.getFizzBuzzValue(99));
        assertEquals("Fizz", FizzBuzz.getFizzBuzzValue(102));
        assertEquals("Fizz", FizzBuzz.getFizzBuzzValue(111));
    }

    @Test
    @DisplayName("Test larger numbers divisible by 5")
    public void testLargeBuzz() {
        assertEquals("Buzz", FizzBuzz.getFizzBuzzValue(95));
        assertEquals("Buzz", FizzBuzz.getFizzBuzzValue(100));
        assertEquals("Buzz", FizzBuzz.getFizzBuzzValue(110));
    }

    @Test
    @DisplayName("Test larger numbers divisible by both 3 and 5")
    public void testLargeFizzBuzz() {
        assertEquals("FizzBuzz", FizzBuzz.getFizzBuzzValue(105));
        assertEquals("FizzBuzz", FizzBuzz.getFizzBuzzValue(120));
        assertEquals("FizzBuzz", FizzBuzz.getFizzBuzzValue(150));
    }

    @Test
    @DisplayName("Edge case: multiples of 15 should always be FizzBuzz, not just Fizz or Buzz")
    public void testFizzBuzzPrecedence() {
        // Ensure that multiples of 15 return "FizzBuzz" and not "Fizz" or "Buzz"
        for (int i = 15; i <= 300; i += 15) {
            assertEquals("FizzBuzz", FizzBuzz.getFizzBuzzValue(i),
                "Multiple of 15 (" + i + ") should return 'FizzBuzz', not 'Fizz' or 'Buzz'");
        }
    }

    @Test
    @DisplayName("Verify pattern consistency across range")
    public void testPatternConsistency() {
        // Test that the pattern repeats correctly every 15 numbers
        for (int offset = 0; offset < 100; offset += 15) {
            assertEquals(FizzBuzz.getFizzBuzzValue(3 + offset), "Fizz");
            assertEquals(FizzBuzz.getFizzBuzzValue(5 + offset), "Buzz");
            assertEquals(FizzBuzz.getFizzBuzzValue(15 + offset), "FizzBuzz");
        }
    }

    @Test
    @DisplayName("Test prime numbers return themselves")
    public void testPrimeNumbers() {
        int[] primes = {2, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47};
        for (int prime : primes) {
            assertEquals(String.valueOf(prime), FizzBuzz.getFizzBuzzValue(prime),
                "Prime number " + prime + " should return itself as a string");
        }
    }
}
