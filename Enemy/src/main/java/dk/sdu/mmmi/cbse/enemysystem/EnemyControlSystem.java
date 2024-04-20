package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

public class EnemyControlSystem implements IEntityProcessingService {

    private final int ROTATION_SPEED = 5;
    private final int TIME_BETWEEN_SHOTS = 300;
    private static long lastShotTime = 0;



    @Override
    public void process(GameData gameData, World world) {
        boolean randomMovement = Math.random() < 0.5;

        for (Entity enemy : world.getEntities(Enemy.class)) {
            if (randomMovement) {
                double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
                double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
                enemy.setX(enemy.getX() + changeX);
                enemy.setY(enemy.getY() + changeY);
            }

            boolean randomRotationDirection = Math.random() < 0.5;
            boolean shouldRotate = Math.random() * Math.random() * Math.random() < 0.5;
            if (shouldRotate) {
                double rotationChange = randomRotationDirection ? -ROTATION_SPEED : ROTATION_SPEED;
                enemy.setRotation(enemy.getRotation() + rotationChange);
            }

            boolean randomShoot = Math.random() < 0.5;
            if (randomShoot) {
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastShotTime > TIME_BETWEEN_SHOTS) {
                    for (BulletSPI bulletSPI : getBulletSPIs()) {
                        Entity bullet = bulletSPI.createBullet(enemy, gameData);
                        world.addEntity(bullet);
                    }
                    lastShotTime = currentTime;
                }
            }


            if (enemy.getX() < 0) {
                enemy.setX(gameData.getDisplayWidth());
            } else if (enemy.getX() > gameData.getDisplayWidth()) {
                enemy.setX(0);

            }

            if (enemy.getY() < 0) {
                enemy.setY(gameData.getDisplayHeight());
            } else if (enemy.getY() > gameData.getDisplayHeight()) {
                enemy.setY(0);
            }
        }

    }
    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(java.util.stream.Collectors.toList());
        }

}
