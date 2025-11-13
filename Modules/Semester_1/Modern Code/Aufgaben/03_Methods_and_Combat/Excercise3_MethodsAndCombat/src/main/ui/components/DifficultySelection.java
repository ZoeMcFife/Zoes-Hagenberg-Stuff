package main.ui.components;

import main.global.Difficulty;
import main.ui.UserInterface;
import main.ui.UserInterfaceHelper;

public class DifficultySelection extends UserInterface
{
    private Difficulty selectedDifficulty;

    @Override
    public void startUI()
    {
        UserInterfaceHelper.clearScreen();

        boolean difficultySelected = false;

        do
        {
            UserInterfaceHelper.printHeading("Difficulty Selection");

            displayDifficultyOptions();
            int choice = UserInterfaceHelper.getIntInput(1, 3);

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
        UserInterfaceHelper.delay(5);
    }

    private void displayDifficultyOptions()
    {
        IO.println("\nDifficulty:");
        IO.println("1. EASY: Smell the flowers.");
        IO.println("2. MEDIUM: Hold your ground.");
        IO.println("3. HARD: This will make your life hell.");
        IO.println("Choose a stat to increase: (1=EASY, 2=MEDIUM, 3=HARD)");
    }

    public Difficulty getSelectedDifficulty()
    {
        return selectedDifficulty;
    }
}
