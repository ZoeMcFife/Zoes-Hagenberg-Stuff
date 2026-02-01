package Screens;

import Global.Config;
import Maze.AiMode;
import UserInterface.Menu.Menu;
import UserInterface.Menu.MenuItem;
import UserInterface.Screen;
import UserInterface.UI;

public class SettingsScreen extends Screen
{
    private boolean finishedSettings = false;

    @Override
    public void startScreen()
    {
        MenuItem mazeSizeMenuItem = new MenuItem("Set Maze Size", this::setMazeSize);
        MenuItem treasureCountMenuItem = new MenuItem("Set Treasure Count", this::setTreasureCount);
        MenuItem aiModeMenuItem = new MenuItem("Set AI Mode", this::setAiMode);
        MenuItem exitMenuItem = new MenuItem("Exit Settings", () -> finishedSettings = true);

        Menu settingsMenu = new Menu("Settings Menu", mazeSizeMenuItem, treasureCountMenuItem, aiModeMenuItem, exitMenuItem);

        while (!finishedSettings)
        {
            UI.clearScreen();

            displayCurrentSettings();

            settingsMenu.startScreen();
        }
    }

    private void displayCurrentSettings()
    {
        UI.printlnBlue("Current Settings:");
        UI.printlnBlue("Maze Size: " + Config.mazeSize);
        UI.printlnBlue("Treasure Count: " + Config.treasureCount);
        UI.printlnBlue("AI Mode: " + Config.aiMode);
        UI.printAsteriskSeparatorLine();
    }

    private void setMazeSize()
    {
        UI.printPurple("Set maze size: ");

        Config.mazeSize = UI.getIntInput(5, 100);

        UI.printlnBlue("Set maze size: " + Config.mazeSize);
        UI.waitForEnterKey();
    }

    private void setTreasureCount()
    {
        UI.printPurple("Set treasure count: ");
        Config.treasureCount = UI.getIntInput(1, Config.mazeSize * Config.mazeSize / 2);
        UI.printlnBlue("Set treasure count: " + Config.treasureCount);
        UI.waitForEnterKey();
    }

    private  void setAiMode()
    {
        UI.printlnPurple("Set AI Mode (GREEDY, MINIMAX, DEBUG): ");
        String input = UI.getStringInput("AI Mode").toUpperCase();

        switch (input)
        {
            case "GREEDY":
                Config.aiMode = AiMode.GREEDY;
                break;
            case "MINIMAX":
                Config.aiMode = AiMode.MINIMAX;
                break;
            case "DEBUG":
                Config.aiMode = AiMode.DEBUG;
                UI.printlnCyan("AI will use Minimax algorithm but also display greedy path for debugging. Greedy is CYAN, Minimax is MAGENTA.");
                break;
            default:
                UI.printlnRed("Invalid AI Mode. Keeping previous setting: " + Config.aiMode);
                UI.waitForEnterKey();
        }

        UI.printlnBlue("Set AI Mode: " + Config.aiMode);
        UI.waitForEnterKey();
    }

}
