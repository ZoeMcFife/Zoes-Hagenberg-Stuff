package main.combat;

import main.character.Enemy;

import java.util.ArrayList;
import java.util.List;

public class Battle
{
    private List<Enemy> enemies = new ArrayList<>();


    public void setEnemies(List<Enemy> enemies)
    {
        this.enemies = enemies;
    }

    public List<Enemy> getEnemies()
    {
        return enemies;
    }

}
