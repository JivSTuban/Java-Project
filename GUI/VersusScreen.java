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
        drawSkillSection();
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
        final int slotXStart = frameX + 10;
        final int slotYStart = frameY + 15;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gp.tileSize +3;
        //draw Players Item
        for(int i=0; i<gp.player.inventory.size();i++){
            g2.drawImage(gp.player.inventory.get(i).image,slotX,slotY,null);

            slotX += slotSize;
            if(i == 4 || i == 9 || i == 14){
                slotX = slotXStart;
                slotY += slotSize;
            }
        }


        //cursor
        int cursorX = slotXStart + (gp.tileSize * slotCol);
        int cursorY = slotYStart + (gp.tileSize * slotRow);
        int cursorWidth = 130;
        int cursorHeight = gp.tileSize;
        // Draw Cursor
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));

        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight,10, 10 );

        int dFrameX = frameX;
        int dFrameY = frameY +frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize*3;
       // drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(12F));

        int itemIndex = getSkillIndexOnSlot();
        if(itemIndex < gp.player.inventory.size()){
            for(String line: gp.player.inventory.get(itemIndex).description.split("\n")){
                g2.drawString(line,textX,textY);
                textY += 32;
            }

        }
        textY += 32;
        g2.drawString("[Z] Use",textX,textY);



    }
    public int getSkillIndexOnSlot(){
        return slotCol + (slotRow * 5);
    }


}
