package UserInterface;

import Global.Config;

public final class UI
{
    private UI()
    {

    }

    /**
     * Displays the Wordle title in ASCII art.
     */
    public static void displayWordleTitle()
    {
        IO.println(" __      __                .___.__          \n" +
                "/  \\    /  \\___________  __| _/|  |   ____  \n" +
                "\\   \\/\\/   /  _ \\_  __ \\/ __ | |  | _/ __ \\ \n" +
                " \\        (  <_> )  | \\/ /_/ | |  |_\\  ___/ \n" +
                "  \\__/\\  / \\____/|__|  \\____ | |____/\\___  >\n" +
                "       \\/                   \\/           \\/ ");
        IO.println("Zoe Edition");
    }

    /**
     * Gets an integer input from the player within a specified range.
     * Continues to prompt until valid input is provided.
     *
     * @param min The minimum allowed value (inclusive)
     * @param max The maximum allowed value (inclusive)
     * @return The validated integer input
     */
    public static int getIntInput(int min, int max)
    {
        while (true)
        {
            try
            {
                IO.print("> ");

                int value = Integer.parseInt(IO.readln());

                if (value >= min && value <= max)
                {
                    return value;
                }
            }
            catch (NumberFormatException ignored)
            {

            }

            IO.println("Invalid input. Enter a number between " + min + " and " + max + ".");
        }
    }

    /**
     * Gets a yes/no response from the player.
     * Accepts 'y', 'yes', 'n', or 'no' (case insensitive).
     *
     * @param prompt The question to ask the player
     * @return true for yes, false for no
     */
    public static boolean getYesNoInput(String prompt)
    {
        while (true)
        {
            IO.print(prompt + " (y/n): ");
            String input = IO.readln().trim().toLowerCase();

            if (input.equals("y") || input.equals("yes"))
            {
                return true;
            }
            else if (input.equals("n") || input.equals("no"))
            {
                return false;
            }

            IO.println("Invalid input. Please enter 'y' or 'n'.");
        }
    }

    // Thanks you autocomplete for like suggesting so many of these... i surely will need them....

    public static void printSeparatorLine()
    {
        IO.println("--------------------------------------------------");
    }

    public static void printDoubleSeparatorLine()
    {
        IO.println("==================================================");
    }

    public static void printAsteriskSeparatorLine()
    {
        IO.println("**************************************************");
    }

    public static void printHashSeparatorLine()
    {
        IO.println("##################################################");
    }

    public static void printBlankSeparatorLine()
    {
        IO.println();
    }

    /**
     * Pauses execution until the player presses Enter.
     */
    public static void waitForEnterKey()
    {
        IO.readln("Press Enter to continue...");
    }

    /**
     * Pauses execution for a short duration based on GameManager.DELAY_SHORT.
     */
    public static void delayShort()
    {
        delay(Config.DELAY_SHORT);
    }

    /**
     * Pauses execution for a medium duration based on GameManager.DELAY_MEDIUM.
     */
    public static void delayMedium()
    {
        delay(Config.DELAY_MEDIUM);
    }

    /**
     * Pauses execution for a long duration based on GameManager.DELAY_LONG.
     */
    public static void delayLong()
    {
        delay(Config.DELAY_LONG);
    }

    /**
     * Pauses execution for a specified duration.
     *
     * @param milliseconds The number of milliseconds to delay
     */
    private static void delay(int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Clears the console screen by printing blank lines.
     */
    public static void clearScreen()
    {
        for (int i = 0; i < 50; i++)
        {
            System.out.println();
        }
    }

    /**
     * Exits the game with a humorous message.
     */
    public static void exitGame()
    {
        IO.println("Your computer will blow up in 3 seconds...");
        System.exit(0);
    }
}
