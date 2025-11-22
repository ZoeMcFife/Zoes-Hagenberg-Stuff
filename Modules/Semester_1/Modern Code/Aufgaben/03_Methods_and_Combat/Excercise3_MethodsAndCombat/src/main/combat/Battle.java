package main.combat;

import main.character.Enemy;
import main.character.GameCharacter;
import main.character.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a battle between the player and one or more enemies.
 * Handles participant ordering based on dexterity and tracks combat state.
 */
public class Battle
{
    private List<Enemy> enemies = new ArrayList<>();
    private List<GameCharacter> participantsOrderedByDexterity = new ArrayList<>();

    /**
     * Creates a new battle with the given enemies and player.
     * Automatically orders participants by dexterity for turn order.
     * 
     * @param enemies List of enemies in the battle
     * @param player The player character
     */
    public Battle(List<Enemy> enemies, Player player)
    {
        setEnemies(enemies);
        setParticipantsOrderedByDexterity(enemies, player);
    }

    /**
     * Sets up the participant order for combat turns.
     * Participants are sorted by dexterity in descending order (highest goes first).
     * 
     * @param enemies List of enemies in the battle
     * @param player The player character
     */
    private void setParticipantsOrderedByDexterity(List<Enemy> enemies, Player player)
    {
        participantsOrderedByDexterity.addAll(enemies);
        participantsOrderedByDexterity.add(player);

        participantsOrderedByDexterity.sort((a, b) -> b.getDexterity() - a.getDexterity());
    }

    /**
     * Gets the list of all battle participants ordered by dexterity.
     * 
     * @return List of characters in turn order (highest dexterity first)
     */
    public List<GameCharacter> getParticipantsOrderedByDexterity()
    {
        return participantsOrderedByDexterity;
    }

    /**
     * Sets the enemies participating in the battle.
     * 
     * @param enemies List of enemy characters
     */
    public void setEnemies(List<Enemy> enemies)
    {
        this.enemies = enemies;
    }

    /**
     * Gets the list of enemies in the battle.
     * 
     * @return List of enemy characters
     */
    public List<Enemy> getEnemies()
    {
        return enemies;
    }

    /**
     * Checks if the battle is over (all enemies defeated).
     *
     * @return true if all enemies are defeated, false otherwise
     */
    public boolean isBattleOver()
    {
        return enemies.stream().noneMatch(GameCharacter::isAlive);
    }

}
