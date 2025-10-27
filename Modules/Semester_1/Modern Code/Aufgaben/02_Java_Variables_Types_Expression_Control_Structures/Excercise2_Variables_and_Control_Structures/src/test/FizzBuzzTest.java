package test;

import main.FizzBuzz;
import org.junit.jupiter.api.Test;

public class FizzBuzzTest
{
    @Test
    public void testGetFizzBuzz()
    {
        FizzBuzz fb = new FizzBuzz(20);

        assert fb.getFizzBuzz(1).equals("1");
        assert fb.getFizzBuzz(3).equals("Fizz");
        assert fb.getFizzBuzz(5).equals("Buzz");
        assert fb.getFizzBuzz(15).equals("FizzBuzz");
    }
}
