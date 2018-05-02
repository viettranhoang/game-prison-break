package vn.ptit.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import vn.ptit.model.GameObject;


public class ImageDrawer implements IGameDrawer {
    private Image image;

    public ImageDrawer(String url) {
        try {
            this.image = ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(GameObject gameObject, Graphics g) {
        g.drawImage(this.image,
                gameObject.getX(),
                gameObject.getY(),
                gameObject.getWidth(),
                gameObject.getHeight(),
                null);
    }
}
