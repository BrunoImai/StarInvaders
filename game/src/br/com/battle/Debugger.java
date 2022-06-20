package br.com.battle;

import br.pucpr.jge.GameObject;

import java.awt.*;

public interface Debugger {
    void draw(Graphics2D g2d);
    public GameObject gameObject = null;
}
