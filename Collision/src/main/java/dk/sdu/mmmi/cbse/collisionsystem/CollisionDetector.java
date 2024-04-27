package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.enemysystem.Enemy;

public class CollisionDetector implements IPostEntityProcessingService {

    public CollisionDetector() {
    }

    @Override
    public void process(GameData gameData, World world) {
        // two for loops for all entities in the world
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {

                // if the two entities are identical or not colliding, skip the iteration
                if (entity1.getID().equals(entity2.getID()) || !this.collides(entity1, entity2)) {
                    continue;
                }

                // CollisionDetection
                if (entity1 instanceof Bullet && entity2 instanceof Asteroid) {
                    // If entity1 is a bullet and entity2 is an asteroid, remove both entities
                    world.removeEntity(entity1);
                    world.removeEntity(entity2);
                }
                if (entity1 instanceof Bullet && entity2 instanceof Enemy) {
                    // If entity1 is a bullet and entity2 is an enemy, remove both entities
                    world.removeEntity(entity1);
                    world.removeEntity(entity2);
                    spawnNewEnemy(gameData, world);
                }
            }
        }
    }

    private void spawnNewEnemy(GameData gameData, World world) {
        Entity enemy = Enemy.createEnemy(gameData);
        world.addEntity(enemy);
    }


    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }

}