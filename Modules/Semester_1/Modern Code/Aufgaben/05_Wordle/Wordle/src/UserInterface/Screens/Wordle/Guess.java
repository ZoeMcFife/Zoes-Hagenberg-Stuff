package UserInterface.Screens.Wordle;

import Global.Config;
import UserInterface.UI;

public class Guess
{
    private final String word;
    private final GuessAccuracy[] accuracies;

    public Guess(String word, GuessAccuracy[] accuracies)
    {
        this.word = word;
        this.accuracies = accuracies;
    }

    public void printGuess()
    {
        for (int i = 0; i < word.length(); i++)
        {
            char letter = word.charAt(i);
            GuessAccuracy accuracy = accuracies[i];

            switch (accuracy)
            {
                case CORRECT -> UI.printGreen(String.valueOf(letter));
                case WRONG_POSITION -> UI.printYellow(String.valueOf(letter));
                case INCORRECT -> UI.printGray(String.valueOf(letter));
            }
        }
        UI.printBlankSeparatorLine();
    }

    public String getWord()
    {
        return word;
    }

    public GuessAccuracy[] getAccuracies()
    {
        return accuracies;
    }
}
