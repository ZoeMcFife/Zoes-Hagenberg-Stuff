package FileIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Reads Wordle words from CSV files and stores them in global variables.
 */
public class WordleWordReader
{
    public static String validGuessesFile = "files/valid_guesses.csv";
    public static String validSolutionsFile = "files/valid_solutions.csv";

    /**
     * Reads valid guesses and solutions from CSV files and stores them in global variables.
     */
    public static void readWordleWords()
    {
        Set<String> validGuesses = readCsvFile(validGuessesFile);
        Set<String> validSolutions = readCsvFile(validSolutionsFile);

        validGuesses.addAll(validSolutions);

        List<String> validSolutionsList = new ArrayList<>(validSolutions);

        Global.Words.validGuesses = validGuesses;
        Global.Words.validSolutions = validSolutionsList;
    }

    /**
     * Reads a CSV file and returns a set of words.
     *
     * @param file The path to the CSV file.
     * @return A set of words read from the file.
     */
    public static Set<String> readCsvFile(String file)
    {
        Set<String> words = new HashSet<>();

        try (BufferedReader reader = Files.newBufferedReader(Path.of(file)))
        {
            String line;

            // Skip header
            reader.readLine();

            while ((line = reader.readLine()) != null)
            {
                line = line.trim().toUpperCase();

                if (!line.isEmpty())
                {
                    words.add(line);
                }
            }
        }
        catch (IOException e)
        {
            System.err.println("Error reading file: " + file);
            System.err.println(e.getMessage());
            System.err.println("Make sure the file exists and is accessible. Program will exit.");
            System.exit(1);
        }

        return words;
    }
}
