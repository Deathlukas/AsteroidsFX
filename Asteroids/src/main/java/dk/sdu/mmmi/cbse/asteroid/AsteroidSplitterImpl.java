package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

public class AsteroidSplitterImpl implements IAsteroidSplitter {
    @Override
    public void createSplitAsteroid(Entity e, World w) {
        // Create two new asteroids based on the properties of the original asteroid
        Entity newAsteroid1 = createAsteroidBasedOn(e);
        Entity newAsteroid2 = createAsteroidBasedOn(e);

        // Add the new asteroids to the world
        w.addEntity(newAsteroid1);
        w.addEntity(newAsteroid2);
    }

    // Helper method to create a new asteroid based on the properties of an existing asteroid
    private Entity createAsteroidBasedOn(Entity originalAsteroid) {
        Entity newAsteroid = new Asteroid();
        // Copy properties from the original asteroid
        newAsteroid.setPolygonCoordinates(originalAsteroid.getPolygonCoordinates());
        newAsteroid.setX(originalAsteroid.getX());
        newAsteroid.setY(originalAsteroid.getY());
        newAsteroid.setRadius(originalAsteroid.getRadius());
        newAsteroid.setRotation(originalAsteroid.getRotation());
        // Add any additional logic for the new asteroid if needed
        return newAsteroid;
    }
}