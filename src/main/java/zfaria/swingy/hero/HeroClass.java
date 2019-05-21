package zfaria.swingy.hero;

/**
 * The HeroClass class provides methods that get the leveled values of the different attributes:
 * hitpoints, damage, armor, and luck. These scale per level and the implementation changes their
 * formulas slightly
 */
public interface HeroClass {

    int getBaseDamage(int level);

    float getBaseLuck(int level);

    float getBaseHitpoints(int level);

    float getBaseDefense(int level);

    @Override
    String toString();

}
