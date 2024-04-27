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

    private static final int MIN_ASTEROIDS = 3; // Minimum number of asteroids to maintain
    private static final int MAX_ASTEROIDS = 6; // Maximum number of asteroids allowed
    @Override
    public void process(GameData gameData, World world) {
        // Ensure there are enough asteroids in the world
        int currentAsteroids = world.getEntities(Asteroid.class).size();
        while (currentAsteroids < MIN_ASTEROIDS) {
            spawnAsteroid(world, gameData);
            currentAsteroids++; // Increment the current number of asteroids
        }

        // Move existing asteroids
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            // Move asteroid based on its rotation
            double changeX = Math.cos(Math.toRadians(asteroid.getRotation())) * 5;
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation())) * 5;
            asteroid.setX(asteroid.getX() + changeX);
            asteroid.setY(asteroid.getY() + changeY);

            // Check if asteroid moves out of bounds and reset its position
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
        }
    }

    private void spawnAsteroid(World world, GameData gameData) {
        // Ensure maximum number of asteroids is not exceeded
        if (world.getEntities(Asteroid.class).size() >= MAX_ASTEROIDS) {
            return;
        }

        // Create a new asteroid
        Entity asteroid = createAsteroid(gameData);

        // Add the new asteroid to the world
        world.addEntity(asteroid);
    }

    private Entity createAsteroid(GameData gameData) {
        Entity asteroid = new Asteroid();
        Random rnd = new Random();
        int size = rnd.nextInt(10) + 5;
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(rnd.nextInt(gameData.getDisplayWidth()));  // Random X position within display width
        asteroid.setY(rnd.nextInt(gameData.getDisplayHeight())); // Random Y position within display height
        asteroid.setRadius(20);
        asteroid.setRotation(rnd.nextInt(360));  // Random rotation angle (0 to 359 degrees)

        System.out.println("New asteroid created: " + asteroid);
        System.out.println("Position: (" + asteroid.getX() + ", " + asteroid.getY() + ")");

        return asteroid;
    }

    public void setAsteroidSplitter(IAsteroidSplitter asteroidSplitter) {
        this.asteroidSplitter = asteroidSplitter;
    }

    public void removeAsteroidSplitter(IAsteroidSplitter asteroidSplitter) {
        this.asteroidSplitter = null;
    }
}
