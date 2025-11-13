package test;

import main.character.DangerLevel;
import main.character.Enemy;
import main.factory.*;
import main.item.*;

public class FactoryDemo
{
    public static void main(String[] args)
    {
        System.out.println("=== Factory Demonstration ===\n");

        // Test EnemyFactory
        System.out.println("--- Enemy Factory ---");
        System.out.println("Total enemies: " + EnemyFactory.getEnemyCount());
        
        Enemy enemy1 = EnemyFactory.createEnemyById(1);
        System.out.println("Enemy by ID 1: " + enemy1.getName() + " (HP: " + enemy1.getMaxHealth() + ")");
        
        Enemy enemy2 = EnemyFactory.createEnemyByName("Rift Goliath");
        System.out.println("Enemy by name 'Rift Goliath': " + enemy2.getName() + " (HP: " + enemy2.getMaxHealth() + ")");
        
        Enemy randomEnemy = EnemyFactory.createRandomEnemy();
        System.out.println("Random enemy: " + randomEnemy.getName());
        
        Enemy deathEnemy = EnemyFactory.createRandomEnemyByDangerLevel(DangerLevel.DEATH);
        System.out.println("Random DEATH level enemy: " + deathEnemy.getName() + " (HP: " + deathEnemy.getMaxHealth() + ")");

        // Test WeaponFactory
        System.out.println("\n--- Weapon Factory ---");
        System.out.println("Total weapons: " + WeaponFactory.getWeaponCount());
        
        Weapon weapon1 = WeaponFactory.createWeaponById(1);
        System.out.println("Weapon by ID 1: " + weapon1.getName() + " (Damage: " + weapon1.getDamage() + ", Rarity: " + weapon1.getRarity() + ")");
        
        Weapon weapon2 = WeaponFactory.createWeaponByName("Starcore Blade");
        System.out.println("Weapon by name 'Starcore Blade': " + weapon2.getName() + " (Damage: " + weapon2.getDamage() + ", Magic: " + weapon2.isMagic() + ")");
        
        Weapon randomWeapon = WeaponFactory.createRandomWeapon();
        System.out.println("Random weapon: " + randomWeapon.getName());
        
        Weapon legendaryWeapon = WeaponFactory.createRandomWeaponByRarity(ItemRarity.LEGENDARY);
        System.out.println("Random LEGENDARY weapon: " + legendaryWeapon.getName() + " (Damage: " + legendaryWeapon.getDamage() + ")");

        // Test ShieldFactory
        System.out.println("\n--- Shield Factory ---");
        System.out.println("Total shields: " + ShieldFactory.getShieldCount());
        
        Shield shield1 = ShieldFactory.createShieldById(1);
        System.out.println("Shield by ID 1: " + shield1.getName() + " (Defense: " + shield1.getDefense() + ", Rarity: " + shield1.getRarity() + ")");
        
        Shield shield2 = ShieldFactory.createShieldByName("Shield of Wolfram");
        System.out.println("Shield by name 'Shield of Wolfram': " + shield2.getName() + " (Defense: " + shield2.getDefense() + ")");
        
        Shield randomShield = ShieldFactory.createRandomShield();
        System.out.println("Random shield: " + randomShield.getName());

        // Test ArmourFactory
        System.out.println("\n--- Armour Factory ---");
        System.out.println("Total armours: " + ArmourFactory.getArmourCount());
        
        Armour armour1 = ArmourFactory.createArmourById(1);
        System.out.println("Armour by ID 1: " + armour1.getName() + " (Defense: " + armour1.getDefense() + ", Rarity: " + armour1.getRarity() + ")");
        
        Armour armour2 = ArmourFactory.createArmourByName("Skymetal Plate");
        System.out.println("Armour by name 'Skymetal Plate': " + armour2.getName() + " (Defense: " + armour2.getDefense() + ")");
        
        Armour randomArmour = ArmourFactory.createRandomArmour();
        System.out.println("Random armour: " + randomArmour.getName());

        // Test HealingPotionFactory
        System.out.println("\n--- Healing Potion Factory ---");
        System.out.println("Total healing potions: " + HealingPotionFactory.getPotionCount());
        
        HealingPotion potion1 = HealingPotionFactory.createPotionById(1);
        System.out.println("Potion by ID 1: " + potion1.getName() + " (Healing: " + potion1.getHealingAmount() + ", Rarity: " + potion1.getRarity() + ")");
        
        HealingPotion potion2 = HealingPotionFactory.createPotionByName("Emergency Tonic");
        System.out.println("Potion by name 'Emergency Tonic': " + potion2.getName() + " (Healing: " + potion2.getHealingAmount() + ")");
        
        HealingPotion randomPotion = HealingPotionFactory.createRandomPotion();
        System.out.println("Random potion: " + randomPotion.getName());

        System.out.println("\n=== All Factories Working! ===");
        System.out.println("Summary:");
        System.out.println("  - " + EnemyFactory.getEnemyCount() + " enemies");
        System.out.println("  - " + WeaponFactory.getWeaponCount() + " weapons");
        System.out.println("  - " + ShieldFactory.getShieldCount() + " shields");
        System.out.println("  - " + ArmourFactory.getArmourCount() + " armours");
        System.out.println("  - " + HealingPotionFactory.getPotionCount() + " healing potions");
    }
}
