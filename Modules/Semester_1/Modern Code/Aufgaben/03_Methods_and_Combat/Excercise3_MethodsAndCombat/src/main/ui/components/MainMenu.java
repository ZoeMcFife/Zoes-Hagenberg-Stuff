package main.ui.components;

import main.global.Difficulty;
import main.global.GameManager;
import main.ui.UserInterface;
import main.ui.UserInterfaceHelper;

import static java.io.IO.*;

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
                    if (!canGameStart())
                    {
                        println("You must create a character and choose a difficulty before starting the game.");
                        UserInterfaceHelper.waitForEnterKey();
                        break;
                    }

                    println("Starting game...");

                    break;
                case 4:
                    println("Exiting game. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    println("Invalid choice. Please try again.");
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
            println("1. Create Character (Current: " + GameManager.getPlayer().getName() + ")");
        }
        else
        {
            println("1. Create Character");
        }

        if (GameManager.difficulty == Difficulty.NONE)
        {
            println("2. Choose Difficulty");
        }
        else
        {
            println("2. Choose Difficulty (Current: " + GameManager.difficulty + ")");
        }

        println("3. Start Game");
        println("4. Exit");

        println("Choose an option (1-4)");
    }

    public boolean canGameStart()
    {
        return GameManager.hasPlayerBeenInitialized && GameManager.difficulty != Difficulty.NONE;
    }
}
