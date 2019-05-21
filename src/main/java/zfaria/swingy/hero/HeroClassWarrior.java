package zfaria.swingy.hero;

/**
 * The warrior is the jack of all trades class, having respectable values in all
 * places
 */
public class HeroClassWarrior implements HeroClass {
    @Override
    public int getBaseDamage(int level) {
        return (6) + (level * 1);
    }

    @Override
    public float getBaseLuck(int level) {
        return (.1f) + (level * .02f);
    }

    @Override
    public float getBaseHitpoints(int level) {
        return (25) + (level * 5);
    }

    @Override
    public float getBaseDefense(int level) {
        return (.1f) + (level * .05f);
    }

    @Override
    public String toString() {
        return "Warrior";
    }
}
