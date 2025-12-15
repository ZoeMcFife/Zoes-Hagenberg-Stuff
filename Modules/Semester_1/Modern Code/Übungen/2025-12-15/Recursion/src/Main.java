//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main()
{
    // compare the execution times of both methods
    long n = 5;

    long startTime = System.nanoTime();
    long recursiveResult = Factorial.factorial(n);
    long recursiveTime = System.nanoTime() - startTime;

    startTime = System.nanoTime();
    long iterativeResult = Factorial.factorialIterative(n);
    long iterativeTime = System.nanoTime() - startTime;

    System.out.println("Recursive result: " + recursiveResult + ", Time: " + recursiveTime + " ns");
    System.out.println("Iterative result: " + iterativeResult + ", Time: " + iterativeTime + " ns");
}
