package GUI;

import Controles.Cooldown;
import Controles.KeyHandler;
import Entities.Entity;
import Entities.Items.SuperItem;
import Entities.Player;
import Sound.Sound;
import Tile.Versus.BackgroundTM;
import Tile.Versus.DesignTM;
import Tile.world1.DesignTileManager;
import Tile.world1.CollisionTileManger;

import Tile.world1.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class GamePanel extends JPanel implements Runnable {


    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public boolean toxinOn = false;
    public boolean NPCCollide = false;


    //World settings
    public int maxWorldCol = 80;
    public int maxWorldRow = 80;

    final int FPS = 144;
    Cooldown dps = new Cooldown(1000);
    //Main Map




    TileManager tileManager = new TileManager(this);
    CollisionTileManger collisionTileManger = new CollisionTileManger(this);
    DesignTileManager designTileManager = new DesignTileManager(this);
    //Versus Screen
    BackgroundTM  bgTMVersus = new BackgroundTM(this);
    DesignTM dTMVersus = new DesignTM(this);
    //keyhandler
    KeyHandler keyH = new KeyHandler(this);

    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public SuperItem superItem = new SuperItem();
    Random rand = new Random();


    //Sound
    Sound sound = new Sound();

    public Player player = new Player(this, keyH);
    public UI ui = new UI(this);

    public VersusScreen vsScreen  = new VersusScreen(this);
    public Entity[] npc = new Entity[10];
    /*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
                                                                       Game State
     -----------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public int gameState = 1;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int versusScreen = 4;
    public final int inventoryState = 5;


    public SuperItem[] objItem = new SuperItem[99];

     /*-----------------------------------------------------------------------------------------------------------------------
                                                      Versus Screen
     -----------------------------------------------------------------------------------------------------------------------*/
    public Cooldown turnTimer = new Cooldown(6000);
    public Cooldown npcAttackCD = new Cooldown( 6100);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setItem();
        aSetter.setNPC();
          //playMusic(0);
        // playSE(1);

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000 / FPS;

        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            update(keyH);

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void update(KeyHandler keyH) {
        player.update(keyH);
        if(gameState == versusScreen){
            if(turnTimer.isOnCooldown() && !(npcAttackCD.isOnCooldown() )){
                if(npc[player.NPCCollision].getNpcHp() > 1){
                    player.setPlayerHP(player.getPlayerHP() - npc[player.NPCCollision].getNpcDamge());
                    npcAttackCD.trigger();
                    System.out.println("Damage taken: " + player.getPlayerHP());
                }
            }
            if(npc[player.NPCCollision].getNpcHp() < 1){
                player.setGold(player.getGold() +  goldDrop(npc[player.NPCCollision].NPC_name));
                npc[player.NPCCollision]= null;
                gameState = playState;
            }
        }

        if (this.toxinOn) {//check for toxin collision
            if (!dps.isOnCooldown()) {//add timer to the damage make it damage per second
                dps.trigger();//trigger the dps
                player.playerHP--;//minus 1 hp for hero if step on the toxin
                System.out.println(player.playerHP);
            }
        }
        if (player.playerHP < 0)
            player.playerHP = 0;
        for (Entity entity : npc) {
            if (entity != null) {
                entity.update();
            }
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        long drawStart = 0;
        drawStart = System.nanoTime();

        Graphics2D g2 = (Graphics2D) g;
        if (gameState != versusScreen) {
            mainMap(g2);
        }
        if(gameState == versusScreen){
             vsScreen.draw(g2);
        }


        long drawEnd = System.nanoTime();
        long passed = drawEnd - drawStart;
        System.out.println("Draw Time: "+passed);

    }



    /* ---------------------------------------------------------------------------------------------------------------------------
                                                            MAPS
     ---------------------------------------------------------------------------------------------------------------------------*/

    private void mainMap(Graphics2D g2){

        //the main screen
        tileManager.draw(g2);
        collisionTileManger.draw(g2);

        //Item
        for (SuperItem superItem : objItem) {
            if (superItem != null) {
                superItem.draw(g2, this);
            }
        }
        //toxins

        player.draw(g2);
        designTileManager.draw(g2);
        for (Entity entity : npc) {
            if (entity != null) {
                entity.draw(g2);
            }
        }

        ui.draw(g2);



        g2.dispose();
    }


    public void playMusic ( int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic ( int i){
        sound.setFile(i);
        sound.stop();
    }
    public void playSE ( int i){
        sound.setFile(i);
        sound.play();
    }
    double goldDrop(String name){
        if(name.equals("drone"))
            return rand.nextDouble(100,150);
        return 0;
    }

}