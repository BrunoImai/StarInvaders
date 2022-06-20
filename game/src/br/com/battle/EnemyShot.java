package br.com.battle;

import br.pucpr.jge.AbstractGameObject;
import br.pucpr.jge.GameObject;
import br.pucpr.jge.InputManager;

public class EnemyShot extends AbstractGameObject {
    private boolean isAlive = true;


    public EnemyShot(double x, double y) {
        super("/image/intruderShot.png", x, y);

    }

    public void update(double s, InputManager keys) {
        y += 400 * s;
    }

    @Override
    public boolean isInGame() {
        return y < 630 && isAlive;
    }


    @Override
    public void onCollision(GameObject other) {
        if (other instanceof DebugDecorated) {
            if (((DebugDecorated) other).debugObject instanceof Ship) {
                isAlive = false;
            }
        }
        if (other instanceof Ship) {
            isAlive = false;
        }
    }
}
