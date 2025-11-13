package main.commands;

import main.character.GameCharacter;

public abstract class Command
{
    public abstract void execute(GameCharacter gameCharacter);
}
