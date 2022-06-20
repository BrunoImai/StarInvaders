package br.com.battle;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import br.pucpr.jge.AbstractGameObject;
import br.pucpr.jge.GameObject;
import br.pucpr.jge.InputManager;
import br.pucpr.jge.SpriteSheet;

public class Explosion extends AbstractGameObject {

    public int frames = 0, maxFrames = 9, index = 0, maxIndex = 9;
    private BufferedImage[] explosion;
    public SpriteSheet spriteSheet;

    public Explosion(double x, double y) {
        super("/image/explosion.png", x, y);
        spriteSheet = new SpriteSheet("/image/explosion.png");
        explosion = new BufferedImage[10];
        explosion[0] = spriteSheet.getSprite(0, 0, 68, 51);
        explosion[1] = spriteSheet.getSprite(68, 0, 68, 51);
        explosion[2] = spriteSheet.getSprite(136, 0, 68, 51);
        explosion[3] = spriteSheet.getSprite(204, 0, 68, 51);
        explosion[4] = spriteSheet.getSprite(272, 0, 68, 51);
        explosion[5] = spriteSheet.getSprite(340, 0, 68, 51);
        explosion[6] = spriteSheet.getSprite(408, 0, 68, 51);
        explosion[7] = spriteSheet.getSprite(476, 0, 68, 51);
        explosion[8] = spriteSheet.getSprite(544, 0, 68, 51);
        explosion[9] = spriteSheet.getSprite(612, 0, 68, 51);
    }

    @Override
    public void update(double s, InputManager keys) {
        frames++;
        if(frames == maxFrames) {
            frames = 0;
            index++;
            if(index >= maxIndex) {
                index = maxIndex;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(explosion[index], (int) x, (int) y, null);
    }

    @Override
    public boolean isInGame() {
        return index < maxIndex;
    }
}