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
        displayBattle(battle);

        TurnUI turnUI = new TurnUI(battle);
        turnUI.startUI();

        UserInterfaceHelper.waitForEnterKey();
    }

    private void displayBattle(Battle battle)
    {
        List<List<String>> enemyBoxes = new ArrayList<>();

        for (Enemy enemy : battle.getEnemies())
        {
            enemyBoxes.add(enemy.getDisplayBox());
        }

        List<String> finalOutput = GameCharacter.combineEnemyBoxes(enemyBoxes);

        for (String line : finalOutput)
        {
            IO.println(line);
        }

    }
}
