package main.ui.components.character;

import main.character.Player;
import main.global.GameManager;
import main.ui.UserInterface;

/**
 * UI screen for using available stat points on a player character.
 * Delegates to StatAllocationUI for the actual point allocation interface.
 */
public class UseAvailableStatPointsUI extends UserInterface
{
    /**
     * Starts the stat point usage interface.
     * Launches the stat allocation UI with the player's current available points.
     */
    @Override
    public void startUI()
    {
        Player player = GameManager.getPlayer();
        StatAllocationUI statAllocationUI = new StatAllocationUI(player.getAvailableStatPoints(), player);
        statAllocationUI.startUI();
    }

}
