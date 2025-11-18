package main.ui.components.main_menu;

import main.character.Player;
import main.factory.baseFactories.ArmourFactory;
import main.factory.baseFactories.HealingPotionFactory;
import main.factory.baseFactories.ShieldFactory;
import main.factory.baseFactories.WeaponFactory;
import main.global.Difficulty;
import main.global.GameManager;
import main.item.ItemRarity;
import main.item.Weapon;
import main.ui.UserInterface;
import main.ui.UserInterfaceHelper;
import main.ui.components.battle.GameLoop;

/**
 * Main menu UI screen for the game.
 * Provides options to create a character, select difficulty, start the game, or exit.
 */
public class MainMenu extends UserInterface
{
    /**
     * Displays the main menu and processes player choices.
     * Loops indefinitely until the player exits the game.
     * Creates a default player with legendary equipment for testing.
     */
    @Override
    public void startUI()
    {
        while(true)
        {
            Player player = new Player("Player", 10, 10, 10);
            GameManager.setPlayer(player);
            player.equipItem(WeaponFactory.createRandomWeaponByRarity(ItemRarity.LEGENDARY), false);
            player.equipItem(ArmourFactory.createRandomArmourByRarity(ItemRarity.LEGENDARY), false);
            player.equipItem(ShieldFactory.createRandomShieldByRarity(ItemRarity.LEGENDARY), false);
            player.addItemsToInventory(HealingPotionFactory.createRandomPotion(), HealingPotionFactory.createRandomPotion(), HealingPotionFactory.createRandomPotion());


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

    /**
     * Displays the available menu options.
     * Shows current character and difficulty if they have been set.
     */
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

    /**
     * Checks if the game can be started.
     * Requires both a player character and difficulty to be set.
     * 
     * @return true if a character has been created and difficulty selected, false otherwise
     */
    public boolean canGameStart()
    {
        return GameManager.hasPlayerBeenInitialized && GameManager.difficulty != Difficulty.NONE;
    }
}
