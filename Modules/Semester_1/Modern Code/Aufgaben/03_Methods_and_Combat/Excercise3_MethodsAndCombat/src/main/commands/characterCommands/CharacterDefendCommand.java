package main.commands.characterCommands;

import main.character.GameCharacter;
import main.commands.Command;

public class CharacterDefendCommand extends Command
{

    @Override
    public void execute(GameCharacter gameCharacter)
    {
        gameCharacter.defend();
    }
}
