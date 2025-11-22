package main.ui.components.battle;

import main.global.GameManager;
import main.ui.UserInterface;
import main.ui.UIHelper;

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
        UIHelper.clearScreen();

        UIHelper.displayLogo();

        UIHelper.printHeading(GameManager.getPlayer().getName() + " has perished in battle!");

        UIHelper.displayPlayer(GameManager.getPlayer());

        GameManager.removePlayer();

        UIHelper.waitForEnterKey();
    }
}
