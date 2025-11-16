package main.ui.components;

import main.character.DangerLevel;
import main.character.Enemy;
import main.character.GameCharacter;
import main.combat.Battle;
import main.factory.generators.BattleGenerator;
import main.global.GameManager;
import main.ui.UserInterface;
import main.ui.UserInterfaceHelper;

import java.util.ArrayList;
import java.util.List;

public class BattleUI extends UserInterface
{
    private DangerLevel dangerLevel;
    private int battleNumber;

    public BattleUI(DangerLevel dangerLevel, int battleNumber)
    {
        this.dangerLevel = dangerLevel;
        this.battleNumber = battleNumber;
    }

    @Override
    public void startUI()
    {
        Battle battle = BattleGenerator.generateBattle(dangerLevel);

        displayBattleStartMessage();

        while (GameManager.getPlayer().isAlive())
        {
            TurnUI turnUI = new TurnUI(battle);
            turnUI.startUI();

            UserInterfaceHelper.waitForEnterKey();
        }

    }

    public void displayBattleStartMessage()
    {
        IO.println("  ____        _   _   _         _____ _             _   \n" +
                " |  _ \\      | | | | | |       / ____| |           | |  \n" +
                " | |_) | __ _| |_| |_| | ___  | (___ | |_ __ _ _ __| |_ \n" +
                " |  _ < / _` | __| __| |/ _ \\  \\___ \\| __/ _` | '__| __|\n" +
                " | |_) | (_| | |_| |_| |  __/  ____) | || (_| | |  | |_ \n" +
                " |____/ \\__,_|\\__|\\__|_|\\___| |_____/ \\__\\__,_|_|   \\__|\n" +
                "                                                        \n" +
                "                                                        ");
        IO.println("Battle " + battleNumber + " begins!");
        IO.println("Danger Level: " + dangerLevel);
    }

}
