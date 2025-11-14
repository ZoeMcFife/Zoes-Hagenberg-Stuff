package main.ui.components;

import main.character.DangerLevel;
import main.character.Player;
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
            Player player = new Player("Player", 10, 10, 10);
            GameManager.setPlayer(player);

            GameManager.difficulty = Difficulty.HARD;

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
                        IO.println("You must create a character and choose a difficulty before starting the game.");
                        UserInterfaceHelper.waitForEnterKey();
                        break;
                    }

                    IO.println("Starting game...");
                    UserInterfaceHelper.clearScreen();

                    GameLoop gameLoop = new GameLoop();
                    gameLoop.startUI();

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

    public boolean canGameStart()
    {
        return GameManager.hasPlayerBeenInitialized && GameManager.difficulty != Difficulty.NONE;
    }
}
