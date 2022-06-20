package br.pucpr.jge;

import java.awt.*;

public interface GameObject {
    double getX();

    double getY();

    void load();

    void update(double s, InputManager keys);

    void draw(Graphics2D g2d);

    boolean isInGame();

    Rectangle getHitBox();

    void onCollision(GameObject other);
}
