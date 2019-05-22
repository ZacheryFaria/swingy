package zfaria.swingy.hero;

/**
 * Has good damage, and fantastic luck, but mediocre defense and hp
 */
public class HeroClassRogue implements HeroClass {
    @Override
    public int getBaseDamage(int level) {
        return (7) + (level * 2);
    }

    @Override
    public float getBaseLuck(int level) {
        return (.3f) + (level * .075f);
    }

    @Override
    public float getBaseHitpoints(int level) {
        return (15) + (level * 3);
    }

    @Override
    public float getBaseDefense(int level) {
        return (.02f) + (level * .009f);
    }

    @Override
    public String toString() {
        return "Rogue";
    }
}
