package UserInterface.Screens;

import Global.Config;
import UserInterface.Screen;
import UserInterface.UI;

public class HelpScreen extends Screen
{
    @Override
    public void startScreen()
    {
        UI.clearScreen();
        UI.printlnGreen("===== Help =====");

        UI.printYellow("Type ");
        UI.printGreen(Config.HELP_COMMAND);
        UI.printYellow(" to receive a hint for the current word." + System.lineSeparator());

        UI.printYellow("Type ");
        UI.printGreen(Config.EXIT_COMMAND);
        UI.printYellow(" to exit the current game." + System.lineSeparator());

        UI.waitForEnterKey();
    }
}
