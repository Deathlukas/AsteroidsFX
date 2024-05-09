package dk.sdu.mmmi.cbse.common.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Asteroid extends Entity{

    private int splitCount;

    public Asteroid() {
        this.splitCount = 0;
    }

    public int getSplitCount() {
        return splitCount;
    }

    public void incrementSplitCount() {
        splitCount++;
    }
}
