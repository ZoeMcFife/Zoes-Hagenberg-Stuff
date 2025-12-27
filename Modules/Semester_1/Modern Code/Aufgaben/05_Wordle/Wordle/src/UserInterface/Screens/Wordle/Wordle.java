package UserInterface.Screens.Wordle;

import Global.Config;
import Global.Words;
import UserInterface.Screen;
import UserInterface.UI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Wordle extends Screen
{
    private final String pickedWord;

    private final Guess[] guesses = new Guess[Config.MAX_ATTEMPTS];

    private final char[] guessedLetters = new char[Config.MAX_WORD_LENGTH];

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
        for (int i = 0; i < guessedLetters.length; i++)
        {
            guessedLetters[i] = Config.PLACEHOLDER_LETTER;
        }

        pickedWord = Words.getRandomSolutionWord();
        //pickedWord = "APPLE";

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
            String currentGuess = UI.getStringInput("Enter your Guess", Config.MAX_WORD_LENGTH, Config.MAX_WORD_LENGTH).toUpperCase();

            if (currentGuess.equals(Config.EXIT_COMMAND))
            {
                return new Guess("EXIT!", new GuessAccuracy[0]);
            }
            else if (currentGuess.equals(Config.HELP_COMMAND))
            {
                return findHintGuess();
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
                guessedLetters[i] = guessedWord.charAt(i);
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

    private Guess findHintGuess()
    {
        char[] missingLetters = getMissingLetters();

        char[] emptyArray = new char[Config.MAX_WORD_LENGTH];
        for (int i = 0; i < emptyArray.length; i++)
        {
            emptyArray[i] = Config.PLACEHOLDER_LETTER;
        }

        if (Arrays.equals(missingLetters, emptyArray))
        {
            UI.printlnRed("No missing letters to provide hints for.");
            UI.delayMedium();
            return computeGuess("APPLE");
        }

        List<String> words = new ArrayList<>();
        words.addAll(Words.validGuesses);
        Collections.shuffle(words);

        for (String word : words)
        {
            int availableHints = Config.HINT_ACCURACY;

            for (int i = 0; i < word.length(); i++)
            {
                if (missingLetters[i] != Config.PLACEHOLDER_LETTER)
                {
                    if (word.charAt(i) == missingLetters[i])
                    {
                        availableHints--;

                        if (availableHints <= 0)
                        {
                            return computeGuess(word);
                        }
                    }
                }
            }
        }

        UI.printlnRed("No more hints available. Entering APPLE as default guess.");
        UI.delayMedium();
        return computeGuess("APPLE");
    }

    private char[] getMissingLetters()
    {
        char[] missingLetters = new char[Config.MAX_WORD_LENGTH];

        for (int i = 0; i < guessedLetters.length; i++)
        {
            missingLetters[i] = Config.PLACEHOLDER_LETTER;

            if (guessedLetters[i] == Config.PLACEHOLDER_LETTER)
            {
                missingLetters[i] = pickedWord.charAt(i);
            }
        }

        return missingLetters;
    }
}
