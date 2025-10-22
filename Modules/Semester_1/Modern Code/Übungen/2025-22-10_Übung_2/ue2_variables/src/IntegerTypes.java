public class IntegerTypes
{
    public static void main(String[] args)
    {
        byte smallNumber = 127;
        short mediumNumber = 32000;
        int largeNumber = 2_000_000_000;
        long hugeNumber = 129_000_000_000L;

        // print the values
        System.out.println("Byte value: " + smallNumber);
        System.out.println("Short value: " + mediumNumber);
        System.out.println("Integer value: " + largeNumber);
        System.out.println("Long value: " + hugeNumber);

        //this will cause an overflow
        smallNumber += 1;
        System.out.println("Byte value after overflow: " + smallNumber);
    }
}
