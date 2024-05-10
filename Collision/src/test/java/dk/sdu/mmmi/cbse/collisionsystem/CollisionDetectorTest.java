package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.asteroid.AsteroidSplitterImpl;
import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.enemysystem.Enemy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CollisionDetectorTest {

    private CollisionDetector collisionDetector;
    private GameData gameData;
    private World world;

    @Before
    public void setUp() {
        IAsteroidSplitter asteroidSplitter = new AsteroidSplitterImpl();
        collisionDetector = new CollisionDetector(asteroidSplitter);
        gameData = new GameData();
        world = new World();
    }

    @Test
    public void testBulletAsteroidCollision() {
        Bullet bullet = new Bullet();
        bullet.setX(100);
        bullet.setY(100);
        bullet.setRadius(5);
        world.addEntity(bullet);

        Asteroid asteroid = new Asteroid();
        asteroid.setX(100);
        asteroid.setY(100);
        asteroid.setRadius(20);
        world.addEntity(asteroid);

        collisionDetector.process(gameData, world);

        assertTrue("Bullet and asteroid should collide", world.getEntities().isEmpty());
    }

    @Test
    public void testNoBulletAsteroidCollision() {
        Bullet bullet = new Bullet();
        bullet.setX(100);
        bullet.setY(100);
        bullet.setRadius(5);
        world.addEntity(bullet);

        Asteroid asteroid = new Asteroid();
        asteroid.setX(200);
        asteroid.setY(200);
        asteroid.setRadius(20);
        world.addEntity(asteroid);

        collisionDetector.process(gameData, world);

        assertEquals("Bullet and asteroid should not collide", 2, world.getEntities().size());
    }

    @Test
    public void testnobulletEnemyCollision() {
        Bullet bullet = new Bullet();
        bullet.setX(100);
        bullet.setY(100);
        bullet.setRadius(5);
        world.addEntity(bullet);

        Enemy enemy = new Enemy(gameData);
        enemy.setX(200);
        enemy.setY(200);
        enemy.setRadius(20);
        world.addEntity(enemy);

        collisionDetector.process(gameData, world);

        assertEquals("Bullet and enemy should not collide", 2, world.getEntities().size());
    }
    @Test
    public void testCollision() {

        CollisionDetector collisionDetector = new CollisionDetector();

        Entity entity1 = new Entity();
        entity1.setX(0);
        entity1.setY(0);
        entity1.setRadius(5);

        Entity entity2 = new Entity();
        entity2.setX(5);
        entity2.setY(0);
        entity2.setRadius(5);

        assertTrue("Entities should collide", collisionDetector.collides(entity1, entity2));
    }
    @Test
    public void testNoCollision() {

        CollisionDetector collisionDetector = new CollisionDetector();

        Entity entity1 = new Entity();
        entity1.setX(0);
        entity1.setY(0);
        entity1.setRadius(5);

        Entity entity2 = new Entity();
        entity2.setX(20);
        entity2.setY(20);
        entity2.setRadius(5);

        assertFalse("Entities should not collide", collisionDetector.collides(entity1, entity2));
    }
}
