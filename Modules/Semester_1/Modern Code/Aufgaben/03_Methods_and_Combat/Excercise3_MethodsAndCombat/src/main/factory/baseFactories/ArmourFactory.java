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
        ItemRarity rarity;

        ArmourData(int id, String name, double weight, double value, double defense, ItemRarity rarity)
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
        // Armours from the item list
        registerArmour(1, "Fargoth War Gauntlet", 5.0, 420, 12, ItemRarity.HIGH);
        registerArmour(2, "Null-Field Cloak", 1.5, 480, 8, ItemRarity.HIGH);
        registerArmour(3, "Skymetal Plate", 22.0, 900, 36, ItemRarity.LEGENDARY);
        registerArmour(4, "Ironclad Greaves", 6.0, 140, 10, ItemRarity.LOW);
        registerArmour(5, "Rune-etched Helm", 3.0, 260, 12, ItemRarity.MEDIUM);
        registerArmour(6, "Aegis Mesh Vest", 4.0, 360, 18, ItemRarity.HIGH);
        registerArmour(7, "Shadowstep Boots", 1.8, 420, 6, ItemRarity.HIGH);
        registerArmour(8, "Warden's Mantle", 2.2, 520, 14, ItemRarity.HIGH);
        registerArmour(9, "Patchwork Armour Mk I", 10.0, 95, 6, ItemRarity.LOW);
        
        // Additional armours from the extended list
        registerArmour(10, "Abysswatcher Helm", 2.2, 220, 10, ItemRarity.MEDIUM);
        registerArmour(11, "Shadowweave Coat", 1.6, 280, 8, ItemRarity.HIGH);
        registerArmour(12, "Guardian Frame Mk III", 18.0, 1000, 40, ItemRarity.LEGENDARY);
        registerArmour(13, "Wolfclan Chestplate", 12.0, 500, 24, ItemRarity.HIGH);
        registerArmour(14, "Runic Guard Plate", 9.0, 460, 30, ItemRarity.HIGH);
        registerArmour(15, "Fargoth Barrier Cloak", 2.0, 800, 16, ItemRarity.LEGENDARY);
        registerArmour(16, "Explorer's Webbing", 4.0, 150, 10, ItemRarity.LOW);
        registerArmour(17, "Crimson Vambraces", 2.5, 300, 12, ItemRarity.MEDIUM);
        registerArmour(18, "Clothes", 1.0, 2, 0, ItemRarity.LOW);
    }

    public static Armour createBaseArmour()
    {
        return createArmourByName("Clothes");
    }

    private static void registerArmour(int id, String name, double weight, double value, double defense, ItemRarity rarity)
    {
        ArmourData data = new ArmourData(id, name, weight, value, defense, rarity);
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
