package main.factory;

import main.character.DangerLevel;
import main.character.Enemy;

import java.util.*;

public class EnemyFactory
{
    private static final Map<Integer, EnemyData> ENEMIES_BY_ID = new HashMap<>();
    private static final Map<String, EnemyData> ENEMIES_BY_NAME = new HashMap<>();
    private static final Map<DangerLevel, List<EnemyData>> ENEMIES_BY_DANGER = new EnumMap<>(DangerLevel.class);
    private static final List<EnemyData> ALL_ENEMIES = new ArrayList<>();
    private static final Random random = new Random();

    private static class EnemyData
    {
        int id;
        String name;
        int maxHealth;
        int strength;
        int dexterity;
        int intelligence;
        String flavor;
        DangerLevel dangerLevel;

        EnemyData(int id, String name, int maxHealth, int strength, int dexterity, int intelligence, String flavor, DangerLevel dangerLevel)
        {
            this.id = id;
            this.name = name;
            this.maxHealth = maxHealth;
            this.strength = strength;
            this.dexterity = dexterity;
            this.intelligence = intelligence;
            this.flavor = flavor;
            this.dangerLevel = dangerLevel;
        }
    }

    static
    {
        // Initialize all enemies with danger levels based on stats and health
        // All stats (strength, dexterity, intelligence) are now within 1-10 range
        registerEnemy(1, "Abyssal Marauder", 60, 5, 6, 3, "Scavenger pirate mutated by Abyss storms", DangerLevel.MOSTLY_HARMLESS);
        registerEnemy(2, "Gateway Corsair", 85, 6, 6, 5, "Alliance privateer, nimble and slippery", DangerLevel.DANGEROUS);
        registerEnemy(3, "Protectorate Scout", 70, 4, 7, 5, "Trained recon Laikan operative", DangerLevel.MOSTLY_HARMLESS);
        registerEnemy(4, "Fargoth Enforcer", 160, 9, 4, 3, "Heavy-hitter in Fargoth armour", DangerLevel.EXTREME);
        registerEnemy(5, "Void Siren", 50, 3, 8, 8, "Lures pilots with abyssal song; high dex & cunning", DangerLevel.DANGEROUS);
        registerEnemy(6, "Rift Hound", 40, 7, 8, 2, "Wolf-clan bred terrors, fast and savage", DangerLevel.MOSTLY_HARMLESS);
        registerEnemy(7, "Nebula Shade", 120, 3, 9, 8, "Stealthy energy-wraith from nebula shadows", DangerLevel.EXTREME);
        registerEnemy(8, "Rune-masked Berserker", 140, 10, 3, 3, "Blindsided by rage, packs raw strength", DangerLevel.EXTREME);
        registerEnemy(9, "Echo Stalker", 55, 3, 9, 5, "Uses echoes of ship comms to ambush", DangerLevel.DANGEROUS);
        registerEnemy(10, "Relic Golem", 220, 10, 3, 3, "Ancient automated guardian of old gateway ruins", DangerLevel.DEATH);
        registerEnemy(11, "Abyss Leviathan (juvenile)", 300, 10, 2, 3, "Small abyssal behemoth that tears hulls", DangerLevel.DEATH);
        registerEnemy(12, "Comms Raider", 75, 5, 7, 6, "Disrupts FTL comms to isolate targets", DangerLevel.DANGEROUS);
        registerEnemy(13, "Smuggler Captain", 95, 6, 5, 7, "Cunning pirate leader with tricks", DangerLevel.DANGEROUS);
        registerEnemy(14, "Bone Corsair", 110, 8, 5, 5, "Transhuman pirate sporting bone-carved gear", DangerLevel.DANGEROUS);
        registerEnemy(15, "Riftling Swarm (single swarm-entity)", 90, 4, 10, 2, "Dozens of tiny attackers acting as one", DangerLevel.DANGEROUS);
        registerEnemy(16, "Warp Gladiator", 200, 10, 6, 5, "Pit fighter from Gateway arenas", DangerLevel.EXTREME);
        registerEnemy(17, "Shadow Engineer", 80, 3, 6, 9, "Hacks systems mid-fight; can disable tech", DangerLevel.DANGEROUS);
        registerEnemy(18, "Vortex Saboteur", 65, 5, 9, 5, "Plants abyssal traps along flight paths", DangerLevel.DANGEROUS);
        registerEnemy(19, "Grave Warden", 180, 9, 4, 8, "Protector of tomb-ark relics, knows rituals", DangerLevel.EXTREME);
        registerEnemy(20, "Corrupted Knight of Wolfram", 210, 10, 5, 4, "Protectorate knight gone wrong", DangerLevel.DEATH);
        registerEnemy(21, "Siren Harbinger", 130, 4, 7, 10, "Powerful siren with area mind-affect", DangerLevel.EXTREME);
        registerEnemy(22, "Rift Goliath", 360, 10, 3, 3, "Huge rift-spawned brute that smashes ships", DangerLevel.DEATH);
        registerEnemy(23, "Arc Seeker", 95, 5, 8, 8, "Hunts energy signatures; fights with arc weapons", DangerLevel.DANGEROUS);
        registerEnemy(24, "Gateway Warden Drone", 140, 8, 5, 4, "Ancient watch-drone, precision strikes", DangerLevel.EXTREME);
        registerEnemy(25, "Bonewright Artificer", 115, 4, 4, 10, "Crafts undead constructs in battle", DangerLevel.EXTREME);
        registerEnemy(26, "Fargoth Raider Pack Leader", 155, 10, 6, 5, "Leads raiding parties with brutal tactics", DangerLevel.EXTREME);
        registerEnemy(27, "Smogfiend", 70, 3, 6, 3, "Abyss-sulfur creature that clouds sensors and chokes", DangerLevel.MOSTLY_HARMLESS);
        registerEnemy(28, "Rift Whisperer", 100, 3, 7, 10, "Uses abyssal whispers to confuse and control", DangerLevel.EXTREME);
        registerEnemy(29, "Coldsteel Duelist", 85, 8, 9, 6, "Elegantly deadly, fast precision strikes", DangerLevel.DANGEROUS);
        registerEnemy(30, "Portal Borer", 130, 9, 4, 3, "Bores through hull and creates local micro-abysses", DangerLevel.EXTREME);
        registerEnemy(31, "Fallen Chaplain", 120, 5, 3, 10, "Zealot using faith-driven powers, dangerous morale debuff", DangerLevel.EXTREME);
        registerEnemy(32, "Alloy Colossus", 280, 10, 3, 3, "Industrial war construct, heavy armour", DangerLevel.DEATH);
        registerEnemy(33, "Riftnymph Scout", 45, 3, 10, 6, "Tiny but distractingly deadly", DangerLevel.MOSTLY_HARMLESS);
        registerEnemy(34, "Gatebreaker Corsair", 170, 10, 5, 4, "Specializes in ripping open abyss gateways", DangerLevel.EXTREME);
        registerEnemy(35, "Echoing Shade Commander", 200, 5, 8, 10, "Leads shades, coordinates attacks telepathically", DangerLevel.EXTREME);
        registerEnemy(36, "Scarred Deckhand (mutineer)", 75, 6, 5, 3, "Angry, desperate fighter on derelicts", DangerLevel.DANGEROUS);
        registerEnemy(37, "Nebular Harpooner", 100, 8, 5, 4, "Heavy ranged harpoon user from deep-mining fleets", DangerLevel.DANGEROUS);
        registerEnemy(38, "Hollow Watcher", 95, 3, 7, 9, "Observes and anticipates crew moves; debuffs morale", DangerLevel.DANGEROUS);
        registerEnemy(39, "Rift Catapult", 160, 10, 3, 3, "Siege engine of mobile wreckage that slams ships", DangerLevel.EXTREME);
        registerEnemy(40, "Fargoth Inquisitor", 150, 8, 5, 10, "Interrogator with psionic interrogation tactics", DangerLevel.EXTREME);
        registerEnemy(41, "Gateway Smelter (rogue drone)", 110, 6, 3, 4, "Melts hulls with focused heat-beam", DangerLevel.DANGEROUS);
        registerEnemy(42, "Abyssal Seeker", 130, 7, 8, 6, "Tracks spiritual signatures, resists normal attacks", DangerLevel.EXTREME);
        registerEnemy(43, "Wolf Clan Zealot", 125, 9, 5, 4, "Religiously fanatic raider; packs rage", DangerLevel.EXTREME);
        registerEnemy(44, "Halcyon Scavenger", 60, 4, 8, 3, "Quick looter that snatches items mid-battle", DangerLevel.MOSTLY_HARMLESS);
        registerEnemy(45, "Relic-bound Revenant", 240, 10, 4, 8, "Cursed guardian that regenerates slowly", DangerLevel.DEATH);
        registerEnemy(46, "Starforged Duel Automaton", 190, 10, 9, 3, "Engineered fighter, precise and deadly", DangerLevel.EXTREME);
        registerEnemy(47, "Abyssal Tempest", 210, 8, 6, 9, "Personified storm spirit that alters battlefield", DangerLevel.EXTREME);
        registerEnemy(48, "Ghost Deck Captain", 175, 7, 5, 10, "Semi-corporeal leader who commands dead crews", DangerLevel.EXTREME);
        registerEnemy(49, "Rift Spriggan (trickster)", 55, 3, 10, 8, "Steals buffs and then flees", DangerLevel.DANGEROUS);
        registerEnemy(50, "Blackwater Brute", 320, 10, 3, 3, "Hulking pirate enforcer that tears through shields", DangerLevel.DEATH);
    }

    private static void registerEnemy(int id, String name, int maxHealth, int strength, int dexterity, int intelligence, String flavor, DangerLevel dangerLevel)
    {
        EnemyData data = new EnemyData(id, name, maxHealth, strength, dexterity, intelligence, flavor, dangerLevel);
        ENEMIES_BY_ID.put(id, data);
        ENEMIES_BY_NAME.put(name.toLowerCase(), data);
        ENEMIES_BY_DANGER.computeIfAbsent(dangerLevel, k -> new ArrayList<>()).add(data);
        ALL_ENEMIES.add(data);
    }

    public static Enemy createEnemyById(int id)
    {
        EnemyData data = ENEMIES_BY_ID.get(id);
        if (data == null)
        {
            throw new IllegalArgumentException("Enemy with ID " + id + " not found");
        }
        return createEnemyFromData(data);
    }

    public static Enemy createEnemyByName(String name)
    {
        EnemyData data = ENEMIES_BY_NAME.get(name.toLowerCase());
        if (data == null)
        {
            throw new IllegalArgumentException("Enemy with name '" + name + "' not found");
        }
        return createEnemyFromData(data);
    }

    public static Enemy createRandomEnemy()
    {
        if (ALL_ENEMIES.isEmpty())
        {
            throw new IllegalStateException("No enemies registered");
        }
        EnemyData data = ALL_ENEMIES.get(random.nextInt(ALL_ENEMIES.size()));
        return createEnemyFromData(data);
    }

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
        return new Enemy(data.name, data.maxHealth, data.strength, data.dexterity, data.intelligence);
    }

    public static List<String> getAllEnemyNames()
    {
        return ALL_ENEMIES.stream().map(e -> e.name).toList();
    }

    public static int getEnemyCount()
    {
        return ALL_ENEMIES.size();
    }
}
