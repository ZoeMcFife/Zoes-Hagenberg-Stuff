package UserInterface.Screens;

import UserInterface.Menu.Menu;
import UserInterface.Menu.MenuItem;
import UserInterface.Screen;
import UserInterface.Screens.Wordle.Wordle;
import UserInterface.UI;

public class MainScreen extends Screen
{
    @Override
    public void startScreen()
    {
        while (true)
        {
            Menu mainMenu = new Menu("Main Menu");

            UI.printBlankSeparatorLine();

            mainMenu.startScreen();

            UI.clearScreen();
        }
    }


}
