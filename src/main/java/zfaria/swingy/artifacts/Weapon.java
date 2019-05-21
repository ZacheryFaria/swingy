package zfaria.swingy.artifacts;

public class Weapon extends Artifact {
    public Weapon(int stat) {
        super(stat);
    }

    public Weapon() {
    }

    @Override
    public String getType() {
        return "Weapon";
    }

    @Override
    public String getStatTranslation() {
        return "" + getStat();
    }
}
