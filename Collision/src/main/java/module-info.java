import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.collisionsystem.CollisionDetector;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;

module Collision {
    requires Common;
    requires Asteroids;
    requires Player;
    requires Enemy;
    requires CommonBullet;
    requires javafx.graphics;
    requires CommonAsteroids;

    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collisionsystem.CollisionDetector;
    opens dk.sdu.mmmi.cbse.collisionsystem to JavaFX.graphics;
    exports dk.sdu.mmmi.cbse.collisionsystem;
}