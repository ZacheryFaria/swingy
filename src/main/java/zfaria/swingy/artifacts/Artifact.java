package zfaria.swingy.artifacts;

public abstract class Artifact implements IArtifact {

    private String name;

    private int stat;

    public Artifact(String name, int stat) {
        this.name = name;
        this.stat = stat;
    }

    @Override
    public int getStat() {
        return stat;
    }

    @Override
    public String getName() {
        return name;
    }
}
