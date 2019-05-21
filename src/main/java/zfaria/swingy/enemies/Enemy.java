package zfaria.swingy.enemies;

import zfaria.swingy.Coordinate;

public class Enemy {

    private String name;
    private float hitPoints;
    private float damage;
    private int level;
    private int experienceReward;

    public Enemy(String name, int level, float hitPoints, float damage, int reward) {
        this.name = name;
        this.level = level;
        this.hitPoints = hitPoints;
        this.damage = damage;
        this.experienceReward = reward;
    }

    public boolean isAlive() {
        return hitPoints > 0.f;
    }

    public String getName() {
        return name;
    }

    public float getHitPoints() {
        return hitPoints;
    }

    public float getDamage() {
        return damage;
    }

    public int getLevel() {
        return level;
    }

    public void takeDamage(int attack) {
        this.hitPoints -= attack;
    }

    public int getExperienceReward() {
        return experienceReward;
    }

    public String toString() {
        return String.format("Name: %s\nLevel: %d\nHealth: %.0f\nAttack: %.0f",
                name,
                level,
                hitPoints,
                damage);
    }
}
