class Main
{
    public static void main(String[] args)
    {
        Clanker clanker = new Clanker();
        Clanker clanker2 = new Clanker();

        Thread thread1 = new Thread(clanker);
        Thread thread2 = new Thread(clanker2);

        thread1.start();
        thread2.start();

        try
        {
            thread1.join();
            thread2.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        if (clanker.guessCount < clanker2.guessCount)
        {
            IO.println("Clanker 1 wins with " + clanker.guessCount + " guesses!");
            IO.println("Clanker 2 got murdered. " + clanker2.guessCount + " guesses.");
        }
        else if (clanker2.guessCount < clanker.guessCount)
        {
            IO.println("Clanker 2 wins with " + clanker2.guessCount + " guesses!");
            IO.println("Clanker 1 got murdered. "  + clanker.guessCount + " guesses.");
        }
        else
        {
            IO.println("It's a tie! Both clankers had " + clanker.guessCount + " guesses!");
            IO.println("Both clankers die.");
        }

    }
}