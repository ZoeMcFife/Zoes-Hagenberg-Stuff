# PP System Implementation Guide

## Overview
This implementation adds a Power Points (PP) system to the combat game, allowing players to use special attacks with their weapons. Players gain PP by defending with shields and can spend PP to unleash powerful special attacks.

## How It Works

### 1. **Gaining PP**
- When a player chooses to **Defend** in battle, they gain PP based on their equipped shield
- Different shields grant different amounts of PP:
  - **LOW rarity shields**: 3-5 PP per defend
  - **MEDIUM rarity shields**: 8-9 PP per defend
  - **HIGH rarity shields**: 11-12 PP per defend
  - **LEGENDARY rarity shields**: 15 PP per defend
- Even the basic "Fists" shield grants 3 PP when defending

### 2. **Using Special Attacks**
- In the battle menu, option 4 is "Use Special"
- The menu displays:
  - Your current PP
  - The PP cost of your equipped weapon's special attack
- When you select "Use Special":
  - The game checks if you have enough PP
  - If yes: Deducts the PP cost, displays special flavor text, and deals extra damage
  - If no: Shows a message explaining you need more PP

### 3. **Special Attack Damage**
- Special attacks deal **base weapon damage + special damage bonus**
- The damage is calculated using the same stat modifiers as normal attacks:
  - Physical weapons scale with Strength
  - Magic weapons scale with Intelligence

### 4. **Weapon Special Attacks**
Each weapon has unique special attack properties:

**LOW Rarity Weapons:**
- PP Cost: 20-25
- Special Damage: ~15-20
- Example: "Scrapgun Mk II" - "Scrap metal flies in all directions!"

**MEDIUM Rarity Weapons:**
- PP Cost: 30-35
- Special Damage: ~24-33
- Example: "Gateway Cutlass" - "The blade gleams with arcane energy as it slashes through the air!"

**HIGH Rarity Weapons:**
- PP Cost: 40-45
- Special Damage: ~36-44
- Example: "Abyssic Longrifle" - "A devastating shot echoes from the depths of the abyss!"

**LEGENDARY Rarity Weapons:**
- PP Cost: 50-55
- Special Damage: ~55-70
- Example: "Warpspike Lance" - "Reality warps as the lance pierces through dimensions!"

## Battle Strategy

### Recommended Tactics:
1. **Start by defending** to build up PP in the early rounds
2. **Use better shields** to gain PP faster
3. **Save PP for critical moments** or tough enemies
4. **Balance between** normal attacks and special attacks
5. **Legendary weapons** have the most powerful specials but cost the most PP

### Example Battle Flow:
```
Turn 1: Defend → Gain 8 PP (with Nebular Disk Shield)
Turn 2: Defend → Gain 8 PP (Total: 16 PP)
Turn 3: Attack → Chip away at enemy health
Turn 4: Defend → Gain 8 PP (Total: 24 PP)
Turn 5: Use Special → Spend all 24 PP for massive damage!
```

## Technical Details

### Modified Classes:

**Weapon.java**
- `specialDamage` - Bonus damage added to special attacks
- `specialFlavorText` - Message displayed when special is used
- `ppCost` - PP required to use this weapon's special

**Shield.java**
- `ppGain` - PP granted to player when defending

**Player.java**
- `currentPP` - Tracks current Power Points
- `getCurrentPP()` - Returns current PP
- `gainPP(int amount)` - Adds PP (with validation)
- `useSpecial(GameCharacter target)` - Executes special attack

**GameCharacter.java**
- `defend()` - Now grants PP to players based on shield

**ActionType.java**
- Added `USE_SPECIAL` action type

**TurnUI.java**
- Added option 4: "Use Special"
- Displays current PP and cost
- Handles USE_SPECIAL action

### Factory Updates:
- **WeaponFactory**: 26+ weapons updated with special attack data
- **ShieldFactory**: 20+ shields updated with PP gain values

## Testing

Run the PPSystemTest class to verify:
- Weapon and Shield PP values are set correctly
- Player can gain and use PP properly
- Special attacks work only with sufficient PP
- Validation prevents negative PP values
- All getters/setters work correctly

## Notes

- Only **players** can use special attacks (enemies cannot)
- PP persists across turns but not across battles
- Even the default "Fists" weapon has a special attack (though weak)
- The system is fully backward compatible with existing code
- All weapons and shields can still be created without PP values (they default to 0)

## Future Enhancements (Not Implemented)

Possible additions if needed:
- Maximum PP cap for players
- PP regeneration per turn
- Special attacks for enemies
- Multiple special attacks per weapon
- PP-based abilities beyond weapon specials
- Visual PP bar in the UI
- Sound effects for special attacks

---

**Enjoy strategically managing your Power Points in battle!**
