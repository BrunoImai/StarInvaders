package br.com.battle;

import br.pucpr.jge.AbstractGameObject;
import br.pucpr.jge.InputManager;
import br.pucpr.jge.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Shield extends AbstractGameObject {

    public int frames = 0, maxFrames = 19, index = 0, maxIndex = 19;
    private BufferedImage[] shield;
    public SpriteSheet spriteSheet;

    public Shield(double x, double y) {
        super("/image/shield.png", x, y);
        spriteSheet = new SpriteSheet("/image/shield.png");
        shield = new BufferedImage[20];
        shield[0] = spriteSheet.getSprite(0, 0, 58, 50);
        shield[1] = spriteSheet.getSprite(58, 0, 58, 50);
        shield[2] = spriteSheet.getSprite(116, 0, 58, 50);
        shield[3] = spriteSheet.getSprite(174, 0, 58, 50);
        shield[4] = spriteSheet.getSprite(232, 0, 58, 50);

        shield[5] = spriteSheet.getSprite(0, 50, 58, 50);
        shield[6] = spriteSheet.getSprite(58, 50, 58, 50);
        shield[7] = spriteSheet.getSprite(116, 50, 58, 50);
        shield[8] = spriteSheet.getSprite(174, 50, 58, 50);
        shield[9] = spriteSheet.getSprite(232, 50, 58, 50);

        shield[10] = spriteSheet.getSprite(0, 100, 58, 50);
        shield[11] = spriteSheet.getSprite(58, 100, 58, 50);
        shield[12] = spriteSheet.getSprite(116, 100, 58, 50);
        shield[13] = spriteSheet.getSprite(174, 100, 58, 50);
        shield[14] = spriteSheet.getSprite(232, 100, 58, 50);

        shield[15] = spriteSheet.getSprite(0, 150, 58, 50);
        shield[16] = spriteSheet.getSprite(58, 150, 58, 50);
        shield[17] = spriteSheet.getSprite(116, 150, 58, 50);
        shield[18] = spriteSheet.getSprite(174, 150, 58, 50);
        shield[19] = spriteSheet.getSprite(232, 150, 58, 50);
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
        g2d.drawImage(shield[index], (int) x, (int) y, null);
        System.out.println(isInGame());
    }

    @Override
    public boolean isInGame() {
        return index < maxIndex;
    }
}