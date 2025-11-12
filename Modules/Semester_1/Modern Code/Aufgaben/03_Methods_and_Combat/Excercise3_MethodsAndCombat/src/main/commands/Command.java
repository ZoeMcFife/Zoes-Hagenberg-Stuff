package main.commands;

import main.character.Character;

public abstract class Command
{
    public abstract void execute(Character character);
}
