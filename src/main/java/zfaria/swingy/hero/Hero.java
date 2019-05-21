package zfaria.swingy.hero;

import zfaria.swingy.Coordinate;
import zfaria.swingy.Map;
import zfaria.swingy.artifacts.Armor;
import zfaria.swingy.artifacts.Helm;
import zfaria.swingy.artifacts.Weapon;
import zfaria.swingy.enemies.Enemy;
import zfaria.swingy.view.GameView;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Random;

public class Hero {

    @Size(min = 3, max = 10, message = "Name should be between 3 to 10 characters.")
    @NotNull
    private String name;

    @NotNull(message = "Invalid Class name")
    private HeroClass heroClass;

    private int level;

    private int experience;

    private int attack;

    /*
     * Defense is a float modifier that decreaes the amount of oncoming damage by a %.
     */
    private float defense;

    private float hitPoints;

    /*
     * Luck increases chances of a critical hit, (1.5x damage)
     * Better loot and better odds of escaping.
     */
    private float luck;

    private Armor armor;

    private Weapon weapon;

    private Helm helm;

    private Coordinate pos;

    Hero(String name, String className, int experience, String armor, String weapon, String helm) {
        this(name, className, experience);
        // TODO create artifact builder?
    }

    public Hero(String name, String className) {
        experience = 0;
        updateLevel();
        this.heroClass = HeroClassFactory.getHeroClass(className);
        this.name = name;
        this.armor = new Armor("armor", 5);
        this.weapon = new Weapon("weapon", 5);
        this.helm = new Helm("helm", 5);
    }

    public Hero(String name, String className, int experience) {
        this(name, className);
        this.experience = experience;
        updateLevel();
    }

    public void setClassStats() {
        this.attack = heroClass.getBaseDamage(level);
        this.defense = heroClass.getBaseDefense(level);
        this.luck = heroClass.getBaseLuck(level);
        this.hitPoints = heroClass.getBaseHitpoints(level);
        this.hitPoints += helm.getStat();
        this.attack += weapon.getStat();
        this.defense += armor.getStat() / 100f;
    }

    private void updateLevel() {
        boolean found = false;
        int lvl = 1;
        do {
            int needed = lvl * 1000 + (int)Math.pow(lvl - 1, 2) * 450;
            if (experience >= needed) {
                lvl++;
            } else {
                found = true;
            }
        } while (!found);
        this.level = lvl;
    }


    @Override
    public String toString() {
        return String.format("Name: %s Class: %s Level: %d", name, heroClass.toString(), level);
    }

    public String getName() {
        return name;
    }

    public String getHeroClass() {
        return heroClass.toString();
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getAttack() {
        return attack;
    }

    public float getDefense() {
        return defense;
    }

    public float getHitPoints() {
        return hitPoints;
    }

    public float getLuck() {
        return luck;
    }

    public Armor getArmor() {
        return armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Helm getHelm() {
        return helm;
    }

    public void setStartingPosition(Map map) {
        this.pos = new Coordinate(map.getSize() / 2, map.getSize() / 2);
    }

    public Coordinate getPosition() {
        return pos;
    }

    public void flee(GameView view, Enemy enemy) {
        Random r = new Random();
        float num = r.nextFloat();
        float chance = .50f + luck;
        if (num > chance) {
            view.messageUser("You failed to flee!");
            fight(view, enemy);
        } else {
            view.messageUser("You successfully fled...");
        }
    }

    public void fight(GameView view, Enemy enemy) {
        while (enemy.isAlive() && this.isAlive()) {
            this.hitPoints -= enemy.getDamage() * (1 - this.defense);
            enemy.takeDamage(this.attack);
        }
        if (this.isAlive()) {
            view.messageUser("You defeated the " + enemy.getName());
            view.messageUser("You received " + enemy.getExperienceReward() + " XP");
            this.experience += enemy.getExperienceReward();
            updateLevel();
        }
    }

    public boolean isAlive() {
        return hitPoints > 0.f;
    }

    public void reward(int i) {
        this.experience += i;
        updateLevel();
    }
}
