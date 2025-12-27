package UserInterface.Screens;

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
        UI.printGreen("HELP! ");
        UI.printYellow("to receive a hint for the current word." + System.lineSeparator());

        UI.printYellow("Type ");
        UI.printGreen("QUIT! ");
        UI.printYellow("to exit the current game." + System.lineSeparator());

        UI.waitForEnterKey();
    }
}
