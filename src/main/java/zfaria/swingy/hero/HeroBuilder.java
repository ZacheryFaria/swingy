package zfaria.swingy.hero;

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
    private String armor;
    private String helm;
    private String weapon;

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

    public HeroBuilder setArmor(String armor) {
        this.armor = armor;
        return this;
    }

    public HeroBuilder setHelm(String helm) {
        this.helm = helm;
        return this;
    }

    public HeroBuilder setWeapon(String weapon) {
        this.weapon = weapon;
        return this;
    }

    public Hero build() {
        Hero h = new Hero(name, className, experience, armor, helm, weapon);
        return h;
    }
}
