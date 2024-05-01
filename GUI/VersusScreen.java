package GUI;



import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

public class VersusScreen {
    GamePanel gp;
    Graphics2D g2;
    Font arial_16;
    Font arial_10;
    BufferedImage battleFieldBG,selectBG,player,playerHPHUD,EnemyHPHUD;
    DecimalFormat df = new DecimalFormat("###,###.##");
    public int slotCol = 0;
    public int slotRow = 0;

    public VersusScreen(GamePanel gp ) {
        this.gp = gp;
        arial_16 = new Font("Arial", Font.PLAIN,16);
        arial_10 = new Font("Arial", Font.PLAIN,10);

        // Player player1 = new Player();

    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        if(gp.keyH.zPressed) {
            battleFieldBG =  setup("/res/versus/versusBg");
            g2.drawImage(battleFieldBG, 0, -40, 1920,1080,null);

            player =  setup("/res/PlayerImage/playerVS");
            g2.drawImage(player, 80, 170, 240,300,null);
            selectBG =  setup("/res/versus/blackBG");
            g2.drawImage(selectBG, 90, 670, 1700,330,null);
            selectBG =  setup("/res/versus/select");
            g2.drawImage(selectBG, 90, 670, 1700,330,null);

            drawPlayerHpBar(gp.player.getPlayerHP(),118,115);
            playerHPHUD =  setup("/res/versus/vsHPBarPlayer");
            g2.drawImage(playerHPHUD, 60, 60, 150,70,null);
            int currPos = gp.npc[gp.player.NPCCollision].maxHP - gp.npc[gp.player.NPCCollision].getNpcHp()+573;
            drawNPCHpBar(gp.npc[gp.player.NPCCollision].getNpcHp(),currPos,110);
            EnemyHPHUD =  setup("/res/versus/vsHPBarEnemy");
            g2.drawImage(EnemyHPHUD, 560, 55, 160,70,null);

            Font font = new Font("Arial", Font.PLAIN, 20);
            g2.setFont(font);
            g2.setColor(Color.WHITE);
            g2.drawString(gp.npc[gp.player.NPCCollision].NPC_VSname, 600, 93);


            drawSkillSection();


        }
    }
    public void draw(Graphics2D g2,int enemy){
        this.g2 = g2;
        if(gp.keyH.zPressed) {
            battleFieldBG =  setup("/res/versus/versusBg");
            g2.drawImage(battleFieldBG, 0, -40, 1920,1080,null);

            player =  setup("/res/PlayerImage/playerVS");
            g2.drawImage(player, 80, 170, 240,300,null);
            selectBG =  setup("/res/versus/blackBG");
            g2.drawImage(selectBG, 90, 670, 1700,330,null);
            selectBG =  setup("/res/versus/select");
            g2.drawImage(selectBG, 90, 670, 1700,330,null);

            drawPlayerHpBar(gp.player.getPlayerHP(),118,115);
            playerHPHUD =  setup("/res/versus/vsHPBarPlayer");
            g2.drawImage(playerHPHUD, 60, 60, 150,70,null);
            int currPos = gp.npc[enemy].maxHP - gp.npc[enemy].getNpcHp()+573;
            drawNPCHpBar(gp.npc[enemy].getNpcHp(),currPos,110);
            EnemyHPHUD =  setup("/res/versus/vsHPBarEnemy");
            g2.drawImage(EnemyHPHUD, 560, 55, 160,70,null);

            Font font = new Font("Arial", Font.PLAIN, 20);
            g2.setFont(font);
            g2.setColor(Color.WHITE);
            g2.drawString(gp.npc[enemy].NPC_VSname, 600, 93);


            drawSkillSection();


        }
    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(28,32,36,250);
        g2.setColor(c);
        g2.fillRect(x,y,width,height);

        c = new Color(254,196,13);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(2));
        // g2.drawRect(x+5,y+5,width-10,height-10);

    }
    private void drawSkillSection(){
        //Frame
        int frameX = gp.tileSize;
        int frameY = 350;
        int frameWidth = 650;
        int frameHeight= 150 ;
        //drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //Slot
        final int slotXStart = frameX + 160;
        final int slotYStart = frameY + 350;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gp.tileSize;
        //draw Players Item
        for(int i=0; i<gp.player.skills.size();i++){
            g2.drawString(gp.player.skills.get(i).skillName,slotX+50,slotY+30);
            slotX += slotSize+44;
            if(i == 1){
                slotX = slotXStart;
                slotY += slotSize;
            }
        }


        //cursor
        int cursorX = slotXStart-40 + (gp.tileSize * slotCol);
        int cursorY = slotYStart + (gp.tileSize * slotRow);
        int cursorWidth = gp.tileSize*2;
        int cursorHeight = gp.tileSize;
        // Draw Cursor
        g2.setColor(Color.GREEN);
        g2.setStroke(new BasicStroke(3));

        g2.drawRoundRect(cursorX*2, cursorY, cursorWidth, cursorHeight,10, 10 );

        int dFrameX = frameX;
        int dFrameY = frameY +frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize*3;
       // drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

        int textX = dFrameX + 30;
        int textY = 425;
        g2.setFont(g2.getFont().deriveFont(14F));

        int itemIndex = getSkillIndexOnSlot();
        if(itemIndex < gp.player.skills.size()){
            for(String line: gp.player.skills.get(itemIndex).description.split("\n")){
                g2.drawString(line,530,textY);
                textY += 32;
            }

        }
        textY += 32;
        g2.drawString("[Enter] Use",textX,textY);



    }
    void drawPlayerHpBar(int hp,int x, int y){

        Color color = Color.decode("#2DFE54");
        g2.setColor(color);
        int hpPrint = gp.player.getPlayerHP()/((gp.player.maxHP/100));
        g2.fillRoundRect(x,y,  (hpPrint)-18,10,10,10);

    }
    void drawNPCHpBar(int hp,int x, int y){
        Color color = Color.decode("#2DFE54");
        g2.setColor(color);
        int hpPrint = (gp.npc[gp.player.NPCCollision].getNpcHp()/((gp.npc[gp.player.NPCCollision].maxHP/50))+35);
        g2.fillRoundRect(x,y,hpPrint,12,10,10);

    }
    public int getSkillIndexOnSlot(){
        return slotCol + (slotRow * 2);
    }
    public BufferedImage setup(String imagePath){
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath +".png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }



}
