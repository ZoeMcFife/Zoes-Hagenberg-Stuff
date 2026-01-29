package Screens;

import UserInterface.Menu.Menu;
import UserInterface.Screen;
import UserInterface.UI;

public class MainScreen extends Screen
{
    @Override
    public void startScreen()
    {
        while (true)
        {
            UI.printBlankSeparatorLine();

            Menu mainMenu = new Menu("Main Menu");

            mainMenu.startScreen();

            UI.clearScreen();
        }
    }
}
