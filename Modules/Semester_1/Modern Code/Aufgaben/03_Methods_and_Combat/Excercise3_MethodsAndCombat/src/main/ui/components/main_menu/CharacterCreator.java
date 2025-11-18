package main.ui.components.main_menu;

import main.character.GameCharacter;
import main.character.Player;
import main.ui.UserInterface;
import main.ui.UserInterfaceHelper;

/**
 * UI screen for creating a new player character.
 * Allows the player to choose a name and allocate stat points.
 */
public class CharacterCreator extends UserInterface
{
    /** Total number of skill points available for stat allocation */
    private static final int AVAILABLE_SKILL_POINTS = 10;

    private Player playerCharacter;

    /**
     * Displays the character creation interface.
     * Prompts for name and stat allocation, then confirms the creation.
     * Repeats if the player is not satisfied with their creation.
     */
    @Override
    public void startUI()
    {
        boolean isPlayerSatisfied = false;

        do
        {
            UserInterfaceHelper.clearScreen();

            UserInterfaceHelper.printHeading("Character Creator");

            String name = UserInterfaceHelper.getPlayerStringInput("Enter character name");

            UserInterfaceHelper.delayMedium();

            int strength = GameCharacter.MIN_STAT_VALUE;
            int dexterity = GameCharacter.MIN_STAT_VALUE;
            int intelligence = GameCharacter.MIN_STAT_VALUE;
            int remainingPoints = AVAILABLE_SKILL_POINTS;

            while (remainingPoints > 0)
            {
                printStatAllocationMenu(strength, dexterity, intelligence, remainingPoints);
                int choice = UserInterfaceHelper.getIntInput(1, 3);

                switch (choice)
                {
                    case 1:
                        if (strength >= GameCharacter.MAX_STAT_VALUE)
                        {
                            IO.println("Strength is already at maximum value.");
                            continue;
                        }

                        strength++;
                        remainingPoints--;

                        break;
                    case 2:
                        if (dexterity >= GameCharacter.MAX_STAT_VALUE)
                        {
                            IO.println("Dexterity is already at maximum value.");
                            continue;
                        }

                        dexterity++;
                        remainingPoints--;

                        break;
                    case 3:
                        if (intelligence >= GameCharacter.MAX_STAT_VALUE)
                        {
                            IO.println("Intelligence is already at maximum value.");
                            continue;
                        }

                        intelligence++;
                        remainingPoints--;

                        break;
                }

                UserInterfaceHelper.clearScreen();

            }

            UserInterfaceHelper.delayMedium();

            playerCharacter = new Player(name, strength, dexterity, intelligence);

            UserInterfaceHelper.printCharacterInformation(playerCharacter);

            isPlayerSatisfied = UserInterfaceHelper.getYesNoInput("\nAre you satisfied with your creation?");

            if (!isPlayerSatisfied)
            {
                UserInterfaceHelper.clearScreen();

                IO.println("Your creation has been discarded.");
                UserInterfaceHelper.delayLong();
            }
        }
        while (!isPlayerSatisfied);
    }

    /**
     * Gets the created player character.
     * 
     * @return The player character created during character creation
     */
    public Player getPlayerCharacter()
    {
        return playerCharacter;
    }

    /**
     * Displays the stat allocation menu showing current stats and remaining points.
     * 
     * @param strength Current strength value
     * @param dexterity Current dexterity value
     * @param intelligence Current intelligence value
     * @param remainingPoints Number of unallocated skill points
     */
    private void printStatAllocationMenu(int strength, int dexterity, int intelligence, int remainingPoints)
    {
        IO.println("\nAllocate your skill points:");
        IO.println("1. Strength: " + strength);
        IO.println("2. Dexterity: " + dexterity);
        IO.println("3. Intelligence: " + intelligence);
        IO.println("Remaining Points: " + remainingPoints);
        IO.println("Choose a stat to increase: (1=STR, 2=DEX, 3=INT)");
    }


}
