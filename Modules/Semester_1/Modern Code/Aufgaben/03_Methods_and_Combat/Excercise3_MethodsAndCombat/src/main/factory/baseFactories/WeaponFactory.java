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
        // LOW: damage 6-44, PP cost 23-29 (+15%), special damage ~90% of base (+60%), ppGainPerUse 5-10
        // MEDIUM: damage 44-66, PP cost 35-42 (+15%), special damage ~83% of base (+60%), ppGainPerUse 9-13
        // HIGH: damage 66-88, PP cost 46-53 (+15%), special damage ~83% of base (+60%), ppGainPerUse 15-20
        // LEGENDARY: damage 88-132, PP cost 58-65 (+15%), special damage ~90% of base (+60%), ppGainPerUse 20-23
        registerWeapon(1, "Gateway Cutlass", 2.5, 280, 49, false, ItemRarity.MEDIUM, 39, "Arcane Slash", "Arcane Slash strikes with overwhelming force!", 35, 8);
        registerWeapon(2, "Abyssic Longrifle", 8.0, 750, 79, false, ItemRarity.HIGH, 63, "Abyssal Shot", "Abyssal Shot strikes with overwhelming force!", 48, 18);
        registerWeapon(3, "Protectorate Saber", 2.0, 320, 52, false, ItemRarity.MEDIUM, 42, "Protective Strike", "Protective Strike strikes with overwhelming force!", 37, 10);
        registerWeapon(4, "Riftfire Pistol", 2.2, 680, 74, true, ItemRarity.HIGH, 60, "Rift Inferno", "Rift Inferno strikes with overwhelming force!", 50, 15);
        registerWeapon(5, "Bonecut Halberd", 7.5, 380, 60, false, ItemRarity.MEDIUM, 49, "Bone Cleave", "Bone Cleave strikes with overwhelming force!", 40, 10);
        registerWeapon(6, "Echo Daggers (pair)", 1.5, 350, 52, true, ItemRarity.MEDIUM, 42, "Echo Barrage", "Echo Barrage strikes with overwhelming force!", 34, 7);
        registerWeapon(7, "Warpspike Lance", 9.5, 1450, 107, false, ItemRarity.LEGENDARY, 95, "Dimensional Pierce", "Dimensional Pierce strikes with overwhelming force!", 58, 20);
        registerWeapon(8, "Arc Thrower (hand)", 3.0, 820, 74, false, ItemRarity.HIGH, 60, "Lightning Arc", "Lightning Arc strikes with overwhelming force!", 48, 10);
        registerWeapon(9, "Phasebreaker Blade", 2.8, 1350, 101, true, ItemRarity.LEGENDARY, 90, "Phase Break", "Phase Break strikes with overwhelming force!", 60, 21);
        registerWeapon(10, "Starforged Rapier", 1.8, 295, 48, false, ItemRarity.MEDIUM, 39, "Starlight Thrust", "Starlight Thrust strikes with overwhelming force!", 39, 10);
        registerWeapon(11, "Seabreaker Trident", 6.0, 410, 63, false, ItemRarity.MEDIUM, 50, "Tidal Wave", "Tidal Wave strikes with overwhelming force!", 40, 15);
        registerWeapon(12, "Circuit Lacer", 2.5, 550, 68, false, ItemRarity.HIGH, 56, "Circuit Overload", "Circuit Overload strikes with overwhelming force!", 48, 16);
        registerWeapon(13, "Starcore Blade", 3.2, 1620, 115, true, ItemRarity.LEGENDARY, 103, "Stellar Flare", "Stellar Flare strikes with overwhelming force!", 58, 20);
        registerWeapon(14, "Riftfang Blade", 2.0, 380, 55, true, ItemRarity.MEDIUM, 44, "Void Rend", "Void Rend strikes with overwhelming force!", 36, 15);
        registerWeapon(15, "Wolfclan Hammer", 9.0, 780, 83, false, ItemRarity.HIGH, 67, "Pack Fury", "Pack Fury strikes with overwhelming force!", 49, 15);
        registerWeapon(16, "Protectorate Arc Pike", 8.5, 1280, 96, true, ItemRarity.LEGENDARY, 85, "Arc Surge", "Arc Surge strikes with overwhelming force!", 57, 21);
        registerWeapon(17, "Scrapgun Mk II", 6.0, 150, 35, false, ItemRarity.LOW, 30, "Scrap Blast", "Scrap Blast strikes with overwhelming force!", 25, 5);
        registerWeapon(18, "Smuggler's Repeater", 3.0, 340, 50, false, ItemRarity.MEDIUM, 40, "Rapid Fire", "Rapid Fire strikes with overwhelming force!", 34, 10);
        registerWeapon(19, "Runeforge Mace", 5.5, 690, 77, true, ItemRarity.HIGH, 62, "Rune Burst", "Rune Burst strikes with overwhelming force!", 49, 18);
        registerWeapon(20, "Bonechime Dagger", 1.2, 280, 46, true, ItemRarity.MEDIUM, 37, "Death Toll", "Death Toll strikes with overwhelming force!", 36, 15);
        registerWeapon(21, "Fargoth Execution Blade", 10.0, 1580, 123, false, ItemRarity.LEGENDARY, 110, "Final Judgment", "Final Judgment strikes with overwhelming force!", 58, 23);
        registerWeapon(22, "Echo Reaver Staff", 4.5, 620, 72, true, ItemRarity.HIGH, 59, "Echo Destruction", "Echo Destruction strikes with overwhelming force!", 46, 18);
        registerWeapon(23, "Stormcall Crossbow", 7.5, 740, 79, false, ItemRarity.HIGH, 63, "Storm Call", "Storm Call strikes with overwhelming force!", 47, 18);
        registerWeapon(24, "Fists", 0.0, 0, 14, false, ItemRarity.LOW, 16, "Desperate Flurry", "Desperate Flurry strikes with overwhelming force!", 5, 10);
        registerWeapon(25, "Coalspike Cleaver", 4.0, 380, 59, false, ItemRarity.MEDIUM, 47, "Coal Dust Cloud", "Coal Dust Cloud strikes with overwhelming force!", 35, 8);
        registerWeapon(26, "Canyon Cleaver", 7.5, 1420, 105, false, ItemRarity.LEGENDARY, 93, "Rock Slide", "Rock Slide strikes with overwhelming force!", 59, 23);
        registerWeapon(27, "Runic Pickaxe", 5.5, 125, 24, false, ItemRarity.LOW, 20, "Rune Strike", "Rune Strike strikes with overwhelming force!", 25, 8);
        registerWeapon(28, "Gutterboard Saw", 2.5, 720, 74, false, ItemRarity.HIGH, 60, "Sawblade Fury", "Sawblade Fury strikes with overwhelming force!", 48, 15);
        registerWeapon(29, "Feyspinner Bow", 2.0, 95, 19, false, ItemRarity.LOW, 16, "Fairy Shot", "Fairy Shot strikes with overwhelming force!", 27, 6);
        registerWeapon(30, "Ironclaw Pistol", 3.5, 420, 61, false, ItemRarity.MEDIUM, 49, "Claw Rend", "Claw Rend strikes with overwhelming force!", 34, 8);
        registerWeapon(31, "Smuggler's Shiv", 1.8, 310, 52, true, ItemRarity.MEDIUM, 42, "Shadow Stab", "Shadow Stab strikes with overwhelming force!", 35, 10);
        registerWeapon(32, "Tunnelcarver Axe", 6.0, 360, 57, true, ItemRarity.MEDIUM, 46, "Tunnel Carve", "Tunnel Carve strikes with overwhelming force!", 36, 15);
        registerWeapon(33, "Aether Chalice", 1.5, 1290, 99, true, ItemRarity.LEGENDARY, 89, "Aether Drain", "Aether Drain strikes with overwhelming force!", 58, 23);
        registerWeapon(34, "Brine Harpoon", 7.0, 780, 81, false, ItemRarity.HIGH, 66, "Brine Strike", "Brine Strike strikes with overwhelming force!", 48, 17);
        registerWeapon(35, "Forgebrand Axe", 8.0, 340, 55, false, ItemRarity.MEDIUM, 44, "Forge Burn", "Forge Burn strikes with overwhelming force!", 35, 10);
        registerWeapon(36, "Echo Lance", 4.5, 1380, 103, false, ItemRarity.LEGENDARY, 92, "Echo Strike", "Echo Strike strikes with overwhelming force!", 62, 21);
        registerWeapon(37, "Nightfall Shiv", 1.5, 290, 48, false, ItemRarity.MEDIUM, 39, "Nightfall", "Nightfall strikes with overwhelming force!", 35, 9);
        registerWeapon(38, "Bonesnap Mace", 3.5, 110, 22, false, ItemRarity.LOW, 19, "Bone Snap", "Bone Snap strikes with overwhelming force!", 26, 6);
        registerWeapon(39, "Heatsear Blunderbuss", 5.0, 140, 28, false, ItemRarity.LOW, 24, "Heat Sear", "Heat Sear strikes with overwhelming force!", 27, 8);
        registerWeapon(40, "Riftsplitter", 3.0, 400, 63, false, ItemRarity.MEDIUM, 50, "Rift Split", "Rift Split strikes with overwhelming force!", 39, 9);
        registerWeapon(41, "Stormcoil Pistol", 2.8, 1540, 118, false, ItemRarity.LEGENDARY, 105, "Storm Coil", "Storm Coil strikes with overwhelming force!", 58, 23);
        registerWeapon(42, "Runecharger Baton", 4.5, 680, 77, false, ItemRarity.HIGH, 62, "Rune Charge", "Rune Charge strikes with overwhelming force!", 46, 20);
        registerWeapon(43, "Wolfram Cleaver", 9.0, 90, 16, false, ItemRarity.LOW, 14, "Wolf Cleave", "Wolf Cleave strikes with overwhelming force!", 26, 5);
        registerWeapon(44, "Skymeld Sabre", 3.5, 1620, 121, false, ItemRarity.LEGENDARY, 108, "Sky Meld", "Sky Meld strikes with overwhelming force!", 59, 20);
        registerWeapon(45, "Echo-string Crossbow", 2.5, 330, 52, true, ItemRarity.MEDIUM, 42, "Echo String", "Echo String strikes with overwhelming force!", 37, 9);
        registerWeapon(46, "Shoreline Glaive", 5.5, 740, 79, false, ItemRarity.HIGH, 63, "Shore Break", "Shore Break strikes with overwhelming force!", 46, 18);
        registerWeapon(47, "Abyss Needle", 2.0, 120, 26, false, ItemRarity.LOW, 23, "Abyss Needle", "Abyss Needle strikes with overwhelming force!", 25, 5);
        registerWeapon(48, "Runewire Whip", 3.0, 1480, 112, true, ItemRarity.LEGENDARY, 100, "Rune Lash", "Rune Lash strikes with overwhelming force!", 57, 21);
        registerWeapon(49, "Smokestack Rifle", 6.5, 380, 59, false, ItemRarity.MEDIUM, 47, "Smoke Shot", "Smoke Shot strikes with overwhelming force!", 35, 8);
        registerWeapon(50, "Gloomcaller Flute", 2.5, 760, 81, true, ItemRarity.HIGH, 66, "Gloom Call", "Gloom Call strikes with overwhelming force!", 48, 18);
        registerWeapon(51, "Spore Spitter", 3.0, 85, 13, false, ItemRarity.LOW, 11, "Spore Cloud", "Spore Cloud strikes with overwhelming force!", 24, 6);
        registerWeapon(52, "Tidebreaker Maul", 7.0, 420, 61, false, ItemRarity.MEDIUM, 49, "Tide Break", "Tide Break strikes with overwhelming force!", 39, 15);
        registerWeapon(53, "Sirenbone Harp", 2.5, 1390, 107, true, ItemRarity.LEGENDARY, 95, "Siren Song", "Siren Song strikes with overwhelming force!", 58, 21);
        registerWeapon(54, "Cinderbrand Rapier", 2.0, 820, 83, false, ItemRarity.HIGH, 67, "Cinder Brand", "Cinder Brand strikes with overwhelming force!", 48, 17);
        registerWeapon(55, "Coalforge Pike", 9.5, 1560, 126, false, ItemRarity.LEGENDARY, 113, "Coal Forge", "Coal Forge strikes with overwhelming force!", 58, 21);
        registerWeapon(56, "Hollowpoint Cutter", 2.5, 350, 57, false, ItemRarity.MEDIUM, 46, "Hollow Point", "Hollow Point strikes with overwhelming force!", 34, 10);
        registerWeapon(57, "Gravhook Harpoon", 6.5, 730, 79, false, ItemRarity.HIGH, 63, "Grav Hook", "Grav Hook strikes with overwhelming force!", 47, 18);
        registerWeapon(58, "Nebula Handcannon", 3.0, 1430, 110, false, ItemRarity.LEGENDARY, 99, "Nebula Blast", "Nebula Blast strikes with overwhelming force!", 57, 21);
        registerWeapon(59, "Shard-edged Kukri", 2.0, 780, 81, false, ItemRarity.HIGH, 66, "Shard Edge", "Shard Edge strikes with overwhelming force!", 49, 19);
        registerWeapon(60, "Tinker Grenade", 1.5, 75, 17, false, ItemRarity.LOW, 14, "Grenade Toss", "Grenade Toss strikes with overwhelming force!", 23, 5);
        registerWeapon(61, "Phantom Dirk", 1.8, 380, 55, true, ItemRarity.MEDIUM, 44, "Phantom Strike", "Phantom Strike strikes with overwhelming force!", 35, 14);
        registerWeapon(62, "Ironjaw Club", 8.5, 1510, 116, false, ItemRarity.LEGENDARY, 103, "Iron Jaw", "Iron Jaw strikes with overwhelming force!", 57, 20);
        registerWeapon(63, "Riftflare Wand", 1.5, 690, 74, true, ItemRarity.HIGH, 60, "Rift Flare", "Rift Flare strikes with overwhelming force!", 49, 19);
        registerWeapon(64, "Voidspike Rifle", 7.5, 1580, 123, false, ItemRarity.LEGENDARY, 110, "Void Spike", "Void Spike strikes with overwhelming force!", 57, 23);
        registerWeapon(65, "Shardshot Revolver", 3.5, 340, 52, false, ItemRarity.MEDIUM, 42, "Shard Shot", "Shard Shot strikes with overwhelming force!", 37, 9);
        registerWeapon(66, "Runic Morningstar", 6.0, 850, 85, true, ItemRarity.HIGH, 69, "Rune Star", "Rune Star strikes with overwhelming force!", 48, 16);
        registerWeapon(67, "Seamstalker Spear", 5.0, 720, 79, false, ItemRarity.HIGH, 63, "Seam Stalk", "Seam Stalk strikes with overwhelming force!", 49, 17);
        registerWeapon(68, "Blinkblade", 2.2, 1490, 114, true, ItemRarity.LEGENDARY, 102, "Blink Strike", "Blink Strike strikes with overwhelming force!", 57, 23);
        registerWeapon(69, "Stitcher's Needle", 2.5, 760, 81, false, ItemRarity.HIGH, 66, "Stitch Pierce", "Stitch Pierce strikes with overwhelming force!", 51, 16);
        registerWeapon(70, "Golem Lash", 5.5, 410, 61, false, ItemRarity.MEDIUM, 49, "Golem Lash", "Golem Lash strikes with overwhelming force!", 39, 14);
        registerWeapon(71, "Stormcord Staff", 4.0, 790, 83, true, ItemRarity.HIGH, 67, "Storm Cord", "Storm Cord strikes with overwhelming force!", 49, 15);
        registerWeapon(72, "Rune-sap Mace", 5.0, 710, 77, true, ItemRarity.HIGH, 62, "Rune Sap", "Rune Sap strikes with overwhelming force!", 46, 16);
        registerWeapon(73, "Abyssal Hook", 4.5, 380, 59, false, ItemRarity.MEDIUM, 47, "Abyss Hook", "Abyss Hook strikes with overwhelming force!", 35, 10);
        registerWeapon(74, "Pilot's Saber", 2.5, 145, 30, false, ItemRarity.LOW, 26, "Pilot Strike", "Pilot Strike strikes with overwhelming force!", 27, 8);
        registerWeapon(75, "Echo-tongue Dagger", 1.8, 1520, 118, true, ItemRarity.LEGENDARY, 105, "Echo Tongue", "Echo Tongue strikes with overwhelming force!", 58, 21);
        registerWeapon(76, "Netherbrand Carbine", 5.5, 390, 59, false, ItemRarity.MEDIUM, 47, "Nether Brand", "Nether Brand strikes with overwhelming force!", 34, 9);
        registerWeapon(77, "Starfall Cleaver", 6.5, 790, 83, false, ItemRarity.HIGH, 67, "Starfall", "Starfall strikes with overwhelming force!", 51, 17);
        registerWeapon(78, "Warden's Flail", 5.0, 360, 57, false, ItemRarity.MEDIUM, 46, "Warden Flail", "Warden Flail strikes with overwhelming force!", 39, 15);
        registerWeapon(79, "Forgehammer", 8.0, 130, 27, false, ItemRarity.LOW, 24, "Forge Hammer", "Forge Hammer strikes with overwhelming force!", 27, 5);
        registerWeapon(80, "Coalbrand Javelin", 4.5, 380, 59, false, ItemRarity.MEDIUM, 47, "Coal Brand", "Coal Brand strikes with overwhelming force!", 34, 15);
        registerWeapon(81, "Sparkshell Rifle", 7.0, 1410, 110, false, ItemRarity.LEGENDARY, 99, "Spark Shell", "Spark Shell strikes with overwhelming force!", 58, 20);
        registerWeapon(82, "Bonewhisper Staff", 3.5, 310, 50, true, ItemRarity.MEDIUM, 40, "Bone Whisper", "Bone Whisper strikes with overwhelming force!", 34, 10);
        registerWeapon(83, "Wolfram Pike", 6.5, 400, 61, false, ItemRarity.MEDIUM, 49, "Wolf Pike", "Wolf Pike strikes with overwhelming force!", 40, 9);
        registerWeapon(84, "Guttercoil SMG", 4.5, 155, 33, false, ItemRarity.LOW, 29, "Gutter Coil", "Gutter Coil strikes with overwhelming force!", 27, 8);
        registerWeapon(85, "Runeflux Arcgun", 5.0, 420, 63, false, ItemRarity.MEDIUM, 50, "Rune Flux", "Rune Flux strikes with overwhelming force!", 37, 8);
        registerWeapon(86, "Harbinger Pike", 6.0, 390, 59, false, ItemRarity.MEDIUM, 47, "Harbinger", "Harbinger strikes with overwhelming force!", 34, 9);
        registerWeapon(87, "Fargoth Cleaver", 7.5, 840, 85, false, ItemRarity.HIGH, 69, "Fargoth Cleave", "Fargoth Cleave strikes with overwhelming force!", 49, 19);
        registerWeapon(88, "Hollowblade Scimitar", 3.5, 360, 57, false, ItemRarity.MEDIUM, 46, "Hollow Blade", "Hollow Blade strikes with overwhelming force!", 36, 15);
        registerWeapon(89, "Crimson Fang", 3.0, 410, 61, false, ItemRarity.MEDIUM, 49, "Crimson Fang", "Crimson Fang strikes with overwhelming force!", 37, 10);
        registerWeapon(90, "Rustbite Dagger", 4.5, 170, 35, false, ItemRarity.LOW, 30, "Rust Bite", "Rust Bite strikes with overwhelming force!", 23, 6);
        registerWeapon(91, "Shadowfang", 3.5, 380, 59, false, ItemRarity.MEDIUM, 47, "Shadow Fang", "Shadow Fang strikes with overwhelming force!", 35, 14);
        registerWeapon(92, "Eclipse Edge", 3.0, 1480, 112, false, ItemRarity.LEGENDARY, 100, "Eclipse", "Eclipse strikes with overwhelming force!", 57, 23);
        registerWeapon(93, "Oblivion Fang", 2.5, 1360, 105, false, ItemRarity.LEGENDARY, 93, "Oblivion", "Oblivion strikes with overwhelming force!", 58, 22);
        registerWeapon(94, "Stormreaver", 5.5, 750, 79, false, ItemRarity.HIGH, 63, "Storm Reave", "Storm Reave strikes with overwhelming force!", 48, 19);
        registerWeapon(95, "Ironclaw", 3.5, 370, 57, false, ItemRarity.MEDIUM, 46, "Iron Claw", "Iron Claw strikes with overwhelming force!", 40, 8);
        registerWeapon(96, "Whisperfang", 4.0, 165, 33, false, ItemRarity.LOW, 29, "Whisper Fang", "Whisper Fang strikes with overwhelming force!", 23, 5);
        registerWeapon(97, "Thunderstrike", 5.0, 780, 81, false, ItemRarity.HIGH, 66, "Thunder Strike", "Thunder Strike strikes with overwhelming force!", 47, 15);
        registerWeapon(98, "Nightfang", 3.0, 350, 55, false, ItemRarity.MEDIUM, 44, "Night Fang", "Night Fang strikes with overwhelming force!", 36, 8);
        registerWeapon(99, "Lionheart Edge", 5.5, 820, 83, false, ItemRarity.HIGH, 67, "Lion's Roar", "Lion's Roar strikes with overwhelming force!", 49, 19);
        registerWeapon(100, "Dragonfang", 3.5, 1540, 118, false, ItemRarity.LEGENDARY, 105, "Dragon's Rage", "Dragon's Rage strikes with overwhelming force!", 59, 23);
        registerWeapon(101, "PP Blade", 5, 15400000, 130, false, ItemRarity.LEGENDARY, 160, "PP Special", "The PP surges through your veins.", 100, 27);
        registerWeapon(102, "PP Knife", 3, 154000, 50, false, ItemRarity.MEDIUM, 69, "PP Special Lite", "PP is flowing your veins.", 50, 20);
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
