# Implementation Summary: Enemy and Item Factory System

## Overview
This implementation adds a comprehensive factory system to the Excercise3_MethodsAndCombat project, enabling dynamic creation of enemies and items with various properties and rarities.

## Components Added

### 1. Utility Classes
- **IO.java** - Console I/O utility with static print/println methods

### 2. Enums
- **DangerLevel.java** - Categorizes enemies into 5 danger levels:
  - HARMLESS
  - MOSTLY_HARMLESS
  - DANGEROUS
  - EXTREME
  - DEATH

### 3. Item System Updates
- Updated `Item`, `Weapon`, `Shield`, `Armour`, and `HealingPotion` classes
- Added ItemRarity support to all item types
- New constructors accepting rarity parameter
- Maintained backward compatibility with existing constructors

### 4. Factory Classes

#### EnemyFactory
- **50 unique enemies** from the game lore
- Each with custom stats, flavor text, and danger level
- Methods: byId, byName, random, byDangerLevel

#### WeaponFactory
- **23 unique weapons** including magical and physical
- Damage ranges from 14 to 62
- Methods: byId, byName, random, byRarity

#### ShieldFactory
- **6 unique shields**
- Defense ranges from 8 to 30
- Methods: byId, byName, random, byRarity

#### ArmourFactory
- **17 unique armour pieces**
- Defense ranges from 6 to 40
- Methods: byId, byName, random, byRarity

#### HealingPotionFactory
- **21 unique healing items**
- Healing ranges from 10 to 50 HP
- Methods: byId, byName, random

## Key Features

### Flexible Creation Methods
All factories support multiple creation patterns:
1. **By ID** - Specific item/enemy using numeric identifier
2. **By Name** - Case-insensitive name lookup
3. **Random** - Randomly select from all available
4. **By Category** - Filter by danger level (enemies) or rarity (items)

### Data Organization
- HashMap-based lookups for O(1) access by ID and name
- EnumMap-based categorization for efficient filtering
- Complete data encapsulation within factory classes

### Testing
- FactoryDemo.java demonstrates all factory functionality
- Verified all 117 items/enemies can be created successfully

## Statistics

| Category | Count |
|----------|-------|
| Enemies | 50 |
| Weapons | 23 |
| Shields | 6 |
| Armours | 17 |
| Healing Potions | 21 |
| **Total** | **117** |

## Usage Example

```java
// Create a powerful enemy
Enemy boss = EnemyFactory.createRandomEnemyByDangerLevel(DangerLevel.DEATH);

// Equip the player with legendary gear
Weapon sword = WeaponFactory.createRandomWeaponByRarity(ItemRarity.LEGENDARY);
Shield shield = ShieldFactory.createShieldByName("Echo Shard Shield");
Armour armor = ArmourFactory.createRandomArmourByRarity(ItemRarity.HIGH);

// Prepare healing items
HealingPotion potion = HealingPotionFactory.createPotionByName("Emergency Tonic");
```

## Code Quality
- ✅ Compiles without errors
- ✅ No security vulnerabilities detected by CodeQL
- ✅ Follows existing code patterns
- ✅ Fully documented with README
- ✅ Minimal changes to existing code
- ✅ All static imports properly configured

## Files Modified
- GameCharacter.java (1 line - fixed Weapon constructor call)
- Item.java (added rarity support)
- Weapon.java (added rarity constructor)
- Shield.java (added rarity constructor)
- Armour.java (added rarity constructor)
- HealingPotion.java (added rarity constructor)
- UI components (added IO static imports)

## Files Created
- main/util/IO.java
- main/character/DangerLevel.java
- main/factory/EnemyFactory.java
- main/factory/WeaponFactory.java
- main/factory/ShieldFactory.java
- main/factory/ArmourFactory.java
- main/factory/HealingPotionFactory.java
- test/FactoryTest.java
- test/FactoryDemo.java
- FACTORY_README.md
- IMPLEMENTATION_SUMMARY.md
