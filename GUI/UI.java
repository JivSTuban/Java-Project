package GUI;

import Entities.Items.AccessCard;
import Entities.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Player player;
    Graphics2D g2;
    Font arial_16;
    Font arial_10;
    BufferedImage accessCard,healthImage,bootsImage,coinImage;
    DecimalFormat df = new DecimalFormat("###,###.##");
    boolean drawBoots = false;
    public String currentDialogue = "";
    public int slotCol = 0;
    public int slotRow = 0;


    public UI(GamePanel gp ) {
        this.gp = gp;
        arial_16 = new Font("Arial", Font.PLAIN,16);
        arial_10 = new Font("Arial", Font.PLAIN,10);
        AccessCard accessCard = new AccessCard();
        // Player player1 = new Player();

    }

    public void draw(Graphics2D g2){
     this.g2 = g2;
        gameHud(g2);

        if(gp.gameState == gp.playState){

        }
        if(gp.gameState == gp.pauseState){

        }
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
        if(gp.gameState == gp.inventoryState){
            drawInventoryScreen();
        }
    }

    public void drawDialogueScreen(){
        //Window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*5;
        drawSubWindow(x,y,width,height);

        x += gp.tileSize;
        y += gp.tileSize;
        for(String line: currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y += 40;
        }


    }


    public void drawSubWindow(int x, int y, int width, int height){
       Color c = new Color(0,0,0,190);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height, 35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);

    }
    /*-------------------------------------------------------------------------------
                                    Inventory Hub
        ------------------------------------------------------------------------------- */

    private void drawInventoryScreen(){
        //Frame
        int frameX = gp.tileSize*9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight= gp.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //Slot
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        //cursor
        int cursorX = slotXStart + (gp.tileSize * slotCol);
        int cursorY = slotYStart + (gp.tileSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;
        // Draw Cursor
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight,10, 10 );



    }

    private void gameHud(Graphics2D g2){


        g2.setFont(arial_16);
        g2.setColor(Color.white);
        /*-------------------------------------------------------------------------------
                                      Access card Count
        ------------------------------------------------------------------------------- */
        try {

            accessCard = ImageIO.read(getClass().getResourceAsStream("/res/Inventory/InvAccessCard.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2.drawImage(accessCard, 20, 20, gp.tileSize-2,gp.tileSize-2,null);
        g2.drawString( ""+gp.player.accessCard,53,60);
        /*-------------------------------------------------------------------------------
                                      Salve Count
        ------------------------------------------------------------------------------- */
        try {

            accessCard = ImageIO.read(getClass().getResourceAsStream("/res/Inventory/InvSalve.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2.drawImage(accessCard, 20, 70, gp.tileSize-3,gp.tileSize-2,null);
        g2.drawString( ""+gp.player.salveCount,53,110);


        /*-------------------------------------------------------------------------------
                                       HealthBar
        ------------------------------------------------------------------------------- */
        try{
            int getImgHP = gp.player.getPlayerHP()/(gp.player.maxHP/10);
            if(gp.player.getPlayerHP() <(gp.player.maxHP/10))
                healthImage = ImageIO.read(getClass().getResourceAsStream("/res/HealthBar/HealthBar-01.png"));
            else
                healthImage = ImageIO.read(getClass().getResourceAsStream("/res/HealthBar/HealthBar-0"+getImgHP+".png"));

        }catch(IOException e){
            e.printStackTrace();
        }
        g2.setFont(arial_10);
        g2.drawString( ""+gp.player.playerHP,430,271);
        g2.drawImage(healthImage, 383, 250, gp.tileSize,gp.tileSize,null);

        /*-------------------------------------------------------------------------------
                                     Boots Cooldown
        ------------------------------------------------------------------------------- */
//

        if(gp.player.bootsCD>1000){
            try {
                int get = gp.player.bootsCD/500;
                bootsImage = ImageIO.read(getClass().getResourceAsStream("/res/Boots/boots" + get + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }


            g2.drawImage(bootsImage, 10, 500, gp.tileSize-4, gp.tileSize-4, null);

            g2.drawString(String.valueOf(gp.player.bootsCD/1000),28,528);
        }

        /*-------------------------------------------------------------------------------
                                     Coin
        ------------------------------------------------------------------------------- */
        try{
            coinImage = ImageIO.read(getClass().getResourceAsStream("/res/UI/Coin.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        g2.drawImage(coinImage, 670, 25, gp.tileSize/2,gp.tileSize/2,null);
        g2.setFont(arial_16);
        g2.setColor(Color.YELLOW);
        g2.drawString( ""+df.format(gp.player.getGold()),700,43);

    }
}

