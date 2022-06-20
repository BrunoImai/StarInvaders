package br.pucpr.jge;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AbstractGameObject implements GameObject {
    protected double x;
    protected double y;
    protected BufferedImage sprite;
    private final String spriteName;

    public AbstractGameObject(String spriteName, double x, double y) {
        this.spriteName = spriteName;
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void load() {
        sprite = new Loader().loadImage(spriteName);
    }

    @Override
    public void update(double s, InputManager keys) {

    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(sprite, (int)x, (int)y, null);
    }

    @Override
    public boolean isInGame() {
        return true;
    }

    @Override
    public Rectangle getHitBox() {
        return new Rectangle((int)x, (int)y, sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void onCollision(GameObject other) {

    }
}
