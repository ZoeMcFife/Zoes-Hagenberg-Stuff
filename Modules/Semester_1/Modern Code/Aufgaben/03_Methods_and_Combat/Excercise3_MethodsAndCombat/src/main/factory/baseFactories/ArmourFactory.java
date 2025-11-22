package main.factory.baseFactories;

import main.item.Armour;
import main.item.ItemRarity;

import java.util.*;

/**
 * Factory class for creating armour items.
 * Provides methods to create armour by ID, name, rarity, or randomly.
 * Contains 18 predefined armour pieces with various defensive capabilities.
 */
public class ArmourFactory
{
    private static final Map<Integer, ArmourData> ARMOURS_BY_ID = new HashMap<>();
    private static final Map<String, ArmourData> ARMOURS_BY_NAME = new HashMap<>();
    private static final Map<ItemRarity, List<ArmourData>> ARMOURS_BY_RARITY = new EnumMap<>(ItemRarity.class);
    private static final List<ArmourData> ALL_ARMOURS = new ArrayList<>();
    private static final Random random = new Random();

    private static class ArmourData
    {
        int id;
        String name;
        double weight;
        double value;
        double defense;
        double maxDurability;
        ItemRarity rarity;

        ArmourData(int id, String name, double weight, double value, double defense, double maxDurability, ItemRarity rarity)
        {
            this.id = id;
            this.name = name;
            this.weight = weight;
            this.value = value;
            this.defense = defense;
            this.maxDurability = maxDurability;
            this.rarity = rarity;
        }
    }

    static
    {
        // Armour rebalanced by rarity
        // LOW: defense 5-20, durability 100-200, weight 1-6, value proportional
        // MEDIUM: defense 20-40, durability 200-500, weight 2-10, value proportional
        // HIGH: defense 40-60, durability 500-900, weight 3-15, value proportional
        // LEGENDARY: defense 60-80, durability 900-2000, weight 5-20, value proportional
        registerArmour(1, "Fargoth War Gauntlet", 10.0, 750, 52, 6800, ItemRarity.HIGH);
        registerArmour(2, "Null-Field Cloak", 8.0, 820, 56, 7200, ItemRarity.HIGH);
        registerArmour(3, "Skymetal Plate", 18.0, 1650, 72, 14800, ItemRarity.LEGENDARY);
        registerArmour(4, "Ironclad Greaves", 4.0, 280, 12, 1400, ItemRarity.LOW);
        registerArmour(5, "Rune-etched Helm", 6.0, 380, 28, 3200, ItemRarity.MEDIUM);
        registerArmour(6, "Aegis Mesh Vest", 9.0, 880, 58, 7800, ItemRarity.HIGH);
        registerArmour(7, "Shadowstep Boots", 7.0, 720, 48, 620, ItemRarity.HIGH);
        registerArmour(8, "Warden's Mantle", 8.5, 760, 54, 7000, ItemRarity.HIGH);
        registerArmour(9, "Patchwork Armour Mk I", 5.0, 180, 14, 1600, ItemRarity.LOW);
        registerArmour(10, "Abysswatcher Helm", 6.5, 360, 26, 3100, ItemRarity.MEDIUM);
        registerArmour(11, "Shadowweave Coat", 8.0, 780, 52, 7200, ItemRarity.HIGH);
        registerArmour(12, "Guardian Frame Mk III", 16.0, 1840, 76, 16200, ItemRarity.LEGENDARY);
        registerArmour(13, "Wolfclan Chestplate", 11.0, 840, 56, 7500, ItemRarity.HIGH);
        registerArmour(14, "Runic Guard Plate", 10.5, 820, 54, 7300, ItemRarity.HIGH);
        registerArmour(15, "Fargoth Barrier Cloak", 12.0, 1720, 68, 15400, ItemRarity.LEGENDARY);
        registerArmour(16, "Explorer's Webbing", 3.5, 260, 10, 1500, ItemRarity.LOW);
        registerArmour(17, "Crimson Vambraces", 6.0, 390, 28, 3400, ItemRarity.MEDIUM);
        registerArmour(18, "Clothes", 1.0, 50, 10, 450, ItemRarity.LOW);
        registerArmour(19, "Coalminer Vest", 4.5, 220, 15, 1600, ItemRarity.LOW);
        registerArmour(20, "Fey-silk Coat", 9.5, 790, 52, 7100, ItemRarity.HIGH);
        registerArmour(21, "Runebound Plate", 15.0, 1920, 84, 16800, ItemRarity.LEGENDARY);
        registerArmour(22, "Tunnelweave Harness", 7.0, 420, 32, 3800, ItemRarity.MEDIUM);
        registerArmour(23, "Smelter Apron", 12.0, 860, 58, 7900, ItemRarity.HIGH);
        registerArmour(24, "Gutterhide Jacket", 6.5, 380, 30, 3400, ItemRarity.MEDIUM);
        registerArmour(25, "Cindermail", 10.0, 810, 54, 7200, ItemRarity.HIGH);
        registerArmour(26, "Void-forged Cuirass", 17.0, 1880, 76, 17200, ItemRarity.LEGENDARY);
        registerArmour(27, "Seamrunner Jacket", 7.5, 410, 32, 3700, ItemRarity.MEDIUM);
        registerArmour(28, "Nebula Overcoat", 13.0, 920, 58, 8400, ItemRarity.HIGH);
        registerArmour(29, "Echo Mesh", 8.0, 440, 34, 4000, ItemRarity.MEDIUM);
        registerArmour(30, "Hearthguard Mail", 11.5, 850, 56, 7700, ItemRarity.HIGH);
        registerArmour(31, "Sporeguard Robes", 9.0, 780, 52, 7000, ItemRarity.HIGH);
        registerArmour(32, "Marrow Plate", 7.0, 390, 30, 3500, ItemRarity.MEDIUM);
        registerArmour(33, "Gravel Shielding", 19.0, 1760, 72, 15800, ItemRarity.LEGENDARY);
        registerArmour(34, "Tinkerwright Gear", 8.5, 450, 34, 4100, ItemRarity.MEDIUM);
        registerArmour(35, "Ironbark Vest", 12.5, 880, 58, 8000, ItemRarity.HIGH);
        registerArmour(36, "Sirenscale Jacket", 3.5, 240, 12, 1700, ItemRarity.LOW);
        registerArmour(37, "Abysswatch Cloak", 10.0, 840, 56, 7600, ItemRarity.HIGH);
        registerArmour(38, "Blinkweave Vest", 6.5, 400, 30, 3600, ItemRarity.MEDIUM);
        registerArmour(39, "Starforged Mail", 13.5, 900, 58, 8200, ItemRarity.HIGH);
        registerArmour(40, "Runic Workplate", 16.5, 1810, 70, 16400, ItemRarity.LEGENDARY);
        registerArmour(41, "Forgeplate Greaves", 7.5, 420, 32, 3800, ItemRarity.MEDIUM);
        registerArmour(42, "Hollowbone Greaves", 5.5, 330, 24, 2900, ItemRarity.MEDIUM);
        registerArmour(43, "Smogproof Tunic", 11.0, 870, 56, 7900, ItemRarity.HIGH);
        registerArmour(44, "Warden's Pauldrons", 9.5, 810, 54, 7300, ItemRarity.HIGH);
        registerArmour(45, "Coalplate Breast", 14.5, 1580, 66, 14200, ItemRarity.LEGENDARY);
        registerArmour(46, "Mariner's Jerkin", 10.5, 760, 50, 6800, ItemRarity.HIGH);
        registerArmour(47, "Rustwarden Hauberk", 6.0, 370, 28, 3300, ItemRarity.MEDIUM);
        registerArmour(48, "Voidward Girdle", 18.5, 1940, 78, 17800, ItemRarity.LEGENDARY);
        registerArmour(49, "Galeweave Shirt", 9.0, 790, 52, 7100, ItemRarity.HIGH);
        registerArmour(50, "Stonebound Hauberk", 7.0, 410, 32, 3700, ItemRarity.MEDIUM);
        registerArmour(51, "Runeweave Gloves", 6.0, 380, 28, 3400, ItemRarity.MEDIUM);
        registerArmour(52, "Tidecaller's Wrap", 6.5, 390, 30, 3500, ItemRarity.MEDIUM);
        registerArmour(53, "Graveyard Shroud", 7.0, 400, 30, 3600, ItemRarity.MEDIUM);
        registerArmour(54, "Smuggler's Leathers", 14.0, 1680, 68, 15200, ItemRarity.LEGENDARY);
        registerArmour(55, "Harpooner's Coat", 7.5, 420, 32, 3800, ItemRarity.MEDIUM);
        registerArmour(56, "Brineproof Suit", 12.0, 890, 58, 8100, ItemRarity.HIGH);
        registerArmour(57, "Rift-stitched Overalls", 11.5, 860, 56, 7800, ItemRarity.HIGH);
        registerArmour(58, "Shardbound Vest", 10.5, 830, 54, 7500, ItemRarity.HIGH);
        registerArmour(59, "Gloomcloak", 8.5, 450, 34, 4100, ItemRarity.MEDIUM);
        registerArmour(60, "Pilot's Armor", 15.5, 1720, 70, 15600, ItemRarity.LEGENDARY);
        registerArmour(61, "Engineer Apron MkII", 7.0, 410, 32, 3700, ItemRarity.MEDIUM);
        registerArmour(62, "Sparksmith Jacket", 9.5, 800, 52, 7200, ItemRarity.HIGH);
        registerArmour(63, "Runic Keeper Plate", 10.0, 820, 54, 7400, ItemRarity.HIGH);
        registerArmour(64, "Wolfclan Greaves MkII", 16.0, 1780, 72, 16000, ItemRarity.LEGENDARY);
        registerArmour(65, "Wolfram Helm", 10.5, 850, 56, 7700, ItemRarity.HIGH);
        registerArmour(66, "Seabreak Helm", 6.0, 370, 28, 3300, ItemRarity.MEDIUM);
        registerArmour(67, "Beacon Harness", 11.0, 870, 56, 7900, ItemRarity.HIGH);
        registerArmour(68, "Abyssal Mantle", 15.0, 1640, 68, 14800, ItemRarity.LEGENDARY);
        registerArmour(69, "Coal-smelter Helm", 18.0, 1860, 74, 16800, ItemRarity.LEGENDARY);
        registerArmour(70, "Echo Guard Chest", 7.5, 420, 32, 3800, ItemRarity.MEDIUM);
        registerArmour(71, "Netherweave Vest", 11.0, 870, 56, 7900, ItemRarity.HIGH);
        registerArmour(72, "Protectorate Harness", 12.0, 900, 58, 8200, ItemRarity.HIGH);
        registerArmour(73, "Fargoth Platelet", 8.0, 440, 34, 4000, ItemRarity.MEDIUM);
        registerArmour(74, "Gateway Harness", 17.5, 1820, 74, 16600, ItemRarity.LEGENDARY);
        registerArmour(75, "Portalkeeper Shawl", 9.0, 780, 52, 7000, ItemRarity.HIGH);
        registerArmour(76, "Shadecloak", 16.5, 1740, 70, 15800, ItemRarity.LEGENDARY);
        registerArmour(77, "Runic Ceremonial Coat", 6.5, 390, 30, 3500, ItemRarity.MEDIUM);
        registerArmour(78, "Voidsteel Brigandine", 7.0, 400, 30, 3600, ItemRarity.MEDIUM);
        registerArmour(79, "Starhelm", 19.0, 1900, 76, 17400, ItemRarity.LEGENDARY);
        registerArmour(80, "Golem Harness", 5.0, 320, 22, 2800, ItemRarity.MEDIUM);
        registerArmour(81, "Ironbender Waist", 17.0, 1820, 74, 16060, ItemRarity.LEGENDARY);
        registerArmour(82, "Dustward Tunic", 10.0, 820, 54, 7400, ItemRarity.HIGH);
        registerArmour(83, "Spirit-etched Mesh", 6.0, 370, 28, 3030, ItemRarity.MEDIUM);
        registerArmour(84, "Cargo Handler Vest", 13.0, 920, 58, 8400, ItemRarity.HIGH);
        registerArmour(85, "Leviathan-hide Coat", 7.5, 410, 32, 3700, ItemRarity.MEDIUM);
        registerArmour(86, "Riftsteel Mail", 6.5, 390, 30, 3500, ItemRarity.MEDIUM);
        registerArmour(87, "Tollkeeper Vest", 4.0, 250, 14, 1800, ItemRarity.LOW);
        registerArmour(88, "Forgeborn Girdle", 12.5, 900, 58, 8200, ItemRarity.HIGH);
        registerArmour(89, "Beacon Plate", 6.0, 380, 28, 3400, ItemRarity.MEDIUM);
        registerArmour(90, "Rundown Vest", 3.5, 230, 12, 1600, ItemRarity.LOW);
        registerArmour(91, "Patchwork MkII", 5.5, 340, 24, 3000, ItemRarity.MEDIUM);
        registerArmour(92, "Sootguard Cloak", 5.0, 310, 22, 2700, ItemRarity.MEDIUM);
        registerArmour(93, "Dragonhide Plate", 18.5, 1880, 76, 17200, ItemRarity.LEGENDARY);
        registerArmour(94, "Ironwarden Suit", 6.0, 370, 28, 3300, ItemRarity.MEDIUM);
        registerArmour(95, "Stormbreaker Armor", 9.5, 800, 52, 7200, ItemRarity.HIGH);
        registerArmour(96, "Celestial Aegis Armor", 15.5, 1700, 68, 15400, ItemRarity.LEGENDARY);
        registerArmour(97, "Steel Sentinel", 8.0, 440, 34, 4000, ItemRarity.MEDIUM);
        registerArmour(98, "Shadowguard Vest", 8.5, 450, 34, 4010, ItemRarity.MEDIUM);
        registerArmour(99, "Lionheart Plate", 9.0, 460, 36, 4200, ItemRarity.MEDIUM);
        registerArmour(100, "Falconwing Armor", 7.5, 420, 32, 3800, ItemRarity.MEDIUM);

    }

    public static Armour createBaseArmour()
    {
        return createArmourByName("Clothes");
    }

    private static void registerArmour(int id, String name, double weight, double value, double defense, double maxDurability, ItemRarity rarity)
    {
        ArmourData data = new ArmourData(id, name, weight, value, defense, maxDurability, rarity);
        ARMOURS_BY_ID.put(id, data);
        ARMOURS_BY_NAME.put(name.toLowerCase(), data);
        ARMOURS_BY_RARITY.computeIfAbsent(rarity, k -> new ArrayList<>()).add(data);
        ALL_ARMOURS.add(data);
    }

    public static Armour createArmourById(int id)
    {
        ArmourData data = ARMOURS_BY_ID.get(id);
        if (data == null)
        {
            throw new IllegalArgumentException("Armour with ID " + id + " not found");
        }
        return createArmourFromData(data);
    }

    public static Armour createArmourByName(String name)
    {
        ArmourData data = ARMOURS_BY_NAME.get(name.toLowerCase());
        if (data == null)
        {
            throw new IllegalArgumentException("Armour with name '" + name + "' not found");
        }
        return createArmourFromData(data);
    }

    public static Armour createRandomArmour()
    {
        if (ALL_ARMOURS.isEmpty())
        {
            throw new IllegalStateException("No armours registered");
        }
        ArmourData data = ALL_ARMOURS.get(random.nextInt(ALL_ARMOURS.size()));
        return createArmourFromData(data);
    }

    public static Armour createRandomArmourByRarity(ItemRarity rarity)
    {
        List<ArmourData> armours = ARMOURS_BY_RARITY.get(rarity);
        if (armours == null || armours.isEmpty())
        {
            throw new IllegalArgumentException("No armours found for rarity: " + rarity);
        }
        ArmourData data = armours.get(random.nextInt(armours.size()));
        return createArmourFromData(data);
    }

    private static Armour createArmourFromData(ArmourData data)
    {
        return new Armour(data.name, data.weight, data.value, data.defense, data.rarity);
    }

    public static List<String> getAllArmourNames()
    {
        return ALL_ARMOURS.stream().map(a -> a.name).toList();
    }

    public static int getArmourCount()
    {
        return ALL_ARMOURS.size();
    }
}
