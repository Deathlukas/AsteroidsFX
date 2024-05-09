package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.playersystem.Player;
import dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PlayerControlSystemTest {

    @Test
    public void PlayerMovesUpWhenUpKeyIsDown() {

        GameData gameData = new GameData();
        World world = new World();
        PlayerControlSystem controlSystem = new PlayerControlSystem();
        Entity player = new Player();
        player.setY(100);
        world.addEntity(player);

        gameData.getKeys().setKey(GameKeys.UP, true);
        controlSystem.process(gameData, world);

        assertEquals(100.0 - Math.sin(Math.toRadians(player.getRotation())), player.getY(), 0.1);
    }

    @Test
    public void PlayerRotatesLeftWhenLeftKeyIsDown() {

        GameData gameData = new GameData();
        World world = new World();
        PlayerControlSystem controlSystem = new PlayerControlSystem();
        Entity player = new Player();
        player.setRotation(0);
        world.addEntity(player);

        gameData.getKeys().setKey(GameKeys.LEFT, true);
        controlSystem.process(gameData, world);

        assertEquals(0.0 - 5.0, player.getRotation(), 0.1);
    }
    @Test
    public void PlayerRotatesRightWhenRightKeyIsDown() {

        GameData gameData = new GameData();
        World world = new World();
        PlayerControlSystem controlSystem = new PlayerControlSystem();
        Entity player = new Player();
        player.setRotation(0);
        world.addEntity(player);

        gameData.getKeys().setKey(GameKeys.RIGHT, true);
        controlSystem.process(gameData, world);

        assertEquals(0.0 + 5.0, player.getRotation(), 0.1);
    }
}