package UserInterface.Screens.Wordle;

import Global.Config;
import Global.Words;
import UserInterface.Screen;
import UserInterface.UI;

import java.util.List;

public class Wordle extends Screen
{
    private final String pickedWord;

    private final Guess[] guesses = new Guess[Config.MAX_ATTEMPTS];

    @Override
    public void startScreen()
    {
        UI.clearScreen();

        for (int attempt = 0; attempt < Config.MAX_ATTEMPTS; attempt++)
        {
            printGuesses();

            Guess currentGuess = enterGuess();

            if (currentGuess.getWord().equals("EXIT!"))
            {
                UI.clearScreen();
                UI.printlnYellow("You have exited the game.");
                UI.waitForEnterKey();
                return;
            }

            guesses[attempt] = currentGuess;

            if (isCorrectGuess(currentGuess.getWord()))
            {
                UI.clearScreen();
                UI.printlnGreen("Congratulations! You've guessed the word in " + (attempt + 1) + " attempts!");
                printGuesses();
                UI.waitForEnterKey();
                return;
            }

            UI.clearScreen();
        }

        // failure

        UI.clearScreen();
        printGuesses();

        UI.printAsteriskSeparatorLine();
        UI.printlnRed("You failed... ");
        UI.printlnRed("The correct word was: " + pickedWord);
        UI.printAsteriskSeparatorLine();
        UI.waitForEnterKey();
    }

    public Wordle()
    {
        pickedWord = Words.getRandomSolutionWord();

        if (Config.EASY_MODE)
        {
            UI.printlnGray("Picked Word (for testing purposes): " + pickedWord);
            UI.waitForEnterKey();
        }
    }

    private void printGuesses()
    {
        for (Guess guess : guesses)
        {
            if (guess != null)
            {
                guess.printGuess();
            }
            else
            {
                UI.printlnGray("_____");
            }
        }
    }

    private Guess enterGuess()
    {
        do
        {
            String currentGuess = UI.getStringInput("Enter your Guess", 5).toUpperCase();

            if (currentGuess.equals(Config.EXIT_COMMAND))
            {
                return new Guess("EXIT!", new GuessAccuracy[0]);
            }
            else if (currentGuess.equals(Config.HELP_COMMAND))
            {
               UI.printlnRed("Not implemented yet.");
            }

            if (validateGuess(currentGuess))
            {
                return computeGuess(currentGuess);
            }
            else
            {
                UI.printlnRed("Invalid Guess. Please try again.");
            }
        }
        while (true);
    }

    private boolean validateGuess(String guess)
    {
        return Words.validGuesses.contains(guess);
    }

    private boolean isCorrectGuess(String guess)
    {
        return guess.equals(pickedWord);
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
