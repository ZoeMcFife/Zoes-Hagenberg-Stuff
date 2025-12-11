public class Clanker implements Runnable
{
    public int guessCount = 0;

    @Override
    public void run()
    {
        Pegs pegs = new Pegs(-1, -1);

        do
        {
            Secret secret = Secret.generateRandomSecret();

            Secret guess = Secret.generateRandomSecret();

            IO.println(secret);
            IO.println(guess);

            pegs = secret.validateGuess(guess);

            guessCount++;

            IO.println(pegs.toString());
        }
        while (pegs.black() != 4);
    }
}
