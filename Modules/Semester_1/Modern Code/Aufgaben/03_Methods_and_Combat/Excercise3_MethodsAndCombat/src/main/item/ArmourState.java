package main.item;

public enum ArmourState
{
    PRISTINE(0.6),
    SCRATCHED(1.0),
    WORN(1.2),
    DAMAGED(1.5),
    BROKEN(3);

    public final double wearMultiplier;

    ArmourState(double wearMultiplier)
    {
        this.wearMultiplier = wearMultiplier;
    }
}
