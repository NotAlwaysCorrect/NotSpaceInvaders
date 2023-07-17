package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main implements Runnable{
    JFrame frame;
    JPanel startMenu;
    JLabel startGameLabel;
    public boolean gameScreenIsFocused = false;
    public char direction = 'U';
    Thread gameThread;



    public Main() {
//        myWindow = new Window();

        frame = new JFrame();
//        frame.setUndecorated(true);

        startMenu = new JPanel();
        startMenu.setBackground(Color.BLACK);
        startMenu.setLayout(new GridBagLayout());

        startGameLabel = new JLabel("Start Game");
        startGameLabel.setFont(new Font("SansSerif",Font.PLAIN, 150));
        startGameLabel.setForeground(Color.YELLOW);
        startMenu.add(startGameLabel);

        GamePanel gamePanel = new GamePanel();

        addKeyActions(startMenu, "ENTER", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(startMenu);
                frame.add(gamePanel);
                gameScreenIsFocused = gamePanel.requestFocusInWindow();
                frame.revalidate();
                frame.repaint();
            }
        });

        startMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                frame.remove(startMenu);
                frame.add(gamePanel);
                gameScreenIsFocused = gamePanel.requestFocusInWindow();
                frame.revalidate();
                frame.repaint();
            }
        });




        frame.add(startMenu, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Space Invaders");
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        gamePanel.startGameThread();


    }



    public static void addKeyActions(JComponent c, String key, final Action action) {
        c.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), key);
        c.getActionMap().put(key, action);
        c.setFocusable(true);
    }
    public static void main(String[] args) {
        new Main();
    }


    @Override
    public void run() {

    }
}