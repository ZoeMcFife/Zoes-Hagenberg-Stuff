package main.ui.components.main_menu;

import main.global.Difficulty;
import main.ui.UserInterface;
import main.ui.UIHelper;

/**
 * UI screen for selecting game difficulty.
 * Allows the player to choose between Easy, Medium, or Hard difficulty.
 */
public class DifficultySelection extends UserInterface
{
    private Difficulty selectedDifficulty;

    /**
     * Displays the difficulty selection menu and processes the player's choice.
     * Continues prompting until a valid difficulty is selected.
     */
    @Override
    public void startUI()
    {
        UIHelper.clearScreen();

        boolean difficultySelected = false;

        do
        {
            UIHelper.printHeading("Difficulty Selection");

            displayDifficultyOptions();
            int choice = UIHelper.getIntInput(1, 3);

            switch(choice)
            {
                case 1:
                    selectedDifficulty = Difficulty.EASY;
                    difficultySelected = true;
                    break;
                case 2:
                    selectedDifficulty = Difficulty.MEDIUM;
                    difficultySelected = true;
                    break;
                case 3:
                    selectedDifficulty = Difficulty.HARD;
                    difficultySelected = true;
                    break;
                default:
                    IO.println("Invalid choice. Please select a valid difficulty option.");
                    break;
            }
        }
        while (!difficultySelected);

        IO.println("You have selected " + selectedDifficulty + " difficulty.");
        UIHelper.delayLong();
    }

    /**
     * Displays the available difficulty options with descriptions.
     */
    private void displayDifficultyOptions()
    {
        IO.println("\nDifficulty:");
        IO.println("1. EASY: Smell the flowers.");
        IO.println("2. MEDIUM: Hold your ground.");
        IO.println("3. HARD: This will make your life hell.");
        IO.println("Choose a stat to increase: (1=EASY, 2=MEDIUM, 3=HARD)");
    }

    /**
     * Gets the difficulty selected by the player.
     * 
     * @return The selected difficulty level
     */
    public Difficulty getSelectedDifficulty()
    {
        return selectedDifficulty;
    }
}
