import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires CommonBullet;
    requires CommonAsteroids;

    uses dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;

    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collisionsystem.CollisionDetector;
    opens dk.sdu.mmmi.cbse.collisionsystem to JavaFX.graphics;
    exports dk.sdu.mmmi.cbse.collisionsystem;
}