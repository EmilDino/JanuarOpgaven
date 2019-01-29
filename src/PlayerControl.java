import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.contacts.Velocity;

public class PlayerControl extends Control {

    // makes the player a physicsObject, controlled by gravity and velocity
    private PhysicsComponent physics;

    @Override
    public void onUpdate(Entity entity, double tpf) {

    }

    // this is so the player can only jump on ground
     boolean canJump = false;

    // determines how fast the player moves to the left
    public void left() {
        getEntity().setScaleX(-1);
        physics.setVelocityX(-150);
    }

    // determines how fast the player moves to the right
    public void right() {
        getEntity().setScaleX(1);
        physics.setVelocityX(150);
    }

    // determines how high the player jumps
    public void jump() {
        if (canJump == true) {
            physics.setVelocityY(-400);
            canJump = false;
        }

        if (physics.getVelocityY() == 0) {
            canJump = true;
        }
    }

    // sets the speed of the dive move
    public void dive () {
        physics.setVelocityY(500);
    }

    // slows down the player's horizontal movement speed, when not pressing A or D
    public void stop () {
        physics.setVelocityX(physics.getVelocityX() * 0.5);
    }
}
