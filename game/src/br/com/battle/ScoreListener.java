package br.com.battle;

import br.pucpr.jge.Listener;

public class ScoreListener implements Listener {
    int scorePoints = 0;

    public void notify(Object obs) {
        scorePoints += 100;
    }
}
