package main.ui.components;

import main.global.Difficulty;
import main.global.GameManager;
import main.ui.UserInterface;
import main.ui.UserInterfaceHelper;

public class MainMenu extends UserInterface
{
    @Override
    public void startUI()
    {
        while(true)
        {
            UserInterfaceHelper.displayLogo();
            displayStartOptions();
            int choice = UserInterfaceHelper.getIntInput(1, 4);

            switch (choice)
            {
                case 1:
                    CharacterCreator characterCreator = new CharacterCreator();
                    characterCreator.startUI();
                    GameManager.setPlayer(characterCreator.getPlayerCharacter());
                    break;
                case 2:
                    DifficultySelection difficultySelection = new DifficultySelection();
                    difficultySelection.startUI();
                    GameManager.difficulty = difficultySelection.getSelectedDifficulty();
                    break;
                case 3:
                    IO.println("Starting game...");

                    break;
                case 4:
                    IO.println("Exiting game. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    IO.println("Invalid choice. Please try again.");
                    startUI();
                    break;
            }

            UserInterfaceHelper.clearScreen();
        }
    }

    public void displayStartOptions()
    {
        if (GameManager.hasPlayerBeenInitialized)
        {
            IO.println("1. Create Character (Current: " + GameManager.getPlayer().getName() + ")");
        }
        else
        {
            IO.println("1. Create Character");
        }

        if (GameManager.difficulty == Difficulty.NONE)
        {
            IO.println("2. Choose Difficulty");
        }
        else
        {
            IO.println("2. Choose Difficulty (Current: " + GameManager.difficulty + ")");
        }

        IO.println("3. Start Game");
        IO.println("4. Exit");

        IO.println("Choose an option (1-4)");
    }
}
