import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;


public class PlatformerApp extends GameApplication {

    // Sets the size of the scene, which is equal to the size of the map made with the Tiled application
    @Override
    protected void initSettings (GameSettings settings) {
        settings.setWidth(15 * 70);
        settings.setHeight(10* 70);
    }

    private Entity player;

    // Player controls
    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).left();
            }
        }, KeyCode.A);

        getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).right();
            }
        }, KeyCode.D);

        getInput().addAction(new UserAction("Jump") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).jump();
            }
        }, KeyCode.W);
    }

    // Gets the map created in Tiled and makes it the map of the game
    @Override
    protected void initGame() {
        getGameWorld().setEntityFactory(new PlatformerFactory());
        getGameWorld().setLevelFromMap("Platformergame1.json");

        // Sets spawnloction of the player
        player = getGameWorld().spawn("player", 50, 50);
    }

    // handles collisions and removes coins when touched by the player
    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(PlatformerType.PLAYER, PlatformerType.COIN) {
            @Override
            protected void onCollisionBegin(Entity player, Entity coin) {
                coin.removeFromWorld();
            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(PlatformerType.PLAYER, PlatformerType.DOOR) {
            @Override
            protected void onCollisionBegin(Entity player, Entity door) {
                getDisplay().showMessageBox("level complete", () -> {
                    System.out.println("Dialog closed");
                });
            }
        });
    }

    // Launches the game
    public static void main(String[] args) {
        launch(args);
    }
}
