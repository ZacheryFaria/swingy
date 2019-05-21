package zfaria.swingy.enemies;

import java.util.Random;

public class EnemyFactory {

    private Random random = new Random();

    private int level;

    private static String enemy_names[] = {
            "Rat",
            "Goblin",
            "Ogre",
            "Orc",
            "Bandit"
    };

    public EnemyFactory(int level) {
        this.level = level;
    }

    public Enemy getRandomEnemy() {
        String enemyName = enemy_names[random.nextInt(enemy_names.length)];

        int level = this.level + (random.nextInt(3) - 1);
        if (level <= 0) level = 1;
        int health = 5 * (level + random.nextInt(3) - 1) + 3;
        int attack = 2 + (level * 2);
        int reward = level * 50 + (random.nextInt(level) % 2 * 25);
        return new Enemy(enemyName, level, health, attack, reward);
    }
}
