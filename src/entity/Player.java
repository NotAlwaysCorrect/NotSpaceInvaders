package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    int fireRate = 15;

    int fireRateFrameCount = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        getPlayerImage();
        setDefaultValues();

    }

    public void setDefaultValues() {

        x =(int) (3840.00/1.5) / 2 - 24;
        y = 1200;
        speed = 10;
    }

    public void getPlayerImage() {
        try {
            playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/white_triangle.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
        if(keyH.leftPressed) {
            x -= speed;
        }
        else if (keyH.rightPressed) {
            x += speed;
        }

        if(gp.keyH.shotKeyPressed && fireRateFrameCount >= fireRate) {
            projectile = new Projectile(gp);
            projectile.set(this.x,this.y);
            System.out.println("X: " + this.x + ", Y: " + this.y);
            gp.projectileList.add(projectile);
            fireRateFrameCount = 0;
        }

        fireRateFrameCount++;

    }

    public void draw(Graphics2D g2) {
        BufferedImage image = playerImage;
        g2.drawImage(image,x,y,48,48, null);
    }
}

