package entity;

import main.GamePanel;

public class Enemy extends Entity{
    GamePanel gp;
    public Enemy(GamePanel gp) {
        speed = 5;
        this.gp = gp;
    }

    public void update() {

    }
}


