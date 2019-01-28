import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import com.almasb.fxgl.entity.SetEntityFactory;
import javafx.scene.shape.Rectangle;


// Generates all of the entities in the game
@SetEntityFactory
public class PlatformerFactory implements EntityFactory {

    // Spawns all objects marked as platforms in Tiled, in the game
    @Spawns("platform")
    public Entity newPlatform(SpawnData data) {
        return Entities.builder()
                .type(PlatformerType.PLATFORM)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .build();
    }

    // spawns the door in game
    @Spawns("door")
    public Entity newDoor(SpawnData data) {
        return Entities.builder()
                .type(PlatformerType.DOOR)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }

    // Spawns the player
    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        return Entities.builder()
                .type(PlatformerType.PLAYER)
                .from(data)
                .viewFromNodeWithBBox(new Rectangle(30, 30, Color.BLUE))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new PlayerControl())
                .build();
    }



    // Spawns all objects marked as coins in Tiled, in the game
    @Spawns("coin")
    public Entity newcoin (SpawnData data) {
        return Entities.builder()
                .type(PlatformerType.COIN)
                .from(data)
                .viewFromNodeWithBBox(new Circle(data.<Integer>get("width") / 2, Color.GOLD))
                //.with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }

}
