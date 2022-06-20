package br.com.battle;

import br.pucpr.jge.GameFrame;
import br.pucpr.jge.InputManager;
import br.pucpr.jge.Steps;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static java.awt.event.KeyEvent.VK_ESCAPE;
import static java.awt.event.KeyEvent.VK_SPACE;

public class InitialScreen implements Steps {
    @Override
    public void load() {

    }

    @Override
    public boolean update(double s, InputManager keys) {

        if (keys.isDown(VK_SPACE)) {
            EventQueue.invokeLater(() ->
                    new GameFrame("Star Battle",
                            800, 600,
                            new StarBattle()
                    ).setVisible(true)
            );
            return false;
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
        g2d.drawString( String.valueOf("Marcella Resende, Bruno Imai, João Xavier") ,90,350);
        g2d.drawString( String.valueOf("Press Backspace to begin") ,200,400);
        g2d.drawString( String.valueOf("Press esc to close") ,250,450);

        font = f.deriveFont(Font.PLAIN,40f);
        g2d.setFont(font);

        g2d.drawString(  String.valueOf("Star Invasion!!!") ,110,250);

    }

    @Override
    public void unload() {

    }
}
