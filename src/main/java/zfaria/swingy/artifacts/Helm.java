package zfaria.swingy.artifacts;

public class Helm extends Artifact {
    public Helm(float stat) {
        super(stat);
    }

    public Helm() {
    }

    @Override
    public String getType() {
        return "Helmet";
    }

    @Override
    public String getStatTranslation() {
        return "" + getStat();
    }
}
