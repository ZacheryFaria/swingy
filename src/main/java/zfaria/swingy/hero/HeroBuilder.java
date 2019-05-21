package zfaria.swingy.hero;

import zfaria.swingy.artifacts.Armor;
import zfaria.swingy.artifacts.Helm;
import zfaria.swingy.artifacts.Weapon;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class HeroBuilder {

    private Hero h;

    private String name;
    private String className;
    private int experience;
    private Armor armor;
    private Helm helm;
    private Weapon weapon;

    public HeroBuilder() {
    }

    public HeroBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public HeroBuilder setClassName(String className) {
        this.className = className;
        return this;
    }

    public HeroBuilder setExperience(int experience) {
        this.experience = experience;
        return this;
    }

    public HeroBuilder setArmor(int stat) {
        this.armor = new Armor(stat);
        return this;
    }

    public HeroBuilder setHelm(int stat) {
        this.helm = new Helm(stat);
        return this;
    }

    public HeroBuilder setWeapon(int stat) {
        this.weapon = new Weapon(stat);
        return this;
    }

    public Hero build() {
        Hero h = new Hero(name, className, experience, armor, weapon, helm);
        return h;
    }
}
