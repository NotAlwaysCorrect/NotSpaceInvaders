package main;

import entity.Enemy;
import entity.Entity;
import entity.Player;
import entity.Projectile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{

////    Screen Settings
//    final int ORIGINAL_TILE_SIZE = 16;
//    final int SCALE = 3;
//    final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
//    final int MAX_SCREEN_COLUMN = 16;
//    final int MAX_SCREEN_ROW = 12;
//    final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN;
//    final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

    int FPS = 60;

    public KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    public ArrayList<Projectile> projectileList = new ArrayList<>();
    public ArrayList<Enemy> enemyList = new ArrayList<>();
    Enemy enemy;
    int enemySpawnFrameRate = 40;
    int getEnemySpawnFrameRateCount = 0;
    int spawnCount = 0;



//    Set player's default position
    public GamePanel() {

//        Screen settings youtube tutorial
//        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update() {

        player.update();

        for (Projectile projectile : projectileList) {
            if (projectile != null) {
                projectile.update();
                if (projectile.y < -24) {
                    projectile = null;
                }
            }
        }

        for (Enemy enemy : enemyList) {
            if (enemy != null) {
                enemy.update();
            }
        }

        if (getEnemySpawnFrameRateCount >= enemySpawnFrameRate && spawnCount < 10) {
            spawnEnemies();
            getEnemySpawnFrameRateCount = 0;
            spawnCount++;
        }

        getEnemySpawnFrameRateCount++;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);

        for (Projectile projectile : projectileList) {
            if (projectile != null) {
                projectile.draw(g2);
            }
        }

        for (Enemy enemy : enemyList) {
            if (enemy != null) {
                enemy.draw(g2);
            }
        }

        g2.dispose();
    }


    public void spawnEnemies() {
        enemy = new Enemy(this);
        enemy.set(500,-48);
        enemyList.add(enemy);
    }


}
