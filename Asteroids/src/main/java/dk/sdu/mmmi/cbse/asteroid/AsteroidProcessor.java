package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class AsteroidProcessor implements IEntityProcessingService {

    private IAsteroidSplitter asteroidSplitter = new AsteroidSplitterImpl();

    private static final int MIN_ASTEROIDS = 3;
    private static final int MAX_ASTEROIDS = 6;

    private static final int MAX_SPLIT_COUNT = 3;

    @Override
    public void process(GameData gameData, World world) {
        int currentAsteroids = world.getEntities(Asteroid.class).size();
        while (currentAsteroids < MIN_ASTEROIDS) {
            spawnAsteroid(world, gameData);
            currentAsteroids++;
        }

        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            double changeX = Math.cos(Math.toRadians(asteroid.getRotation())) * 5;
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation())) * 5;
            asteroid.setX(asteroid.getX() + changeX);
            asteroid.setY(asteroid.getY() + changeY);

            if (asteroid.getX() < 0) {
                asteroid.setX(gameData.getDisplayWidth());
            } else if (asteroid.getX() > gameData.getDisplayWidth()) {
                asteroid.setX(0);
            }
            if (asteroid.getY() < 0) {
                asteroid.setY(gameData.getDisplayHeight());
            } else if (asteroid.getY() > gameData.getDisplayHeight()) {
                asteroid.setY(0);
            }
            if (asteroid.getRadius() > 6 && asteroid.getRadius() % 6 == 0) {
                splitAsteroidIfNeeded((Asteroid) asteroid, world);
            }
        }
    }

    private void splitAsteroidIfNeeded(Asteroid asteroid, World world) {
        if (asteroid.getSplitCount() < MAX_SPLIT_COUNT) {
            asteroidSplitter.createSplitAsteroid(asteroid, world);
            asteroid.incrementSplitCount(); // Increment split count
        }
    }

    private void spawnAsteroid(World world, GameData gameData) {
        if (world.getEntities(Asteroid.class).size() >= MAX_ASTEROIDS) {
            return;
        }

        Entity asteroid = createAsteroid(gameData);

        world.addEntity(asteroid);
    }

    private Entity createAsteroid(GameData gameData) {
        Entity asteroid = new Asteroid();
        Random rnd = new Random();
        int size = 20;
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(rnd.nextInt(gameData.getDisplayWidth()));  // Random X position within display width
        asteroid.setY(rnd.nextInt(gameData.getDisplayHeight())); // Random Y position within display height
        asteroid.setRadius(20);
        asteroid.setRotation(rnd.nextInt(360));
        return asteroid;
    }

    public void setAsteroidSplitter(IAsteroidSplitter asteroidSplitter) {
        this.asteroidSplitter = asteroidSplitter;
    }

    public void removeAsteroidSplitter(IAsteroidSplitter asteroidSplitter) {
        this.asteroidSplitter = null;
    }
}