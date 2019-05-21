package zfaria.swingy.hero;

import java.util.HashMap;
import java.util.Map;

public final class HeroClassFactory {

    private static final Map<String, HeroClass> classes;

    private HeroClassFactory() {}

    static {
        classes = new HashMap<String, HeroClass>();
        classes.put("mage", new HeroClassMage());
        classes.put("rogue", new HeroClassRogue());
        classes.put("warrior", new HeroClassWarrior());
    }

    public static HeroClass getHeroClass(String className) {
        className = className.toLowerCase();
        if (classes.containsKey(className)) {
            return classes.get(className);
        } else {
            return null;
        }
    }

}
