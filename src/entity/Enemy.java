package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Enemy extends Entity{
    GamePanel gp;
    public Enemy(GamePanel gp) {
        speed = 5;
        this.gp = gp;
        getEnemyImage();
    }
    public void getEnemyImage() {
        try {
            enemyImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/red_triangle.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void set(int worldX, int worldY) {
        x = worldX;
        y = worldY;
    }
    public void update() {
        y += speed/2;
        x += speed;
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = enemyImage;
        g2.drawImage(image,x,y,48,48, null);
    }

}


