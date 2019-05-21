package zfaria.swingy.artifacts;

import java.util.Random;

public class ArtifactFactory {

    private Random random;

    public ArtifactFactory() {
        random = new Random();
    }

    public Armor getRandomArmor(int level) {
        int stat = (level * 2) + (random.nextInt(3) - 1);
        return new Armor(stat);
    }

    public Helm getRandomHelm(int level) {
        int stat = (level * 2) + (random.nextInt(3) - 1);
        return new Helm(stat);
    }

    public Weapon getRandomWeapon(int level) {
        int stat = (level * 2) + (random.nextInt(3) - 1);
        return new Weapon(stat);
    }

    public IArtifact getRandomArtifact(int level) {
        int piece = random.nextInt(5);
        if (piece == 0) {
            return getRandomArmor(level);
        } else if (piece == 1) {
            return getRandomHelm(level);
        } else if (piece == 2) {
            return getRandomWeapon(level);
        } else {
            return null;
        }
    }
}
