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

}
