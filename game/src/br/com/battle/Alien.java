package br.com.battle;

import br.pucpr.jge.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Alien extends AbstractGameObject {
    private double initialX;
    private double t;
    private boolean isAlive = true;
    private double shotInterval = 0.0;

    private final List<Listener> listeners = new ArrayList<Listener>();


    public Alien(double x, double y) {
        super("/image/destroyer.png", x, y);
        this.initialX = x;
    }

    public void update(double s, InputManager keys) {
        x = initialX + Math.sin(t) * 50;
        t += s;
        shotInterval+= s;
        if (t >= 1) {
            if (canShot()) {
                var shot = new EnemyShot(getX() + 25, getY() + this.sprite.getHeight());
                GameManager.getInstance().add(shot);
                shotInterval = 0;
            }
        }
    }

    public boolean canShot() {
        Random rand = new Random();

        int int_random = rand.nextInt(10000);

        return int_random < 10;

    }


    @Override
    public boolean isInGame() {
        return isAlive;
    }


    @Override
    public void onCollision(GameObject other) {
        if (other instanceof DebugDecorated) {
            if (((DebugDecorated) other).debugObject instanceof Shot) {
                isAlive = false;
                GameManager.getInstance().add(new Explosion(this.getX(), this.getY()));
                notifyObserver();
            }
        }

        if (other instanceof Shot) {
            isAlive = false;
            GameManager.getInstance().add(new Explosion(this.getX(), this.getY()));
            notifyObserver();
        }
    }

    private void notifyObserver() {
        for (Listener listener : listeners) {
            listener.notify(this);
        }
    }
    public void addObserver(Listener listener) { //também chamado de addListener(...)
        listeners.add(listener); //"obs" passará a ser notificado sobre mudanças em this
    }
    public void removerListener(Listener listener) { //também chamado de removeListener(...)
        listeners.remove(listener); //"obs" deixará de ser notificado sobre mudanças em this
    }
}
