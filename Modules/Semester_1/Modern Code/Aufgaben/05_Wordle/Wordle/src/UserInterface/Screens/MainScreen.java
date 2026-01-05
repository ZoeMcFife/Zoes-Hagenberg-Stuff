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
            UI.displayWordleTitle();

            MenuItem startGame = new MenuItem("Start Game", this::startGame);
            MenuItem help = new MenuItem("Help", this::showHelp);
            MenuItem quitGame = new MenuItem("Quit Game", this::quitGame);
            Menu mainMenu = new Menu("Main Menu", startGame, help, quitGame);

            UI.printBlankSeparatorLine();

            mainMenu.startScreen();

            UI.clearScreen();
        }
    }

    private void startGame()
    {
        Wordle wordleGame = new Wordle();
        wordleGame.startScreen();
    }

    private void showHelp()
    {
        HelpScreen helpScreen = new HelpScreen();
        helpScreen.startScreen();
    }

    private void quitGame()
    {
        UI.exitGame();
    }

}
