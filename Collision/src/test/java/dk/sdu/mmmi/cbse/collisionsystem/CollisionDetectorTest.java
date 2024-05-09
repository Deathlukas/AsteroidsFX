package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.asteroid.AsteroidSplitterImpl;
import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.enemysystem.Enemy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
}
