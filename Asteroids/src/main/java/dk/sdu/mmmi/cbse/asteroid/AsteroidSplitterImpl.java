package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.Random;

public class AsteroidSplitterImpl implements IAsteroidSplitter {

    private static final int MIN_SPLIT_SIZE = 20;

    @Override
    public void createSplitAsteroid(Entity asteroid, World world) {
        System.out.println("Creating split asteroid");

        world.removeEntity(asteroid);

        if (asteroid.getRadius() < MIN_SPLIT_SIZE) {
            return;
        }

        Asteroid newAsteroid = new Asteroid();
        int size = 10;
        newAsteroid.setRadius(size);
        newAsteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        newAsteroid.setX(asteroid.getX());
        newAsteroid.setY(asteroid.getY());
        world.addEntity(newAsteroid);
    }
}
