package main.factory.baseFactories;

import main.item.HealingPotion;
import main.item.ItemRarity;

import java.util.*;

/**
 * Factory class for creating healing potion items.
 * Provides methods to create potions by ID, name, rarity, or randomly.
 * Contains 21 predefined healing potions with various healing amounts.
 */
public class HealingPotionFactory
{
    private static final Map<Integer, PotionData> POTIONS_BY_ID = new HashMap<>();
    private static final Map<String, PotionData> POTIONS_BY_NAME = new HashMap<>();
    private static final List<PotionData> ALL_POTIONS = new ArrayList<>();
    private static final Random random = new Random();

    private static class PotionData
    {
        int id;
        String name;
        double weight;
        double value;
        double healingAmount;
        ItemRarity rarity;

        PotionData(int id, String name, double weight, double value, double healingAmount, ItemRarity rarity)
        {
            this.id = id;
            this.name = name;
            this.weight = weight;
            this.value = value;
            this.healingAmount = healingAmount;
            this.rarity = rarity;
        }
    }

    static
    {
        // Healing items from the list
        registerPotion(1, "Small Health Potion",         1.2, 30, 35, ItemRarity.LOW);
        registerPotion(2, "Minor Healing Salve",         1.0, 35, 45, ItemRarity.LOW);
        registerPotion(3, "Bandage Roll",                0.8, 20, 30, ItemRarity.LOW);
        registerPotion(4, "Herbal Tonic",                1.6, 45, 60, ItemRarity.MEDIUM);
        registerPotion(5, "Stitching Kit",               0.9, 20, 25, ItemRarity.LOW);
        registerPotion(6, "Redleaf Brew",                1.7, 55, 70, ItemRarity.MEDIUM);
        registerPotion(7, "Honey Balm",                  1.1, 30, 40, ItemRarity.LOW);
        registerPotion(8, "Freshwater Flask",            2.0, 20, 30, ItemRarity.LOW);
        registerPotion(9, "Healing Elixir",              1.8, 60, 75, ItemRarity.MEDIUM);
        registerPotion(10, "Small Med-Pack",             1.4, 35, 45, ItemRarity.LOW);
        registerPotion(11, "Revitalizing Tea",           1.0, 28, 35, ItemRarity.LOW);
        registerPotion(12, "Soothing Gel",               1.5, 50, 65, ItemRarity.MEDIUM);
        registerPotion(13, "Quick Patch",                0.7, 22, 30, ItemRarity.LOW);
        registerPotion(14, "Restorative Drop",           1.3, 55, 70, ItemRarity.MEDIUM);
        registerPotion(15, "Minor Lifeforce Potion",     1.2, 32, 40, ItemRarity.LOW);
        registerPotion(16, "Herb Poultice",              1.0, 35, 45, ItemRarity.LOW);
        registerPotion(17, "Healing Dust",               0.5, 15, 20, ItemRarity.LOW);
        registerPotion(18, "Warm Compress",              1.3, 45, 60, ItemRarity.MEDIUM);
        registerPotion(19, "Small Vitality Vial",        0.9, 30, 38, ItemRarity.LOW);
        registerPotion(20, "Emergency Tonic",            2.2, 80, 95, ItemRarity.HIGH);
        registerPotion(21, "Protectorate Med-Patch",     0.6, 65, 75, ItemRarity.LOW);
        registerPotion(22, "Brand X Elixir",             3.0, 5000, 300, ItemRarity.LEGENDARY);
        registerPotion(23, "Lesser Vitality Draught",     1.0, 25, 35, ItemRarity.LOW);
        registerPotion(24, "Refreshing Decoction",        1.2, 28, 38, ItemRarity.LOW);
        registerPotion(25, "Mender's Tonic",             1.3, 30, 40, ItemRarity.LOW);
        registerPotion(26, "Soothing Infusion",          1.5, 35, 45, ItemRarity.MEDIUM);
        registerPotion(27, "Vital Essence",              1.6, 40, 50, ItemRarity.MEDIUM);
        registerPotion(28, "Healing Serum",              1.8, 45, 60, ItemRarity.MEDIUM);
        registerPotion(29, "Fortifying Brew",            2.0, 50, 65, ItemRarity.MEDIUM);
        registerPotion(30, "Rejuvenating Extract",       2.2, 55, 70, ItemRarity.MEDIUM);
        registerPotion(31, "Invigorating Elixir",        2.5, 60, 75, ItemRarity.HIGH);
        registerPotion(32, "Restorative Mixture",        2.7, 65, 80, ItemRarity.HIGH);
        registerPotion(33, "Guardian's Tonic",           3.0, 70, 85, ItemRarity.HIGH);
        registerPotion(34, "Celestial Draught",          3.2, 75, 90, ItemRarity.HIGH);
        registerPotion(35, "Mystic Infusion",            3.5, 80, 100, ItemRarity.LEGENDARY);
        registerPotion(36, "Ethereal Brew",              3.8, 85, 105, ItemRarity.LEGENDARY);
        registerPotion(37, "Astral Elixir",              4.0, 90, 110, ItemRarity.LEGENDARY);
        registerPotion(38, "Supreme Vitality Potion",    4.2, 95, 115, ItemRarity.LEGENDARY);
        registerPotion(39, "Primordial Tonic",           4.5, 100, 120, ItemRarity.LEGENDARY);
        registerPotion(40, "Arcane Health Vial",         4.8, 110, 130, ItemRarity.LEGENDARY);
        registerPotion(41, "Healing Infusion",           1.1, 26, 36, ItemRarity.LOW);
        registerPotion(42, "Soothing Draught",           1.3, 30, 40, ItemRarity.LOW);
        registerPotion(43, "Mender’s Brew",              1.4, 32, 42, ItemRarity.LOW);
        registerPotion(44, "Revitalizing Tonic",         1.6, 36, 46, ItemRarity.MEDIUM);
        registerPotion(45, "Potent Herbal Extract",      1.7, 40, 50, ItemRarity.MEDIUM);
        registerPotion(46, "Enchanted Healing Salve",    1.9, 45, 55, ItemRarity.MEDIUM);
        registerPotion(47, "Life Infusion",              2.1, 50, 60, ItemRarity.MEDIUM);
        registerPotion(48, "Greater Vitality Potion",    2.3, 55, 65, ItemRarity.HIGH);
        registerPotion(49, "Elixir of Recovery",         2.6, 60, 70, ItemRarity.HIGH);
        registerPotion(50, "Phoenix Feather Tonic",      2.8, 65, 75, ItemRarity.HIGH);
        registerPotion(51, "Regeneration Draught",       3.0, 70, 80, ItemRarity.HIGH);
        registerPotion(52, "Titan’s Brew",               3.3, 75, 85, ItemRarity.HIGH);
        registerPotion(53, "Divine Restorative",         3.5, 80, 90, ItemRarity.LEGENDARY);
        registerPotion(54, "Elixir of Eternal Health",   3.8, 90, 100, ItemRarity.LEGENDARY);
        registerPotion(55, "Mythic Healing Salve",       4.0, 95, 105, ItemRarity.LEGENDARY);
        registerPotion(56, "Lifeblood Potion",           4.2, 100, 110, ItemRarity.LEGENDARY);
        registerPotion(57, "Astral Vitality Draught",    4.5, 110, 120, ItemRarity.LEGENDARY);
        registerPotion(58, "Elder's Healing Elixir",     4.8, 120, 135, ItemRarity.LEGENDARY);
        registerPotion(59, "Primordial Restoration",     5.0, 130, 145, ItemRarity.LEGENDARY);
        registerPotion(60, "Supreme Health Vial",        5.2, 140, 155, ItemRarity.LEGENDARY);
        registerPotion(61, "Lesser Restoration Potion",  1.2, 28, 38, ItemRarity.LOW);
        registerPotion(62, "Minor Revitalization",       1.3, 30, 40, ItemRarity.LOW);
        registerPotion(63, "Healing Brew",               1.4, 32, 42, ItemRarity.LOW);
        registerPotion(64, "Vitality Draught",           1.6, 36, 46, ItemRarity.MEDIUM);
        registerPotion(65, "Greater Healing Tonic",      1.8, 42, 52, ItemRarity.MEDIUM);
        registerPotion(66, "Potent Life Essence",        2.0, 48, 58, ItemRarity.MEDIUM);
        registerPotion(67, "Regenerative Elixir",       2.2, 52, 62, ItemRarity.HIGH);
        registerPotion(68, "Revival Brew",               2.4, 58, 68, ItemRarity.HIGH);
        registerPotion(69, "Lifeforce Tonic",            2.6, 60, 70, ItemRarity.HIGH);
        registerPotion(70, "Ultimate Vitality Potion",   2.8, 65, 75, ItemRarity.HIGH);
        registerPotion(71, "Ethereal Restoration",       3.0, 70, 145, ItemRarity.LEGENDARY);
        registerPotion(72, "Celestial Healing Brew",     3.3, 75, 190, ItemRarity.LEGENDARY);
        registerPotion(73, "Mythical Life Elixir",       3.5, 80, 170, ItemRarity.LEGENDARY);
        registerPotion(74, "Divine Vitality Draught",    3.8, 90, 180, ItemRarity.LEGENDARY);
        registerPotion(75, "Arcane Health Tonic",       4.0, 95, 200, ItemRarity.LEGENDARY);
        registerPotion(76, "Cute cat bandaid",        0.1, 5, 25, ItemRarity.LOW);
        registerPotion(77, "Pure PP Canister",       5.0, 1000, 250, ItemRarity.LEGENDARY);
    }

    private static void registerPotion(int id, String name, double weight, double value, double healingAmount, ItemRarity rarity)
    {
        PotionData data = new PotionData(id, name, weight, value, healingAmount, rarity);
        POTIONS_BY_ID.put(id, data);
        POTIONS_BY_NAME.put(name.toLowerCase(), data);
        ALL_POTIONS.add(data);
    }

    public static HealingPotion createPotionById(int id)
    {
        PotionData data = POTIONS_BY_ID.get(id);
        if (data == null)
        {
            throw new IllegalArgumentException("Healing potion with ID " + id + " not found");
        }
        return createPotionFromData(data);
    }

    public static HealingPotion createRandomPotionByRarity(ItemRarity rarity)
    {
        List<PotionData> filteredPotions = new ArrayList<>();
        for (PotionData data : ALL_POTIONS)
        {
            if (data.rarity == rarity)
            {
                filteredPotions.add(data);
            }
        }
        if (filteredPotions.isEmpty())
        {
            throw new IllegalArgumentException("No healing potions found with rarity: " + rarity);
        }
        PotionData data = filteredPotions.get(random.nextInt(filteredPotions.size()));
        return createPotionFromData(data);
    }

    public static HealingPotion createPotionByName(String name)
    {
        PotionData data = POTIONS_BY_NAME.get(name.toLowerCase());
        if (data == null)
        {
            throw new IllegalArgumentException("Healing potion with name '" + name + "' not found");
        }
        return createPotionFromData(data);
    }

    public static HealingPotion createRandomPotion()
    {
        if (ALL_POTIONS.isEmpty())
        {
            throw new IllegalStateException("No healing potions registered");
        }
        PotionData data = ALL_POTIONS.get(random.nextInt(ALL_POTIONS.size()));
        return createPotionFromData(data);
    }

    private static HealingPotion createPotionFromData(PotionData data)
    {
        return new HealingPotion(data.name, data.weight, data.value, data.healingAmount, data.rarity);
    }

    public static List<String> getAllPotionNames()
    {
        return ALL_POTIONS.stream().map(p -> p.name).toList();
    }

    public static int getPotionCount()
    {
        return ALL_POTIONS.size();
    }
}
