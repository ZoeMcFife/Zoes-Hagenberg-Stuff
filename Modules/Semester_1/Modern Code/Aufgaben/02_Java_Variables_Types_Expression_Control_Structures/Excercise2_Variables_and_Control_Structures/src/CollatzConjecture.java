public class CollatzConjecture
{
    /**
     * Represents one step in the Collatz sequence
     */
    public static class CollatzStep {
        public final int value;
        public final boolean isEven;
        public final int nextValue;

        public CollatzStep(int value, boolean isEven, int nextValue) {
            this.value = value;
            this.isEven = isEven;
            this.nextValue = nextValue;
        }
    }

    /**
     * Calculate the next value in the Collatz sequence
     * @param n the current value
     * @return the next value in the sequence
     */
    public static int getNextCollatzValue(int n) {
        if (n % 2 == 0) {
            return n / 2;
        } else {
            return 3 * n + 1;
        }
    }

    /**
     * Generate the complete Collatz sequence for a starting number
     * @param startNumber the starting number
     * @return an array of CollatzStep objects representing the sequence
     */
    public static CollatzStep[] generateCollatzSequence(int startNumber) {
        // First, count how many steps we need
        int count = 0;
        int temp = startNumber;
        while (temp != 1) {
            temp = getNextCollatzValue(temp);
            count++;
        }

        // Now generate the sequence
        CollatzStep[] steps = new CollatzStep[count];
        int current = startNumber;
        for (int i = 0; i < count; i++) {
            boolean isEven = (current % 2 == 0);
            int next = getNextCollatzValue(current);
            steps[i] = new CollatzStep(current, isEven, next);
            current = next;
        }

        return steps;
    }

    /**
     * Count the number of steps to reach 1
     * @param startNumber the starting number
     * @return the number of steps
     */
    public static int countSteps(int startNumber) {
        int steps = 0;
        int current = startNumber;
        while (current != 1) {
            current = getNextCollatzValue(current);
            steps++;
        }
        return steps;
    }

    public static void main(String[] args)
    {
        // Starting number
        int startNumber = 5;

        // Display header
        System.out.println("=== Collatz Conjecture ===");
        System.out.println("Starting with " + startNumber + ":");

        // Generate and display the sequence
        CollatzStep[] sequence = generateCollatzSequence(startNumber);
        for (CollatzStep step : sequence) {
            String parity = step.isEven ? "even" : "odd";
            System.out.println(step.value + " (" + parity + ") -> " + step.nextValue);
        }

        // Display final result
        int stepCount = sequence.length;
        System.out.println("Reached 1 in " + stepCount + " steps!");
    }
}

