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
        // Weapons rebalanced by rarity
        // LOW: damage 5-40, weight 1-3 (small) or 3-10 (big), value 50-200
        // MEDIUM: damage 40-60, weight similar, value 200-500
        // HIGH: damage 60-80, weight similar, value 500-1000
        // LEGENDARY: damage 80-120, weight similar, value 1000+
        registerWeapon(1, "Gateway Cutlass", 2.5, 280, 45, false, ItemRarity.MEDIUM);
        registerWeapon(2, "Abyssic Longrifle", 8.0, 750, 72, false, ItemRarity.HIGH);
        registerWeapon(3, "Protectorate Saber", 2.0, 320, 48, false, ItemRarity.MEDIUM);
        registerWeapon(4, "Riftfire Pistol", 2.2, 680, 68, true, ItemRarity.HIGH);
        registerWeapon(5, "Bonecut Halberd", 7.5, 380, 55, false, ItemRarity.MEDIUM);
        registerWeapon(6, "Echo Daggers (pair)", 1.5, 350, 52, true, ItemRarity.MEDIUM);
        registerWeapon(7, "Warpspike Lance", 9.5, 1450, 98, false, ItemRarity.LEGENDARY);
        registerWeapon(8, "Arc Thrower (hand)", 3.0, 820, 74, false, ItemRarity.HIGH);
        registerWeapon(9, "Phasebreaker Blade", 2.8, 1350, 92, true, ItemRarity.LEGENDARY);
        registerWeapon(10, "Starforged Rapier", 1.8, 295, 44, false, ItemRarity.MEDIUM);
        registerWeapon(11, "Seabreaker Trident", 6.0, 410, 58, false, ItemRarity.MEDIUM);
        registerWeapon(12, "Circuit Lacer", 2.5, 550, 62, false, ItemRarity.HIGH);
        registerWeapon(13, "Starcore Blade", 3.2, 1620, 105, true, ItemRarity.LEGENDARY);
        registerWeapon(14, "Riftfang Blade", 2.0, 380, 50, true, ItemRarity.MEDIUM);
        registerWeapon(15, "Wolfclan Hammer", 9.0, 780, 76, false, ItemRarity.HIGH);
        registerWeapon(16, "Protectorate Arc Pike", 8.5, 1280, 88, true, ItemRarity.LEGENDARY);
        registerWeapon(17, "Scrapgun Mk II", 6.0, 150, 32, false, ItemRarity.LOW);
        registerWeapon(18, "Smuggler's Repeater", 3.0, 340, 46, false, ItemRarity.MEDIUM);
        registerWeapon(19, "Runeforge Mace", 5.5, 690, 70, true, ItemRarity.HIGH);
        registerWeapon(20, "Bonechime Dagger", 1.2, 280, 42, true, ItemRarity.MEDIUM);
        registerWeapon(21, "Fargoth Execution Blade", 10.0, 1580, 112, false, ItemRarity.LEGENDARY);
        registerWeapon(22, "Echo Reaver Staff", 4.5, 620, 66, true, ItemRarity.HIGH);
        registerWeapon(23, "Stormcall Crossbow", 7.5, 740, 72, false, ItemRarity.HIGH);
        registerWeapon(24, "Fists", 0.0, 0, 5, false, ItemRarity.LOW);
        registerWeapon(25, "Coalspike Cleaver", 4.0, 380, 54, false, ItemRarity.MEDIUM);
        registerWeapon(26, "Canyon Cleaver", 7.5, 1420, 96, false, ItemRarity.LEGENDARY);
        registerWeapon(27, "Runic Pickaxe", 5.5, 125, 22, false, ItemRarity.LOW);
        registerWeapon(28, "Gutterboard Saw", 2.5, 720, 68, false, ItemRarity.HIGH);
        registerWeapon(29, "Feyspinner Bow", 2.0, 95, 18, false, ItemRarity.LOW);
        registerWeapon(30, "Ironclaw Pistol", 3.5, 420, 56, false, ItemRarity.MEDIUM);
        registerWeapon(31, "Smuggler's Shiv", 1.8, 310, 48, true, ItemRarity.MEDIUM);
        registerWeapon(32, "Tunnelcarver Axe", 6.0, 360, 52, true, ItemRarity.MEDIUM);
        registerWeapon(33, "Aether Chalice", 1.5, 1290, 90, true, ItemRarity.LEGENDARY);
        registerWeapon(34, "Brine Harpoon", 7.0, 780, 74, false, ItemRarity.HIGH);
        registerWeapon(35, "Forgebrand Axe", 8.0, 340, 50, false, ItemRarity.MEDIUM);
        registerWeapon(36, "Echo Lance", 4.5, 1380, 94, false, ItemRarity.LEGENDARY);
        registerWeapon(37, "Nightfall Shiv", 1.5, 290, 44, false, ItemRarity.MEDIUM);
        registerWeapon(38, "Bonesnap Mace", 3.5, 110, 20, false, ItemRarity.LOW);
        registerWeapon(39, "Heatsear Blunderbuss", 5.0, 140, 26, false, ItemRarity.LOW);
        registerWeapon(40, "Riftsplitter", 3.0, 400, 58, false, ItemRarity.MEDIUM);
        registerWeapon(41, "Stormcoil Pistol", 2.8, 1540, 108, false, ItemRarity.LEGENDARY);
        registerWeapon(42, "Runecharger Baton", 4.5, 680, 70, false, ItemRarity.HIGH);
        registerWeapon(43, "Wolfram Cleaver", 9.0, 90, 15, false, ItemRarity.LOW);
        registerWeapon(44, "Skymeld Sabre", 3.5, 1620, 110, false, ItemRarity.LEGENDARY);
        registerWeapon(45, "Echo-string Crossbow", 2.5, 330, 48, true, ItemRarity.MEDIUM);
        registerWeapon(46, "Shoreline Glaive", 5.5, 740, 72, false, ItemRarity.HIGH);
        registerWeapon(47, "Abyss Needle", 2.0, 120, 24, false, ItemRarity.LOW);
        registerWeapon(48, "Runewire Whip", 3.0, 1480, 102, true, ItemRarity.LEGENDARY);
        registerWeapon(49, "Smokestack Rifle", 6.5, 380, 54, false, ItemRarity.MEDIUM);
        registerWeapon(50, "Gloomcaller Flute", 2.5, 760, 74, true, ItemRarity.HIGH);
        registerWeapon(51, "Spore Spitter", 3.0, 85, 12, false, ItemRarity.LOW);
        registerWeapon(52, "Tidebreaker Maul", 7.0, 420, 56, false, ItemRarity.MEDIUM);
        registerWeapon(53, "Sirenbone Harp", 2.5, 1390, 98, true, ItemRarity.LEGENDARY);
        registerWeapon(54, "Cinderbrand Rapier", 2.0, 820, 76, false, ItemRarity.HIGH);
        registerWeapon(55, "Coalforge Pike", 9.5, 1560, 115, false, ItemRarity.LEGENDARY);
        registerWeapon(56, "Hollowpoint Cutter", 2.5, 350, 52, false, ItemRarity.MEDIUM);
        registerWeapon(57, "Gravhook Harpoon", 6.5, 730, 72, false, ItemRarity.HIGH);
        registerWeapon(58, "Nebula Handcannon", 3.0, 1430, 100, false, ItemRarity.LEGENDARY);
        registerWeapon(59, "Shard-edged Kukri", 2.0, 780, 74, false, ItemRarity.HIGH);
        registerWeapon(60, "Tinker Grenade", 1.5, 75, 16, false, ItemRarity.LOW);
        registerWeapon(61, "Phantom Dirk", 1.8, 380, 50, true, ItemRarity.MEDIUM);
        registerWeapon(62, "Ironjaw Club", 8.5, 1510, 106, false, ItemRarity.LEGENDARY);
        registerWeapon(63, "Riftflare Wand", 1.5, 690, 68, true, ItemRarity.HIGH);
        registerWeapon(64, "Voidspike Rifle", 7.5, 1580, 112, false, ItemRarity.LEGENDARY);
        registerWeapon(65, "Shardshot Revolver", 3.5, 340, 48, false, ItemRarity.MEDIUM);
        registerWeapon(66, "Runic Morningstar", 6.0, 850, 78, true, ItemRarity.HIGH);
        registerWeapon(67, "Seamstalker Spear", 5.0, 720, 72, false, ItemRarity.HIGH);
        registerWeapon(68, "Blinkblade", 2.2, 1490, 104, true, ItemRarity.LEGENDARY);
        registerWeapon(69, "Stitcher's Needle", 2.5, 760, 74, false, ItemRarity.HIGH);
        registerWeapon(70, "Golem Lash", 5.5, 410, 56, false, ItemRarity.MEDIUM);
        registerWeapon(71, "Stormcord Staff", 4.0, 790, 76, true, ItemRarity.HIGH);
        registerWeapon(72, "Rune-sap Mace", 5.0, 710, 70, true, ItemRarity.HIGH);
        registerWeapon(73, "Abyssal Hook", 4.5, 380, 54, false, ItemRarity.MEDIUM);
        registerWeapon(74, "Pilot's Saber", 2.5, 145, 28, false, ItemRarity.LOW);
        registerWeapon(75, "Echo-tongue Dagger", 1.8, 1520, 108, true, ItemRarity.LEGENDARY);
        registerWeapon(76, "Netherbrand Carbine", 5.5, 390, 54, false, ItemRarity.MEDIUM);
        registerWeapon(77, "Starfall Cleaver", 6.5, 790, 76, false, ItemRarity.HIGH);
        registerWeapon(78, "Warden's Flail", 5.0, 360, 52, false, ItemRarity.MEDIUM);
        registerWeapon(79, "Forgehammer", 8.0, 130, 25, false, ItemRarity.LOW);
        registerWeapon(80, "Coalbrand Javelin", 4.5, 380, 54, false, ItemRarity.MEDIUM);
        registerWeapon(81, "Sparkshell Rifle", 7.0, 1410, 100, false, ItemRarity.LEGENDARY);
        registerWeapon(82, "Bonewhisper Staff", 3.5, 310, 46, true, ItemRarity.MEDIUM);
        registerWeapon(83, "Wolfram Pike", 6.5, 400, 56, false, ItemRarity.MEDIUM);
        registerWeapon(84, "Guttercoil SMG", 4.5, 155, 30, false, ItemRarity.LOW);
        registerWeapon(85, "Runeflux Arcgun", 5.0, 420, 58, false, ItemRarity.MEDIUM);
        registerWeapon(86, "Harbinger Pike", 6.0, 390, 54, false, ItemRarity.MEDIUM);
        registerWeapon(87, "Fargoth Cleaver", 7.5, 840, 78, false, ItemRarity.HIGH);
        registerWeapon(88, "Hollowblade Scimitar", 3.5, 360, 52, false, ItemRarity.MEDIUM);
        registerWeapon(89, "Variant 65 Blade", 3.0, 410, 56, false, ItemRarity.MEDIUM);
        registerWeapon(90, "Variant 66 Blade", 4.5, 170, 32, false, ItemRarity.LOW);
        registerWeapon(91, "Variant 67 Blade", 3.5, 380, 54, false, ItemRarity.MEDIUM);
        registerWeapon(92, "Variant 68 Blade", 3.0, 1480, 102, false, ItemRarity.LEGENDARY);
        registerWeapon(93, "Variant 69 Blade", 2.5, 1360, 96, false, ItemRarity.LEGENDARY);
        registerWeapon(94, "Variant 70 Blade", 5.5, 750, 72, false, ItemRarity.HIGH);
        registerWeapon(95, "Variant 71 Blade", 3.5, 370, 52, false, ItemRarity.MEDIUM);
        registerWeapon(96, "Variant 72 Blade", 4.0, 165, 30, false, ItemRarity.LOW);
        registerWeapon(97, "Variant 73 Blade", 5.0, 780, 74, false, ItemRarity.HIGH);
        registerWeapon(98, "Variant 74 Blade", 3.0, 350, 50, false, ItemRarity.MEDIUM);
        registerWeapon(99, "Variant 75 Blade", 5.5, 820, 76, false, ItemRarity.HIGH);
        registerWeapon(100, "Variant 76 Blade", 3.5, 1540, 108, false, ItemRarity.LEGENDARY);

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
