// java
public class Main {
    public static void main(String[] args) {
        // integer variables
        int playerHealth;
        int maxHealth = 100;
        playerHealth = maxHealth;

        // damage and multiplier
        double damage = 10.0;
        double criticalMultiplier = 1.5;

        // booleans and class
        boolean isAlive;
        boolean hasWeapon = false;
        char playerClass = 'W';

        // print initial state
        System.out.println("Initial state:");
        System.out.println("playerHealth: " + playerHealth);
        System.out.println("maxHealth: " + maxHealth);
        System.out.println("damage: " + damage);
        System.out.println("criticalMultiplier: " + criticalMultiplier);
        System.out.println("hasWeapon: " + hasWeapon);
        System.out.println("playerClass: " + playerClass);

        // player finds a weapon
        System.out.println("\nPlayer finds a weapon!");
        hasWeapon = true;
        damage += 5.0; // weapon increases base damage
        System.out.println("hasWeapon: " + hasWeapon);
        System.out.println("damage after equipping weapon: " + damage);

        // player levels up
        System.out.println("\nPlayer levels up!");
        int level = 1;
        level++; // gained a level
        maxHealth += 20;
        // restore some health on level up, but not above max
        playerHealth = Math.min(playerHealth + 20, maxHealth);
        damage += 3.0; // level-up increases damage
        criticalMultiplier += 0.1;
        System.out.println("level: " + level);
        System.out.println("maxHealth: " + maxHealth);
        System.out.println("playerHealth: " + playerHealth);
        System.out.println("damage: " + damage);
        System.out.println("criticalMultiplier: " + criticalMultiplier);

        // compute a sample critical hit and check alive
        double criticalDamage = damage * criticalMultiplier;
        System.out.println("\nsample criticalDamage: " + criticalDamage);

        isAlive = playerHealth > 0;
        System.out.println("isAlive: " + isAlive);
    }
}
