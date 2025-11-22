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

        ShieldData(int id, String name, double weight, double value, double defense, ItemRarity rarity)
        {
            this.id = id;
            this.name = name;
            this.weight = weight;
            this.value = value;
            this.defense = defense;
            this.rarity = rarity;
        }
    }

    static
    {
        // Shields from the item list
        registerShield(1, "Echo Shard Shield", 6.5, 700, 28, ItemRarity.LEGENDARY);
        registerShield(2, "Stormbreaker Shield", 7.8, 520, 22, ItemRarity.HIGH);
        registerShield(3, "Hollowbone Shield", 4.5, 95, 8, ItemRarity.LOW);
        registerShield(4, "Shield of Wolfram", 9.0, 640, 30, ItemRarity.HIGH);
        
        // Additional shields from the extended list
        registerShield(5, "Patch-Steel Shield", 5.0, 120, 12, ItemRarity.LOW);
        registerShield(6, "Nebular Disk Shield", 3.5, 340, 18, ItemRarity.MEDIUM);
        registerShield(7, "Fists", 0.0, 0, 1, ItemRarity.LOW);

        // --- SHIELDS 8-100 (adds 93, bringing total to 100) ---
        registerShield(8, "Runewood Buckler", 4.0, 140, 10, ItemRarity.LOW);
        registerShield(9, "Canyon Bulwark", 11.5, 6487, 49, ItemRarity.LEGENDARY);
        registerShield(10, "Smelter Plate", 1.3, 55, 4, ItemRarity.LOW);
        registerShield(11, "Gutter Parry", 9.9, 662, 44, ItemRarity.LEGENDARY);
        registerShield(12, "Abyssal Aegis", 6.9, 673, 24, ItemRarity.HIGH);
        registerShield(13, "Seaworn Pavise", 9.8, 609, 34, ItemRarity.HIGH);
        registerShield(14, "Beacon Disk", 7.4, 361, 11, ItemRarity.HIGH);
        registerShield(15, "Void Shard Guard", 5.2, 542, 11, ItemRarity.MEDIUM);
        registerShield(16, "Echo Resonator", 5.0, 740, 12, ItemRarity.HIGH);
        registerShield(17, "Hearthshield", 6.9, 122, 24, ItemRarity.MEDIUM);
        registerShield(18, "Sporeguard Barrier", 3.1, 43, 2, ItemRarity.MEDIUM);
        registerShield(19, "Marrow Bulwark", 5.4, 92, 13, ItemRarity.LOW);
        registerShield(20, "Nebula Disk MkII", 4.2, 333, 24, ItemRarity.HIGH);
        registerShield(21, "Tollguard Shield", 7.3, 452, 21, ItemRarity.HIGH);
        registerShield(22, "Patch-Steel MkII", 8.4, 692, 11, ItemRarity.HIGH);
        registerShield(23, "Smogveil Shield", 1.0, 178, 8, ItemRarity.MEDIUM);
        registerShield(24, "Fargoth Kite", 8.3, 1189, 42, ItemRarity.LEGENDARY);
        registerShield(25, "Warden's Barrier", 0.6, 189, 6, ItemRarity.MEDIUM);
        registerShield(26, "Pilot's Buckle", 8.3, 197, 14, ItemRarity.MEDIUM);
        registerShield(27, "Runic Disk", 5.8, 432, 26, ItemRarity.HIGH);
        registerShield(28, "Wolfclan Heater", 6.0, 215, 14, ItemRarity.MEDIUM);
        registerShield(29, "Shardmesh Shield", 8.2, 451, 19, ItemRarity.MEDIUM);
        registerShield(30, "Gale Disk", 3.9, 717, 22, ItemRarity.HIGH);
        registerShield(31, "Ironrein Buckler", 1.4, 255, 9, ItemRarity.MEDIUM);
        registerShield(32, "Leviathan Scute", 11.6, 1569, 34, ItemRarity.LEGENDARY);
        registerShield(33, "Netherguard", 8.4, 131, 11, ItemRarity.MEDIUM);
        registerShield(34, "Portal Disk", 6.7, 130, 13, ItemRarity.MEDIUM);
        registerShield(35, "Gravewatch Shield", 7.0, 528, 12, ItemRarity.MEDIUM);
        registerShield(36, "Coalface Plate", 4.1, 702, 18, ItemRarity.HIGH);
        registerShield(37, "Harpooner's Guard", 10.9, 922, 42, ItemRarity.LEGENDARY);
        registerShield(38, "Smuggler's Parrion", 4.0, 514, 11, ItemRarity.MEDIUM);
        registerShield(39, "Tideward Shield", 4.8, 222, 15, ItemRarity.MEDIUM);
        registerShield(40, "Runebone Buckler", 5.4, 253, 11, ItemRarity.MEDIUM);
        registerShield(41, "Abyss Echo Shield", 6.7, 595, 21, ItemRarity.HIGH);
        registerShield(42, "Echofoam Shield", 9.4, 984, 32, ItemRarity.HIGH);
        registerShield(43, "Stormbreaker MkII", 2.9, 124, 5, ItemRarity.MEDIUM);
        registerShield(44, "Cliffside Shield", 7.2, 111, 10, ItemRarity.MEDIUM);
        registerShield(45, "Forgeplate Buckler", 10.9, 340, 19, ItemRarity.MEDIUM);
        registerShield(46, "Hollowbone Tower", 7.8, 394, 12, ItemRarity.MEDIUM);
        registerShield(47, "Seamguard Disk", 7.7, 379, 20, ItemRarity.HIGH);
        registerShield(48, "Shipwright Shield", 4.6, 539, 21, ItemRarity.HIGH);
        registerShield(49, "Sparkshell Ward", 6.1, 759, 34, ItemRarity.HIGH);
        registerShield(50, "Gloomshield", 8.2, 463, 20, ItemRarity.MEDIUM);
        registerShield(51, "Wolfram Barrier", 5.2, 435, 15, ItemRarity.MEDIUM);
        registerShield(52, "Beacon Circle", 5.9, 453, 24, ItemRarity.HIGH);
        registerShield(53, "Rift Barrier", 5.3, 541, 12, ItemRarity.MEDIUM);
        registerShield(54, "Searplate Shield", 8.1, 1177, 22, ItemRarity.HIGH);
        registerShield(55, "Cargo Clamp", 6.9, 58, 5, ItemRarity.LOW);
        registerShield(56, "Skymetal Disk", 6.2, 919, 35, ItemRarity.HIGH);
        registerShield(57, "Runic Pavilion", 8.0, 764, 40, ItemRarity.HIGH);
        registerShield(58, "Voidflame Targe", 3.1, 391, 12, ItemRarity.MEDIUM);
        registerShield(59, "Starforged Shield", 1.2, 1254, 23, ItemRarity.LEGENDARY);
        registerShield(60, "Pilot's Kite", 8.5, 235, 24, ItemRarity.MEDIUM);
        registerShield(61, "Warden's Disc", 8.6, 532, 12, ItemRarity.MEDIUM);
        registerShield(62, "Boneflame Guard", 10.3, 501, 32, ItemRarity.HIGH);
        registerShield(63, "Shade Screen", 7.9, 1136, 21, ItemRarity.HIGH);
        registerShield(64, "Ironclad Wall", 2.7, 128, 6, ItemRarity.MEDIUM);
        registerShield(65, "Echo Mesh Shield", 3.6, 595, 12, ItemRarity.MEDIUM);
        registerShield(66, "Tollkeeper Buckler", 1.0, 97, 4, ItemRarity.LOW);
        registerShield(67, "Patchwork Cover", 6.6, 276, 18, ItemRarity.MEDIUM);
        registerShield(68, "Nether Plate", 9.2, 1016, 23, ItemRarity.HIGH);
        registerShield(69, "Smokestack Shield", 6.2, 100, 7, ItemRarity.MEDIUM);
        registerShield(70, "Runeforge Ward", 8.3, 462, 24, ItemRarity.HIGH);
        registerShield(71, "Beacon Aegis", 4.5, 295, 14, ItemRarity.MEDIUM);
        registerShield(72, "Coalplate Bulwark", 8.1, 919, 19, ItemRarity.MEDIUM);
        registerShield(73, "Rune-etched Targe", 7.6, 731, 27, ItemRarity.HIGH);
        registerShield(74, "Seabreak Guard", 7.7, 677, 29, ItemRarity.HIGH);
        registerShield(75, "Gravplate", 3.7, 460, 15, ItemRarity.MEDIUM);
        registerShield(76, "Hearth Disk", 10.7, 1089, 29, ItemRarity.HIGH);
        registerShield(77, "Stalwart Shield", 5.9, 133, 6, ItemRarity.MEDIUM);
        registerShield(78, "Spiral Guard", 4.6, 397, 25, ItemRarity.HIGH);
        registerShield(79, "Voidglass Targe", 7.2, 1173, 44, ItemRarity.LEGENDARY);
        registerShield(80, "Galeguard", 3.5, 201, 11, ItemRarity.MEDIUM);
        registerShield(81, "Riftsteel Disk", 7.2, 363, 14, ItemRarity.MEDIUM);
        registerShield(82, "Forgeborn Shield", 5.5, 533, 22, ItemRarity.MEDIUM);
        registerShield(83, "Mariner's Shield", 1.6, 916, 34, ItemRarity.HIGH);
        registerShield(84, "Rustwarden Disk", 9.1, 548, 26, ItemRarity.HIGH);
        registerShield(85, "Spore Curtain", 3.2, 97, 10, ItemRarity.MEDIUM);
        registerShield(86, "Hoarder's Buckler", 9.1, 548, 26, ItemRarity.HIGH);
        registerShield(87, "Beacon Pavise", 3.2, 97, 10, ItemRarity.MEDIUM);
        registerShield(88, "Variant Shield 1", 9.9, 212, 12, ItemRarity.MEDIUM);
        registerShield(89, "Variant Shield 82", 6.0, 189, 9, ItemRarity.MEDIUM);
        registerShield(90, "Variant Shield 83", 10.9, 451, 24, ItemRarity.LEGENDARY);
        registerShield(91, "Variant Shield 84", 6.1, 609, 21, ItemRarity.MEDIUM);
        registerShield(92, "Variant Shield 85", 10.7, 1610, 38, ItemRarity.HIGH);
        registerShield(93, "Variant Shield 86", 6.8, 1329, 21, ItemRarity.HIGH);
        registerShield(94, "Variant Shield 87", 5.1, 245, 20, ItemRarity.MEDIUM);
        registerShield(95, "Variant Shield 88", 5.7, 77, 3, ItemRarity.MEDIUM);
        registerShield(96, "Variant Shield 89", 5.8, 75, 4, ItemRarity.MEDIUM);
        registerShield(97, "Variant Shield 90", 3.7, 736, 15, ItemRarity.MEDIUM);
        registerShield(98, "Variant Shield 91", 3.9, 159, 7, ItemRarity.LOW);
        registerShield(99, "Variant Shield 92", 6.2, 393, 40, ItemRarity.LEGENDARY);
        registerShield(100, "Variant Shield 93", 8.4, 506, 10, ItemRarity.HIGH);

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
        return new Shield(data.name, data.weight, data.value, data.defense, data.rarity);
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
