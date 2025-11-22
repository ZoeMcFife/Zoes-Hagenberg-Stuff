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
        double specialDamage;
        String specialAttackName;
        String specialFlavorText;
        int ppCost;
        int ppGainPerUse;

        WeaponData(int id, String name, double weight, double value, double damage, boolean isMagic, ItemRarity rarity,
                   double specialDamage, String specialAttackName, String specialFlavorText, int ppCost, int ppGainPerUse)
        {
            this.id = id;
            this.name = name;
            this.weight = weight;
            this.value = value;
            this.damage = damage;
            this.isMagic = isMagic;
            this.rarity = rarity;
            this.specialDamage = specialDamage;
            this.specialAttackName = specialAttackName;
            this.specialFlavorText = specialFlavorText;
            this.ppCost = ppCost;
            this.ppGainPerUse = ppGainPerUse;
        }
    }

    static
    {
        // Weapons rebalanced by rarity with PP special attacks
        // LOW: damage 5-40, PP cost 23-29 (+15%), special damage ~90% of base (+50%), ppGainPerUse 1-3
        // MEDIUM: damage 40-60, PP cost 35-42 (+15%), special damage ~83% of base (+50%), ppGainPerUse 3-7
        // HIGH: damage 60-80, PP cost 46-53 (+15%), special damage ~83% of base (+50%), ppGainPerUse 7-12
        // LEGENDARY: damage 80-120, PP cost 58-65 (+15%), special damage ~90% of base (+50%), ppGainPerUse 12-15
        registerWeapon(1, "Gateway Cutlass", 2.5, 280, 45, false, ItemRarity.MEDIUM, 36, "Arcane Slash", "Arcane Slash strikes with overwhelming force!", 35, 3);
        registerWeapon(2, "Abyssic Longrifle", 8.0, 750, 72, false, ItemRarity.HIGH, 58, "Abyssal Shot", "Abyssal Shot strikes with overwhelming force!", 48, 10);
        registerWeapon(3, "Protectorate Saber", 2.0, 320, 48, false, ItemRarity.MEDIUM, 39, "Protective Strike", "Protective Strike strikes with overwhelming force!", 37, 5);
        registerWeapon(4, "Riftfire Pistol", 2.2, 680, 68, true, ItemRarity.HIGH, 55, "Rift Inferno", "Rift Inferno strikes with overwhelming force!", 50, 7);
        registerWeapon(5, "Bonecut Halberd", 7.5, 380, 55, false, ItemRarity.MEDIUM, 45, "Bone Cleave", "Bone Cleave strikes with overwhelming force!", 40, 5);
        registerWeapon(6, "Echo Daggers (pair)", 1.5, 350, 52, true, ItemRarity.MEDIUM, 42, "Echo Barrage", "Echo Barrage strikes with overwhelming force!", 34, 7);
        registerWeapon(7, "Warpspike Lance", 9.5, 1450, 98, false, ItemRarity.LEGENDARY, 87, "Dimensional Pierce", "Dimensional Pierce strikes with overwhelming force!", 58, 12);
        registerWeapon(8, "Arc Thrower (hand)", 3.0, 820, 74, false, ItemRarity.HIGH, 60, "Lightning Arc", "Lightning Arc strikes with overwhelming force!", 48, 10);
        registerWeapon(9, "Phasebreaker Blade", 2.8, 1350, 92, true, ItemRarity.LEGENDARY, 82, "Phase Break", "Phase Break strikes with overwhelming force!", 60, 13);
        registerWeapon(10, "Starforged Rapier", 1.8, 295, 44, false, ItemRarity.MEDIUM, 36, "Starlight Thrust", "Starlight Thrust strikes with overwhelming force!", 39, 5);
        registerWeapon(11, "Seabreaker Trident", 6.0, 410, 58, false, ItemRarity.MEDIUM, 46, "Tidal Wave", "Tidal Wave strikes with overwhelming force!", 40, 7);
        registerWeapon(12, "Circuit Lacer", 2.5, 550, 62, false, ItemRarity.HIGH, 51, "Circuit Overload", "Circuit Overload strikes with overwhelming force!", 48, 8);
        registerWeapon(13, "Starcore Blade", 3.2, 1620, 105, true, ItemRarity.LEGENDARY, 94, "Stellar Flare", "Stellar Flare strikes with overwhelming force!", 58, 12);
        registerWeapon(14, "Riftfang Blade", 2.0, 380, 50, true, ItemRarity.MEDIUM, 40, "Void Rend", "Void Rend strikes with overwhelming force!", 36, 7);
        registerWeapon(15, "Wolfclan Hammer", 9.0, 780, 76, false, ItemRarity.HIGH, 61, "Pack Fury", "Pack Fury strikes with overwhelming force!", 49, 7);
        registerWeapon(16, "Protectorate Arc Pike", 8.5, 1280, 88, true, ItemRarity.LEGENDARY, 78, "Arc Surge", "Arc Surge strikes with overwhelming force!", 57, 13);
        registerWeapon(17, "Scrapgun Mk II", 6.0, 150, 32, false, ItemRarity.LOW, 28, "Scrap Blast", "Scrap Blast strikes with overwhelming force!", 25, 1);
        registerWeapon(18, "Smuggler's Repeater", 3.0, 340, 46, false, ItemRarity.MEDIUM, 37, "Rapid Fire", "Rapid Fire strikes with overwhelming force!", 34, 5);
        registerWeapon(19, "Runeforge Mace", 5.5, 690, 70, true, ItemRarity.HIGH, 57, "Rune Burst", "Rune Burst strikes with overwhelming force!", 49, 10);
        registerWeapon(20, "Bonechime Dagger", 1.2, 280, 42, true, ItemRarity.MEDIUM, 34, "Death Toll", "Death Toll strikes with overwhelming force!", 36, 7);
        registerWeapon(21, "Fargoth Execution Blade", 10.0, 1580, 112, false, ItemRarity.LEGENDARY, 100, "Final Judgment", "Final Judgment strikes with overwhelming force!", 58, 15);
        registerWeapon(22, "Echo Reaver Staff", 4.5, 620, 66, true, ItemRarity.HIGH, 54, "Echo Destruction", "Echo Destruction strikes with overwhelming force!", 46, 10);
        registerWeapon(23, "Stormcall Crossbow", 7.5, 740, 72, false, ItemRarity.HIGH, 58, "Storm Call", "Storm Call strikes with overwhelming force!", 47, 10);
        registerWeapon(24, "Fists", 0.0, 0, 13, false, ItemRarity.LOW, 15, "Desperate Flurry", "Desperate Flurry strikes with overwhelming force!", 5, 5);
        registerWeapon(25, "Coalspike Cleaver", 4.0, 380, 54, false, ItemRarity.MEDIUM, 43, "Coal Dust Cloud", "Coal Dust Cloud strikes with overwhelming force!", 35, 3);
        registerWeapon(26, "Canyon Cleaver", 7.5, 1420, 96, false, ItemRarity.LEGENDARY, 85, "Rock Slide", "Rock Slide strikes with overwhelming force!", 59, 15);
        registerWeapon(27, "Runic Pickaxe", 5.5, 125, 22, false, ItemRarity.LOW, 19, "Rune Strike", "Rune Strike strikes with overwhelming force!", 25, 3);
        registerWeapon(28, "Gutterboard Saw", 2.5, 720, 68, false, ItemRarity.HIGH, 55, "Sawblade Fury", "Sawblade Fury strikes with overwhelming force!", 48, 7);
        registerWeapon(29, "Feyspinner Bow", 2.0, 95, 18, false, ItemRarity.LOW, 15, "Fairy Shot", "Fairy Shot strikes with overwhelming force!", 27, 2);
        registerWeapon(30, "Ironclaw Pistol", 3.5, 420, 56, false, ItemRarity.MEDIUM, 45, "Claw Rend", "Claw Rend strikes with overwhelming force!", 34, 3);
        registerWeapon(31, "Smuggler's Shiv", 1.8, 310, 48, true, ItemRarity.MEDIUM, 39, "Shadow Stab", "Shadow Stab strikes with overwhelming force!", 35, 5);
        registerWeapon(32, "Tunnelcarver Axe", 6.0, 360, 52, true, ItemRarity.MEDIUM, 42, "Tunnel Carve", "Tunnel Carve strikes with overwhelming force!", 36, 7);
        registerWeapon(33, "Aether Chalice", 1.5, 1290, 90, true, ItemRarity.LEGENDARY, 81, "Aether Drain", "Aether Drain strikes with overwhelming force!", 58, 15);
        registerWeapon(34, "Brine Harpoon", 7.0, 780, 74, false, ItemRarity.HIGH, 60, "Brine Strike", "Brine Strike strikes with overwhelming force!", 48, 9);
        registerWeapon(35, "Forgebrand Axe", 8.0, 340, 50, false, ItemRarity.MEDIUM, 40, "Forge Burn", "Forge Burn strikes with overwhelming force!", 35, 5);
        registerWeapon(36, "Echo Lance", 4.5, 1380, 94, false, ItemRarity.LEGENDARY, 84, "Echo Strike", "Echo Strike strikes with overwhelming force!", 62, 13);
        registerWeapon(37, "Nightfall Shiv", 1.5, 290, 44, false, ItemRarity.MEDIUM, 36, "Nightfall", "Nightfall strikes with overwhelming force!", 35, 4);
        registerWeapon(38, "Bonesnap Mace", 3.5, 110, 20, false, ItemRarity.LOW, 18, "Bone Snap", "Bone Snap strikes with overwhelming force!", 26, 2);
        registerWeapon(39, "Heatsear Blunderbuss", 5.0, 140, 26, false, ItemRarity.LOW, 22, "Heat Sear", "Heat Sear strikes with overwhelming force!", 27, 3);
        registerWeapon(40, "Riftsplitter", 3.0, 400, 58, false, ItemRarity.MEDIUM, 46, "Rift Split", "Rift Split strikes with overwhelming force!", 39, 4);
        registerWeapon(41, "Stormcoil Pistol", 2.8, 1540, 108, false, ItemRarity.LEGENDARY, 96, "Storm Coil", "Storm Coil strikes with overwhelming force!", 58, 15);
        registerWeapon(42, "Runecharger Baton", 4.5, 680, 70, false, ItemRarity.HIGH, 57, "Rune Charge", "Rune Charge strikes with overwhelming force!", 46, 12);
        registerWeapon(43, "Wolfram Cleaver", 9.0, 90, 15, false, ItemRarity.LOW, 13, "Wolf Cleave", "Wolf Cleave strikes with overwhelming force!", 26, 1);
        registerWeapon(44, "Skymeld Sabre", 3.5, 1620, 110, false, ItemRarity.LEGENDARY, 99, "Sky Meld", "Sky Meld strikes with overwhelming force!", 59, 12);
        registerWeapon(45, "Echo-string Crossbow", 2.5, 330, 48, true, ItemRarity.MEDIUM, 39, "Echo String", "Echo String strikes with overwhelming force!", 37, 4);
        registerWeapon(46, "Shoreline Glaive", 5.5, 740, 72, false, ItemRarity.HIGH, 58, "Shore Break", "Shore Break strikes with overwhelming force!", 46, 10);
        registerWeapon(47, "Abyss Needle", 2.0, 120, 24, false, ItemRarity.LOW, 21, "Abyss Needle", "Abyss Needle strikes with overwhelming force!", 25, 1);
        registerWeapon(48, "Runewire Whip", 3.0, 1480, 102, true, ItemRarity.LEGENDARY, 91, "Rune Lash", "Rune Lash strikes with overwhelming force!", 57, 13);
        registerWeapon(49, "Smokestack Rifle", 6.5, 380, 54, false, ItemRarity.MEDIUM, 43, "Smoke Shot", "Smoke Shot strikes with overwhelming force!", 35, 3);
        registerWeapon(50, "Gloomcaller Flute", 2.5, 760, 74, true, ItemRarity.HIGH, 60, "Gloom Call", "Gloom Call strikes with overwhelming force!", 48, 10);
        registerWeapon(51, "Spore Spitter", 3.0, 85, 12, false, ItemRarity.LOW, 10, "Spore Cloud", "Spore Cloud strikes with overwhelming force!", 24, 2);
        registerWeapon(52, "Tidebreaker Maul", 7.0, 420, 56, false, ItemRarity.MEDIUM, 45, "Tide Break", "Tide Break strikes with overwhelming force!", 39, 7);
        registerWeapon(53, "Sirenbone Harp", 2.5, 1390, 98, true, ItemRarity.LEGENDARY, 87, "Siren Song", "Siren Song strikes with overwhelming force!", 58, 13);
        registerWeapon(54, "Cinderbrand Rapier", 2.0, 820, 76, false, ItemRarity.HIGH, 61, "Cinder Brand", "Cinder Brand strikes with overwhelming force!", 48, 9);
        registerWeapon(55, "Coalforge Pike", 9.5, 1560, 115, false, ItemRarity.LEGENDARY, 103, "Coal Forge", "Coal Forge strikes with overwhelming force!", 58, 13);
        registerWeapon(56, "Hollowpoint Cutter", 2.5, 350, 52, false, ItemRarity.MEDIUM, 42, "Hollow Point", "Hollow Point strikes with overwhelming force!", 34, 5);
        registerWeapon(57, "Gravhook Harpoon", 6.5, 730, 72, false, ItemRarity.HIGH, 58, "Grav Hook", "Grav Hook strikes with overwhelming force!", 47, 10);
        registerWeapon(58, "Nebula Handcannon", 3.0, 1430, 100, false, ItemRarity.LEGENDARY, 90, "Nebula Blast", "Nebula Blast strikes with overwhelming force!", 57, 13);
        registerWeapon(59, "Shard-edged Kukri", 2.0, 780, 74, false, ItemRarity.HIGH, 60, "Shard Edge", "Shard Edge strikes with overwhelming force!", 49, 11);
        registerWeapon(60, "Tinker Grenade", 1.5, 75, 16, false, ItemRarity.LOW, 13, "Grenade Toss", "Grenade Toss strikes with overwhelming force!", 23, 1);
        registerWeapon(61, "Phantom Dirk", 1.8, 380, 50, true, ItemRarity.MEDIUM, 40, "Phantom Strike", "Phantom Strike strikes with overwhelming force!", 35, 6);
        registerWeapon(62, "Ironjaw Club", 8.5, 1510, 106, false, ItemRarity.LEGENDARY, 94, "Iron Jaw", "Iron Jaw strikes with overwhelming force!", 57, 12);
        registerWeapon(63, "Riftflare Wand", 1.5, 690, 68, true, ItemRarity.HIGH, 55, "Rift Flare", "Rift Flare strikes with overwhelming force!", 49, 11);
        registerWeapon(64, "Voidspike Rifle", 7.5, 1580, 112, false, ItemRarity.LEGENDARY, 100, "Void Spike", "Void Spike strikes with overwhelming force!", 57, 15);
        registerWeapon(65, "Shardshot Revolver", 3.5, 340, 48, false, ItemRarity.MEDIUM, 39, "Shard Shot", "Shard Shot strikes with overwhelming force!", 37, 4);
        registerWeapon(66, "Runic Morningstar", 6.0, 850, 78, true, ItemRarity.HIGH, 63, "Rune Star", "Rune Star strikes with overwhelming force!", 48, 8);
        registerWeapon(67, "Seamstalker Spear", 5.0, 720, 72, false, ItemRarity.HIGH, 58, "Seam Stalk", "Seam Stalk strikes with overwhelming force!", 49, 9);
        registerWeapon(68, "Blinkblade", 2.2, 1490, 104, true, ItemRarity.LEGENDARY, 93, "Blink Strike", "Blink Strike strikes with overwhelming force!", 57, 15);
        registerWeapon(69, "Stitcher's Needle", 2.5, 760, 74, false, ItemRarity.HIGH, 60, "Stitch Pierce", "Stitch Pierce strikes with overwhelming force!", 51, 8);
        registerWeapon(70, "Golem Lash", 5.5, 410, 56, false, ItemRarity.MEDIUM, 45, "Golem Lash", "Golem Lash strikes with overwhelming force!", 39, 6);
        registerWeapon(71, "Stormcord Staff", 4.0, 790, 76, true, ItemRarity.HIGH, 61, "Storm Cord", "Storm Cord strikes with overwhelming force!", 49, 7);
        registerWeapon(72, "Rune-sap Mace", 5.0, 710, 70, true, ItemRarity.HIGH, 57, "Rune Sap", "Rune Sap strikes with overwhelming force!", 46, 8);
        registerWeapon(73, "Abyssal Hook", 4.5, 380, 54, false, ItemRarity.MEDIUM, 43, "Abyss Hook", "Abyss Hook strikes with overwhelming force!", 35, 5);
        registerWeapon(74, "Pilot's Saber", 2.5, 145, 28, false, ItemRarity.LOW, 24, "Pilot Strike", "Pilot Strike strikes with overwhelming force!", 27, 3);
        registerWeapon(75, "Echo-tongue Dagger", 1.8, 1520, 108, true, ItemRarity.LEGENDARY, 96, "Echo Tongue", "Echo Tongue strikes with overwhelming force!", 58, 13);
        registerWeapon(76, "Netherbrand Carbine", 5.5, 390, 54, false, ItemRarity.MEDIUM, 43, "Nether Brand", "Nether Brand strikes with overwhelming force!", 34, 4);
        registerWeapon(77, "Starfall Cleaver", 6.5, 790, 76, false, ItemRarity.HIGH, 61, "Starfall", "Starfall strikes with overwhelming force!", 51, 9);
        registerWeapon(78, "Warden's Flail", 5.0, 360, 52, false, ItemRarity.MEDIUM, 42, "Warden Flail", "Warden Flail strikes with overwhelming force!", 39, 7);
        registerWeapon(79, "Forgehammer", 8.0, 130, 25, false, ItemRarity.LOW, 22, "Forge Hammer", "Forge Hammer strikes with overwhelming force!", 27, 1);
        registerWeapon(80, "Coalbrand Javelin", 4.5, 380, 54, false, ItemRarity.MEDIUM, 43, "Coal Brand", "Coal Brand strikes with overwhelming force!", 34, 7);
        registerWeapon(81, "Sparkshell Rifle", 7.0, 1410, 100, false, ItemRarity.LEGENDARY, 90, "Spark Shell", "Spark Shell strikes with overwhelming force!", 58, 12);
        registerWeapon(82, "Bonewhisper Staff", 3.5, 310, 46, true, ItemRarity.MEDIUM, 37, "Bone Whisper", "Bone Whisper strikes with overwhelming force!", 34, 5);
        registerWeapon(83, "Wolfram Pike", 6.5, 400, 56, false, ItemRarity.MEDIUM, 45, "Wolf Pike", "Wolf Pike strikes with overwhelming force!", 40, 4);
        registerWeapon(84, "Guttercoil SMG", 4.5, 155, 30, false, ItemRarity.LOW, 27, "Gutter Coil", "Gutter Coil strikes with overwhelming force!", 27, 3);
        registerWeapon(85, "Runeflux Arcgun", 5.0, 420, 58, false, ItemRarity.MEDIUM, 46, "Rune Flux", "Rune Flux strikes with overwhelming force!", 37, 3);
        registerWeapon(86, "Harbinger Pike", 6.0, 390, 54, false, ItemRarity.MEDIUM, 43, "Harbinger", "Harbinger strikes with overwhelming force!", 34, 4);
        registerWeapon(87, "Fargoth Cleaver", 7.5, 840, 78, false, ItemRarity.HIGH, 63, "Fargoth Cleave", "Fargoth Cleave strikes with overwhelming force!", 49, 11);
        registerWeapon(88, "Hollowblade Scimitar", 3.5, 360, 52, false, ItemRarity.MEDIUM, 42, "Hollow Blade", "Hollow Blade strikes with overwhelming force!", 36, 7);
        registerWeapon(89, "Crimson Fang", 3.0, 410, 56, false, ItemRarity.MEDIUM, 45, "Crimson Fang", "Crimson Fang strikes with overwhelming force!", 37, 5);
        registerWeapon(90, "Rustbite Dagger", 4.5, 170, 32, false, ItemRarity.LOW, 28, "Rust Bite", "Rust Bite strikes with overwhelming force!", 23, 2);
        registerWeapon(91, "Shadowfang", 3.5, 380, 54, false, ItemRarity.MEDIUM, 43, "Shadow Fang", "Shadow Fang strikes with overwhelming force!", 35, 6);
        registerWeapon(92, "Eclipse Edge", 3.0, 1480, 102, false, ItemRarity.LEGENDARY, 91, "Eclipse", "Eclipse strikes with overwhelming force!", 57, 15);
        registerWeapon(93, "Oblivion Fang", 2.5, 1360, 96, false, ItemRarity.LEGENDARY, 85, "Oblivion", "Oblivion strikes with overwhelming force!", 58, 14);
        registerWeapon(94, "Stormreaver", 5.5, 750, 72, false, ItemRarity.HIGH, 58, "Storm Reave", "Storm Reave strikes with overwhelming force!", 48, 11);
        registerWeapon(95, "Ironclaw", 3.5, 370, 52, false, ItemRarity.MEDIUM, 42, "Iron Claw", "Iron Claw strikes with overwhelming force!", 40, 3);
        registerWeapon(96, "Whisperfang", 4.0, 165, 30, false, ItemRarity.LOW, 27, "Whisper Fang", "Whisper Fang strikes with overwhelming force!", 23, 1);
        registerWeapon(97, "Thunderstrike", 5.0, 780, 74, false, ItemRarity.HIGH, 60, "Thunder Strike", "Thunder Strike strikes with overwhelming force!", 47, 7);
        registerWeapon(98, "Nightfang", 3.0, 350, 50, false, ItemRarity.MEDIUM, 40, "Night Fang", "Night Fang strikes with overwhelming force!", 36, 3);
        registerWeapon(99, "Lionheart Edge", 5.5, 820, 76, false, ItemRarity.HIGH, 61, "Lion's Roar", "Lion's Roar strikes with overwhelming force!", 49, 11);
        registerWeapon(100, "Dragonfang", 3.5, 1540, 108, false, ItemRarity.LEGENDARY, 96, "Dragon's Rage", "Dragon's Rage strikes with overwhelming force!", 59, 15);

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

    private static void registerWeapon(int id, String name, double weight, double value, double damage, boolean isMagic, ItemRarity rarity,
                                       double specialDamage, String specialAttackName, String specialFlavorText, int ppCost, int ppGainPerUse)
    {
        WeaponData data = new WeaponData(id, name, weight, value, damage, isMagic, rarity, specialDamage, specialAttackName, specialFlavorText, ppCost, ppGainPerUse);
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
        return new Weapon(data.name, data.weight, data.value, data.damage, data.isMagic, data.rarity,
                         data.specialDamage, data.specialAttackName, data.specialFlavorText, data.ppCost, data.ppGainPerUse);
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
