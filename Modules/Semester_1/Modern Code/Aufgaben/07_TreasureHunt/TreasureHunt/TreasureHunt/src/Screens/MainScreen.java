package Screens;

import UserInterface.Menu.Menu;
import UserInterface.Menu.MenuItem;
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

            MenuItem mazeTestMenuItem = new MenuItem("Maze Test Screen", this::mazeTestScreen);

            Menu mainMenu = new Menu("Main Menu", mazeTestMenuItem);

            mainMenu.startScreen();

            UI.clearScreen();
        }
    }

    public void mazeTestScreen()
    {
        MazeTestScreen mazeTestScreen = new MazeTestScreen();
        mazeTestScreen.startScreen();
    }
}
