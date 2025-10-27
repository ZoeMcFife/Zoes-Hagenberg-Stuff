public class DiceRollingSimulator
{
    /**
     * Represents the result of a dice roll
     */
    public static class RollResult {
        public final int rollNumber;
        public final int value;

        public RollResult(int rollNumber, int value) {
            this.rollNumber = rollNumber;
            this.value = value;
        }
    }

    /**
     * Roll a dice (returns a value between 1 and 6)
     * @return a random value between 1 and 6
     */
    public static int rollDice() {
        return (int)(Math.random() * 6) + 1;
    }

    /**
     * Simulate rolling dice until a target is reached
     * @param target the target number (1-6)
     * @return an array of RollResult objects representing each roll
     */
    public static RollResult[] rollUntilTarget(int target) {
        java.util.ArrayList<RollResult> rolls = new java.util.ArrayList<>();
        int rollCount = 0;
        int currentRoll;

        do {
            rollCount++;
            currentRoll = rollDice();
            rolls.add(new RollResult(rollCount, currentRoll));
        } while (currentRoll != target);

        return rolls.toArray(new RollResult[0]);
    }

    /**
     * Simulate rolling dice until target with a custom dice roller (for testing)
     * @param target the target number (1-6)
     * @param diceRoller a function that provides dice values
     * @return an array of RollResult objects representing each roll
     */
    public static RollResult[] rollUntilTargetWithRoller(int target, java.util.function.Supplier<Integer> diceRoller) {
        java.util.ArrayList<RollResult> rolls = new java.util.ArrayList<>();
        int rollCount = 0;
        int currentRoll;

        do {
            rollCount++;
            currentRoll = diceRoller.get();
            rolls.add(new RollResult(rollCount, currentRoll));
        } while (currentRoll != target);

        return rolls.toArray(new RollResult[0]);
    }

    public static void main(String[] args)
    {
        // Target number
        int targetNumber = 6;

        // Display header
        System.out.println("=== Dice Rolling Simulator ===");
        System.out.println("Target number: " + targetNumber);

        // Roll until we hit the target
        RollResult[] rolls = rollUntilTarget(targetNumber);

        // Display each roll
        for (RollResult roll : rolls) {
            System.out.println("Roll " + roll.rollNumber + ": " + roll.value);
        }

        // Display final result
        System.out.println("Found target number " + targetNumber + " in " + rolls.length + " rolls!");
    }
}
