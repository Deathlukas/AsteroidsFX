package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.asteroid.AsteroidSplitterImpl;
import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.ArrayList;
import java.util.List;

public class CollisionDetector implements IPostEntityProcessingService {

    private IAsteroidSplitter asteroidSplitter;
    private static final int MAX_SPLIT_COUNT = 2;

    public CollisionDetector(IAsteroidSplitter asteroidSplitter) {
        this.asteroidSplitter = asteroidSplitter;
    }

    public CollisionDetector() {
        this.asteroidSplitter = (IAsteroidSplitter) new AsteroidSplitterImpl();
    }

    @Override
    public void process(GameData gameData, World world) {
        List<Entity> asteroidsToRemove = new ArrayList<>();

        for (Entity entity : world.getEntities()) {
            if (entity instanceof Bullet) {
                handleBulletCollision(entity, world, asteroidsToRemove);
            } else if (entity instanceof Asteroid) {
                handleAsteroidCollision(entity, world, asteroidsToRemove);
            }
        }

        for (Entity asteroid : asteroidsToRemove) {
            world.removeEntity(asteroid);
        }
    }

    private void handleBulletCollision(Entity bullet, World world, List<Entity> asteroidsToRemove) {
        for (Entity entity : world.getEntities()) {
            if (entity instanceof Asteroid && collides(bullet, entity)) {
                world.removeEntity(bullet);
                asteroidsToRemove.add(entity);
                break;
            }
        }
    }

    private void handleAsteroidCollision(Entity asteroid, World world, List<Entity> asteroidsToRemove) {
        for (Entity entity : world.getEntities()) {
            if (entity instanceof Asteroid && collides(asteroid, entity) && asteroid != entity) {
                Asteroid asteroid1 = (Asteroid) asteroid;
                Asteroid asteroid2 = (Asteroid) entity;

                if (canSplit(asteroid1) && canSplit(asteroid2)) {
                    asteroidSplitter.createSplitAsteroid(asteroid1, world);
                    asteroidSplitter.createSplitAsteroid(asteroid2, world);
                    asteroid1.incrementSplitCount();
                    asteroid2.incrementSplitCount();
                }

                asteroidsToRemove.add(asteroid);
                asteroidsToRemove.add(entity);
                break;
            }
        }
    }

    private boolean canSplit(Asteroid asteroid) {
        return asteroid.getSplitCount() < MAX_SPLIT_COUNT;
    }

    private boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) (entity1.getX() - entity2.getX());
        float dy = (float) (entity1.getY() - entity2.getY());
        float distanceSquared = dx * dx + dy * dy;
        float radiusSum = entity1.getRadius() + entity2.getRadius();
        return distanceSquared <= radiusSum * radiusSum;
    }
}