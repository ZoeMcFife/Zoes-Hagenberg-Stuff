package UserInterface.Screens.Wordle;

import Global.Config;
import Global.Words;
import UserInterface.Screen;

import java.util.List;

public class Wordle extends Screen
{
    private final String pickedWord;

    private final String[] guesses = new String[Config.MAX_ATTEMPTS];

    public Wordle()
    {
        pickedWord = Words.getRandomSolutionWord();
    }

    @Override
    public void startScreen()
    {
        // Implementation for Wordle screen goes here
    }

}
