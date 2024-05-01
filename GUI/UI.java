package GUI;

import Controles.Cooldown;
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
    public Graphics2D g2;
    boolean startUp = true;
    public boolean hackFailed = false;
    Font arial_20,arial_16, arial_14, arial_10, arial_12;

    Cooldown blink = new Cooldown(300);
    public HackingUI hacking   ;

    BufferedImage accessCard,healthImage,bootsImage,coinImage,selectItem;
    DecimalFormat df = new DecimalFormat("###,###.##");
    boolean drawBoots = false;
    public String currentDialogue = "";
    public int slotCol = 0;
    public int slotRow = 0;

     InventoryHUD  invHUD = new InventoryHUD(gp,this);


    public UI(GamePanel gp ) {
        this.gp = gp;
        arial_20 = new Font("Arial", Font.PLAIN,20);
        arial_16 = new Font("Arial", Font.PLAIN,16);
        arial_10 = new Font("Arial", Font.PLAIN,10);
        arial_14 = new Font("Arial", Font.PLAIN,14);
        arial_12 = new Font("Arial", Font.PLAIN,14);
        AccessCard accessCard = new AccessCard();
        // Player player1 = new Player();

    }

    public void draw(Graphics2D g2){
     this.g2 = g2;
        gameHud(g2);

        if(gp.gameState == gp.playState){
                startUp = true;
        }
        if(gp.gameState == gp.pauseState){

        }
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
        if(gp.gameState == gp.inventoryState){
            invHUD.draw(g2);
            drawInventoryScreen();


        }
        if(gp.gameState == gp.hackingState){
            drawSubWindow(440,80,1100,700,200);
            if(startUp){
                hacking = new HackingUI(gp,this);
                startUp = false;
                hacking.cdToHack.trigger();
            }
            hacking.draw(g2);
            hacking.drawHackingScreen();
           // gp.gameState = gp.versusScreen;
        }
    }

    public void drawDialogueScreen(){
        //Window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*5;
        drawSubWindow(x,y,width,height,0);

        x += gp.tileSize;
        y += gp.tileSize;
        for(String line: currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y += 40;
        }


    }


    public void drawSubWindow(int x, int y, int width, int height, int alpha){
       Color c = new Color(28,32,36,alpha);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,10,10);

        c = new Color(254,196,13);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(2));
       // g2.drawRect(x+5,y+5,width-10,height-10);

    }
    /*-------------------------------------------------------------------------------
                                    Inventory Hud
        ------------------------------------------------------------------------------- */

    private void drawInventoryScreen(){
        //Frame
        int frameX = (gp.tileSize*18)+18;
        int frameY = gp.tileSize+20;
        int frameWidth = gp.tileSize * 6 ;
        int frameHeight= gp.tileSize * 6;

        //Slot
        final int slotXStart = frameX + 10;
        final int slotYStart = frameY + 30;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gp.tileSize +4;
        //draw Players Item
        for(int i=0; i<gp.player.inventory.size();i++){
            g2.drawImage(gp.player.inventory.get(i).image, slotX, slotY+7, 70, 70, null);
            g2.setFont(arial_20);
            if(gp.player.inventory.get(i).quantity != 0)
            g2.drawString(String.valueOf(gp.player.inventory.get(i).quantity),slotX+60,slotY+65);
            g2.setFont(arial_14);

            slotX += slotSize;
            if(i == 4 || i == 9 || i == 14){
                slotX = slotXStart;
                slotY += slotSize;
            }
        }


        //cursor
        int cursorX = slotXStart + (gp.tileSize * slotCol);
        int cursorY = slotYStart + (gp.tileSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;
        // Draw Cursor
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));

        try {
            selectItem = ImageIO.read(getClass().getResourceAsStream("/res/Inventory/selectItem.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
           g2.drawImage(selectItem, cursorX, cursorY, cursorWidth+5, cursorHeight, null);

        int dFrameX = frameX;
        int dFrameY = frameY +frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize*3;
        drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight,0);

        int textX = dFrameX + 20;
        int textY = (dFrameY + gp.tileSize)-70;
        g2.setFont(g2.getFont().deriveFont(12F));

        int itemIndex = getItemIndexOnSlot();
        if(itemIndex < gp.player.inventory.size()){
            g2.setFont(arial_20);
            g2.drawString(gp.player.inventory.get(itemIndex).invLabel,1480,490);
            g2.setFont(arial_16);
            for(String line: gp.player.inventory.get(itemIndex).description.split("\n")){
                g2.drawString(line,textX,textY-30);
                textY += 32;
            }

        }


    }
    public int getItemIndexOnSlot(){
        return slotCol + (slotRow * 5);
    }
    public void alert(String alert,int x, int y){
        g2.drawString(alert,x,y);
    }


    private void gameHud(Graphics2D g2){


        g2.setFont(arial_16);
        g2.setColor(Color.white);

        /*-------------------------------------------------------------------------------
                                       HealthBar
        ------------------------------------------------------------------------------- */

        try{
            healthImage = ImageIO.read(getClass().getResourceAsStream("/res/HealthBar/HealthBar-01.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        g2.setFont(arial_10);
        int hpPrint = gp.player.getPlayerHP()/((gp.player.maxHP/100))/2;
        drawPlayerHpBar(hpPrint);
        g2.setColor(Color.white);
        g2.drawString( ""+gp.player.playerHP,950,462);
        g2.drawImage(healthImage, 890, 425,56 ,gp.tileSize,null);

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
            g2.drawImage(bootsImage, 20, 950, gp.tileSize-4, gp.tileSize-4, null);
            g2.setFont(arial_20);
            g2.drawString(String.valueOf(gp.player.bootsCD/1000),50,995);
        }

        /*-------------------------------------------------------------------------------
                                     Coin
        ------------------------------------------------------------------------------- */
        try{
            coinImage = ImageIO.read(getClass().getResourceAsStream("/res/UI/Coin.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        g2.drawImage(coinImage, 1750, 25, gp.tileSize/2,gp.tileSize/2,null);
        g2.setFont(arial_20);
        g2.setColor(Color.YELLOW);
        g2.drawString( ""+df.format(gp.player.getGold()),1800,50);

    }
    void drawPlayerHpBar(int hp){
        g2.setColor(Color.red);
        g2.fillRoundRect(893, 458,hp,12,7,7);

    }
    public void drawImageInForm(BufferedImage image, int x, int y, int width, int height){
        g2.drawImage(image, x, y, width, height, null);
    }
}

