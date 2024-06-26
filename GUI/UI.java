package GUI;

import Controles.Cooldown;
import Entities.Items.AccessCard;
import Entities.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Player player;
    public Graphics2D g2;
    boolean startUp = true;
    public boolean hackFailed = false;
    Font arial_20,arial_16, arial_14, arial_10, arial_12,maruMonica;

    Cooldown blink = new Cooldown(300);
    public HackingUI hacking   ;

    BufferedImage accessCard,healthImage,bootsImage,coinImage,selectItem;
    DecimalFormat df = new DecimalFormat("###,###.##");
    boolean drawBoots = false;
    public String currentDialogue = "";
    public int slotCol = 0;
    public int slotRow = 0;

     InventoryHUD  invHUD = new InventoryHUD(gp,this);
     StoreUI  storeHUD = new StoreUI(gp,this);


    public UI(GamePanel gp ) {
        this.gp = gp;
        arial_20 = new Font("Arial", Font.PLAIN,20);
        arial_16 = new Font("Arial", Font.PLAIN,16);
        arial_10 = new Font("Arial", Font.PLAIN,10);
        arial_14 = new Font("Arial", Font.PLAIN,14);
        arial_12 = new Font("Arial", Font.PLAIN,14);
        try {
            InputStream is = getClass().getResourceAsStream("/res/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT,is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AccessCard accessCard = new AccessCard();
        // Player player1 = new Player();

    }

    public void draw(Graphics2D g2){
     this.g2 = g2;
        gameHud(g2);

        //g2.setFont(arial_20);
        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        //playstate
        if(gp.gameState == gp.playState){
                startUp = true;
        }
        //pausestate
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        //dialoguestate
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
        if(gp.gameState == gp.inventoryState){
            invHUD.draw(g2);
            drawInventoryScreen();
        }
        if(gp.gameState == gp.buyState){
            storeHUD.draw(g2);
            drawStoreScreen();
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
            if(!hacking.cdToHack.isOnCooldown()){
                gp.gameState = gp.playState;
                gp.player.setPlayerHP(gp.player.getPlayerHP() - 10);
            }

        }
    }
    public void drawDialogueScreen(){
        //Window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*5;
        drawSubWindow(x,y,width,height,0);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,38F));
        x += gp.tileSize;
        y += gp.tileSize;
        for(String line: currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y += 40;
        }


    }
    public void drawToTalk(int x, int y, int width, int height, int alpha){
        Color backgroundColor = new Color(24, 57, 43);
        g2.setColor(backgroundColor);
        g2.fillRoundRect(x, y, width, height, 10, 10);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
        g2.drawString("Press [Z] to talk",x+30,y+40);
        Color Border = new Color(34,221,13);
        g2.setColor(Border);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,10,10);
    }
     /*-------------------------------------------------------------------------------
                                    Pause Screen
        ------------------------------------------------------------------------------- */
    public void drawPauseScreen(){

        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight;

        g2.drawString(text, x, y);
    }
    public int getXForCenteredText(String text){

        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return  x;
    }


    public void drawSubWindow(int x, int y, int width, int height, int alpha){
        Color backgroundColor = new Color(24, 57, 43);
        g2.setColor(backgroundColor);
        g2.fillRoundRect(x, y, width, height, 10, 10);

        Color Border = new Color(34,221,13);
        g2.setColor(Border);
        g2.setStroke(new BasicStroke(6));
        g2.drawRect(x+5,y+5,width-10,height-10);

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


        int textX = dFrameX + 20;
        int textY = (dFrameY + gp.tileSize)-70;
        g2.setFont(g2.getFont().deriveFont(12F));

        int itemIndex = getItemIndexOnSlot();
        if(itemIndex < gp.player.inventory.size()){
            g2.setFont(arial_20);
            g2.drawString(gp.player.inventory.get(itemIndex).invLabel,1480,490);
            g2.setFont(arial_20);
            for(String line: gp.player.inventory.get(itemIndex).description.split("\n")){
                g2.drawString(line,textX,textY-30);
                textY += 32;
            }

        }


    }
    private void drawStoreScreen(){
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
        for(int i=0; i<gp.storeItem.size();i++){
            g2.drawImage(gp.storeItem.get(i).image, slotX, slotY+7, 70, 70, null);
            g2.setFont(arial_20);
            if(gp.storeItem.get(i).quantity != 0)
                g2.drawString(String.valueOf(gp.storeItem.get(i).quantity),slotX+60,slotY+65);
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


        int textX = dFrameX + 20;
        int textY = (dFrameY + gp.tileSize)-70;
        g2.setFont(g2.getFont().deriveFont(12F));

        int itemIndex = getItemIndexOnSlot();
        if(itemIndex < gp.storeItem.size()){
            g2.setFont(arial_20);
            g2.drawString(gp.storeItem.get(itemIndex).invLabel,1480,490);
            g2.setFont(arial_16);
            for(String line: gp.storeItem.get(itemIndex).description.split("\n")){
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

