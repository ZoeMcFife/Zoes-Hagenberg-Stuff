package UserInterface.Screens;

import Exercises.DecimalToBinary;
import UserInterface.Screen;
import UserInterface.UI;

public class DecimalToBinaryScreen extends Screen
{
    @Override
    public void startScreen()
    {
        UI.clearScreen();

        UI.printYellow("===== Decimal to Binary Converter =====" + System.lineSeparator());

        printBinary(0);
        printBinary(1);
        printBinary(5);
        printBinary(10);
        printBinary(15);
        printBinary(42);
        printBinary(255);

        IO.println("");
        UI.waitForEnterKey();
    }

    private void printBinary(int n)
    {
        IO.print("Decimal: " + n + " -> Binary: ");
        UI.printlnYellow(DecimalToBinary.decimalToBinary(n));
    }
}
