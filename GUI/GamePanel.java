package GUI;

import Controles.KeyHandler;
import Entities.Player;
import Tile.ForeGround;
import Tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //World settings
    public int maxWorldCol = 30;
    public int maxWorldRow = 24;
    public int worldWidth = tileSize * maxWorldCol;
    public int worldHeight = tileSize * maxWorldRow;

    final int FPS = 60;
    TileManager tileManager = new TileManager(this);

    ForeGround foreGround = new ForeGround(this);
    KeyHandler keyH = new KeyHandler();

    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;

        double nextDrawTime = System.nanoTime()+drawInterval;

        while(gameThread!=null){

            update(keyH);
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if (remainingTime<0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public void update(KeyHandler keyH){
       player.update(keyH);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        foreGround.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
