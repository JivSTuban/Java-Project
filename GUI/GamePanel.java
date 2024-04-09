package GUI;

import Controles.KeyHandler;
import Entities.Items.SuperItem;
import Entities.Player;
import Entities.Toxin;
import Tile.world1.DesignTileManager;
import Tile.world1.CollisionTileManger;
import Tile.world1.OutsideTiles;
import Tile.world1.TileManager;

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
    public int maxWorldCol = 80;
    public int maxWorldRow = 80;
    public int worldWidth = tileSize * maxWorldCol;
    public int worldHeight = tileSize * maxWorldRow;

    final int FPS = 144;
    TileManager tileManager = new TileManager(this);
    CollisionTileManger collisionTileManger = new CollisionTileManger(this);
    DesignTileManager designTileManager = new DesignTileManager(this);
    OutsideTiles outsideTiles = new OutsideTiles(this);

    KeyHandler keyH = new KeyHandler();



    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);

    public UI ui = new UI(this, player);

    public SuperItem objItem[] = new SuperItem[99];
    public Toxin toxins[] = new Toxin[199];


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setupGame(){
        aSetter.setItem();
        aSetter.setToxin();

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
        outsideTiles.draw(g2);
        tileManager.draw(g2);
        collisionTileManger.draw(g2);
        designTileManager.draw(g2);
        //Item
        for (SuperItem superItem : objItem) {
            if (superItem != null) {
                superItem.draw(g2, this);
            }
        }
        //toxins
        for (Toxin toxin : toxins) {
            if (toxin != null) {
                toxin.draw(g2, this);
            }
        }

        player.draw(g2);
        ui.draw(g2);

        g2.dispose();
    }
}