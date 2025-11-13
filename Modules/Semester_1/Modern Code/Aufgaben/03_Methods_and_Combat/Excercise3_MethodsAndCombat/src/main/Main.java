package main;

import main.ui.CharacterCreator;
import main.ui.UserInterfaceHelper;

public class Main
{
    public static void main(String[] args)
    {
        UserInterfaceHelper ui = new UserInterfaceHelper();
        ui.displayLogo();

        CharacterCreator characterCreator = new CharacterCreator();
        characterCreator.startCharacterCreation();
    }
}