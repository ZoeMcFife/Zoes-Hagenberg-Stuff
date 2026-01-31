//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main()
{
    Factorial[] array = {new Factorial(1), new Factorial(2), new Factorial(3), new Factorial(4), new Factorial(5)};

    Factorial a = array[0];

    IO.println(a);

    array[0] = new Factorial(10);

    IO.println(a);


   //compare the execution times of both methods
    long n = 4;

    long startTime = System.nanoTime();
    long recursiveResult = Factorial.factorial(n);
    long recursiveTime = System.nanoTime() - startTime;

    startTime = System.nanoTime();
    long iterativeResult = Factorial.factorialIterative(n);
    long iterativeTime = System.nanoTime() - startTime;

    System.out.println("Recursive result: " + recursiveResult + ", Time: " + recursiveTime + " ns");
    System.out.println("Iterative result: " + iterativeResult + ", Time: " + iterativeTime + " ns");
}
