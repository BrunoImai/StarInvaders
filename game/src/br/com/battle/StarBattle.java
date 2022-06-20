package br.com.battle;

import br.pucpr.jge.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static java.awt.event.KeyEvent.*;

// BRUNO IMAI, MARCELLA RESENDE, JOÃO XAVIER

public class StarBattle implements Steps {
    ScoreListener score = new ScoreListener();
    LifeListener shipLife = new LifeListener();

    boolean bossLoaded = false;
    @Override
    public void load() {

        var ship = new Ship() ;
        ship.addObserver(shipLife);
        GameManager.getInstance().add(ship);

        for (var y = 0; y < 4; y++) {
            for (var x = 0; x < 5; x++) {
                var xOff = 50 + 50 * (y % 2);
                var alien = new Alien(x * 150 + xOff, y * 75 + 25);
                alien.addObserver(score);
                GameManager.getInstance().add(alien);
            }
        }
    }

    @Override
    public boolean update(double s, InputManager keys) {
        if (score.scorePoints >= 2000 && !bossLoaded) {
            System.out.println(score.scorePoints);
            var boss = new Boss(100,-100);
            boss.addObserver(score);
            GameManager.getInstance().add(boss);
            bossLoaded = true;
        }

        return !keys.isDown(VK_ESCAPE);
    }

    @Override
    public void draw(Graphics2D g2d) throws IOException, FontFormatException {
        Font f = Font.createFont(Font.TRUETYPE_FONT, new File("game/src/br/com/battle/PressStart2P.ttf"));
        var font = f.deriveFont(Font.PLAIN,16f);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        g2d.fillRect(0, 0, 800, 600);
        g2d.setColor(Color.yellow);
        g2d.setFont(font);
        g2d.drawString( "Score "+ String.valueOf(score.scorePoints) ,620,590);
        g2d.drawString( "Lifes "+ String.valueOf(shipLife.lifes) ,10,590);


        font = f.deriveFont(Font.PLAIN,30f);
        g2d.setFont(font);
        if (shipLife.lifes <= 0) {
            g2d.drawString(  String.valueOf("GAME OVER") ,270,300);
        }

        if (score.scorePoints >= 2100) {
            g2d.drawString(  String.valueOf("YOU WIN!!!") ,270,300);
        }
    }

    @Override
    public void unload() {

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
            new GameFrame("Star Battle",
                800, 600,
                new InitialScreen()
            ).setVisible(true)
        );
    }
}
