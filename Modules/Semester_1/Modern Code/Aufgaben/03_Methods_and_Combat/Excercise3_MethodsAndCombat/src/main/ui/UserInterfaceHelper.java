package main.ui;

import main.character.Enemy;
import main.character.GameCharacter;
import main.character.Player;
import main.combat.Battle;
import main.global.GameManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Utility class providing common UI functions for displaying game elements
 * and handling user input throughout the game.
 */
public class UserInterfaceHelper
{
    /**
     * Displays the main menu screen with the game logo.
     */
    public void displayMainMenu()
    {
        displayLogo();

        IO.println("Press any key to start your adventure!");
    }

    /**
     * Displays the game's ASCII art logo.
     */
    public static void displayLogo()
    {
        IO.println("""
                                     .__    .___.__              \s
                  ____ ___  ___ ____ |__| __| _/|__|__ __  _____ \s
                _/ __ \\\\  \\/  // ___\\|  |/ __ | |  |  |  \\/     \\\s
                \\  ___/ >    <\\  \\___|  / /_/ | |  |  |  /  Y Y  \\
                 \\___  >__/\\_ \\\\___  >__\\____ | |__|____/|__|_|  /
                     \\/      \\/    \\/        \\/                \\/\s
                                                                 \s
                """);
    }

    /**
     * Prints a formatted heading with equal signs.
     * 
     * @param text The heading text to display
     */
    public static void printHeading(String text)
    {
        IO.println("\n=== " + text + " ===\n");
    }

    /**
     * Prints a formatted sub-heading with double equal signs.
     * 
     * @param text The sub-heading text to display
     */
    public static void printSubHeading(String text)
    {
        IO.println("\n== " + text + " ==\n");
    }

    /**
     * Prints a prompt for player input.
     * 
     * @param text The prompt text to display
     */
    public static void printPlayerInputPrompt(String text)
    {
        IO.print("\n" + text + ": ");
    }

    /**
     * Gets a string input from the player with a custom prompt.
     * 
     * @param inputPrompt The prompt to display to the player
     * @return The player's input string
     */
    public static String getPlayerStringInput(String inputPrompt)
    {
        printPlayerInputPrompt(inputPrompt);
        return IO.readln();
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
     * Displays detailed information about a character including stats and equipment.
     * 
     * @param character The character whose information to display
     */
    public static void printCharacterInformation(GameCharacter character)
    {
        printHeading(character.getName());

        printSubHeading("Stats");

        IO.println("Health: \t" + character.getHealth() + " / " + character.getMaxHealth());
        IO.println("Strength: \t" + character.getStrength());
        IO.println("Dexterity: \t" + character.getDexterity());
        IO.println("Intelligence: \t" + character.getIntelligence());
        IO.println("Status: \t" + character.getStatus());

        printSubHeading("Equipment");

        IO.println("Weapon: \t" + character.getEquippedWeapon());
        character.getEquippedWeapon().printItemStats();
        IO.println("Armor: \t" + character.getEquippedArmour());
        character.getEquippedArmour().printItemStats();
        IO.println("Shield: \t" + character.getEquippedShield());
        character.getEquippedShield().printItemStats();
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

    /**
     * Pauses execution until the player presses Enter.
     */
    public static void waitForEnterKey()
    {
        IO.readln("Press Enter to continue...");
    }

    public static void delayShort()
    {
        delay(GameManager.DELAY_SHORT);
    }

    public static void delayMedium()
    {
        delay(GameManager.DELAY_MEDIUM);
    }

    public static void delayLong()
    {
        delay(GameManager.DELAY_LONG);
    }

    /**
     * Pauses execution for a specified duration.
     * 
     * @param seconds The number of seconds to delay
     */
    private static void delay(double seconds)
    {
        try
        {
            Thread.sleep((int) (seconds * 1000));
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Displays the player's character box with stats and status.
     * 
     * @param player The player character to display
     */
    public static void displayPlayer(Player player)
    {
        List<String> playerBox = player.getDisplayBox();

        for (String line : playerBox)
        {
            IO.println(line);
        }
    }

    /**
     * Displays all enemies in a battle side by side.
     * 
     * @param battle The battle containing the enemies to display
     */
    public static void displayEnemies(Battle battle)
    {
        List<List<String>> enemyBoxes = new ArrayList<>();

        for (Enemy enemy : battle.getEnemies())
        {
            enemyBoxes.add(enemy.getDisplayBox());
        }

        List<String> finalOutput = GameCharacter.combineEnemyBoxes(enemyBoxes);

        for (String line : finalOutput)
        {
            IO.println(line);
        }
    }
}
