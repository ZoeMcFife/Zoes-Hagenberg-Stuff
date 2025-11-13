# Factory System Documentation

This project now includes a comprehensive factory system for creating enemies and items.

## Factories Overview

### EnemyFactory
Creates enemies with varying danger levels and stats.

**Methods:**
- `createEnemyById(int id)` - Create enemy by ID (1-50)
- `createEnemyByName(String name)` - Create enemy by name (case-insensitive)
- `createRandomEnemy()` - Create a random enemy
- `createRandomEnemyByDangerLevel(DangerLevel level)` - Create random enemy by danger level
- `getAllEnemyNames()` - Get list of all enemy names
- `getEnemyCount()` - Returns 50

**Danger Levels:**
- HARMLESS - Very weak enemies
- MOSTLY_HARMLESS - Weak enemies
- DANGEROUS - Moderate threat
- EXTREME - High threat
- DEATH - Extremely dangerous enemies

**Total Enemies:** 50

### WeaponFactory
Creates weapons with various damage values and rarities.

**Methods:**
- `createWeaponById(int id)` - Create weapon by ID (1-23)
- `createWeaponByName(String name)` - Create weapon by name (case-insensitive)
- `createRandomWeapon()` - Create a random weapon
- `createRandomWeaponByRarity(ItemRarity rarity)` - Create random weapon by rarity
- `getAllWeaponNames()` - Get list of all weapon names
- `getWeaponCount()` - Returns 23

**Total Weapons:** 23

### ShieldFactory
Creates shields with varying defense values.

**Methods:**
- `createShieldById(int id)` - Create shield by ID (1-6)
- `createShieldByName(String name)` - Create shield by name (case-insensitive)
- `createRandomShield()` - Create a random shield
- `createRandomShieldByRarity(ItemRarity rarity)` - Create random shield by rarity
- `getAllShieldNames()` - Get list of all shield names
- `getShieldCount()` - Returns 6

**Total Shields:** 6

### ArmourFactory
Creates armour with varying defense values.

**Methods:**
- `createArmourById(int id)` - Create armour by ID (1-17)
- `createArmourByName(String name)` - Create armour by name (case-insensitive)
- `createRandomArmour()` - Create a random armour
- `createRandomArmourByRarity(ItemRarity rarity)` - Create random armour by rarity
- `getAllArmourNames()` - Get list of all armour names
- `getArmourCount()` - Returns 17

**Total Armours:** 17

### HealingPotionFactory
Creates healing potions with various healing amounts.

**Methods:**
- `createPotionById(int id)` - Create potion by ID (1-21)
- `createPotionByName(String name)` - Create potion by name (case-insensitive)
- `createRandomPotion()` - Create a random potion
- `getAllPotionNames()` - Get list of all potion names
- `getPotionCount()` - Returns 21

**Total Healing Potions:** 21

## Item Rarity System

All items now support the following rarity levels:
- **LOW** - Common items
- **MEDIUM** - Uncommon items
- **HIGH** - Rare items
- **LEGENDARY** - Extremely rare and powerful items

## Usage Examples

```java
// Create a specific enemy
Enemy enemy = EnemyFactory.createEnemyById(10);  // Relic Golem
Enemy enemy2 = EnemyFactory.createEnemyByName("Void Siren");

// Create a random dangerous enemy
Enemy dangerousEnemy = EnemyFactory.createRandomEnemyByDangerLevel(DangerLevel.DANGEROUS);

// Create a specific weapon
Weapon weapon = WeaponFactory.createWeaponByName("Starcore Blade");

// Create a random legendary weapon
Weapon legendaryWeapon = WeaponFactory.createRandomWeaponByRarity(ItemRarity.LEGENDARY);

// Create random items
Shield shield = ShieldFactory.createRandomShield();
Armour armour = ArmourFactory.createRandomArmour();
HealingPotion potion = HealingPotionFactory.createRandomPotion();
```

## Testing

Run `test.FactoryDemo` to see all factories in action:

```bash
javac -d out -sourcepath src src/test/FactoryDemo.java
java -cp out test.FactoryDemo
```

## Total Content

- **50** unique enemies with flavor text and danger levels
- **23** unique weapons (including magical and non-magical)
- **6** unique shields
- **17** unique armours
- **21** unique healing potions

**Grand Total:** 117 unique items/enemies
