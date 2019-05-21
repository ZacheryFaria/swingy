package zfaria.swingy.artifacts;

public abstract class Artifact implements IArtifact {

    private int stat;

    public Artifact() {
        this(0);
    }

    public Artifact(int stat) {
        this.stat = stat;
    }

    @Override
    public int getStat() {
        return stat;
    }
}
