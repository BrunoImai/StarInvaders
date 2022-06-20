package br.com.battle;

import br.pucpr.jge.Listener;

public class LifeListener implements Listener{
        int lifes = 3;

        public void notify(Object obs) {
             lifes --;
        }

}
