package br.pucpr.jge;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Loader {
    private final String root;

    public Loader(String root) {
        this.root = root;
    }

    public Loader() {
        this("/assets");
    }

    BufferedImage createErrorImage() {
        var img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

        var g2d = img.getGraphics();
        g2d.setColor(Color.MAGENTA);
        g2d.fillRect(0, 0, 100, 100);
        g2d.dispose();

        return img;
    }

    public BufferedImage loadImage(String path) {
        var name = root + path;
        try {
            if (GameManager.getInstance().imageList.containsKey(name)){
//                System.out.println("Hash");
                return GameManager.getInstance().imageList.get(name);
            }
            GameManager.getInstance().imageList.put(name, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(name))));
//            System.out.println("Mem");
            return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(name)));
        } catch (Exception e) {
            System.err.println("Unable to load " + name);
            return createErrorImage();
        }
    }
}
