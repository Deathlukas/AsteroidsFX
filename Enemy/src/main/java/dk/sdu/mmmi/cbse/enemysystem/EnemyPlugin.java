package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;

    public EnemyPlugin() {
    }
    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemyShip(gameData);
        world.addEntity(enemy);

    }

    public Entity createEnemyShip (GameData gameData) {
        Entity enemyShip = new Enemy(gameData);
        enemyShip.setPolygonCoordinates(-5, -5, 10, 0, -5, 5);
        enemyShip.setX(Math.random() * gameData.getDisplayWidth() / 2 + gameData.getDisplayWidth() / 2);
        enemyShip.setY(Math.random() * gameData.getDisplayHeight() / 2 + gameData.getDisplayHeight() / 2);
        enemyShip.setRadius(1);
        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {

        world.removeEntity(enemy);

    }
}
