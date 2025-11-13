package main.commands.CharacterCommands;

import main.character.GameCharacter;
import main.commands.Command;

public class CharacterAttackCommand extends Command
{
    public GameCharacter target;

    @Override
    public void execute(GameCharacter gameCharacter)
    {
        gameCharacter.attack(target);
    }
}
