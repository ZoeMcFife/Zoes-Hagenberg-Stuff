package main.ui.components;

import main.character.DangerLevel;
import main.character.Enemy;
import main.character.GameCharacter;
import main.combat.Battle;
import main.factory.generators.BattleGenerator;
import main.ui.UserInterface;
import main.ui.UserInterfaceHelper;

import java.util.ArrayList;
import java.util.List;

public class BattleUI extends UserInterface
{
    private DangerLevel dangerLevel;

    public BattleUI(DangerLevel dangerLevel)
    {
        this.dangerLevel = dangerLevel;
    }

    @Override
    public void startUI()
    {
        Battle battle = BattleGenerator.generateBattle(dangerLevel);

        while (true)
        {
            TurnUI turnUI = new TurnUI(battle);
            turnUI.startUI();

            UserInterfaceHelper.waitForEnterKey();
        }

    }


}
