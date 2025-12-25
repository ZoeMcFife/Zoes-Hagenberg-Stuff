import FileIO.WordleWordReader;

public class Main
{
    public static void main(String[] args)
    {
        WordleWordReader.readWordleWords();

        IO.println(Global.Words.validGuesses);
        IO.println(Global.Words.validSolutions);
    }
}
