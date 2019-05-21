package zfaria.swingy.artifacts;

import java.util.Random;

public class ArtifactFactory {

    private Random random;

    private static String armor_names[] = {

    };

    private static String helm_names[] = {

    };

    private static String weap_names[] = {

    };

    public ArtifactFactory() {
        random = new Random();
    }

    public Armor getRandomArmor(int level) {
        int choice = random.nextInt(armor_names.length);
        String choiceString = armor_names[choice];
        int stat = (level * 2) + (random.nextInt(3) - 1);
        return new Armor(choiceString, stat);
    }

    public Helm getRandomHelm(int level) {
        int choice = random.nextInt(weap_names.length);
        String choiceString = helm_names[choice];
        int stat = (level * 2) + (random.nextInt(3) - 1);
        return new Helm(choiceString, stat);
    }

    public Weapon getRandomWeapon(int level) {
        int choice = random.nextInt(weap_names.length);
        String choiceString = weap_names[choice];
        int stat = (level * 2) + (random.nextInt(3) - 1);
        return new Weapon(choiceString, stat);
    }

    public IArtifact getRandomArtifact(int level) {
        int piece = random.nextInt(3);
        if (piece == 0) {
            return getRandomArmor(level);
        } else if (piece == 1) {
            return getRandomHelm(level);
        } else {
            return getRandomWeapon(level);
        }
    }
}
