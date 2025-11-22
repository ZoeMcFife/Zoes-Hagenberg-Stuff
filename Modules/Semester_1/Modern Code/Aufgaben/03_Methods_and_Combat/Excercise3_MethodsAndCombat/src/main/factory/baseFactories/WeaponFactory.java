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
        registerWeapon(25, "Coalspike Cleaver", 3.6, 450, 46, false, ItemRarity.MEDIUM);
        registerWeapon(26, "Canyon Cleaver", 5.1, 925, 78, false, ItemRarity.LEGENDARY);
        registerWeapon(27, "Runic Pickaxe", 6.9, 601, 21, false, ItemRarity.LOW);
        registerWeapon(28, "Gutterboard Saw", 1.7, 501, 39, false, ItemRarity.HIGH);
        registerWeapon(29, "Feyspinner Bow", 1.0, 521, 12, false, ItemRarity.LOW);
        registerWeapon(30, "Ironclaw Pistol", 5.0, 1309, 44, false, ItemRarity.MEDIUM);
        registerWeapon(31, "Smuggler's Shiv", 5.3, 519, 28, true, ItemRarity.MEDIUM);
        registerWeapon(32, "Tunnelcarver Axe", 8.0, 468, 20, true, ItemRarity.MEDIUM);
        registerWeapon(33, "Aether Chalice", 1.3, 530, 73, true, ItemRarity.LEGENDARY);
        registerWeapon(34, "Brine Harpoon", 6.9, 1164, 45, false, ItemRarity.HIGH);
        registerWeapon(35, "Forgebrand Axe", 8.4, 1142, 20, false, ItemRarity.MEDIUM);
        registerWeapon(36, "Echo Lance", 3.2, 526, 70, false, ItemRarity.LEGENDARY);
        registerWeapon(37, "Nightfall Shiv", 6.6, 582, 22, false, ItemRarity.MEDIUM);
        registerWeapon(38, "Bonesnap Mace", 3.3, 674, 15, false, ItemRarity.LOW);
        registerWeapon(39, "Heatsear Blunderbuss", 4.3, 332, 10, false, ItemRarity.LOW);
        registerWeapon(40, "Riftsplitter", 3.8, 974, 19, false, ItemRarity.MEDIUM);
        registerWeapon(41, "Stormcoil Pistol", 7.3, 1187, 86, false, ItemRarity.LEGENDARY);
        registerWeapon(42, "Runecharger Baton", 6.6, 543, 40, false, ItemRarity.HIGH);
        registerWeapon(43, "Wolfram Cleaver", 9.1, 168, 10, false, ItemRarity.LOW);
        registerWeapon(44, "Skymeld Sabre", 8.6, 1203, 70, false, ItemRarity.LEGENDARY);
        registerWeapon(45, "Echo-string Crossbow", 1.6, 131, 26, true, ItemRarity.MEDIUM);
        registerWeapon(46, "Shoreline Glaive", 2.8, 745, 33, false, ItemRarity.HIGH);
        registerWeapon(47, "Abyss Needle", 8.1, 425, 13, false, ItemRarity.LOW);
        registerWeapon(48, "Runewire Whip", 8.6, 1348, 83, true, ItemRarity.LEGENDARY);
        registerWeapon(49, "Smokestack Rifle", 6.1, 270, 17, false, ItemRarity.MEDIUM);
        registerWeapon(50, "Gloomcaller Flute", 2.3, 441, 47, true, ItemRarity.HIGH);
        registerWeapon(51, "Spore Spitter", 4.7, 202, 10, false, ItemRarity.LOW);
        registerWeapon(52, "Tidebreaker Maul", 4.5, 442, 24, false, ItemRarity.MEDIUM);
        registerWeapon(53, "Sirenbone Harp", 8.4, 1246, 81, true, ItemRarity.LEGENDARY);
        registerWeapon(54, "Cinderbrand Rapier", 6.8, 916, 40, false, ItemRarity.HIGH);
        registerWeapon(55, "Coalforge Pike", 9.1, 1189, 102, false, ItemRarity.LEGENDARY);
        registerWeapon(56, "Hollowpoint Cutter", 2.5, 412, 17, false, ItemRarity.MEDIUM);
        registerWeapon(57, "Gravhook Harpoon", 5.5, 678, 44, false, ItemRarity.HIGH);
        registerWeapon(58, "Nebula Handcannon", 1.5, 1148, 65, false, ItemRarity.LEGENDARY);
        registerWeapon(59, "Shard-edged Kukri", 2.6, 931, 37, false, ItemRarity.HIGH);
        registerWeapon(60, "Tinker Grenade", 1.1, 95, 15, false, ItemRarity.LOW);
        registerWeapon(61, "Phantom Dirk", 6.4, 688, 24, true, ItemRarity.MEDIUM);
        registerWeapon(62, "Ironjaw Club", 7.7, 1208, 60, false, ItemRarity.LEGENDARY);
        registerWeapon(63, "Riftflare Wand", 5.1, 552, 31, true, ItemRarity.HIGH);
        registerWeapon(64, "Voidspike Rifle", 1.6, 1206, 71, false, ItemRarity.LEGENDARY);
        registerWeapon(65, "Shardshot Revolver", 7.0, 260, 23, false, ItemRarity.MEDIUM);
        registerWeapon(66, "Runic Morningstar", 5.0, 968, 49, true, ItemRarity.HIGH);
        registerWeapon(67, "Seamstalker Spear", 3.4, 371, 43, false, ItemRarity.HIGH);
        registerWeapon(68, "Blinkblade", 2.8, 1215, 63, true, ItemRarity.LEGENDARY);
        registerWeapon(69, "Stitcher's Needle", 8.1, 733, 34, false, ItemRarity.HIGH);
        registerWeapon(70, "Golem Lash", 9.0, 554, 28, false, ItemRarity.MEDIUM);
        registerWeapon(71, "Stormcord Staff", 6.2, 777, 44, true, ItemRarity.HIGH);
        registerWeapon(72, "Rune-sap Mace", 4.6, 424, 40, true, ItemRarity.HIGH);
        registerWeapon(73, "Abyssal Hook", 6.8, 256, 20, false, ItemRarity.MEDIUM);
        registerWeapon(74, "Pilot's Saber", 6.4, 176, 22, false, ItemRarity.LOW);
        registerWeapon(75, "Echo-tongue Dagger", 3.6, 1116, 68, true, ItemRarity.LEGENDARY);
        registerWeapon(76, "Netherbrand Carbine", 4.7, 744, 25, false, ItemRarity.MEDIUM);
        registerWeapon(77, "Starfall Cleaver", 4.2, 1037, 45, false, ItemRarity.HIGH);
        registerWeapon(78, "Warden's Flail", 5.6, 220, 21, false, ItemRarity.MEDIUM);
        registerWeapon(79, "Forgehammer", 6.5, 344, 15, false, ItemRarity.LOW);
        registerWeapon(80, "Coalbrand Javelin", 9.4, 706, 32, false, ItemRarity.MEDIUM);
        registerWeapon(81, "Sparkshell Rifle", 4.8, 1075, 59, false, ItemRarity.LEGENDARY);
        registerWeapon(82, "Bonewhisper Staff", 3.0, 81, 19, true, ItemRarity.MEDIUM);
        registerWeapon(83, "Wolfram Pike", 3.3, 343, 21, false, ItemRarity.MEDIUM);
        registerWeapon(84, "Guttercoil SMG", 8.7, 220, 19, false, ItemRarity.LOW);
        registerWeapon(85, "Runeflux Arcgun", 9.7, 460, 26, false, ItemRarity.MEDIUM);
        registerWeapon(86, "Harbinger Pike", 1.5, 359, 28, false, ItemRarity.MEDIUM);
        registerWeapon(87, "Fargoth Cleaver", 4.8, 979, 46, false, ItemRarity.HIGH);
        registerWeapon(88, "Hollowblade Scimitar", 5.4, 1031, 18, false, ItemRarity.MEDIUM);
        registerWeapon(89, "Variant 65 Blade", 7.6, 1256, 7, false, ItemRarity.MEDIUM);
        registerWeapon(90, "Variant 66 Blade", 10.0, 1396, 13, false, ItemRarity.LOW);
        registerWeapon(91, "Variant 67 Blade", 5.1, 1354, 44, false, ItemRarity.MEDIUM);
        registerWeapon(92, "Variant 68 Blade", 10.0, 912, 93, false, ItemRarity.LEGENDARY);
        registerWeapon(93, "Variant 69 Blade", 4.0, 702, 45, false, ItemRarity.LEGENDARY);
        registerWeapon(94, "Variant 70 Blade", 4.6, 1034, 25, false, ItemRarity.HIGH);
        registerWeapon(95, "Variant 71 Blade", 5.0, 664, 32, false, ItemRarity.MEDIUM);
        registerWeapon(96, "Variant 72 Blade", 6.9, 1131, 18, false, ItemRarity.LOW);
        registerWeapon(97, "Variant 73 Blade", 6.5, 1040, 26, false, ItemRarity.HIGH);
        registerWeapon(98, "Variant 74 Blade", 3.3, 288, 21, false, ItemRarity.MEDIUM);
        registerWeapon(99, "Variant 75 Blade", 1.9, 1310, 42, false, ItemRarity.HIGH);
        registerWeapon(100, "Variant 76 Blade", 5.9, 1151, 50, false, ItemRarity.LEGENDARY);

    }

    /**
     * Creates the base weapon (Fists).
     *
     * @return A new instance of the base weapon
     */
    public static Weapon createBaseWeapon()
    {
        return createWeaponByName("Fists");
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
