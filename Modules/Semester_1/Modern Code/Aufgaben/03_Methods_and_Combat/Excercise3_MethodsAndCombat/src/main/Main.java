package main;

import main.global.GameManager;
import main.ui.components.CharacterCreator;
import main.ui.UserInterfaceHelper;

public class Main
{
    public static void main(String[] args)
    {
        GameManager gameManager = new GameManager();

        UserInterfaceHelper.displayLogo();

        CharacterCreator characterCreator = new CharacterCreator();
        characterCreator.startCharacterCreation();

        gameManager.player = characterCreator.getPlayerCharacter();
    }
}