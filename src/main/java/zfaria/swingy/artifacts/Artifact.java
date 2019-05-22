package zfaria.swingy.artifacts;

public abstract class Artifact implements IArtifact {

    private float stat;
    private float luck;

    public Artifact() {
        this(0);
    }

    public Artifact(float stat) {
        this.stat = stat;
        luck = 0f;
    }

    @Override
    public void updateLuck(float luck) {
        this.luck = luck;
    }

    @Override
    public float getStat() {
        return (stat * (1f + luck));
    }

    @Override
    public int getBaseStat() {
        return (int)stat;
    }
}
