package GUI;



import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

public class NewGameUI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_16;
    Font arial_10;
    BufferedImage battleFieldBG,selectHUD,player,playerHPHUD,EnemyHPHUD,blackBG,enemyProfile;
    BufferedImage droneGIF;

    DecimalFormat df = new DecimalFormat("###,###.##");
    public int slotCol = 0;
    public int slotRow = 0;


    public NewGameUI(GamePanel gp ) {
        this.gp = gp;
        arial_16 = new Font("Arial", Font.PLAIN,16);
        arial_10 = new Font("Arial", Font.PLAIN,10);

        // Player player1 = new Player();

    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        try{



        }catch (Exception e){
            e.printStackTrace();
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
        int slotSize = gp.tileSize+10;
        //draw Players Item
        for(int i=0; i<gp.newGameOption.size();i++){
            g2.drawString(gp.newGameOption.get(i),slotX+220,slotY+90);
            slotX += slotSize+64;
            if(i == 0){
                slotX = slotXStart;
                slotY += slotSize;
            }
        }


        //cursor
        int cursorX = slotXStart-40 + (gp.tileSize * slotCol);
        int cursorY = (slotYStart+50) + (gp.tileSize * slotRow);
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
        int textY = 760;
        g2.setFont(g2.getFont().deriveFont(32F));

//        int itemIndex = getSkillIndexOnSlot();
//        if(itemIndex < gp.newGameOption.size()){
//            for(String line: gp.newGameOption.get(itemIndex).split("\n")){
//                g2.drawString(line,1230,textY);
//                textY += 72;
//            }
//
//        }
        textY += 32;
        g2.drawString("[Enter] Use",textX,textY);

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
    /* ********************************
            This is the GIF loader
     ******************************** */
    private BufferedImage loadGIFImage(String path) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        return image;
    }



}
