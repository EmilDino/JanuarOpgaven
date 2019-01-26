import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;

public class PlayerControl extends Control {

    // makes the player a physicsObject, controlled by gravity and velocity
    private PhysicsComponent physics;

    @Override
    public void onUpdate(Entity entity, double tpf) {

    }

    // determines how fast the player moves to the left
    public void left() {
        physics.setVelocityX(-100);
    }

    // determines how fast the player moves to the right
    public void right() {
        physics.setVelocityX(100);
    }

    // determines how much the player jumps
    public void jump() {
        physics.setVelocityY(-300);
    }
}
