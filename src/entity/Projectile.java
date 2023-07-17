package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Projectile extends Entity{
    GamePanel gp;

    public Projectile(GamePanel gp) {
        speed = 10;
        this.gp = gp;
        getProjectileImage();
    }

    public void getProjectileImage() {
        try {
            projectileImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/Projectile.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void set(int worldX, int worldY) {
        x = worldX;
        y = worldY;
    }
    public void update() {
        y -= speed;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = projectileImage;
        g2.drawImage(image,x,y,48,48, null);
    }

}
