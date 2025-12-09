import java.util.Arrays;

public class Secret
{
    public static int SECRET_COUNT = 4;
    public static int NUMBER_RANGE = 6;

    private int[] secretNumbers;
    private SecretColors[] secretColors;

    public Secret(int[] numbers, SecretColors[] colors)
    {
        setSecretNumbers(numbers);
        setSecretColors(colors);
    }

    public Pegs validateGuess(Secret guess)
    {
        int blackPegs = countBlackPegs(guess);
        int whitePegs = countWhitePegs(guess);

        return new Pegs(blackPegs, whitePegs);
    }

    public int countBlackPegs(Secret guess)
    {
        int blackPegs = 0;

        for (int i = 0; i < SECRET_COUNT; i++)
        {
            if (this.secretNumbers[i] == guess.getSecretNumbers()[i] &&
                this.secretColors[i] == guess.getSecretColors()[i])
            {
                blackPegs++;
            }
        }

        return blackPegs;
    }

    public int countWhitePegs(Secret guess)
    {
        int whitePegs = 0;

        for (SecretColors color : guess.getSecretColors())
        {
            if (Arrays.asList(this.secretColors).contains(color) &&
                this.secretColors[Arrays.asList(guess.getSecretColors()).indexOf(color)] != color)
            {
                whitePegs++;
            }
        }

        return whitePegs;
    }

    public int[] getSecretNumbers()
    {
        return secretNumbers;
    }

    public SecretColors[] getSecretColors()
    {
        return secretColors;
    }

    public void setSecretColors(SecretColors[] secretColors)
    {
        if (secretColors.length != SECRET_COUNT)
        {
            throw new IllegalArgumentException("Invalid secret length");
        }

        this.secretColors = secretColors;
    }

    public void setSecretNumbers(int[] secretNumbers)
    {
        if (secretNumbers.length != SECRET_COUNT)
        {
            throw new IllegalArgumentException("Invalid secret length");
        }

        for (int number : secretNumbers)
        {
            if (number < 1 || number > NUMBER_RANGE)
            {
                throw new IllegalArgumentException("Number out of range");
            }
        }

        this.secretNumbers = secretNumbers;
    }

    public static Secret generateRandomSecret()
    {
        int[] numbers = new int[SECRET_COUNT];
        SecretColors[] colors = new SecretColors[SECRET_COUNT];

        for (int i = 0; i < SECRET_COUNT; i++)
        {
            numbers[i] = (int) (Math.random() * NUMBER_RANGE) + 1;
            colors[i] = SecretColors.values()[(int) (Math.random() * SecretColors.values().length)];
        }

        return new Secret(numbers, colors);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Numbers: ");
        sb.append(Arrays.toString(secretNumbers));
        sb.append(" Colors: ");
        sb.append(Arrays.toString(secretColors));
        return sb.toString();
    }
}
