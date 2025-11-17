package main.ui.components.battle;

import main.global.GameManager;
import main.ui.UserInterface;
import main.ui.UserInterfaceHelper;

/**
 * UI screen displayed when the player character dies.
 * Shows the player's final stats and removes them from the game.
 */
public class DeathUI extends UserInterface
{
    /**
     * Displays the death screen with player's final information.
     * Clears the player from the game manager after display.
     */
    @Override
    public void startUI()
    {
        UserInterfaceHelper.clearScreen();

        UserInterfaceHelper.displayLogo();

        UserInterfaceHelper.printHeading(GameManager.getPlayer().getName() + " has perished in battle!");

        UserInterfaceHelper.displayPlayer(GameManager.getPlayer());

        GameManager.removePlayer();

        UserInterfaceHelper.waitForEnterKey();
    }
}
