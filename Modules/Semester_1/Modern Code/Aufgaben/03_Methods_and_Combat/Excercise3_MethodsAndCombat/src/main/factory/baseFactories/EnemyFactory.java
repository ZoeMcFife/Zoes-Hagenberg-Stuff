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
        // All stats (strength, dexterity, intelligence) are now within 1-10 range
        registerEnemy(1, "Abyssal Marauder", 60, 5, 6, 3, "Scavenger pirate mutated by Abyss storms", DangerLevel.HARMLESS, 15);
        registerEnemy(2, "Gateway Corsair", 85, 6, 6, 5, "Alliance privateer, nimble and slippery", DangerLevel.DANGEROUS, 40);
        registerEnemy(3, "Protectorate Scout", 70, 4, 7, 5, "Trained recon Laikan operative", DangerLevel.MOSTLY_HARMLESS, 20);
        registerEnemy(4, "Fargoth Enforcer", 160, 9, 4, 3, "Heavy-hitter in Fargoth armour", DangerLevel.EXTREME, 80);
        registerEnemy(5, "Void Siren", 50, 3, 8, 8, "Lures pilots with abyssal song; high dex & cunning", DangerLevel.DANGEROUS, 40);
        registerEnemy(6, "Rift Hound", 40, 7, 8, 2, "Wolf-clan bred terrors, fast and savage", DangerLevel.MOSTLY_HARMLESS, 20);
        registerEnemy(7, "Nebula Shade", 120, 3, 9, 8, "Stealthy energy-wraith from nebula shadows", DangerLevel.EXTREME, 80);
        registerEnemy(8, "Rune-masked Berserker", 140, 10, 3, 3, "Blindsided by rage, packs raw strength", DangerLevel.EXTREME, 80);
        registerEnemy(9, "Echo Stalker", 55, 3, 9, 5, "Uses echoes of ship comms to ambush", DangerLevel.DANGEROUS, 40);
        registerEnemy(10, "Relic Golem", 220, 10, 3, 3, "Ancient automated guardian of old gateway ruins", DangerLevel.DEATH, 150);
        registerEnemy(11, "Abyss Leviathan (juvenile)", 300, 10, 2, 3, "Small abyssal behemoth that tears hulls", DangerLevel.DEATH, 150);
        registerEnemy(12, "Comms Raider", 75, 5, 7, 6, "Disrupts FTL comms to isolate targets", DangerLevel.DANGEROUS, 40);
        registerEnemy(13, "Smuggler Captain", 95, 6, 5, 7, "Cunning pirate leader with tricks", DangerLevel.DANGEROUS, 40);
        registerEnemy(14, "Bone Corsair", 110, 8, 5, 5, "Transhuman pirate sporting bone-carved gear", DangerLevel.DANGEROUS, 40);
        registerEnemy(15, "Riftling Swarm (single swarm-entity)", 90, 4, 10, 2, "Dozens of tiny attackers acting as one", DangerLevel.DANGEROUS, 40);
        registerEnemy(16, "Warp Gladiator", 200, 10, 6, 5, "Pit fighter from Gateway arenas", DangerLevel.EXTREME, 80);
        registerEnemy(17, "Shadow Engineer", 80, 3, 6, 9, "Hacks systems mid-fight; can disable tech", DangerLevel.DANGEROUS, 40);
        registerEnemy(18, "Vortex Saboteur", 65, 5, 9, 5, "Plants abyssal traps along flight paths", DangerLevel.DANGEROUS, 40);
        registerEnemy(19, "Grave Warden", 180, 9, 4, 8, "Protector of tomb-ark relics, knows rituals", DangerLevel.EXTREME, 80);
        registerEnemy(20, "Corrupted Knight of Wolfram", 210, 10, 5, 4, "Protectorate knight gone wrong", DangerLevel.DEATH, 150);
        registerEnemy(21, "Siren Harbinger", 130, 4, 7, 10, "Powerful siren with area mind-affect", DangerLevel.EXTREME, 80);
        registerEnemy(22, "Rift Goliath", 360, 10, 3, 3, "Huge rift-spawned brute that smashes ships", DangerLevel.DEATH, 150);
        registerEnemy(23, "Arc Seeker", 95, 5, 8, 8, "Hunts energy signatures; fights with arc weapons", DangerLevel.DANGEROUS, 40);
        registerEnemy(24, "Gateway Warden Drone", 140, 8, 5, 4, "Ancient watch-drone, precision strikes", DangerLevel.EXTREME, 80);
        registerEnemy(25, "Bonewright Artificer", 115, 4, 4, 10, "Crafts undead constructs in battle", DangerLevel.EXTREME, 80);
        registerEnemy(26, "Fargoth Raider Pack Leader", 155, 10, 6, 5, "Leads raiding parties with brutal tactics", DangerLevel.EXTREME, 80);
        registerEnemy(27, "Smogfiend", 70, 3, 6, 3, "Abyss-sulfur creature that clouds sensors and chokes", DangerLevel.MOSTLY_HARMLESS, 20);
        registerEnemy(28, "Rift Whisperer", 100, 3, 7, 10, "Uses abyssal whispers to confuse and control", DangerLevel.EXTREME, 80);
        registerEnemy(29, "Coldsteel Duelist", 85, 8, 9, 6, "Elegantly deadly, fast precision strikes", DangerLevel.DANGEROUS, 40);
        registerEnemy(30, "Portal Borer", 130, 9, 4, 3, "Bores through hull and creates local micro-abysses", DangerLevel.EXTREME, 80);
        registerEnemy(31, "Fallen Chaplain", 120, 5, 3, 10, "Zealot using faith-driven powers, dangerous morale debuff", DangerLevel.EXTREME, 80);
        registerEnemy(32, "Alloy Colossus", 280, 10, 3, 3, "Industrial war construct, heavy armour", DangerLevel.DEATH, 150);
        registerEnemy(33, "Riftnymph Scout", 45, 3, 10, 6, "Tiny but distractingly deadly", DangerLevel.MOSTLY_HARMLESS, 20);
        registerEnemy(34, "Gatebreaker Corsair", 170, 10, 5, 4, "Specializes in ripping open abyss gateways", DangerLevel.EXTREME, 80);
        registerEnemy(35, "Echoing Shade Commander", 200, 5, 8, 10, "Leads shades, coordinates attacks telepathically", DangerLevel.EXTREME, 80);
        registerEnemy(36, "Scarred Deckhand (mutineer)", 75, 6, 5, 3, "Angry, desperate fighter on derelicts", DangerLevel.DANGEROUS, 40);
        registerEnemy(37, "Nebular Harpooner", 100, 8, 5, 4, "Heavy ranged harpoon user from deep-mining fleets", DangerLevel.DANGEROUS, 40);
        registerEnemy(38, "Hollow Watcher", 95, 3, 7, 9, "Observes and anticipates crew moves; debuffs morale", DangerLevel.DANGEROUS, 40);
        registerEnemy(39, "Rift Catapult", 160, 10, 3, 3, "Siege engine of mobile wreckage that slams ships", DangerLevel.EXTREME, 80);
        registerEnemy(40, "Fargoth Inquisitor", 150, 8, 5, 10, "Interrogator with psionic interrogation tactics", DangerLevel.EXTREME, 80);
        registerEnemy(41, "Gateway Smelter (rogue drone)", 110, 6, 3, 4, "Melts hulls with focused heat-beam", DangerLevel.DANGEROUS, 40);
        registerEnemy(42, "Abyssal Seeker", 130, 7, 8, 6, "Tracks spiritual signatures, resists normal attacks", DangerLevel.EXTREME, 80);
        registerEnemy(43, "Wolf Clan Zealot", 125, 9, 5, 4, "Religiously fanatic raider; packs rage", DangerLevel.EXTREME, 80);
        registerEnemy(44, "Halcyon Scavenger", 60, 4, 8, 3, "Quick looter that snatches items mid-battle", DangerLevel.MOSTLY_HARMLESS, 20);
        registerEnemy(45, "Relic-bound Revenant", 240, 10, 4, 8, "Cursed guardian that regenerates slowly", DangerLevel.DEATH, 150);
        registerEnemy(46, "Starforged Duel Automaton", 190, 10, 9, 3, "Engineered fighter, precise and deadly", DangerLevel.EXTREME, 80);
        registerEnemy(47, "Abyssal Tempest", 210, 8, 6, 9, "Personified storm spirit that alters battlefield", DangerLevel.EXTREME, 80);
        registerEnemy(48, "Ghost Deck Captain", 175, 7, 5, 10, "Semi-corporeal leader who commands dead crews", DangerLevel.EXTREME, 80);
        registerEnemy(49, "Rift Spriggan (trickster)", 55, 3, 10, 8, "Steals buffs and then flees", DangerLevel.DANGEROUS, 40);
        registerEnemy(50, "Blackwater Brute", 320, 10, 3, 3, "Hulking pirate enforcer that tears through shields", DangerLevel.DEATH, 150);
        registerEnemy(51, "Gutter Tunneler", 55, 2, 2, 1, "tunnel-born menace, adapted to the canyon depths", DangerLevel.MOSTLY_HARMLESS, 12);
        registerEnemy(52, "Vermin Sapper", 31, 2, 5, 1, "fey-touched trickster, steals buffs and gear", DangerLevel.MOSTLY_HARMLESS, 11);
        registerEnemy(53, "Husk Guard", 133, 6, 7, 4, "tunnel-born menace, adapted to the canyon depths", DangerLevel.HARMLESS, 34);
        registerEnemy(54, "Plague Sapper", 39, 3, 1, 1, "mechanical shamble with coal-smoke lungs", DangerLevel.MOSTLY_HARMLESS, 11);
        registerEnemy(55, "Iron Tollkeeper", 53, 1, 6, 4, "fey-touched trickster, steals buffs and gear", DangerLevel.MOSTLY_HARMLESS, 30);
        registerEnemy(56, "Marrow Miner", 160, 5, 7, 3, "runic-etched marauder from old mines", DangerLevel.HARMLESS, 59);
        registerEnemy(57, "Spiral Bruiser", 160, 7, 4, 6, "collector of lost tech, bites and runs", DangerLevel.EXTREME, 114);
        registerEnemy(58, "Gutter Borer", 67, 2, 6, 3, "runic-etched marauder from old mines", DangerLevel.MOSTLY_HARMLESS, 16);
        registerEnemy(59, "Spindle Striker", 79, 3, 6, 2, "spore-tainted creature that fouls the air", DangerLevel.MOSTLY_HARMLESS, 17);
        registerEnemy(60, "Seam Borer", 60, 3, 1, 2, "ancient miner spirit guarding old veins", DangerLevel.HARMLESS, 17);
        registerEnemy(61, "Warden Guard", 138, 5, 4, 3, "fey-touched trickster, steals buffs and gear", DangerLevel.DANGEROUS, 29);
        registerEnemy(62, "Clamp Leech", 154, 5, 4, 3, "fey-touched trickster, steals buffs and gear", DangerLevel.DANGEROUS, 45);
        registerEnemy(63, "Abyssal Sentry", 34, 2, 6, 4, "ancient miner spirit guarding old veins", DangerLevel.MOSTLY_HARMLESS, 9);
        registerEnemy(64, "Marrow Reaver", 139, 5, 7, 2, "runic-etched marauder from old mines", DangerLevel.DANGEROUS, 53);
        registerEnemy(65, "Blink Guard", 57, 2, 4, 1, "collector of lost tech, bites and runs", DangerLevel.MOSTLY_HARMLESS, 18);
        registerEnemy(66, "Claw Prowler", 58, 2, 2, 3, "canyon scavenger that ambushes at dusk", DangerLevel.MOSTLY_HARMLESS, 25);
        registerEnemy(67, "Torch Drudge", 87, 3, 2, 2, "mechanical shamble with coal-smoke lungs", DangerLevel.MOSTLY_HARMLESS, 21);
        registerEnemy(68, "Spore Foragerling", 44, 1, 6, 2, "spore-tainted creature that fouls the air", DangerLevel.MOSTLY_HARMLESS, 10);
        registerEnemy(69, "Tinker Hob", 120, 4, 5, 6, "engineered worker gone feral in the abyss", DangerLevel.DANGEROUS, 44);
        registerEnemy(70, "Shard Skulk", 205, 9, 3, 5, "collector of lost tech, bites and runs", DangerLevel.EXTREME, 95);
        registerEnemy(71, "Gravel Worker", 93, 3, 4, 2, "tunnel-born menace, adapted to the canyon depths", DangerLevel.MOSTLY_HARMLESS, 20);
        registerEnemy(72, "Blink Stalker", 145, 6, 8, 6, "hollow-eyed ghoul attracted to ship noise", DangerLevel.DANGEROUS, 72);
        registerEnemy(73, "Forge Hob", 86, 3, 3, 2, "mechanical shamble with coal-smoke lungs", DangerLevel.MOSTLY_HARMLESS, 19);
        registerEnemy(74, "Coal Gnasher", 51, 4, 5, 1, "canyon scavenger that ambushes at dusk", DangerLevel.MOSTLY_HARMLESS, 14);
        registerEnemy(75, "Spindle Drudge", 72, 3, 4, 2, "collector of lost tech, bites and runs", DangerLevel.MOSTLY_HARMLESS, 17);
        registerEnemy(76, "Runebound Skulk", 202, 8, 6, 7, "runic-etched marauder from old mines", DangerLevel.EXTREME, 101);
        registerEnemy(77, "Smogfiend Junior", 47, 2, 3, 1, "spore-tainted creature that fouls the air", DangerLevel.MOSTLY_HARMLESS, 12);
        registerEnemy(78, "Tunnel Forager", 99, 5, 8, 5, "tunnel-born menace, adapted to the canyon depths", DangerLevel.DANGEROUS, 39);
        registerEnemy(79, "Hollow Tunneler", 220, 10, 2, 4, "hollow-eyed ghoul attracted to ship noise", DangerLevel.DEATH, 160);
        registerEnemy(80, "Coal Sentry", 66, 3, 5, 2, "ancient miner spirit guarding old veins", DangerLevel.MOSTLY_HARMLESS, 15);
        registerEnemy(81, "Mire Prowler", 112, 6, 3, 4, "collector of lost tech, bites and runs", DangerLevel.DANGEROUS, 36);
        registerEnemy(82, "Grim Tollkeeper", 154, 5, 4, 3, "engineered worker gone feral in the abyss", DangerLevel.DANGEROUS, 45);
        registerEnemy(83, "Spiral Whelp", 48, 2, 6, 2, "spore-tainted creature that fouls the air", DangerLevel.MOSTLY_HARMLESS, 13);
        registerEnemy(84, "Gloom Rover", 162, 6, 7, 6, "collector of lost tech, bites and runs", DangerLevel.DANGEROUS, 51);
        registerEnemy(85, "Toll Gnasher", 92, 4, 6, 3, "tunnel-born menace, adapted to the canyon depths", DangerLevel.MOSTLY_HARMLESS, 25);
        registerEnemy(86, "Spiral Tunneler", 174, 6, 4, 8, "fey-touched trickster, steals buffs and gear", DangerLevel.DANGEROUS, 76);
        registerEnemy(87, "Shell Chanter", 123, 5, 7, 5, "collector of lost tech, bites and runs", DangerLevel.HARMLESS, 33);
        registerEnemy(88, "Abyssal Guard", 115, 3, 5, 3, "collector of lost tech, bites and runs", DangerLevel.HARMLESS, 22);
        registerEnemy(89, "Clamp Gnasher", 134, 3, 3, 2, "canyon scavenger that ambushes at dusk", DangerLevel.HARMLESS, 55);
        registerEnemy(90, "Brine Leech", 135, 3, 5, 4, "tunnel-born menace, adapted to the canyon depths", DangerLevel.DANGEROUS, 28);
        registerEnemy(91, "Warden Artificer", 33, 4, 5, 2, "engineered worker gone feral in the abyss", DangerLevel.MOSTLY_HARMLESS, 16);
        registerEnemy(92, "Nebula Rover", 102, 6, 8, 7, "engineered worker gone feral in the abyss", DangerLevel.DANGEROUS, 41);
        registerEnemy(93, "Rift Gloomling", 128, 6, 4, 3, "hollow-eyed ghoul attracted to ship noise", DangerLevel.DANGEROUS, 22);
        registerEnemy(94, "Forge Borer", 198, 6, 9, 4, "mechanical shamble with coal-smoke lungs", DangerLevel.EXTREME, 74);
        registerEnemy(95, "Shell Tollkeeper", 55, 4, 6, 3, "tunnel-born menace, adapted to the canyon depths", DangerLevel.MOSTLY_HARMLESS, 16);
        registerEnemy(96, "Smelt Whelp", 113, 3, 7, 5, "spore-tainted creature that fouls the air", DangerLevel.DANGEROUS, 22);
        registerEnemy(97, "Void Hoarder", 309, 10, 5, 7, "tunnel-born menace, adapted to the canyon depths", DangerLevel.DEATH, 218);
        registerEnemy(98, "Goblin Drudge", 277, 7, 6, 6, "runic-etched marauder from old mines", DangerLevel.EXTREME, 103);
        registerEnemy(99, "Torch Warden", 95, 7, 5, 7, "mechanical shamble with coal-smoke lungs", DangerLevel.DANGEROUS, 39);
        registerEnemy(100, "Spore Forager", 36, 4, 6, 4, "canyon scavenger that ambushes at dusk", DangerLevel.HARMLESS, 11);
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
