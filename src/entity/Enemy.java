package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Enemy extends Entity{
    GamePanel gp;
    public char direction;
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
        direction = 'r';
        x = worldX;
        y = worldY;

        hurtboxWidth = 48;
        hurtboxHeight = 48;
    }
    public void update() {
        if (x >=1000) {
            direction = 'l';
        } else if (x <= 300) {
            direction = 'r';
        }

        y += speed / 2;

        if (direction == 'r') {
            x += speed;
        } else {
            x -= speed;
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = enemyImage;
        g2.drawImage(image,x,y,48,48, null);
    }

}


