package Entities;

import GUI.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    GamePanel gp;
    private int speed;
    public int worldX, worldY;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCount = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = null;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
<<<<<<< HEAD

}
=======
}
>>>>>>> b0dc4c552a02e4b70a86ad17e94b808fcf44bd62
