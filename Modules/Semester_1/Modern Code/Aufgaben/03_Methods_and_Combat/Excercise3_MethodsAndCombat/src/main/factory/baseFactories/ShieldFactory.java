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
