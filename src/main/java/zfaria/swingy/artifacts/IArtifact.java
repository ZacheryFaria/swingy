package zfaria.swingy.artifacts;

public interface IArtifact {

    float getStat();

    int getBaseStat();

    void updateLuck(float luck);

    String getType();

    String getStatTranslation();
}
