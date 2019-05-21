package zfaria.swingy.artifacts;

public class Armor extends Artifact {
    public Armor(int stat) {
        super(stat);
    }

    public Armor() {
    }

    @Override
    public String getType() {
        return "Armor";
    }

    @Override
    public String getStatTranslation() {
        return getStat() + "%";
    }
}
