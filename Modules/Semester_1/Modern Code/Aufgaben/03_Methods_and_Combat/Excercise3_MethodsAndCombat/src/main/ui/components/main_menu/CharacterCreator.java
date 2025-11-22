package main.ui.components.main_menu;

import main.character.GameCharacter;
import main.character.Player;
import main.ui.UserInterface;
import main.ui.UIHelper;
import main.ui.components.character.StatAllocationUI;

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
            UIHelper.clearScreen();

            UIHelper.printHeading("Character Creator");

            String name = UIHelper.getPlayerStringInput("Enter character name");
            playerCharacter = new Player(name);
            UIHelper.delayMedium();

            StatAllocationUI statAllocationUI = new StatAllocationUI(AVAILABLE_SKILL_POINTS, playerCharacter);
            statAllocationUI.startUI();

            UIHelper.delayMedium();


            UIHelper.printCharacterInformation(playerCharacter);

            isPlayerSatisfied = UIHelper.getYesNoInput("\nAre you satisfied with your creation?");

            if (!isPlayerSatisfied)
            {
                UIHelper.clearScreen();

                IO.println("Your creation has been discarded.");
                UIHelper.delayLong();
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




}
