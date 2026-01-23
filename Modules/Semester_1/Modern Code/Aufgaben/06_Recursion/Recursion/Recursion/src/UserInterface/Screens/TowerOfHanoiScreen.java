package UserInterface.Screens;

import Exercises.TowerOfHanoi;
import UserInterface.Screen;
import UserInterface.UI;

public class TowerOfHanoiScreen extends Screen
{
    @Override
    public void startScreen()
    {
        UI.clearScreen();

        TowerOfHanoi towerOfHanoi3 = new TowerOfHanoi(3);

        UI.printBlankSeparatorLine();

        TowerOfHanoi towerOfHanoi4 = new TowerOfHanoi(4);

        UI.printBlankSeparatorLine();

        TowerOfHanoi towerOfHanoi5 = new TowerOfHanoi(5);

        UI.waitForEnterKey();
    }
}
