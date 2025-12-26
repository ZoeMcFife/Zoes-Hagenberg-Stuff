import FileIO.WordleWordReader;
import UserInterface.Screens.MainScreen;
import UserInterface.UI;

public class Main
{
    public static void main(String[] args)
    {
        WordleWordReader.readWordleWords();

        MainScreen mainScreen = new MainScreen();
        mainScreen.startScreen();
    }
}
