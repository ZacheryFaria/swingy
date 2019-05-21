package zfaria.swingy;

import zfaria.swingy.enemies.Enemy;
import zfaria.swingy.enemies.EnemyFactory;

import java.util.Random;

public class Map {

    private int size;

    private Enemy enemies[][];

    private boolean visited[][];

    private int enemyCount;


    private EnemyFactory ef;

    private static final float density = .5f;

    public Map(int level) {
        size = (level - 1) * 5 + 10 - (level % 2);

        enemies = new Enemy[size][size];

        visited = new boolean[size][size];

        visited[size / 2][size / 2] = true;

        enemyCount = (int)((size * size) * density);

        ef = new EnemyFactory(level);

        populateMap();
    }

    private void populateMap() {
        Random r = new Random();
        for (int i = 0; i < enemyCount; i++) {
            int x = r.nextInt(size);
            int y = r.nextInt(size);
            if (enemyExists(new Coordinate(x, y)) || (x == size / 2 && y == size / 2)) {
                i--;
            } else {
                enemies[y][x] = ef.getRandomEnemy();
            }
        }
    }

    public boolean enemyExists(Coordinate c) {
        return enemies[c.getY()][c.getX()] != null;
    }

    public boolean hasVisited(Coordinate c) {
        return visited[c.getY()][c.getX()];
    }

    public void visit(Coordinate c) {
        visited[c.getY()][c.getX()] = true;
    }

    public boolean containsMonster(Coordinate c) {
        return (enemyExists(c) && hasVisited(c));
    }

    public void removeEnemy(Coordinate c) {
        enemies[c.getY()][c.getX()] = null;
    }

    public Enemy getEnemyAt(Coordinate c) {
        return enemies[c.getY()][c.getX()];
    }

    public int getSize() {
        return size;
    }

}
