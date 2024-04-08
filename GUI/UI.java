package GUI;

import Entities.Items.ItemBoots;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Font arial_40;
    BufferedImage image;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40= new Font("Arial", Font.PLAIN,40);
        ItemBoots boots = new ItemBoots();

        image = boots.image;
    }

    public void draw(Graphics2D g2){
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawImage(image, gp.tileSize/2, gp.tileSize, gp.tileSize,gp.tileSize,null);
        g2.drawString( ""+gp.player.boots,74,90);

    }

}
