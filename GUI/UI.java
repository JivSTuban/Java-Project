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
    Font arial_16;
    BufferedImage image,healthImage,bootsImage,coinImage;
    DecimalFormat df = new DecimalFormat("###,###.##");
    boolean drawBoots = false;

    public UI(GamePanel gp ,Player player) {
        this.gp = gp;
        arial_16 = new Font("Arial", Font.PLAIN,16);
        AccessCard accessCard = new AccessCard();
        // Player player1 = new Player();
        image = accessCard.image;


    }

    public void draw(Graphics2D g2){
        g2.setFont(arial_16);
        g2.setColor(Color.white);
        /*-------------------------------------------------------------------------------
                                      Access card Count
        ------------------------------------------------------------------------------- */
        g2.drawImage(image, gp.tileSize/2, gp.tileSize, gp.tileSize,gp.tileSize,null);
        g2.drawString( "x"+gp.player.accessCard,74,90);

        /*-------------------------------------------------------------------------------
                                       HealthBar
        ------------------------------------------------------------------------------- */
        try{
            int getImgHP = gp.player.getPlayerHP()/10;
            if(gp.player.getPlayerHP() <10)
                healthImage = ImageIO.read(getClass().getResourceAsStream("/res/HealthBar/HealthBar-01.png"));
            else
            healthImage = ImageIO.read(getClass().getResourceAsStream("/res/HealthBar/HealthBar-0"+getImgHP+".png"));

        }catch(IOException e){
            e.printStackTrace();
        }

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

        }
        else {
            try {
                bootsImage = ImageIO.read(getClass().getResourceAsStream("/res/Boots/boots1.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        g2.drawImage(bootsImage, 10, 10, gp.tileSize, gp.tileSize, null);
        if(gp.player.bootsCD>1000){
          g2.drawString(String.valueOf(gp.player.bootsCD/1000),30,36);
        }

        /*-------------------------------------------------------------------------------
                                     Coin
        ------------------------------------------------------------------------------- */
        try{
                healthImage = ImageIO.read(getClass().getResourceAsStream("/res/UI/Coin.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        g2.drawImage(healthImage, 670, 25, gp.tileSize/2,gp.tileSize/2,null);
        g2.setFont(arial_16);
        g2.setColor(Color.YELLOW);
        g2.drawString( ""+df.format(gp.player.getGold()),700,43);


    }

}
