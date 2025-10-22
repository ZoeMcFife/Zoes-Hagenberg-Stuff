public class FloatTypes
{
    public static void main(String[] args)
    {
        float price = 19.99F;
        double pi = 3.141592653589793;
        double scientificNotation = 1.23e4;

        // print the values
        System.out.println("Float value: " + price);
        System.out.println("Double value (pi): " + pi);
        System.out.println("Double value (scientific notation): " + scientificNotation);

        // compare precision between float and double, divide 1 by 3 and print
        float floatPrecision = 1.0F / 3.0F;
        double doublePrecision = 1.0 / 3.0;
        System.out.println("Float precision (1/3): " + floatPrecision);
        System.out.println("Double precision (1/3): " + doublePrecision);

    }
}
