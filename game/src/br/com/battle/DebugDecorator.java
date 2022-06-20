package br.com.battle;

import br.pucpr.jge.GameObject;

public abstract class DebugDecorator implements GameObject{

    protected GameObject debugObject;

    public DebugDecorator(GameObject debugRectangle) {
        this.debugObject = debugRectangle;
    }

}
