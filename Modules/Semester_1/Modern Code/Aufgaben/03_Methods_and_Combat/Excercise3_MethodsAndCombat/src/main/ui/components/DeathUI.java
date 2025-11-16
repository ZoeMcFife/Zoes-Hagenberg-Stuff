package main.ui.components;

import main.global.GameManager;
import main.ui.UserInterface;
import main.ui.UserInterfaceHelper;

public class DeathUI extends UserInterface
{
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
