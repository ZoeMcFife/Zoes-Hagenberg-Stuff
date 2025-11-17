package main.combat;

import main.character.Enemy;
import main.character.GameCharacter;
import main.character.Player;

import java.util.ArrayList;
import java.util.List;

public class Battle
{
    private List<Enemy> enemies = new ArrayList<>();
    private List<GameCharacter> participantsOrderedByDexterity = new ArrayList<>();

    public Battle(List<Enemy> enemies, Player player)
    {
        setEnemies(enemies);
        setParticipantsOrderedByDexterity(enemies, player);
    }

    private void setParticipantsOrderedByDexterity(List<Enemy> enemies, Player player)
    {
        participantsOrderedByDexterity.addAll(enemies);
        participantsOrderedByDexterity.add(player);

        participantsOrderedByDexterity.sort((a, b) -> b.getDexterity() - a.getDexterity());
    }

    public List<GameCharacter> getParticipantsOrderedByDexterity()
    {
        return participantsOrderedByDexterity;
    }

    public void setEnemies(List<Enemy> enemies)
    {
        this.enemies = enemies;
    }

    public List<Enemy> getEnemies()
    {
        return enemies;
    }

}
