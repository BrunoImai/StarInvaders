package br.com.battle;

import br.pucpr.jge.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Boss extends AbstractGameObject {
    private double initialX;
    private double t;
    private double shotInterval = 0.0;
    private int lifes = 10;

    private final List<Listener> listeners = new ArrayList<Listener>();


    public Boss(double x, double y) {
        super("/image/boss.png", x, y);
        this.initialX = x;
    }

    public void update(double s, InputManager keys) {
        x = initialX + Math.sin(t) * 500;
        t += s;
        shotInterval+= s;
        if (t >= 1) {
            if (canShot()) {
                var shot = new EnemyShot(getX() + 20, getY() + this.sprite.getHeight() );
                GameManager.getInstance().add(shot);

                var shot2 = new EnemyShot(getX() + this.sprite.getWidth()/2.0, getY() + this.sprite.getHeight() );
                GameManager.getInstance().add(shot2);

                var shot3 = new EnemyShot(getX() + this.sprite.getWidth() - 20, getY() + this.sprite.getHeight() );
                GameManager.getInstance().add(shot3);
                shotInterval = 0;
            }
        }
    }

    public boolean canShot() {
        Random rand = new Random();

        int int_random = rand.nextInt(10000);

        return int_random < 100;

    }


    @Override
    public boolean isInGame() {
        return  isAlive();
    }


    public boolean isAlive() {
        if (lifes <= 0){
            notifyObserver();
            return false;
        };

        return true;
    }

    @Override
    public void onCollision(GameObject other) {
        if (other instanceof DebugDecorated) {
            if (((DebugDecorated) other).debugObject instanceof Shot) {
                System.out.println(lifes);
                lifes--;
            }
        }

        if (other instanceof Shot) {
            System.out.println(lifes);
            lifes--;
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
