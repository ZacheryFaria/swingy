package zfaria.swingy.hero;

import zfaria.swingy.Coordinate;
import zfaria.swingy.Map;
import zfaria.swingy.artifacts.*;
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

    private float attack;

    private float defense;

    private float hitPoints;

    private float luck;

    private Armor armor;

    private Weapon weapon;

    private Helm helm;

    private Coordinate pos;

    private ArtifactFactory artifactFactory;

    Hero(String name, String className, int experience, Armor armor, Weapon weapon, Helm helm) {
        this(name, className, experience);
        this.armor = armor;
        this.weapon = weapon;
        this.helm = helm;
    }

    public Hero(String name, String className) {
        experience = 0;
        updateLevel();
        this.heroClass = HeroClassFactory.getHeroClass(className);
        this.name = name;
        this.armor = new Armor();
        this.weapon = new Weapon();
        this.helm = new Helm();
        artifactFactory = new ArtifactFactory();
    }

    public Hero(String name, String className, int experience) {
        this(name, className);
        this.experience = experience;
        updateLevel();
    }

    public void setClassStats() {
        this.luck = heroClass.getBaseLuck(level);
        this.hitPoints = heroClass.getBaseHitpoints(level);
        applyArtifacts();
    }

    public void applyArtifacts() {
        this.armor.updateLuck(luck);
        this.weapon.updateLuck(luck);
        this.helm.updateLuck(luck);
        this.attack = heroClass.getBaseDamage(level) + weapon.getStat();
        this.defense = heroClass.getBaseDefense(level) + armor.getStat() / 100f;
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

    public float getAttack() {
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

    public boolean flee(GameView view, Enemy enemy) {
        Random r = new Random();
        float num = r.nextFloat();
        float escapeChance = .5f * (1f + luck);
        escapeChance = Math.min(escapeChance, .9f);
        if (num > escapeChance) {
            view.messageUser("You failed to flee!");
            return fight(view, enemy);
        } else {
            view.messageUser("You successfully fled...");
            return true;
        }
    }

    public boolean fight(GameView view, Enemy enemy) {
        float ehp = this.hitPoints + helm.getStat();
        while (enemy.isAlive() && ehp > 0) {
            ehp -= enemy.getDamage() * (1 - this.defense);
            enemy.takeDamage(this.attack);
        }
        if (this.hitPoints > ehp) {
            this.hitPoints = ehp;
        }
        if (this.isAlive()) {
            view.messageUser("You defeated the " + enemy.getName());
            view.messageUser("You received " + enemy.getExperienceReward() + " XP");
            this.experience += enemy.getExperienceReward();
            updateLevel();
            heal(enemy.getLevel());
            return true;
        }
        return false;
    }

    private void heal(int level) {
        this.hitPoints += level;
    }

    public boolean isAlive() {
        return hitPoints > 0.f;
    }

    public void reward(int i) {
        this.experience += i;
        updateLevel();
    }

    public void equip(IArtifact artifact) {
        artifact.updateLuck(luck);
        if (artifact instanceof Armor) {
            this.armor = (Armor)artifact;
        } else if (artifact instanceof Weapon) {
            this.weapon = (Weapon)artifact;
        } else if (artifact instanceof Helm) {
            this.helm = (Helm)artifact;
        }
        applyArtifacts();
    }
}
