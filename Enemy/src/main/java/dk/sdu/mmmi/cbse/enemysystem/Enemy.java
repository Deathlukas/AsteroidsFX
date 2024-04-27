package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class Enemy extends Entity {

    public Enemy(GameData gameData) {
        super.setPolygonCoordinates(-5, -5, 10, 0, -5, 5);
        super.setRadius(1);
        super.setX(Math.random() * gameData.getDisplayWidth());
        super.setY(Math.random() * gameData.getDisplayHeight());
    }

    public static Enemy createEnemy(GameData gameData) {
        return new Enemy(gameData);
    }
}