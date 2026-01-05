package Global;

/**
 * Global configuration class for application-wide constants.
 */
public class Config
{
    /** Short delay **/
    public static final int DELAY_SHORT = 500;
    /** Medium delay **/
    public static final int DELAY_MEDIUM = 1000;
    /** Long delay **/
    public static final int DELAY_LONG = 2000;

    public static final int MAX_ATTEMPTS = 6;
    public static final int MAX_WORD_LENGTH = 5;

    /** Number of letters revealed in a hint **/
    public static final int HINT_ACCURACY = 1;

    /** Placeholder letter for unguessed letters used for the auto solve feature **/
    public static final char PLACEHOLDER_LETTER = '_';

    /** Enable easy mode; this just shows the picked word before the game starts **/
    public static final boolean EASY_MODE = true;

    /** Command to exit the current game **/
    public static final String EXIT_COMMAND = "EXIT!";

    /** Command to get help/hint for the current word **/
    public static final String HELP_COMMAND = "HELP!";
}
