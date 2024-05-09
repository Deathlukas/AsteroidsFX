package dk.sdu.mmmi.cbse.playersystem;

import static org.junit.Assert.*;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.playersystem.Player;
import dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;
import org.junit.Test;

public class PlayerPluginTest {

    @Test
    public void testStart() {
        // Create a PlayerPlugin instance
        PlayerPlugin playerPlugin = new PlayerPlugin();

        // Create mock GameData and World objects
        GameData gameData = new GameData();
        World world = new World();

        // Call the start method with the mock GameData object
        playerPlugin.start(gameData, world);

        // Check if the player entity is added to the world
        assertNotNull("Player entity should not be null", playerPlugin.getPlayer());
    }

    @Test
    public void testStop() {
        // Create a PlayerPlugin instance with the player entity
        PlayerPlugin playerPlugin = new PlayerPlugin();

        // Create mock GameData and World objects
        GameData gameData = new GameData();
        World world = new World();

        // Call the start method to add the player entity to the world
        playerPlugin.start(gameData, world);

        // Get the player entity from the PlayerPlugin
        Entity player = (Entity) playerPlugin.getPlayer();

        // Call the stop method
        playerPlugin.stop(gameData, world);

        // Check if the player entity is removed from the world
        assertFalse("Player entity should be removed from the world", world.getEntities().contains(player));
    }
    @Test
    public void testCreatePlayerShip() {
        // Create a PlayerPlugin instance
        PlayerPlugin playerPlugin = new PlayerPlugin();

        // Create mock GameData object
        GameData gameData = new GameData();

        // Call the createPlayerShip method
        Entity playerShip = playerPlugin.createPlayerShip(gameData);

        // Check if the player ship entity is created
        assertNotNull("Player ship entity should not be null", playerShip);
    }
}