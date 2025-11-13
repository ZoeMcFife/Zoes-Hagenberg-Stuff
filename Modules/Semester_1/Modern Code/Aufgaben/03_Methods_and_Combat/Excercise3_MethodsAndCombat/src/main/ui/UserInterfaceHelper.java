package main.ui;

import main.character.GameCharacter;

import java.util.Scanner;

import static main.util.IO.*;

public class UserInterfaceHelper
{
    public void displayMainMenu()
    {
        displayLogo();

        println("Press any key to start your adventure!");
    }

    public static void displayLogo()
    {
        println("                     .__    .___.__               \n" +
                "  ____ ___  ___ ____ |__| __| _/|__|__ __  _____  \n" +
                "_/ __ \\\\  \\/  // ___\\|  |/ __ | |  |  |  \\/     \\ \n" +
                "\\  ___/ >    <\\  \\___|  / /_/ | |  |  |  /  Y Y  \\\n" +
                " \\___  >__/\\_ \\\\___  >__\\____ | |__|____/|__|_|  /\n" +
                "     \\/      \\/    \\/        \\/                \\/ \n" +
                "                                                  \n");
    }

    public static void printHeading(String text)
    {
        println("\n=== " + text + " ===\n");
    }

    public static void printSubHeading(String text)
    {
        println("\n== " + text + " ==\n");
    }

    public static void printPlayerInputPrompt(String text)
    {
        print("\n" + text + ": ");
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

        println("Health: \t" + character.getHealth() + " / " + character.getMaxHealth());
        println("Strength: \t" + character.getStrength());
        println("Dexterity: \t" + character.getDexterity());
        println("Intelligence: \t" + character.getIntelligence());
        println("Status: \t" + character.getStatus());

        printSubHeading("Equipment");

        println("Weapon: \t" + character.getEquippedWeapon());
        character.getEquippedWeapon().printItemStats();
        println("Armor: \t" + character.getEquippedArmour());
        character.getEquippedArmour().printItemStats();
        println("Shield: \t" + character.getEquippedShield());
        character.getEquippedShield().printItemStats();
    }

    public static int getIntInput(int min, int max)
    {
        while (true)
        {
            try
            {
                print("> ");
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

            println("Invalid input. Enter a number between " + min + " and " + max + ".");
        }
    }

    public static boolean getYesNoInput(String prompt)
    {
        while (true)
        {
            print(prompt + " (y/n): ");
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

            println("Invalid input. Please enter 'y' or 'n'.");
        }
    }

    public static void waitForEnterKey()
    {
        println("Press Enter to continue...");
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
}
