package main.factory.baseFactories;

import main.character.DangerLevel;
import main.character.Enemy;

import java.util.*;

/**
 * Factory class for creating enemy characters.
 * Provides methods to create enemies by ID, name, danger level, or randomly.
 * Contains 50 predefined enemies with various danger levels and stats.
 */
public class EnemyFactory
{
    private static final Map<Integer, EnemyData> ENEMIES_BY_ID = new HashMap<>();
    private static final Map<String, EnemyData> ENEMIES_BY_NAME = new HashMap<>();
    private static final Map<DangerLevel, List<EnemyData>> ENEMIES_BY_DANGER = new EnumMap<>(DangerLevel.class);
    private static final List<EnemyData> ALL_ENEMIES = new ArrayList<>();
    private static final Random random = new Random();

    /**
     * Internal data structure for storing enemy information before instantiation.
     */
    private static class EnemyData
    {
        int id;
        String name;
        int maxHealth;
        int strength;
        int dexterity;
        int intelligence;
        int experienceReward;
        String flavor;
        DangerLevel dangerLevel;

        EnemyData(int id, String name, int maxHealth, int strength, int dexterity, int intelligence, int experienceReward, String flavor, DangerLevel dangerLevel)
        {
            this.id = id;
            this.name = name;
            this.maxHealth = maxHealth;
            this.strength = strength;
            this.dexterity = dexterity;
            this.intelligence = intelligence;
            this.experienceReward = experienceReward;
            this.flavor = flavor;
            this.dangerLevel = dangerLevel;
        }
    }

    static
    {
        // Initialize all enemies with danger levels based on stats and health
        // HARMLESS: Str 1-2, Dex 1-3, Int 1-2, HP 20-50, XP 50
        // MOSTLY_HARMLESS: Str 2-4, Dex 2-5, Int 2-5, HP 50-100, XP 100
        // DANGEROUS: Str 4-7, Dex 4-7, Int 4-7, HP 100-180, XP 200
        // EXTREME: Str 4-10, Dex 4-10, Int 4-10, HP 180-300, XP 350
        // DEATH: Str 7-10, Dex 7-10, Int 7-10, HP 300-400, XP 500
        registerEnemy(1, "Abyssal Marauder", 35, 1, 2, 1, "Scavenger pirate mutated by Abyss storms", DangerLevel.HARMLESS, 50);
        registerEnemy(2, "Gateway Corsair", 130, 5, 6, 5, "Alliance privateer, nimble and slippery", DangerLevel.DANGEROUS, 200);
        registerEnemy(3, "Protectorate Scout", 75, 3, 4, 3, "Trained recon Laikan operative", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(4, "Fargoth Enforcer", 250, 8, 7, 6, "Heavy-hitter in Fargoth armour", DangerLevel.EXTREME, 350);
        registerEnemy(5, "Void Siren", 145, 5, 7, 6, "Lures pilots with abyssal song; high dex & cunning", DangerLevel.DANGEROUS, 200);
        registerEnemy(6, "Rift Hound", 65, 3, 5, 2, "Wolf-clan bred terrors, fast and savage", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(7, "Nebula Shade", 220, 6, 9, 8, "Stealthy energy-wraith from nebula shadows", DangerLevel.EXTREME, 350);
        registerEnemy(8, "Rune-masked Berserker", 280, 10, 5, 4, "Blindsided by rage, packs raw strength", DangerLevel.EXTREME, 350);
        registerEnemy(9, "Echo Stalker", 155, 4, 7, 5, "Uses echoes of ship comms to ambush", DangerLevel.DANGEROUS, 200);
        registerEnemy(10, "Relic Golem", 350, 9, 7, 8, "Ancient automated guardian of old gateway ruins", DangerLevel.DEATH, 500);
        registerEnemy(11, "Abyss Leviathan (juvenile)", 390, 10, 8, 7, "Small abyssal behemoth that tears hulls", DangerLevel.DEATH, 500);
        registerEnemy(12, "Comms Raider", 125, 5, 7, 6, "Disrupts FTL comms to isolate targets", DangerLevel.DANGEROUS, 200);
        registerEnemy(13, "Smuggler Captain", 160, 6, 6, 7, "Cunning pirate leader with tricks", DangerLevel.DANGEROUS, 200);
        registerEnemy(14, "Bone Corsair", 170, 7, 6, 5, "Transhuman pirate sporting bone-carved gear", DangerLevel.DANGEROUS, 200);
        registerEnemy(15, "Riftling Swarm (single swarm-entity)", 140, 4, 7, 4, "Dozens of tiny attackers acting as one", DangerLevel.DANGEROUS, 200);
        registerEnemy(16, "Warp Gladiator", 270, 9, 8, 7, "Pit fighter from Gateway arenas", DangerLevel.EXTREME, 350);
        registerEnemy(17, "Shadow Engineer", 135, 4, 6, 7, "Hacks systems mid-fight; can disable tech", DangerLevel.DANGEROUS, 200);
        registerEnemy(18, "Vortex Saboteur", 120, 5, 7, 5, "Plants abyssal traps along flight paths", DangerLevel.DANGEROUS, 200);
        registerEnemy(19, "Grave Warden", 260, 8, 6, 9, "Protector of tomb-ark relics, knows rituals", DangerLevel.EXTREME, 350);
        registerEnemy(20, "Corrupted Knight of Wolfram", 380, 10, 8, 7, "Protectorate knight gone wrong", DangerLevel.DEATH, 500);
        registerEnemy(21, "Siren Harbinger", 240, 6, 8, 10, "Powerful siren with area mind-affect", DangerLevel.EXTREME, 350);
        registerEnemy(22, "Rift Goliath", 400, 10, 9, 8, "Huge rift-spawned brute that smashes ships", DangerLevel.DEATH, 500);
        registerEnemy(23, "Arc Seeker", 150, 5, 7, 7, "Hunts energy signatures; fights with arc weapons", DangerLevel.DANGEROUS, 200);
        registerEnemy(24, "Gateway Warden Drone", 230, 7, 6, 8, "Ancient watch-drone, precision strikes", DangerLevel.EXTREME, 350);
        registerEnemy(25, "Bonewright Artificer", 190, 5, 5, 10, "Crafts undead constructs in battle", DangerLevel.EXTREME, 350);
        registerEnemy(26, "Fargoth Raider Pack Leader", 285, 9, 8, 7, "Leads raiding parties with brutal tactics", DangerLevel.EXTREME, 350);
        registerEnemy(27, "Smogfiend", 80, 3, 4, 3, "Abyss-sulfur creature that clouds sensors and chokes", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(28, "Rift Whisperer", 210, 5, 7, 10, "Uses abyssal whispers to confuse and control", DangerLevel.EXTREME, 350);
        registerEnemy(29, "Coldsteel Duelist", 165, 6, 7, 6, "Elegantly deadly, fast precision strikes", DangerLevel.DANGEROUS, 200);
        registerEnemy(30, "Portal Borer", 255, 8, 6, 5, "Bores through hull and creates local micro-abysses", DangerLevel.EXTREME, 350);
        registerEnemy(31, "Fallen Chaplain", 225, 6, 5, 10, "Zealot using faith-driven powers, dangerous morale debuff", DangerLevel.EXTREME, 350);
        registerEnemy(32, "Alloy Colossus", 370, 10, 7, 8, "Industrial war construct, heavy armour", DangerLevel.DEATH, 500);
        registerEnemy(33, "Riftnymph Scout", 70, 2, 5, 4, "Tiny but distractingly deadly", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(34, "Gatebreaker Corsair", 295, 10, 7, 6, "Specializes in ripping open abyss gateways", DangerLevel.EXTREME, 350);
        registerEnemy(35, "Echoing Shade Commander", 275, 7, 9, 10, "Leads shades, coordinates attacks telepathically", DangerLevel.EXTREME, 350);
        registerEnemy(36, "Scarred Deckhand (mutineer)", 110, 5, 5, 4, "Angry, desperate fighter on derelicts", DangerLevel.DANGEROUS, 200);
        registerEnemy(37, "Nebular Harpooner", 155, 7, 5, 4, "Heavy ranged harpoon user from deep-mining fleets", DangerLevel.DANGEROUS, 200);
        registerEnemy(38, "Hollow Watcher", 145, 4, 6, 7, "Observes and anticipates crew moves; debuffs morale", DangerLevel.DANGEROUS, 200);
        registerEnemy(39, "Rift Catapult", 265, 9, 6, 5, "Siege engine of mobile wreckage that slams ships", DangerLevel.EXTREME, 350);
        registerEnemy(40, "Fargoth Inquisitor", 245, 7, 7, 10, "Interrogator with psionic interrogation tactics", DangerLevel.EXTREME, 350);
        registerEnemy(41, "Gateway Smelter (rogue drone)", 140, 6, 5, 4, "Melts hulls with focused heat-beam", DangerLevel.DANGEROUS, 200);
        registerEnemy(42, "Abyssal Seeker", 235, 7, 8, 8, "Tracks spiritual signatures, resists normal attacks", DangerLevel.EXTREME, 350);
        registerEnemy(43, "Wolf Clan Zealot", 250, 9, 7, 6, "Religiously fanatic raider; packs rage", DangerLevel.EXTREME, 350);
        registerEnemy(44, "Halcyon Scavenger", 85, 3, 5, 3, "Quick looter that snatches items mid-battle", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(45, "Relic-bound Revenant", 360, 9, 8, 9, "Cursed guardian that regenerates slowly", DangerLevel.DEATH, 500);
        registerEnemy(46, "Starforged Duel Automaton", 290, 10, 9, 7, "Engineered fighter, precise and deadly", DangerLevel.EXTREME, 350);
        registerEnemy(47, "Abyssal Tempest", 300, 8, 8, 10, "Personified storm spirit that alters battlefield", DangerLevel.DEATH, 500);
        registerEnemy(48, "Ghost Deck Captain", 280, 8, 7, 10, "Semi-corporeal leader who commands dead crews", DangerLevel.EXTREME, 350);
        registerEnemy(49, "Rift Spriggan (trickster)", 115, 4, 7, 6, "Steals buffs and then flees", DangerLevel.DANGEROUS, 200);
        registerEnemy(50, "Blackwater Brute", 385, 10, 9, 7, "Hulking pirate enforcer that tears through shields", DangerLevel.DEATH, 500);
        registerEnemy(51, "Gutter Tunneler", 90, 3, 4, 2, "tunnel-born menace, adapted to the canyon depths", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(52, "Vermin Sapper", 55, 2, 3, 2, "fey-touched trickster, steals buffs and gear", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(53, "Husk Guard", 125, 5, 6, 4, "runic guardian of forgotten crypts", DangerLevel.DANGEROUS, 200);
        registerEnemy(54, "Plague Sapper", 60, 2, 3, 2, "mechanical shamble with coal-smoke lungs", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(55, "Iron Tollkeeper", 75, 3, 4, 3, "automated toll collector gone rogue", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(56, "Marrow Miner", 45, 2, 3, 1, "runic-etched marauder from old mines", DangerLevel.HARMLESS, 50);
        registerEnemy(57, "Spiral Bruiser", 220, 7, 6, 8, "collector of lost tech, bites and runs", DangerLevel.EXTREME, 350);
        registerEnemy(58, "Gutter Borer", 85, 3, 5, 3, "tunnel borer that digs through debris", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(59, "Spindle Striker", 95, 4, 5, 3, "spore-tainted creature that fouls the air", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(60, "Seam Borer", 30, 1, 2, 1, "ancient miner spirit guarding old veins", DangerLevel.HARMLESS, 50);
        registerEnemy(61, "Warden Guard", 160, 6, 5, 5, "armored guardian of restricted zones", DangerLevel.DANGEROUS, 200);
        registerEnemy(62, "Clamp Leech", 140, 5, 6, 5, "parasitic creature that drains energy", DangerLevel.DANGEROUS, 200);
        registerEnemy(63, "Abyssal Sentry", 70, 3, 4, 2, "ancient miner spirit guarding old veins", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(64, "Marrow Reaver", 175, 7, 6, 5, "runic-etched marauder from old mines", DangerLevel.DANGEROUS, 200);
        registerEnemy(65, "Blink Guard", 65, 2, 5, 2, "collector of lost tech, bites and runs", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(66, "Claw Prowler", 80, 3, 4, 3, "canyon scavenger that ambushes at dusk", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(67, "Torch Drudge", 75, 3, 3, 2, "mechanical shamble with coal-smoke lungs", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(68, "Spore Foragerling", 50, 2, 3, 2, "spore-tainted creature that fouls the air", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(69, "Tinker Hob", 150, 5, 6, 7, "engineered worker gone feral in the abyss", DangerLevel.DANGEROUS, 200);
        registerEnemy(70, "Shard Skulk", 260, 8, 7, 8, "collector of lost tech, bites and runs", DangerLevel.EXTREME, 350);
        registerEnemy(71, "Gravel Worker", 85, 3, 4, 2, "tunnel-born menace, adapted to the canyon depths", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(72, "Blink Stalker", 165, 6, 7, 6, "hollow-eyed ghoul attracted to ship noise", DangerLevel.DANGEROUS, 200);
        registerEnemy(73, "Forge Hob", 70, 2, 4, 2, "mechanical shamble with coal-smoke lungs", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(74, "Coal Gnasher", 60, 3, 4, 2, "canyon scavenger that ambushes at dusk", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(75, "Spindle Drudge", 78, 3, 4, 2, "collector of lost tech, bites and runs", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(76, "Runebound Skulk", 245, 7, 8, 9, "runic-etched marauder from old mines", DangerLevel.EXTREME, 350);
        registerEnemy(77, "Smogfiend Junior", 55, 2, 3, 2, "spore-tainted creature that fouls the air", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(78, "Tunnel Forager", 120, 5, 6, 5, "tunnel-born menace, adapted to the canyon depths", DangerLevel.DANGEROUS, 200);
        registerEnemy(79, "Hollow Tunneler", 340, 8, 7, 7, "hollow-eyed ghoul attracted to ship noise", DangerLevel.DEATH, 500);
        registerEnemy(80, "Coal Sentry", 88, 3, 5, 3, "ancient miner spirit guarding old veins", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(81, "Mire Prowler", 135, 5, 5, 5, "collector of lost tech, bites and runs", DangerLevel.DANGEROUS, 200);
        registerEnemy(82, "Grim Tollkeeper", 155, 6, 5, 5, "engineered worker gone feral in the abyss", DangerLevel.DANGEROUS, 200);
        registerEnemy(83, "Spiral Whelp", 62, 2, 4, 2, "spore-tainted creature that fouls the air", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(84, "Gloom Rover", 148, 5, 7, 6, "collector of lost tech, bites and runs", DangerLevel.DANGEROUS, 200);
        registerEnemy(85, "Toll Gnasher", 92, 4, 5, 3, "tunnel-born menace, adapted to the canyon depths", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(86, "Spiral Tunneler", 170, 6, 6, 7, "fey-touched trickster, steals buffs and gear", DangerLevel.DANGEROUS, 200);
        registerEnemy(87, "Shell Chanter", 40, 1, 3, 2, "collector of lost tech, bites and runs", DangerLevel.HARMLESS, 50);
        registerEnemy(88, "Abyssal Guard", 35, 2, 2, 1, "collector of lost tech, bites and runs", DangerLevel.HARMLESS, 50);
        registerEnemy(89, "Clamp Gnasher", 42, 2, 2, 1, "canyon scavenger that ambushes at dusk", DangerLevel.HARMLESS, 50);
        registerEnemy(90, "Brine Leech", 130, 5, 6, 5, "tunnel-born menace, adapted to the canyon depths", DangerLevel.DANGEROUS, 200);
        registerEnemy(91, "Warden Artificer", 68, 3, 4, 3, "engineered worker gone feral in the abyss", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(92, "Nebula Rover", 142, 6, 7, 7, "engineered worker gone feral in the abyss", DangerLevel.DANGEROUS, 200);
        registerEnemy(93, "Rift Gloomling", 118, 5, 5, 4, "hollow-eyed ghoul attracted to ship noise", DangerLevel.DANGEROUS, 200);
        registerEnemy(94, "Forge Borer", 270, 8, 9, 7, "mechanical shamble with coal-smoke lungs", DangerLevel.EXTREME, 350);
        registerEnemy(95, "Shell Tollkeeper", 72, 3, 5, 3, "tunnel-born menace, adapted to the canyon depths", DangerLevel.MOSTLY_HARMLESS, 100);
        registerEnemy(96, "Smelt Whelp", 110, 4, 6, 5, "spore-tainted creature that fouls the air", DangerLevel.DANGEROUS, 200);
        registerEnemy(97, "Void Hoarder", 375, 9, 8, 9, "tunnel-born menace, adapted to the canyon depths", DangerLevel.DEATH, 500);
        registerEnemy(98, "Goblin Drudge", 285, 8, 7, 8, "runic-etched marauder from old mines", DangerLevel.EXTREME, 350);
        registerEnemy(99, "Torch Warden", 138, 6, 6, 6, "mechanical shamble with coal-smoke lungs", DangerLevel.DANGEROUS, 200);
        registerEnemy(100, "Spore Forager", 38, 2, 3, 1, "canyon scavenger that ambushes at dusk", DangerLevel.HARMLESS, 50);
    }

    private static void registerEnemy(int id, String name, int maxHealth, int strength, int dexterity, int intelligence, String flavor, DangerLevel dangerLevel, int experienceReward)
    {
        EnemyData data = new EnemyData(id, name, maxHealth, strength, dexterity, intelligence, experienceReward, flavor, dangerLevel);
        ENEMIES_BY_ID.put(id, data);
        ENEMIES_BY_NAME.put(name.toLowerCase(), data);
        ENEMIES_BY_DANGER.computeIfAbsent(dangerLevel, k -> new ArrayList<>()).add(data);
        ALL_ENEMIES.add(data);
    }

    /**
     * Creates an enemy by its unique ID.
     * 
     * @param id The enemy ID (1-50)
     * @return A new enemy instance
     * @throws IllegalArgumentException if no enemy exists with the given ID
     */
    public static Enemy createEnemyById(int id)
    {
        EnemyData data = ENEMIES_BY_ID.get(id);
        if (data == null)
        {
            throw new IllegalArgumentException("Enemy with ID " + id + " not found");
        }
        return createEnemyFromData(data);
    }

    /**
     * Creates an enemy by its name (case-insensitive).
     * 
     * @param name The enemy name
     * @return A new enemy instance
     * @throws IllegalArgumentException if no enemy exists with the given name
     */
    public static Enemy createEnemyByName(String name)
    {
        EnemyData data = ENEMIES_BY_NAME.get(name.toLowerCase());
        if (data == null)
        {
            throw new IllegalArgumentException("Enemy with name '" + name + "' not found");
        }
        return createEnemyFromData(data);
    }

    /**
     * Creates a random enemy from all available enemies.
     * 
     * @return A new random enemy instance
     * @throws IllegalStateException if no enemies are registered
     */
    public static Enemy createRandomEnemy()
    {
        if (ALL_ENEMIES.isEmpty())
        {
            throw new IllegalStateException("No enemies registered");
        }
        EnemyData data = ALL_ENEMIES.get(random.nextInt(ALL_ENEMIES.size()));
        return createEnemyFromData(data);
    }

    /**
     * Creates a random enemy of the specified danger level.
     * 
     * @param dangerLevel The desired danger level
     * @return A new random enemy of the specified danger level
     * @throws IllegalArgumentException if no enemies exist for the given danger level
     */
    public static Enemy createRandomEnemyByDangerLevel(DangerLevel dangerLevel)
    {
        List<EnemyData> enemies = ENEMIES_BY_DANGER.get(dangerLevel);
        if (enemies == null || enemies.isEmpty())
        {
            throw new IllegalArgumentException("No enemies found for danger level: " + dangerLevel);
        }
        EnemyData data = enemies.get(random.nextInt(enemies.size()));
        return createEnemyFromData(data);
    }

    private static Enemy createEnemyFromData(EnemyData data)
    {
        return new Enemy(data.name, data.maxHealth, data.strength, data.dexterity, data.intelligence, data.experienceReward);
    }

    /**
     * Gets a list of all enemy names.
     * 
     * @return List of all registered enemy names
     */
    public static List<String> getAllEnemyNames()
    {
        return ALL_ENEMIES.stream().map(e -> e.name).toList();
    }

    /**
     * Gets the total number of registered enemies.
     * 
     * @return The enemy count (always 50)
     */
    public static int getEnemyCount()
    {
        return ALL_ENEMIES.size();
    }
}
