package GUI;

import Controles.Cooldown;
import Controles.KeyHandler;
import Entities.Entity;
import Entities.Items.AccessCard;
import Entities.Items.SuperItem;
import Entities.NPC_Drone;
import Entities.NPC_OptimusKhai;
import Entities.Player;
import LoginRegister.LoginForm;
import Sound.Sound;
import Tile.Versus.BackgroundTM;
import Tile.Versus.DesignTM;
import Tile.world1.DesignTileManager;
import Tile.world1.CollisionTileManger;

import Tile.world1.Foreground;
import Tile.world1.TileManager;
import Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class GamePanel extends JPanel implements Runnable {
    private boolean isDead = false;

    final int originalTileSize = 16;
    final int scale = 5;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 22;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public boolean toxinOn = false;
    public boolean footStepOn = false;
    public boolean NPCCollide = false;


    //World settings
    public int maxWorldCol = 80;
    public int maxWorldRow = 80;

    final int FPS = 144;
    Cooldown dps = new Cooldown(1000);
    Cooldown footStepCd = new Cooldown(1200);
    Cooldown footRemoveCd = new Cooldown(1000);
    //Main Map
    User user;



    TileManager tileManager = new TileManager(this);
    CollisionTileManger collisionTileManger = new CollisionTileManger(this);
    DesignTileManager designTileManager = new DesignTileManager(this);
    Foreground foregroundTileManager = new Foreground(this);
    //Versus Screen
    BackgroundTM  bgTMVersus = new BackgroundTM(this);
    DesignTM dTMVersus = new DesignTM(this);
    //keyhandler
    public KeyHandler keyH = new KeyHandler(this);

    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public SuperItem superItem = new SuperItem();
    Random rand = new Random();
    LoginForm loginForm;

    //Sound
    Sound sound = new Sound();

    public Player player;
    public UI ui = new UI(this);

    public VersusScreen vsScreen  = new VersusScreen(this);
    public Entity[] npc = new Entity[99];
    /*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
                                                                       Game State
     -----------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public int gameState = 1;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int versusScreen = 4;
    public final int inventoryState = 5;
    public final int hackingState = 6;


    public SuperItem[] objItem = new SuperItem[99];
    public SuperItem[] footStep = new SuperItem[99];
    public SuperItem[] accessCardDrop = new SuperItem[4];



    /*-----------------------------------------------------------------------------------------------------------------------
                                                      Versus Screen
     -----------------------------------------------------------------------------------------------------------------------*/
    public String enemySkillUsed = "";
    public Cooldown turnTimer = new Cooldown(6000);
    public Cooldown npcAttackCD = new Cooldown( 6100);


    public GamePanel(User user) throws SQLException, IOException {
        this.user = user;
        loginForm = new LoginForm(user, "");
        player = new Player(this, keyH, user,loginForm);
        LoginForm loginForm = new LoginForm(user, "");
   //     loginForm.addItemsToPlayer(player);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }


    public void setupGame(LoginForm loginForm) throws SQLException {
        aSetter.setItem(loginForm);
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

        while (gameThread != null ) {

           try{
              if(gameState!=pauseState){
                  update(keyH);
              }
               loginForm.updateLocationToDB(String.valueOf(Math.round((((float) player.worldX / (this.tileSize))))), String.valueOf(Math.round((float) (player.worldY+20.5 )/ (this.tileSize))));
               loginForm.updateMoneyToDB(player.getGold());
           }catch (SQLException e){
               e.printStackTrace();
           }

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

    public void update(KeyHandler keyH) throws SQLException {


       try{
           if(gameState != versusScreen){
               player.update(keyH);
               footStepSettings();
           }
           if(gameState == versusScreen)
               versusMethod();
       }catch (Exception e){
           System.out.println();
       }

        if (player.playerHP < 0){
            player.playerHP = 0;
            isDead = true;
        }

           if(gameState != versusScreen){
               for (Entity entity : npc) {
                   if (entity != null ) {
                       if(!entity.NPC_name.equals("OptimusKhai")){
                           entity.update();

                       }
                   }
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
        else {
            if(player.NPCCollision != 999){
                vsScreen.draw(g2);
                if(npc[player.NPCCollision].getNpcHp() < 1){
                    aSetter.setItemDrop(player.accessCardDropCount,Math.round((((float) player.worldX / (this.tileSize)))), Math.round((float) (player.worldY+20.5 )/ (this.tileSize)));
                    npc[player.NPCCollision] = null;
                }
            }

        }

        long drawEnd = System.nanoTime();
        long passed = drawEnd - drawStart;
        //System.out.println("Draw Time: "+passed);

    }



    /* ---------------------------------------------------------------------------------------------------------------------------
                                                            MAPS
     ---------------------------------------------------------------------------------------------------------------------------*/

    private void mainMap(Graphics2D g2){

        //the main screen
        tileManager.draw(g2);
        collisionTileManger.draw(g2);
        foregroundTileManager.draw(g2);
        //Item
        for (SuperItem superItem : objItem) {
            if (superItem != null) {
                superItem.draw(g2, this);
            }
        }
        //drawFootStep
        for (SuperItem superItem : footStep) {
            if (superItem != null) {
                superItem.draw(g2, this);
            }
        }
        for (SuperItem superItem : accessCardDrop) {
            if (superItem != null) {
                superItem.draw(g2, this);
            }
        }
        //toxins

        player.draw(g2);
        designTileManager.draw(g2);
        for (Entity entity : npc) {
            if (entity != null) {
                    entity.draw(g2,entity.npcScaleX,entity.npcScaleY);
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
            return rand.nextDouble(15,20);
        return 0;
    }

    /* ---------------------------------------------------------------------------------------------------------------------------
                                                            FootStep Settings
     ---------------------------------------------------------------------------------------------------------------------------*/
        private void footStepSettings(){
            if (this.toxinOn) {//check for toxin collision
                if (!dps.isOnCooldown()) {//add timer to the damage make it damage per second
                    dps.trigger();//trigger the dps
                    player.playerHP--;//minus 1 hp for hero if step on the toxin
                    System.out.println(player.playerHP);
                    footStepCd.trigger();
                    footStepOn = true;
                }
            }

            if(footStepOn && !toxinOn) {
                aSetter.setToxin(Math.round((((float) player.worldX / (this.tileSize)))), Math.round((float) (player.worldY+20.5 )/ (this.tileSize)));
            }
            if (!footStepCd.isOnCooldown()) {
                footStepOn = false;
                footRemoveCd.trigger();

                for(int i = 0; i<99;i++){
                    footStep[i] = null;
                }
            }
        }
    /* ---------------------------------------------------------------------------------------------------------------------------
                                                       Versus Settings
    ---------------------------------------------------------------------------------------------------------------------------*/
        private void versusMethod(){
            try{
                if(player.NPCCollision == 6 ){//this is the boss
                    if(gameState == versusScreen){

                        if(turnTimer.isOnCooldown() && !(npcAttackCD.isOnCooldown() )){
                            if(npc[player.NPCCollision].getNpcHp() > 1){
                                int enemyDMG = npc[player.NPCCollision].getDamage();
                                player.setPlayerHP(player.getPlayerHP() - enemyDMG);
                                System.out.println("(Enemy) -- damage = "+enemyDMG);
                                enemySkillUsed = npc[player.NPCCollision].getSkillName();
                                npcAttackCD.trigger();
                                System.out.println( "(Enemy) -- Current HP: "+npc[player.NPCCollision].npcHp);
                                System.out.println("(Player Hp)=: "+ player.getPlayerHP());

                            }
                        }
                    }

                    if(npc[player.NPCCollision].getNpcHp() < 1){
                        keyH.isfight = false;
                        player.setGold(player.getGold() +  goldDrop(npc[player.NPCCollision].NPC_name));

                        gameState = playState;
                    }

                }
                else if(player.NPCCollision != 999  ){
                    if(gameState == versusScreen){
//
                        if(turnTimer.isOnCooldown() && !(npcAttackCD.isOnCooldown() )){
                            if(npc[player.NPCCollision].getNpcHp() > 1){
                                player.setPlayerHP(player.getPlayerHP() - npc[player.NPCCollision].getDamage());
                                npcAttackCD.trigger();
                                System.out.println("Damage taken: " + player.getPlayerHP());
                                System.out.println("NPC last HP: "+npc[player.NPCCollision].getNpcHp());
                                if(npc[player.NPCCollision].getNpcHp() < 1){
                                    npc[player.NPCCollision].setNpcHp(0);
                                }
                            }
                        }
                        if(npc[player.NPCCollision].getNpcHp() < 1){
                            loginForm.addEnemyKilledToDB(player.NPCCollision);
                            enemySkillUsed = npc[player.NPCCollision].getSkillName();
                            keyH.isfight = false;
                            player.setGold(player.getGold() +  goldDrop(npc[player.NPCCollision].NPC_name));
                            gameState = playState;
                        }
                    }
                }
            }catch (Exception e){
              e.printStackTrace();
            }


        }


}