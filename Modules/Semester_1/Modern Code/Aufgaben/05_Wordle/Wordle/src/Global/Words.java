package Global;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Global class to store valid Wordle words.
 */
public class Words
{
    /**
     * List of valid Wordle solution words.
     */
    public static List<String> validSolutions = new ArrayList<String>();

    /**
     * Set of valid Wordle guess words. Using a set for O(1) lookup time.
     */
    public static Set<String> validGuesses = new HashSet<>();

    public static String getRandomSolutionWord()
    {
        int randomIndex = (int) (Math.random() * validSolutions.size());
        return validSolutions.get(randomIndex);
    }
}
