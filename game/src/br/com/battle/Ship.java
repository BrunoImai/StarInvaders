package br.com.battle;

import br.pucpr.jge.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.awt.event.KeyEvent.*;

public class Ship extends AbstractGameObject {
    private double shotIntervalMachineGun = 0.3;
    private double shotIntervalCannon = 1.3;
    private boolean blink = false;
    private int lifes = 3;
    private boolean invencible = false;
    private double invencibleInterval = 0;
    private double blinkInterval = 0;

    public Ship() {
        super("/image/intruder.png", 0, 500);
    }
    private final List<Listener> listeners = new ArrayList<Listener>();

    public void update(double s, InputManager keys) {
        shotIntervalMachineGun += s;
        blink = false;

        if (invencible) {
            blinkInterval += s;

            if (blinkInterval >= 0.01) {
                blink = false;
                blinkInterval = 0;
            } else {
                blink = true;
            }

            invencibleInterval += s;

            if (invencibleInterval >= 2) {
                invencibleInterval = 0;
                System.out.println("Perdi Invencibilidade");
                invencible = false;
            }
        }


        if (keys.isDown(VK_RIGHT)) {
            x += 400 * s;
        } else if (keys.isDown(VK_LEFT)) {
            x -= 400 * s;
        }

        if (keys.isDown(VK_Z) && shotIntervalMachineGun > 0.3) {
            var shot = new Shot(getX() + 25, getY() - 10);
            GameManager.getInstance().add(shot);
            shotIntervalMachineGun = 0;
        }


        if (keys.isDown(VK_X) && shotIntervalCannon > 1.3) {
            var shot = new ShotCannon(getX() + 25, getY() - 100);
            GameManager.getInstance().add(shot);
            shotIntervalCannon = 0;
        }

        if (!isAlive()) GameManager.getInstance().add(new Explosion(this.getX(), this.getY()));

    }

    @Override
    public boolean isInGame() {
        return isAlive() ;
    }

    public boolean isAlive() {

        return lifes >0;
    }

    @Override
    public void onCollision(GameObject other) {
        if (!invencible) {
            if (other instanceof DebugDecorated) {
                if (((DebugDecorated) other).debugObject instanceof EnemyShot) {
                    lifes -= 1;
                    notifyObserver();
                    invencible = true;
                    System.out.println("Invencibilidade");

                }
            }

            if (other instanceof EnemyShot) {
                lifes -= 1;
                notifyObserver();
                invencible = true;
                System.out.println("Invencibilidade");
            }
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

    @Override
    public void draw(Graphics2D g2d) {
        if (!blink) g2d.drawImage(sprite, (int)x, (int)y, null);
    }
}
