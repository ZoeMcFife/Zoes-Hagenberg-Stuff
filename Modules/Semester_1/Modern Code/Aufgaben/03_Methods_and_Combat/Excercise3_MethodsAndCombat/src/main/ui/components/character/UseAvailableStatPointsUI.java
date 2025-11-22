package main.ui.components.character;

import main.character.Player;
import main.global.GameManager;
import main.ui.UserInterface;

public class UseAvailableStatPointsUI extends UserInterface
{
    @Override
    public void startUI()
    {
        Player player = GameManager.getPlayer();
        StatAllocationUI statAllocationUI = new StatAllocationUI(player.getAvailableStatPoints(), player);
        statAllocationUI.startUI();
    }

}
