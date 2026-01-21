package UserInterface.Screens;

import Exercises.NumberToWords;
import UserInterface.Screen;
import UserInterface.UI;

public class NumberToWordsScreen extends Screen
{
    @Override
    public void startScreen()
    {
        UI.clearScreen();

        UI.printDoubleSeparatorLine();
        UI.printlnBlue("=== Number to Words Conversion ===");
        UI.printDoubleSeparatorLine();
        UI.printBlankSeparatorLine();

        printConversion(0);
        printConversion(5);
        printConversion(19);
        printConversion(23);
        printConversion(42);
        printConversion(100);
        printConversion(123);
        printConversion(456);
        printConversion(1000);
        printConversion(1234);
        printConversion(5678);
        printConversion(9999);

        UI.printBlankSeparatorLine();
        UI.printDoubleSeparatorLine();
        UI.printlnPurple("End of conversion list.");
        UI.printDoubleSeparatorLine();

        UI.waitForEnterKey();
    }

    private void printConversion(int number)
    {
        UI.printYellow(String.format("%-6d", number));
        UI.printCyan(" -> ");
        UI.printlnGreen(NumberToWords.numberToWords(number));
    }
}
