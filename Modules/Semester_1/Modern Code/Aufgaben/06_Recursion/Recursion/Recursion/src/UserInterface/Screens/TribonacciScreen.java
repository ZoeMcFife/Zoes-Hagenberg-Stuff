package UserInterface.Screens;

import Exercises.Tribonacci;
import UserInterface.Screen;
import UserInterface.UI;

public class TribonacciScreen extends Screen
{
    @Override
    public void startScreen()
    {
        UI.clearScreen();

        UI.printDoubleSeparatorLine();
        UI.printlnBlue("=== Tribonacci Sequence ===");
        UI.printDoubleSeparatorLine();
        UI.printBlankSeparatorLine();

        for (int i = 0; i <= 14; i++)
        {
            printTribonacci(i);
        }

        UI.printBlankSeparatorLine();
        UI.printDoubleSeparatorLine();
        UI.printlnPurple("End of sequence.");
        UI.printDoubleSeparatorLine();

        UI.waitForEnterKey();
    }

    private void printTribonacci(int n)
    {
        UI.printYellow("T(" + n + ")");
        UI.printCyan(" = ");
        UI.printlnGreen(String.valueOf(Tribonacci.tribonacciMemoized(n)));
    }
}
