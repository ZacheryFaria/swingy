package zfaria.swingy.hero;

/**
 * The mage has an exceptional base damage, but rather poor luck and defense/hp
 */
public class HeroClassMage implements HeroClass {
    @Override
    public int getBaseDamage(int level) {
        return (10) + (level * 3);
    }

    @Override
    public float getBaseLuck(int level) {
        return (.05f) + (level * .01f);
    }

    @Override
    public float getBaseHitpoints(int level) {
        return (15) + (level * 3);
    }

    @Override
    public float getBaseDefense(int level) {
        return (.02f) + (level * .025f);
    }

    @Override
    public String toString() {
        return "Mage";
    }
}
