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
            UI.printBlankSeparatorLine();

            MenuItem fibonacci = new MenuItem("Fibonacci", this::fibonacci);
            MenuItem towerOfHanoi = new MenuItem("Tower of Hanoi", this::towerOfHanoi);
            MenuItem decimalToBinary = new MenuItem("Decimal to Binary", this::decimalToBinary);
            MenuItem numberToWords = new MenuItem("Number to Words", this::NumberToWords);
            MenuItem tribonacci = new MenuItem("Tribonacci", this::tribonacci);
            MenuItem quit = new MenuItem("Quit", this::quit);

            Menu mainMenu = new Menu("Main Menu", fibonacci, towerOfHanoi, decimalToBinary, numberToWords, tribonacci, quit);

            mainMenu.startScreen();

            UI.clearScreen();
        }
    }

    public void fibonacci()
    {
        FibonacciScreen fibonacciScreen = new FibonacciScreen();
        fibonacciScreen.startScreen();
    }

    public void towerOfHanoi()
    {
        TowerOfHanoiScreen towerOfHanoiScreen = new TowerOfHanoiScreen();
        towerOfHanoiScreen.startScreen();
    }

    public void decimalToBinary()
    {
        DecimalToBinaryScreen decimalToBinaryScreen = new DecimalToBinaryScreen();
        decimalToBinaryScreen.startScreen();
    }

    public void NumberToWords()
    {
        NumberToWordsScreen numberToWordsScreen = new NumberToWordsScreen();
        numberToWordsScreen.startScreen();
    }

    public void tribonacci()
    {

    }

    public void quit()
    {
        UI.exit();
    }


}
