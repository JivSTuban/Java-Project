package GUI;

import Controles.KeyHandler;
import Entities.Entity;
import Entities.Items.SuperItem;
//import Entities.NPC_robot;
import Entities.Player;
import Entities.Toxin;
import Sound.Sound;
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

    final int FPS = 144;
    TileManager tileManager = new TileManager(this);
    CollisionTileManger collisionTileManger = new CollisionTileManger(this);
    DesignTileManager designTileManager = new DesignTileManager(this);
    OutsideTiles outsideTiles = new OutsideTiles(this);

    KeyHandler keyH = new KeyHandler();

    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    //Sound
    Sound sound = new Sound();

    public Player player = new Player(this, keyH);
    public Entity npc[] = new Entity[10];


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
        aSetter.setNPC();
      //  playMusic(0);

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

        for(int i = 0;i<npc.length;i++){
            if(npc[i] != null){
                npc[i].update();
            }
        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        long drawStart = 0;
        drawStart = System.nanoTime();

        Graphics2D g2 = (Graphics2D) g;
       // outsideTiles.draw(g2);
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
        for(int i = 0; i< npc.length;i++){
            if(npc[i] != null){
                npc[i].draw(g2);
            }
        }

        ui.draw(g2);
        long drawEnd = System.nanoTime();
        long passed = drawEnd - drawStart;
        System.out.println("Draw Time: "+passed);


        g2.dispose();
    }
    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic(){
        sound.stop();
    }
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
}