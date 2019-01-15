import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.settings.GameSettings;


public class PlatformerApp extends GameApplication {

    @Override
    protected void initSettings (GameSettings settings) {
        settings.setWidth(15 * 70);
        settings.setHeight(10* 70);

    }
    @Override
    protected void initGame() {
        getGameWorld().setLevelFromMap("Platformergame1.json");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
