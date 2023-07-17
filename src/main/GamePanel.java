import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GamePanel extends JPanel implements Runnable{

////    Screen Settings
//    final int ORIGINAL_TILE_SIZE = 16;
//    final int SCALE = 3;
//    final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
//    final int MAX_SCREEN_COLUMN = 16;
//    final int MAX_SCREEN_ROW = 12;
//    final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN;
//    final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;


    JPanel gameScreen;
    public boolean gameScreenIsFocused = false;
    Thread gameThread;
    public char direction = 'U';
    public GamePanel() {

//        Screen settings youtube tutorial
//        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));


        this.setBackground(Color.black);
        this.setDoubleBuffered(true);


        gameScreen = new JPanel();
        Main.addKeyActions(gameScreen, "LEFT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameScreenIsFocused)
                    direction = 'L';
            }
        });
        Main.addKeyActions(gameScreen, "RIGHT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameScreenIsFocused)
                    direction = 'R';
            }
        });
        Main.addKeyActions(gameScreen, "SPACE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameScreenIsFocused) {
//                    Something happens
//                    Shooting

                }
            }
        });


    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread != null) {
//            System.out.println("Game Thread is running!");
            update();
            repaint();
        }
    }
    public void update() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);

        g2.fillRect(100,100,48,48);

        g2.dispose();
    }
}
