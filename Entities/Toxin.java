package Entities;

import GUI.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Toxin {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 2, 2);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public Toxin() {
        name = "toxin";
        try{
//            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/0toxin.png")));
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/Items/Chest.png")));
        }catch(IOException e){
            e.printStackTrace();

        }
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

}
