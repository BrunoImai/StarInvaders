package br.com.battle;

import br.pucpr.jge.AbstractGameObject;
import br.pucpr.jge.GameObject;
import br.pucpr.jge.InputManager;

public class Shot extends AbstractGameObject {
    private boolean isAlive = true;


    public Shot(double x, double y) {
        super("/image/shot.png", x, y);

    }

    public void update(double s, InputManager keys) {
        y -= 800 * s;
    }

    @Override
    public boolean isInGame() {
        return y > -50 && isAlive;
    }


    @Override
    public void onCollision(GameObject other) {
        if (other instanceof DebugDecorated) {
            if (((DebugDecorated) other).debugObject instanceof Alien) {
                isAlive = false;
            }
        }
        if (other instanceof Alien || other instanceof Boss) {
            isAlive = false;
        }
    }
}
