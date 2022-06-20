package br.pucpr.jge;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private final BufferedImage spritesheet;

    public SpriteSheet(String path) {
        spritesheet = new Loader().loadImage(path);
    }

    public BufferedImage getSprite(int x, int y, int width, int height) {
        return spritesheet.getSubimage(x, y, width, height);
    }
}