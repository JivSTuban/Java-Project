package GUI;

import Entities.Items.AccessCard;
import Entities.Items.ItemBoots;
import Entities.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Font arial_30;
    BufferedImage image,healthImage;
    boolean drawBoots = false;

    public UI(GamePanel gp ,Player player) {
        this.gp = gp;
        arial_30= new Font("Arial", Font.PLAIN,30);
        AccessCard accessCard = new AccessCard();
        // Player player1 = new Player();
        image = accessCard.image;

    }


    public void draw(Graphics2D g2){
        g2.setFont(arial_30);
        g2.setColor(Color.white);

        g2.drawImage(image, gp.tileSize/2, gp.tileSize, gp.tileSize,gp.tileSize,null);
        //  g2.drawString("x"+);
        g2.drawString( "x"+gp.player.accessCard,74,90);

        try {
            if(gp.player.playerHP < 10)
                healthImage = ImageIO.read(getClass().getResourceAsStream("/res/HealthBar/HealthBar-9.png.png"));
            else if(gp.player.playerHP < 20)
                healthImage = ImageIO.read(getClass().getResourceAsStream("/res/HealthBar/HealthBar-8.png.png"));
            else if(gp.player.playerHP < 30)
                healthImage = ImageIO.read(getClass().getResourceAsStream("/res/HealthBar/HealthBar-7.png.png"));
            else if(gp.player.playerHP < 40)
                healthImage = ImageIO.read(getClass().getResourceAsStream("/res/HealthBar/HealthBar-6.png.png"));
            else if(gp.player.playerHP < 50)
                healthImage = ImageIO.read(getClass().getResourceAsStream("/res/HealthBar/HealthBar-5.png.png"));
            else if(gp.player.playerHP < 60)
                healthImage = ImageIO.read(getClass().getResourceAsStream("/res/HealthBar/HealthBar-4.png.png"));
            else if(gp.player.playerHP < 70)
                healthImage = ImageIO.read(getClass().getResourceAsStream("/res/HealthBar/HealthBar-4.png.png"));
            else if(gp.player.playerHP < 80)
                healthImage = ImageIO.read(getClass().getResourceAsStream("/res/HealthBar/HealthBar-3.png.png"));
            else if(gp.player.playerHP < 90)
                healthImage = ImageIO.read(getClass().getResourceAsStream("/res/HealthBar/HealthBar-2.png.png"));
            else if(gp.player.playerHP <= 100)
                healthImage = ImageIO.read(getClass().getResourceAsStream("/res/HealthBar/HealthBar-1.png.png"));

        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions properly
        }
                g2.drawImage(healthImage, 383, 250, gp.tileSize,gp.tileSize,null);
    }

}
