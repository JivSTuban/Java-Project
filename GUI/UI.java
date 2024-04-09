package GUI;

import Entities.Items.AccessCard;
import Entities.Items.ItemBoots;
import Entities.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Font arial_40;
    BufferedImage image;
    boolean drawBoots = false;

    public UI(GamePanel gp ,Player player) {
        this.gp = gp;
        arial_40= new Font("Arial", Font.PLAIN,40);
        AccessCard accessCard = new AccessCard();
       // Player player1 = new Player();
        image = accessCard.image;
    }


    public void draw(Graphics2D g2){
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        g2.drawImage(image, gp.tileSize/2, gp.tileSize, gp.tileSize,gp.tileSize,null);
      //  g2.drawString("x"+);


    }

}
