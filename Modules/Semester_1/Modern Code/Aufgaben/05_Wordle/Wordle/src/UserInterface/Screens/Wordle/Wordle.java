package UserInterface.Screens.Wordle;

import Global.Config;
import Global.Words;
import UserInterface.Screen;

import java.util.List;

public class Wordle extends Screen
{
    private final String pickedWord;

    private final String[] guesses = new String[Config.MAX_ATTEMPTS];

    @Override
    public void startScreen()
    {
        // Implementation for Wordle screen goes here
    }

    public Wordle()
    {
        pickedWord = "APPLE";

        Guess g = computeGuess("ALLEY");
        g.printGuess();

        //pickedWord = Words.getRandomSolutionWord();
    }

    private Guess computeGuess(String guessedWord)
    {
        GuessAccuracy[] accuracies = new GuessAccuracy[Config.MAX_WORD_LENGTH];
        boolean[] letterUsedInPickedWord = new boolean[Config.MAX_WORD_LENGTH];

        // First pass: Check for correct letters in correct positions
        for (int i = 0; i < Config.MAX_WORD_LENGTH; i++)
        {
            if (guessedWord.charAt(i) == pickedWord.charAt(i))
            {
                accuracies[i] = GuessAccuracy.CORRECT;
                letterUsedInPickedWord[i] = true;
            }
        }

        // Second pass: Check for correct letters in wrong positions
        for (int i = 0; i < Config.MAX_WORD_LENGTH; i++)
        {
            if (accuracies[i] == null)
            {
                boolean found = false;
                for (int j = 0; j < Config.MAX_WORD_LENGTH; j++)
                {
                    if (!letterUsedInPickedWord[j] && guessedWord.charAt(i) == pickedWord.charAt(j))
                    {
                        found = true;
                        letterUsedInPickedWord[j] = true;
                        break;
                    }
                }
                accuracies[i] = found ? GuessAccuracy.WRONG_POSITION : GuessAccuracy.INCORRECT;
            }
        }

        return new Guess(guessedWord, accuracies);

    }



}
