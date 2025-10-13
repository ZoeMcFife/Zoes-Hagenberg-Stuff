public class SimpleCalculator
{
    // Calculator takes two integer values as input
    // and does simple calculations with them and prints it out to the console
    public void simpleCalculations(int a, int b)
    {
        // Calculate sum, difference, etc and save them as variables
        int sum = a + b;
        int difference = a - b;
        int product = a * b;
        float quotient = (float) a / b; // cast to float to avoid integer division

        // Print out the results
        IO.println("Simple Calculator Results:");
        IO.println(a + " + " + b + " = " + sum);
        IO.println(a + " - " + b + " = " + difference);
        IO.println(a + " * " + b + " = " + product);
        IO.println(a + " / " + b + " = " + quotient);
    }
}
