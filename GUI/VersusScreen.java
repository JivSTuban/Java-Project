package GUI;



import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class VersusScreen {
    GamePanel gp;
    Graphics2D g2;
    Font arial_16;
    Font arial_10;
    BufferedImage accessCard,healthImage,bootsImage,coinImage,invHud;
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
            drawSkillSection();
        drawPlayerHpBar(gp.player.getPlayerHP(),100,10);
        drawNPCHpBar(gp.npc[gp.player.NPCCollision].getNpcHp(),400,10);
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
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //Slot
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gp.tileSize;
        //draw Players Item
        for(int i=0; i<gp.player.skills.size();i++){
            g2.drawString(gp.player.skills.get(i).skillName,slotX+20,slotY+30);
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
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));

        g2.drawRoundRect(cursorX*2, cursorY, cursorWidth, cursorHeight,10, 10 );

        int dFrameX = frameX;
        int dFrameY = frameY +frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize*3;
       // drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

        int textX = dFrameX + 30;
        int textY = 400;
        g2.setFont(g2.getFont().deriveFont(14F));

        int itemIndex = getSkillIndexOnSlot();
        if(itemIndex < gp.player.skills.size()){
            for(String line: gp.player.skills.get(itemIndex).description.split("\n")){
                g2.drawString(line,400,textY);
                textY += 32;
            }

        }
        textY += 32;
        g2.drawString("[Enter] Use",textX,textY);



    }
    void drawPlayerHpBar(int hp,int x, int y){
        g2.setColor(Color.red);
        g2.fillRoundRect(x,y,hp/2,10,10,10);

    }
    void drawNPCHpBar(int hp,int x, int y){
        g2.setColor(Color.red);
        g2.fillRoundRect(x,y,hp*3,10,10,10);

    }
    public int getSkillIndexOnSlot(){
        return slotCol + (slotRow * 2);
    }


}
