package main.factory.baseFactories;

import main.item.ItemRarity;
import main.item.Weapon;

import java.util.*;

/**
 * Factory class for creating weapon items.
 * Provides methods to create weapons by ID, name, or randomly.
 * Contains 24 predefined weapons with various rarities and properties.
 */
public class WeaponFactory
{
    private static final Map<Integer, WeaponData> WEAPONS_BY_ID = new HashMap<>();
    private static final Map<String, WeaponData> WEAPONS_BY_NAME = new HashMap<>();
    private static final Map<ItemRarity, List<WeaponData>> WEAPONS_BY_RARITY = new EnumMap<>(ItemRarity.class);
    private static final List<WeaponData> ALL_WEAPONS = new ArrayList<>();
    private static final Random random = new Random();

    /**
     * Internal data structure for storing weapon information before instantiation.
     */
    private static class WeaponData
    {
        int id;
        String name;
        double weight;
        double value;
        double damage;
        boolean isMagic;
        ItemRarity rarity;

        WeaponData(int id, String name, double weight, double value, double damage, boolean isMagic, ItemRarity rarity)
        {
            this.id = id;
            this.name = name;
            this.weight = weight;
            this.value = value;
            this.damage = damage;
            this.isMagic = isMagic;
            this.rarity = rarity;
        }
    }

    static
    {
        // Weapons from the item list
        registerWeapon(1, "Gateway Cutlass", 3.0, 120, 18, false, ItemRarity.MEDIUM);
        registerWeapon(2, "Abyssic Longrifle", 7.5, 560, 46, false, ItemRarity.HIGH);
        registerWeapon(3, "Protectorate Saber", 2.8, 220, 26, false, ItemRarity.MEDIUM);
        registerWeapon(4, "Riftfire Pistol", 2.0, 340, 30, true, ItemRarity.HIGH);
        registerWeapon(5, "Bonecut Halberd", 8.0, 160, 36, false, ItemRarity.MEDIUM);
        registerWeapon(6, "Echo Daggers (pair)", 1.0, 190, 14, true, ItemRarity.MEDIUM);
        registerWeapon(7, "Warpspike Lance", 9.0, 840, 54, false, ItemRarity.LEGENDARY);
        registerWeapon(8, "Arc Thrower (hand)", 3.2, 460, 34, false, ItemRarity.HIGH);
        registerWeapon(9, "Phasebreaker Blade", 2.5, 950, 40, true, ItemRarity.LEGENDARY);
        registerWeapon(10, "Starforged Rapier", 1.6, 210, 22, false, ItemRarity.MEDIUM);
        registerWeapon(11, "Seabreaker Trident", 6.5, 340, 32, false, ItemRarity.MEDIUM);
        registerWeapon(12, "Circuit Lacer", 2.0, 300, 20, false, ItemRarity.HIGH);
        registerWeapon(13, "Starcore Blade", 3.5, 1200, 62, true, ItemRarity.LEGENDARY);
        
        // Additional weapons from the extended list
        registerWeapon(14, "Riftfang Blade", 2.3, 310, 24, true, ItemRarity.MEDIUM);
        registerWeapon(15, "Wolfclan Hammer", 9.5, 450, 38, false, ItemRarity.HIGH);
        registerWeapon(16, "Protectorate Arc Pike", 8.0, 780, 52, true, ItemRarity.LEGENDARY);
        registerWeapon(17, "Scrapgun Mk II", 6.4, 190, 30, false, ItemRarity.LOW);
        registerWeapon(18, "Smuggler's Repeater", 3.0, 260, 28, false, ItemRarity.MEDIUM);
        registerWeapon(19, "Runeforge Mace", 5.2, 420, 34, true, ItemRarity.HIGH);
        registerWeapon(20, "Bonechime Dagger", 1.1, 125, 16, true, ItemRarity.MEDIUM);
        registerWeapon(21, "Fargoth Execution Blade", 11.0, 980, 60, false, ItemRarity.LEGENDARY);
        registerWeapon(22, "Echo Reaver Staff", 4.8, 360, 20, true, ItemRarity.HIGH);
        registerWeapon(23, "Stormcall Crossbow", 7.0, 400, 36, false, ItemRarity.HIGH);
        registerWeapon(24, "Fists", 0.0, 0, 5, false, ItemRarity.LOW);
    }

    private static void registerWeapon(int id, String name, double weight, double value, double damage, boolean isMagic, ItemRarity rarity)
    {
        WeaponData data = new WeaponData(id, name, weight, value, damage, isMagic, rarity);
        WEAPONS_BY_ID.put(id, data);
        WEAPONS_BY_NAME.put(name.toLowerCase(), data);
        WEAPONS_BY_RARITY.computeIfAbsent(rarity, k -> new ArrayList<>()).add(data);
        ALL_WEAPONS.add(data);
    }

    /**
     * Creates a weapon by its unique ID.
     * 
     * @param id The weapon ID (1-24)
     * @return A new weapon instance
     * @throws IllegalArgumentException if no weapon exists with the given ID
     */
    public static Weapon createWeaponById(int id)
    {
        WeaponData data = WEAPONS_BY_ID.get(id);
        if (data == null)
        {
            throw new IllegalArgumentException("Weapon with ID " + id + " not found");
        }
        return createWeaponFromData(data);
    }

    /**
     * Creates a weapon by its name (case-insensitive).
     * 
     * @param name The weapon name
     * @return A new weapon instance
     * @throws IllegalArgumentException if no weapon exists with the given name
     */
    public static Weapon createWeaponByName(String name)
    {
        WeaponData data = WEAPONS_BY_NAME.get(name.toLowerCase());
        if (data == null)
        {
            throw new IllegalArgumentException("Weapon with name '" + name + "' not found");
        }
        return createWeaponFromData(data);
    }

    /**
     * Creates a random weapon from all available weapons.
     * 
     * @return A new random weapon instance
     * @throws IllegalStateException if no weapons are registered
     */
    public static Weapon createRandomWeapon()
    {
        if (ALL_WEAPONS.isEmpty())
        {
            throw new IllegalStateException("No weapons registered");
        }
        WeaponData data = ALL_WEAPONS.get(random.nextInt(ALL_WEAPONS.size()));
        return createWeaponFromData(data);
    }

    /**
     * Creates a random weapon of the specified rarity.
     * 
     * @param rarity The desired weapon rarity
     * @return A new random weapon of the specified rarity
     * @throws IllegalArgumentException if no weapons exist for the given rarity
     */
    public static Weapon createRandomWeaponByRarity(ItemRarity rarity)
    {
        List<WeaponData> weapons = WEAPONS_BY_RARITY.get(rarity);
        if (weapons == null || weapons.isEmpty())
        {
            throw new IllegalArgumentException("No weapons found for rarity: " + rarity);
        }
        WeaponData data = weapons.get(random.nextInt(weapons.size()));
        return createWeaponFromData(data);
    }

    private static Weapon createWeaponFromData(WeaponData data)
    {
        return new Weapon(data.name, data.weight, data.value, data.damage, data.isMagic, data.rarity);
    }

    /**
     * Gets a list of all weapon names.
     * 
     * @return List of all registered weapon names
     */
    public static List<String> getAllWeaponNames()
    {
        return ALL_WEAPONS.stream().map(w -> w.name).toList();
    }

    /**
     * Gets the total number of registered weapons.
     * 
     * @return The weapon count (always 24)
     */
    public static int getWeaponCount()
    {
        return ALL_WEAPONS.size();
    }
}
