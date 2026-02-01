package Global;

import Maze.AiMode;

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

    public static int mazeSize = 20;
    public static int treasureCount = 6;
    public static AiMode aiMode = AiMode.DEBUG;
}
