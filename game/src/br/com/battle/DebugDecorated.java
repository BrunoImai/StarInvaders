package br.com.battle;

import br.pucpr.jge.GameObject;
import br.pucpr.jge.InputManager;

import java.awt.*;

public class DebugDecorated extends DebugDecorator{
    public DebugDecorated(GameObject object) {
        super(object);
    }

    @Override
    public double getX() {
         return debugObject.getX();
    }

    @Override
    public double getY() {
        return debugObject.getY();
    }

    @Override
    public void load() {
        debugObject.load();
    }

    @Override
    public void update(double s, InputManager keys) {
        debugObject.update(s,keys);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.draw(debugObject.getHitBox());
        debugObject.draw(g2d);
    }

    @Override
    public boolean isInGame() {
        return debugObject.isInGame();
    }

    @Override
    public Rectangle getHitBox() {
        return debugObject.getHitBox();
    }

    @Override
    public void onCollision(GameObject other) {
        debugObject.onCollision(other);
    }
}
