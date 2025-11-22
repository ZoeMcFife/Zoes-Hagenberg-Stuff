package main.item;

public enum ArmourState
{
    PRISTINE(0.3),
    SCRATCHED(1.0),
    WORN(1.3),
    DAMAGED(1.7),
    BROKEN(3);

    public final double wearMultiplier;

    ArmourState(double wearMultiplier)
    {
        this.wearMultiplier = wearMultiplier;
    }
}
