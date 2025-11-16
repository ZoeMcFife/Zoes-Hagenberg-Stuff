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
        registerPotion(1, "Small Health Potion", 0.3, 15, 20, ItemRarity.LOW);
        registerPotion(2, "Minor Healing Salve", 0.2, 18, 25, ItemRarity.LOW);
        registerPotion(3, "Bandage Roll", 0.1, 5, 15, ItemRarity.LOW);
        registerPotion(4, "Herbal Tonic", 0.4, 22, 30, ItemRarity.MEDIUM);
        registerPotion(5, "Stitching Kit", 0.2, 8, 10, ItemRarity.LOW);
        registerPotion(6, "Redleaf Brew", 0.3, 28, 35, ItemRarity.MEDIUM);
        registerPotion(7, "Honey Balm", 0.3, 16, 20, ItemRarity.LOW);
        registerPotion(8, "Freshwater Flask", 0.5, 5, 15, ItemRarity.LOW);
        registerPotion(9, "Healing Elixir", 0.4, 32, 40, ItemRarity.MEDIUM);
        registerPotion(10, "Small Med-Pack", 0.3, 20, 25, ItemRarity.LOW);
        registerPotion(11, "Revitalizing Tea", 0.2, 15, 20, ItemRarity.LOW);
        registerPotion(12, "Soothing Gel", 0.3, 24, 30, ItemRarity.MEDIUM);
        registerPotion(13, "Quick Patch", 0.1, 8, 15, ItemRarity.LOW);
        registerPotion(14, "Restorative Drop", 0.2, 30, 35, ItemRarity.MEDIUM);
        registerPotion(15, "Minor Lifeforce Potion", 0.3, 18, 20, ItemRarity.LOW);
        registerPotion(16, "Herb Poultice", 0.2, 20, 25, ItemRarity.LOW);
        registerPotion(17, "Healing Dust", 0.1, 6, 10, ItemRarity.LOW);
        registerPotion(18, "Warm Compress", 0.3, 22, 30, ItemRarity.MEDIUM);
        registerPotion(19, "Small Vitality Vial", 0.2, 16, 20, ItemRarity.LOW);
        registerPotion(20, "Emergency Tonic", 0.4, 45, 50, ItemRarity.HIGH);
        registerPotion(21, "Protectorate Med-Patch", 0.1, 40, 20, ItemRarity.LOW);
        registerPotion(22, "Brand X Elixir", 0.5, 100, 80, ItemRarity.LEGENDARY);
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
