package Exercises;

import UserInterface.UI;

public class TowerOfHanoi
{
    private int disks;

    public TowerOfHanoi(int disks)
    {
        setDisks(disks);

        UI.printlnBlue("Solving Tower of Hanoi with " + getDisks() + " disks:");
        solve(getDisks(), 'A', 'C', 'B');
    }

    private void solve(int n, char fromPeg, char toPeg, char auxPeg)
    {
        if (n == 0)
        {
            return;
        }

        solve(n - 1, fromPeg, auxPeg, toPeg);

        UI.printlnGreen("Move disk " + n + " from peg " + fromPeg + " to peg " + toPeg);

        solve(n - 1, auxPeg, toPeg, fromPeg);
    }


    public int getDisks()
    {
        return disks;
    }

    private void setDisks(int disks)
    {
        this.disks = disks;
    }
}
