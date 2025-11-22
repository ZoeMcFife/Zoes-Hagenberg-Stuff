package main.factory.baseFactories;

import main.item.ItemRarity;
import main.item.Shield;

import java.util.*;

/**
 * Factory class for creating shield items.
 * Provides methods to create shields by ID, name, rarity, or randomly.
 * Contains 7 predefined shields with various defensive capabilities.
 */
public class ShieldFactory
{
    private static final Map<Integer, ShieldData> SHIELDS_BY_ID = new HashMap<>();
    private static final Map<String, ShieldData> SHIELDS_BY_NAME = new HashMap<>();
    private static final Map<ItemRarity, List<ShieldData>> SHIELDS_BY_RARITY = new EnumMap<>(ItemRarity.class);
    private static final List<ShieldData> ALL_SHIELDS = new ArrayList<>();
    private static final Random random = new Random();

    private static class ShieldData
    {
        int id;
        String name;
        double weight;
        double value;
        double defense;
        ItemRarity rarity;
        int ppGain;

        ShieldData(int id, String name, double weight, double value, double defense, ItemRarity rarity)
        {
            this.id = id;
            this.name = name;
            this.weight = weight;
            this.value = value;
            this.defense = defense;
            this.rarity = rarity;
            this.ppGain = 0;
        }

        ShieldData(int id, String name, double weight, double value, double defense, ItemRarity rarity, int ppGain)
        {
            this.id = id;
            this.name = name;
            this.weight = weight;
            this.value = value;
            this.defense = defense;
            this.rarity = rarity;
            this.ppGain = ppGain;
        }
    }

    static
    {
        // Shields rebalanced by rarity with variable PP gains
        // LOW: defense 5-12, PP gain 10-20
        // MEDIUM: defense 12-20, PP gain 20-35
        // HIGH: defense 20-30, PP gain 35-50
        // LEGENDARY: defense 28-40, PP gain 50-60
        registerShield(1, "Echo Shard Shield", 7.0, 1420, 35, ItemRarity.LEGENDARY, 50);
        registerShield(2, "Stormbreaker Shield", 8.0, 780, 26, ItemRarity.HIGH, 48);
        registerShield(3, "Hollowbone Shield", 3.5, 140, 8, ItemRarity.LOW, 14);
        registerShield(4, "Shield of Wolfram", 9.0, 850, 28, ItemRarity.HIGH, 37);
        registerShield(5, "Patch-Steel Shield", 4.0, 180, 10, ItemRarity.LOW, 15);
        registerShield(6, "Nebular Disk Shield", 5.5, 320, 16, ItemRarity.MEDIUM, 30);
        registerShield(7, "Fists", 0.0, 0, 4, ItemRarity.LOW, 10);
        registerShield(8, "Runewood Buckler", 3.5, 160, 9, ItemRarity.LOW, 17);
        registerShield(9, "Canyon Bulwark", 9.5, 1580, 38, ItemRarity.LEGENDARY, 53);
        registerShield(10, "Smelter Plate", 2.5, 90, 6, ItemRarity.LOW, 14);
        registerShield(11, "Gutter Parry", 8.5, 1520, 36, ItemRarity.LEGENDARY, 59);
        registerShield(12, "Abyssal Aegis", 7.5, 740, 25, ItemRarity.HIGH, 39);
        registerShield(13, "Seaworn Pavise", 8.0, 820, 27, ItemRarity.HIGH, 37);
        registerShield(14, "Beacon Disk", 6.0, 360, 18, ItemRarity.MEDIUM, 20);
        registerShield(15, "Void Shard Guard", 5.5, 340, 16, ItemRarity.MEDIUM, 21);
        registerShield(16, "Echo Resonator", 7.0, 720, 24, ItemRarity.HIGH, 41);
        registerShield(17, "Hearthshield", 6.5, 380, 19, ItemRarity.MEDIUM, 27);
        registerShield(18, "Sporeguard Barrier", 5.0, 300, 15, ItemRarity.MEDIUM, 29);
        registerShield(19, "Marrow Bulwark", 3.0, 120, 7, ItemRarity.LOW, 17);
        registerShield(20, "Nebula Disk MkII", 7.5, 760, 25, ItemRarity.HIGH, 40);
        registerShield(21, "Tollguard Shield", 7.0, 710, 24, ItemRarity.HIGH, 49);
        registerShield(22, "Patch-Steel MkII", 6.5, 370, 18, ItemRarity.MEDIUM, 35);
        registerShield(23, "Smogveil Shield", 5.0, 310, 15, ItemRarity.MEDIUM, 33);
        registerShield(24, "Fargoth Kite", 9.0, 1640, 40, ItemRarity.LEGENDARY, 51);
        registerShield(25, "Warden's Barrier", 5.5, 330, 16, ItemRarity.MEDIUM, 22);
        registerShield(26, "Pilot's Buckle", 6.0, 360, 18, ItemRarity.MEDIUM, 34);
        registerShield(27, "Runic Disk", 7.5, 780, 26, ItemRarity.HIGH, 36);
        registerShield(28, "Wolfclan Heater", 6.5, 370, 18, ItemRarity.MEDIUM, 23);
        registerShield(29, "Shardmesh Shield", 6.5, 380, 19, ItemRarity.MEDIUM, 30);
        registerShield(30, "Gale Disk", 7.0, 730, 24, ItemRarity.HIGH, 37);
        registerShield(31, "Ironrein Buckler", 5.5, 330, 16, ItemRarity.MEDIUM, 28);
        registerShield(32, "Leviathan Scute", 9.5, 1660, 38, ItemRarity.LEGENDARY, 59);
        registerShield(33, "Netherguard", 6.0, 350, 17, ItemRarity.MEDIUM, 34);
        registerShield(34, "Portal Disk", 6.0, 360, 18, ItemRarity.MEDIUM, 31);
        registerShield(35, "Gravewatch Shield", 6.5, 370, 18, ItemRarity.MEDIUM, 28);
        registerShield(36, "Coalface Plate", 7.0, 700, 23, ItemRarity.HIGH, 41);
        registerShield(37, "Harpooner's Guard", 9.0, 1580, 37, ItemRarity.LEGENDARY, 52);
        registerShield(38, "Smuggler's Parrion", 5.5, 340, 16, ItemRarity.MEDIUM, 28);
        registerShield(39, "Tideward Shield", 6.0, 360, 18, ItemRarity.MEDIUM, 34);
        registerShield(40, "Runebone Buckler", 5.5, 330, 16, ItemRarity.MEDIUM, 24);
        registerShield(41, "Abyss Echo Shield", 7.5, 760, 25, ItemRarity.HIGH, 50);
        registerShield(42, "Echofoam Shield", 8.0, 840, 27, ItemRarity.HIGH, 44);
        registerShield(43, "Stormbreaker MkII", 5.0, 300, 15, ItemRarity.MEDIUM, 26);
        registerShield(44, "Cliffside Shield", 5.5, 320, 16, ItemRarity.MEDIUM, 20);
        registerShield(45, "Forgeplate Buckler", 6.5, 380, 19, ItemRarity.MEDIUM, 24);
        registerShield(46, "Hollowbone Tower", 6.0, 360, 18, ItemRarity.MEDIUM, 32);
        registerShield(47, "Seamguard Disk", 7.5, 750, 25, ItemRarity.HIGH, 37);
        registerShield(48, "Shipwright Shield", 7.5, 760, 25, ItemRarity.HIGH, 41);
        registerShield(49, "Sparkshell Ward", 8.0, 820, 27, ItemRarity.HIGH, 37);
        registerShield(50, "Gloomshield", 7.0, 710, 24, ItemRarity.HIGH, 48);
        registerShield(51, "Wolfram Barrier", 6.5, 370, 18, ItemRarity.MEDIUM, 35);
        registerShield(52, "Beacon Circle", 7.5, 770, 26, ItemRarity.HIGH, 40);
        registerShield(53, "Rift Barrier", 6.0, 350, 17, ItemRarity.MEDIUM, 24);
        registerShield(54, "Searplate Shield", 7.5, 750, 25, ItemRarity.HIGH, 46);
        registerShield(55, "Cargo Clamp", 3.0, 110, 7, ItemRarity.LOW, 13);
        registerShield(56, "Skymetal Disk", 8.0, 860, 28, ItemRarity.HIGH, 46);
        registerShield(57, "Runic Pavilion", 8.5, 880, 29, ItemRarity.HIGH, 47);
        registerShield(58, "Voidflame Targe", 5.5, 340, 16, ItemRarity.MEDIUM, 24);
        registerShield(59, "Starforged Shield", 9.0, 1540, 36, ItemRarity.LEGENDARY, 59);
        registerShield(60, "Pilot's Kite", 6.5, 380, 19, ItemRarity.MEDIUM, 24);
        registerShield(61, "Warden's Disc", 6.0, 360, 18, ItemRarity.MEDIUM, 34);
        registerShield(62, "Boneflame Guard", 8.0, 820, 27, ItemRarity.HIGH, 35);
        registerShield(63, "Shade Screen", 7.5, 760, 25, ItemRarity.HIGH, 46);
        registerShield(64, "Ironclad Wall", 5.0, 310, 15, ItemRarity.MEDIUM, 35);
        registerShield(65, "Echo Mesh Shield", 6.0, 360, 18, ItemRarity.MEDIUM, 27);
        registerShield(66, "Tollkeeper Buckler", 2.5, 95, 6, ItemRarity.LOW, 12);
        registerShield(67, "Patchwork Cover", 6.5, 370, 18, ItemRarity.MEDIUM, 28);
        registerShield(68, "Nether Plate", 7.5, 780, 26, ItemRarity.HIGH, 47);
        registerShield(69, "Smokestack Shield", 5.5, 320, 16, ItemRarity.MEDIUM, 26);
        registerShield(70, "Runeforge Ward", 7.5, 770, 26, ItemRarity.HIGH, 50);
        registerShield(71, "Beacon Aegis", 6.0, 360, 18, ItemRarity.MEDIUM, 20);
        registerShield(72, "Coalplate Bulwark", 7.0, 700, 23, ItemRarity.HIGH, 42);
        registerShield(73, "Rune-etched Targe", 7.5, 790, 27, ItemRarity.HIGH, 44);
        registerShield(74, "Seabreak Guard", 8.0, 810, 27, ItemRarity.HIGH, 44);
        registerShield(75, "Gravplate", 6.5, 370, 18, ItemRarity.MEDIUM, 27);
        registerShield(76, "Hearth Disk", 8.0, 840, 28, ItemRarity.HIGH, 40);
        registerShield(77, "Stalwart Shield", 5.0, 310, 15, ItemRarity.MEDIUM, 29);
        registerShield(78, "Spiral Guard", 7.5, 780, 26, ItemRarity.HIGH, 42);
        registerShield(79, "Voidglass Targe", 9.5, 1620, 39, ItemRarity.LEGENDARY, 51);
        registerShield(80, "Galeguard", 5.5, 330, 16, ItemRarity.MEDIUM, 26);
        registerShield(81, "Riftsteel Disk", 6.0, 360, 18, ItemRarity.MEDIUM, 21);
        registerShield(82, "Forgeborn Shield", 7.0, 710, 24, ItemRarity.HIGH, 43);
        registerShield(83, "Mariner's Shield", 8.0, 820, 27, ItemRarity.HIGH, 42);
        registerShield(84, "Rustwarden Disk", 7.5, 790, 26, ItemRarity.HIGH, 36);
        registerShield(85, "Spore Curtain", 5.5, 320, 16, ItemRarity.MEDIUM, 21);
        registerShield(86, "Hoarder's Buckler", 7.5, 790, 26, ItemRarity.HIGH, 42);
        registerShield(87, "Beacon Pavise", 5.5, 320, 16, ItemRarity.MEDIUM, 28);
        registerShield(88, "Lionheart Guard", 6.0, 360, 18, ItemRarity.MEDIUM, 33);
        registerShield(89, "Steelcrest", 5.5, 340, 17, ItemRarity.MEDIUM, 30);
        registerShield(90, "Aegis of Eternity", 9.0, 1560, 37, ItemRarity.LEGENDARY, 56);
        registerShield(91, "Stormwall", 7.0, 710, 24, ItemRarity.HIGH, 50);
        registerShield(92, "Titan's Bulwark", 8.0, 850, 28, ItemRarity.HIGH, 50);
        registerShield(93, "Iron Sentinel", 7.5, 770, 25, ItemRarity.HIGH, 46);
        registerShield(94, "Shadow Guard", 6.5, 380, 19, ItemRarity.MEDIUM, 33);
        registerShield(95, "Bronze Bastion", 5.0, 300, 14, ItemRarity.MEDIUM, 22);
        registerShield(96, "Ravencrest", 5.0, 310, 15, ItemRarity.MEDIUM, 26);
        registerShield(97, "Falcon Shield", 6.5, 370, 18, ItemRarity.MEDIUM, 20);
        registerShield(98, "Traveler's Buckler", 3.5, 130, 8, ItemRarity.LOW, 11);
        registerShield(99, "Celestial Aegis", 9.0, 1600, 38, ItemRarity.LEGENDARY, 58);
        registerShield(100, "Dragonplate", 7.5, 760, 25, ItemRarity.HIGH, 50);

    }


    public static Shield createBaseShield()
    {
        return createShieldByName("Fists");
    }

    private static void registerShield(int id, String name, double weight, double value, double defense, ItemRarity rarity)
    {
        ShieldData data = new ShieldData(id, name, weight, value, defense, rarity);
        SHIELDS_BY_ID.put(id, data);
        SHIELDS_BY_NAME.put(name.toLowerCase(), data);
        SHIELDS_BY_RARITY.computeIfAbsent(rarity, k -> new ArrayList<>()).add(data);
        ALL_SHIELDS.add(data);
    }

    private static void registerShield(int id, String name, double weight, double value, double defense, ItemRarity rarity, int ppGain)
    {
        ShieldData data = new ShieldData(id, name, weight, value, defense, rarity, ppGain);
        SHIELDS_BY_ID.put(id, data);
        SHIELDS_BY_NAME.put(name.toLowerCase(), data);
        SHIELDS_BY_RARITY.computeIfAbsent(rarity, k -> new ArrayList<>()).add(data);
        ALL_SHIELDS.add(data);
    }

    public static Shield createShieldById(int id)
    {
        ShieldData data = SHIELDS_BY_ID.get(id);
        if (data == null)
        {
            throw new IllegalArgumentException("Shield with ID " + id + " not found");
        }
        return createShieldFromData(data);
    }

    public static Shield createShieldByName(String name)
    {
        ShieldData data = SHIELDS_BY_NAME.get(name.toLowerCase());
        if (data == null)
        {
            throw new IllegalArgumentException("Shield with name '" + name + "' not found");
        }
        return createShieldFromData(data);
    }

    public static Shield createRandomShield()
    {
        if (ALL_SHIELDS.isEmpty())
        {
            throw new IllegalStateException("No shields registered");
        }
        ShieldData data = ALL_SHIELDS.get(random.nextInt(ALL_SHIELDS.size()));
        return createShieldFromData(data);
    }

    public static Shield createRandomShieldByRarity(ItemRarity rarity)
    {
        List<ShieldData> shields = SHIELDS_BY_RARITY.get(rarity);
        if (shields == null || shields.isEmpty())
        {
            throw new IllegalArgumentException("No shields found for rarity: " + rarity);
        }
        ShieldData data = shields.get(random.nextInt(shields.size()));
        return createShieldFromData(data);
    }

    private static Shield createShieldFromData(ShieldData data)
    {
        return new Shield(data.name, data.weight, data.value, data.defense, data.rarity, data.ppGain);
    }

    public static List<String> getAllShieldNames()
    {
        return ALL_SHIELDS.stream().map(s -> s.name).toList();
    }

    public static int getShieldCount()
    {
        return ALL_SHIELDS.size();
    }
}
