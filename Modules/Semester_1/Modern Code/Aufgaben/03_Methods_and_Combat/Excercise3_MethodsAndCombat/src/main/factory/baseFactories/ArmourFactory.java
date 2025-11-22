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
        registerArmour(1, "Fargoth War Gauntlet", 5.0, 420, 12, 500, ItemRarity.HIGH);
        registerArmour(2, "Null-Field Cloak", 1.5, 480, 8, 600, ItemRarity.HIGH);
        registerArmour(3, "Skymetal Plate", 22.0, 900, 36, 1500, ItemRarity.LEGENDARY);
        registerArmour(4, "Ironclad Greaves", 6.0, 140, 10, 100, ItemRarity.LOW);
        registerArmour(5, "Rune-etched Helm", 3.0, 260, 12, 400, ItemRarity.MEDIUM);
        registerArmour(6, "Aegis Mesh Vest", 4.0, 360, 18, 750, ItemRarity.HIGH);
        registerArmour(7, "Shadowstep Boots", 1.8, 420, 6, 450, ItemRarity.HIGH);
        registerArmour(8, "Warden's Mantle", 2.2, 520, 14, 500, ItemRarity.HIGH);
        registerArmour(9, "Patchwork Armour Mk I", 10.0, 95, 6, 180, ItemRarity.LOW);
        registerArmour(10, "Abysswatcher Helm", 2.2, 220, 10, 450, ItemRarity.MEDIUM);
        registerArmour(11, "Shadowweave Coat", 1.6, 280, 8, 900, ItemRarity.HIGH);
        registerArmour(12, "Guardian Frame Mk III", 18.0, 1000, 40, 1750, ItemRarity.LEGENDARY);
        registerArmour(13, "Wolfclan Chestplate", 12.0, 500, 24, 180, ItemRarity.HIGH);
        registerArmour(14, "Runic Guard Plate", 9.0, 460, 30, 800, ItemRarity.HIGH);
        registerArmour(15, "Fargoth Barrier Cloak", 2.0, 800, 16, 2000, ItemRarity.LEGENDARY);
        registerArmour(16, "Explorer's Webbing", 4.0, 150, 10, 150, ItemRarity.LOW);
        registerArmour(17, "Crimson Vambraces", 2.5, 300, 12, 450, ItemRarity.MEDIUM);
        registerArmour(18, "Clothes", 1.0, 2, 0, 50, ItemRarity.LOW);
        registerArmour(19, "Coalminer Vest", 7.9, 78, 11, 80, ItemRarity.LOW);
        registerArmour(20, "Fey-silk Coat", 12.3, 721, 20, 575, ItemRarity.HIGH);
        registerArmour(21, "Runebound Plate", 16.4, 5715, 49, 3071, ItemRarity.LEGENDARY);
        registerArmour(22, "Tunnelweave Harness", 5.2, 89, 17, 67, ItemRarity.MEDIUM);
        registerArmour(23, "Smelter Apron", 18.3, 1296, 40, 1047, ItemRarity.HIGH);
        registerArmour(24, "Gutterhide Jacket", 3.1, 270, 17, 88, ItemRarity.MEDIUM);
        registerArmour(25, "Cindermail", 7.5, 765, 29, 536, ItemRarity.HIGH);
        registerArmour(26, "Void-forged Cuirass", 27.7, 3156, 76, 3068, ItemRarity.LEGENDARY);
        registerArmour(27, "Seamrunner Jacket", 6.4, 42, 9, 240, ItemRarity.MEDIUM);
        registerArmour(28, "Nebula Overcoat", 17.7, 1390, 45, 1835, ItemRarity.HIGH);
        registerArmour(29, "Echo Mesh", 3.5, 714, 30, 489, ItemRarity.MEDIUM);
        registerArmour(30, "Hearthguard Mail", 13.3, 1677, 38, 531, ItemRarity.HIGH);
        registerArmour(31, "Sporeguard Robes", 11.5, 358, 14, 630, ItemRarity.HIGH);
        registerArmour(32, "Marrow Plate", 5.8, 61, 17, 134, ItemRarity.MEDIUM);
        registerArmour(33, "Gravel Shielding", 23.3, 5197, 74, 1647, ItemRarity.LEGENDARY);
        registerArmour(34, "Tinkerwright Gear", 6.1, 900, 21, 382, ItemRarity.MEDIUM);
        registerArmour(35, "Ironbark Vest", 20.1, 1521, 40, 1537, ItemRarity.HIGH);
        registerArmour(36, "Sirenscale Jacket", 3.9, 82, 8, 88, ItemRarity.LOW);
        registerArmour(37, "Abysswatch Cloak", 8.2, 1864, 38, 465, ItemRarity.HIGH);
        registerArmour(38, "Blinkweave Vest", 5.6, 337, 12, 166, ItemRarity.MEDIUM);
        registerArmour(39, "Starforged Mail", 17.0, 1776, 27, 1831, ItemRarity.HIGH);
        registerArmour(40, "Runic Workplate", 16.1, 3723, 37, 1392, ItemRarity.LEGENDARY);
        registerArmour(41, "Forgeplate Greaves", 6.6, 518, 20, 167, ItemRarity.MEDIUM);
        registerArmour(42, "Hollowbone Greaves", 2.4, 127, 6, 57, ItemRarity.MEDIUM);
        registerArmour(43, "Smogproof Tunic", 16.8, 1663, 35, 173, ItemRarity.HIGH);
        registerArmour(44, "Warden's Pauldrons", 10.0, 911, 29, 309, ItemRarity.HIGH);
        registerArmour(45, "Coalplate Breast", 15.5, 1094, 30, 1698, ItemRarity.LEGENDARY);
        registerArmour(46, "Mariner's Jerkin", 14.2, 435, 16, 648, ItemRarity.HIGH);
        registerArmour(47, "Rustwarden Hauberk", 5.6, 225, 16, 106, ItemRarity.MEDIUM);
        registerArmour(48, "Voidward Girdle", 27.9, 3998, 61, 1903, ItemRarity.LEGENDARY);
        registerArmour(49, "Galeweave Shirt", 7.0, 590, 29, 307, ItemRarity.HIGH);
        registerArmour(50, "Stonebound Hauberk", 6.6, 899, 15, 500, ItemRarity.MEDIUM);
        registerArmour(51, "Runeweave Gloves", 10.6, 278, 10, 54, ItemRarity.MEDIUM);
        registerArmour(52, "Tidecaller's Wrap", 11.3, 127, 10, 601, ItemRarity.MEDIUM);
        registerArmour(53, "Graveyard Shroud", 6.0, 298, 17, 416, ItemRarity.MEDIUM);
        registerArmour(54, "Smuggler's Leathers", 7.9, 912, 43, 1665, ItemRarity.LEGENDARY);
        registerArmour(55, "Harpooner's Coat", 10.9, 560, 14, 118, ItemRarity.MEDIUM);
        registerArmour(56, "Brineproof Suit", 11.9, 1908, 36, 1275, ItemRarity.HIGH);
        registerArmour(57, "Rift-stitched Overalls", 17.9, 1038, 35, 850, ItemRarity.HIGH);
        registerArmour(58, "Shardbound Vest", 12.7, 671, 44, 708, ItemRarity.HIGH);
        registerArmour(59, "Gloomcloak", 5.5, 746, 30, 534, ItemRarity.MEDIUM);
        registerArmour(60, "Pilot's Armor", 17.9, 2507, 42, 229, ItemRarity.LEGENDARY);
        registerArmour(61, "Engineer Apron MkII", 12.1, 402, 22, 405, ItemRarity.MEDIUM);
        registerArmour(62, "Sparksmith Jacket", 6.7, 1083, 25, 842, ItemRarity.HIGH);
        registerArmour(63, "Runic Keeper Plate", 9.3, 1817, 23, 1007, ItemRarity.HIGH);
        registerArmour(64, "Wolfclan Greaves MkII", 19.6, 542, 47, 1530, ItemRarity.LEGENDARY);
        registerArmour(65, "Wolfram Helm", 3.3, 852, 38, 888, ItemRarity.HIGH);
        registerArmour(66, "Seabreak Helm", 3.8, 100, 11, 228, ItemRarity.MEDIUM);
        registerArmour(67, "Beacon Harness", 9.5, 1119, 33, 631, ItemRarity.HIGH);
        registerArmour(68, "Abyssal Mantle", 13.7, 2876, 44, 1417, ItemRarity.LEGENDARY);
        registerArmour(69, "Coal-smelter Helm", 28.7, 1271, 58, 3037, ItemRarity.LEGENDARY);
        registerArmour(70, "Echo Guard Chest", 9.4, 358, 17, 325, ItemRarity.MEDIUM);
        registerArmour(71, "Netherweave Vest", 12.1, 1005, 39, 1038, ItemRarity.HIGH);
        registerArmour(72, "Protectorate Harness", 9.2, 1201, 43, 1120, ItemRarity.HIGH);
        registerArmour(73, "Fargoth Platelet", 3.9, 748, 29, 349, ItemRarity.MEDIUM);
        registerArmour(74, "Gateway Harness", 20.3, 4548, 56, 1521, ItemRarity.LEGENDARY);
        registerArmour(75, "Portalkeeper Shawl", 9.4, 509, 16, 243, ItemRarity.HIGH);
        registerArmour(76, "Shadecloak", 19.9, 1719, 64, 1432, ItemRarity.LEGENDARY);
        registerArmour(77, "Runic Ceremonial Coat", 8.7, 122, 11, 174, ItemRarity.MEDIUM);
        registerArmour(78, "Voidsteel Brigandine", 4.6, 376, 12, 174, ItemRarity.MEDIUM);
        registerArmour(79, "Starhelm", 30.0, 2636, 62, 2920, ItemRarity.LEGENDARY);
        registerArmour(80, "Golem Harness", 5.4, 178, 7, 64, ItemRarity.MEDIUM);
        registerArmour(81, "Ironbender Waist", 18.6, 2455, 62, 1705, ItemRarity.LEGENDARY);
        registerArmour(82, "Dustward Tunic", 8.8, 606, 27, 335, ItemRarity.HIGH);
        registerArmour(83, "Spirit-etched Mesh", 8.2, 169, 9, 116, ItemRarity.MEDIUM);
        registerArmour(84, "Cargo Handler Vest", 18.9, 1346, 40, 1376, ItemRarity.HIGH);
        registerArmour(85, "Leviathan-hide Coat", 7.8, 215, 12, 77, ItemRarity.MEDIUM);
        registerArmour(86, "Riftsteel Mail", 4.7, 212, 10, 166, ItemRarity.MEDIUM);
        registerArmour(87, "Tollkeeper Vest", 3.2, 186, 16, 195, ItemRarity.LOW);
        registerArmour(88, "Forgeborn Girdle", 9.9, 2145, 43, 601, ItemRarity.HIGH);
        registerArmour(89, "Beacon Plate", 5.6, 268, 13, 163, ItemRarity.MEDIUM);
        registerArmour(90, "Rundown Vest", 6.3, 78, 9, 160, ItemRarity.LOW);
        registerArmour(91, "Patchwork MkII", 5.7, 269, 12, 220, ItemRarity.MEDIUM);
        registerArmour(92, "Sootguard Cloak", 3.8, 48, 4, 102, ItemRarity.MEDIUM);
        registerArmour(93, "Variant Armor 75", 25.8, 3293, 58, 2843, ItemRarity.LEGENDARY);
        registerArmour(94, "Variant Armor 76", 4.5, 240, 6, 83, ItemRarity.MEDIUM);
        registerArmour(95, "Variant Armor 77", 6.0, 772, 23, 223, ItemRarity.HIGH);
        registerArmour(96, "Variant Armor 78", 8.5, 638, 38, 659, ItemRarity.LEGENDARY);
        registerArmour(97, "Variant Armor 79", 10.5, 855, 25, 446, ItemRarity.MEDIUM);
        registerArmour(98, "Variant Armor 80", 15.6, 375, 26, 520, ItemRarity.MEDIUM);
        registerArmour(99, "Variant Armor 81", 4.4, 646, 30, 560, ItemRarity.MEDIUM);
        registerArmour(100, "Variant Armor 82", 7.0, 547, 19, 491, ItemRarity.MEDIUM);

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
