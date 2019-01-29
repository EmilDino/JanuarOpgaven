import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

import java.util.Map;


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

            @Override
            protected void onActionEnd() {
                player.getControl(PlayerControl.class).stop();
            }
        }, KeyCode.A);

        getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).right();
            }

            @Override
            protected void onActionEnd() {
                player.getControl(PlayerControl.class).stop();
            }
        }, KeyCode.D);

        getInput().addAction(new UserAction("Jump") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).jump();
            }
        }, KeyCode.W);

        getInput().addAction(new UserAction("Dive") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).dive();
            }
        }, KeyCode.S);
    }

    // Gets the map created in Tiled and makes it the map of the game
    @Override
    protected void initGame() {
        getGameWorld().setEntityFactory(new PlatformerFactory());
        getGameWorld().setLevelFromMap("Platformergame1.json");

        // Sets spawnloction of the player
        player = getGameWorld().spawn("player", 50, 50);
    }

    // handles collisions
    @Override
    protected void initPhysics() {
        // removes coins when touched by the player
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(PlatformerType.PLAYER, PlatformerType.COIN) {
            @Override
            protected void onCollisionBegin(Entity player, Entity coin) {
                coin.removeFromWorld();
                getGameState().increment("coinsCollected", +1);
            }
        });

        // shows that the level is completed when the player  touches the door
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(PlatformerType.PLAYER, PlatformerType.DOOR) {
            @Override
            protected void onCollisionBegin(Entity player, Entity door) {
                getDisplay().showMessageBox("level complete", () -> {
                    System.out.println("Dialog closed");
                });
            }
        });
    }

    // coin counter
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("coinsCollected", 0);
    }

    // sets the location of the coin counter
    @Override
    protected void initUI() {
        Text textPixels = new Text();
        textPixels.setTranslateX(50);
        textPixels.setTranslateY(100);

        textPixels.textProperty().bind(getGameState().intProperty("coinsCollected").asString());

        getGameScene().addUINode(textPixels);
    }


    // Launches the game
    public static void main(String[] args) {
        launch(args);
    }
}
