package Entities;

import GUI.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

    GamePanel gp;
    public int speed;
    public int worldX, worldY;
    public boolean collision = false;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCount = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0 , 0, 48,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionCounter =0;
    public int holder=0;


    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public void setAction(){}
    public void update(){
        setAction();
        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        if (!collisionOn){
            switch (direction){
                case"up":
                    worldY -= getSpeed();
                    break;
                case"down":
                    worldY += getSpeed();
                    break;
                case"left":
                    worldX -= getSpeed();
                    break;
                case"right":
                    worldX += getSpeed();
                    break;

            }
        }
        spriteCount++;
        if(spriteCount>12){
            if (spriteNum==1){
                spriteNum=2;
            } else if (spriteNum==2) {
                spriteNum=1;
            }
            spriteCount = 0;
        }


    }
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            if(direction.equals("up")){
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
            }else if(direction.equals("left")){
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }

            }else if(direction.equals("down")){
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
            }else if(direction.equals("right")){
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }

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
}