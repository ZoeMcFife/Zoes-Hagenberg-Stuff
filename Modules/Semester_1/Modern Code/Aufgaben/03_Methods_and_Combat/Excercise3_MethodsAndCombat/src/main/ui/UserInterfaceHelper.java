package main.ui;

import main.character.Enemy;
import main.character.GameCharacter;
import main.character.Player;
import main.combat.Battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterfaceHelper
{
    public void displayMainMenu()
    {
        displayLogo();

        IO.println("Press any key to start your adventure!");
    }

    public static void displayLogo()
    {
        IO.println("                     .__    .___.__               \n" +
                "  ____ ___  ___ ____ |__| __| _/|__|__ __  _____  \n" +
                "_/ __ \\\\  \\/  // ___\\|  |/ __ | |  |  |  \\/     \\ \n" +
                "\\  ___/ >    <\\  \\___|  / /_/ | |  |  |  /  Y Y  \\\n" +
                " \\___  >__/\\_ \\\\___  >__\\____ | |__|____/|__|_|  /\n" +
                "     \\/      \\/    \\/        \\/                \\/ \n" +
                "                                                  \n");
    }

    public static void printHeading(String text)
    {
        IO.println("\n=== " + text + " ===\n");
    }

    public static void printSubHeading(String text)
    {
        IO.println("\n== " + text + " ==\n");
    }

    public static void printPlayerInputPrompt(String text)
    {
        IO.print("\n" + text + ": ");
    }

    public static String getPlayerStringInput(String inputPrompt)
    {
        printPlayerInputPrompt(inputPrompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void clearScreen()
    {
        for (int i = 0; i < 50; i++)
        {
            System.out.println();
        }
    }

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

    public static int getIntInput(int min, int max)
    {
        while (true)
        {
            try
            {
                IO.print("> ");
                Scanner scanner = new Scanner(System.in);

                int value = Integer.parseInt(scanner.nextLine());

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

    public static boolean getYesNoInput(String prompt)
    {
        while (true)
        {
            IO.print(prompt + " (y/n): ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().trim().toLowerCase();

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

    public static void waitForEnterKey()
    {
        IO.println("Press Enter to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void delay(double seconds)
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

    public static void displayPlayer(Player player)
    {
        List<String> playerBox = player.getDisplayBox();

        for (String line : playerBox)
        {
            IO.println(line);
        }
    }

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
