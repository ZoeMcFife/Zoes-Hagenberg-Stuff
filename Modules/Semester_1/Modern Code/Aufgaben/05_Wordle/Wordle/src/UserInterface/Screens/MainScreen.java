package UserInterface.Screens;

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
            UI.displayWordleTitle();

            MenuItem startGame = new MenuItem("Start Game", this::startGame);
            MenuItem quitGame = new MenuItem("Quit Game", this::quitGame);

            UI.printBlankSeparatorLine();

            Menu mainMenu = new Menu("Main Menu", startGame, quitGame);
            mainMenu.startScreen();

            UI.clearScreen();
        }
    }

    private void startGame()
    {

    }

    private void quitGame()
    {
        UI.exitGame();
    }

}
